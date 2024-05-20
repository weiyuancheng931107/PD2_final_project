package com.musicgenreclassifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

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
    ArrayList<Double> beat;
    int meter;
    ArrayList<ArrayList<Integer>> chordList;

    public Judge(ArrayList<Integer> notename, ArrayList<Double> beat, int meter) {
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
        
        // ArrayList<ArrayList<Integer>> currentChordList = new ArrayList<>(originalChordList);
        ArrayList<ArrayList<Integer>> currentChordList = new ArrayList<>(originalChordList);
        // System.out.println(originalChordList);
        // System.out.println("-======================================");
        Iterator<ArrayList<Integer>> iterator = currentChordList.iterator();
        for (int i = 0; i < beat.size(); i++) {
            // ArrayList<ArrayList<Integer>> currentChordList = new ArrayList<>(originalChordList);
            // Iterator<ArrayList<Integer>> iterator = currentChordList.iterator();
            while (iterator.hasNext()) {
                ArrayList<Integer> currentChord = iterator.next();
                if (!currentChord.contains(notename.get(i))) {
                    iterator.remove();
                }
            }
            if (parameter <= 1) {
                // System.out.println(currentChordList);
                judgements.put(j + 1, currentChordList);
            }
            parameter += (double) (1.0 / beat.get(i));
            while (Math.abs(parameter - 1) <= 0.01 || parameter >= 1) {
                System.out.println("asdasd, " + i);
                if (parameter > 1) {
                    currentChordList = new ArrayList<>(originalChordList);
                    iterator = currentChordList.iterator();
                    while (iterator.hasNext()) {
                        ArrayList<Integer> currentChord = iterator.next();
                        if (!currentChord.contains(notename.get(i))) {
                            iterator.remove();
                        }
                    }
                } else {
                    currentChordList = new ArrayList<>(originalChordList);
                }
                parameter -= 1;
                j++;
                // judgements.put(j, currentChordList);
                
            }
            iterator = currentChordList.iterator();
        }

        return judgements;
    }

    public HashMap<Integer, ArrayList<Integer>> getRepeat(HashMap<Integer, ArrayList<ArrayList<Integer>>> judgements) {
        HashMap<Integer, ArrayList<Integer>> repeat = new HashMap<>();
        System.out.println(judgements);
        Random random = new Random();

        List<Integer> keys = new ArrayList<>(judgements.keySet());
        for (int i = 0; i < keys.size() - 1; i++) {
            int key1 = keys.get(i);
            ArrayList<ArrayList<Integer>> judgeList1 = judgements.get(key1);
            boolean found = false;

            for (int j = i + 1; j < keys.size(); j++) {
                int key2 = keys.get(j);
                ArrayList<ArrayList<Integer>> judgeList2 = judgements.get(key2);

                for (ArrayList<Integer> judge1 : judgeList1) {
                    if (found) break;
                    for (ArrayList<Integer> judge2 : judgeList2) {
                        if (judge1.equals(judge2)) {
                            repeat.put(key1, judge1);
                            repeat.put(key2, judge1);
                            found = true;
                            break;
                        }
                    }
                }
            }

            if (!found) {
                int randomIndex1 = random.nextInt(judgeList1.size());
                repeat.put(key1, judgeList1.get(randomIndex1));

                for (int j = 0; j < keys.size(); j++) {
                    if (i == j) continue;
                    int key2 = keys.get(j);
                    ArrayList<ArrayList<Integer>> judgeList2 = judgements.get(key2);
                    int randomIndex2 = random.nextInt(judgeList2.size());
                    repeat.put(key2, judgeList2.get(randomIndex2));
                }
            }
        }

        System.out.println(repeat);
        return repeat;
    }

    public void music() throws InvalidMidiDataException, MidiUnavailableException, IOException{
        HashMap<Integer, ArrayList<ArrayList<Integer>>> a = new HashMap<>();
        a = judgement();
        ArrayList<Double> b = new ArrayList<>();
        b.add(1.0);
        b.add(1.0);
        b.add(1.0);
        b.add(1.0);
        Metronome s = new Metronome(120, 29, a.get(1).get(3), 4, 100,b, 0);

        // s.rhythmchord();
        s.rhythmonenote();
        s.writeToFile("output");
    }

}
