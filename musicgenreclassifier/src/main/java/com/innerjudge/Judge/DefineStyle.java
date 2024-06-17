package com.innerjudge.Judge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import com.innerjudge.MusicGenerator.*;
import com.innerjudge.MusicStyle.Jazz;
import com.innerjudge.MusicStyle.Rock;
import com.innerjudge.MusicStyle.RnB;
import com.innerjudge.MusicStyle.Reggae;
import com.innerjudge.MusicStyle.BossaNova;
import com.innerjudge.MusicStyle.Funk;
import com.innerjudge.MusicStyle.Soul;
public class DefineStyle {
    private int style;
    private int bpm;
    private Map<Integer, ArrayList<Integer>> chordMap;
    private int barAmount;

    // Constructor to initialize the style, BPM, chord map, and bar amount
    public DefineStyle(int style, int bpm, Map<Integer, ArrayList<Integer>> chordMap, int barAmount) {
        this.style = style;
        this.bpm = bpm;
        this.chordMap = chordMap;
        this.barAmount = barAmount;
    }

    // Method to merge MIDI files based on the style
    public void midiMerge() throws InvalidMidiDataException, MidiUnavailableException, IOException {

        // Define MIDI file names and output file name
        String[] midiFiles;
        String outputFile = "merged.mid";

        switch (style) {
            case 0:
                // Rock style
                Rock rock = new Rock(bpm, 50, chordMap, barAmount);
                rock.piano();
                rock.drums();
                rock.bass();
                rock.guitar();
                midiFiles = new String[]{"piano.mid", "guitar.mid", "Bass.mid", "output.mid", "drums.mid"};
                break;

            case 1:
                // Jazz style
                Jazz jazz = new Jazz(bpm, 50, chordMap, barAmount);
                jazz.piano();
                jazz.drums();
                jazz.bass();
                midiFiles = new String[]{"piano.mid", "Bass.mid", "output.mid", "drums.mid"};
                break;

            case 2:
                // Bossa Nova style
                BossaNova bossaNova = new BossaNova(bpm, 100, chordMap, barAmount);
                bossaNova.piano();
                bossaNova.drums();
                bossaNova.bass();
                midiFiles = new String[]{"piano.mid", "Bass.mid", "output.mid", "drums.mid"};
                break;

            case 3:
                // RnB style
                RnB rnb = new RnB(bpm, 50, chordMap, barAmount);
                rnb.piano();
                rnb.drums();
                rnb.bass();
                rnb.guitar();
                midiFiles = new String[]{"piano.mid", "guitar.mid", "Bass.mid", "output.mid", "drums.mid"};
                break;

            case 4:
                // Soul style
                Soul soul = new Soul(bpm, 50, chordMap, barAmount);
                soul.piano();
                soul.drums();
                soul.bass();
                soul.guitar();
                midiFiles = new String[]{"piano.mid", "guitar.mid", "Bass.mid", "output.mid", "drums.mid"};
                break;

            case 5:
                // Funk style
                Funk funk = new Funk(bpm, 50, chordMap, barAmount);
                funk.piano();
                funk.drums();
                funk.bass();
                funk.guitar();
                midiFiles = new String[]{"piano.mid", "guitar.mid", "Bass.mid", "output.mid", "drums.mid"};
                break;

            case 6:
                // Reggae style
                Reggae reggae = new Reggae(bpm, 50, chordMap, barAmount);
                reggae.piano();
                reggae.drums();
                reggae.bass();
                reggae.guitar();
                midiFiles = new String[]{"piano.mid", "guitar.mid", "Bass.mid", "output.mid", "drums.mid"};
                break;

            default:
                throw new IllegalArgumentException("Unknown music style: " + style);
        }

        // Merge the MIDI files
        MidiMerger.mergeMidiFiles(midiFiles, outputFile);
    }
}
