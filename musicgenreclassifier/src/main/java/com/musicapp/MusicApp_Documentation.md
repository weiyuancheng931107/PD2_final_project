
# MusicApp Class Documentation

## Overview

The `MusicApp` class is responsible for generate a GUI(Graphical User Interface) for user to notate a sheet.

### Constructor

```java
public MusicApp()
```

Creates the interface and generates several button panels.

### Methods

#### `checkEnablePianoKeys()`

```java
public void checkEnablePianoKeys()
```

Checks the keyboard is enabled or not.

#### `checkFinished()`

```java
public void checkFinished()
```

Checks whether the sheet exceeds four bars and can terminate the interface.

#### `removeLastNote()`

```java
private void removeLastNote()
```

Removes last note so it can go backward.

#### `updateNotationPanel()`

```java
private void updateNotationPanel()
```

Repaints the sheet when exceeding four bars.

#### `updateTotal()`

```java
private void updateTotal()
```

Updates total note time in four bars.

#### `addRestNote()`

```java
private void addRestNote()
```

paints rest to the sheet.

#### `addRestNote()`

```java
private void addRestNote()
```

paints rest to the sheet.

#### `main(String[] args)`

```java
public static void main(String[] args)
```

Creates a frame and execute the program.

---

# MusicNotation Class Documentation

## Overview

The `MusicNotation` class draws four lines of sheet with treble clef notation.

### Constructor

```java
public MusicNotation()
```

Initializes the notation panel with sheet and treble clef notation.

### Methods

#### `paintComponent(Graphics g)`

```java
@Override
protected void paintComponent(Graphics g)
```

Perform custom painting in a `JComponent`, such as `JPanel`.

#### `drawStaff(Graphics2D g2d)`

```java
private void drawStaff(Graphics2D g2d)
```

Draws sheet and treble clef notation.

- `g2d`: java class which supports powerful painting functions.

#### `addNotation(int x, int y, int width, int height)`

```java
private void addNotation(int x, int y, int width, int height)
```

Adds a image of treble clef notation to the sheet.

- `x`: start x position of the image.
- `y`: start y position of the image.
- `width`: width of the image.
- `height`: height of the image.

---

# MusicNotations Class Documentation

## Overview

The `MusicNotations` class draws notes and rests when they are added or deleted.

### Constructor

```java
public MusicNotations(MusicApp musicApp)
```

- `musicApp`: a reference to `MusicApp` instance and can receive note information from there.

### Methods

#### `paintComponent(Graphics g)`

```java
@Override
protected void paintComponent(Graphics g)
```

Draws the musical notation including notes and rests.

#### `drawNoteByType(Graphics2D g2d, int x, int y, String noteType)`

```java
private void drawNoteByType(Graphics2D g2d, int x, int y, String noteType)
```

Draws notes based on the note type.

- `noteType`: Type of a note such as whole, quarter rest.

#### `public void drawSharp(Graphics2D g2d, int x, int y)`

```java
public void drawSharp(Graphics2D g2d, int x, int y)
```

Draws a sharp sign.

#### `editImage()`

```java
private void editImage()
```

Edits and resized images of rest notations.

---

# OneNote Class Documentation

## Overview

The `OneNote` class is used to store information of a note, such as key, ptich, time, etc.

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

The `PianoPanel` class generates a keyboard for user to input the note key.

### Constructor

```java
public PianoPanel(MusicApp musicApp)
```

- `musicApp`: a reference to class `MusicApp` instance and pass key information to there.

### Methods

#### `enablePianoKeys(boolean enabled)`

```java
public void enablePianoKeys(boolean enabled)
```

Enables or disenables the keyboard.

- `enabled`: A boolean variable determining whether the keyboard is enabled.

#### `actionPerformed(ActionEvent e)`

```java
@Override
public void actionPerformed(ActionEvent e)
```

Passes the note key when corresponding piano key is clicked.

- `e`: A variable handles actions performed by users on GUI components.
