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
            // 设置节奏器参数
            int bpm = 200;
            int program = 20; // 管风琴的程序号为20
             // MIDI音符C的值为60
            int pitch = 2; // 设置音高
            int velocity = 100; // 音量
            Chord chord = new Chord(1, 3);
            ArrayList<Integer> C_maj = new ArrayList<>();
            ArrayList<Integer> C_min = new ArrayList<>();
            int[] a = {0};
            int i = 0;
            int tick = 0;
            C_maj = chord.get_maj7();
            C_min = chord.get_min7();
            // 创建Metronome对象
            Metronome metronome = new Metronome(bpm, 20, C_maj, pitch, velocity, 1,1);
            Metronome chordtest = new Metronome(bpm, 2, C_min, pitch, velocity, 1,0);

            // 设置管风琴音色
            metronome.getTrack().add(Metronome.createProgramChangeMessage());
            chordtest.getTrack().add(Metronome.createProgramChangeMessage());

            // 用户输入节拍
            for(i = 0;i<4;i++){
                
                metronome.rhythmchord();
                chordtest.rhythmchord();
            }

            // 将Sequence输出为MIDI文件
            chordtest.writeToFile("piano");
            metronome.writeToFile("pipeorgan");
            
        } catch (MidiUnavailableException | InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
        // System.out.println("end");
        // System.exit(0);
    }
    
}
