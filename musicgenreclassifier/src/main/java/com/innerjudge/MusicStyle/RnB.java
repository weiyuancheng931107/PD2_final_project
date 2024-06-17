package com.innerjudge.MusicStyle;

import java.io.IOException;
import java.util.*;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import com.innerjudge.MusicGenerator.*;
/**
 * Interface defining the methods required for an RnB rhythm section.
 */
interface InnerRnB {
    void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException;
    void guitar() throws InvalidMidiDataException, MidiUnavailableException, IOException;
    void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException;
    void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException;
}

/**
 * Class representing the RnB music genre, implementing the InnerRnB interface.
 */
public class RnB implements InnerRnB {


    // Chord hash map for storing chord progressions
    private Map<Integer, ArrayList<Integer>> chordHashMap;

    // BPM (beats per minute) for the rhythm
    private int bpm;

    // Velocity (volume) for the notes
    private int velocity;

    // Placeholder for pauses in the rhythm
    private ArrayList<Integer> pause;

    // Total number of bars in the rhythm
    private int barAmount;

    /**
     * Constructor for the RnB class.
     * @param bpm - Beats per minute for the rhythm.
     * @param velocity - Volume for the notes.
     * @param chordHashMap - Chord progressions for the melody.
     * @param barAmount - Number of bars in the rhythm.
     * @throws InvalidMidiDataException
     * @throws MidiUnavailableException
     */
    public RnB(int bpm, int velocity, Map<Integer, ArrayList<Integer>> chordHashMap, int barAmount) throws InvalidMidiDataException, MidiUnavailableException {
        this.bpm = bpm;
        this.velocity = 50;
        this.chordHashMap = chordHashMap;
        this.pause = new ArrayList<>(Collections.singletonList(-1));
        this.barAmount = barAmount * 4 + 1;
        System.out.println(barAmount);
    }

    /**
     * Method to generate the drum track for the RnB rhythm.
     * @throws InvalidMidiDataException
     * @throws MidiUnavailableException
     * @throws IOException
     */
    @Override
    public void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException {
        ArrayList<Integer> drumChord = new ArrayList<>();
        ArrayList<ArrayList<Integer>> drumChordFinal = new ArrayList<>();
        ArrayList<Double> drumsBeat = new ArrayList<>();
        // Populate the drum chord and beat lists for each bar
        for (int j = 0; j < (barAmount - 1)/16; j++) {
            for (int i = 0; i < 64; i++) {
                drumsBeat.add(4.0);
            }

            // Populate drum chords for each beat
            for(int k = 0;k<8;k++){
                drumChord = new ArrayList<>(Arrays.asList(Drumsound.ACOUSTIC_BASS_DRUM, Drumsound.PEDAL_HI_HAT));
                drumChordFinal.add(drumChord);
                drumChord = new ArrayList<>(Arrays.asList(Drumsound.PEDAL_HI_HAT));
                drumChordFinal.add(drumChord);
                drumChord = new ArrayList<>(Arrays.asList(Drumsound.PEDAL_HI_HAT));
                drumChordFinal.add(drumChord);
                drumChord = new ArrayList<>(Arrays.asList(Drumsound.PEDAL_HI_HAT));
                drumChordFinal.add(drumChord);
                drumChord = new ArrayList<>(Arrays.asList(Drumsound.PEDAL_HI_HAT, Drumsound.ACOUSTIC_SNARE));
                drumChordFinal.add(drumChord);
                drumChord = new ArrayList<>(Arrays.asList(Drumsound.PEDAL_HI_HAT));
                drumChordFinal.add(drumChord);
                drumChord = new ArrayList<>(Arrays.asList(Drumsound.PEDAL_HI_HAT, Drumsound.ACOUSTIC_BASS_DRUM));
                drumChordFinal.add(drumChord);
                drumChord = new ArrayList<>(Arrays.asList(Drumsound.PEDAL_HI_HAT));
                drumChordFinal.add(drumChord);
                // Repeat similar patterns for the rest of the drum beats
            }
        }
        // Create a MetronomeWithNoPitch instance to play the drum track
        MetronomeWithNoPitch drums = new MetronomeWithNoPitch(bpm, 59, drumChordFinal, 2, velocity, drumsBeat, 9);
        drums.playRhythm();
        drums.saveToFile("drums");
    }

