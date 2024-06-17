
# DefineStyle Class Documentation

## Overview

The `DefineStyle` class is responsible for initializing music style, BPM, chord map, and bar amount. It also contains a method to merge MIDI files based on the selected music style.

### Constructor

```java
public DefineStyle(int style, int bpm, Map<Integer, ArrayList<Integer>> chordMap, int barAmount)
```

- `style`: An integer representing the music style.
- `bpm`: Beats per minute.
- `chordMap`: A map where keys are integers and values are lists of integers representing chords.
- `barAmount`: The number of bars.

### Methods

#### `midiMerge()`

```java
public void midiMerge() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Merges MIDI files based on the selected music style.



# Judge Class Documentation

## Overview

The `Judge` class is responsible for judging and determining the best chords based on the given notes and beats.

### Constructor

```java
public Judge(ArrayList<Integer> notename, ArrayList<Double> beat, int meter)
```

- `notename`: A list of integers representing note names.
- `beat`: A list of doubles representing beats.
- `meter`: An integer representing the meter.

### Methods

#### `getChord()`

```java
public ArrayList<ArrayList<Integer>> getChord()
```

Returns the list of chords.

#### `sortChord()`

```java
public static LinkedHashMap<Integer, Double> sortChord(LinkedHashMap<Integer, Double> pointeOfChord)
```

Sorts the chords based on their scores.

- `pointeOfChord`: A map where keys are integers and values are doubles representing chord scores.

#### `judgement()`

```java
public Map<Integer, ArrayList<Integer>> judgement()
```

Judges and determines the best chords based on the given notes and beats. Returns a map where keys are integers and values are lists of integers representing chords.

#### `getRepeat()`

```java
public Map<Integer, ArrayList<Integer>> getRepeat(Map<Integer, ArrayList<ArrayList<Integer>>> judgements)
```

Determines the repeated chords based on the judgements. Returns a map where keys are integers and values are lists of integers representing repeated chords.

- `judgements`: A map where keys are integers and values are lists of lists of integers representing judgements.
