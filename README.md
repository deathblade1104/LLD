# LLD: Design Patterns and Java Low-Level Design

This repository contains comprehensive implementations of software design patterns in Java, with detailed documentation, UML diagrams, and practical examples. The goal is to build strong intuition for designing maintainable, extensible, and testable systems.

## 📚 Repository Structure

### Creational Patterns
- **[Simple Factory](designPatterns/creational/simpleFactory/README.md)** - Centralized object creation
- **[Factory Method](designPatterns/creational/factory/README.md)** - Delegate creation to subclasses
- **[Builder](designPatterns/creational/builder/README.md)** - Step-by-step object construction
- **[Prototype](designPatterns/creational/prototype/README.md)** - Clone existing objects
- **[Singleton](designPatterns/creational/singleton/README.md)** - Single instance guarantee

### Structural Patterns
- **[Adapter](designPatterns/structural/adapter/README.md)** - Interface compatibility
- **[Decorator](designPatterns/structural/decorator/README.md)** - Dynamic behavior extension

### Behavioral Patterns
- **[Observer](designPatterns/behavioural/observer/README.md)** - Event-driven communication

## 🚀 Quick Start

### Prerequisites
- Java 17+ (recommended)
- No build tools required; examples use `javac`

### Running Examples
From the repository root:

```bash
# Compile and run any pattern
javac -d . designPatterns/creational/singleton/*.java
java -cp . designPatterns.creational.singleton.Main

# Or compile all patterns at once
find designPatterns -name "*.java" -exec javac -d . {} +
```

### Pattern Navigation
Each pattern directory contains:
- `README.md` - Detailed explanation with UML diagrams
- `Main.java` - Runnable demonstration
- Supporting classes with clear naming
- Package structure following Java conventions

## 🎯 Learning Approach

### For Each Pattern
1. **Read the README** - Understand the problem and solution
2. **Study the UML diagram** - Visualize relationships
3. **Run the code** - See the pattern in action
4. **Modify and experiment** - Understand variations

### Code Conventions
- Clear, descriptive class names
- Small, focused examples
- Composition over inheritance
- Runnable and self-explanatory
- Proper package structure

## 📖 Learning Resources

- [Head First Design Patterns (2nd ed.)](https://www.oreilly.com/library/view/head-first-design/9781492077992/)
- [Refactoring Guru: Design Patterns](https://refactoring.guru/design-patterns)
- [Effective Java (3rd ed.)](https://www.pearson.com/en-us/subject-catalog/p/effective-java/P200000003457/9780134686097)

## 🗺️ Roadmap

### Planned Additions
- **Structural**: Bridge, Composite, Facade, Flyweight, Proxy
- **Behavioral**: Command, Iterator, Mediator, Memento, State, Template Method, Visitor, Chain of Responsibility
- **LLD Problems**: Real-world system design challenges

---

*This repository focuses on deliberate practice—small, high-signal examples over boilerplate. Each pattern is thoroughly documented with practical examples you can run immediately.*