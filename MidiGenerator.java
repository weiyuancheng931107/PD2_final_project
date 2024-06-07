package com.musicgenreclassifier;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class MidiGenerator {
    private final Sequence sequence;
    private final Track track;
    private final int bpm;
    private final int velocity;
    private final ArrayList<Integer> noteSequence;
    private final ArrayList<Integer> beatDurations;
    private final int channel;
    private final Sequencer sequencer;
    private int tick = 0;

    public MidiGenerator(int bpm, int program, ArrayList<Integer> noteSequence, int pitch, int velocity, ArrayList<Double> beats, int channel) throws InvalidMidiDataException, MidiUnavailableException {
        this.bpm = bpm;
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
                if (noteSequence != null) {
                    track.add(new MidiEvent(createNoteOnMessage(noteSequence.get(j)), tick));
                    track.add(new MidiEvent(createNoteOffMessage(noteSequence.get(j)), tick + beatDurations.get(j)));
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
