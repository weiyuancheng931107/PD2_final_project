# BossaNova Class Documentation

## Overview

The `BossaNova` class represents the Bossa Nova music genre and implements the `InnerBossaNova` interface. It generates drum, piano, and bass tracks for a Bossa Nova rhythm.

### Interface: InnerBossaNova

This interface defines the methods required for a Bossa Nova rhythm section.


#### Fields

- `Map<Integer, ArrayList<Integer>> chordHashMap` - Stores chord progressions for the melody.
- `private int bpm` - Beats per minute for the rhythm.
- `private int velocity` - Volume for the notes.
- `public ArrayList<Integer> pause` - Placeholder for pauses in the rhythm.
- `private int barAmount` - Total number of bars in the rhythm.

#### Constructor

```java
public BossaNova(int bpm, int velocity, Map<Integer, ArrayList<Integer>> chordHashMap, int barAmount) throws InvalidMidiDataException, MidiUnavailableException
```

- `bpm - Beats per minute for the rhythm.
- `velocity - Volume for the notes.
- `chordHashMap - Chord progressions for the melody.
- `barAmount - Number of bars in the rhythm.

#### Methods

##### `drum()`

```java
void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the drum track for the Bossa Nova rhythm.

##### `piano()`

```java
void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the piano track for the Bossa Nova rhythm.

##### `bass()`

```java
void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the bass track for the Bossa Nova rhythm.

##### `groupAndFilter(Map<Integer, ArrayList<Integer>> input)`

```java
static Map<Integer, ArrayList<Integer>> groupAndFilter(Map<Integer, ArrayList<Integer>> input)
```

Groups and filters the chords based on beat groups.

##### `rand(int min, int max, Random random)`

```java
static int rand(int min, int max, Random random)
```

Generates a random integer within a specified range.

##### `sortAscending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortAscending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in ascending order.

##### `sortDescending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortDescending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in descending order.

---

# Funk Class Documentation

## Overview

The `Funk` class represents the Funk music genre and implements the `InnerFunk` interface. It generates drum, guitar, bass, and piano tracks for a Funk rhythm.

### Interface: InnerFunk

This interface defines the methods required for a Funk rhythm section.


#### Fields

- `private int bpm` - Beats per minute for the rhythm.
- `private int velocity` - Volume for the notes.
- `private int barAmount` - Total number of bars in the rhythm.
- `public Map<Integer, ArrayList<Integer>> chordHashMap` - Stores chord progressions for the melody.
- `public ArrayList<Integer> pause` - Placeholder for pauses in the rhythm.

#### Constructor

```java
public Funk(int bpm, int velocity, Map<Integer, ArrayList<Integer>> chordHashMap, int barAmount) throws InvalidMidiDataException, MidiUnavailableException
```

- `bpm - Beats per minute for the rhythm.
- `velocity - Volume for the notes.
- `chordHashMap - Chord progressions for the melody.
- `barAmount - Number of bars in the rhythm.

#### Methods

##### `drums()`

```java
void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException`
```

Generates the drum track for the Funk rhythm.

##### `guitar()`

```java
void guitar() throws InvalidMidiDataException, MidiUnavailableException, IOException`
```

Generates the guitar track for the Funk rhythm.

##### `bass()`

```java
void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException`
```

Generates the bass track for the Funk rhythm.

##### `piano()`

```java
void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException`
```

Generates the piano track for the Funk rhythm.

##### `groupAndFilter(Map<Integer, ArrayList<Integer>> input)`

```java
static Map<Integer, ArrayList<Integer>> groupAndFilter(Map<Integer, ArrayList<Integer>> input)
```
Groups and filters the chords based on beat groups.

##### `rand(int min, int max, Random random)`

```java
static int rand(int min, int max, Random random)
```

Generates a random integer within a specified range.

##### `sortAscending(ArrayList<ArrayList<Integer>> listOfLists)`

```java 
static void sortAscending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in ascending order.

##### `sortDescending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortDescending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in descending order.

---

# Jazz Class Documentation

## Overview

The `Jazz` class represents the Jazz music genre and implements the `InnerJazz` interface. It generates drum, piano, and bass tracks for a Jazz rhythm.

### Interface: InnerJazz

This interface defines the methods required for a Jazz rhythm section.


#### Fields

- `private Map<Integer, ArrayList<Integer>> chordHashMap` - Stores chord progressions for the melody.
- `private int bpm` - Beats per minute for the rhythm.
- `private int velocity` - Volume for the notes.
- `private ArrayList<Integer> pause` - Placeholder for pauses in the rhythm.
- `private int barAmount` - Total number of bars in the rhythm.

