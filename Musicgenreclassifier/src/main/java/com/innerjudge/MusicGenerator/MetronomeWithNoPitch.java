package com.innerjudge.MusicGenerator;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MetronomeWithNoPitch {
    private final Sequence sequence;
    private final Track track;
    private final int velocity;
    private final ArrayList<ArrayList<Integer>> noteSequence;
    private final ArrayList<Integer> beatDurations;
    private final int channel;
    private final Sequencer sequencer;
    private int tick = 0;

    public MetronomeWithNoPitch(int bpm, int program, ArrayList<ArrayList<Integer>> noteSequence, int pitch, int velocity, ArrayList<Double> beats, int channel) throws InvalidMidiDataException, MidiUnavailableException {
        this.velocity = velocity;
        this.channel = channel;
        this.noteSequence = noteSequence;
        this.beatDurations = new ArrayList<>();
        for (double beat : beats) {
            this.beatDurations.add((int) (120 / beat));
        }
        this.sequence = new Sequence(Sequence.PPQ, bpm);
        this.track = sequence.createTrack();
        this.sequencer = MidiSystem.getSequencer();
        sequencer.open();
        track.add(createProgramChangeMessage(program));
    }

    public void playRhythm() throws InvalidMidiDataException {
        for (int j = 0; j < beatDurations.size(); j++) {
            for (int note : noteSequence.get(j)) {
                if (note != -1) {
                    track.add(new MidiEvent(createNoteOnMessage(note), tick));
                    track.add(new MidiEvent(createNoteOffMessage(note), tick + beatDurations.get(j)));
                }
            }
            tick += beatDurations.get(j);
        }
    }

    public void saveToFile(String filename) throws IOException {
        File outputFile = new File(filename + ".mid");
        MidiSystem.write(sequence, 1, outputFile);
        sequencer.close();
    }

    private ShortMessage createNoteOnMessage(int note) throws InvalidMidiDataException {
        ShortMessage noteOn = new ShortMessage();
        noteOn.setMessage(ShortMessage.NOTE_ON, channel, note, velocity);
        return noteOn;
    }

    private ShortMessage createNoteOffMessage(int note) throws InvalidMidiDataException {
        ShortMessage noteOff = new ShortMessage();
        noteOff.setMessage(ShortMessage.NOTE_OFF, channel, note, velocity);
        return noteOff;
    }

    private MidiEvent createProgramChangeMessage(int program) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.PROGRAM_CHANGE, 0, program, 0);
        return new MidiEvent(message, 0);
    }
}
