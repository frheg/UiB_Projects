package no.uib.inf101.SpaceInvaders.controller;

import no.uib.inf101.SpaceInvaders.model.GameState;

public interface ControllableInvadersModel {

    /**
     * Gets the gamestate
     */
    GameState getGameState();

    /**
     * Returns an int for the tickspeed
     */
    int gameEngine();

    /**
     * Makes the game run with a tickspeed
     */
    void clockTick();

    /**
     * Switches between gameState.ACTIVE_GAME and gameState.PAUSED_GAME
     */
    void pauseGame();

    /**
     * 
     * @param deltaX for the change in playerX
     * @return the changed x-coordinate of the player
     */
    int setPlayerX(int deltaX);

    /**
     * Spawns a laser
     */
    void spawnLaser();

    /**
     * Spawns an alien
     */
    void spawnAlien();

    /**
     * Resets all game-values and changes gamestate to ACTIVE_GAME
     */
    void restartGame();

}
