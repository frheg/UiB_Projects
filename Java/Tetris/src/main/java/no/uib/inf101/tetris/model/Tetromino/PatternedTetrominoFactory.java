package no.uib.inf101.tetris.model.Tetromino;

public class PatternedTetrominoFactory implements TetrominoFactory {

    String string;
    private int index = 0; // Create an index = 0

    public PatternedTetrominoFactory(String string) {
        this.string = string; // Saved input as field-variable
    }

    @Override
    public Tetromino getNext() { // Get the next character
        char character = string.charAt(index); // Create a char element which finds the character at an given index
        index = (index + 1) % string.length(); // Update the index
        return Tetromino.newTetromino(character); // Return a new tetromino object with the string character found
    }
}