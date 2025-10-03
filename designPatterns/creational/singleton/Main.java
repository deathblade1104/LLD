package designPatterns.creational.singleton;

public class Main {
  public static void main(String[] args) {
    PrinterService s1 = new PrinterService();
    PrinterService s2 = new PrinterService();

    s1.printPage("Hello");
    s2.printPage("World");

    System.out.println("Final page count: " + Printer.getInstance().getPageCount());
  }
}
