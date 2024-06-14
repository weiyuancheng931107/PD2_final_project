package com.musicgenreclassifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

/**
 * Interface defining the methods required for a BossaNova rhythm section.
 */
interface InnerBossaNova {
    void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException;
    void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException;
    void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException;
}

/**
 * Class representing the Bossa Nova music genre, implementing the InnerBossaNova interface.
 */
class BossaNova implements InnerBossaNova {

    // Constants for MIDI drum instrument codes
    public static final int KICK_DRUM = 36; // Bass drum
    public static final int RIDE = 42;
    public static final int CRASH = 51;

    // Chord hash map for storing chord progressions
    Map<Integer, ArrayList<Integer>> chordHashMap;

    // BPM (beats per minute) for the rhythm
    private int bpm;

    // Velocity (volume) for the notes
    private int velocity;

    // Placeholder for pauses in the rhythm
    public ArrayList<Integer> pause;

    // Total number of bars in the rhythm
    private int barAmount;

    // Additional MIDI drum instrument codes
    public static final int ACOUSTIC_BASS_DRUM = 35;
    public static final int BASS_DRUM = 36;
    public static final int SIDE_STICK = 37;
    public static final int ACOUSTIC_SNARE = 38;
    public static final int HAND_CLAP = 39;
    public static final int ELECTRIC_SNARE = 40;
    public static final int LOW_FLOOR_TOM = 41;
    public static final int CLOSED_HI_HAT = 42;
    public static final int HIGH_FLOOR_TOM = 43;
    public static final int PEDAL_HI_HAT = 42;
    public static final int LOW_TOM = 45;
    public static final int OPEN_HI_HAT = 46;
    public static final int LOW_MID_TOM = 47;
    public static final int HI_MID_TOM = 48;
    public static final int CRASH_CYMBAL_1 = 49;
    public static final int HIGH_TOM = 50;
    public static final int RIDE_CYMBAL_1 = 51;
    public static final int CHINESE_CYMBAL = 52;
    public static final int RIDE_BELL = 53;
    public static final int TAMBOURINE = 54;
    public static final int SPLASH_CYMBAL = 55;
    public static final int COWBELL = 56;
    public static final int CRASH_CYMBAL_2 = 57;
    public static final int VIBRASLAP = 58;
    public static final int RIDE_CYMBAL_2 = 59;
    public static final int HI_BONGO = 60;
    public static final int LOW_BONGO = 61;
    public static final int MUTE_HI_CONGA = 62;
    public static final int OPEN_HI_CONGA = 63;
    public static final int LOW_CONGA = 64;
    public static final int HIGH_TIMBALE = 65;
    public static final int LOW_TIMBALE = 66;
    public static final int HIGH_AGOGO = 67;
    public static final int LOW_AGOGO = 68;
    public static final int CABASA = 69;
    public static final int MARACAS = 70;
    public static final int SHORT_WHISTLE = 71;
    public static final int LONG_WHISTLE = 72;
    public static final int SHORT_GUIRO = 73;
    public static final int LONG_GUIRO = 74;
    public static final int CLAVES = 75;
    public static final int HI_WOOD_BLOCK = 76;
    public static final int LOW_WOOD_BLOCK = 77;
    public static final int MUTE_CUICA = 78;
    public static final int OPEN_CUICA = 79;
    public static final int MUTE_TRIANGLE = 80;
    public static final int OPEN_TRIANGLE = 81;

    /**
     * Constructor for the BossaNova class.
     * @param bpm - Beats per minute for the rhythm.
     * @param velocity - Volume for the notes.
     * @param chordHashMap - Chord progressions for the melody.
     * @param barAmount - Number of bars in the rhythm.
     * @throws InvalidMidiDataException
     * @throws MidiUnavailableException
     */
    public BossaNova(int bpm, int velocity, Map<Integer, ArrayList<Integer>> chordHashMap, int barAmount) throws InvalidMidiDataException, MidiUnavailableException {
        this.bpm = bpm;
        this.velocity = velocity;
        this.chordHashMap = chordHashMap;
        this.pause = new ArrayList<>();
        pause.add(-1);
        this.barAmount = barAmount * 4 + 1;
    }

