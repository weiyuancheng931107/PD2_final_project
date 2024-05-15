package com.musicgenreclassifier;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Track;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            int bpm = 100;
            int velocity = 100;
            Jazz drumsJazz = new Jazz(bpm, velocity);
            drumsJazz.drums();
            
            
        } catch (MidiUnavailableException | InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
    }
    
}
