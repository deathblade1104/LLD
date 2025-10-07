# Observer Pattern

## Overview
The Observer pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

## Examples in this folder
- `bookstore/` — Stock notifications for a bookstore (subject: `BookStore`, observers: `BookCustomer`)
- `weather/` — Temperature updates broadcast (subject: `WeatherStation`, observers: `MobileDisplay`, `WebDashboard`, `AlertSystem`)

## UML (BookStore)
```mermaid
classDiagram
  class IStore { <<interface>>
    +addCustomer(ICustomer)
    +removeCustomer(ICustomer)
    +notifyCustomers()
  }
  class ICustomer { <<interface>>
    +update(int)
  }
  class BookStore {
    -customers: List~ICustomer~
    -stockQuantity: int
    +addCustomer(ICustomer)
    +removeCustomer(ICustomer)
    +notifyCustomers()
    +updateQuantity(int)
  }
  class BookCustomer {
    -observedStockQuantity: int
    -store: IStore
    +update(int)
  }
  IStore <|.. BookStore
  ICustomer <|.. BookCustomer
  BookStore --> ICustomer : notifies
```

## UML (Weather)
```mermaid
classDiagram
  class Subject { <<interface>>
    +addObserver(Observer)
    +removeObserver(Observer)
    +notifyObservers(String)
  }
  class Observer { <<interface>>
    +update(String)
  }
  class WeatherStation {
    -currTemp: int
    -observers: List~Observer~
    +updateTemp(int)
  }
  class MobileDisplay { +update(String) }
  class WebDashboard { +update(String) }
  class AlertSystem { +update(String) }
  Subject <|.. WeatherStation
  Observer <|.. MobileDisplay
  Observer <|.. WebDashboard
  Observer <|.. AlertSystem
  WeatherStation --> Observer : notifies
```

## Run
```bash
# BookStore
javac -d . designPatterns/behavioural/observer/bookstore/*.java
java -cp . designPatterns.behavioural.observer.bookstore.Main

# Weather
javac -d . designPatterns/behavioural/observer/weather/*.java
java -cp . designPatterns.behavioural.observer.weather.Main
```
