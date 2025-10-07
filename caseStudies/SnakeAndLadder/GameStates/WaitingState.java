package caseStudies.SnakeAndLadder.GameStates;

import caseStudies.SnakeAndLadder.GameManager;

public class WaitingState implements GameState {
  @Override
  public String name() {
    return "WAITING_FOR_PLAYERS";
  }

  @Override
  public void onEnter(GameManager gm) {
    gm.getPublisher().publish("State → WAITING_FOR_PLAYERS");
    if (gm.getPlayers().size() < 2 || gm.getPlayers().size() > 10)
      throw new IllegalStateException("Players must be between 2 and 10");
    if (gm.getBoard() == null || gm.getDice() == null || gm.getCompletionStrategy() == null)
      throw new IllegalStateException("Board, Dice and CompletionStrategy must be set before starting");
    gm.setState(new InProgressState());
  }

  @Override
  public void playTurn(GameManager gm) {
    /* no-op */ }
}