package caseStudies.RideHailingSystem.Users;

public class User {
  protected final String phoneNumber;
  protected final String name;

  public User(String phoneNumber, String name) {
    this.phoneNumber = phoneNumber;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
}