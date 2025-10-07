package caseStudies.VendingMachine;

import java.util.HashMap;

public class VendingMachine {
  private VendingMachineState state;
  HashMap<String, Product> productMap;
  String currProductName;

  public VendingMachine() {
    this.state = new IdleState();
    this.productMap = new HashMap<>();
  }

  public void setState(VendingMachineState state) {
    this.state = state;
  }

  public void next() {
    state.next(this); // key call in State Pattern
  }

  public VendingMachineStatesEnum getStatus() {
    return state.getStateName();
  }

  public boolean checkProductAvailability(String productName) {
    return productMap.containsKey(productName) && productMap.get(productName).qty() > 0;
  }

  public void upsertProduct(String productName, int newPrice, int qty) {
    Product prevListing = productMap.getOrDefault(productName, null);
    int prevQty = prevListing == null ? 0 : prevListing.qty();
    productMap.put(productName, new Product(productName, newPrice, prevQty + qty));
  }

  public void dispenseProduct(String productName) {
    Product prevListing = productMap.get(productName);
    if (prevListing == null || prevListing.qty() <= 0) {
      throw new IllegalArgumentException("Product not available.");
    }
    int newQty = prevListing.qty() - 1;
    if (newQty > 0) {
      productMap.put(productName, new Product(prevListing.name(), prevListing.price(), newQty));
    } else {
      productMap.remove(productName);
    }
  }

  public int getProductPrice(String productName) {
    Product prevListing = productMap.get(productName);
    if (prevListing == null) {
      throw new IllegalArgumentException("Product not found.");
    }
    return prevListing.price();
  }

  public String getProductNameFromUser() {
    return "Coke"; // hardcoded for demo
  }

  public int getMoneyFromUser() {
    return 50; // hardcoded for demo
  }
}
