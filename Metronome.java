package com.musicgenreclassifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.midi.*;

class Metronome {
    private Sequence sequence;
    private Track track;
    private int bpm;
    private static int program;
    private ArrayList<Integer> notename;
    private int pitch;
    private int velocity;
    private int beat;
    private int channel;
    public static int tick =0;
    private Sequencer sequencer;
    public Metronome(int bpm, int program, ArrayList<Integer> notename, int pitch, int velocity, double beat,int channel) throws InvalidMidiDataException, MidiUnavailableException {
        this.bpm = bpm;
        Metronome.program = program;
        this.notename = notename;
        this.pitch = pitch;
        this.velocity = velocity;
        this.channel = channel;
        this.beat = (int) (120 / beat);
        this.sequence = new Sequence(Sequence.PPQ, bpm);
        this.track = (this.sequence).createTrack();
        sequencer = MidiSystem.getSequencer(); // 获取 sequencer
        sequencer.open(); // 打开 sequencer
    }

    public int getBpm() {
        return bpm;
    }

    public int getProgram() {
        return program;
    }

    public ArrayList<Integer> getNotename() {
        return notename;
    }

    public int getPitch() {
        return pitch;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getBeat() {
        return this.beat;
    }

    public void rhythmchord() throws InvalidMidiDataException {
        // 添加MIDI事件：音符ON
        for(int i = 0;i<notename.size();i++){
            if(notename.get(i) == -1){
                break;
            }
            ShortMessage noteOn = new ShortMessage();
            noteOn.setMessage(ShortMessage.NOTE_ON, channel, notename.get(i), velocity);
            track.add(new MidiEvent(noteOn, tick));
        }
        // 添加MIDI事件：音符OFF
        for(int i =0;i<notename.size();i++){
            if(notename.get(i) == -1){
                continue;
            }
            ShortMessage noteOff = new ShortMessage();
            noteOff.setMessage(ShortMessage.NOTE_OFF, channel, notename.get(i), velocity);
            track.add(new MidiEvent(noteOff, tick + this.beat)); 
        }
        tick += this.beat;
    }
    public void rhythmonenote() throws InvalidMidiDataException {
        // 添加MIDI事件：音符ON
        for(int i = 0;i<notename.size();i++){
            if(notename.get(i) == -1){
                continue;
            }
            ShortMessage noteOn = new ShortMessage();
            noteOn.setMessage(ShortMessage.NOTE_ON, channel, notename.get(i), velocity);
            track.add(new MidiEvent(noteOn, tick));
        // 添加MIDI事件：音符OFF
            ShortMessage noteOff = new ShortMessage();
            noteOff.setMessage(ShortMessage.NOTE_OFF, channel, notename.get(i), velocity);
            track.add(new MidiEvent(noteOff, tick + this.beat)); 
            tick += this.beat;
        }
    }
    public static MidiEvent createProgramChangeMessage() throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.PROGRAM_CHANGE, 0, program, 0);
        return new MidiEvent(message, 0);
    }
    public void writeToFile(String Filname) throws IOException {
        File outputFile = new File(Filname + ".mid");
        MidiSystem.write(sequence, 1, outputFile);
        // 请确保在不再需要Sequencer实例时关闭它
        sequencer.close();
    }
    

    public Track getTrack() {
        return track;
    }
    
}