package com.musicgenreclassifier;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

//import com.musicgenreclassifier.midimerge;

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

        // noteSequence.add(12+12*5);
        // noteSequence.add(11+12*5);
        // noteSequence.add(9+12*5);
        // noteSequence.add(7+12*5);
        // noteSequence.add(5+12*5);
        // noteSequence.add(7+12*5);
        // noteSequence.add(9+12*5);
        // noteSequence.add(12+12*5);
        // noteSequence.add(11+12*5);
        // noteSequence.add(9+12*5);
        // noteSequence.add(7+12*5);
        // noteSequence.add(5+12*5);
        // noteSequence.add(4+12*5);
        // noteSequence.add(4+12*5);
        // noteSequence.add(0+12*5);
        // noteSequence.add(1+12*5);
        // noteSequence.add(2+12*5);
        // noteSequence.add(9+12*5);
        // noteSequence.add(9+12*5);
        // noteSequence.add(0+12*6);
        // noteSequence.add(11+12*5);
        // noteSequence.add(7+12*5);


        noteSequence.add(7+12*5);
        noteSequence.add(4+12*5);
        noteSequence.add(4+12*5);
        noteSequence.add(5+12*5);
        noteSequence.add(2+12*5);
        noteSequence.add(2+12*5);
        noteSequence.add(0+12*5);
        noteSequence.add(2+12*5);
        noteSequence.add(4+12*5);
        noteSequence.add(5+12*5);
        noteSequence.add(7+12*5);
        noteSequence.add(7+12*5);
        noteSequence.add(7+12*5);
        noteSequence.add(7+12*5);
        noteSequence.add(4+12*5);
        noteSequence.add(4+12*5);
        noteSequence.add(5+12*5);
        noteSequence.add(2+12*5);
        noteSequence.add(2+12*5);
        noteSequence.add(0+12*5);
        noteSequence.add(2+12*5);
        noteSequence.add(4+12*5);
        noteSequence.add(5+12*5);
        noteSequence.add(7+12*5);
        noteSequence.add(7+12*5);
        noteSequence.add(0+12*5);

        // noteDurations.add((double)(2.0/(double)(3.0)));
        // noteDurations.add(2.0);
        // noteDurations.add(1.0);
        // noteDurations.add(1.0);
        // noteDurations.add(2.0);
        // noteDurations.add((double)(2.0/(double)(3.0)));
        // noteDurations.add(1.0);
        // noteDurations.add(1.0);
        // noteDurations.add((double)(2.0/(double)(3.0)));
        // noteDurations.add(2.0);
        // noteDurations.add(1.0);
        // noteDurations.add(2.0);
        // noteDurations.add(2.0);
        // noteDurations.add(0.25);
        // noteDurations.add((double)(1.0/(double)(3.0)));
        // noteDurations.add(1.0);
        // noteDurations.add(2.0);
        // noteDurations.add(1.0);
        // noteDurations.add((double)(2.0/(double)(5.0)));
        // noteDurations.add((double)(1.0/(double)(3.0)));
        // noteDurations.add(1.0);
        // noteDurations.add(0.25);


        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(0.5);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(0.5);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(0.5);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(0.5);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(0.5);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(1.0);
        noteDurations.add(0.25);
        Judge judge = new Judge(noteSequence, noteDurations, velocity);
        Map<Integer, ArrayList<Integer>> chordMap = judge.judgement();
        MidiGenerator midiGenerator = new MidiGenerator(bpm, 56, noteSequence, bpm, 100, noteDurations, 3);
        midiGenerator.playRhythm();
        midiGenerator.saveToFile("output.mid");
        DefineStyle defineStyle = new DefineStyle(5,bpm,chordMap);
        defineStyle.midimerge();
    }
}
