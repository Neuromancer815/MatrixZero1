package com.neuromancer.matrixzero;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public static String lastDirectionPressed = "";


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                lastDirectionPressed = "up";
                break;

            case KeyEvent.VK_S:
                downPressed = true;
                lastDirectionPressed = "down";
                break;

            case KeyEvent.VK_A:
                leftPressed = true;
                lastDirectionPressed = "left";
                break;

            case KeyEvent.VK_D:
                rightPressed = true;
                lastDirectionPressed = "right";
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }


        if (upPressed) {
            lastDirectionPressed = "up";
        } else if (downPressed) {
            lastDirectionPressed = "down";
        } else if (leftPressed) {
            lastDirectionPressed = "left";
        } else if (rightPressed) {
            lastDirectionPressed = "right";
        }
    }
}
