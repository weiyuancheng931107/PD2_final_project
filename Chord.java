package com.musicgenreclassifier;

// class Chord{
//     int noteName;
//     int pitch;
//     public Chord(int noteName, int pitch) {
//         this.noteName = noteName;
//         this.pitch = pitch;
//     }
//     public int[] get_maj7(){
//         int pitch = 3;
//         int[] maj7 = {noteName,noteName+4,noteName+7,noteName+11};
//         for(int noteValue=1;noteValue<=4;noteValue++){
//             Note C = new Note(maj7[noteValue-1]%13,pitch);
//             maj7[noteValue-1] = C.getNote();
//         }
//         System.out.println("maj7");
//         return maj7;
//     }
//     public int[] get_7(){
//         int pitch = 3;
//         int[] _7 = {noteName,noteName+4,noteName+7,noteName+10};
//         for(int noteValue=1;noteValue<=4;noteValue++){
//             Note C = new Note(_7[noteValue-1]%13,pitch);
//             _7[noteValue-1] = C.getNote();
//         }
//         System.out.println("7");
//         return _7;
//     }
//     public int[] get_min7(){
//         int pitch = 3;
//         int[] C_min7 = {noteName,noteName+3,noteName+7,noteName+10};
//         for(int noteValue=1;noteValue<=4;noteValue++){
//             Note C = new Note(C_min7[noteValue-1]%13,pitch);
//             C_min7[noteValue-1] = C.getNote();
//         }
//         System.out.println("-7");
//         return C_min7;
//     }
//     public int[]  get_O7(){
//         int pitch = 3;
//         int[] C_O7 = {noteName,noteName+3,noteName+6,noteName+10};
//         for(int noteValue=1;noteValue<=4;noteValue++){
//             Note C = new Note(C_O7[noteValue-1]&13,pitch);
//             C_O7[noteValue-1] = C.getNote();
//         }
//         System.out.println("o7");
//         return C_O7;
//     }
//     public int[]  get_maj(){
//         int pitch = 3;
//         int[] C_maj = {noteName,noteName+4,noteName+7};
//         for(int noteValue=1;noteValue<=3;noteValue++){
//             Note C = new Note(C_maj[noteValue-1]%13,pitch);
//             C_maj[noteValue-1] = C.getNote();
//         }
//         System.out.println("maj");
//         return C_maj;
//     }
//     public int[]  get_min(){
//         int pitch = 3;
//         int[] C_min = {noteName,noteName+3,noteName+7};
//         for(int noteValue=1;noteValue<=3;noteValue++){
//             Note C = new Note(C_min[noteValue-1]%13,pitch);
//             C_min[noteValue-1] = C.getNote();
//         }
//         System.out.println("min");
//         return C_min;
//     }
//     public int[]  get_O(){
//         int pitch = 3;
//         int[] C_O = {noteName,noteName+3,noteName+6};
//         for(int noteValue=1;noteValue<=3;noteValue++){
//             Note C = new Note(C_O[noteValue-1]%13,pitch);
//             C_O[noteValue-1] = C.getNote();
//         }
//         System.out.println("O");
//         return C_O;
//     }
// }
import java.util.ArrayList;

class Chord{
    int noteName;
    int pitch;
    public Chord(int noteName, int pitch) {
        this.noteName = noteName;
        this.pitch = pitch;
    }
    public ArrayList<Integer> get_maj7(){
        int pitch = 3;
        ArrayList<Integer> maj7 = new ArrayList<>();
        maj7.add(noteName);
        maj7.add(noteName + 4);
        maj7.add(noteName + 7);
        maj7.add(noteName + 11);
        for(int noteValue=0;noteValue<4;noteValue++){
            Note C = new Note(maj7.get(noteValue) % 12, pitch);
            maj7.set(noteValue, C.getNote());
        }
        System.out.println("maj7");
        return maj7;
    }
    public ArrayList<Integer> get_7(){
        int pitch = 3;
        ArrayList<Integer> _7 = new ArrayList<>();
        _7.add(noteName);
        _7.add(noteName + 4);
        _7.add(noteName + 7);
        _7.add(noteName + 10);
        for(int noteValue=0;noteValue<4;noteValue++){
            Note C = new Note(_7.get(noteValue) % 12, pitch);
            _7.set(noteValue, C.getNote());
        }
        System.out.println("7");
        return _7;
    }
    public ArrayList<Integer> get_min7(){
        int pitch = 3;
        ArrayList<Integer> C_min7 = new ArrayList<>();
        C_min7.add(noteName);
        C_min7.add(noteName + 3);
        C_min7.add(noteName + 7);
        C_min7.add(noteName + 10);
        for(int noteValue=0;noteValue<4;noteValue++){
            Note C = new Note(C_min7.get(noteValue) % 12, pitch);
            C_min7.set(noteValue, C.getNote());
        }
        System.out.println("-7");
        return C_min7;
    }
    public ArrayList<Integer> get_O7(){
        int pitch = 3;
        ArrayList<Integer> C_O7 = new ArrayList<>();
        C_O7.add(noteName);
        C_O7.add(noteName + 3);
        C_O7.add(noteName + 6);
        C_O7.add(noteName + 10);
        for(int noteValue=0;noteValue<4;noteValue++){
            Note C = new Note(C_O7.get(noteValue) % 12, pitch);
            C_O7.set(noteValue, C.getNote());
        }
        System.out.println("o7");
        return C_O7;
    }
    public ArrayList<Integer> get_maj(){
        int pitch = 3;
        ArrayList<Integer> C_maj = new ArrayList<>();
        C_maj.add(noteName);
        C_maj.add(noteName + 4);
        C_maj.add(noteName + 7);
        for(int noteValue=0;noteValue<3;noteValue++){
            Note C = new Note(C_maj.get(noteValue) % 12, pitch);
            C_maj.set(noteValue, C.getNote());
        }
        System.out.println("maj");
        return C_maj;
    }
    public ArrayList<Integer> get_min(){
        int pitch = 3;
        ArrayList<Integer> C_min = new ArrayList<>();
        C_min.add(noteName);
        C_min.add(noteName + 3);
        C_min.add(noteName + 7);
        for(int noteValue=0;noteValue<3;noteValue++){
            Note C = new Note(C_min.get(noteValue) % 12, pitch);
            C_min.set(noteValue, C.getNote());
        }
        System.out.println("min");
        return C_min;
    }
    public ArrayList<Integer> get_O(){
        int pitch = 3;
        ArrayList<Integer> C_O = new ArrayList<>();
        C_O.add(noteName);
        C_O.add(noteName + 3);
        C_O.add(noteName + 6);
        for(int noteValue=0;noteValue<3;noteValue++){
            Note C = new Note(C_O.get(noteValue) % 12, pitch);
            C_O.set(noteValue, C.getNote());
        }
        System.out.println("O");
        return C_O;
    }
}
