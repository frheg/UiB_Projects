package no.uib.inf101.tetris.model.Tetromino;

public interface TetrominoFactory {
    /**
     * Creates a new tetromino object randomly from a list of the different types
     * with a character as a key
     * 
     * @return Returns a new tetromino object with the string character found
     */
    public Tetromino getNext();
}
