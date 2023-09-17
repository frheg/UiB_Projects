package no.uib.inf101.tetris.model.Tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory {

    private final Random random = new Random(); // Creates a random object

    @Override
    public Tetromino getNext() {
        char[] list = { 'L', 'J', 'S', 'Z', 'T', 'I', 'O' }; // List with the possible character/shapes
        char c = list[random.nextInt(list.length)]; // Picks a random
        return Tetromino.newTetromino(c); // Returns the result
    }
}
