package no.uib.inf101.tetris.controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.TetrisView;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class TetrisController implements java.awt.event.KeyListener {
    private ControllableTetrisModel tetrisModel;
    private TetrisView tetrisView;
    private Timer timer;
    private TetrisSong tetrisSong;

    // Constructor
    public TetrisController(ControllableTetrisModel tetrisModel, TetrisView tetrisView) {
        tetrisView.setFocusable(true);
        this.tetrisView = tetrisView;
        this.tetrisModel = tetrisModel;
        tetrisView.addKeyListener(this);
        this.timer = new Timer(tetrisModel.gameEngine(), this::clockTick);  // Creates a new timer object
        updateClockTimer();         
        timer.start();                      // Starts the clocktimer

        tetrisSong = new TetrisSong();      // Creates a new song object
        tetrisSong.run();                   // Runs the song
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {            // The key-pressed method which states the different behaviours of different keys pressed
        if (tetrisModel.getGameState() == GameState.GAME_OVER) {    // If the gamestate is GAME_OVER it immediatly returns and ends the method
            // return;
            if (e.getKeyCode() == KeyEvent.VK_R) {
                    tetrisModel.restartGame();
            }
        }
        else if (tetrisModel.getGameState() == GameState.ACTIVE_GAME) { // If the game-tate is ACTIVE_GAME, the statements under is possible
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {                   // If left-arrow-key is pressed, move the tetromino -1 in the collumns
                // Left arrow was pressed                       
                tetrisModel.moveTetromino(0, -1);       
            } 
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {             // If right-arrow-key is pressed, move the tetromino -1 in the collumns
                // Right arrow was pressed
                tetrisModel.moveTetromino(0, 1);
            } 
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {              // If down-arrow-key is pressed, move the tetromino +1 in the collumns
                // Down arrow was pressed
                tetrisModel.moveTetromino(1, 0);
            } 
            else if (e.getKeyCode() == KeyEvent.VK_UP) {                // If uo-arrow-key is pressed, move the tetromino +1 in the rows
                // Up arrow was pressed
                tetrisModel.rotatedCounterClockwise();
            } 
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) {             // If the spacebar is pressed, call the dropTetromino method
                // Spacebar was pressed
                tetrisModel.dropTetromino();
            }
        } 
        if (tetrisModel.getGameState() != GameState.GAME_OVER) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) { // If the enter-key is pressed, pause the game with the pauseGame
                                                       // method
                tetrisModel.pauseGame();
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) { // If the enter-key is pressed, start the game with the
                                                              // startGame method
                tetrisModel.startGame();
            }
        }
        tetrisView.repaint();   // Update the tetrisView (view of the tetris-board) / updates the paint() method
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void clockTick(ActionEvent actionEvent) {                    // A method for the continualy ?????
        if (tetrisModel.getGameState() == GameState.ACTIVE_GAME) {      // If the gamestate is ACTIVE_GAME, do the following:
            tetrisModel.clockTick();                                    //      Call the clockTick method on the tetrisModel
            updateClockTimer();                                         //      Update the timer
            tetrisView.repaint();                                       //      Update the view
            timer.restart();                                            //      Restard the timer
        }
    }

    private void updateClockTimer() {                   // Updates the timer
        int tickInterval = tetrisModel.gameEngine();    // Defines a tickinterval
        timer.setDelay(tickInterval);                   // Uses the tickinterval as a delay on the timer
        timer.setInitialDelay(tickInterval);            // Set inital delay 
    }
}
