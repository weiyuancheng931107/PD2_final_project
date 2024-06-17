package com.musicapp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.File;

public class MusicNotations extends JPanel {
    private MusicApp musicApp;
    private final int STAFF_HEIGHT = 60; // Height of each staff
    private final int STAFF_GAP = 20;
    private ImageIcon[] restIcons = new ImageIcon[4];
    private int[] widths = new int[4];
    private int[] heights = new int[4];
    public MusicNotations(MusicApp musicApp) {
        setLayout(null);
        editImage();
        this.musicApp = musicApp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define variables to handle multiple lines of staff
        int x = 55;
        int y = 0;
        int staffHeight = 120;
        int currentLine = 20;
        int panelWidth = this.getWidth();
        double num = 0;
        int countLine = 0;

        for (int i = musicApp.getIndex(); i < musicApp.getList().size(); i++) {
            OneNote note = musicApp.getList().get(i);
            num += 1 / note.get_time();
            y=0+70*countLine;
            if(x>=1560) {
                y += 70; 
                x = 55;   
                countLine++;        
            }

            // Determine the y position based on the note and the current line
            switch (note.get_Note()) {
                case 0:
                    y += 60 + (currentLine);
                    break;
                case 1:
                    y += 60 + (currentLine);
                    drawSharp(g2d, x - 5, y + 5);
                    break;
                case 2:
                    y += 90 + (currentLine);
                    break;
                case 3:
                    y += 90 + (currentLine);
                    drawSharp(g2d, x - 5, y + 5);
                    break;
                case 4:
                    y += 85 + (currentLine);
                    break;
                case 5:
                    y += 80 + (currentLine);
                    break;
                case 6:
                    y += 80 + (currentLine);
                    drawSharp(g2d, x - 5, y + 5);
                    break;
                case 7:
                    y += 75 + (currentLine);
                    break;
                case 8:
                    y += 75 + (currentLine);
                    drawSharp(g2d, x - 5, y + 5);
                    break;
                case 9:
                    y += 70 + (currentLine);
                    break;
                case 10:
                    y += 70 + (currentLine);
                    drawSharp(g2d, x - 5, y + 5);
                    break;
                case 11:
                    y += 65 + (currentLine);
                    break;
                case -1:
                    y += 60 + (currentLine);
                    break;
            }

            drawNoteByType(g2d, x, y, note.get_type());
            x += 40;

            /*if (x > panelWidth - 300) { // If x exceeds panel width, move to next line
                currentLine+=STAFF_GAP+STAFF_HEIGHT+15;
                y += STAFF_HEIGHT + STAFF_GAP+15;
                x = 55; // Reset x to start of the new line*/
                //drawStaff(g2d, y);
            //}
            if (Math.abs(num - 4) < musicApp.getTolerance()) {
                y = 70+70*countLine;
                if(x>=1560) {
                    y+=70;
                    x = 55;   
                    countLine++;       
                }
                g2d.drawLine(x, y, x, y+40);
                x += 40;
                num = 0;
            }
        }
    }

    /*private void drawStaff(Graphics2D g2d, int yStart) {
        int yGap = 10;

        for (int i = 0; i < 5; i++) {
            g2d.drawLine(50, yStart + i * yGap, 2500, yStart + i * yGap);
        }
    }*/

    private void drawNoteByType(Graphics2D g2d, int x, int y, String noteType) {
        switch (noteType) {
            case "Whole":
                drawWholeNote(g2d, x, y);
                break;
            case "Half":
                drawHalfNote(g2d, x, y);
                break;
            case "DottedHalf":
                drawDottedHalfNote(g2d, x, y);
                break;
            case "Quarter":
                drawQuarterNote(g2d, x, y);
                break;
            case "DottedQuarter":
                drawDottedQuarterNote(g2d, x, y);
                break;
            case "DoubleDottedQuarter":
                drawDoubleDottedQuarterNote(g2d, x, y);
                break;
            case "Eighth":
                drawEighthNote(g2d, x, y);
                break;
            case "Sixteenth":
                drawSixteenthNoteGroup(g2d, x, y);
                break;
            case "Triple":
                drawTriplet(g2d, x, y);
                break;
            case "AugTriple":
                drawAugmentedTriplet(g2d, x, y);
                break;
            case "Quintuplet":
                drawQuintuplet(g2d, x, y);
                break;
            case "ProQuintuplet":
                drawProQuintuplet(g2d, x, y);
                break;
            case "WholeNoteRest":
                drawWholeRest(g2d, x, y);
                break;
            case "HalfNoteRest":
                drawHalfRest(g2d, x, y);
                break;
            case "QuarterRest":
                drawQuarterRest(g2d, x, y);
                break;
            case "EighthRest":
                drawEighthRest(g2d, x, y);
                break;
            case "DottedEighth":
                drawDottedEighthNote(g2d, x, y);
                break;
            case "SixteenthRest":
                drawSixteenthRest(g2d, x, y);
                break;
            case "TripletRest":
                drawTripletRest(g2d, x, y);
                break;
            case "AugTripletRest":
                drawAugTripletRest(g2d, x, y);
                break;
            case "QuintupletRest":
                drawQuintupletRest(g2d, x, y);
                break;
            case "ProQuintupletRest":
                drawProQuintupletRest(g2d, x, y);
                break;
        }
    }

