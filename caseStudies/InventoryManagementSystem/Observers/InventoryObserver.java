package caseStudies.InventoryManagementSystem.Observers;

import caseStudies.InventoryManagementSystem.Product.Product;

public interface InventoryObserver {
  void update(Product product);
}