#### Constructor

```java
public Jazz(int bpm, int velocity, Map<Integer, ArrayList<Integer>> chordHashMap, int barAmount) throws InvalidMidiDataException, MidiUnavailableException
```

- `bpm - Beats per minute for the rhythm.
- `velocity - Volume for the notes.
- `chordHashMap - Chord progressions for the melody.
- `barAmount - Number of bars in the rhythm.

#### Methods

##### `drums()`

```java
void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the drum track for the Jazz rhythm.

##### `piano()`

```java
void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the piano track for the Jazz rhythm.

##### `bass()`

```java
void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the bass track for the Jazz rhythm.

##### `groupAndFilter(Map<Integer, ArrayList<Integer>> input)`

```java
static Map<Integer, ArrayList<Integer>> groupAndFilter(Map<Integer, ArrayList<Integer>> input)
```

Groups and filters the chords based on beat groups.

##### `rand(int min, int max, Random random)`

```java
static int rand(int min, int max, Random random)
```

Generates a random integer within a specified range.

##### `sortAscending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortAscending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in ascending order.

##### `sortDescending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortDescending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in descending order.

---

# Reggae Class Documentation

## Overview

The `Reggae` class represents the Reggae music genre and implements the `InnerReggae` interface. It generates drum, guitar, bass, and piano tracks for a Reggae rhythm.

### Interface: InnerReggae

This interface defines the methods required for a Reggae rhythm section.


#### Fields

- `private Map<Integer, ArrayList<Integer>> chordHashMap` - Stores chord progressions for the melody.
- `private int bpm` - Beats per minute for the rhythm.
- `private int velocity` - Volume for the notes.
- `private ArrayList<Integer> pause` - Placeholder for pauses in the rhythm.
- `private int barAmount` - Total number of bars in the rhythm.

#### Constructor

```java
public Reggae(int bpm, int velocity, Map<Integer, ArrayList<Integer>> chordHashMap, int barAmount) throws InvalidMidiDataException, MidiUnavailableException
```
- `bpm - Beats per minute for the rhythm.
- `velocity - Volume for the notes.
- `chordHashMap - Chord progressions for the melody.
- `barAmount - Number of bars in the rhythm.


#### Methods

##### `drums()`

```java
void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException
```
Generates the drum track for the Reggae rhythm.

##### `guitar()`

```java
void guitar() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the guitar track for the Reggae rhythm.

##### `bass()`

```java
void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the bass track for the Reggae rhythm.

##### `piano()`

```java
void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the piano track for the Reggae rhythm.

##### `groupAndFilter(Map<Integer, ArrayList<Integer>> input)`

```java
static Map<Integer, ArrayList<Integer>> groupAndFilter(Map<Integer, ArrayList<Integer>> input)
```

Groups and filters the chords based on beat groups.

##### `rand(int min, int max, Random random)`

```java
static int rand(int min, int max, Random random)
```
Generates a random integer within a specified range.

##### `sortAscending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortAscending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in ascending order.

##### `sortDescending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortDescending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in descending order.

---

# RnB Class Documentation

## Overview

The `RnB` class represents the RnB music genre and implements the `InnerRnB` interface. It generates drum, guitar, bass, and piano tracks for an RnB rhythm.

### Interface: InnerRnB

This interface defines the methods required for an RnB rhythm section.


#### Fields

- `private Map<Integer, ArrayList<Integer>> chordHashMap` - Stores chord progressions for the melody.
- `private int bpm` - Beats per minute for the rhythm.
- `private int velocity` - Volume for the notes.
- `private ArrayList<Integer> pause` - Placeholder for pauses in the rhythm.
- `private int barAmount` - Total number of bars in the rhythm.

#### Constructor

```java
public RnB(int bpm, int velocity, Map<Integer, ArrayList<Integer>> chordHashMap, int barAmount) throws InvalidMidiDataException, MidiUnavailableException
```
- `bpm - Beats per minute for the rhythm.
- `velocity - Volume for the notes.
- `chordHashMap - Chord progressions for the melody.
- `barAmount - Number of bars in the rhythm.

#### Methods

##### `drums()`

```java
void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException
```
Generates the drum track for the RnB rhythm.

##### `guitar()`

```java
void guitar() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the guitar track for the RnB rhythm.

##### `bass()`

```java
void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the bass track for the RnB rhythm.

##### `piano()`

