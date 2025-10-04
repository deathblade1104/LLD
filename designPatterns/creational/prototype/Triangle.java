package designPatterns.creational.prototype;

public class Triangle implements IShape {

  private int perpendicular, base, hypotenuse;

  public Triangle(int perpendicular, int base, int hypotenuse) {
    this.perpendicular = perpendicular;
    this.base = base;
    this.hypotenuse = hypotenuse;
  }

  public int getBase() {
    return this.base;
  }

  public int getPerpendicular() {
    return this.perpendicular;
  }

  public int getHypotenuse() {
    return this.hypotenuse;
  }

  @Override
  public IShape clone() {
    return new Triangle(this.perpendicular, this.base, this.hypotenuse);
  }

  @Override
  public String toString() {
    return "Triangle [perpendicular=" + perpendicular + ", base=" + base + ", hypotenuse=" + hypotenuse + "]";
  }
}
