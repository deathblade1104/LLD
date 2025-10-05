# Parking Lot Case Study

Comprehensive parking lot simulation with tickets, waitlist notifications (Observer), pluggable pricing strategy, and a typed grid.

## Problem Statement (brief)
- Support multiple vehicle types with different contiguous spot requirements.
- Multi-floor grid with rows and columns; contiguous allocation per row.
- Park → issue ticket; Unpark → compute charges; render current state.
- If full, add driver+vehicle to a waitlist and notify when a spot becomes available.

## Entities (brief)
- ParkingLot: Maintains a 3D grid of `SpotState`; finds first fit; marks occupy/release.
- ParkingPosition: Record for (floor,row,column) start of allocation.
- SpotState: EMPTY, OCCUPIED_CAR, OCCUPIED_BIKE, OCCUPIED_TRUCK.
- Vehicle: Abstract; `Car`, `Bike`, `Truck`; `VehicleType` encodes `spaceRequired`.
- Ticket: Immutable record of a parking session (id, type, position, inTime, spots).
- ParkingInfo: Immutable internal store of position and in-time.
- ChargingStrategy: Strategy interface; `CalculateCharges` is a flat-rate per spot per hour.
- ParkingLotRenderer: Renders the grid (separation of concerns).
- Driver: Name, phone, and associated Vehicle (1:1 for now).
- Waitlist/Observer: FIFO of Drivers; `WaitlistObserver` gets notified when a spot is served.

## Design Overview
- API: `parkVehicle(Vehicle) -> Optional<Ticket>`; `unParkVehicle(Ticket) -> int`.
- If `parkVehicle` fails via `requestPark(Driver)`, the driver is enqueued.
- On `unParkVehicle`, manager attempts to auto-serve the next driver in waitlist; observers get notified with that driver.
- Pricing via `ChargingStrategy`; easily switchable.
- Rendering moved out of the core model.

## Class Diagram
```mermaid
classDiagram
  %% Core Grid
  class ParkingLot {
    -floors: int
    -rows: int
    -columns: int
    -grid: SpotState[][][]
    -spotsOccupied: int
    +ParkingLot(floors, rows, columns)
    +markSpaceAsOccupied(pos: ParkingPosition, space: int, type: VehicleType) void
    +markSpaceAsUnoccupied(pos: ParkingPosition, space: int) void
    +totalFreeSpots() int
    +getFirstEmptyPositionInGrid(spaceRequired: int) Optional~ParkingPosition~
    +print() void
  }
  class ParkingPosition {
    +floor: int
    +row: int
    +column: int
  }
  class SpotState {
    <<enumeration>>
    +EMPTY
    +OCCUPIED_CAR
    +OCCUPIED_BIKE
    +OCCUPIED_TRUCK
  }
  class ParkingLotRenderer {
    +print(grid: SpotState[][][]): void
  }

  %% Manager & Pricing
  class ParkingLotManager {
    -ticketIdToInfo: Map~String, ParkingInfo~
    -licenseToTicketId: Map~int, String~
    -waitlist: Waitlist
    +parkVehicle(v: Vehicle) Optional~Ticket~
    +requestPark(d: Driver) Optional~Ticket~
    +unParkVehicle(t: Ticket): int
    +subscribe(obs: WaitlistObserver): void
    +unsubscribe(obs: WaitlistObserver): void
  }
  class ParkingInfo {
    +getPosition(): ParkingPosition
    +getInTime(): Date
  }
  class Ticket {
    +ticketId: String
    +licenseNumber: int
    +vehicleType: VehicleType
    +position: ParkingPosition
    +inTime: Date
    +spotsOccupied: int
  }
  class ChargingStrategy { <<interface>> +calculateCharge(inTime: Date, spots: int) int }
  class CalculateCharges { +calculateCharge(Date, int) int }

  %% Vehicles & Drivers
  class Vehicle { <<abstract>> +getType(): VehicleType +getLicenseNumber(): int +getSpace(): int }
  class Car
  class Bike
  class Truck
  class VehicleType {
    <<enumeration>>
    +CAR
    +BIKE
    +TRUCK
  }
  class VehicleFactory { +createVehicle(type: VehicleType, license: int): Vehicle }
  class Driver { +getName(): String +getPhoneNumber(): String +getVehicle(): Vehicle }

  %% Waitlist & Observer
  class Waitlist {
    +enqueue(d: Driver)
    +peek(): Driver
    +pop(): Driver
    +isEmpty(): boolean
    +notifyAvailable(d: Driver)
  }
  class WaitlistObserver { <<interface>> +onSpotAvailable(d: Driver) }

  ParkingLotManager --> ParkingLot
  ParkingLotManager --> ParkingInfo
  ParkingLotManager --> ChargingStrategy
  ParkingLotManager --> Waitlist
  ParkingInfo --> ParkingPosition
  ParkingLotRenderer ..> ParkingLot
  Vehicle <|-- Car
  Vehicle <|-- Bike
  Vehicle <|-- Truck
  VehicleFactory --> Vehicle
  Driver --> Vehicle
  Waitlist --> WaitlistObserver : notifies
```

## Sequence: Waitlist Serve & Notify
```mermaid
sequenceDiagram
  participant Driver as Driver (waitlisted)
  participant Manager as ParkingLotManager
  participant Lot as ParkingLot
  participant WL as Waitlist
  participant Obs as WaitlistObserver

  Manager->>WL: enqueue(driver) [when requestPark fails]
  Note over Manager: later
  Manager->>Lot: unParkVehicle(ticket)
  Manager->>WL: peek()
  alt can park
    Manager->>Lot: markSpaceAsOccupied(pos, space, type)
    Manager->>WL: notifyAvailable(driver)
    WL->>Obs: onSpotAvailable(driver)
    WL->>WL: pop()
  else cannot park yet
    Manager-->>Manager: keep driver queued
  end
```

## Run
From repository root:
```bash
javac -d . caseStudies/ParkingLot/**/**/*.java
java -cp . caseStudies.ParkingLot.Main
```

## Notes
- Contiguity: allocation requires contiguous cells in a row; manager scans floors/rows.
- Pricing: ceil(hours) × rate × spots; strategy interface allows swapping rules.
- Tickets: un-park by ticket; state is tracked immutably.
- Observer: drivers failing to park are queued and notified when auto-served.


