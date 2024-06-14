package com.musicgenreclassifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

class DefineStyle {
    private int style;
    private int bpm;
    private  Map<Integer, ArrayList<Integer>> chordMap;
    private int barAmount;
    public DefineStyle(int style,int bpm,Map<Integer, ArrayList<Integer>> chordMap,int barAmount){
        this.style = style;
        this.bpm = bpm;
        this.chordMap = chordMap;
        this.barAmount = barAmount;
    }
    public void midimerge() throws InvalidMidiDataException, MidiUnavailableException, IOException{
        
        if(style == 0){
            Rock rock= new Rock(bpm, 100, chordMap,barAmount);
            rock.Piano();
            rock.Drums();
            rock.Bass();
            rock.Guitar();
            String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 1){
            Jazz jazz= new Jazz(bpm, 100, chordMap,barAmount);
            jazz.Piano();
            jazz.Drums();
            jazz.Bass();
            String[] midiFiles = {"piano.mid","bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 2){
            BossaNova  bossaNova= new BossaNova(bpm, 100, chordMap,barAmount);
            bossaNova.Piano();
            bossaNova.Drums();
            bossaNova.Bass();
            String[] midiFiles = {"piano.mid","bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 3){
            RnB rnb= new RnB(bpm, 100, chordMap,barAmount);
            rnb.Piano();
            rnb.Drums();
            rnb.Bass();
            rnb.Guitar();
            String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 4){
            Soul soul= new Soul(bpm, 100, chordMap,barAmount);
            soul.Piano();
            soul.Drums();
            soul.Bass();
            soul.Guitar();
            String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 5){
            Funk funk= new Funk(bpm, 100, chordMap,barAmount);
            funk.Piano();
            funk.Drums();
            funk.Bass();
            funk.Guitar();
            String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 6){
            Reggae reggae= new Reggae(bpm, 100, chordMap,barAmount);
            reggae.Piano();
            reggae.Drums();
            reggae.Bass();
            reggae.Guitar();
            String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } 
    }
}
