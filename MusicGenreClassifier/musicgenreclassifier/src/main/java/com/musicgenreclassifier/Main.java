package com.musicgenreclassifier;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Track;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidMidiDataException, MidiUnavailableException, IOException {
        int bpm = 120;
        int velocity = 100;
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Double> b = new ArrayList<>();

        // a.add(1);
        a.add(13);
        a.add(6);
        a.add(6);
        a.add(6);
        a.add(10);
        a.add(3);
        a.add(10);
        // b.add(32.0);
        b.add(0.5);
        b.add(1.0);
        b.add(1.0);
        b.add(1.0);
        b.add(0.25);
        b.add(0.25);
        b.add(0.25);
        // {1=[2, 6, 9, 0], 3=[0, 4, 7, 10], 5=[0, 4, 7, 11], 6=[0, 4, 7, 11], 9=[11, 3, 6, 10], 13=[11, 3, 6, 10]}
        Judge judge = new Judge(a , b, velocity);
        Map<Integer,ArrayList<Integer>> finall = new HashMap<>();
        System.out.println("asdad");
        finall = judge.judgement();
        // System.out.println(finall);
        // judge.getRepeat(finall);
        Jazz jazz = new Jazz(bpm, velocity, finall);
        jazz.Piano();
        jazz.Drums();
        jazz.Bass();
    }
    
}
