# Ride Hailing System Case Study

A modular ride-hailing simulation demonstrating State, Strategy, Factory, Observer, Singleton, and Builder patterns.

## Problem Statement (brief)
- Riders request trips specifying pickup, drop, and car type.
- System matches a suitable driver based on configurable strategy (nearest/highest-rated).
- Trip advances through states from request to completion.
- Fare is calculated via configurable billing strategies (with/without surge).
- Observers receive updates on trip lifecycle events.

## Entities (detailed)
- RideHailingManagerService (Singleton)
  - Purpose: Orchestrates end-to-end trip flow and resource management.
  - Data: `List<Rider>`, `List<Driver>`, `RideMatchingStrategy strategy`.
  - Key ops: `registerRider()`, `registerDriver()`, `setMatchingStrategy()`, `requestRide()`.
  - Notes: Singleton (`getInstance()`) centralizes coordination.

- Trip (State + Observer)
  - Purpose: Represents a single ride with lifecycle, participants, and billing.
  - Data: `Rider`, `Driver`, `Location from/to`, `CarType`, `TripState state`, `TripEventPublisher publisher`, `Bill`, timestamps, `distanceKm`.
  - Key ops: `next()`, `cancel()`, `assignDriver()`, `publish()`.
  - Notes: Publishes human-readable messages to observers on state changes.

- TripState hierarchy (State Pattern)
  - `RequestedState` → `DriverEnRouteState` → `StartedState` → `CompletedState`, with `CancelledState` path.
  - Interface: `TripState` with `next(Trip)`, `cancel(Trip)`, `getStatus()`.

- Matching (Strategy Pattern)
  - `RideMatchingStrategy`: `NearestDriverStrategy`, `HighestRatedDriverStrategy`.
  - Chooses a driver among available drivers given rider location and requested car type.

- Billing (Strategy + Factory)
  - `BillingStrategy`: `DistanceBillingStrategy`, `SurgeBillingStrategy`.
  - Selected via `BillingStrategyFactory.select(surgeOn)`.
  - Uses `Bill.Builder` to compose final bill amounts.

- Cars (Factory)
  - `SimpleCarFactory` creates `MiniCar`, `SedanCar`, `SUVCar` via `CarType`.

- Users
  - `Rider`, `Driver` (availability, rating, `Location`).

- Utils
  - `Location` with `distanceKm(a, b)` used by matching and billing.

- Trip Observer System (Observer Pattern)
  - `TripEventPublisher` with `subscribe()`, `unsubscribe()`, `publish(trip, msg)`.
  - Observers: `RiderNotifier`, `DriverNotifier` implementing `TripObserver.update(trip, msg)`.

## Design Patterns Used
- Singleton: `RideHailingManagerService` ensures a single orchestrator.
- State: `TripState` hierarchy governs trip lifecycle.
- Strategy: `RideMatchingStrategy` and `BillingStrategy` are pluggable.
- Factory: `SimpleCarFactory` (cars), `BillingStrategyFactory` (billing strategies).
- Observer: `TripEventPublisher` with `TripObserver` subscribers.
- Builder: `Bill.Builder` constructs immutable `Bill` with derived totals.

