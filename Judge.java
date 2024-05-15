package com.musicgenreclassifier;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Judge {
    Chord C;
    Chord Csharp;
    Chord D;
    Chord Dsharp;
    Chord E;
    Chord F;
    Chord Fsharp;
    Chord G;
    Chord Gsharp;
    Chord A;
    Chord Asharp;
    Chord B;
    ArrayList<Integer> notename;
    ArrayList<Integer> beat;
    int meter;
    ArrayList<ArrayList<Integer>> Chord;
    public Judge(ArrayList<Integer> notename,ArrayList<Integer> beat,int meter){
        Chord C = new Chord(0,4);
        Chord Csharp = new Chord(1,4);
        Chord D = new Chord(2,4);
        Chord Dsharp = new Chord(3,4);
        Chord E = new Chord(4,4);
        Chord F = new Chord(5,4);
        Chord Fsharp = new Chord(6,4);
        Chord G = new Chord(7,4);
        Chord Gsharp = new Chord(8,4);
        Chord A = new Chord(9,4);
        Chord Asharp = new Chord(10,4);
        Chord B = new Chord(11,4);
        ArrayList<ArrayList<Integer>> Chord = new ArrayList<>();
        this.notename = notename;
        this.beat = beat;
        this.meter = meter;
    }
    public ArrayList<ArrayList<Integer>> getChord(){
        return Chord;
    }
    private  HashMap<Integer,ArrayList<ArrayList<Integer>>> judement(){
        ArrayList<Chord> ChordList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> judeList= new ArrayList<>();
        HashMap<Integer,ArrayList<ArrayList<Integer>>> judefinHashMap = new HashMap<>();
        double parameter = 0;
        int j = 0;
        ChordList.add(C);
        ChordList.add(Csharp);
        ChordList.add(D);
        ChordList.add(Dsharp);
        ChordList.add(E);
        ChordList.add(F);
        ChordList.add(Fsharp);
        ChordList.add(G);
        ChordList.add(Gsharp);
        ChordList.add(A);
        ChordList.add(Asharp);
        ChordList.add(B);
        for(int i = 0;i<ChordList.size();i++){
            judeList.add(ChordList.get(i).get_7());
            judeList.add(ChordList.get(i).get_O());
            judeList.add(ChordList.get(i).get_O7());
            judeList.add(ChordList.get(i).get_maj());
            judeList.add(ChordList.get(i).get_maj7());
            judeList.add(ChordList.get(i).get_min());
            judeList.add(ChordList.get(i).get_min7());
        }
        for(int i = 0;i<beat.size();i++){
            if(parameter<1){
                if(judeList.get(i).contains(notename.get(i))){}
                else{
                    judeList.remove(judeList.get(i));
                }
                parameter += (double)(1/beat.get(i));
            }
            else{
                judefinHashMap.put(j+1, judeList);
                j++;
                parameter -= 1;
            }
        }
        return judefinHashMap;
    }
}

