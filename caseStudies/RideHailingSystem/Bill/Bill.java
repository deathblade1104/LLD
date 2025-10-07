package caseStudies.RideHailingSystem.Bill;

public class Bill {
  private final double baseFare;
  private final double distanceKm;
  private final double perKm;
  private final double surgeMultiplier;
  private final double taxes;
  private final double total;

  Bill(double baseFare, double distanceKm, double perKm, double surgeMultiplier, double taxes, double total) {
    this.baseFare = baseFare;
    this.distanceKm = distanceKm;
    this.perKm = perKm;
    this.surgeMultiplier = surgeMultiplier;
    this.taxes = taxes;
    this.total = total;
  }

  public double baseFare() {
    return baseFare;
  }

  public double distanceKm() {
    return distanceKm;
  }

  public double perKm() {
    return perKm;
  }

  public double surgeMultiplier() {
    return surgeMultiplier;
  }

  public double taxes() {
    return taxes;
  }

  public double total() {
    return total;
  }

  @Override
  public String toString() {
    return "Bill{base=" + baseFare + ", km=" + distanceKm + ", perKm=" + perKm +
        ", surge=" + surgeMultiplier + ", taxes=" + taxes + ", total=" + total + "}";
  }

  public static class Builder {
    private double baseFare, distanceKm, perKm, surgeMultiplier = 1.0, taxes;

    public Builder baseFare(double v) {
      this.baseFare = v;
      return this;
    }

    public Builder distanceKm(double v) {
      this.distanceKm = v;
      return this;
    }

    public Builder perKm(double v) {
      this.perKm = v;
      return this;
    }

    public Builder surge(double v) {
      this.surgeMultiplier = v;
      return this;
    }

    public Builder taxes(double v) {
      this.taxes = v;
      return this;
    }

    public Bill build() {
      double subtotal = baseFare + (distanceKm * perKm * surgeMultiplier);
      double total = subtotal + taxes;
      return new Bill(baseFare, distanceKm, perKm, surgeMultiplier, taxes, total);
    }
  }
}