package caseStudies.SnakeAndLadder.Dice;

import java.util.concurrent.ThreadLocalRandom;

public class StandardDice implements Dice {
  private final int faces;

  public StandardDice(int faces) {
    if (faces < 2)
      throw new IllegalArgumentException("Dice must have >= 2 faces");
    this.faces = faces;
  }

  @Override
  public int roll() {
    return ThreadLocalRandom.current().nextInt(1, faces + 1);
  }
}