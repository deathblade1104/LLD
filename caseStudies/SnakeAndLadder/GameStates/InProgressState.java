package caseStudies.SnakeAndLadder.GameStates;

import java.util.List;

import caseStudies.SnakeAndLadder.GameManager;
import caseStudies.SnakeAndLadder.Dice.Dice;
import caseStudies.SnakeAndLadder.GameBoard.GameBoard;
import caseStudies.SnakeAndLadder.GameCompletionStrategy.GameCompletionStrategy;
import caseStudies.SnakeAndLadder.Player.Player;

public class InProgressState implements GameState {
  @Override
  public String name() {
    return "IN_PROGRESS";
  }

  @Override
  public void onEnter(GameManager gm) {
    gm.getPublisher().publish("State → IN_PROGRESS");
  }

  @Override
  public void playTurn(GameManager gm) {
    List<Player> players = gm.getPlayers();
    Dice dice = gm.getDice();
    GameBoard board = gm.getBoard();
    GameCompletionStrategy strategy = gm.getCompletionStrategy();

    Player current = players.get(gm.getTurnIndex());
    int roll = dice.roll();

    int tentative = current.getPosition() + roll;
    if (tentative > board.getSize()) {
      gm.getPublisher()
          .publish(current.getName() + " rolled " + roll + " → overshoot, stays at " + current.getPosition());
    } else {
      int afterEffect = board.applyEffects(tentative);
      String effectNote = (afterEffect != tentative) ? (" (→ " + afterEffect + ")") : "";
      current.setPosition(afterEffect);
      gm.getPublisher().publish(current.getName() + " rolled " + roll + " → " + tentative + effectNote);
    }

    gm.incrementTurnCount();

    if (strategy.isGameOver(current, board, players, gm.getTurnCount())) {
      gm.setWinner(strategy.winnerName(board, players));
      gm.setState(new CompletedState());
      return;
    }

    gm.setTurnIndex((gm.getTurnIndex() + 1) % players.size());
  }
}
