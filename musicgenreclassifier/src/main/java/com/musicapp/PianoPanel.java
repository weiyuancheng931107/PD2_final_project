package com.musicapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PianoPanel extends JLayeredPane implements ActionListener {
    public static final int[] WHITEKEYS = {0, 2, 4, 5, 7, 9, 11}; // {"C", "D", "E", "F", "G", "A", "B"}
    public static final int[] BLACKKEYS = {1, 3, -1, 6, 8, 10}; // {"C#", "D#", "", "F#", "G#", "A#"};

    private ArrayList<JButton> whiteKeys = new ArrayList<>();
    private ArrayList<JButton> blackKeys = new ArrayList<>();
    private MusicApp musicApp;

    public PianoPanel(MusicApp musicApp) {
        this.musicApp = musicApp;
        setLayout(null);

        final int WHITE_KEY_WIDTH = 60;
        final int WHITE_KEY_HEIGHT = 200;
        final int OFFSET_X = 50;
        final int OFFSET_Y = 0;

        final int BLACK_KEY_WIDTH = 40;
        final int BLACK_KEY_HEIGHT = 120;
        final int BLACK_KEY_OFFSET = WHITE_KEY_WIDTH - (BLACK_KEY_WIDTH / 2);

        // Create white keys
        for (int i = 0; i < WHITEKEYS.length; i++) {
            JButton whiteKey = new JButton();
            int x = OFFSET_X + i * WHITE_KEY_WIDTH;
            whiteKey.setBounds(x, OFFSET_Y, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
            whiteKey.setBackground(Color.WHITE);
            whiteKey.setOpaque(true);
            whiteKey.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            whiteKey.addActionListener(this);
            whiteKey.setActionCommand(String.valueOf(WHITEKEYS[i]));
            whiteKey.setEnabled(false); // Initially disabled
            whiteKeys.add(whiteKey);
            add(whiteKey, Integer.valueOf(1));
        }

        // Create black keys
        for (int i = 0; i < BLACKKEYS.length; i++) {
            if (BLACKKEYS[i] == -1) {
                continue;
            }
            JButton blackKey = new JButton();
            int x = OFFSET_X + BLACK_KEY_OFFSET + i * WHITE_KEY_WIDTH;
            blackKey.setBounds(x, OFFSET_Y, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);
            blackKey.setBackground(Color.BLACK);
            blackKey.setForeground(Color.WHITE);
            blackKey.setOpaque(true);
            blackKey.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            blackKey.addActionListener(this);
            blackKey.setActionCommand(String.valueOf(BLACKKEYS[i]));
            blackKey.setEnabled(false); // Initially disabled
            blackKeys.add(blackKey);
            add(blackKey, Integer.valueOf(2)); // Ensure black keys are on a higher layer
        }
    }

    public void enablePianoKeys(boolean enabled) {
        for (JButton key : whiteKeys) {
            key.setEnabled(enabled);
            key.setBackground(enabled ? Color.WHITE : Color.LIGHT_GRAY);
        }
        for (JButton key : blackKeys) {
            key.setEnabled(enabled);
            key.setBackground(enabled ? Color.BLACK : Color.DARK_GRAY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String command = source.getActionCommand();
        OneNote note = new OneNote();
        
        int key = Integer.parseInt(command);

        note.add_Note(key);
        
        if (musicApp.getSelectedNoteType().contains("Rest")) {
            note.add_Note(-1);
        }
        musicApp.getFinishedButton().setEnabled(false);
        musicApp.setIsFinished(false);
        note.add_time(musicApp.getSelectedNoteType());
        note.add_Pitch(musicApp.getPitch());
        //repaint();
        musicApp.getList().add(note);
        musicApp.setTotal(0);
        musicApp.setOneBar(0);
        for (int i = musicApp.getIndex(); i < musicApp.getList().size(); i++) {
            OneNote localNote = musicApp.getList().get(i);
            musicApp.setTotal(musicApp.getTotal() + 1 / localNote.get_time());
        }
        System.out.println("total: " + musicApp.getTotal());
        for (int i = musicApp.getIndex(); i < musicApp.getList().size(); i++) {
            OneNote localNote = musicApp.getList().get(i);
            musicApp.setOneBar(musicApp.getOneBar() + 1 / localNote.get_time());
            if (Math.abs(musicApp.getOneBar() - 4) < musicApp.getTolerance()) {
                musicApp.setOneBar(0);
            } else if (musicApp.getOneBar() > 4) {
                JOptionPane.showMessageDialog(this, "over one bar!", "WARNING!", JOptionPane.WARNING_MESSAGE);
                musicApp.getList().remove(musicApp.getList().size() - 1);
                return;
            }
        }
        if (Math.abs(musicApp.getTotal() - 16) < musicApp.getTolerance()) {
            musicApp.setBar(musicApp.getBar() + 4);
            refreshNotationPanel();
            musicApp.setFirst(true);
            musicApp.setIsFinished(true);
            musicApp.setIndex(musicApp.getList().size());
        }
        if (Math.abs(musicApp.getTotal() - 16) < musicApp.getTolerance() && musicApp.getBpm() != 0) {
            musicApp.getFinishedButton().setEnabled(true);
        } else if (musicApp.getTotal() > 16) {
            musicApp.getList().remove(musicApp.getList().size() - 1);
            return;
        }
        if (!musicApp.isFirst()) {
            refreshNotationPanel();
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

        // Add code to play the note here
    }

    private void refreshNotationPanel() {
        Component[] components = musicApp.getLayeredPane().getComponentsInLayer(3);
        if (components.length > 0) {
            Component component = components[0];
            musicApp.getLayeredPane().remove(component);
            musicApp.getLayeredPane().revalidate();
            musicApp.getLayeredPane().repaint();
        }
    }
    public void refreshRestNote(){
        if (Math.abs(musicApp.getTotal() - 16) < musicApp.getTolerance()) {
        System.out.println("freshed");
            musicApp.setBar(musicApp.getBar() + 4);
            refreshNotationPanel();
            musicApp.setFirst(true);
            musicApp.setIndex(musicApp.getList().size());
            musicApp.setTotal(0);
            musicApp.setIsFinished(true);
        }
    }
}
