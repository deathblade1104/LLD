package caseStudies.InventoryManagementSystem;

import java.util.Arrays;
import java.util.List;

import caseStudies.InventoryManagementSystem.Observers.DashboardAlertSystem;
import caseStudies.InventoryManagementSystem.Observers.SupplierNotifier;
import caseStudies.InventoryManagementSystem.Product.Product;
import caseStudies.InventoryManagementSystem.Product.ProductCategory;
import caseStudies.InventoryManagementSystem.Product.SimpleProductFactory;
import caseStudies.InventoryManagementSystem.ReplinshmentStrategy.BulkOrderReplineshmentStrategy;

public class InventoryManagementMain {
  public static void main(String[] args) {
    System.out.println("=== Inventory Management System Demo ===");

    // Initialize system components
    InventoryManager manager = InventoryManager.getInstance();
    SimpleProductFactory factory = new SimpleProductFactory();

    // Create warehouses
    Warehouse warehouse1 = new Warehouse(1, "Main Warehouse", "New York");
    Warehouse warehouse2 = new Warehouse(2, "Secondary Warehouse", "Los Angeles");

    manager.addWarehouse(warehouse1);
    manager.addWarehouse(warehouse2);

    // Create products
    Product laptop = factory.createProduct(ProductCategory.ELECTRONICS, "LAP001", "Gaming Laptop", 1299.99, 50, 10);
    Product shirt = factory.createProduct(ProductCategory.CLOTHING, "CLO001", "Cotton T-Shirt", 29.99, 100, 20);
    Product milk = factory.createProduct(ProductCategory.GROCERY, "GRO001", "Organic Milk", 4.99, 200, 50);

    // Add products to warehouses
    warehouse1.addProduct(laptop, 50);
    warehouse1.addProduct(shirt, 100);
    warehouse2.addProduct(milk, 200);

    // Set up observers
    List<String> admins = Arrays.asList("admin1@company.com", "admin2@company.com");
    DashboardAlertSystem dashboard = new DashboardAlertSystem("HIGH", admins);
    SupplierNotifier supplier = new SupplierNotifier("TechSupplier Inc", "orders@techsupplier.com");

    manager.addObserver(dashboard);
    manager.addObserver(supplier);

    // Set replenishment strategy
    manager.setReplenishmentStrategy(new BulkOrderReplineshmentStrategy());

    System.out.println("\n=== Initial Inventory Status ===");
    System.out.println("Laptop total across warehouses: " + manager.getTotalQuantityAcrossWarehouses("LAP001"));
    System.out.println("Shirt total across warehouses: " + manager.getTotalQuantityAcrossWarehouses("CLO001"));
    System.out.println("Milk total across warehouses: " + manager.getTotalQuantityAcrossWarehouses("GRO001"));

    System.out.println("\n=== Initial Inventory Check ===");
    manager.performInventoryCheck();

    System.out.println("\n=== Simulating Sales (Removing Stock) ===");
    // Simulate sales - remove stock to trigger low inventory alerts
    warehouse1.removeProduct("LAP001", 45); // Laptop: 50 -> 5 (below threshold 10)
    warehouse1.removeProduct("CLO001", 85); // Shirt: 100 -> 15 (below threshold 20)
    warehouse2.removeProduct("GRO001", 160); // Milk: 200 -> 40 (below threshold 50)

    System.out.println("\n=== Checking Inventory After Sales ===");
    manager.performInventoryCheck();

    System.out.println("\n=== Demonstrating Transfer Operations ===");
    // Show warehouses with laptop before transfer
    List<Warehouse> warehousesWithLaptop = manager.getWarehousesWithProduct("LAP001");
    System.out.println("Warehouses with laptop before transfer: " + warehousesWithLaptop.size());

    // Add laptop to warehouse2 for transfer demo
    warehouse2.addProduct(laptop, 20);
    System.out.println("Added 20 laptops to LA warehouse for transfer demo");

    // Transfer laptops from LA to NYC
    manager.transferProduct("LAP001", 15, warehouse2, warehouse1);

    System.out.println("\n=== Post-Transfer Inventory Status ===");
    System.out.println("NYC Warehouse - Laptop quantity: " + warehouse1.getAvailableQuantity("LAP001"));
    System.out.println("LA Warehouse - Laptop quantity: " + warehouse2.getAvailableQuantity("LAP001"));
    System.out.println("Total laptops across all warehouses: " + manager.getTotalQuantityAcrossWarehouses("LAP001"));

    System.out.println("\n=== Individual Product Check ===");
    manager.checkAndReplenish("LAP001");

    System.out.println("\n=== Final Inventory Status ===");
    System.out.println("Laptop quantity: " + warehouse1.getAvailableQuantity("LAP001"));
    System.out.println("Shirt quantity: " + warehouse1.getAvailableQuantity("CLO001"));
    System.out.println("Milk quantity: " + warehouse2.getAvailableQuantity("GRO001"));

    System.out.println("\n=== Demo Complete ===");
  }
}
