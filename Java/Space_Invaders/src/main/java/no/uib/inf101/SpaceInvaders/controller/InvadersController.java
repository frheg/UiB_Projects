package no.uib.inf101.SpaceInvaders.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

import no.uib.inf101.SpaceInvaders.model.GameState;
import no.uib.inf101.SpaceInvaders.model.InvadersModel;
import no.uib.inf101.SpaceInvaders.sound.InvadersSong;
import no.uib.inf101.SpaceInvaders.sound.LaserSound;
import no.uib.inf101.SpaceInvaders.model.Constants;
import no.uib.inf101.SpaceInvaders.view.InterfaceInvaderView;
import no.uib.inf101.SpaceInvaders.view.InvaderView;

public class InvadersController implements java.awt.event.KeyListener {

    InvaderView invaderView;
    InterfaceInvaderView interfaceInvaderView;
    ControllableInvadersModel invadersModel;
    Timer timer;
    int counter;
    InvadersSong invadersSong;
    LaserSound laserSound;

    public InvadersController(ControllableInvadersModel controllableInvadersModel, InvaderView invaderView) {
        invaderView.setFocusable(true);
        this.interfaceInvaderView = new InvadersModel();
        this.invaderView = invaderView;
        this.counter = 0;
        this.invadersModel = controllableInvadersModel;
        this.timer = new Timer(invadersModel.gameEngine(), this::clockTick);
        invaderView.addKeyListener(this);
        updateClockTimer();
        timer.start();
        invadersSong = new InvadersSong();
        invadersSong.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (invadersModel.getGameState() == GameState.GAME_OVER) {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                invadersModel.restartGame();
            }
        }
        if (invadersModel.getGameState() == GameState.ACTIVE_GAME) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                invadersModel.setPlayerX(-Constants.PLAYER_DELTA_X);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                invadersModel.setPlayerX(Constants.PLAYER_DELTA_X);
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                laserSound = new LaserSound();
                laserSound.run();
                invadersModel.spawnLaser();
            }
        }
        if (invadersModel.getGameState() == GameState.ACTIVE_GAME
                || invadersModel.getGameState() == GameState.GAME_PAUSED) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                interfaceInvaderView.pauseGame();
            }
        }
        invaderView.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * A method which desides when to spawn an alien based on the
     * returned increment of the {@code alienSpawnIncrement()} method
     * 
     * @param actionEvent
     */
    private void clockTick(ActionEvent actionEvent) {
        if (invadersModel.getGameState() == GameState.ACTIVE_GAME) {
            counter++;
            if (counter % alienSpawnIncrement() == 0) {
                invadersModel.spawnAlien();
            }
            invadersModel.clockTick();
            updateClockTimer();
            invaderView.repaint();
            timer.restart();
        }
    }

    /**
     * 
     * @return the tickspeed for the game
     */
    private int getTickSpeed() {
        int alienSpawnIncrement = Constants.ALIEN_SPAWN_INCREMENT;
        int score = ((InvadersModel) invadersModel).getScore();
        int tickSpeed = (int) Math.ceil(-Constants.DIFFICULTY * Math.log10(score)) + alienSpawnIncrement;
        if (tickSpeed <= 0) {
            return 1;
        }
        return tickSpeed;
    }

    /**
     * 
     * @return the increment at which aliens spawns
     */
    private int alienSpawnIncrement() {
        if (((InvadersModel) invadersModel).getScore() > 0) {
            return getTickSpeed();
        }
        return Constants.ALIEN_SPAWN_INCREMENT;
    }

    /**
     * A method in which it updates the timer for the clock
     */
    private void updateClockTimer() {
        int tickInterval = invadersModel.gameEngine();
        timer.setDelay(tickInterval);
        timer.setInitialDelay(tickInterval);
    }
}
