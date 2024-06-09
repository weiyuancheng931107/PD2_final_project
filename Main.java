package com.musicgenreclassifier;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import com.musicgenreclassifier.MidiMerger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InvalidMidiDataException, MidiUnavailableException, IOException , InterruptedException{
        int bpm = 120;
        int velocity = 100;

        ArrayList<Integer> noteSequence = new ArrayList<>();
        ArrayList<Double> noteDurations = new ArrayList<>();

        noteSequence.add(12+12*5);
        noteSequence.add(11+12*5);
        noteSequence.add(9+12*5);
        noteSequence.add(7+12*5);
        noteSequence.add(5+12*5);
        noteSequence.add(7+12*5);
        noteSequence.add(9+12*5);
        noteSequence.add(12+12*5);
        noteSequence.add(11+12*5);
        noteSequence.add(9+12*5);
        noteSequence.add(7+12*5);
        noteSequence.add(5+12*5);
        noteSequence.add(4+12*5);
        noteSequence.add(4+12*5);

        noteDurations.add((double)(2.0/(double)(3.0)));
        noteDurations.add(2.0);
        noteDurations.add(1.0);
        noteDurations.add(2.0);
        noteDurations.add(1.0);
        noteDurations.add((double)(2.0/(double)(3.0)));
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add((double)(2.0/(double)(3.0)));
        noteDurations.add(2.0);
        noteDurations.add(1.0);
        noteDurations.add(2.0);
        noteDurations.add(2.0);
        noteDurations.add(0.25);

        Judge judge = new Judge(noteSequence, noteDurations, velocity);
        Map<Integer, ArrayList<Integer>> chordMap = judge.judgement();
        MidiGenerator midiGenerator = new MidiGenerator(bpm, 56, noteSequence, bpm, velocity, noteDurations, 0);
        midiGenerator.playRhythm();
        midiGenerator.saveToFile("output.mid");
        Jazz rock= new Jazz(bpm, velocity, chordMap);
        //Rock rock= new Rock(bpm, velocity, chordMap);
        rock.Piano();
        rock.Drums();
        rock.Bass();

        //rock.Guitar();
        MidiMerger midimerge = new MidiMerger();
        //MidiMergerTry midimergetry = new MidiMergerTry();
        //String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
        String[] midiFiles = {"drums.mid","piano.mid","bass.mid","output.mid"};
        String outputFile = "merged.mid";
        midimerge.mergeMidiFiles(midiFiles,outputFile);
        //midimerge.convertAndMergeMidiFiles(midiFiles,outputFile);
    }
}
