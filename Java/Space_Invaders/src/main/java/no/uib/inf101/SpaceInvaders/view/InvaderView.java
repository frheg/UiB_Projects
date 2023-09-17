package no.uib.inf101.SpaceInvaders.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

import no.uib.inf101.SpaceInvaders.model.GameState;
import no.uib.inf101.SpaceInvaders.model.InvadersModel;
import no.uib.inf101.SpaceInvaders.model.Constants;

public class InvaderView extends JPanel {

    private InterfaceInvaderView model;
    private InvadersModel invadersModel = new InvadersModel();
    private Object object = new Object();
    private boolean mouseIsInTheRectangle = false;
    private boolean mouseIsPressed = false;

    // Preloading images
    private BufferedImage player = Inf101Graphics.loadImageFromResources("/spaceShipMid.png");
    private BufferedImage background = Inf101Graphics.loadImageFromResources("/spaceWallpaperIMG.jpeg");
    private BufferedImage playerMid = Inf101Graphics.loadImageFromResources("/spaceShipMid.png");
    private BufferedImage playerLeft = Inf101Graphics.loadImageFromResources("/spaceShipLeft.png");
    private BufferedImage playerRight = Inf101Graphics.loadImageFromResources("/spaceShipRight.png");
    private BufferedImage alienship = Inf101Graphics.loadImageFromResources("/alienship.png");
    private BufferedImage laserIMG = Inf101Graphics.loadImageFromResources("/laserGreen.png");
    private BufferedImage invasionIMG = Inf101Graphics.loadImageFromResources("/invasionIMG.jpg");
    private BufferedImage startIMG = Inf101Graphics.loadImageFromResources("/startIMG.jpg");
    private BufferedImage headerLogo = Inf101Graphics.loadImageFromResources("/headerLogo.png");

