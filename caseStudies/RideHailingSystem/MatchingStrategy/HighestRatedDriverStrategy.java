package caseStudies.RideHailingSystem.MatchingStrategy;

import java.util.List;

import caseStudies.RideHailingSystem.Cars.CarType;
import caseStudies.RideHailingSystem.Users.Driver;
import caseStudies.RideHailingSystem.Users.Rider;
import caseStudies.RideHailingSystem.Utils.Location;

public class HighestRatedDriverStrategy implements RideMatchingStrategy {
  @Override
  public Driver match(Rider rider, Location pickup, CarType requestedType, List<Driver> drivers) {
    Driver best = null;
    double bestRating = -1.0;
    for (Driver d : drivers) {
      if (!d.isAvailable())
        continue;
      if (d.getCar().getCarType() != requestedType)
        continue;
      if (d.getRating() > bestRating) {
        bestRating = d.getRating();
        best = d;
      }
    }
    return best;
  }
}