## Class Diagram
```mermaid
classDiagram
  class RideHailingManagerService {
    -instance: RideHailingManagerService
    -riders: List~Rider~
    -drivers: List~Driver~
    -matchingStrategy: RideMatchingStrategy
    +getInstance() RideHailingManagerService
    +registerRider(r: Rider)
    +registerDriver(d: Driver)
    +setMatchingStrategy(s: RideMatchingStrategy)
    +requestRide(rider: Rider, from: Location, to: Location, type: CarType, surge: boolean) Trip
  }

  class Trip {
    -rider: Rider
    -driver: Driver
    -from: Location
    -to: Location
    -requestedCarType: CarType
    -state: TripState
    -publisher: TripEventPublisher
    -bill: Bill
    -distanceKm: double
    +next()
    +cancel()
    +assignDriver(d: Driver)
    +publish(msg: String)
  }

  class TripState {
    <<interface>>
    +next(trip: Trip)
    +cancel(trip: Trip)
    +getStatus() TripStatus
  }
  TripState <|.. RequestedState
  TripState <|.. DriverEnRouteState
  TripState <|.. StartedState
  TripState <|.. CompletedState
  TripState <|.. CancelledState

  class TripEventPublisher {
    +subscribe(obs: TripObserver)
    +unsubscribe(obs: TripObserver)
    +publish(trip: Trip, msg: String)
  }
  class TripObserver {
    <<interface>>
    +update(trip: Trip, msg: String)
  }
  class RiderNotifier { +update(trip: Trip, msg: String) }
  class DriverNotifier { +update(trip: Trip, msg: String) }

  class RideMatchingStrategy {
    <<interface>>
    +match(rider: Rider, from: Location, type: CarType, drivers: List~Driver~) Driver
  }
  RideMatchingStrategy <|.. NearestDriverStrategy
  RideMatchingStrategy <|.. HighestRatedDriverStrategy

  class BillingStrategy {
    <<interface>>
    +generateBill(trip: Trip) Bill
  }
  BillingStrategy <|.. DistanceBillingStrategy
  BillingStrategy <|.. SurgeBillingStrategy
  class BillingStrategyFactory { +select(surge: boolean) BillingStrategy }
  class Bill {
    -baseFare: double
    -distanceKm: double
    -perKm: double
    -surgeMultiplier: double
    -taxes: double
    -total: double
  }
  class BillBuilder {
    +baseFare(v: double) BillBuilder
    +distanceKm(v: double) BillBuilder
    +perKm(v: double) BillBuilder
    +surge(v: double) BillBuilder
    +taxes(v: double) BillBuilder
    +build() Bill
  }

  class SimpleCarFactory { +createCar(type: CarType, plate: String, model: String) Car }
  class Car { <<abstract>> }
  class MiniCar
  class SedanCar
  class SUVCar
  Car <|-- MiniCar
  Car <|-- SedanCar
  Car <|-- SUVCar

  RideHailingManagerService --> RideMatchingStrategy
  RideHailingManagerService --> Trip
  Trip --> TripState
  Trip --> TripEventPublisher
  TripEventPublisher --> TripObserver
  RiderNotifier ..|> TripObserver
  DriverNotifier ..|> TripObserver
  RideHailingManagerService --> BillingStrategyFactory
  BillingStrategyFactory --> BillingStrategy
  Trip --> Bill
  SimpleCarFactory --> Car
  BillBuilder --> Bill : builds
```

## State Diagram (Trip Lifecycle)
```mermaid
stateDiagram-v2
  [*] --> REQUESTED
  REQUESTED --> DRIVER_EN_ROUTE: next()
  REQUESTED --> CANCELLED: cancel()/no driver
  DRIVER_EN_ROUTE --> STARTED: next()
  STARTED --> COMPLETED: next()
  COMPLETED --> [*]
  CANCELLED --> [*]
```

## Enums
- CarType
  - Values: `MINI`, `SEDAN`, `SUV`
  - Fields: `perKm`, `baseFare`
- TripStatus
  - Values: `REQUESTED`, `DRIVER_EN_ROUTE`, `STARTED`, `COMPLETED`, `CANCELLED`, `FAILED`


## Sequence: Request to Completion
```mermaid
sequenceDiagram
  participant Main as RideHailingSystemMain
  participant Mgr as RideHailingManagerService
  participant Match as RideMatchingStrategy
  participant Trip as Trip
  participant Pub as TripEventPublisher
  participant Obs1 as RiderNotifier
  participant Obs2 as DriverNotifier
  participant BillF as BillingStrategyFactory
  participant BillS as BillingStrategy

  Main->>Mgr: requestRide(rider, from, to, type, surge)
  Mgr->>Trip: new Trip(...)
  Mgr->>Pub: subscribe(Obs1), subscribe(Obs2)
  Mgr->>Match: match(rider, from, type, drivers)
  Match-->>Mgr: chosen driver
  alt driver unavailable
    Mgr->>Trip: setState(Cancelled)
    Trip->>Pub: publish("No drivers available")
  else driver found
    Mgr->>Trip: assignDriver(driver)
    Trip->>Pub: publish("Driver assigned ...")
    Trip->>Trip: next() // Requested->DriverEnRoute
    Trip->>Trip: next() // DriverEnRoute->Started
    Trip->>Trip: next() // Started->Completed
    Mgr->>BillF: select(surge)
    BillF-->>Mgr: BillingStrategy
    Mgr->>BillS: generateBill(trip)
    BillS-->>Mgr: Bill
    Mgr->>Trip: setBill(bill)
    Trip->>Pub: publish("Bill generated ...")
  end
```

## Run
From repository root:
```bash
javac -d . caseStudies/RideHailingSystem/**/**/*.java
java -cp . caseStudies.RideHailingSystem.RideHailingSystemMain
```

## Notes / Extensions
- Add geo-indexing for faster nearest-driver lookups.
- Add cancellation flows with penalties.
- Replace String messages with typed events.
- Add tests for matching/billing strategies and state transitions.
- Add persistence and real-time websockets for observer updates.


