package com.musicgenreclassifier;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ReadSheet {
        public ReadSheet(){
            String filePath = "C:\\projects\\MusicGenreClassifier\\musicgenreclassifier\\src\\main\\java\\com\\musicgenreclassifier\\sheetmusic.txt";  // Replace with your txt file path

            try {
                // Create Scanner object to read the file
                Scanner scanner = new Scanner(new File(filePath));

                // Read bpm from the first line
                int bpm = Integer.parseInt(scanner.nextLine().trim());

                // Read noteSequence from the second line
                String[] noteSequenceStr = scanner.nextLine().trim().split(" ");
                ArrayList<Integer> noteSequence = new ArrayList<>();
                for (String note : noteSequenceStr) {
                    noteSequence.add(Integer.parseInt(note));
                }

                // Read noteDurations from the third line
                String[] noteDurationsStr = scanner.nextLine().trim().split(" ");
                ArrayList<Double> noteDurations = new ArrayList<>();
                for (String duration : noteDurationsStr) {
                    noteDurations.add(Double.parseDouble(duration));
                }

                // Read style from the fourth line
                int style = Integer.parseInt(scanner.nextLine().trim());

                // Read baramount from the fifth line
                int baramount = Integer.parseInt(scanner.nextLine().trim());

                // Close scanner
                scanner.close();

                // Create Judge object and perform judgement
                Judge judge = new Judge(noteSequence, noteDurations, 100);
                Map<Integer, ArrayList<Integer>> chordMap = judge.judgement();

                // Create MidiGenerator object and generate MIDI file
                MidiGenerator midiGenerator = new MidiGenerator(bpm, 56, noteSequence, bpm, 100, noteDurations, 3);
                midiGenerator.playRhythm();
                midiGenerator.saveToFile("output.mid");

                // Create DefineStyle object and call midimerge method
                DefineStyle defineStyle = new DefineStyle(style, bpm, chordMap,baramount);
                defineStyle.midimerge();

            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

