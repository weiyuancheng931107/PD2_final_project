package com.innerjudge.MusicBasic;

public class Note {
    int noteName;
    int pitch;
    public Note(int noteName,int pitch){
        this.noteName = noteName;
        this.pitch = pitch;
    }
    public int getNote(){
        switch (noteName) {
            case 0://"C"
                return 12*pitch+12;
            case 1://"C#":
                return 12*pitch+13;
            case 2://"D":
                return 12*pitch+14;
            case 3://"D#":
                return 12*pitch+15;
            case 4://"E":
                return 12*pitch+16;
            case 5://"F":
                return 12*pitch+17;
            case 6://"F#":
                return 12*pitch+18;
            case 7://"G":
                return 12*pitch+19;
            case 8://"G#":
                return 12*pitch+20;
            case 9://"A":
                return 12*pitch+21;
            case 10://"A#":
                return 12*pitch+22;
            case 11://"B":
                return 12*pitch+23;
            default:
                return -1;
        }
        
    }

}
