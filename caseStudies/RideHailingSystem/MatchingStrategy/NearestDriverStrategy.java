package caseStudies.RideHailingSystem.MatchingStrategy;

import java.util.List;

import caseStudies.RideHailingSystem.Cars.CarType;
import caseStudies.RideHailingSystem.Users.Driver;
import caseStudies.RideHailingSystem.Users.Rider;
import caseStudies.RideHailingSystem.Utils.Location;

public class NearestDriverStrategy implements RideMatchingStrategy {
  @Override
  public Driver match(Rider rider, Location pickup, CarType requestedType, List<Driver> drivers) {
    Driver best = null;
    double bestDist = Double.MAX_VALUE;
    for (Driver d : drivers) {

      if (!d.isAvailable())
        continue;

      if (d.getCar().getCarType() != requestedType)
        continue;

      double dist = Location.distanceKm(pickup, d.getLocation());

      if (dist < bestDist) {
        bestDist = dist;
        best = d;
      }
    }
    return best;
  }
}
