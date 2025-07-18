package com.neuromancer.matrixzero;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int originalTileSie = 16; //16x16 tiles
    final int scale = 3;
    public final int tileSize = originalTileSie * scale; //48x48 tile
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12; //4:3 screen ratio
    final int screenWidth = tileSize * maxScreenColumn; //768 px
    final int screenHeight = tileSize * maxScreenRow; //576 px
    int FPS = 60; //framerate

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    //setting the res.player's default position
//    int playerX = 100;
//    int playerY = 100;
//    int playerSpeed = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
//    public void run() { //this is the thread sleep game loop its inaccurate
//        double secondToNanosecond = 1_000_000_000;
//        double drawInterval = secondToNanosecond/FPS; //0.0166666 seconds per frame
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        while (gameThread != null) {
//            update(); //updates info like character position
//            repaint(); //draw the screen with updated info
//
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1_000_000; //convert nanosecond to millisecond
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long)remainingTime); //sleep only accepts millisecond
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public void run() { //delta game loop more accurate
        double secondToNanosecond = 1_000_000_000;
        double drawInterval = secondToNanosecond/FPS; //0.0166666 seconds per frame
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }

}

