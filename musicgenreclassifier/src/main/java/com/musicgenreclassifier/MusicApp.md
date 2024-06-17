
# MusicApp Class Documentation

## Overview

someWord

### Constructor

```java
public MusicApp()
```

### Methods

#### `checkEnablePianoKeys()`

```java
public void checkEnablePianoKeys()
```

explanin the method

#### `formRest()`

```java
private void formRest()
```

explanin the method

#### `formNote()`

```java
public void formNote()
```

explanin the method

#### `checkLengthAndSoOn()`

```java
private void checkLengthAndSoOn()
```

explanin the method

#### `checkFinished()`

```java
private void checkFinished()
```

explanin the method

#### `main(String[] args)`

```java
public static void main(String[] args)
```

explanin the method

#### `get_CurrentNote()`

```java
public OneNote get_CurrentNote()
```

explanin the method

#### `getNoteList()`

```java
public ArrayList<OneNote> getNoteList()
```

explanin the method

#### `isOneBar()`

```java
public boolean isOneBar()
```

explanin the method

#### `getIndex()`

```java
public int getIndex()
```

explanin the method

---

# MusicNotation Class Documentation

## Overview

### Constructor

```java
public MusicNotation()
```

---

# MusicNotations Class Documentation

## Overview

### Constructor

```java
public MusicNotations(MusicApp musicApp)
```

---

# OneNote Class Documentation

## Overview

The `OneNote` class is used to store information of a note, like key, ptich, time, etc.

### Methods

#### `add_Note(int key)`

```java
public void add_Note(int key)
```

Sets the note key.

#### `get_Note()`

```java
public Integer get_Note()
```

Returns the note key.

#### `add_time(String type)`

```java
public void add_time(String type)
```

Sets the note type and trnsforms to the note time.

#### `get_time()`

```java
public Double get_time()
```

Returns the note time.

#### `get_type()`

```java
public String get_type()
```

Returns the note type.

#### `add_Pitch(int pitch)`

```java
public void add_Pitch(int pitch)
```

Sets the note pitch.

#### `get_Pitch()`

```java
public Integer get_Pitch()
```

Returns the note pitch.

#### `set_IsRest(boolean isRest)`

```java
public void set_IsRest(boolean isRest)
```

Sets whether the code is a rest.

#### get_IsRest()`

```java
public boolean get_IsRest()
```

Returns whether the code is a rest.

---

# PianoPanel Class Documentation

## Overview

### Constructor

```java
public PianoPanel(MusicApp musicApp)
```