```java
void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the piano track for the RnB rhythm.

##### `groupAndFilter(Map<Integer, ArrayList<Integer>> input)`

```java
static Map<Integer, ArrayList<Integer>> groupAndFilter(Map<Integer, ArrayList<Integer>> input)
```

Groups and filters the chords based on beat groups.

##### `rand(int min, int max, Random random)`

```java
static int rand(int min, int max, Random random)
```
Generates a random integer within a specified range.

##### `sortAscending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortAscending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in ascending order.

##### `sortDescending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortDescending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in descending order.

---

# Rock Class Documentation

## Overview

The `Rock` class represents the RnB music genre and implements the `InnerRock` interface. It generates drum, guitar, bass, and piano tracks for an Rock rhythm.

### Interface: InnerRock

This interface defines the methods required for an Rock rhythm section.


#### Fields

- `private Map<Integer, ArrayList<Integer>> chordHashMap` - Stores chord progressions for the melody.
- `private int bpm` - Beats per minute for the rhythm.
- `private int velocity` - Volume for the notes.
- `private ArrayList<Integer> pause` - Placeholder for pauses in the rhythm.
- `private int barAmount` - Total number of bars in the rhythm.

#### Constructor

```java
public Rock(int bpm, int velocity, Map<Integer, ArrayList<Integer>> chordHashMap, int barAmount) throws InvalidMidiDataException, MidiUnavailableException
```
- `bpm - Beats per minute for the rhythm.
- `velocity - Volume for the notes.
- `chordHashMap - Chord progressions for the melody.
- `barAmount - Number of bars in the rhythm.

#### Methods

##### `drums()`

```java
void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException
```
Generates the drum track for the Rock rhythm.

##### `guitar()`

```java
void guitar() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the guitar track for the Rock rhythm.

##### `bass()`

```java
void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the bass track for the Rock rhythm.

##### `piano()`

```java
void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the piano track for the Rock rhythm.

##### `groupAndFilter(Map<Integer, ArrayList<Integer>> input)`

```java
static Map<Integer, ArrayList<Integer>> groupAndFilter(Map<Integer, ArrayList<Integer>> input)
```

Groups and filters the chords based on beat groups.

##### `rand(int min, int max, Random random)`

```java
static int rand(int min, int max, Random random)
```
Generates a random integer within a specified range.

##### `sortAscending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortAscending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in ascending order.

##### `sortDescending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortDescending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in descending order.

---

# Soul Class Documentation

## Overview

The `Soul` class represents the Soul music genre and implements the `InnerSoul` interface. It generates drum, guitar, bass, and piano tracks for an Soul rhythm.

### Interface: InnerRnB

This interface defines the methods required for an Soul rhythm section.


#### Fields

- `private Map<Integer, ArrayList<Integer>> chordHashMap` - Stores chord progressions for the melody.
- `private int bpm` - Beats per minute for the rhythm.
- `private int velocity` - Volume for the notes.
- `private ArrayList<Integer> pause` - Placeholder for pauses in the rhythm.
- `private int barAmount` - Total number of bars in the rhythm.

#### Constructor

```java
public Soul(int bpm, int velocity, Map<Integer, ArrayList<Integer>> chordHashMap, int barAmount) throws InvalidMidiDataException, MidiUnavailableException
```
- `bpm - Beats per minute for the rhythm.
- `velocity - Volume for the notes.
- `chordHashMap - Chord progressions for the melody.
- `barAmount - Number of bars in the rhythm.

#### Methods

##### `drums()`

```java
void drums() throws InvalidMidiDataException, MidiUnavailableException, IOException
```
Generates the drum track for the Soul rhythm.

##### `guitar()`

```java
void guitar() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the guitar track for the Soul rhythm.

##### `bass()`

```java
void bass() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the bass track for the Soul rhythm.

##### `piano()`

```java
void piano() throws InvalidMidiDataException, MidiUnavailableException, IOException
```

Generates the piano track for the Soul rhythm.

##### `groupAndFilter(Map<Integer, ArrayList<Integer>> input)`

```java
static Map<Integer, ArrayList<Integer>> groupAndFilter(Map<Integer, ArrayList<Integer>> input)
```

Groups and filters the chords based on beat groups.

##### `rand(int min, int max, Random random)`

```java
static int rand(int min, int max, Random random)
```
Generates a random integer within a specified range.

##### `sortAscending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortAscending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in ascending order.

##### `sortDescending(ArrayList<ArrayList<Integer>> listOfLists)`

```java
static void sortDescending(ArrayList<ArrayList<Integer>> listOfLists)
```

Sorts the inner lists of a list of lists in descending order.

---

# Drumsound Class Documentation

This class contains programs of every piece in the drum kit.

---

