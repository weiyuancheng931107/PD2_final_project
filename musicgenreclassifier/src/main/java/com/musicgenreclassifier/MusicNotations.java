package com.musicgenreclassifier;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MusicNotations extends JPanel {
    private MusicApp musicApp;

    public MusicNotations(MusicApp musicApp) {
        this.musicApp = musicApp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw staff
        drawStaff(g2d);

        // Draw notes
        int x = 55;
        int y = 0;
        double num = 0;
        for (int i = musicApp.getIndex(); i < musicApp.getList().size(); i++) {
            Notes note = musicApp.getList().get(i);
            num += 1 / note.get_time();
            switch (note.get_Node()) {
                case 0:
                    y = 80;
                    break;
                case 1:
                    y = 80;
                    drawSharp(g2d, x - 5, y + 5);
                    break;
                case 2:
                    y = 110;
                    break;
                case 3:
                    y = 110;
                    drawSharp(g2d, x - 5, y + 5);
                    break;
                case 4:
                    y = 105;
                    break;
                case 5:
                    y = 100;
                    break;
                case 6:
                    y = 100;
                    drawSharp(g2d, x - 5, y + 5);
                    break;
                case 7:
                    y = 95;
                    break;
                case 8:
                    y = 95;
                    drawSharp(g2d, x - 5, y + 5);
                    break;
                case 9:
                    y = 90;
                    break;
                case 10:
                    y = 90;
                    drawSharp(g2d, x - 5, y + 5);
                    break;
                case 11:
                    y = 85;
                    break;
                case -1:
                    y = 60;
                    break;
            }
            drawNoteByType(g2d, x, y, note.get_type());
            x += 100;
            if (num % 4 == 0) {
                g2d.drawLine(x, 70, x, 110);
                x += 100;
            }
        }
    }

    private void drawStaff(Graphics2D g2d) {
        int yStart = 70; // Adjust this value to move the staff down
        int yGap = 10;

        for (int i = 0; i < 5; i++) {
            g2d.drawLine(50, yStart + i * yGap, 2500, yStart + i * yGap);
        }
    }

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
            case "ThirtySecond":
                drawThirtySecondNote(g2d, x, y);
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
            case "QuarterRest":
                drawQuarterRest(g2d, x, y);
                break;
            case "WholeNoteRest":
                drawWholeRest(g2d, x, y);
                break;
            case "HalfNoteRest":
                drawHalfRest(g2d, x, y);
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
            case "ThirtySecondRest":
                drawThirtySecondRest(g2d, x, y);
        }
    }

    private void drawWholeNote(Graphics2D g2d, int x, int y) {
        g2d.drawOval(x, y, 20, 10); // Note head (hollow and wider)
    }

    private void drawWholeRest(Graphics2D g2d, int x, int y) {
        g2d.fillRect(x, y + 30, 40, 10);
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
        g2d.fillRect(x, y + 20, 40, 10);
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
        // Draw the vertical line
        g2d.drawLine(x, y, x, y + 20);

        // Draw the top left hook
        g2d.drawLine(x, y, x + 10, y + 10);

        // Draw the bottom right hook
        g2d.drawLine(x, y + 20, x - 10, y + 10);
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
        int[] xPoints = {x, x + 5, x + 3, x + 10, x + 8, x + 13};
        int[] yPoints = {y + 20, y + 25, y + 30, y + 35, y + 40, y + 45};
        g2d.drawPolyline(xPoints, yPoints, xPoints.length);

        g2d.fillOval(x + 8, y + 40, 5, 5);
    }

    private void drawSixteenthNoteGroup(Graphics2D g2d, int x, int y) {
        // Draw a group of sixteenth notes
        g2d.fillOval(x, y, 10, 10); // Note head (filled)
        g2d.drawLine(x + 10, y + 5, x + 10, y - 30); // Note stem
        g2d.drawLine(x + 10, y - 30, x + 20, y - 20); // Note flag
        g2d.drawLine(x + 10, y - 25, x + 20, y - 15); // Second connecting line
    }

    private void drawSixteenthRest(Graphics2D g2d, int x, int y) {
        int[] xPoints1 = {x, x + 5, x + 3, x + 10, x + 8, x + 13};
        int[] yPoints1 = {y + 20, y + 25, y + 30, y + 35, y + 40, y + 45};
        g2d.drawPolyline(xPoints1, yPoints1, xPoints1.length);

        g2d.fillOval(x + 8, y + 40, 5, 5);

        int[] xPoints2 = {x + 3, x + 8, x + 6, x + 13, x + 11, x + 16};
        int[] yPoints2 = {y + 15, y + 20, y + 25, y + 30, y + 35, y + 40};
        g2d.drawPolyline(xPoints2, yPoints2, xPoints2.length);

        g2d.fillOval(x + 11, y + 35, 5, 5);
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

    private void drawThirtySecondNote(Graphics2D g2d, int x, int y) {
        g2d.fillOval(x, y, 10, 10); // Note head (filled)
        g2d.drawLine(x + 10, y + 5, x + 10, y - 30); // Note stem
        g2d.drawLine(x + 10, y - 30, x + 20, y - 20); // Note flag
        g2d.drawLine(x + 10, y - 25, x + 20, y - 15); // Second flag
        g2d.drawLine(x + 10, y - 20, x + 20, y - 10); // Third flag
    }

    private void drawThirtySecondRest(Graphics2D g2d, int x, int y) {
        int[] xPoints1 = {x, x + 5, x + 3, x + 10, x + 8, x + 13};
        int[] yPoints1 = {y + 20, y + 25, y + 30, y + 35, y + 40, y + 45};
        g2d.drawPolyline(xPoints1, yPoints1, xPoints1.length);

        g2d.fillOval(x + 8, y + 40, 5, 5);

        int[] xPoints2 = {x + 3, x + 8, x + 6, x + 13, x + 11, x + 16};
        int[] yPoints2 = {y + 15, y + 20, y + 25, y + 30, y + 35, y + 40};
        g2d.drawPolyline(xPoints2, yPoints2, xPoints2.length);

        g2d.fillOval(x + 11, y + 35, 5, 5);

        int[] xPoints3 = {x + 6, x + 11, x + 9, x + 16, x + 14, x + 19};
        int[] yPoints3 = {y + 10, y + 15, y + 20, y + 25, y + 30, y + 35};
        g2d.drawPolyline(xPoints3, yPoints3, xPoints3.length);

        g2d.fillOval(x + 14, y + 30, 5, 5);
    }

    public void drawSharp(Graphics2D g2d, int x, int y) {
        // Draw a sharp sign at position (x, y)
        g2d.drawLine(x, y - 10, x, y + 10); // Vertical line 1
        g2d.drawLine(x + 6, y - 10, x + 6, y + 10); // Vertical line 2
        g2d.drawLine(x - 3, y - 4, x + 9, y - 4); // Horizontal line 1
        g2d.drawLine(x - 3, y + 4, x + 9, y + 4); // Horizontal line 2
    }
}
