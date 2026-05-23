import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class platform {

    int width, height, cellSize;
    int foodx, foody,getSizeX,getSizeY;

    Image headImg = new ImageIcon("head.png").getImage();
    Image bodyImg = new ImageIcon("body.png").getImage();
    Image iconImg = new ImageIcon("icon.png").getImage();
    Image foodImg = new ImageIcon("food2.png").getImage();

    int snakeX, snakeY;
    int bodySize = 10;

    int[] bodyX = new int[100];
    int[] bodyY = new int[100];

    JFrame frame;
    JPanel panel;
    JButton replayButton;

    boolean up, down, left, right = true;
    boolean showGrid = false;
    String direction = "RIGHT";
    boolean gameOver = false;
    boolean playAgain = false;

    void draw(int width, int height, int cellSize,int getSizeX, int getSizeY) {

        this.width = width;
        this.height = height;
        this.cellSize = cellSize;

        Color background = new Color(38, 59, 106);
        Color snake = new Color(177, 83, 215);
        Color box = new Color(160, 213, 133);
        Color food = new Color(243, 117, 194);

        frame = new JFrame();
        Random r = new Random();

        foodx = r.nextInt(width);
        foody = r.nextInt(height);

        snakeX = (width * cellSize) / 2;
        snakeY = (height * cellSize) / 2;

        // INIT BODY
        for (int i = 0; i < bodySize; i++) {
            bodyX[i] = snakeX - i * cellSize;
            bodyY[i] = snakeY;
        }
        right = true;

        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                // grid
                if (showGrid) {
                    g2.setPaint(box);
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            g2.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
                        }
                    }
                }
                // snake
                g2.setColor(snake);
                for (int i = 0; i < bodySize; i++) {
                    if (i == 0){
                        g2.drawImage(headImg, bodyX[i], bodyY[i], cellSize, cellSize, null);
                    }
                    else {
                        g2.drawImage(bodyImg, bodyX[i], bodyY[i], cellSize, cellSize, null);
                    }

                }

                // food
                g2.setColor(food);
                //g2.fillRect(foodx * cellSize, foody * cellSize, cellSize, cellSize);
                g2.drawImage(foodImg, foodx * cellSize, foody * cellSize, cellSize, cellSize, null);
                //gameover
                if (setGameOver()){

                    //background
                    g2.setColor(Color.gray);
                    g2.fillRect(140,240,500,200);
                    // game over text
                    g2.setColor(Color.WHITE);
                    g2.setFont(new Font("Arial", Font.BOLD, 40));
                    g2.drawString("Game Over", 285, 340);
                    //replay button

                }
            }
        };

        panel.setBackground(background);
        panel.setFocusable(true);

        frame.setResizable(false);
        frame.add(panel);
        frame.setSize(width * cellSize +5, height * cellSize +5);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Snake Game");
        frame.setIconImage(iconImg);
        panel.requestFocusInWindow();

        new javax.swing.Timer(150, e -> {

            if (!gameOver) {

                update();
                clashAndGrow();

                if (setGameOver()) {
                    gameOver = true;
                }

                panel.repaint();
            }

        }).start();


        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {

                    case KeyEvent.VK_W:
                        if (!direction.equals("DOWN")) {
                            direction = "UP";
                        }
                        break;

                    case KeyEvent.VK_S:
                        if (!direction.equals("UP")) {
                            direction = "DOWN";
                        }
                        break;

                    case KeyEvent.VK_A:
                        if (!direction.equals("RIGHT")) {
                            direction = "LEFT";
                        }
                        break;

                    case KeyEvent.VK_D:
                        if (!direction.equals("LEFT")) {
                            direction = "RIGHT";
                        }
                        break;
                }
            }
        });
    }

    void update() {

        // move body
        for (int i = bodySize - 1; i > 0; i--) {
            bodyX[i] = bodyX[i - 1];
            bodyY[i] = bodyY[i - 1];
        }

        // move head

        switch (direction) {

            case "UP":
                snakeY -= cellSize;
                break;

            case "DOWN":
                snakeY += cellSize;
                break;

            case "LEFT":
                snakeX -= cellSize;
                break;

            case "RIGHT":
                snakeX += cellSize;
                break;
        }

        bodyX[0] = snakeX;
        bodyY[0] = snakeY;

        panel.repaint();
    }

    void clashAndGrow() {

        if (snakeX == foodx * cellSize && snakeY == foody * cellSize) {
            bodySize++;
            // spawn new food
            Random r = new Random();
            foodx = r.nextInt(width -3);
            foody = r.nextInt(height -3);
        }
        //clash sides
        if (snakeX >= width * cellSize) {
            snakeX = 0 - cellSize;
        }
        else if (snakeX < 0) {
            snakeX = (width - 1) * cellSize ;
        }

        if (snakeY >= height * cellSize) {
            snakeY = 0 - cellSize;
        }
        else if (snakeY < 0) {
            snakeY = (height - 1) * cellSize ;
        }

    }
    boolean setGameOver() {

        for (int i = 1; i < bodySize; i++) {

            if (snakeX == bodyX[i] && snakeY == bodyY[i]) {
                return true;
            }
        }

        return false;
    }
}