    private void drawWholeNote(Graphics2D g2d, int x, int y) {
        g2d.drawOval(x, y, 15, 10); // Note head (hollow and wider)
    }

    private void drawWholeRest(Graphics2D g2d, int x, int y) {
        g2d.fillRect(x, y, 12, 5);
    }

    private void drawHalfNote(Graphics2D g2d, int x, int y) {
        g2d.drawOval(x, y, 10, 10);  // Note head
        g2d.drawLine(x + 10, y + 5, x + 10, y - 30);  // Note stem
    }

    private void drawDottedHalfNote(Graphics2D g2d, int x, int y) {
        drawHalfNote(g2d, x, y);
        g2d.fillOval(x + 20, y + 1, 5, 5);
    }

    private void drawHalfRest(Graphics2D g2d, int x, int y) {
        g2d.fillRect(x, y + 5, 12, 5);
    }

    private void drawQuarterNote(Graphics2D g2d, int x, int y) {
        g2d.fillOval(x, y, 10, 10);  // Note head
        g2d.drawLine(x + 10, y + 5, x + 10, y - 30);  // Note stem
    }

    private void drawDottedQuarterNote(Graphics2D g2d, int x, int y) {
        drawQuarterNote(g2d, x, y);
        g2d.fillOval(x + 20, y + 1, 5, 5);
    }

    private void drawDoubleDottedQuarterNote(Graphics2D g2d, int x, int y) {
        drawQuarterNote(g2d, x, y);
        g2d.fillOval(x + 20, y + 1, 5, 5);
        g2d.fillOval(x + 30, y + 1, 5, 5);
    }

    private void drawQuarterRest(Graphics2D g2d, int x, int y) {
        JLabel quarterRest = new JLabel(restIcons[0]);
        quarterRest.setBounds(0, 0, widths[0], heights[0]);

        JPanel panel = new JPanel();
        panel.setBounds(x, y - 3, widths[0], heights[0]);
        panel.setOpaque(false);
        panel.add(quarterRest);
        add(panel);
    }

    private void drawEighthNote(Graphics2D g2d, int x, int y) {
        g2d.fillOval(x, y, 10, 10);
        g2d.drawLine(x + 10, y + 5, x + 10, y - 30);
        g2d.drawLine(x + 10, y - 30, x + 20, y - 20);
    }

    private void drawDottedEighthNote(Graphics2D g2d, int x, int y) {
        drawEighthNote(g2d, x, y);
        g2d.fillOval(x + 20, y + 1, 5, 5);
    }

    private void drawEighthRest(Graphics2D g2d, int x, int y) {
        JLabel eighthRest = new JLabel(restIcons[1]);
        eighthRest.setBounds(0, 0, widths[1], heights[1]);

        JPanel panel = new JPanel();
        panel.setBounds(x, y + 3, widths[1], heights[1]);
        panel.setOpaque(false);
        panel.add(eighthRest);
        add(panel);
    }

    private void drawSixteenthNoteGroup(Graphics2D g2d, int x, int y) {
        // Draw a group of sixteenth notes
        g2d.fillOval(x, y, 10, 10); // Note head (filled)
        g2d.drawLine(x + 10, y + 5, x + 10, y - 30); // Note stem
        g2d.drawLine(x + 10, y - 30, x + 20, y - 20); // Note flag
        g2d.drawLine(x + 10, y - 25, x + 20, y - 15); // Second connecting line
    }