    /**
     * Method to generate the guitar track for the RnB rhythm.
     * @throws InvalidMidiDataException
     * @throws MidiUnavailableException
     * @throws IOException
     */
    @Override
    public void guitar() throws InvalidMidiDataException, MidiUnavailableException, IOException {
        ArrayList<Integer> guitarChordTemp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> guitarChord = new ArrayList<>();
        ArrayList<Double> guitarBeat = new ArrayList<>();
        ArrayList<Integer> beatCount = new ArrayList<>();
        ArrayList<ArrayList<Integer>> guitarChordFinal = new ArrayList<>();
        ArrayList<Double> guitarBeatFinal = new ArrayList<>();
        this.chordHashMap = groupAndFilter(this.chordHashMap);
        int lastBeat = 0;

        // Populate guitar chords and beats based on chordHashMap
        for (Integer key : chordHashMap.keySet()) {
            beatCount.add(key);
            guitarChordTemp = chordHashMap.get(key);
            guitarChord.add(guitarChordTemp);
            lastBeat = key;
        }

        // Calculate beats for each chord
        for (int i = 0; i < beatCount.size() - 1; i++) {
            guitarBeat.add(1 / ((double) (beatCount.get(i + 1) - beatCount.get(i))));
        }
        if (lastBeat % 4 != 0) {
            guitarBeat.add(1 / ((double) (barAmount - lastBeat)));
        } else {
            guitarBeat.add(1.0);
        }

        // Create final guitar beat and chord sequences
        for (int i = 0; i < guitarBeat.size(); i++) {
            if (guitarBeat.get(i) == 0.25) {
                guitarBeatFinal.add(1.0);
                guitarBeatFinal.add(1.0);
                guitarBeatFinal.add(4.0);
                guitarBeatFinal.add(2.0);
                guitarBeatFinal.add(2.0);
                guitarBeatFinal.add((double) (4.0 / (double) 3.0));

                guitarChordFinal.add(guitarChord.get(i));
                guitarChordFinal.add(pause);
                guitarChordFinal.add(guitarChord.get(i));
                guitarChordFinal.add(guitarChord.get(i));
                guitarChordFinal.add(guitarChord.get(i));
                guitarChordFinal.add(guitarChord.get(i));
            } else if (guitarBeat.get(i) == 1.0) {
                guitarBeatFinal.add(1.0);
                guitarChordFinal.add(guitarChord.get(i));
            } else if (guitarBeat.get(i) == 0.5) {
                guitarBeatFinal.add(1.0);
                guitarBeatFinal.add(1.0);
                guitarChordFinal.add(guitarChord.get(i));
                guitarChordFinal.add(pause);
            } else {
                guitarBeatFinal.add(1.0);
                guitarBeatFinal.add(2.0);
                guitarBeatFinal.add(2.0);
                guitarBeatFinal.add(2.0);
                guitarBeatFinal.add(2.0);

                guitarChordFinal.add(guitarChord.get(i));
                guitarChordFinal.add(pause);
                guitarChordFinal.add(guitarChord.get(i));
                guitarChordFinal.add(pause);
                guitarChordFinal.add(guitarChord.get(i));
            }
        }

        // Create a Metronome instance to play the guitar track
        Metronome guitar = new Metronome(bpm, 25, guitarChordFinal, 4, velocity, guitarBeatFinal, 0);
        guitar.rhythmChord();
        guitar.writeToFile("guitar");
    }

