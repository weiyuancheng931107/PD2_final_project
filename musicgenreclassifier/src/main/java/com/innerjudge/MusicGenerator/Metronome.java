package com.innerjudge.MusicGenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.midi.*;
import com.innerjudge.MusicBasic.*;
public class Metronome {
    private Sequence sequence;
    private Track track;
    private int bpm;
    private static int program;
    private ArrayList<ArrayList<Integer>> noteName;
    private int pitch;
    private int velocity;
    private ArrayList<Integer> beat;
    private int channel;
    private int tick = 0;
    private Sequencer sequencer;

    public Metronome(int bpm, int program, ArrayList<ArrayList<Integer>> noteName, int pitch, int velocity, ArrayList<Double> beat, int channel) throws InvalidMidiDataException, MidiUnavailableException {
        this.bpm = bpm;
        Metronome.program = program;
        this.noteName = new ArrayList<>();
        for (int i = 0; i < noteName.size(); i++) {
            ArrayList<Integer> temp = new ArrayList<>(); 
            for (int j = 0; j < noteName.get(i).size(); j++) {
                Note note = new Note(noteName.get(i).get(j) % 12, pitch);
                temp.add(note.getNote());
            }
            this.noteName.add(temp); 
        }
        this.pitch = pitch;
        this.velocity = velocity;
        this.channel = channel;

        this.beat = new ArrayList<>();
        for (int i = 0; i < beat.size(); i++) {
            this.beat.add((int) (120 / beat.get(i)));
        }
        this.sequence = new Sequence(Sequence.PPQ, bpm);
        this.track = this.sequence.createTrack();
        sequencer = MidiSystem.getSequencer(); // Get sequencer
        sequencer.open(); // Open sequencer
    }

    public int getBpm() {
        return bpm;
    }

    public int getProgram() {
        return program;
    }

    public ArrayList<ArrayList<Integer>> getNoteName() {
        return noteName;
    }

    public int getPitch() {
        return pitch;
    }

    public int getVelocity() {
        return velocity;
    }

    public ArrayList<Integer> getBeat() {
        return this.beat;
    }

    public int getTick() {
        return tick;
    }

    public void rhythmChord() throws InvalidMidiDataException {
        // Add MIDI events: note ON
        for (int j = 0; j < beat.size(); j++) {
            for (int k = 0; k < noteName.get(j).size(); k++) {
                if (noteName.get(j).get(k) == -1) {
                    break;
                }
                ShortMessage noteOn = new ShortMessage();
                noteOn.setMessage(ShortMessage.NOTE_ON, channel, noteName.get(j).get(k), velocity);

                track.add(createProgramChangeMessage(tick));
                track.add(new MidiEvent(noteOn, tick));
            }
            // Add MIDI events: note OFF
            for (int k = 0; k < noteName.get(j).size(); k++) {
                if (noteName.get(j).get(k) == -1) {
                    break;
                }
                ShortMessage noteOff = new ShortMessage();
                noteOff.setMessage(ShortMessage.NOTE_OFF, channel, noteName.get(j).get(k), velocity);
                track.add(new MidiEvent(noteOff, tick + this.beat.get(j)));
            }
            tick += this.beat.get(j);
        }
    }

    public static MidiEvent createProgramChangeMessage(int tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.PROGRAM_CHANGE, 0, program, 0);
        return new MidiEvent(message, tick);
    }

    public void writeToFile(String filename) throws IOException {
        File outputFile = new File(filename + ".mid");
        MidiSystem.write(sequence, 1, outputFile);
        // close the sequencer when it's no longer needed
        sequencer.close();
    }

    public Track getTrack() {
        return track;
    }
}
