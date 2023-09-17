package no.uib.inf101.SpaceInvaders.model;

import java.awt.Color;

/**
 * Here is all the constants of the game defined
 */
public interface Constants {
    int BOARD_WIDTH = 700;
    int BOARD_HEIGHT = 800;

    int LASER_WIDTH = 6;
    int LASER_HEIGHT = 20;

    int ALIEN_WIDTH = 50;
    int ALIEN_HEIGHT = 50;

    int PLAYER_WIDTH = 50;
    int PLAYER_HEIGHT = 50;

    int START_HEALTH = 100;
    int HEALTH_INCREMENT = 5;

    int GAME_TICK_SPEED = 33; // equals 30fps

    int LASER_SPEED_INCREMENT = -15;
    int ALIEN_SPEED_INCREMENT = 2;
    int ALIEN_SPAWN_INCREMENT = 60;

    int DIFFICULTY = 15; // Higher value -> Higher difficulty

    int ALIEN_SPAWN_Y = -50;
    int PLAYER_SPAWN_Y = 725;
    int PLAYER_SPAWN_X = 325;
    int LASER_SPAWN_Y = 750;

    int PLAYER_DELTA_X = 10;

    Color TEXT_COLOR = new Color(255, 255, 255, 255);
    Color HEALTHBAR_BACKGROUND_COLOR = new Color(255, 255, 255, 255);
    Color HEALTHBAR_HEALTH_COLOR = new Color(255, 0, 0, 255);

    int HEALTHBAR_HEIGHT = 25;
    int HEALTHBAR_WIDTH = 100;
    int HEALTHBAR_MARGIN = 2;
    int HEALTHBAR_Y = 30;
    int HEALTHBAR_X = BOARD_WIDTH - HEALTHBAR_WIDTH - 50;
    
}
