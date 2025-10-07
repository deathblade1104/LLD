package caseStudies.VendingMachine;

public class IdleState implements VendingMachineState {

  @Override
  public void next(VendingMachine machine) {
    System.out.println("Currently in Idle State...");

    if (machine.productMap.isEmpty()) {
      System.out.println("No products available. Switching to OUT_OF_STOCK state.");
      machine.setState(new OutOfStockState());
    } else {
      System.out.println("User inserted money. Moving to PRODUCT_SELECTED state...");
      machine.setState(new ProductSelectedState());
    }
  }

  @Override
  public VendingMachineStatesEnum getStateName() {
    return VendingMachineStatesEnum.IDLE;
  }
}
