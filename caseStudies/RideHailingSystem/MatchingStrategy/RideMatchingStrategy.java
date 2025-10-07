package caseStudies.RideHailingSystem.MatchingStrategy;

import java.util.List;

import caseStudies.RideHailingSystem.Cars.CarType;
import caseStudies.RideHailingSystem.Users.Driver;
import caseStudies.RideHailingSystem.Users.Rider;
import caseStudies.RideHailingSystem.Utils.Location;

public interface RideMatchingStrategy {
  Driver match(Rider rider, Location pickup, CarType requestedType, List<Driver> drivers);
}