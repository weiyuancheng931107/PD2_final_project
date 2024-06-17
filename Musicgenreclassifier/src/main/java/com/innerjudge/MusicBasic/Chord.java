package com.innerjudge.MusicBasic;

import java.util.ArrayList;

public class Chord {
    private final int rootNote;
    private final int pitch;

    public Chord(int rootNote, int pitch) {
        this.rootNote = rootNote;
        this.pitch = pitch;
    }

    public ArrayList<Integer> getMaj7() {
        return createChord(rootNote, rootNote + 4, rootNote + 7, rootNote + 11);
    }

    public ArrayList<Integer> get7() {
        return createChord(rootNote, rootNote + 4, rootNote + 7, rootNote + 10);
    }

    public ArrayList<Integer> getMin7() {
        return createChord(rootNote, rootNote + 3, rootNote + 7, rootNote + 10);
    }

    public ArrayList<Integer> getDim7() {
        return createChord(rootNote, rootNote + 3, rootNote + 6, rootNote + 10);
    }

    public ArrayList<Integer> getMaj() {
        return createChord(rootNote, rootNote + 4, rootNote + 7);
    }

    public ArrayList<Integer> getMin() {
        return createChord(rootNote, rootNote + 3, rootNote + 7);
    }

    public ArrayList<Integer> getDim() {
        return createChord(rootNote, rootNote + 3, rootNote + 6);
    }

    private ArrayList<Integer> createChord(int... notes) {
        ArrayList<Integer> chord = new ArrayList<>();
        for (int note : notes) {
            chord.add(new Note(note % 12, pitch).getNote());
        }
        return chord;
    }
}
