package caseStudies.VendingMachine;

public interface VendingMachineState {
  void next(VendingMachine machine);

  VendingMachineStatesEnum getStateName();
}
