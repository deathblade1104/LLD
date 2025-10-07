# Observer — Weather Example

Broadcast temperature changes to multiple displays.

## UML
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

## Entities
- `Subject`: Contract for registering/removing observers and notifying them
- `Observer`: Receives updates from the subject
- `WeatherStation`: Concrete subject managing temperature and notifications
- `MobileDisplay`, `WebDashboard`, `AlertSystem`: Concrete observers

## Run
```bash
javac -d . designPatterns/behavioural/observer/weather/*.java
java -cp . designPatterns.behavioural.observer.weather.Main
```
