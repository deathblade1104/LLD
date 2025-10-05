package caseStudies.ParkingLot.ParkingLotManager;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import caseStudies.ParkingLot.Drivers.Driver;
import caseStudies.ParkingLot.ParkingLot.ParkingLot;
import caseStudies.ParkingLot.ParkingLot.ParkingPosition;
import caseStudies.ParkingLot.ParkingLot.Ticket;
import caseStudies.ParkingLot.Vehicles.Vehicle;

public class ParkingLotManager {
  private HashMap<String, ParkingInfo> ticketIdToInfo;
  private HashMap<Integer, String> licenseToTicketId;
  private ParkingLot parkingLot;
  private final ChargingStrategy chargingService = new CalculateCharges();
  private final Waitlist waitlist = new Waitlist();

  public ParkingLotManager(
      ParkingLot lot) {
    this.parkingLot = lot;
    this.ticketIdToInfo = new HashMap<>();
    this.licenseToTicketId = new HashMap<>();
  }

  // Observer management
  public void subscribe(WaitlistObserver observer) {
    waitlist.subscribe(observer);
  }

  public void unsubscribe(WaitlistObserver observer) {
    waitlist.unsubscribe(observer);
  }

  public Optional<Ticket> parkVehicle(Vehicle vehicle) {
    Optional<ParkingPosition> posOpt = this.parkingLot.getFirstEmptyPositionInGrid(vehicle.getSpace());
    if (posOpt.isEmpty()) {
      System.out.println("No empty Spaces Found. Vehicle can't be parked");
      return Optional.empty();
    }
    ParkingPosition pos = posOpt.get();
    this.parkingLot.markSpaceAsOccupied(pos, vehicle.getSpace(), vehicle.getType());
    ParkingInfo info = new ParkingInfo(pos);
    String ticketId = UUID.randomUUID().toString();
    ticketIdToInfo.put(ticketId, info);
    licenseToTicketId.put(vehicle.getLicenseNumber(), ticketId);
    this.parkingLot.print();
    Ticket ticket = new Ticket(ticketId, vehicle.getLicenseNumber(), vehicle.getType(), pos, info.getInTime(),
        vehicle.getSpace());
    return Optional.of(ticket);
  }

  // Request by driver; auto-enqueue on failure and notify them later
  public Optional<Ticket> requestPark(Driver driver) {
    Optional<Ticket> t = parkVehicle(driver.getVehicle());
    if (t.isEmpty()) {
      System.out.println("No space; enqueuing driver: " + driver.getName());
      waitlist.enqueue(driver);
    }
    return t;
  }

  public int unParkVehicle(Ticket ticket) {
    ParkingInfo info = ticketIdToInfo.get(ticket.ticketId());

    if (info == null) {
      throw new IllegalStateException("Vehicle not parked in this lot. ");
    }

    this.parkingLot.markSpaceAsUnoccupied(info.getPosition(), ticket.spotsOccupied());
    this.parkingLot.print();
    ticketIdToInfo.remove(ticket.ticketId());
    licenseToTicketId.remove(ticket.licenseNumber());
    int charges = chargingService.calculateCharge(info.getInTime(), ticket.spotsOccupied());
    // Try to serve the next driver on the waitlist
    if (!waitlist.isEmpty()) {
      Driver next = waitlist.peek();
      Optional<Ticket> served = parkVehicle(next.getVehicle());
      if (served.isPresent()) {
        System.out.println("Serving waitlisted driver: " + next.getName());
        waitlist.notifyAvailable(next);
        waitlist.pop();
      }
    }
    return charges;
  }

}
