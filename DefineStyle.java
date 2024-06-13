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
    public DefineStyle(int style,int bpm,Map<Integer, ArrayList<Integer>> chordMap){
        this.style = style;
        this.bpm = bpm;
        this.chordMap = chordMap;
    }
    public void midimerge() throws InvalidMidiDataException, MidiUnavailableException, IOException{
        
        if(style == 0){
            Rock rock= new Rock(bpm, 100, chordMap);
            rock.Piano();
            rock.Drums();
            rock.Bass();
            rock.Guitar();
            MidiMerger midimerge = new MidiMerger();
            String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 1){
            Jazz rock= new Jazz(bpm, 100, chordMap);
            rock.Piano();
            rock.Drums();
            rock.Bass();
            MidiMerger midimerge = new MidiMerger();
            String[] midiFiles = {"piano.mid","bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 2){
            BossaNova rock= new BossaNova(bpm, 100, chordMap);
            rock.Piano();
            rock.Drums();
            rock.Bass();
            MidiMerger midimerge = new MidiMerger();
            String[] midiFiles = {"piano.mid","bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 3){
            RnB rock= new RnB(bpm, 100, chordMap);
            rock.Piano();
            rock.Drums();
            rock.Bass();
            rock.Guitar();
            MidiMerger midimerge = new MidiMerger();
            String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 4){
            Soul rock= new Soul(bpm, 100, chordMap);
            rock.Piano();
            rock.Drums();
            rock.Bass();
            rock.Guitar();
            MidiMerger midimerge = new MidiMerger();
            String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 5){
            Funk rock= new Funk(bpm, 100, chordMap);
            rock.Piano();
            rock.Drums();
            rock.Bass();
            rock.Guitar();
            MidiMerger midimerge = new MidiMerger();
            String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } else if(style == 6){
            Reggae rock= new Reggae(bpm, 100, chordMap);
            rock.Piano();
            rock.Drums();
            rock.Bass();
            rock.Guitar();
            MidiMerger midimerge = new MidiMerger();
            String[] midiFiles = {"piano.mid","guitar.mid", "bass.mid","output.mid","drums.mid"};
            String outputFile = "merged.mid";
            MidiMerger.mergeMidiFiles(midiFiles,outputFile);
        } 
    }
}
