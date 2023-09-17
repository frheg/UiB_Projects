package no.uib.inf101.sem2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import no.uib.inf101.SpaceInvaders.controller.ControllableInvadersModel;
import no.uib.inf101.SpaceInvaders.model.Constants;
import no.uib.inf101.SpaceInvaders.model.InvadersModel;
import no.uib.inf101.SpaceInvaders.view.InterfaceInvaderView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

class InvadersModelTest {
    
    InterfaceInvaderView interfaceInvaderView;
    ControllableInvadersModel controllableInvadersModel;

    @BeforeEach
    void setUp() {
        interfaceInvaderView = new InvadersModel();
    }

    @Test
    void testSetPlayerX() {
        // Test moving player within bounds
        assertEquals(Constants.PLAYER_SPAWN_X + 10, controllableInvadersModel.setPlayerX(10));
        assertEquals(Constants.PLAYER_SPAWN_X - 10, controllableInvadersModel.setPlayerX(-10));

        // Test moving player beyond left boundary
        assertEquals(Constants.PLAYER_SPAWN_X, controllableInvadersModel.setPlayerX(-Constants.PLAYER_SPAWN_X - 10));

        // Test moving player beyond right boundary
        assertEquals(Constants.BOARD_WIDTH - Constants.PLAYER_WIDTH, controllableInvadersModel.setPlayerX(Constants.BOARD_WIDTH));

        // Test moving player beyond right boundary
        assertEquals(Constants.BOARD_WIDTH - Constants.PLAYER_WIDTH, controllableInvadersModel.setPlayerX(Constants.BOARD_WIDTH + 10));
    }

    @Test
    void testGetPlayerX() {
        // Test initial player position
        assertEquals(Constants.PLAYER_SPAWN_X, interfaceInvaderView.getPlayerX());

        // Test player position after moving
        controllableInvadersModel.setPlayerX(10);
        assertEquals(Constants.PLAYER_SPAWN_X + 10, interfaceInvaderView.getPlayerX());
    }

    @Test
    void testSpawnLaser() {
        // Test initial laser list
        assertTrue(interfaceInvaderView.getLaser().isEmpty());

        // Test spawning a laser
        controllableInvadersModel.spawnLaser();
        assertEquals(1, interfaceInvaderView.getLaser().size());
        assertEquals(Constants.PLAYER_SPAWN_X - Constants.LASER_WIDTH / 2, 
                interfaceInvaderView.getLaser().get(0).get(0));
        assertEquals(Constants.LASER_SPAWN_Y, interfaceInvaderView.getLaser().get(0).get(1));
    }


    @Test
    public void testGetLaser() {
        List<List<Integer>> expected = new ArrayList<>();
        assertEquals(expected, interfaceInvaderView.getLaser());
    }

    @Test
    public void testGetAlien() {
        List<List<Integer>> expected = new ArrayList<>();
        assertEquals(expected, interfaceInvaderView.getAlien());
    }

    @Test
    public void testGetScore() {
        assertEquals(0, interfaceInvaderView.getScore());
    }

    @Test
    public void testGetHealth() {
        assertEquals(Constants.START_HEALTH, interfaceInvaderView.getHealth());
    }
}
