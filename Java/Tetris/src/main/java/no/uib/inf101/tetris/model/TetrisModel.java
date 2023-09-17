package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.Tetromino.Tetromino;
import no.uib.inf101.tetris.model.Tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {

    private GameState gameState = GameState.START_GAME;
    private TetrisBoard board;
    private TetrominoFactory tetroFact;
    private Tetromino fallingTetromino;
    private int score = 0;

    public TetrisModel(TetrisBoard boardParam, TetrominoFactory tetroFactParam) {
        this.board = boardParam;
        this.tetroFact = tetroFactParam;
        this.fallingTetromino = this.tetroFact.getNext().shiftedToTopCenterOf(this.board);
    }

    @Override
    public GridDimension getDimension() { 
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;   
    }

    @Override
    public Iterable<GridCell<Character>> getTetrominoOnBoard() {
        return fallingTetromino;
    }

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {  // Moves the tetromino with the parameters as a difference between the current and next position
        Tetromino candidate = fallingTetromino.shiftedBy(deltaRow, deltaCol);   // Creates a new Tetromino object as a candidate for the next object with new position through the shiftedBy method
        boolean canMove = isPositionLegal(candidate);   // Calls on the isPositionLegal method to check whether the next position is elligeble
        if (canMove) {  // If the next position is true in terms og the isPositionLegal method...
            fallingTetromino = candidate;   // ... the current fallingTetromino gets the to be our newly created tetromino
        }
        return canMove; // returns the boolean value of the result of the isPositionLegal method
    }

    @Override
    public boolean rotatedCounterClockwise() {  // Rotates our tetromino counterclockwise
        Tetromino candidate = fallingTetromino.rotatedCounterClockwise();   // Creates a new Tetromino candidate which is then rotated
        boolean canMove = isPositionLegal(candidate);   
        if (canMove) {
            fallingTetromino = candidate;
        }
        return canMove;
    }

    private boolean isPositionLegal(Tetromino tetromino) {      // Checks if the position is legal in the grid of the tetromino and returns a boolean
        for (GridCell<Character> gridCell : tetromino) {
            if (!board.positionIsOnGrid(gridCell.pos())) {
                return false;
            }
            if (board.get(gridCell.pos()) != '-') {
                return false;
            }
        }
        return true;
    }

    private void newTetromino() { // Creates a new tetromino
        Tetromino newFallingTetromino = this.tetroFact.getNext().shiftedToTopCenterOf(this.board);
        if (!isPositionLegal(newFallingTetromino)) {
            gameState = GameState.GAME_OVER;
        } else {
            fallingTetromino = this.tetroFact.getNext().shiftedToTopCenterOf(this.board);
        }
    }

    private void freezeTetromino() { // Freezes tetromino on grid
        for (GridCell<Character> value : this.fallingTetromino) {
            board.set(value.pos(), value.value());
        }
        score = board.removeFullRows();
        newTetromino();
    }

    @Override
    public void dropTetromino() { // Drops the tetromino to the bottom of the grid
        while (true) {
            Tetromino tetro = fallingTetromino.shiftedBy(1, 0);
            if (isPositionLegal(tetro)) {
                fallingTetromino = tetro;
            } else {
                freezeTetromino();
                break;
            }
        }
    }
    // Samarbeid med Synne Bondevik

    @Override
    public GameState getGameState() {   // Returns the gamestate
        return gameState;
    }

    @Override
    public int gameEngine() {   // Returns the tickspeed of the game
        score = getScore();     // Gets the score
        int tickSpeed = (-15*score)+1000;   
        if (tickSpeed >= 50) {     // The tickspeed increases in the function in tickSpeed
            return tickSpeed;       // Returns the speed if above 50
        }
        else    {
            return 50;             // Else return 50 as speed
        }
    }

    public int getScore() {     // Returns the score
        return score;
    }

    @Override
    public void clockTick() {   
        boolean canMove = moveTetromino(1, 0);
        if (!canMove) {
            freezeTetromino();
            newTetromino();
        }
    }

    @Override
    public void pauseGame() {             
        if (gameState == GameState.ACTIVE_GAME) {
            gameState = GameState.GAME_PAUSED;
        } else {
            gameState = GameState.ACTIVE_GAME;
        }
    }

    @Override
    public void startGame() {
        gameState = GameState.ACTIVE_GAME;
    }

    @Override
    public void restartGame() {
        if (gameState == GameState.GAME_OVER)   {
            gameState = GameState.ACTIVE_GAME;
            score = 0;
            board.clearBoard();
        }
    }
}
