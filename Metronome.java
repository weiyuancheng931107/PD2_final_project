package com.musicgenreclassifier;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Metronome {
    private Sequence sequence;
    private Track track;
    int bpm;
    static int program;
    ArrayList<ArrayList<Integer>> notename;
    int pitch;
    int velocity;
    ArrayList<Integer> beat;
    int channel;
    int tick = 0;
    private Sequencer sequencer;

    public Metronome(int bpm, int program, ArrayList<ArrayList<Integer>> notename, int pitch, int velocity, ArrayList<Double> beat, int channel) throws InvalidMidiDataException, MidiUnavailableException {
        this.bpm = bpm;
        Metronome.program = program;
        this.notename = new ArrayList<>();
        for (int i = 0; i < notename.size(); i++) {
            ArrayList<Integer> temp = new ArrayList<>(); // 每次迭代都创建一个新的temp列表
            for (int j = 0; j < notename.get(i).size(); j++) {
                Note note = new Note(notename.get(i).get(j) % 12, pitch);
                temp.add(note.getNote());
            }
            this.notename.add(temp); // 将每个新创建的temp列表添加到notename中
        }
        this.pitch = pitch;
        this.velocity = velocity;
        this.channel = channel;
        this.beat = new ArrayList<>();
        for (int i = 0; i < beat.size(); i++) {
            this.beat.add((int) (120 / beat.get(i)));
        }
        this.sequence = new Sequence(Sequence.PPQ, bpm);
        this.track = (this.sequence).createTrack();
        sequencer = MidiSystem.getSequencer(); // Get sequencer
        sequencer.open(); // Open sequencer
        
        // Add the Program Change message to set the instrument
        track.add(createProgramChangeMessage());
    }

    public int getBpm() {
        return bpm;
    }

    public int getProgram() {
        return program;
    }

    public ArrayList<ArrayList<Integer>> getNotename() {
        return notename;
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

    public void rhythmchord() throws InvalidMidiDataException {
        // Add MIDI events: note ON and OFF
        for (int j = 0; j < beat.size(); j++) {
            for(int k = 0; k < notename.get(j).size(); k++){
                if (notename.get(j).get(k) == -1) {
                    break;
                }
                // Note ON
                ShortMessage noteOn = new ShortMessage();
                noteOn.setMessage(ShortMessage.NOTE_ON, channel, notename.get(j).get(k), velocity);
                track.add(new MidiEvent(noteOn, tick));
                
                // Note OFF
                ShortMessage noteOff = new ShortMessage();
                noteOff.setMessage(ShortMessage.NOTE_OFF, channel, notename.get(j).get(k), velocity);
                track.add(new MidiEvent(noteOff, tick + this.beat.get(j)));
            }
            tick += this.beat.get(j);
        }
    }

    public static MidiEvent createProgramChangeMessage() throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        // Set the instrument (program number)
        message.setMessage(ShortMessage.PROGRAM_CHANGE, 0, program, 0);
        return new MidiEvent(message, 0);
    }

    public void writeToFile(String filename) throws IOException {
        File outputFile = new File(filename + ".mid");
        MidiSystem.write(sequence, 1, outputFile);
        // Ensure to close the sequencer when it's no longer needed
        sequencer.close();
    }

    public Track getTrack() {
        return track;
    }
}
