package com.musicgenreclassifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Judge {
    Chord C;
    Chord Csharp;
    Chord D;
    Chord Dsharp;
    Chord E;
    Chord F;
    Chord Fsharp;
    Chord G;
    Chord Gsharp;
    Chord A;
    Chord Asharp;
    Chord B;
    ArrayList<Integer> notename;
    ArrayList<Integer> beat;
    int meter;
    ArrayList<ArrayList<Integer>> chordList;

    public Judge(ArrayList<Integer> notename, ArrayList<Integer> beat, int meter) {
        this.C = new Chord(0, -1);
        this.Csharp = new Chord(1, -1);
        this.D = new Chord(2, -1);
        this.Dsharp = new Chord(3, -1);
        this.E = new Chord(4, -1);
        this.F = new Chord(5, -1);
        this.Fsharp = new Chord(6, -1);
        this.G = new Chord(7, -1);
        this.Gsharp = new Chord(8, -1);
        this.A = new Chord(9, -1);
        this.Asharp = new Chord(10, -1);
        this.B = new Chord(11, -1);
        this.chordList = new ArrayList<>();
        this.notename = notename;
        this.beat = beat;
        this.meter = meter;
    }

    public ArrayList<ArrayList<Integer>> getChord() {
        return chordList;
    }

    public HashMap<Integer, ArrayList<ArrayList<Integer>>> judgement() {
        ArrayList<Chord> chords = new ArrayList<>();
        HashMap<Integer, ArrayList<ArrayList<Integer>>> judgements = new HashMap<>();
        double parameter = 0;
        int j = 0;

        chords.add(C);
        chords.add(Csharp);
        chords.add(D);
        chords.add(Dsharp);
        chords.add(E);
        chords.add(F);
        chords.add(Fsharp);
        chords.add(G);
        chords.add(Gsharp);
        chords.add(A);
        chords.add(Asharp);
        chords.add(B);

        for (int i = 0; i < chords.size(); i++) {
            chordList.add(chords.get(i).get_7());
            chordList.add(chords.get(i).get_O());
            chordList.add(chords.get(i).get_O7());
            chordList.add(chords.get(i).get_maj());
            chordList.add(chords.get(i).get_maj7());
            chordList.add(chords.get(i).get_min());
            chordList.add(chords.get(i).get_min7());
        }

        ArrayList<ArrayList<Integer>> originalChordList = new ArrayList<>(chordList);

        for (int i = 0; i < beat.size(); i++) {
            ArrayList<ArrayList<Integer>> currentChordList = new ArrayList<>(originalChordList);
            Iterator<ArrayList<Integer>> iterator = currentChordList.iterator();
            while (iterator.hasNext()) {
                ArrayList<Integer> currentChord = iterator.next();
                if (!currentChord.contains(notename.get(i))) {
                    iterator.remove();
                }
            }
            if (parameter <= 1) {
                judgements.put(j + 1, currentChordList);
            }
            parameter += (double) (1.0 / beat.get(i));
            while (parameter >= 1) {
                parameter -= 1;
                j++;
            }
        }

        return judgements;
    }
}
