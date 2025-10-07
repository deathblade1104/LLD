package caseStudies.VendingMachine;

public class AwaitingPaymentState implements VendingMachineState {

  @Override
  public void next(VendingMachine machine) {
    System.out.println("Awaiting payment...");
    int money = machine.getMoneyFromUser();
    int price = machine.getProductPrice(machine.currProductName);

    if (money >= price) {
      System.out.println("Payment accepted. Dispensing product...");
      machine.setState(new DispensingState());
    } else {
      System.out.println("Insufficient funds. Returning to Idle State.");
      machine.setState(new IdleState());
    }
  }

  @Override
  public VendingMachineStatesEnum getStateName() {
    return VendingMachineStatesEnum.AWAITING_PAYMENT;
  }
}
