package designPatterns.creational.prototype;

public class Square implements IShape {
  private int length;

  public Square(int length) {
    this.length = length;
  }

  public int getLength() {
    return this.length;
  }

  @Override
  public IShape clone() {
    return new Square(this.length);
  }

  @Override
  public String toString() {
    return "Square [length=" + length + "]";
  }
}
