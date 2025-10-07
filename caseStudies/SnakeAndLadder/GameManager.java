package caseStudies.SnakeAndLadder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import caseStudies.SnakeAndLadder.Dice.Dice;
import caseStudies.SnakeAndLadder.GameBoard.GameBoard;
import caseStudies.SnakeAndLadder.GameCompletionStrategy.GameCompletionStrategy;
import caseStudies.SnakeAndLadder.GameEvent.ConsoleAnnouncer;
import caseStudies.SnakeAndLadder.GameEvent.GameEventPublisher;
import caseStudies.SnakeAndLadder.GameStates.CompletedState;
import caseStudies.SnakeAndLadder.GameStates.GameState;
import caseStudies.SnakeAndLadder.GameStates.WaitingState;
import caseStudies.SnakeAndLadder.Player.Player;

public class GameManager {
  // Singleton
  private static final GameManager INSTANCE = new GameManager();

  public static GameManager getInstance() {
    return INSTANCE;
  }

  private GameManager() {
  }

  // Core config
  private final List<Player> players = new ArrayList<>();
  private GameBoard board;
  private Dice dice;
  private GameCompletionStrategy completionStrategy;

  // Events
  private final GameEventPublisher publisher = new GameEventPublisher();

  // State
  private GameState state;
  private int turnIndex = 0;
  private int turnCount = 0;
  private String winner = null;

  // Config setters
  public void setBoard(GameBoard board) {
    this.board = board;
  }

  public void setDice(Dice dice) {
    this.dice = dice;
  }

  public void setCompletionStrategy(GameCompletionStrategy strategy) {
    this.completionStrategy = strategy;
  }

  public void addPlayer(String name) {
    players.add(new Player(name));
  }

  // Accessors
  public List<Player> getPlayers() {
    return Collections.unmodifiableList(players);
  }

  public GameBoard getBoard() {
    return board;
  }

  public Dice getDice() {
    return dice;
  }

  public GameCompletionStrategy getCompletionStrategy() {
    return completionStrategy;
  }

  public GameEventPublisher getPublisher() {
    return publisher;
  }

  // State helpers
  public void setState(GameState newState) {
    this.state = newState;
    this.state.onEnter(this);
  }

  public int getTurnIndex() {
    return turnIndex;
  }

  public void setTurnIndex(int idx) {
    this.turnIndex = idx;
  }

  public int getTurnCount() {
    return turnCount;
  }

  public void incrementTurnCount() {
    this.turnCount++;
  }

  public String getWinner() {
    return winner;
  }

  public void setWinner(String w) {
    this.winner = w;
  }

  // Run loop
  public void startGame() {
    publisher.subscribe(new ConsoleAnnouncer());
    setState(new WaitingState());
    // WaitingState moves us to InProgressState
    while (!(state instanceof CompletedState)) {
      state.playTurn(this);
    }
  }
}