    private void drawSixteenthRest(Graphics2D g2d, int x, int y) {
        JLabel sixteenthRest = new JLabel(restIcons[2]);
        sixteenthRest.setBounds(0, 0, widths[2], heights[2]);

        JPanel panel = new JPanel();
        panel.setBounds(x, y + 3, widths[2], heights[2]);
        panel.setOpaque(false);
        panel.add(sixteenthRest);
        add(panel);
    }

    private void drawTriplet(Graphics2D g2d, int x, int y) {
        drawEighthNote(g2d, x, y);
        g2d.drawString("3", x, y - 40); // Triplet marker
    }

    private void drawTripletRest(Graphics2D g2d, int x, int y) {
        drawEighthRest(g2d, x, y);
        g2d.drawString("3", x, y - 40); // Triplet marker
    }

    private void drawAugmentedTriplet(Graphics2D g2d, int x, int y) {
        drawQuarterNote(g2d, x, y);
        g2d.drawString("3", x, y - 40); // Augmented triplet marker
    }

    private void drawAugTripletRest(Graphics2D g2d, int x, int y) {
        drawQuarterRest(g2d, x, y);
        g2d.drawString("3", x, y - 40); // Triplet marker
    }

    private void drawQuintuplet(Graphics2D g2d, int x, int y) {
        drawEighthNote(g2d, x, y);
        g2d.drawString("5", x, y - 40); // Quintuplet marker
    }

    private void drawQuintupletRest(Graphics2D g2d, int x, int y) {
        drawEighthRest(g2d, x, y);
        g2d.drawString("5", x, y - 40); // Triplet marker
    }

    private void drawProQuintuplet(Graphics2D g2d, int x, int y) {
        drawQuarterNote(g2d, x, y);
        g2d.drawString("5", x, y - 40); // Quintuplet marker
    }

    private void drawProQuintupletRest(Graphics2D g2d, int x, int y) {
        drawQuarterRest(g2d, x, y);
        g2d.drawString("5", x, y - 40); // Triplet marker
    }

    public void drawSharp(Graphics2D g2d, int x, int y) {
        // Draw a sharp sign at position (x, y)
        g2d.drawLine(x, y - 10, x, y + 10); // Vertical line 1
        g2d.drawLine(x + 6, y - 10, x + 6, y + 10); // Vertical line 2
        g2d.drawLine(x - 3, y - 4, x + 9, y - 4); // Horizontal line 1
        g2d.drawLine(x - 3, y + 4, x + 9, y + 4); // Horizontal line 2
    }

    private void editImage() {
        String currentDirectory = System.getProperty("user.dir");
        String imagePath = currentDirectory + File.separator + "\\musicgenreclassifier\\src\\main\\java\\com\\graphics\\rest_quarter_removebg.png";

        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        widths[0] = icon.getIconWidth() / 12;
        heights[0] = icon.getIconHeight() / 12;
        Image resizedImage = image.getScaledInstance(widths[0], heights[0], Image.SCALE_SMOOTH);
        restIcons[0] = new ImageIcon(resizedImage);

        imagePath = currentDirectory + File.separator + "\\musicgenreclassifier\\src\\main\\java\\com\\graphics\\rest_eighth_removebg.png";

        icon = new ImageIcon(imagePath);
        image = icon.getImage();
        widths[1] = icon.getIconWidth() / 8;
        heights[1] = icon.getIconHeight() / 8;
        resizedImage = image.getScaledInstance(widths[1], heights[1], Image.SCALE_SMOOTH);
        restIcons[1] = new ImageIcon(resizedImage);
        
        imagePath = currentDirectory + File.separator + "\\musicgenreclassifier\\src\\main\\java\\com\\graphics\\rest_sixteenth_removebg.png";

        icon = new ImageIcon(imagePath);
        image = icon.getImage();
        widths[2] = icon.getIconWidth() / 11;
        heights[2] = icon.getIconHeight() / 11;
        resizedImage = image.getScaledInstance(widths[2], heights[2], Image.SCALE_SMOOTH);
        restIcons[2] = new ImageIcon(resizedImage);
        
        imagePath = currentDirectory + File.separator + "\\musicgenreclassifier\\src\\main\\java\\com\\graphics\\rest_thirtysecond_removebg.png";

        icon = new ImageIcon(imagePath);
        image = icon.getImage();
        widths[3] = icon.getIconWidth() / 1;
        heights[3] = icon.getIconHeight() / 1;
        resizedImage = image.getScaledInstance(widths[3], heights[3], Image.SCALE_SMOOTH);
        restIcons[3] = new ImageIcon(resizedImage);
        
    }
}
