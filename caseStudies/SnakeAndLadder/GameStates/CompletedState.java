package caseStudies.SnakeAndLadder.GameStates;

import caseStudies.SnakeAndLadder.GameManager;

public class CompletedState implements GameState {
  @Override
  public String name() {
    return "COMPLETED";
  }

  @Override
  public void onEnter(GameManager gm) {
    gm.getPublisher().publish("State → COMPLETED");
    gm.getPublisher().publish("🏆 Winner: " + gm.getWinner());
  }

  @Override
  public void playTurn(GameManager gm) {
    /* game over */
    return;
  }
}