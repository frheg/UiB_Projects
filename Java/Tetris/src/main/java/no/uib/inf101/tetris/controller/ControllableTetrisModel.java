package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

public interface ControllableTetrisModel {
    
    /** 
     * 
     * @param deltaRow
     * @param deltaCol
     * @return a boolean if the tetromino was moved
     */
    public boolean moveTetromino(int deltaRow, int deltaCol);  

    /**
     * @return a boolean if the rotation was done aswell as rotating the tetromino
     */
    public boolean rotatedCounterClockwise();  
    
    /** Drops the tetromino */  
    public void dropTetromino();               
    
    /** Gets the gamestate */
    public GameState getGameState();         
    
    /** Returns an int for the tickspeed */   
    public int gameEngine();      
    
    /** Makes the game run with a tickspeed 
     * and checks if the tetromino was moved */              
    public void clockTick();                 
    
    /** Switches between gamestates pause and active */   
    public void pauseGame();              
    
    /** Sets gamestate to active */      
    public void startGame();               
    
    /** Restarts the game */     
    public void restartGame();                  
}
