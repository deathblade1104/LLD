package caseStudies.ParkingLot.ParkingLot;

import java.util.Date;

import caseStudies.ParkingLot.Vehicles.VehicleType;

public record Ticket(
    String ticketId,
    int licenseNumber,
    VehicleType vehicleType,
    ParkingPosition position,
    Date inTime,
    int spotsOccupied) {
}
