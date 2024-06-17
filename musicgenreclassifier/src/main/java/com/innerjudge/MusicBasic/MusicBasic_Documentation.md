
# Chord Class Documentation

## Overview

The `Chord` class represents a musical chord and provides methods to create various types of chords.

### Constructor

```java
public Chord(int rootNote, int pitch)
```

- `rootNote`: An integer representing the root note of the chord.
- `pitch`: An integer representing the pitch of the chord.

### Methods

#### `getMaj7()`

```java
public ArrayList<Integer> getMaj7()
```

Returns a major 7th chord.

#### `get7()`

```java
public ArrayList<Integer> get7()
```

Returns a dominant 7th chord.

#### `getMin7()`

```java
public ArrayList<Integer> getMin7()
```

Returns a minor 7th chord.

#### `getDim7()`

```java
public ArrayList<Integer> getDim7()
```

Returns a diminished 7th chord.

#### `getMaj()`

```java
public ArrayList<Integer> getMaj()
```

Returns a major chord.

#### `getMin()`

```java
public ArrayList<Integer> getMin()
```

Returns a minor chord.

#### `getDim()`

```java
public ArrayList<Integer> getDim()
```

Returns a diminished chord.

### Private Methods

#### `createChord(int... notes)`

```java
private ArrayList<Integer> createChord(int... notes)
```

Creates a chord based on the provided notes.

- `notes`: A variable number of integers representing the notes in the chord.

---

# Note Class Documentation

## Overview

The `Note` class represents a musical note and provides methods to get the note's MIDI value.

### Constructor

```java
public Note(int noteName, int pitch)
```

- `noteName`: An integer representing the note name (0-11).
- `pitch`: An integer representing the pitch of the note.

### Methods

#### `getNote()`

```java
public int getNote()
```

Returns the MIDI value of the note based on the `noteName` and `pitch`.

- `noteName`: An integer representing the note name (0 for C, 1 for C#, ..., 11 for B).
- `pitch`: An integer representing the pitch.

Returns:
- An integer representing the MIDI value of the note.

Example:
- `Note note = new Note(0, 0); // C0`
- `int midiValue = note.getNote(); // 12`

