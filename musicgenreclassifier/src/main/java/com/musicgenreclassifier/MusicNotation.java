package com.musicgenreclassifier;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MusicNotation extends JPanel {
    public MusicNotation() {
        this.setLayout(null);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(30, 50, 100, 100);
        panel.setOpaque(false);
        String currentDirectory = System.getProperty("user.dir");
        String imagePath = currentDirectory + File.separator + "src\\main\\java\\com\\musicgenreclassifier\\treble_clef_notation_removebg.png";
        System.out.println("C:\\Users\\weiyu\\OneDrive\\桌面\\PD2_final_project\\musicgenreclassifier\\src\\main\\java\\com\\musicgenreclassifier\\treble_clef_notation_removebg.png");
        System.out.println(imagePath);
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        int newWidth = icon.getIconWidth() / 6;
        int newHeight = icon.getIconHeight() / 6;
        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);

        JLabel trebleClefNotation = new JLabel(resizedImageIcon);
        trebleClefNotation.setBounds(0, 0, newWidth, newHeight);
        panel.add(trebleClefNotation);

        add(panel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw staff
        drawStaff(g2d, 70);
    }

    private void drawStaff(Graphics2D g2d, int yStart) {
        int yGap = 10;
        for(int j =0; j<4; j++) {
            for (int i = 0; i < 5; i++) {
                g2d.drawLine(50, yStart + i * yGap, getWidth() - 50, yStart + i * yGap);
            }
            yStart+=70;
        }
    }

    
}
