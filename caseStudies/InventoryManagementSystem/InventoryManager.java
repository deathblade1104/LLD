package caseStudies.InventoryManagementSystem;

import java.util.ArrayList;
import java.util.List;

import caseStudies.InventoryManagementSystem.Observers.InventoryObserver;
import caseStudies.InventoryManagementSystem.Product.Product;
import caseStudies.InventoryManagementSystem.Product.SimpleProductFactory;
import caseStudies.InventoryManagementSystem.ReplinshmentStrategy.ReplenishmentStrategy;

public class InventoryManager {
  // Singleton instance
  private static InventoryManager instance;

  // System components
  private List<Warehouse> warehouses;
  private SimpleProductFactory productFactory;
  private List<InventoryObserver> observers;
  private ReplenishmentStrategy replenishmentStrategy;

  private InventoryManager() {
    warehouses = new ArrayList<>();
    observers = new ArrayList<>();
    productFactory = new SimpleProductFactory();
  }

  public static synchronized InventoryManager getInstance() {
    if (instance == null) {
      instance = new InventoryManager();
    }
    return instance;
  }

  public void setReplenishmentStrategy(ReplenishmentStrategy strategy) {
    this.replenishmentStrategy = strategy;
  }

  public void addWarehouse(Warehouse warehouse) {
    warehouses.add(warehouse);
  }

  public void removeWarehouse(Warehouse warehouse) {
    warehouses.remove(warehouse);
  }

  public Product getProductBySku(String sku) {
    for (Warehouse warehouse : warehouses) {
      Product product = warehouse.getProductBySku(sku);
      if (product != null) {
        return product;
      }
    }
    return null;
  }

  // NEW: Get total quantity across all warehouses
  public int getTotalQuantityAcrossWarehouses(String sku) {
    int total = 0;
    for (Warehouse warehouse : warehouses) {
      total += warehouse.getAvailableQuantity(sku);
    }
    return total;
  }

  // NEW: Transfer product between warehouses
  public boolean transferProduct(String sku, int quantity, Warehouse fromWarehouse, Warehouse toWarehouse) {
    System.out.println("Attempting to transfer " + quantity + " units of " + sku +
        " from " + fromWarehouse.getName() + " to " + toWarehouse.getName());

    // Check if source warehouse has enough stock
    if (fromWarehouse.getAvailableQuantity(sku) >= quantity) {
      // Remove from source
      if (fromWarehouse.removeProduct(sku, quantity)) {
        // Get product details from source
        Product product = fromWarehouse.getProductBySku(sku);
        if (product == null) {
          // Product was completely removed, get from manager
          product = getProductBySku(sku);
        }

        // Add to destination
        toWarehouse.addProduct(product, quantity);
        System.out.println("Transfer completed successfully!");
        return true;
      }
    } else {
      System.out.println("Transfer failed: Insufficient stock in source warehouse");
    }
    return false;
  }

  public List<Warehouse> getWarehousesWithProduct(String sku) {
    List<Warehouse> warehousesWithProduct = new ArrayList<>();
    for (Warehouse warehouse : warehouses) {
      if (warehouse.getAvailableQuantity(sku) > 0) {
        warehousesWithProduct.add(warehouse);
      }
    }
    return warehousesWithProduct;
  }

  public void checkAndReplenish(String sku) {
    Product product = getProductBySku(sku);
    if (product != null) {
      // If product is below threshold, notify observers
      if (product.getQuantity() < product.getThreshold()) {
        notifyObservers(product);
        // Apply current replenishment strategy
        if (replenishmentStrategy != null) {
          replenishmentStrategy.replenish(product);
        }
      }
    }
  }

  public void performInventoryCheck() {
    for (Warehouse warehouse : warehouses) {
      for (Product product : warehouse.getAllProducts()) {
        if (product.getQuantity() < product.getThreshold()) {
          notifyObservers(product);
          if (replenishmentStrategy != null) {
            replenishmentStrategy.replenish(product);
          }
        }
      }
    }
  }

  // Observer pattern methods
  public void addObserver(InventoryObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(InventoryObserver observer) {
    observers.remove(observer);
  }

  public void notifyObservers(Product product) {
    System.out.println("=== OBSERVER NOTIFICATION TRIGGERED ===");
    System.out.println("Product: " + product.getName() + " (SKU: " + product.getSku() + ")");
    System.out.println("Current quantity: " + product.getQuantity() + ", Threshold: " + product.getThreshold());
    System.out.println("Notifying " + observers.size() + " observers...");

    for (InventoryObserver observer : observers) {
      observer.update(product);
    }
    System.out.println("=== END OBSERVER NOTIFICATIONS ===\n");
  }
}