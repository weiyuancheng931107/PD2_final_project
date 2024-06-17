package com.musicgenreclassifier;

import javax.swing.*;

import com.innerjudge.ReadSheet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MusicApp extends JPanel {
    public static final String[] NOTES = {"C", "D", "E", "F", "G", "A", "B"};
    public static final String[] SHARPS = {"C#", "D#", "", "F#", "G#", "A#"};

    private ArrayList<OneNote> list = new ArrayList<>();
    private OneNote note;

    private JLayeredPane layeredPane = new JLayeredPane();
    private PianoPanel pianoPanel;
    private MusicNotations notationPanel;
    private int bpm = 0;
    private int pitch = 0;
    private String selectedNoteType = null;
    private boolean first = true;
    private double total = 0;
    private int index = 0;
    private JButton finished;
    private JButton backward; // Declare backward button
    private int bar = 0;
    private int style = 7;
    private double oneBar = 0;
    final double TOLERANCE = 0.0001;

    public MusicApp() {
        setLayout(new BorderLayout());

        // Create layered pane for piano and notation
        layeredPane.setBounds(0, 0, 1500, 800);

        // Create and configure piano panel
        pianoPanel = new PianoPanel(this);
        pianoPanel.setBounds(0, 350, 1500, 250);
        pianoPanel.enablePianoKeys(false);

        // Create and configure notation panel
        MusicNotation notationPanel = new MusicNotation();
        notationPanel.setBounds(0, 0, 1700, 350);

        // Add panels to layered pane
        layeredPane.add(notationPanel, Integer.valueOf(1));
        layeredPane.add(pianoPanel, Integer.valueOf(2));

        add(layeredPane);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new GridLayout(4, 1));
        JPanel buttonPanel = new JPanel();
        String[] noteTypes = {"Whole", "Half", "DottedHalf", "Quarter", "DottedQuarter", "Eighth", "DottedEighth", "DoubleDottedQuarter", "Sixteenth", "Triple", "AugTriple", "Quintuplet", "ProQuintuplet"};
        for (String noteType : noteTypes) {
            JButton button = new JButton(noteType);
            button.setActionCommand(noteType);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedNoteType = e.getActionCommand();
                    checkEnablePianoKeys();
                }
            });
            buttonPanel.add(button);
        }
        totalPanel.add(buttonPanel);

        JPanel pausePanel = new JPanel();
        String[] pauseTypes = {"WholeNoteRest", "HalfNoteRest", "QuarterRest", "EighthRest", "SixteenthRest", "TripletRest", "AugTripletRest", "QuintupletRest", "ProQuintupletRest"};
        for (String noteType : pauseTypes) {
            JButton button = new JButton(noteType);
            button.setActionCommand(noteType);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedNoteType = e.getActionCommand();
                    checkEnablePianoKeys();
                }
            });
            pausePanel.add(button);
        }
        totalPanel.add(pausePanel);

        JPanel pitchPanel = new JPanel();

        for (int i = 1; i <= 8; i++) {
            JButton pitchButton = new JButton("Pitch " + i);
            pitchButton.setActionCommand(String.valueOf(i));
            pitchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pitch = Integer.parseInt(e.getActionCommand());
                    checkEnablePianoKeys();
                }
            });
            pitchPanel.add(pitchButton);
        }

        JPanel stylePanel = new JPanel();
        String[] styleList = {"rock", "jazz", "bossanova", "rnb", "soul", "funk", "reggae"};
        for (int i = 0; i < 7; i++) {
            JButton button = new JButton(styleList[i]);
            button.setActionCommand(String.valueOf(i));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    style = Integer.parseInt(e.getActionCommand());
                    checkFinished();
                }
            });
            stylePanel.add(button);
        }

        finished = new JButton("finished");
        finished.setEnabled(false);
        finished.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent i) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("sheetmusic.txt"))) {
                    writer.write(Integer.toString(bpm));
                    writer.write(" ");
                    writer.write("\n");
                    for (OneNote note : list) {
                        if (note.get_Note() != -1) {
                            writer.write(Integer.toString((note.get_Note() + 12) + note.get_Pitch() * 12));
                        } else {
                            writer.write("-1");
                        }
                        writer.write(" ");
                    }
                    writer.write("\n");
                    for (OneNote note : list) {
                        writer.write(Double.toString(note.get_time()));
                        writer.write(" ");
                    }
                    writer.write("\n");
                    writer.write(Integer.toString(style));
                    writer.write(" ");
                    writer.write("\n");
                    writer.write(Integer.toString(bar));
                    writer.write(" ");
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.clear();
                bar = 0;
                finished.setEnabled(false);
                total = 0;
                ReadSheet readSheet = new ReadSheet();
                readSheet.Read();
                System.exit(0);
            }
        });
        pitchPanel.add(finished);

        backward = new JButton("Backward");
        backward.setEnabled(false);
        backward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeLastNote();
            }
        });
        pitchPanel.add(backward); // Add the button to the appropriate panel, here pitchPanel

        JPanel bpm_panel = new JPanel();
        bpm_panel.setOpaque(false);
        JLabel bpmLabel = new JLabel("BPM:");
        final JTextField bpmField = new JTextField("0", 5);
        bpmField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bpm = Integer.parseInt(bpmField.getText());
                    checkFinished();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter again.");
                }
            }
        });
        bpm_panel.add(bpmLabel, BorderLayout.NORTH);
        bpm_panel.add(bpmField, BorderLayout.CENTER);

        totalPanel.add(pitchPanel);
        totalPanel.add(stylePanel);
        add(totalPanel, BorderLayout.SOUTH);
        add(bpm_panel, BorderLayout.NORTH);
    }

    public void checkEnablePianoKeys() {
        if (pitch != 0 && selectedNoteType != null) {
            pianoPanel.enablePianoKeys(true);
        } else {
            pianoPanel.enablePianoKeys(false);
        }
        backward.setEnabled(!list.isEmpty()); // Enable backward button if list is not empty
    }

    public void checkFinished() {
        if (bpm != 0 && style != 7 && Math.abs(total - 16) < TOLERANCE) {
            finished.setEnabled(true);
        } else {
            finished.setEnabled(false);
        }
    }

    private void removeLastNote() {
        if (!list.isEmpty()) {
            list.remove(list.size() - 1);
            updateNotationPanel();
            updateTotal();
            checkFinished();
            checkEnablePianoKeys();
        }
    }

    private void updateNotationPanel() {
        Component[] components = layeredPane.getComponentsInLayer(3);
        if (components.length > 0) {
            Component component = components[0];
            layeredPane.remove(component);
            layeredPane.revalidate();
            layeredPane.repaint();
        }
        if (!list.isEmpty()) {
            setFirst(false);
        }
        setNotationPanels(new MusicNotations(this));
        getNotationPanels().setBounds(50, 0, 1700, 700);
        getNotationPanels().setOpaque(false);
        layeredPane.add(getNotationPanels(), Integer.valueOf(3));
    }

    private void updateTotal() {
        total = 0;
        for (OneNote note : list) {
            total += 1 / note.get_time();
        }
        setOneBar(0);
        for (int i = 0; i < list.size(); i++) {
            OneNote localNote = list.get(i);
            setOneBar(getOneBar() + 1 / localNote.get_time());
            if (Math.abs(getOneBar() - 4) < TOLERANCE) {
                setOneBar(0);
            } else if (getOneBar() > 4) {
                setOneBar(getOneBar() - 1 / localNote.get_time());
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Music Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);

        MusicApp musicApp = new MusicApp();
        frame.add(musicApp);

        frame.setVisible(true);
    }

    public ArrayList<OneNote> getList() {
        return list;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getBar() {
        return bar;
    }

    public void setBar(int bar) {
        this.bar = bar;
    }

    public double getOneBar() {
        return oneBar;
    }

    public void setOneBar(double oneBar) {
        this.oneBar = oneBar;
    }

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public MusicNotations getNotationPanels() {
        return notationPanel;
    }

    public void setNotationPanels(MusicNotations notationPanel) {
        this.notationPanel = notationPanel;
    }

    public JButton getFinishedButton() {
        return finished;
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public String getSelectedNoteType() {
        return selectedNoteType;
    }

    public void setSelectedNoteType(String selectedNoteType) {
        this.selectedNoteType = selectedNoteType;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public double getTolerance() {
        return TOLERANCE;
    }
}
