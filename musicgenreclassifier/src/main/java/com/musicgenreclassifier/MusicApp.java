import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MusicApp extends JPanel {

    private ArrayList<Note> list = new ArrayList<>();
    private static final String[] NOTES = {"C", "D", "E", "F", "G", "A", "B"};
    private static final String[] SHARPS = {"C#", "D#", "", "F#", "G#", "A#", ""};
    private String selectedNoteType = null;
    private int bpm = 0;
    private int pitch = 0;
    private PianoPanel pianoPanel;
    private JLayeredPane layeredPane = new JLayeredPane();
    private JButton finish;
    private MusicNotations notationPanels;
    private boolean first = true;
    private double total = 0;
    private int index = 0;
    private JButton finished;
    private int bar = 0;
    private int style = 7;
    private double oneBar = 0;
    private boolean clicked = false;
    /*private int tripletCount = 0;
    private boolean tripletMode = false;*/


    public MusicApp() {
        setLayout(new BorderLayout());

        // Create layered pane for piano and notation
        layeredPane.setPreferredSize(new Dimension(1500, 600));

        // Create and configure piano panel
        pianoPanel = new PianoPanel();
        pianoPanel.setBounds(0, 300,1500, 300);

        // Create and configure notation panel
        MusicNotation notationPanel = new MusicNotation();
        notationPanel.setBounds(0, 0, 1500, 300);
        notationPanel.setOpaque(false);

        // Add panels to layered pane
        layeredPane.add(notationPanel, Integer.valueOf(1));
        layeredPane.add(pianoPanel, Integer.valueOf(2));

        add(layeredPane, BorderLayout.CENTER);
        checkEnablePianoKeys();

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new GridLayout(4, 1));
        JPanel buttonPanel = new JPanel();
        String[] noteTypes = {"Whole", "Half","DottedHalf", "Quarter", "DottedQuarter", "Eighth", "DottedEighth", "DoubleDottedQuarter",  "Sixteenth", "ThirtySecond", "Triple", "AugTriple", "Quintuplet", "ProQuintuplet"};
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
        String[] pauseTypes = {"WholeNoteRest", "HalfNoteRest", "QuarterRest", "EighthRest", "SixteenthRest", "TripletRest", "AugTripletRest", "QuintupletRest", "ProQuintupletRest", "ThirtySecondRest"};
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

        finish = new JButton("save");
        finish.setEnabled(false);
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component[] components = layeredPane.getComponentsInLayer(3);
                Component component = components[0];
                layeredPane.remove(component);
                layeredPane.revalidate();
                layeredPane.repaint();
                total = 0;
                index = list.size();
                first = true;
                if(style!=7){
                    finished.setEnabled(true);
                }
                finish.setEnabled(false);
                clicked = true;
                bar+=4;
            }
        });
        pitchPanel.add(finish);

        JPanel stylePanel = new JPanel();
        String[] styleList = {"rock ", "jazz ", "bossanova ", "rnb ", "soul ", "funk ", "reggae "};
        for (int i=0; i<7; i++) {
            JButton button = new JButton(styleList[i] + i);
            button.setActionCommand(String.valueOf(i));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    style = Integer.parseInt(e.getActionCommand());
                    if(clicked){
                        finished.setEnabled(true);
                    }
                }
            });
            stylePanel.add(button);
        }


        finished = new JButton("finished");
        finished.setEnabled(false);
        finish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent i) {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("sheetmusic.txt"))) {
                    writer.write(Integer.toString(bpm));
                    writer.write(" ");
                    writer.write("\n");
                    for(Note note : list) {
                        if(note.get_Node() != -1){
                            writer.write(Integer.toString((note.get_Node()+12)+note.get_Pitch()*12));
                        }
                        else {
                            writer.write("-1");
                        }
                        writer.write(" ");
                    }
                    writer.write("\n");
                    for(Note note : list) {
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
            }
        });
        pitchPanel.add(finished);

        JPanel bpm_panel = new JPanel();
        bpm_panel.setOpaque(false);
        JLabel bpmLabel = new JLabel("BPM:");
        JTextField bpmField = new JTextField("0", 5);
        bpmField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bpm = Integer.parseInt(bpmField.getText());
                    if(total == 16) {
                        finish.setEnabled(true);
                    }
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

    private void checkEnablePianoKeys() {
        if (pitch != 0 && selectedNoteType != null) {
            pianoPanel.enablePianoKeys(true);
        }
        else
            pianoPanel.enablePianoKeys(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Music Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 600);

        MusicApp musicApp = new MusicApp();
        frame.add(musicApp);

        frame.setVisible(true);
    }
    private class PianoPanel extends JLayeredPane implements ActionListener {

        private ArrayList<JButton> whiteKeys = new ArrayList<>();
        private ArrayList<JButton> blackKeys = new ArrayList<>();
        public PianoPanel() {
            setLayout(null);

            int keyWidth = 60;
            int keyHeight = 200;
            int offsetX = 10;
            int offsetY = 50; // Adjust this value to move the piano keyboard down

            int blackKeyWidth = 40;
            int blackKeyHeight = 120;
            int blackKeyOffset = keyWidth - (blackKeyWidth / 2);

            // Create white keys
            for (int i = 0; i < NOTES.length; i++) {
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
            for (int i = 0; i < SHARPS.length; i++) {
                if (!SHARPS[i].isEmpty()) {
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
            Note note = new Note();
            if (command.startsWith("white:")) {
                int index = Integer.parseInt(command.split(":")[1]);
                if (NOTES[index].equals("C")) {
                    note.add_Note(0);
                } else if (NOTES[index].equals("D")) {
                    note.add_Note(2);
                } else if (NOTES[index].equals("E")) {
                    note.add_Note(4);
                } else if (NOTES[index].equals("F")) {
                    note.add_Note(5);
                } else if (NOTES[index].equals("G")) {
                    note.add_Note(7);
                } else if (NOTES[index].equals("A")) {
                    note.add_Note(9);
                } else if (NOTES[index].equals("B")) {
                    note.add_Note(11);
                }
            } else if (command.startsWith("black:")) {
                int index = Integer.parseInt(command.split(":")[1]);
                if (SHARPS[index].equals("C#")) {
                    note.add_Note(1);
                } else if(SHARPS[index].equals("D#")) {
                    note.add_Note(3);
                } else if(SHARPS[index].equals("F#")) {
                    note.add_Note(6);
                } else if(SHARPS[index].equals("G#")) {
                    note.add_Note(8);
                } else if(SHARPS[index].equals("A#")) {
                    note.add_Note(10);
                }
            } if (selectedNoteType.equals("WholeNoteRest") || selectedNoteType.equals("HalfNoteRest") || selectedNoteType.equals("QuarterRest") || selectedNoteType.equals("EighthRest") || selectedNoteType.equals("SixteenthRest") || selectedNoteType.equals("ThirtySecondRest") || selectedNoteType.equals("TripleRest") || selectedNoteType.equals("AugTripletRest") || selectedNoteType.equals("QuintupletRest") || selectedNoteType.equals("ProQuintupletRest")) {
                note.add_Note(-1);
            }
            clicked = false;
            finished.setEnabled(false);
            note.add_time(selectedNoteType);
            note.add_Pitch(pitch);
            repaint();
            list.add(note);
            total = 0;
            oneBar = 0;
            for(int i =index; i<list.size(); i++) {
                Note notes = list.get(i);
                total += 1/notes.get_time();
            }
            for(int i =index; i<list.size(); i++) {
                Note notes = list.get(i);
                oneBar += 1/notes.get_time();
                if(oneBar == 4){
                    oneBar = 0;
                }
                else if(oneBar>4){
                    list.remove(list.size()-1);
                    return;
                }
            }
            System.out.println(total);
            if (total == 16 && bpm != 0) {
                finish.setEnabled(true);
            }
            else if(total > 16) {
                list.remove(list.size()-1);
                return;
            }
            if(first == false) {
                Component[] components = layeredPane.getComponentsInLayer(3);
                Component component = components[0];
                layeredPane.remove(component);
                layeredPane.revalidate();
                layeredPane.repaint();
            }
            notationPanels = new MusicNotations();
            notationPanels.setBounds(50, 0, 1500, 300);
            notationPanels.setOpaque(false);
            layeredPane.add(notationPanels, Integer.valueOf(3));
            checkEnablePianoKeys();
            selectedNoteType = null;
            pitch = 0;
            checkEnablePianoKeys();
            first = false;

            System.out.println("Current Notes: " + note.get_Node() + " " + note.get_type() + " " + note.get_Pitch());
            // Add code to play the note here
        }
    }

    private class MusicNotation extends JPanel {        
        public MusicNotation() {
            this.setLayout(null);
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBounds(30, 50, 100, 100);
            panel.setOpaque(false);

            ImageIcon icon = new ImageIcon("treble_clef_notation_removebg.png");
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

        Graphics2D g2d;
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            this.g2d = g2d;

            // Enable anti-aliasing
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw staff
            drawStaff(g2d);

            /* Draw notes*/
        }

        private void drawStaff(Graphics2D g2d) {
            int yStart = 70; // Adjust this value to move the staff down
            int yGap = 10;

            for (int i = 0; i < 5; i++) {
                g2d.drawLine(50, yStart + i * yGap, 2500, yStart + i * yGap);
            }
        }
    }

    private class MusicNotations extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Enable anti-aliasing
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw staff

            // Draw notes
            int x = 55;
            int y = 0;
            double num = 0;
            for(int i=index; i<list.size(); i++){
                Note note = list.get(i);
                num += 1/note.get_time();
                switch (note.get_Node()) {
                    case 0:
                        y = 80;
                        break;
                    case 1:
                        y = 80;
                        drawSharp(g2d, x-5, y+5);
                        break;
                    case 2:
                        y = 110;
                        break;
                    case 3:
                        y = 110;
                        drawSharp(g2d, x-5, y+5);
                        break;
                    case 4:
                        y = 105;
                        break;
                    case 5:
                        y = 100;
                        break;
                    case 6:
                        y = 100;
                        drawSharp(g2d, x-5, y+5);
                        break;
                    case 7:
                        y = 95;
                        break;
                    case 8:
                        y = 95;
                        drawSharp(g2d, x-5, y+5);
                        break;
                    case 9:
                        y = 90;
                        break;
                    case 10:
                        y = 90;
                        drawSharp(g2d, x-5, y+5);
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
                if(num%4 == 0) {
                    g2d.drawLine(x, 70, x, 110);
                    x += 100;
                }
            }
        }
    
            
        public void drawNoteByType(Graphics2D g2d, int x, int y, String noteType) {
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
                g2d.fillOval(x + 20, y +1, 5, 5); 
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
                g2d.fillOval(x + 20, y +1, 5, 5); 
            }

            private void drawDoubleDottedQuarterNote(Graphics2D g2d, int x, int y) {
                drawQuarterNote(g2d, x, y);
                g2d.fillOval(x + 20, y + 1, 5, 5);
                g2d.fillOval(x + 30, y +1, 5, 5); 
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
                g2d.fillOval(x + 20, y +1, 5, 5); 
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
}


class Note {
    public double time = 0;
    public String type;
    public int pitch = 0;
    public int key = -1;
    public boolean rest = false;
    public void add_Note(int key) {
        this.key = key;
    }
    public Integer get_Node() {
        return key;
    }
    public void add_time(String type) {
        this.type = type;
        switch (type) {
            case "Whole":
                time = 0.25;
                break;
            case "WholeNoteRest":
                time = 0.25;
                break;
            case "DottedHalf":
                time = 1.0/3.0;
                break;
            case "Half":
                time = 0.5;
                break;
            case "HalfNoteRest":
                time = 0.5;
                break;
            case "Quarter":
                time = 1;
                break;
            case "QuarterRest":
                time = 1;
                break;
            case "DottedQuarter":
                time = 2.0/3.0;
                break;
            case "DoubleDottedQuarter":
                time = 4.0/7.0;
                break;
            case "Eighth":
                time = 2;
                break;
            case "DottedEighth":
                time = 4.0/3.0;
                break;
            case "EighthRest":
                time = 2;
                break;
            case "Sixteenth":
                time = 4;
                break;
            case "SixteenthRest":
                time = 4;
                break;
            case "ThirtySecond":
                time = 8;
                break;
            case "ThirtySecondRest":
                time = 8;
                break;
            case "Triple":
                time = 3;
                break;
            case "TripletRest":
                time = 3;
                break;
            case "AugTriple":
                time = 1.5;
                break;
            case "AugTripletRest":
                time = 1.5;
                break;
            case "Quintuplet":
                time = 5;
                break;
            case "QuintupletRest":
                time = 5;
                break;
            case "ProQuintuplet":
                time = 2.5;
                break;
            case "ProQuintupletRest":
                time = 2.5;
                break;
        }
    }
    public String get_type() {
        return type;
    }
    public void add_Pitch(int pitch) {
        this.pitch = pitch;
    }
    public Integer get_Pitch() {
        return pitch;
    }
    public Double get_time() {
        return time;
    }
}
