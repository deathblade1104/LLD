package caseStudies.VendingMachine;

public class DispensingState implements VendingMachineState {

  @Override
  public void next(VendingMachine machine) {
    System.out.println("Dispensing product: " + machine.currProductName);
    machine.dispenseProduct(machine.currProductName);

    if (machine.productMap.isEmpty()) {
      System.out.println("All products sold out. Going to OUT_OF_STOCK state.");
      machine.setState(new OutOfStockState());
    } else {
      System.out.println("Returning to IDLE state.");
      machine.setState(new IdleState());
    }
  }

  @Override
  public VendingMachineStatesEnum getStateName() {
    return VendingMachineStatesEnum.DISPENSING;
  }
}
