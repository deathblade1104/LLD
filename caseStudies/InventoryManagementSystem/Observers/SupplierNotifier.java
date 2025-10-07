package caseStudies.InventoryManagementSystem.Observers;

import caseStudies.InventoryManagementSystem.Product.Product;

public class SupplierNotifier implements InventoryObserver {
  private String supplierName;
  private String contactEmail;

  public SupplierNotifier(String supplierName, String contactEmail) {
    this.supplierName = supplierName;
    this.contactEmail = contactEmail;
  }

  @Override
  public void update(Product product) {
    if (product.getQuantity() < product.getThreshold()) {
      System.out.println("Notification sent to " + supplierName
          + " for low stock of " + product.getName());
    }
  }
}