package com.neuromancer.matrixzero;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Main {
    public static void main (String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("MatrixZero");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}