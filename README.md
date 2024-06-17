# Java Music Application

Java Music Application is a Java-based application designed for various music-related functionalities, including creating, processing, and manipulating MIDI files, generating rhythms, and defining musical styles.

## The structure of the project

The structure and functionalities of the project are described below. There are a few architectural design principles in this project:

* All classes follow the principles of object-oriented programming.
* Builder classes are used for creating complex objects.
* The project makes extensive use of Java standard libraries.

### Files and Their Functions

#### Note.java
This class is responsible for classifying the note.

#### Chord.java
This class handles chord-related operations, such as creating chords. It includes methods to define chord structures and to generate chord sequences.

#### DefineStyle.java
This class allows users to define and apply different musical styles to MIDI sequences. It includes functionality to merge MIDI files according to specified styles.

#### Judge.java
This class evaluates musical sequences by analyzing their structure and rhythm. It is used to ensure the sequences adhere to certain musical standards or styles.

#### Metronome.java
It includes methods to set the tempo and possibly to customize the sound of the beat.

#### MetronomeWithNoPitch.java
Similar to Metronome.java, but this version likely focuses on a metronome with no pitch, providing just a percussive beat without any tonal elements.

#### MidiGenerator.java
This class is responsible for generating MIDI sequences based on given parameters such as tempo, note sequences, and rhythms. It includes methods to play and save these sequences.

#### MidiMerger.java
This class provides functionality to merge multiple MIDI files into one. It includes methods to handle conflicts and ensure the merged file maintains musical coherence.

#### ReadSheet.java
This class reads musical notations from a file and converts them into a format that can be used by other classes in the application. It includes methods to parse musical notes and rhythms from textual representations.

#### Drumsound.java
This class contains programs of every piece in the drum kit.

#### Funk.java
This class is responsible for generating Funk rhythms. It includes methods for defining the specific patterns and time signatures characteristic of Funk music.

#### Jazz.java
This class is responsible for generating Jazz rhythms. It includes methods for defining the specific patterns and time signatures characteristic of Jazz music.

#### BossaNova.java
This class is responsible for generating Bossa Nova rhythms. It includes methods for defining the specific patterns and time signatures characteristic of Bossa Nova music.

#### Reggae.java
This class is responsible for generating Reggae rhythms. It includes methods for defining the specific patterns and time signatures characteristic of Reggae music.

#### RnB.java
This class is responsible for generating RnB rhythms. It includes methods for defining the specific patterns and time signatures characteristic of RnB music.

#### Rock.java
This class is responsible for generating Rock rhythms. It includes methods for defining the specific patterns and time signatures characteristic of Rock music.

#### Soul.java
This class is responsible for generating Soul rhythms. It includes methods for defining the specific patterns and time signatures characteristic of Soul music.

#### MusicApp.java
This class serves as the main entry point of the application. It initializes the application and manages the user interface components.

#### MusicNotation.java
This class handles the creation, manipulation, and display of musical notations within the application.

#### MusicNotations.java
This class extends the functionality of MusicNotation.java to support multiple notations simultaneously, allowing for more complex compositions.

#### Notes.java
This class provides the core functionality for representing and manipulating individual musical notes. It includes methods for note properties such as pitch, duration, and intensity.

#### PianoPanel.java
This class is responsible for creating a visual representation of a piano keyboard. It allows users to interact with the piano and visualize the notes being played.

### Building the project
To build the project, use your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse) to import the project files. Ensure you have the necessary Java Development Kit (JDK) installed. Follow your IDE's instructions to compile and run the project.

## Contributing
Contributions to the Java Music Application are welcome. To contribute, please fork the repository, make your changes, and submit a pull request. Ensure that your code follows the project's coding standards and includes appropriate tests.

## Built With
Java Standard Libraries
MIDI Libraries for Java (e.g., javax.sound.midi)

## Versioning
We use Semantic Versioning for versioning. For the [versions](https://semver.org/) available, see the tags on this repository.

## Authors

* F74121050 鄭爲元 [gmail](f74121050@gs.ncku.edu.tw)
* F74122187 張瓅云 [gmail](christinechang931023@gmail.com)
* F74122014 黃禹璇 [gmail](ankeng40426@gmail.com)
* F74122090 黃偉倫 [gmail]()
