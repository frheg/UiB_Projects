package no.uib.inf101.SpaceInvaders.view;

import java.util.List;

import no.uib.inf101.SpaceInvaders.model.GameState;

public interface InterfaceInvaderView {

    /**
     * 
     * @return the current gamestate
     */
    GameState getGameState();

    /**
     * 
     * @return the players x-position
     */
    int getPlayerX();

    /**
     * 
     * Changes the gamestate between active and paused
     */
    void pauseGame();

    /**
     * 
     * Changes the gamestate to active game when called
     */
    void startGame();

    /**
     * 
     * @return the width of the app in pixels
     */
    int getAppWidth();

    /**
     * 
     * @return the height of the app in pixels
     */
    int getAppHeigth();

    /**
     * 
     * @return a list with all the lasers and its coordinates
     */
    List<List<Integer>> getLaser();

    /**
     * 
     * @return a list with all the aliens and its coordinates
     */
    List<List<Integer>> getAlien();

    /**
     * 
     * @return the current gamescore
     */
    int getScore();

    /**
     * 
     * @return the current health of the player
     */
    int getHealth();
}
