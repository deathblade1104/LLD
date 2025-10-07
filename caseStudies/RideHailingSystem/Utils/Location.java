package caseStudies.RideHailingSystem.Utils;

public record Location(double latitude, double longitude) {
  public static double distanceKm(Location a, Location b) {
    double dx = a.latitude() - b.latitude();
    double dy = a.longitude() - b.longitude();
    return Math.sqrt(dx * dx + dy * dy) * 111.0;
  }
}