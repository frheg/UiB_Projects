package no.uib.inf101.tetris;

import javax.swing.JFrame;

import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.Tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

public class TetrisMain {
  public static final String WINDOW_TITLE = "INF101 Tetris";
  
  public static void main(String[] args) {
    TetrisBoard board = new TetrisBoard(15, 10);
      RandomTetrominoFactory factory = new RandomTetrominoFactory();    // New RandomTetrominoFactory object
      TetrisModel model = new TetrisModel(board, factory);              // New TetrisModel object
      TetrisView view = new TetrisView(model);                          // New TetrisView object
      TetrisController controller = new TetrisController(model, view);  // New TetrisController object

      System.out.println(controller);

    // The JFrame is the "root" application window.
    // We here set som properties of the main window, 
    // and tell it to display our tetrisView
    JFrame frame = new JFrame(WINDOW_TITLE);                // New JFrame object
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Here we set which component to view in our window
    frame.setContentPane(view);
    
    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
  }
}