    /**
     * Method to generate the drum track for the Bossa Nova rhythm.
     * @throws InvalidMidiDataException
     * @throws MidiUnavailableException
     * @throws IOException
     */
    @Override
    public void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException {

        // Initialize drum chord and beat lists
        ArrayList<Integer> drumChord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2, ACOUSTIC_BASS_DRUM));
        ArrayList<ArrayList<Integer>> drumChordFinal = new ArrayList<>();
        ArrayList<Double> drumsBeat = new ArrayList<>();
        
        // Populate the drum chord and beat lists for each bar
        for (int j = 0; j < (barAmount - 1) / barAmount; j++) {
            for (int i = 0; i < barAmount; i++) {
                drumsBeat.add(2.0);
            }

            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(ACOUSTIC_BASS_DRUM, PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Collections.singletonList(PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_SNARE));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_SNARE));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Collections.singletonList(PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);

            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_SNARE, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Collections.singletonList(PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Collections.singletonList(PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_SNARE, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Collections.singletonList(PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_SNARE));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);

            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(ACOUSTIC_BASS_DRUM, PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Collections.singletonList(PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_SNARE));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_SNARE));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Collections.singletonList(PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);

            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_SNARE, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Collections.singletonList(PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Collections.singletonList(PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_SNARE, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Collections.singletonList(PEDAL_HI_HAT));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_SNARE));
            drumChordFinal.add(drumChord);
            drumChord = new ArrayList<>(Arrays.asList(PEDAL_HI_HAT, ACOUSTIC_BASS_DRUM));
            drumChordFinal.add(drumChord);
        }

        // Create a MetronomeWithNoPitch instance to play the drum track
        MetronomeWithNoPitch drums = new MetronomeWithNoPitch(bpm, 59, drumChordFinal, 2, velocity, drumsBeat, 9);
        drums.playRhythm();
        drums.saveToFile("drums");
    }

    /**
     * Method to generate the piano track for the Bossa Nova rhythm.
     * @throws InvalidMidiDataException
     * @throws MidiUnavailableException
     * @throws IOException
     */
    @Override
    public void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException {
        int bars = 1;
        ArrayList<Integer> pianoChordTemp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pianoChord = new ArrayList<>();
        ArrayList<Double> pianoBeat = new ArrayList<>();
        ArrayList<Integer> beatCount = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pianoChordFinal = new ArrayList<>();
        ArrayList<Double> pianoBeatFinal = new ArrayList<>();

        // Filter and group chords
        this.chordHashMap = groupAndFilter(this.chordHashMap);
        int lastBeat = 0;
        for (Integer key : chordHashMap.keySet()) {
            beatCount.add(key);
            pianoChordTemp = chordHashMap.get(key);
            pianoChord.add(pianoChordTemp);
            lastBeat = key;
        }

        // Calculate beats for each chord
        for (int i = 0; i < beatCount.size() - 1; i++) {
            pianoBeat.add(1 / ((double) (beatCount.get(i + 1) - beatCount.get(i))));
        }

        // Handle the last beat
        if (lastBeat % 4 != 0) {
            pianoBeat.add(1 / ((double) (barAmount - lastBeat)));
        } else {
            pianoBeat.add(1.0);
        }

        // Create final piano beat and chord sequences
        for (int i = 0; i < pianoBeat.size(); i++) {
            if (pianoBeat.get(i) == 0.25 && bars % 8 == 1) {
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(2.0);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                bars += 4;
            } else if (pianoBeat.get(i) == 0.25 & bars % 8 == 5) {
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add((double) (2.0 / (double) (3.0)));
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                bars += 4;
            } else if (pianoBeat.get(i) == 1.0 && bars % 8 == 1) {
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 1;
            } else if (pianoBeat.get(i) == 1.0 && bars % 8 == 2) {
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 1;
            } else if (pianoBeat.get(i) == 1.0 && bars % 8 == 3) {
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 1;
            } else if (pianoBeat.get(i) == 1.0 && bars % 8 == 4) {
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(2.0);
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 1;
            } else if (pianoBeat.get(i) == 1.0 && bars % 8 == 5) {
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(2.0);
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 1;
            } else if (pianoBeat.get(i) == 1.0 && bars % 8 == 6) {
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 1;
            } else if (pianoBeat.get(i) == 1.0 && bars % 8 == 7) {
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 1;
            } else if (pianoBeat.get(i) == 1.0 && bars % 8 == 0) {
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 1;
            } else if (pianoBeat.get(i) == 0.5 && bars % 8 == 1) {
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                bars += 2;
            } else if (pianoBeat.get(i) == 0.5 && bars % 8 == 2) {
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(2.0);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 2;
            } else if (pianoBeat.get(i) == 0.5 && bars % 8 == 3) {
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(2.0);
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                bars += 2;
            } else if (pianoBeat.get(i) == 0.5 && bars % 8 == 5) {
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add((double) (2.0 / (double) (3.0)));
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 2;
            } else if (pianoBeat.get(i) == 0.5 && bars % 8 == 6) {
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                bars += 2;
            } else if (pianoBeat.get(i) == 0.5 && bars % 8 == 7) {
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                bars += 2;
            } else if ((pianoBeat.get(i)) - (double) (1.0 / (double) (3.0)) == 0 && bars % 8 == 1) {
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(2.0);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 3;
            } else if ((pianoBeat.get(i)) - (double) (1.0 / (double) (3.0)) == 0 && bars % 8 == 2) {
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(2.0);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                bars += 3;
            } else if ((pianoBeat.get(i)) - (double) (1.0 / (double) (3.0)) == 0 && bars % 8 == 5) {
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add((double) (2.0 / (double) (3.0)));
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                bars += 3;
            } else if ((pianoBeat.get(i)) - (double) (1.0 / (double) (3.0)) == 0 && bars % 8 == 6) {
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add((double) (2.0 / (double) (3.0)));
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pause);
                pianoChordFinal.add(pianoChord.get(i));
                bars += 3;
            }
        }

        // Print chord hash map and piano beat for debugging
        System.out.println(this.chordHashMap);
        System.out.println(pianoBeat);

        // Create a Metronome instance to play the piano track
        Metronome piano = new Metronome(bpm, 4, pianoChordFinal, 3, velocity, pianoBeatFinal, 1);
        piano.rhythmChord();
        piano.writeToFile("piano");
    }

    /**
     * Method to generate the bass track for the Bossa Nova rhythm.
     * @throws InvalidMidiDataException
     * @throws MidiUnavailableException
     * @throws IOException
     */
    @Override
    public void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException {
        Random random = new Random();
        ArrayList<Integer> bassChordTemp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bassChord = new ArrayList<>();
        ArrayList<Double> bassBeat = new ArrayList<>();
        ArrayList<Integer> beatCount = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bassChordFinal = new ArrayList<>();
        ArrayList<Double> bassBeatFinal = new ArrayList<>();

        // Filter and group chords
        this.chordHashMap = groupAndFilter(this.chordHashMap);
        int a = 0;
        int lastBeat = 0;
        for (Integer key : chordHashMap.keySet()) {
            beatCount.add(key);
            bassChordTemp = chordHashMap.get(key);
            bassChord.add(bassChordTemp);
            lastBeat = key;
        }

        // Calculate beats for each chord
        for (int i = 0; i < beatCount.size() - 1; i++) {
            bassBeat.add(1 / ((double) (beatCount.get(i + 1) - beatCount.get(i))));
        }
        if (lastBeat % 4 != 0) {
            bassBeat.add(1 / ((double) (barAmount - lastBeat)));
        } else {
            bassBeat.add(1.0);
        }

        // Create final bass beat and chord sequences
        for (int i = 0; i < bassBeat.size(); i++) {
            if (bassBeat.get(i) == 0.25 && a == 0) {
                bassBeatFinal.add((double) (2.0 / (double) (3.0)));
                bassBeatFinal.add(2.0);
                bassBeatFinal.add((double) (2.0 / (double) (3.0)));
                bassBeatFinal.add(2.0);
                sortAscending(bassChord);
                ArrayList<Integer> bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(0));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(0));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(2));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(2));
                bassChordFinal.add(bassLine);
                a = 1;
            } else if (bassBeat.get(i) == 0.25 && a == 1) {
                bassBeatFinal.add((double) (2.0 / (double) (3.0)));
                bassBeatFinal.add(2.0);
                bassBeatFinal.add((double) (2.0 / (double) (3.0)));
                bassBeatFinal.add(2.0);
                sortDescending(bassChord);
                ArrayList<Integer> bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(0));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(0));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(2));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(2));
                bassChordFinal.add(bassLine);
                a = 0;
            } else if (bassBeat.get(i) == 1.0) {
                ArrayList<Integer> bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(rand(0, 2, random)));
                bassChordFinal.add(bassLine);
                bassBeatFinal.add(1.0);
            } else if (bassBeat.get(i) == 0.5) {
                bassBeatFinal.add((double) (2.0 / (double) (3.0)));
                bassBeatFinal.add(2.0);
                ArrayList<Integer> bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(0));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(0));
                bassChordFinal.add(bassLine);
            } else {
                bassBeatFinal.add((double) (2.0 / (double) (3.0)));
                bassBeatFinal.add(2.0);
                bassBeatFinal.add(1.0);
                ArrayList<Integer> bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(rand(0, 2, random)));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(rand(0, 2, random)));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>();
                bassLine.add(bassChord.get(i).get(rand(0, 2, random)));
                bassChordFinal.add(bassLine);
            }
        }

        // Create a Metronome instance to play the bass track
        Metronome bass = new Metronome(bpm, 46, bassChordFinal, 2, velocity, bassBeatFinal, 0);
        bass.rhythmChord();
        bass.writeToFile("bass");
    }

    /**
     * Group and filter the chords based on beat groups.
     * @param input - Input chord hash map.
     * @return Filtered and grouped chord hash map.
     */
    public static Map<Integer, ArrayList<Integer>> groupAndFilter(Map<Integer, ArrayList<Integer>> input) {
        Map<Integer, ArrayList<Integer>> result = new HashMap<>();
        TreeMap<Integer, List<Integer>> groupedMap = new TreeMap<>();
        for (Integer key : input.keySet()) {
            int groupKey = (key - 1) / 4;
            groupedMap.putIfAbsent(groupKey, new ArrayList<Integer>());
            groupedMap.get(groupKey).add(key);
        }

        for (List<Integer> groupKeys : groupedMap.values()) {
            Set<ArrayList<Integer>> seenGroups = new HashSet<>();
            for (Integer key : groupKeys) {
                ArrayList<Integer> group = input.get(key);
                if (!seenGroups.contains(group)) {
                    seenGroups.add(group);
                    result.put(key, group);
                }
            }
        }
        return result;
    }

    /**
     * Generate a random integer within a specified range.
     * @param min - Minimum value.
     * @param max - Maximum value.
     * @param random - Random instance.
     * @return Random integer within the range.
     */
    public static int rand(int min, int max, Random random) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Sort the inner lists of a list of lists in ascending order.
     * @param listOfLists - List of lists to be sorted.
     */
    public static void sortAscending(ArrayList<ArrayList<Integer>> listOfLists) {
        for (ArrayList<Integer> innerList : listOfLists) {
            Collections.sort(innerList);
        }
    }

    /**
     * Sort the inner lists of a list of lists in descending order.
     * @param listOfLists - List of lists to be sorted.
     */
    public static void sortDescending(ArrayList<ArrayList<Integer>> listOfLists) {
        for (ArrayList<Integer> innerList : listOfLists) {
            Collections.sort(innerList, Collections.reverseOrder());
        }
    }
}
