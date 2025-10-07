package caseStudies.InventoryManagementSystem.ReplinshmentStrategy;

import caseStudies.InventoryManagementSystem.Product.Product;

public abstract class ReplenishmentStrategy {
  abstract public void replenish(Product product);
}
