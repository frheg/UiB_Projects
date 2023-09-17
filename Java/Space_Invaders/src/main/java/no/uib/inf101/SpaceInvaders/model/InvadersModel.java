package no.uib.inf101.SpaceInvaders.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.SpaceInvaders.controller.ControllableInvadersModel;
import no.uib.inf101.SpaceInvaders.view.InterfaceInvaderView;
import no.uib.inf101.SpaceInvaders.sound.ExplosionSound;

public class InvadersModel implements InterfaceInvaderView, ControllableInvadersModel {

    private GameState gameState = GameState.START_GAME;
    private int playerX;
    private int score;
    private int health;
    private int appHeight = Constants.BOARD_HEIGHT;
    private int appWidth = Constants.BOARD_WIDTH;
    private int laserY;
    private List<List<Integer>> laserList;
    private List<List<Integer>> alienList;
    private ExplosionSound explosionSound;

    public InvadersModel() {
        this.playerX = Constants.PLAYER_SPAWN_X;
        this.laserY = Constants.LASER_SPAWN_Y;
        this.laserList = new ArrayList<List<Integer>>();
        this.alienList = new ArrayList<List<Integer>>();
        this.score = 0;
        this.health = Constants.START_HEALTH;
    }

    @Override
    public int gameEngine() {
        return Constants.GAME_TICK_SPEED;
    }

    @Override
    public int setPlayerX(int deltaX) {
        if (canMove(deltaX) == true) {
            this.playerX = playerX + deltaX;
        }
        return this.playerX;
    }

    @Override
    public int getPlayerX() {
        return this.playerX;
    }

    @Override
    public GameState getGameState() {
        return gameState;
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
    public int getAppWidth() {
        return appWidth;
    }

    @Override
    public int getAppHeigth() {
        return appHeight;
    }

    @Override
    public void spawnLaser() {
        List<Integer> position = new ArrayList<>();
        position.add(this.playerX + (Constants.PLAYER_WIDTH / 2) - (Constants.LASER_WIDTH));
        position.add(this.laserY);
        laserList.add(position);
    }

    private boolean canMove(int deltaX) {
        int newPosition = getPlayerX() + deltaX;
        if (newPosition < 0 || newPosition > appWidth - Constants.PLAYER_WIDTH) {
            return false;
        } else
            return true;
    }

    @Override
    public void clockTick() {
        newLaser(Constants.LASER_SPEED_INCREMENT);
        newAlien(Constants.ALIEN_SPEED_INCREMENT);
        checkCollisions();
    }

    private int randomAlienXCoordinate() {
        int min = Constants.ALIEN_WIDTH / 2;
        int max = Constants.BOARD_WIDTH - (Constants.ALIEN_WIDTH / 2) - Constants.ALIEN_WIDTH;
        int x = (int) (Math.random() * (max - min + 1) + min);
        return x;
    }

    @Override
    public void spawnAlien() {
        List<Integer> position = new ArrayList<>();
        position.add(randomAlienXCoordinate());
        position.add(Constants.ALIEN_SPAWN_Y);
        alienList.add(position);
    }

    private void newAlien(int deltaY) {
        List<List<Integer>> movedAliens = new ArrayList<List<Integer>>();
        for (List<Integer> list : alienList) {
            int alienXPos = list.get(0);
            int alienYPos = list.get(1);
            List<Integer> newAliens = new ArrayList<Integer>();
            newAliens.add(alienXPos);
            newAliens.add(alienYPos + deltaY);
            if (alienYPos < Constants.BOARD_HEIGHT) {
                movedAliens.add(newAliens);
                if (newAliens.get(1) >= Constants.BOARD_HEIGHT || getHealth() <= 0) {
                    health -= Constants.HEALTH_INCREMENT;
                }
            }
            if (getHealth() <= 0) {
                gameState = GameState.GAME_OVER;
            }
        }
        // List<List<Integer>> movedAliens = movedObject(deltaY, alienList, canMoveAlien(deltaY));
        this.alienList = movedAliens;
    }

    private void newLaser(int deltaY) {
        List<List<Integer>> movedLasers = new ArrayList<List<Integer>>();
        for (List<Integer> list : laserList) {
            int laserX = list.get(0);
            int laserY = list.get(1);
            List<Integer> newLasers = new ArrayList<Integer>();
            newLasers.add(laserX);
            newLasers.add(laserY + deltaY);
            if (laserY > 0) {
                movedLasers.add(newLasers);
            }
        }
        // List<List<Integer>> movedLasers = movedObject(deltaY, alienList, canMoveLaser(deltaY));
        this.laserList = movedLasers;
    }

    private void checkCollisions() {
        for (int i = 0; i < laserList.size(); i++) {
            Rectangle laserRect = new Rectangle(laserList.get(i).get(0), laserList.get(i).get(1), Constants.LASER_WIDTH,
                    Constants.LASER_HEIGHT);
            for (int j = 0; j < alienList.size(); j++) {
                Rectangle alienRect = new Rectangle(alienList.get(j).get(0), alienList.get(j).get(1),
                        Constants.ALIEN_WIDTH, Constants.ALIEN_HEIGHT);
                if (laserRect.intersects(alienRect)) {
                    laserList.remove(i);
                    alienList.remove(j);
                    i--;
                    score += 10;
                    explosionSound = new ExplosionSound();
                    explosionSound.run();
                    break;
                }
            }
        }
    }

    @Override
    public List<List<Integer>> getLaser() {
        return this.laserList;
    }

    @Override
    public List<List<Integer>> getAlien() {
        return this.alienList;
    }

    @Override
    public void restartGame() {
        score = 0;
        gameState = GameState.ACTIVE_GAME;
        health = Constants.START_HEALTH;
        playerX = Constants.PLAYER_SPAWN_X;
        laserList.clear();
        alienList.clear();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getHealth() {
        return health;
    }
}
