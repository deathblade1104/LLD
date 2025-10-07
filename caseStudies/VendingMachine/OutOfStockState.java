package caseStudies.VendingMachine;

public class OutOfStockState implements VendingMachineState {

  @Override
  public void next(VendingMachine machine) {
    System.out.println("Machine is out of stock.");

    if (!machine.productMap.isEmpty()) {
      System.out.println("Stock refilled. Returning to IDLE state.");
      machine.setState(new IdleState());
    } else {
      System.out.println("Still out of stock. Cannot proceed.");
    }
  }

  @Override
  public VendingMachineStatesEnum getStateName() {
    return VendingMachineStatesEnum.OUT_OF_STOCK;
  }
}
