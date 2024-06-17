package com.musicapp;

public class OneNote {
    public double time = 0.0;
    public String type;
    public int pitch = 0;
    public int key = -1;
    public boolean isRest = false;
    public void add_Note(int key) {
        this.key = key;
    }
    public Integer get_Note() {
        return key;
    }
    public void add_time(String type) {
        this.type = type;
        switch (type) {
            case "Whole":
                time = 0.25;
                break;
            case "WholeNoteRest":
                time = 0.25;
                break;
            case "DottedHalf":
                time = 1.0/3.0;
                break;
            case "Half":
                time = 0.5;
                break;
            case "HalfNoteRest":
                time = 0.5;
                break;
            case "Quarter":
                time = 1;
                break;
            case "QuarterRest":
                time = 1;
                break;
            case "DottedQuarter":
                time = 2.0/3.0;
                break;
            case "DoubleDottedQuarter":
                time = 4.0/7.0;
                break;
            case "Eighth":
                time = 2;
                break;
            case "DottedEighth":
                time = 4.0/3.0;
                break;
            case "EighthRest":
                time = 2;
                break;
            case "Sixteenth":
                time = 4;
                break;
            case "SixteenthRest":
                time = 4;
                break;
            case "Triple":
                time = 3;
                break;
            case "TripletRest":
                time = 3;
                break;
            case "AugTriple":
                time = 1.5;
                break;
            case "AugTripletRest":
                time = 1.5;
                break;
            case "Quintuplet":
                time = 5;
                break;
            case "QuintupletRest":
                time = 5;
                break;
            case "ProQuintuplet":
                time = 2.5;
                break;
            case "ProQuintupletRest":
                time = 2.5;
                break;
        }
    }
    public Double get_time() {
        return time;
    }
    public String get_type() {
        return type;
    }
    public void add_Pitch(int pitch) {
        this.pitch = pitch;
    }
    public Integer get_Pitch() {
        return pitch;
    }

    public void set_IsRest(boolean isRest) {
        this.isRest = isRest;
    }
    public boolean get_IsRest() {
        return isRest;
    }
}
