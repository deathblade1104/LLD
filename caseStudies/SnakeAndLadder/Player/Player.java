package caseStudies.SnakeAndLadder.Player;

public class Player {
  private final String name;
  private int position = 1;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int pos) {
    this.position = pos;
  }
}