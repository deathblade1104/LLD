package caseStudies.RideHailingSystem.Users;

public class Rider extends User {
  private static int idCounter = 1;
  private final int id;

  public Rider(String phoneNumber, String name) {
    super(phoneNumber, name);
    this.id = idCounter++;
  }

  public int getId() {
    return id;
  }

}