    /**
     * Method to generate the bass track for the RnB rhythm.
     * @throws InvalidMidiDataException
     * @throws MidiUnavailableException
     * @throws IOException
     */
    @Override
    public void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException {
        ArrayList<Integer> bassChordTemp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bassChord = new ArrayList<>();
        ArrayList<Double> bassBeat = new ArrayList<>();
        ArrayList<Integer> beatCount = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bassChordFinal = new ArrayList<>();
        ArrayList<Double> bassBeatFinal = new ArrayList<>();
        this.chordHashMap = groupAndFilter(this.chordHashMap);
        int lastBeat = 0;

        // Populate bass chords and beats based on chordHashMap
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
            if (bassBeat.get(i) == 0.25) {
                bassBeatFinal.add(0.5);
                bassBeatFinal.add(1.0);
                bassBeatFinal.add(1.0);
                ArrayList<Integer> bassLine = new ArrayList<>(Collections.singletonList(bassChord.get(i).get(0)));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>(Collections.singletonList(bassChord.get(i).get(0)));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>(Collections.singletonList(bassChord.get(i).get(0)));
                bassChordFinal.add(bassLine);
            } else if (bassBeat.get(i) == 1.0) {
                ArrayList<Integer> bassLine = new ArrayList<>(Collections.singletonList(bassChord.get(i).get(0)));
                bassChordFinal.add(bassLine);
                bassBeatFinal.add(1.0);
            } else if (bassBeat.get(i) == 0.5) {
                bassBeatFinal.add((double) (2.0 / (double) (3.0)));
                bassBeatFinal.add(2.0);
                ArrayList<Integer> bassLine = new ArrayList<>(Collections.singletonList(bassChord.get(i).get(0)));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>(Collections.singletonList(bassChord.get(i).get(0)));
                bassChordFinal.add(bassLine);
            } else {
                bassBeatFinal.add((double) (2.0 / (double) (3.0)));
                bassBeatFinal.add(2.0);
                bassBeatFinal.add(1.0);
                ArrayList<Integer> bassLine = new ArrayList<>(Collections.singletonList(bassChord.get(i).get(0)));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>(Collections.singletonList(bassChord.get(i).get(0)));
                bassChordFinal.add(bassLine);
                bassLine = new ArrayList<>(Collections.singletonList(bassChord.get(i).get(0)));
                bassChordFinal.add(bassLine);
            }
        }

        // Create a Metronome instance to play the bass track
        Metronome bass = new Metronome(bpm, 46, bassChordFinal, 2, velocity, bassBeatFinal, 0);
        bass.rhythmChord();
        bass.writeToFile("bass");
    }

    /**
     * Method to generate the piano track for the RnB rhythm.
     * @throws InvalidMidiDataException
     * @throws MidiUnavailableException
     * @throws IOException
     */
    @Override
    public void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException {
        ArrayList<Integer> pianoChordTemp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pianoChord = new ArrayList<>();
        ArrayList<Double> pianoBeat = new ArrayList<>();
        ArrayList<Integer> beatCount = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pianoChordFinal = new ArrayList<>();
        ArrayList<Double> pianoBeatFinal = new ArrayList<>();
        this.chordHashMap = groupAndFilter(this.chordHashMap);
        int lastBeat = 0;

        // Populate piano chords and beats based on chordHashMap
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
        if (lastBeat % 4 != 0) {
            pianoBeat.add(1 / ((double) (barAmount - lastBeat)));
        } else {
            pianoBeat.add(1.0);
        }

        // Create final piano beat and chord sequences
        for (int i = 0; i < pianoBeat.size(); i++) {
            if (pianoBeat.get(i) == 0.25) {
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(2.0);
                pianoBeatFinal.add(0.5);

                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pianoChord.get(i));

            } else if (pianoBeat.get(i) == 1.0) {
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
            } else if (pianoBeat.get(i) == 0.5) {
                pianoBeatFinal.add(1.0);
                pianoBeatFinal.add(1.0);
                pianoChordFinal.add(pianoChord.get(i));
                pianoChordFinal.add(pause);
            } else {
                pianoBeatFinal.add((double) (1.0 / (double) 3.0));
                pianoChordFinal.add(pianoChord.get(i));
            }
        }

        // Print chord hash map and piano beat for debugging
        System.out.println(this.chordHashMap);
        System.out.println(pianoBeat);

        // Create a Metronome instance to play the piano track
        Metronome piano = new Metronome(bpm, 17, pianoChordFinal, 4, velocity, pianoBeatFinal, 0);
        piano.rhythmChord();
        piano.writeToFile("piano");
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
