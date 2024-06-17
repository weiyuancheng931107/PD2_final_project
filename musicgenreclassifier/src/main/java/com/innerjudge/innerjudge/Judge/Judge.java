package com.innerjudge.Judge;

import java.util.*;
import com.innerjudge.MusicBasic.*;
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
        this.notename = new ArrayList<>();
        for (int i = 0; i < notename.size(); i++) {
            this.notename.add(notename.get(i) % 12);
        }
        this.beat = beat;
        this.meter = meter;
    }

    public ArrayList<ArrayList<Integer>> getChord() {
        return chordList;
    }

    public static LinkedHashMap<Integer, Double> sortChord(LinkedHashMap<Integer, Double> pointeOfChord) {
        LinkedHashMap<Integer, Double> sortedLHashMap = new LinkedHashMap<>();
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
        return sortedLHashMap;
    }

    private int determineKey() {
        int[] noteCount = new int[12];
        int[][] noteContain = new int[12][7];

        // Initialize the array with example data
        noteContain[0] = new int[]{0, 2, 4, 5, 7, 9, 11}; // C major
        noteContain[1] = new int[]{1, 3, 5, 6, 8, 10, 12}; // C# major
        noteContain[2] = new int[]{2, 4, 6, 7, 9, 11, 13}; // D major
        noteContain[3] = new int[]{3, 5, 7, 8, 10, 12, 14}; // D# major
        noteContain[4] = new int[]{4, 6, 8, 9, 11, 13, 15}; // E major
        noteContain[5] = new int[]{5, 7, 9, 10, 12, 14, 16}; // F major
        noteContain[6] = new int[]{6, 8, 10, 11, 13, 15, 17}; // F# major
        noteContain[7] = new int[]{7, 9, 11, 12, 14, 16, 18}; // G major
        noteContain[8] = new int[]{8, 10, 12, 13, 15, 17, 19}; // G# major
        noteContain[9] = new int[]{9, 11, 13, 14, 16, 18, 20}; // A major
        noteContain[10] = new int[]{10, 12, 14, 15, 17, 19, 21}; // A# major
        noteContain[11] = new int[]{11, 13, 15, 16, 18, 20, 22}; // B major

        // Apply % 12 to all elements
        for (int i = 0; i < noteContain.length; i++) {
            for (int j = 0; j < noteContain[i].length; j++) {
                noteContain[i][j] = noteContain[i][j] % 12;
            }
        }

        for (int i = 0; i < notename.size(); i++) {
            for (int j = 0; j < 12; j++) {
                for (int k = 0; k < 7; k++) {
                    if (notename.get(i) == noteContain[j][k]) {
                        noteCount[j]++;
                    }
                }
            }
        }
        int maxCount = 0;
        int key = 0;
        for (int i = 0; i < noteCount.length; i++) {
            if (noteCount[i] > maxCount) {
                maxCount = noteCount[i];
                key = i;
            }
        }
        return key;
    }

    public Map<Integer, ArrayList<Integer>> judgement() {
        ArrayList<Chord> chords = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> judgements = new HashMap<>();
        LinkedHashMap<Integer, Double> pointOfchord = new LinkedHashMap<>();
        double parameter = 0;
        int j = 0;
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
            chordList.add(chords.get(i).get7());
            chordList.add(chords.get(i).getDim());
            chordList.add(chords.get(i).getDim7());
            chordList.add(chords.get(i).getMaj());
            chordList.add(chords.get(i).getMaj7());
            chordList.add(chords.get(i).getMin());
            chordList.add(chords.get(i).getMin7());
        }

        for (int i = 0; i < chordList.size(); i++) {
            pointOfchord.put(i, 0.0);
        }

        int key = determineKey();
        List<Integer> keyNotes = Arrays.asList(key, (key + 2) % 12, (key + 4) % 12, (key + 5) % 12, (key + 7) % 12, (key + 9) % 12, (key + 11) % 12);
        System.out.println(key);
        for (int i = 0; i < beat.size(); i++) {
            for (int k = 0; k < chordList.size(); k++) {
                if (chordList.get(k).contains(notename.get(i))) {
                    // Modify pitch similarity
                    double pitchSimilarity = 0.2 * (1.0 - Math.abs(notename.get(i) % 12 - chordList.get(k).get(0)) / 12.0);

                    // Modify chord progression smoothness
                    double chordProgressionSmoothness = (i > 0 && chordList.get(k).contains(notename.get(i - 1))) ? 0.6 : 0.0;

                    // Modify rhythm matching
                    double rhythmMatching = 0;
                    if ((parameter % 4.0) == 0) {
                        if (beat.get(i) == 0.25) {
                            rhythmMatching = 2.5*1.5;
                        } else if (beat.get(i) == 0.4) {
                            rhythmMatching = 2.2*1.5;
                        } else if (beat.get(i) == 0.5) {
                            rhythmMatching = 2.0*1.5;
                        } else if (beat.get(i) == (double)(1.0/(double)(3.0))) {
                            rhythmMatching = 1.8*1.5;
                        } else if (beat.get(i) == 0.8) {
                            rhythmMatching = 1.6*1.5;
                        } else if (beat.get(i) == 1.0) {
                            rhythmMatching = 1.0*1.5;
                        } else if (beat.get(i) == (double)(2.0/(double)(3.0))) {
                            rhythmMatching = 0.8*1.5;
                        } else if (beat.get(i) == 1.6) {
                            rhythmMatching = 1.1*1.5;
                        } else if (beat.get(i) == 2.0) {
                            rhythmMatching = 1.0*1.5;
                        }
                    }else{
                        if (beat.get(i) == 0.25) {
                            rhythmMatching = 2.5;
                        } else if (beat.get(i) == 0.4) {
                            rhythmMatching = 2.2;
                        } else if (beat.get(i) == 0.5) {
                            rhythmMatching = 2.0;
                        } else if (beat.get(i) == (double)(1.0/(double)(3.0))) {
                            rhythmMatching = 1.8;
                        } else if (beat.get(i) == 0.8) {
                            rhythmMatching = 1.6;
                        } else if (beat.get(i) == 1.0) {
                            rhythmMatching = 1.0;
                        } else if (beat.get(i) == (double)(2.0/(double)(3.0))) {
                            rhythmMatching = 0.96;
                        } else if (beat.get(i) == 1.6) {
                            rhythmMatching = 0.8;
                        } else if (beat.get(i) == 2.0) {
                            rhythmMatching = 0.76;
                        } else if (beat.get(i) == (double)(4.0/(double)(3.0))) {
                            rhythmMatching = 0.71;
                        } else if (beat.get(i) == 3.2) {
                            rhythmMatching = 0.5;
                        } else if (beat.get(i) == 4.0) {
                            rhythmMatching = 0.4;
                        } else if (beat.get(i) == (double)(8.0/(double)(3.0))) {
                            rhythmMatching = 0.2;
                        } else if (beat.get(i) == 6.4) {
                            rhythmMatching = 0.1;
                        } else if (beat.get(i) == 8.0) {
                            rhythmMatching = 0.04;
                        } else if (beat.get(i) == 10.67) {
                            rhythmMatching = 0.03;
                        } else if (beat.get(i) == (double)(16.0/(double)(3.0))) {
                            rhythmMatching = 0.02;
                        } else if (beat.get(i) == 16.0) {
                            rhythmMatching = 0.01;
                        } else if(beat.get(i) == 3.0){
                            rhythmMatching = 0.45;
                        } else if(beat.get(i) == 5.0){
                            rhythmMatching = 0.24;
                        } else if(beat.get(i) == 7.0){
                            rhythmMatching = 0.1;
                        }
                    }

                    // Modify chord stability
                    double chordStability = (i % meter == 0) ? 0.6 : 0.2;

                    // Modify key matching
                    double keyMatching = keyNotes.contains(notename.get(i) % 12) ? 0.4 : 0.0;
                    for (int p = 0; p < chordList.get(k).size(); p++) {
                        if (!keyNotes.contains(chordList.get(k).get(p))) {
                            keyMatching -= 0.2;
                        }
                    }

                    // Modify note context matching
                    double noteContextMatching = 0.0;
                    if (i > 0 && chordList.get(k).contains(notename.get(i - 1))) {
                        noteContextMatching += 0.3;
                    }
                    if (i < notename.size() - 1 && chordList.get(k).contains(notename.get(i + 1))) {
                        noteContextMatching += 0.3;
                    }

                    // Modify speed context matching
                    double speedContextMatching = 0.0;
                    if (i > 0 && Math.abs(beat.get(i) - beat.get(i - 1)) < 0.1) {
                        speedContextMatching += 0.2;
                    }
                    if (i < beat.size() - 1 && Math.abs(beat.get(i) - beat.get(i + 1)) < 0.1) {
                        speedContextMatching += 0.2;
                    }

                    // Add new condition to avoid minor second and major second intervals
                    double intervalAvoidance = 0.0;
                    for (int chordNote : chordList.get(k)) {
                        int interval = Math.abs(notename.get(i) - chordNote) % 12;
                        if (interval == 1) {
                            intervalAvoidance -= 1.5; // Avoid minor second
                        } else if (interval == 2) {
                            intervalAvoidance -= 0.9; // Avoid major second
                        }
                    }

                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + pitchSimilarity + chordProgressionSmoothness + rhythmMatching + chordStability + keyMatching + noteContextMatching + speedContextMatching + intervalAvoidance + 1.0);
                }
            }

            if (parameter <= 1) {
                pointOfchord = sortChord(pointOfchord);
                int index = 0;
                for (Integer keys : pointOfchord.keySet()) {
                    index = keys;
                    break;
                }
                judgements.put(j + 1, chordList.get(index));
            }

            parameter += 1.0 / beat.get(i); // real length
            while (parameter >= 1) {
                parameter -= 1;
                j++;
                for (int k = 0; k < pointOfchord.size(); k++) {
                    pointOfchord.put(k, 0.0);
                }
                if (j % 4 == 0) {
                }
            }
        }
        return judgements;
    }

    public Map<Integer, ArrayList<Integer>> getRepeat(Map<Integer, ArrayList<ArrayList<Integer>>> judgements) {
        Map<Integer, ArrayList<Integer>> repeat = new HashMap<>();
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
                if (judgeList1.size() != 0) {
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
