# Observer — BookStore Example

Stock notifications when a book's quantity changes.

## UML
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
    +update(int)
    +getObservedStockQuantity(): int
  }
  IStore <|.. BookStore
  ICustomer <|.. BookCustomer
  BookStore --> ICustomer : notifies
```

## Entities
- `IStore`: Subject contract for customer registration and notifications
- `ICustomer`: Observer contract receiving stock updates
- `BookStore`: Concrete subject maintaining stock and notifying customers
- `BookCustomer`: Concrete observer reacting to stock changes

## Run
```bash
javac -d . designPatterns/behavioural/observer/bookstore/*.java
java -cp . designPatterns.behavioural.observer.bookstore.Main
```
