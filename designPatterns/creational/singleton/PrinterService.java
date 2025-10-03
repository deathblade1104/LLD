package designPatterns.creational.singleton;

public class PrinterService {

  private Printer printer;

  public PrinterService() {
    this.printer = Printer.getInstance();
  }

  public void printPage(String str) {
    System.out.println("Printing page: " + str);
    this.printer.incrementPageCount();
    System.out.println("Page count: " + this.printer.getPageCount());
  }

}
