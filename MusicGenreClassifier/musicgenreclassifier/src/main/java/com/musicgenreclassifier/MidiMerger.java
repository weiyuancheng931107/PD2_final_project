package com.musicgenreclassifier;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MidiMerger {

    public static void main(String[] args) {
        String[] midiFiles = {"piano.mid", "drums.mid", "bass.mid"};
        String outputFile = "merged.mid";

        try {
            mergeMidiFiles(midiFiles, outputFile);
            System.out.println("MIDI files merged successfully into " + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mergeMidiFiles(String[] midiFiles, String outputFile) throws IOException, InvalidMidiDataException {
        Sequence[] sequences = new Sequence[midiFiles.length];
        for (int i = 0; i < midiFiles.length; i++) {
            sequences[i] = MidiSystem.getSequence(new File(midiFiles[i]));
        }

        Sequence mergedSequence = mergeSequences(sequences);
        MidiSystem.write(mergedSequence, 1, new File(outputFile));
    }

    private static Sequence mergeSequences(Sequence[] sequences) throws InvalidMidiDataException {
        // Assuming all sequences have the same division type and resolution
        float divisionType = sequences[0].getDivisionType();
        int resolution = sequences[0].getResolution();

        Sequence mergedSequence = new Sequence(divisionType, resolution);

        for (Sequence sequence : sequences) {
            for (Track track : sequence.getTracks()) {
                Track newTrack = mergedSequence.createTrack();
                for (int i = 0; i < track.size(); i++) {
                    MidiEvent event = track.get(i);
                    newTrack.add(event);
                }
            }
        }
        
        return mergedSequence;
    }
}
