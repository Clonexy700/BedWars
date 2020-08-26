package com.clonexy700.bedwars.utils;

public enum GameState {
    LOBBY, INGAME;

    public static GameState gameState;

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        GameState.gameState = gameState;
    }

}
