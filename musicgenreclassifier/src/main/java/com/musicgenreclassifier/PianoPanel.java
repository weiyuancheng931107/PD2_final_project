package com.musicgenreclassifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PianoPanel extends JLayeredPane implements ActionListener {
    private ArrayList<JButton> whiteKeys = new ArrayList<>();
    private ArrayList<JButton> blackKeys = new ArrayList<>();
    private MusicApp musicApp;

    public PianoPanel(MusicApp musicApp) {
        this.musicApp = musicApp;
        setLayout(null);

        int keyWidth = 60;
        int keyHeight = 200;
        int offsetX = 10;
        int offsetY = 50; // Adjust this value to move the piano keyboard down

        int blackKeyWidth = 40;
        int blackKeyHeight = 120;
        int blackKeyOffset = keyWidth - (blackKeyWidth / 2);

        // Create white keys
        for (int i = 0; i < MusicApp.NOTES.length; i++) {
            JButton whiteKey = new JButton();
            whiteKey.setBounds(offsetX + i * keyWidth, offsetY, keyWidth, keyHeight);
            whiteKey.setBackground(Color.WHITE);
            whiteKey.setOpaque(true);
            whiteKey.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            whiteKey.addActionListener(this);
            whiteKey.setActionCommand("white:" + i);
            whiteKey.setEnabled(false); // Initially disabled
            whiteKeys.add(whiteKey);
            add(whiteKey, Integer.valueOf(1));
        }

        // Create black keys
        for (int i = 0; i < MusicApp.SHARPS.length; i++) {
            if (!MusicApp.SHARPS[i].isEmpty()) {
                JButton blackKey = new JButton();
                blackKey.setBounds(offsetX + i * keyWidth + blackKeyOffset, offsetY, blackKeyWidth, blackKeyHeight);
                blackKey.setBackground(Color.BLACK);
                blackKey.setForeground(Color.WHITE);
                blackKey.setOpaque(true);
                blackKey.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                blackKey.addActionListener(this);
                blackKey.setActionCommand("black:" + i);
                blackKey.setEnabled(false); // Initially disabled
                blackKeys.add(blackKey);
                add(blackKey, Integer.valueOf(2)); // Ensure black keys are on a higher layer
            }
        }
    }

    public void enablePianoKeys(boolean enable) {
        for (JButton key : whiteKeys) {
            key.setEnabled(enable);
            key.setBackground(enable ? Color.WHITE : Color.LIGHT_GRAY);
        }
        for (JButton key : blackKeys) {
            key.setEnabled(enable);
            key.setBackground(enable ? Color.BLACK : Color.DARK_GRAY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String command = source.getActionCommand();
        Notes note = new Notes();
        if (command.startsWith("white:")) {
            int index = Integer.parseInt(command.split(":")[1]);
            switch (MusicApp.NOTES[index]) {
                case "C":
                    note.add_Note(0);
                    break;
                case "D":
                    note.add_Note(2);
                    break;
                case "E":
                    note.add_Note(4);
                    break;
                case "F":
                    note.add_Note(5);
                    break;
                case "G":
                    note.add_Note(7);
                    break;
                case "A":
                    note.add_Note(9);
                    break;
                case "B":
                    note.add_Note(11);
                    break;
            }
        } else if (command.startsWith("black:")) {
            int index = Integer.parseInt(command.split(":")[1]);
            switch (MusicApp.SHARPS[index]) {
                case "C#":
                    note.add_Note(1);
                    break;
                case "D#":
                    note.add_Note(3);
                    break;
                case "F#":
                    note.add_Note(6);
                    break;
                case "G#":
                    note.add_Note(8);
                    break;
                case "A#":
                    note.add_Note(10);
                    break;
            }
        }
        if (musicApp.getSelectedNoteType().contains("Rest")) {
            note.add_Note(-1);
        }
        musicApp.getFinishedButton().setEnabled(false);
        note.add_time(musicApp.getSelectedNoteType());
        note.add_Pitch(musicApp.getPitch());
        repaint();
        musicApp.getList().add(note);
        musicApp.setTotal(0);
        musicApp.setOneBar(0);
        for (int i = musicApp.getIndex(); i < musicApp.getList().size(); i++) {
            Notes notes = musicApp.getList().get(i);
            musicApp.setTotal(musicApp.getTotal() + 1 / notes.get_time());
        }
        final double TOLERANCE = 0.0001;
        for (int i = musicApp.getIndex(); i < musicApp.getList().size(); i++) {
            Notes notes = musicApp.getList().get(i);
            musicApp.setOneBar(musicApp.getOneBar() + 1 / notes.get_time());
            // System.out.println(" hello" + Math.abs(musicApp.getOneBar() -4));
            if (Math.abs(musicApp.getOneBar() -4) < TOLERANCE) {
                musicApp.setOneBar(0);
            } else if (musicApp.getOneBar() > 4) {
                JOptionPane.showMessageDialog(this, "over one bar!", "WARNING!", JOptionPane.WARNING_MESSAGE);
                musicApp.getList().remove(musicApp.getList().size() - 1);
                return;
            }
        }
        System.out.println(musicApp.getTotal());
        if (musicApp.getTotal() == 16) {
            musicApp.setBar(musicApp.getBar() + 4);
            Component[] components = musicApp.getLayeredPane().getComponentsInLayer(3);
            Component component = components[0];
            musicApp.getLayeredPane().remove(component);
            musicApp.getLayeredPane().revalidate();
            musicApp.getLayeredPane().repaint();
            musicApp.setFirst(true);
            musicApp.setIndex(musicApp.getList().size());
        }
        if (musicApp.getTotal() == 16 && musicApp.getBpm() != 0) {
            musicApp.getFinishedButton().setEnabled(true);
        } else if (musicApp.getTotal() > 16) {
            musicApp.getList().remove(musicApp.getList().size() - 1);
            return;
        }
        if (!musicApp.isFirst()) {
            Component[] components = musicApp.getLayeredPane().getComponentsInLayer(3);
            Component component = components[0];
            musicApp.getLayeredPane().remove(component);
            musicApp.getLayeredPane().revalidate();
            musicApp.getLayeredPane().repaint();
        }
        if (musicApp.getTotal() < 16) {
            musicApp.setFirst(false);
        }
        musicApp.setNotationPanels(new MusicNotations(musicApp));
        musicApp.getNotationPanels().setBounds(50, 0, 1700, 700);
        musicApp.getNotationPanels().setOpaque(false);
        musicApp.getLayeredPane().add(musicApp.getNotationPanels(), Integer.valueOf(3));
        musicApp.setSelectedNoteType(null);
        musicApp.setPitch(0);
        musicApp.checkEnablePianoKeys();
        musicApp.checkFinished();

        System.out.println("Current Notes: " + note.get_Node() + " " + note.get_type() + " " + note.get_Pitch());
        // Add code to play the note here
    }
}
