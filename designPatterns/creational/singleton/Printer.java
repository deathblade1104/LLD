package designPatterns.creational.singleton;

public class Printer {

  private static Printer instance = null;
  private int pageCount;

  private Printer() {
    pageCount = 0;
  }

  public static Printer getInstance() {
    if (instance == null) {
      instance = new Printer();
    }

    return instance;
  }

  public int getPageCount() {
    return this.pageCount;
  }

  public void incrementPageCount() {
    this.pageCount++;
  }
}
