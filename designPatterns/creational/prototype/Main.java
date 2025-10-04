package designPatterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static List<IShape> cloneShapes(List<IShape> shapes) {
    List<IShape> clones = new ArrayList<>();
    for (IShape shape : shapes) { 
      clones.add(shape.clone());
    }
    return clones;
  }

  public static void main(String[] args) {

    List<IShape> shapes = new ArrayList<>();
    shapes.add(new Rectangle(10, 20));
    shapes.add(new Square(15));
    shapes.add(new Triangle(3, 4, 5));

    System.out.println("Original shapes:");
    shapes.forEach(System.out::println);

    List<IShape> clonedShapes = cloneShapes(shapes);

    System.out.println("\nCloned shapes:");
    clonedShapes.forEach(System.out::println);

    System.out.println("\nInstance comparison:");
    for (int i = 0; i < shapes.size(); i++) {
      System.out.println(
          shapes.get(i) + " == " + clonedShapes.get(i) + " ? " + (shapes.get(i) == clonedShapes.get(i)));
    }
  }
}
