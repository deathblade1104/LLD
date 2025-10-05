package caseStudies.ParkingLot;

import caseStudies.ParkingLot.Drivers.Driver;
import caseStudies.ParkingLot.Drivers.DriverWaiter;
import caseStudies.ParkingLot.ParkingLot.ParkingLot;
import caseStudies.ParkingLot.ParkingLot.Ticket;
import caseStudies.ParkingLot.ParkingLotManager.ParkingLotManager;
import caseStudies.ParkingLot.ParkingLotManager.WaitlistObserver;
import caseStudies.ParkingLot.Vehicles.Vehicle;
import caseStudies.ParkingLot.Vehicles.VehicleFactory;
import caseStudies.ParkingLot.Vehicles.VehicleType;

public class Main {
  public static void main(String[] args) {
    ParkingLot lot = new ParkingLot(
        2, // floors
        2, // rows per floor
        5 // columns per row
    );

    ParkingLotManager manager = new ParkingLotManager(lot);

    Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, 101);
    Vehicle car2 = VehicleFactory.createVehicle(VehicleType.CAR, 102);
    Vehicle bike1 = VehicleFactory.createVehicle(VehicleType.BIKE, 201);
    Vehicle bike2 = VehicleFactory.createVehicle(VehicleType.BIKE, 202);
    Vehicle truck1 = VehicleFactory.createVehicle(VehicleType.TRUCK, 301);
    Vehicle truck2 = VehicleFactory.createVehicle(VehicleType.TRUCK, 302);

    // Drivers with 1:1 vehicle mapping
    Driver dCar1 = new Driver("Alice", "+1-555-0101", car1);
    Driver dTruck1 = new Driver("Bob", "+1-555-0102", truck1);
    Driver dBike1 = new Driver("Charlie", "+1-555-0103", bike1);
    Driver dCar2 = new Driver("Diana", "+1-555-0104", car2);
    Driver dBike2 = new Driver("Eve", "+1-555-0105", bike2);
    Driver dTruck2 = new Driver("Frank", "+1-555-0106", truck2);

    // Observers that will receive notifications
    WaitlistObserver wCar = new DriverWaiter();
    WaitlistObserver wTruck = new DriverWaiter();
    WaitlistObserver wBike = new DriverWaiter();
    manager.subscribe(wCar);
    manager.subscribe(wTruck);
    manager.subscribe(wBike);

    System.out.println("=== Parking vehicles ===");
    Ticket tCar1 = manager.parkVehicle(car1).orElse(null); // occupies 2 spots
    Ticket tTruck1 = manager.parkVehicle(truck1).orElse(null); // occupies 4 spots
    Ticket tBike1 = manager.parkVehicle(bike1).orElse(null); // occupies 1 spot
    Ticket tCar2 = manager.parkVehicle(car2).orElse(null);
    Ticket tBike2 = manager.parkVehicle(bike2).orElse(null);

    // This will likely fail and should enqueue Frank (truck2) for notification
    System.out.println("Request park for truck2; if full, auto-enqueue and notify later");
    manager.requestPark(dTruck2);

    System.out.println("Total free spots now: " + lot.totalFreeSpots());

    System.out.println("=== Unparking vehicles and calculating charges ===");
    int chargeBike1 = tBike1 != null ? manager.unParkVehicle(tBike1) : 0;
    System.out.println("Charges for bike1 (license " + bike1.getLicenseNumber() + "): " + chargeBike1);

    // Unpark truck1 to free 4 contiguous spots, which should allow truck2 to be
    // auto-parked
    int chargeTruck1 = tTruck1 != null ? manager.unParkVehicle(tTruck1) : 0;
    System.out.println("Charges for truck1 (license " + truck1.getLicenseNumber() + "): " + chargeTruck1);

    int chargeCar1 = tCar1 != null ? manager.unParkVehicle(tCar1) : 0;
    System.out.println("Charges for car1 (license " + car1.getLicenseNumber() + "): " + chargeCar1);

    System.out.println("Total free spots after unparking: " + lot.totalFreeSpots());

    System.out.println("=== Edge cases ===");
    // Try unparking a vehicle that's not parked
    try {
      // Use a fake ticket to simulate error
      Ticket fake = new Ticket("fake", 999, VehicleType.BIKE, null, new java.util.Date(), 1);
      manager.unParkVehicle(fake);
    } catch (Exception ex) {
      System.out.println("Expected error while unparking non-parked vehicle: " + ex.getMessage());
    }

    // After free spots, manager will attempt to park next waitlisted driver
    // (truck2) and notify
    // Finally, see final free spots
    System.out.println("Final free spots: " + lot.totalFreeSpots());
  }
}
