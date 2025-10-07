package caseStudies.SnakeAndLadder.GameStates;

import caseStudies.SnakeAndLadder.GameManager;

public interface GameState {
  String name();

  void onEnter(GameManager gm);

  void playTurn(GameManager gm);

}
