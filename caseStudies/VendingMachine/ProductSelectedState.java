package caseStudies.VendingMachine;

public class ProductSelectedState implements VendingMachineState {

  @Override
  public void next(VendingMachine machine) {
    System.out.println("Product selection in progress...");
    String productName = machine.getProductNameFromUser();

    if (!machine.checkProductAvailability(productName)) {
      System.out.println("Product not available. Returning to Idle State.");
      machine.setState(new IdleState());
    } else {
      machine.currProductName = productName;
      System.out.println("Product selected: " + productName + ". Awaiting payment...");
      machine.setState(new AwaitingPaymentState());
    }
  }

  @Override
  public VendingMachineStatesEnum getStateName() {
    return VendingMachineStatesEnum.PRODUCT_SELECTED;
  }
}
