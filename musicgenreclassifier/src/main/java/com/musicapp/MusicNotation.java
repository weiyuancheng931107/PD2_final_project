package com.musicapp;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MusicNotation extends JPanel{
    private ImageIcon resizedImageIcon;
    int newWidth;
    int newHeight;

    public MusicNotation() {
        setLayout(null);
        String currentDirectory = System.getProperty("user.dir");
        String imagePath = currentDirectory + File.separator + "\\musicgenreclassifier\\src\\main\\java\\com\\graphics\\treble_clef_notation_removebg.png";

        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        newWidth = icon.getIconWidth() / 6;
        newHeight = icon.getIconHeight() / 6;
        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        resizedImageIcon = new ImageIcon(resizedImage);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // anti-antialias
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawStaff(g2d);
    }

    private void drawStaff(Graphics2D g2d) {
        final int X_START = 50;
        int yStart = 70; // 20
        final int X_END = getWidth() - 50;
        final int Y_GAP = 10;

        for (int line = 0; line < 3; line++) {
            addNotation(X_START -20, yStart - 20, newWidth, newHeight);

            for (int i = 0; i < 5; i++) {
                int y = yStart + Y_GAP * i;
                g2d.drawLine(X_START, y, X_END, y);
            }
            yStart += 70; // 80
        }
    }

    private void addNotation(int x, int y, int width, int height) {
        JLabel trebleClefNotation = new JLabel(resizedImageIcon);
        trebleClefNotation.setBounds(0, 0, width, height);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, width, height);
        panel.setOpaque(false);
        panel.add(trebleClefNotation);
        add(panel);
    }
}
