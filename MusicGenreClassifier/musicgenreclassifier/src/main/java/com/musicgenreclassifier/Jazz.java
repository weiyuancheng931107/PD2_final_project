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
import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * InnerJazz
 */ 
interface InnerJazz {
    void Drums() throws InvalidMidiDataException, MidiUnavailableException, IOException;
    void Piano() throws InvalidMidiDataException, MidiUnavailableException, IOException;
    void Bass() throws InvalidMidiDataException, MidiUnavailableException, IOException;
}
class Jazz implements InnerJazz {
    
    public static final int KICK_DRUM = 36; // Bass drum
    public static final int RIDE = 42;
    public static final int CRASH = 51;
    Map<Integer, ArrayList<Integer>> chordHashMap;
    private int bpm;
    private int velocity;
    public ArrayList<Integer> pause;
    public Jazz(int bpm, int velocity,Map<Integer, ArrayList<Integer>> chordHashMap) throws InvalidMidiDataException, MidiUnavailableException {
        this.bpm = bpm;
        this.velocity = velocity;
        this.chordHashMap = chordHashMap;
        this.pause = new ArrayList<>();
        pause.add(-1);
    }
    public static final int ACOUSTIC_BASS_DRUM = 35;
    public static final int BASS_DRUM= 36;
    public static final int SIDE_STICK = 37;
    public static final int ACOUSTIC_SNARE = 38;
    public static final int HAND_CLAP = 39;
    public static final int ELECTRIC_SNARE = 40;
    public static final int LOW_FLOOR_TOM = 41;
    public static final int CLOSED_HI_HAT = 42;
    public static final int HIGH_FLOOR_TOM = 43;
    public static final int PEDAL_HI_HAT = 44;
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
    @Override
    public void Drums() throws InvalidMidiDataException, MidiUnavailableException, IOException {
        
        ArrayList<Integer> drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM));
        ArrayList<ArrayList<Integer>> drumcordfinal = new ArrayList<>();
        ArrayList<Double> drumsbeat = new ArrayList<>(Arrays.asList(1.0,1.5,3.0,1.0,1.5,3.0,1.5,3.0,1.5,3.0,1.5,3.0,1.5,3.0,1.0,1.5,3.0,1.0,1.5,3.0,1.0,1.5,3.0,1.0,1.5,3.0));
        //第一小節
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM,ACOUSTIC_SNARE,PEDAL_HI_HAT));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM,ACOUSTIC_SNARE,PEDAL_HI_HAT));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2));
        drumcordfinal.add(drumchord);
        //第二小節
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(ACOUSTIC_SNARE));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM,PEDAL_HI_HAT));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_SNARE));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(ACOUSTIC_SNARE));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM,PEDAL_HI_HAT));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2));
        drumcordfinal.add(drumchord);
        //第三小節
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM,ACOUSTIC_SNARE,PEDAL_HI_HAT));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM,ACOUSTIC_SNARE,PEDAL_HI_HAT));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2));
        drumcordfinal.add(drumchord);
        //第四小節
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM,ACOUSTIC_SNARE,PEDAL_HI_HAT));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(RIDE_CYMBAL_2,ACOUSTIC_BASS_DRUM,ACOUSTIC_SNARE,PEDAL_HI_HAT));
        drumcordfinal.add(drumchord);
        drumchord = new ArrayList<>(Arrays.asList(CRASH));
        drumcordfinal.add(drumchord);

        Metronome piano = new Metronome(bpm, 59, drumcordfinal, 3, velocity, drumsbeat, 9);
        piano.rhythmchord();
        piano.writeToFile("drums");
    }

    @Override
    public void Piano() throws InvalidMidiDataException, MidiUnavailableException, IOException {
        Random random = new Random();
        int randomplaylist = rand(0, 1, random) ;
        ArrayList<Integer> pianochordtemp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pianochord = new ArrayList<>();
        ArrayList<Double> pianobeat = new ArrayList<>();
        ArrayList<Integer> beatcount = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pianochordfinal = new ArrayList<>();
        ArrayList<Double> pianobeatfinal = new ArrayList<>();
        this.chordHashMap =  groupAndFilter(this.chordHashMap);
        for (Integer key : chordHashMap.keySet()) {
            beatcount.add(key);
            pianochordtemp = chordHashMap.get(key);
            pianochord.add(pianochordtemp);
        }
        for(int i = 0;i<beatcount.size()-1;i++){
            pianobeat.add(1/((double)(beatcount.get(i+1)-beatcount.get(i))));
        }
        if(beatcount.get((beatcount.size()-1))%4!=0){
            pianobeat.add((double)(1/((double)(4-beatcount.get((beatcount.size())-1)%4)+1.0)));
        }
        else{
            pianobeat.add(1.0);
        }
        for(int i = 0;i<pianobeat.size();i++){
            if(pianobeat.get(i)==0.25 && randomplaylist == 0){
                pianobeatfinal.add(1.0);
                pianobeatfinal.add(2.0);
                pianobeatfinal.add(0.4);
                pianochordfinal.add(pianochord.get(i));
                pianochordfinal.add(pause);
                pianochordfinal.add(pianochord.get(i));
                randomplaylist = rand(0, 1, random) ;
            }
            else if(pianobeat.get(i)==0.25 && randomplaylist == 1){
                pianobeatfinal.add(1.0);
                pianobeatfinal.add(2.0);
                pianobeatfinal.add(2.0);
                pianobeatfinal.add(1.0);
                pianobeatfinal.add(1.0);
                pianochordfinal.add(pianochord.get(i));
                pianochordfinal.add(pause);
                pianochordfinal.add(pianochord.get(i));
                pianochordfinal.add(pause);
                pianochordfinal.add(pianochord.get(i));
                randomplaylist = rand(0, 1, random) ;
            }
            else if(pianobeat.get(i) == 1.0&& randomplaylist == 1){
                pianobeatfinal.add(1.0);
                pianochordfinal.add(pianochord.get(i));
                randomplaylist = rand(0, 1, random) ;
            }
            else if(pianobeat.get(i) == 1.0&& randomplaylist == 0){
                pianobeatfinal.add(2.0);
                pianobeatfinal.add(2.0);
                pianochordfinal.add(pause);
                pianochordfinal.add(pianochord.get(i));
                randomplaylist = rand(0, 1, random) ;

            }
            else if(pianobeat.get(i) == 0.5 && randomplaylist == 0){
                pianobeatfinal.add((double)(2.0/(double)(3.0)));
                pianobeatfinal.add(2.0);
                pianochordfinal.add(pianochord.get(i));
                pianochordfinal.add(pianochord.get(i));
                randomplaylist = rand(0, 1, random) ;
            }
            else if(pianobeat.get(i) == 0.5 && randomplaylist == 1){
                pianobeatfinal.add(2.0);
                pianobeatfinal.add(2.0);
                pianobeatfinal.add(1.0);
                pianochordfinal.add(pause);
                pianochordfinal.add(pianochord.get(i));
                pianochordfinal.add(pianochord.get(i));
                randomplaylist = rand(0, 1, random) ;
            }
            else{
                pianobeatfinal.add(0.5);
                pianobeatfinal.add(2.0);
                pianobeatfinal.add(2.0);
                pianochordfinal.add(pianochord.get(i));
                pianochordfinal.add(pause);
                pianochordfinal.add(pianochord.get(i));
            }
        }
        System.out.println(this.chordHashMap);
        System.out.println(pianobeat);
        Metronome piano = new Metronome(bpm, 4, pianochordfinal, 4, velocity, pianobeatfinal, 0);
        piano.rhythmchord();
        piano.writeToFile("piano");
    }

    @Override
    public void Bass() throws InvalidMidiDataException, MidiUnavailableException, IOException {
        Random random = new Random();
        ArrayList<Integer> basschordtemp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> basschord = new ArrayList<>();
        ArrayList<Double> bassbeat = new ArrayList<>();
        ArrayList<Integer> beatcount = new ArrayList<>();
        ArrayList<ArrayList<Integer>> basschordfinal = new ArrayList<>();
        ArrayList<Double> bassbeatfinal = new ArrayList<>();
        this.chordHashMap =  groupAndFilter(this.chordHashMap);
        ArrayList<Integer> bassonenote= new ArrayList<>();
        int a = 0;
        for (Integer key : chordHashMap.keySet()) {
            beatcount.add(key);
            basschordtemp = chordHashMap.get(key);
            basschord.add(basschordtemp);
        }
        for(int i = 0;i<beatcount.size()-1;i++){
            bassbeat.add(1/((double)(beatcount.get(i+1)-beatcount.get(i))));
        }
        if(beatcount.get((beatcount.size()-1))%4!=0){
            bassbeat.add((double)(1/((double)(4-beatcount.get((beatcount.size())-1)%4)+1.0)));
        }
        else{
            bassbeat.add(1.0);
        }
        for (int i = 0; i < bassbeat.size(); i++) {
            if (bassbeat.get(i) == 0.25 && a == 0) {
                bassbeatfinal.add(1.0);
                bassbeatfinal.add(1.0);
                bassbeatfinal.add(1.0);
                bassbeatfinal.add(1.0);
                sortAscending(basschord);
                for (int j = 0; j < 3; j++) {
                    ArrayList<Integer> bassline = new ArrayList<>();
                    bassline.add(basschord.get(i).get(j));
                    basschordfinal.add(bassline);
                }
                ArrayList<Integer> bassline = new ArrayList<>();
                bassline.add(basschord.get(i).get(2));
                basschordfinal.add(bassline);
                a = 1;
            } else if (bassbeat.get(i) == 0.25 && a == 1) {
                bassbeatfinal.add(1.0);
                bassbeatfinal.add(1.0);
                bassbeatfinal.add(1.0);
                bassbeatfinal.add(1.0);
                sortDescending(basschord);
                for (int j = 0; j < 3; j++) {
                    ArrayList<Integer> bassline = new ArrayList<>();
                    bassline.add(basschord.get(i).get(j));
                    basschordfinal.add(bassline);
                }
                ArrayList<Integer> bassline = new ArrayList<>();
                bassline.add(basschord.get(i).get(2));
                basschordfinal.add(bassline);
                a = 0;
            } else if (bassbeat.get(i) == 1.0) {
                ArrayList<Integer> bassline = new ArrayList<>();
                bassline.add(basschord.get(i).get(rand(0, 2, random)));
                basschordfinal.add(bassline);
                bassbeatfinal.add(1.0);
            } else if (bassbeat.get(i) == 0.5) {
                bassbeatfinal.add(1.0);
                bassbeatfinal.add(1.0);
                ArrayList<Integer> bassline = new ArrayList<>();
                bassline.add(basschord.get(i).get(rand(0, 2, random)));
                basschordfinal.add(bassline);
                bassline = new ArrayList<>();
                bassline.add(basschord.get(i).get(rand(0, 2, random)));
                basschordfinal.add(bassline);
            } else {
                bassbeatfinal.add(1.0);
                bassbeatfinal.add(1.0);
                bassbeatfinal.add(1.0);
                ArrayList<Integer> bassline = new ArrayList<>();
                bassline.add(basschord.get(i).get(rand(0, 2, random)));
                basschordfinal.add(bassline);
                bassline = new ArrayList<>();
                bassline.add(basschord.get(i).get(rand(0, 2, random)));
                basschordfinal.add(bassline);
                bassline = new ArrayList<>();
                bassline.add(basschord.get(i).get(rand(0, 2, random)));
                basschordfinal.add(bassline);
            }
        }
        Metronome bass = new Metronome(bpm, 46, basschordfinal, 2, velocity, bassbeatfinal, 0);
        bass.rhythmchord();
        bass.writeToFile("bass");
    }

    public static Map<Integer, ArrayList<Integer>> groupAndFilter(Map<Integer, ArrayList<Integer>> input) {
        Map<Integer, ArrayList<Integer>> result = new HashMap<>();

        // 將鍵按4的倍數分組
        TreeMap<Integer, List<Integer>> groupedMap = new TreeMap<>();
        for (Integer key : input.keySet()) {
            int groupKey = key / 4;
            groupedMap.putIfAbsent(groupKey, new ArrayList<Integer>());
            groupedMap.get(groupKey).add(key);
        }

        // 用來檢查已經處理過的分組
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
    public static int rand(int min, int max, Random random) {
        return random.nextInt((max - min) + 1) + min;
    }
    public static void sortAscending(ArrayList<ArrayList<Integer>> listOfLists) {
        for (ArrayList<Integer> innerList : listOfLists) {
            Collections.sort(innerList);
        }
    }
    public static void sortDescending(ArrayList<ArrayList<Integer>> listOfLists) {
        for (ArrayList<Integer> innerList : listOfLists) {
            Collections.sort(innerList, Collections.reverseOrder());
        }
    }
}
