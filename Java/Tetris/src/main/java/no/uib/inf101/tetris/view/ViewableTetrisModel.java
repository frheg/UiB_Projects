package no.uib.inf101.tetris.view;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;
 
public interface ViewableTetrisModel {  

    /**
     * Methods in which is used to get values in between files
     * @return the board
     */
    public GridDimension getDimension();

    /**
     * 
     * @return the board
     */
    public Iterable<GridCell<Character>> getTilesOnBoard(); 

    /**
     * 
     * @return a Tetromino
     */
    public Iterable<GridCell<Character>> getTetrominoOnBoard();

    /**
     * 
     * @return the gamestate
     */
    public GameState getGameState();

    /**
     * 
     * @return the score
     */ 
    public int getScore();                                   
}
