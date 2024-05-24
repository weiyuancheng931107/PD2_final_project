package com.musicgenreclassifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


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
    ArrayList<Double> beat;
    int meter;
    ArrayList<ArrayList<Integer>> chordList;

    public Judge(ArrayList<Integer> notename, ArrayList<Double> beat, int meter) {
        this.C = new Chord(0, -1);
        this.Csharp = new Chord(1, -1);
        this.D = new Chord(2, -1);
        this.Dsharp = new Chord(3, -1);
        this.E = new Chord(4, -1);
        this.F = new Chord(5, -1);
        this.Fsharp = new Chord(6, -1);
        this.G = new Chord(7, -1);
        this.Gsharp = new Chord(8, -1);
        this.A = new Chord(9, -1);
        this.Asharp = new Chord(10, -1);
        this.B = new Chord(11, -1);
        this.chordList = new ArrayList<>();
        this.notename = notename;
        this.beat = beat;
        this.meter = meter;
    }

    public ArrayList<ArrayList<Integer>> getChord() {
        return chordList;
    }

    public static Map<Integer, Double> sortChord(Map<Integer, Double> pointeOfChord) {
        // System.out.println(pointeOfChord);
        Map<Integer, Double> sortedLHashMap = new LinkedHashMap<>();
        List<Map.Entry<Integer, Double>> list = new ArrayList<>(pointeOfChord.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (Map.Entry<Integer, Double> entry : list) {
            sortedLHashMap.put(entry.getKey(), entry.getValue());
        }
        // System.out.println(sortedLHashMap);
        return sortedLHashMap;
    }

    @SuppressWarnings("unchecked")
    public Map<Integer, ArrayList<Integer>> judgement() {
        ArrayList<Chord> chords = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> judgements = new HashMap<>();
        ArrayList<Integer> negativeArrayList = new ArrayList<>(Arrays.asList(-1));
        Map<Integer, Double> pointOfchord = new HashMap<>();
        double parameter = 0;
        int j = 0;
        int bar = 0;
        chords.add(C);
        chords.add(Csharp);
        chords.add(D);
        chords.add(Dsharp);
        chords.add(E);
        chords.add(F);
        chords.add(Fsharp);
        chords.add(G);
        chords.add(Gsharp);
        chords.add(A);
        chords.add(Asharp);
        chords.add(B);

        for (int i = 0; i < chords.size(); i++) {
            chordList.add(chords.get(i).get_7());
            chordList.add(chords.get(i).get_O());
            chordList.add(chords.get(i).get_O7());
            chordList.add(chords.get(i).get_maj());
            chordList.add(chords.get(i).get_maj7());
            chordList.add(chords.get(i).get_min());
            chordList.add(chords.get(i).get_min7());
        }
        for(int i = 0;i<chordList.size();i++){
            pointOfchord.put(i,0.0);
        }
        for(int i = 0;i<beat.size();i++){
            for(int k = 0;k<chordList.size();k++){
                if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) == 0 && ((double)beat.get(i)) == 1.0){
                    // System.out.println("true");
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 0.55);
                }
                else if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) != 0 && ((double)beat.get(i)) == 1.0){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 0.33);
                }
                else if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) != 0 && ((double)beat.get(i)) == 3.0){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 0.36);
                }
                else if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) != 0 && ((double)beat.get(i)) == 4.0){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 0.27);
                }
                else if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) != 0 && ((double)beat.get(i)) == 16.0){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 0.063);
                }
                else if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) != 0 && ((double)beat.get(i)) == 32.0){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 0.035);
                }
                else if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) != 0 && ((double)beat.get(i)) == 5.0){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 0.18);
                }
                else if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) != 0 && ((double)beat.get(i)) == 0.25){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 4.1);
                }
                else if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) != 0 && ((double)beat.get(i)) == 0.5){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 2.1);
                }
                else if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) == 0 && ((double)beat.get(i)) == 0.25){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 2.1);
                }
                else if(chordList.get(k).contains(notename.get(i))&&(parameter%4.0) == 0 && ((double)beat.get(i)) == 0.4){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 4);
                }
                else if(chordList.get(k).contains(notename.get(i))&& (judgements.getOrDefault(i-1,negativeArrayList).contains(notename.get(i))|| judgements.getOrDefault(i+1,negativeArrayList).contains(notename.get(i)))){
                    System.out.println(notename.get(i));
                    System.out.println(judgements.getOrDefault(i,negativeArrayList));
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 100.0);
                }
                if(chordList.get(k).contains(notename.get(i))){
                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + 1.0);
                }
            }
            if (parameter <= 1) {
                pointOfchord = sortChord(pointOfchord);
                int index = 0;
                for (Integer key : pointOfchord.keySet()) {
                    index = key;
                    break;
                }
                judgements.put(j+1,chordList.get(index));

            }
            parameter += (double) (1.0 / beat.get(i));
            while ((Math.abs(parameter - 1) <= 0.01 || parameter >= 1)) {
                parameter -= 1;
                j++;
                for(int k = 1;k<pointOfchord.size();k++){
                    pointOfchord.put(k, 0.0);
                }
                if(j%4 == 0){
                    bar++;
                }
            }
        }
        System.out.println(judgements);
    //     ArrayList<ArrayList<Integer>> originalChordList = new ArrayList<>(chordList);
        
    //     // ArrayList<ArrayList<Integer>> currentChordList = new ArrayList<>(originalChordList);
    //     ArrayList<ArrayList<Integer>> currentChordList = new ArrayList<>(originalChordList);
    //     // System.out.println(originalChordList);
    //     // System.out.println("-======================================");
    //     Iterator<ArrayList<Integer>> iterator = currentChordList.iterator();
    //     for (int i = 0; i < beat.size(); i++) {
    //         // ArrayList<ArrayList<Integer>> currentChordList = new ArrayList<>(originalChordList);
    //         // Iterator<ArrayList<Integer>> iterator = currentChordList.iterator();
    //         while (iterator.hasNext()) {
    //             ArrayList<Integer> currentChord = iterator.next();
    //             if (!currentChord.contains(notename.get(i))) {
    //                 iterator.remove();
    //             }
    //         }
    //         if (parameter <= 1) {
    //             // System.out.println(currentChordList);
    //             judgements.put(j + 1, currentChordList);
    //         }
    //         parameter += (double) (1.0 / beat.get(i));
    //         while (Math.abs(parameter - 1) <= 0.01 || parameter >= 1) {
    //             if (parameter > 1) {
    //                 currentChordList = new ArrayList<>(originalChordList);
    //                 iterator = currentChordList.iterator();
    //                 while (iterator.hasNext()) {
    //                     ArrayList<Integer> currentChord = iterator.next();
    //                     if (!currentChord.contains(notename.get(i))) {
    //                         iterator.remove();
    //                     }
    //                 }
    //             } else {
    //                 currentChordList = new ArrayList<>(originalChordList);
    //             }
    //             parameter -= 1;
    //             j++;
    //             // judgements.put(j, currentChordList);
                
    //         }
    //         iterator = currentChordList.iterator();
    //     }
            return judgements;
        }

    public Map<Integer, ArrayList<Integer>> getRepeat(Map<Integer, ArrayList<ArrayList<Integer>>> judgements) {
        Map<Integer, ArrayList<Integer>> repeat = new HashMap<>();
        // System.out.println(judgements);
        Random random = new Random();

        List<Integer> keys = new ArrayList<>(judgements.keySet());
        for (int i = 0; i < keys.size() - 1; i++) {
            int key1 = keys.get(i);
            ArrayList<ArrayList<Integer>> judgeList1 = judgements.get(key1);
            boolean found = false;

            for (int j = i + 1; j < keys.size(); j++) {
                int key2 = keys.get(j);
                ArrayList<ArrayList<Integer>> judgeList2 = judgements.get(key2);

                for (ArrayList<Integer> judge1 : judgeList1) {
                    if (found) break;
                    for (ArrayList<Integer> judge2 : judgeList2) {
                        if (judge1.equals(judge2)) {
                            repeat.put(key1, judge1);
                            repeat.put(key2, judge1);
                            found = true;
                            break;
                        }
                    }
                }
            }

            if (!found) {
                if (judgeList1.size()!= 0) {
                    int randomIndex1 = random.nextInt(judgeList1.size());
                    repeat.put(key1, judgeList1.get(randomIndex1));
    
                    for (int j = 0; j < keys.size(); j++) {
                        if (i == j) continue;
                        int key2 = keys.get(j);
                        ArrayList<ArrayList<Integer>> judgeList2 = judgements.get(key2);
                        int randomIndex2 = random.nextInt(judgeList2.size());
                        repeat.put(key2, judgeList2.get(randomIndex2));
                    }
                }
            }
        }
        return repeat;
    }
    
}
