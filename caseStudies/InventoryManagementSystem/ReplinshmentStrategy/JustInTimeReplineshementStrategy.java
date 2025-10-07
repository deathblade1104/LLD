package caseStudies.InventoryManagementSystem.ReplinshmentStrategy;

import caseStudies.InventoryManagementSystem.Product.Product;

public class JustInTimeReplineshementStrategy extends ReplenishmentStrategy {
  @Override
  public void replenish(Product product) {
    System.out.println("Applying Just-In-Time replenishment for " + product.getName());
  }
}
