package no.uib.inf101.SpaceInvaders;

import javax.swing.JFrame;

import no.uib.inf101.SpaceInvaders.controller.InvadersController;
import no.uib.inf101.SpaceInvaders.model.InvadersModel;
import no.uib.inf101.SpaceInvaders.view.InvaderView;

public class MainSpaceInvaders {
  static String WINDOW_TITLE = "Space Invaders";

  public static void main(String[] args) {

    InvadersModel invadersModel = new InvadersModel();
    InvaderView invaderView = new InvaderView(invadersModel);
    new InvadersController(invadersModel, invaderView);
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(invaderView);
    frame.setResizable(false);
    frame.pack();
    frame.setVisible(true);
  }
}
