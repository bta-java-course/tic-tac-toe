package com.buseduc.javacourse.tictactoe.artifacts.core;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GameState {
private int []gameState;
private GameState previous;

public GameState(int[] gameState){
    this.gameState = gameState;
}

public GameState(int[] gameState, GameState previous){
    this.gameState = gameState;
    this.previous = previous;
    System.out.println(this);
    System.out.println(this.previous);
}

public int[] getGameState(){
return gameState;
}

public void setGameState(int[] gameState){
    this.gameState = gameState;
}

@Override
    public String toString(){
  String toRender = "[";
  toRender += Arrays.stream(this.gameState)
          .mapToObj(String :: valueOf)
          .collect(Collectors.joining(","));
  toRender +="]";
  return  toRender;
}

}