    public InvaderView(InterfaceInvaderView model) {
        this.model = model;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(model.getAppWidth(), model.getAppHeigth()));
        this.setupMousePositionUpdater();
        this.setupMousePressedUpdater();
        this.setupKeyPressedUpdater();
    }

    private void setupMousePressedUpdater() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseIsPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseIsPressed = false;
                model.startGame();
                repaint();
            }
        });
    }

    private void setupKeyPressedUpdater() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player = playerLeft;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player = playerRight;
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player = playerMid;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player = playerMid;
                }
                repaint();
            }
        });
    }

    private Rectangle2D getRectangle() {
        return new Rectangle2D.Double(50, 50, getWidth() - 100, getHeight() - 100);
    }

    private void setupMousePositionUpdater() {
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseIsInTheRectangle = getRectangle().contains(e.getPoint());
                updateCursor();
                repaint();
            }
        });
    }

    private void updateCursor() {
        if (mouseIsInTheRectangle) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    private void drawGame(Graphics2D g) {
        Rectangle2D rectangle = new Rectangle2D.Double(0, 0, getWidth() - 2 * 0, getHeight() - 2 * 0);
        g.setColor(Color.BLACK);
        g.fill(rectangle);
        double scale = (rectangle.getHeight() - 1) / background.getHeight();
        Inf101Graphics.drawImage(g, background, rectangle.getX(), rectangle.getY() + 1, scale);
        g.setColor(new Color(0, 0, 0, 0));
        if (model.getGameState() == GameState.START_GAME) {
            drawStartMenu(rectangle, g);
        } else if (model.getGameState() == GameState.ACTIVE_GAME) {
            drawActivegame(rectangle, g);
        } else if (model.getGameState() == GameState.GAME_PAUSED) {
            Rectangle2D pauseScreen = new Rectangle2D.Double(0, 0, getWidth() - 2 * 0, getHeight() - 2 * 0);
            System.out.println("Game paused");
            drawPausedGame(pauseScreen, g);
        }
        if (model.getGameState() == GameState.GAME_OVER) {
            double scale_ = (rectangle.getHeight() - 1) / background.getHeight();
            Inf101Graphics.drawImage(g, invasionIMG, rectangle.getX(), rectangle.getY() + 1, scale_);
            drawGameOver(rectangle, g);
        }
    }

    private void drawGameOver(Rectangle2D rectangle, Graphics2D canvas) {
        Inf101Graphics.drawImage(canvas, invasionIMG, rectangle.getX(), rectangle.getY() + 1, 1);
        canvas.fill(rectangle);
        canvas.setColor(Constants.TEXT_COLOR);
        canvas.setFont(new Font("SansSerif", Font.BOLD, 50));
        Inf101Graphics.drawCenteredString(canvas, "INVADED!", rectangle);
        canvas.setFont(new Font("SansSerif", Font.BOLD, 20));
        canvas.drawString("Final score: " + model.getScore(), 40, 730);
        canvas.drawString("Restart? Press 'R'", 40, 760);
    }

    private void drawPausedGame(Rectangle2D rectangle, Graphics2D canvas) {
        canvas.fill(rectangle);
        canvas.setColor(Constants.TEXT_COLOR);
        canvas.setFont(new Font("SansSerif", Font.BOLD, 50));
        Inf101Graphics.drawCenteredString(canvas, "PAUSED", rectangle);
    }

    private void drawActivegame(Rectangle2D rectangle, Graphics2D canvas) {
        Rectangle2D player = new Rectangle2D.Double(model.getPlayerX(), Constants.PLAYER_SPAWN_Y,
                Constants.PLAYER_WIDTH,
                Constants.PLAYER_HEIGHT);
        canvas.fill(player);
        drawPlayer(player, canvas);
        if (model.getLaser() != null) {
            for (List<Integer> laser : model.getLaser()) {
                int laserX = laser.get(0);
                int laserY = laser.get(1);
                Rectangle2D laserRec = new Rectangle2D.Double(laserX, laserY, Constants.LASER_WIDTH,
                        Constants.LASER_HEIGHT);
                double scale = (laserRec.getHeight() - 1) / laserRec.getHeight();
                canvas.drawRect(laserX, laserY, Constants.LASER_WIDTH, Constants.LASER_HEIGHT);
                Inf101Graphics.drawImage(canvas, laserIMG, laserX, laserY, scale);
                canvas.fill(laserRec);
            }
        }
        if (model.getAlien() != null) {
            for (List<Integer> alien : model.getAlien()) {
                int alienX = alien.get(0);
                int alienY = alien.get(1);
                Rectangle2D alienRec = new Rectangle2D.Double(alienX, alienY, Constants.ALIEN_WIDTH,
                        Constants.ALIEN_HEIGHT);
                double scale = (alienRec.getHeight() - 1) / alienRec.getHeight();
                canvas.drawRect(alienX, alienY, Constants.ALIEN_WIDTH, Constants.ALIEN_HEIGHT);
                Inf101Graphics.drawImage(canvas, alienship, alienX, alienY, scale);
                canvas.fill(alienRec);
            }
        }
        canvas.setColor(Constants.TEXT_COLOR);
        canvas.setFont(new Font("SansSerif", Font.BOLD, 15));
        canvas.drawString("Score: " + (model.getScore()), 50, 50);
        canvas.drawString("Health: " + (model.getHealth()), Constants.BOARD_WIDTH - 150 - Constants.HEALTHBAR_WIDTH, 50);
        canvas.setColor(Constants.HEALTHBAR_BACKGROUND_COLOR);
        Rectangle2D healthbar = new Rectangle2D.Double(Constants.HEALTHBAR_X - Constants.HEALTHBAR_MARGIN, Constants.HEALTHBAR_Y - Constants.HEALTHBAR_MARGIN,
                Constants.HEALTHBAR_WIDTH + Constants.HEALTHBAR_MARGIN
                        * 2, Constants.HEALTHBAR_HEIGHT + Constants.HEALTHBAR_MARGIN * 2);
        canvas.fill(healthbar);
        drawHealthbar(canvas);
    }

    private void drawHealthbar(Graphics2D canvas){
        int remaingHealthWidth = model.getHealth();
        canvas.setColor(Constants.HEALTHBAR_HEALTH_COLOR);
        Rectangle2D healthRemains = new Rectangle(Constants.HEALTHBAR_X, Constants.HEALTHBAR_Y, remaingHealthWidth, Constants.HEALTHBAR_HEIGHT);
        canvas.fill(healthRemains);
    }

    private void drawPlayer(Rectangle2D rectangle, Graphics2D canvas) {
        double scale = (rectangle.getHeight() - 1) / player.getHeight();
        Inf101Graphics.drawImage(canvas, player, rectangle.getX(), rectangle.getY() + 1, scale);
        this.repaint();
    }

    private void drawStartMenu(Rectangle2D rectangle, Graphics2D canvas) { // Startmenyen
        Inf101Graphics.drawImage(canvas, startIMG, rectangle.getX(), rectangle.getY() + 1, 1);
        canvas.fill(rectangle);
        int buttonMargin = 100;
        int buttonHeight = 100;
        int buttonWidth = getWidth() - buttonMargin * 2;
        int buttonPadding = buttonHeight + 20;
        Color color = mouseIsInTheRectangle ? (mouseIsPressed ? new Color(0, 0, 0, 255)
                : new Color(212, 76, 247, 255))
                : new Color(83, 0, 252, 255);
        Inf101Graphics.drawImage(canvas, headerLogo, 50, 50, 1);
        canvas.setFont(new Font("SansSerif", Font.BOLD, 50));
        canvas.setColor(new Color(255, 255, 255, 200));
        RoundRectangle2D start = new RoundRectangle2D.Double(buttonMargin, buttonMargin + buttonPadding * 2,
                buttonWidth, buttonPadding * 1,
                50, 50);
        canvas.fill(start);
        canvas.setColor(color);
        Inf101Graphics.drawCenteredString(canvas, "Start game", start);
    }
}
