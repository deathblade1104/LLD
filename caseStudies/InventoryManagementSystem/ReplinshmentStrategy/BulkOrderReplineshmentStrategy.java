package caseStudies.InventoryManagementSystem.ReplinshmentStrategy;

import caseStudies.InventoryManagementSystem.Product.Product;

public class BulkOrderReplineshmentStrategy extends ReplenishmentStrategy {
  @Override
  public void replenish(Product product) {
    int replenishQuantity = product.getThreshold() * 3; // Order 3x threshold
    product.addStock(replenishQuantity);
    System.out.println("Bulk Order replenishment: Added " + replenishQuantity +
        " units of " + product.getName() + ". New quantity: " + product.getQuantity());
  }
}
