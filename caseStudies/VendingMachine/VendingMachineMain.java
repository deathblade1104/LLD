package caseStudies.VendingMachine;

public class VendingMachineMain {
  public static void main(String[] args) {
    VendingMachine vm = new VendingMachine();
    vm.upsertProduct("Coke", 50, 2);

    System.out.println("Initial State: " + vm.getStatus());
    vm.next(); // IDLE -> PRODUCT_SELECTED
    vm.next(); // PRODUCT_SELECTED -> AWAITING_PAYMENT
    vm.next(); // AWAITING_PAYMENT -> DISPENSING
    vm.next(); // DISPENSING -> IDLE
  }
}