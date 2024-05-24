package com.musicgenreclassifier;
import java.util.ArrayList;

class Chord{
    int noteName;
    int pitch;
    public Chord(int noteName, int pitch) {
        this.noteName = noteName;
        this.pitch = pitch;
    }
    public ArrayList<Integer> get_maj7(){
        ArrayList<Integer> maj7 = new ArrayList<>();
        maj7.add(noteName);
        maj7.add(noteName + 4);
        maj7.add(noteName + 7);
        maj7.add(noteName + 11);
        for(int noteValue=0;noteValue<4;noteValue++){
            Note C = new Note(maj7.get(noteValue) % 12, pitch);
            maj7.set(noteValue, C.getNote());
        }
        // System.out.println("maj7");
        return maj7;
    }
    public ArrayList<Integer> get_7(){
        ArrayList<Integer> _7 = new ArrayList<>();
        _7.add(noteName);
        _7.add(noteName + 4);
        _7.add(noteName + 7);
        _7.add(noteName + 10);
        for(int noteValue=0;noteValue<4;noteValue++){
            Note C = new Note(_7.get(noteValue) % 12, pitch);
            _7.set(noteValue, C.getNote());
        }
        // System.out.println("7");
        return _7;
    }
    public ArrayList<Integer> get_min7(){
        
        ArrayList<Integer> C_min7 = new ArrayList<>();
        C_min7.add(noteName);
        C_min7.add(noteName + 3);
        C_min7.add(noteName + 7);
        C_min7.add(noteName + 10);
        for(int noteValue=0;noteValue<4;noteValue++){
            Note C = new Note(C_min7.get(noteValue) % 12, pitch);
            C_min7.set(noteValue, C.getNote());
        }
        // System.out.println("-7");
        return C_min7;
    }
    public ArrayList<Integer> get_O7(){
        
        ArrayList<Integer> C_O7 = new ArrayList<>();
        C_O7.add(noteName);
        C_O7.add(noteName + 3);
        C_O7.add(noteName + 6);
        C_O7.add(noteName + 10);
        for(int noteValue=0;noteValue<4;noteValue++){
            Note C = new Note(C_O7.get(noteValue) % 12, pitch);
            C_O7.set(noteValue, C.getNote());
        }
        // System.out.println("o7");
        return C_O7;
    }
    public ArrayList<Integer> get_maj(){
        
        ArrayList<Integer> C_maj = new ArrayList<>();
        C_maj.add(noteName);
        C_maj.add(noteName + 4);
        C_maj.add(noteName + 7);
        for(int noteValue=0;noteValue<3;noteValue++){
            Note C = new Note(C_maj.get(noteValue) % 12, pitch);
            C_maj.set(noteValue, C.getNote());
        }
        // System.out.println("maj");
        return C_maj;
    }
    public ArrayList<Integer> get_min(){
        ArrayList<Integer> C_min = new ArrayList<>();
        C_min.add(noteName);
        C_min.add(noteName + 3);
        C_min.add(noteName + 7);
        for(int noteValue=0;noteValue<3;noteValue++){
            Note C = new Note(C_min.get(noteValue) % 12, pitch);
            C_min.set(noteValue, C.getNote());
        }
        // System.out.println("min");
        return C_min;
    }
    public ArrayList<Integer> get_O(){
        ArrayList<Integer> C_O = new ArrayList<>();
        C_O.add(noteName);
        C_O.add(noteName + 3);
        C_O.add(noteName + 6);
        for(int noteValue=0;noteValue<3;noteValue++){
            Note C = new Note(C_O.get(noteValue) % 12, pitch);
            C_O.set(noteValue, C.getNote());
        }
        // System.out.println("O");
        return C_O;
    }
}
