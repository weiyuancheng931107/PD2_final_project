
# Metronome Class Documentation

## Overview

The `Metronome` class is responsible for creating a sequence of MIDI events and processing MIDI file writting task.

### Constructor

```java
public Metronome(int bpm, int program, ArrayList<ArrayList<Integer>> noteName, int pitch, int velocity, ArrayList<Double> beat, int channel) throws InvalidMidiDataException, MidiUnavailableException
```

- `bpm`: Beats per minute.
- `program`: MIDI program number.
- `noteName`: A list of lists representing the note names.
- `pitch`: Pitch of the notes.
- `velocity`: Velocity of the notes.
- `beat`: A list of doubles representing the beat durations.
- `channel`: MIDI channel.

### Methods

#### `getBpm()`

```java
public int getBpm()
```

Returns the BPM.

#### `getProgram()`

```java
public int getProgram()
```

Returns the MIDI program number.

#### `getNoteName()`

```java
public ArrayList<ArrayList<Integer>> getNoteName()
```

Returns the note names.

#### `getPitch()`

```java
public int getPitch()
```

Returns the pitch.

#### `getVelocity()`

```java
public int getVelocity()
```

Returns the velocity.

#### `getBeat()`

```java
public ArrayList<Integer> getBeat()
```

Returns the beat durations.

#### `getTick()`

```java
public int getTick()
```

Returns the current tick.

#### `rhythmChord()`

```java
public void rhythmChord() throws InvalidMidiDataException
```

Creates the rhythm chord MIDI events.

#### `createProgramChangeMessage()`

```java
public static MidiEvent createProgramChangeMessage(int tick) throws InvalidMidiDataException
```

Creates a program change MIDI event.

#### `writeToFile()`

```java
public void writeToFile(String filename) throws IOException
```

Writes the sequence to a MIDI file.

#### `getTrack()`

```java
public Track getTrack()
```

Returns the track.

---

# MetronomeWithNoPitch Class Documentation

## Overview

The `MetronomeWithNoPitch` class is responsible for creating a sequence of MIDI events but without specifying pitch.

### Constructor

```java
public MetronomeWithNoPitch(int bpm, int program, ArrayList<ArrayList<Integer>> noteSequence, int pitch, int velocity, ArrayList<Double> beats, int channel) throws InvalidMidiDataException, MidiUnavailableException
```

- `bpm`: Beats per minute.
- `program`: MIDI program number.
- `noteSequence`: A list of lists representing the note sequence.
- `pitch`: Pitch of the notes.
- `velocity`: Velocity of the notes.
- `beats`: A list of doubles representing the beat durations.
- `channel`: MIDI channel.

### Methods

#### `playRhythm()`

```java
public void playRhythm() throws InvalidMidiDataException
```

Creates the rhythm MIDI events.

#### `saveToFile()`

```java
public void saveToFile(String filename) throws IOException
```

Writes the sequence to a MIDI file.

---

# MidiGenerator Class Documentation

## Overview

The `MidiGenerator` class is responsible for generating MIDI sequences.

### Constructor

```java
public MidiGenerator(int bpm, int program, ArrayList<Integer> noteSequence, int pitch, int velocity, ArrayList<Double> beats, int channel) throws InvalidMidiDataException, MidiUnavailableException
```

- `bpm`: Beats per minute.
- `program`: MIDI program number.
- `noteSequence`: A list representing the note sequence.
- `pitch`: Pitch of the notes.
- `velocity`: Velocity of the notes.
- `beats`: A list of doubles representing the beat durations.
- `channel`: MIDI channel.

### Methods

#### `playRhythm()`

```java
public void playRhythm() throws InvalidMidiDataException
```

Creates the rhythm MIDI events.

#### `saveToFile()`

```java
public void saveToFile(String filename) throws IOException
```

Writes the sequence to a MIDI file.

---

# MidiMerger Class Documentation

## Overview

The `MidiMerger` class is responsible for merging multiple MIDI files into a single file.

### Methods

#### `mergeMidiFiles()`

```java
public static void mergeMidiFiles(String[] midiFiles, String outputFile) throws IOException, InvalidMidiDataException
```

Merges multiple MIDI files into a single file.

- `midiFiles`: An array of strings representing the file paths of the MIDI files to merge.
- `outputFile`: The file path of the output merged MIDI file.

#### `addSequenceToMerged()`

```java
private static void addSequenceToMerged(Sequence sequence, Sequence mergedSequence) throws InvalidMidiDataException
```

Adds a MIDI sequence to the merged sequence.

- `sequence`: The sequence to add.
- `mergedSequence`: The merged sequence.
