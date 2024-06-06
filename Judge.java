package com.musicgenreclassifier;

import java.util.*;

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
        for(int i = 0;i<notename.size();i++){
            this.notename.add(notename.get(i)%12);
        }
        this.beat = beat;
        this.meter = meter;
    }

    public ArrayList<ArrayList<Integer>> getChord() {
        return chordList;
    }

    public static Map<Integer, Double> sortChord(Map<Integer, Double> pointeOfChord) {
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
        return sortedLHashMap;
    }

    private int determineKey() {
        int[] noteCount = new int[12];
        for (int note : notename) {
            noteCount[note % 12]++;
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

    @SuppressWarnings("unchecked")
    public Map<Integer, ArrayList<Integer>> judgement() {
        ArrayList<Chord> chords = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> judgements = new HashMap<>();
        ArrayList<Integer> negativeArrayList = new ArrayList<>(Collections.singletonList(-1));
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

        // 添加指定和弦
        chordList.add(new ArrayList<>(Arrays.asList(9, 0, 4, 7)));  // A-7
        chordList.add(new ArrayList<>(Arrays.asList(2, 5, 9, 1)));  // D-7
        chordList.add(new ArrayList<>(Arrays.asList(7, 11, 2, 5)));  // G7
        chordList.add(new ArrayList<>(Arrays.asList(0, 4, 7, 11)));  // CMAJ7

        for (int i = 0; i < chordList.size(); i++) {
            pointOfchord.put(i, 0.0);
        }

        int key = determineKey();
        List<Integer> keyNotes = Arrays.asList(key, (key + 2) % 12, (key + 4) % 12, (key + 5) % 12, (key + 7) % 12, (key + 9) % 12, (key + 11) % 12);

        for (int i = 0; i < beat.size(); i++) {
            for (int k = 0; k < chordList.size(); k++) {
                if (chordList.get(k).contains(notename.get(i))) {
                    // 基于音高的相似性
                    double pitchSimilarity = 0.1 * (1.0 - Math.abs(notename.get(i) % 12 - chordList.get(k).get(0)) / 12.0);

                    // 基于和弦进行的流畅度（假设前一个和弦的索引是prevChordIndex）
                    double chordProgressionSmoothness = (i > 0 && chordList.get(k).contains(notename.get(i - 1))) ? 0.5 : 0.0;

                    // 基于节奏匹配度
                    double rhythmMatching = 0;
                    if ((parameter % 4.0) == 0) {
                        if (beat.get(i) == 1.0) {
                            rhythmMatching = 0.6;
                        } else if (beat.get(i) == 0.25) {
                            rhythmMatching = 2.5;
                        } else if (beat.get(i) == 0.5) {
                            rhythmMatching = 2.0;
                        }
                    } else {
                        if (beat.get(i) == 1.0) {
                            rhythmMatching = 0.4;
                        } else if (beat.get(i) == 3.0) {
                            rhythmMatching = 0.35;
                        } else if (beat.get(i) == 4.0) {
                            rhythmMatching = 0.25;
                        } else if (beat.get(i) == 16.0) {
                            rhythmMatching = 0.06;
                        } else if (beat.get(i) == 32.0) {
                            rhythmMatching = 0.03;
                        } else if (beat.get(i) == 5.0) {
                            rhythmMatching = 0.2;
                        }
                    }

                    // 和弦稳定性
                    double chordStability = (i % meter == 0) ? 0.5 : 0.1;

                    // 调性匹配度
                    double keyMatching = keyNotes.contains(notename.get(i) % 12) ? 0.3 : 0.0;

                    // 考虑前后的音符是否包含在某个和弦中
                    double noteContextMatching = 0.0;
                    if (i > 0 && chordList.get(k).contains(notename.get(i - 1))) {
                        noteContextMatching += 0.2;
                    }
                    if (i < notename.size() - 1 && chordList.get(k).contains(notename.get(i + 1))) {
                        noteContextMatching += 0.2;
                    }

                    // 考虑前后的音符和速度比较
                    double speedContextMatching = 0.0;
                    if (i > 0 && Math.abs(beat.get(i) - beat.get(i - 1)) < 0.1) {
                        speedContextMatching += 0.1;
                    }
                    if (i < beat.size() - 1 && Math.abs(beat.get(i) - beat.get(i + 1)) < 0.1) {
                        speedContextMatching += 0.1;
                    }

                    pointOfchord.put(k, pointOfchord.getOrDefault(k, 0.0) + pitchSimilarity + chordProgressionSmoothness + rhythmMatching + chordStability + keyMatching + noteContextMatching + speedContextMatching + 1.0);
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

            parameter += 1.0 / beat.get(i);
            while (parameter >= 1) {
                parameter -= 1;
                j++;
                for (int k = 1; k < pointOfchord.size(); k++) {
                    pointOfchord.put(k, 0.0);
                }
                if (j % 4 == 0) {
                    bar++;
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
