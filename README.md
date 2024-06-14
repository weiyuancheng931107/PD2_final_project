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

#### MetronomeWithNoPitich.java
Similar to Metronome.java, but this version likely focuses on a metronome with no pitch, providing just a percussive beat without any tonal elements.

#### MidiGenerator.java
This class is responsible for generating MIDI sequences based on given parameters such as tempo, note sequences, and rhythms. It includes methods to play and save these sequences.

#### MidiMerger.java
This class provides functionality to merge multiple MIDI files into one. It includes methods to handle conflicts and ensure the merged file maintains musical coherence.

#### ReadSheet.java
This class reads musical notations from a file and converts them into a format that can be used by other classes in the application. It includes methods to parse musical notes and rhythms from textual representations.

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

### Building the project

This project is developed and built with OpenJDK 17. The project uses Gradle and can be built by running Gradle build. With the current configuration, the build consists of compilation, unit tests, and static analysis. It is recommended to delegate the building of the project to Gradle using the provided Gradle wrapper in the IDE to ensure all dependencies are handled correctly. To build locally, run

./gradlew build

## Contributing

Contributions to this project are very welcome. If you are interested, feel free to message me (otsob) through Github. Contributing to this project happens through pull requests. For commits we try to follow basic practices (for example, see the ones by [Painsi](https://gist.github.com/robertpainsi/b632364184e70900af4ab688decf6f53)
or [Beams](https://chris.beams.io/posts/git-commit/)).

As this project is a library, the testing happens through unit tests and it is preferred that changes come accompanied by corresponding unit tests.

The coding style of this project is not very strict, but there are some [basic guidelines](CODING_CONVENTIONS.md).

Contributing to this project is supposed to be enjoyable, so behaving nicely and respectfully is appreciated. For a general guideline on code-of-conduct we follow the one outlined by [Contributor Covenant](https://www.contributor-covenant.org/version/1/4/code-of-conduct).

For pull requests target the `development` branch. The `main` branch is reserved for releases.

If you find a bug in this project or want to propose a feature, please [open an issue](https://github.com/otsob/wmn4j/issues/new/choose) with the corresponding template.

## Built With

* [Gradle](https://gradle.org)
* [Apache Commons Math](https://commons.apache.org/proper/commons-math/)
* [SLF4J](http://www.slf4j.org). When using this project, you need [to bind to a compatible logger](https://www.slf4j.org/manual.html#swapping) implementation.

## Versioning

This project is still in the alpha phase. The released versions are available on [Maven central](https://search.maven.org/artifact/org.wmn4j/wmn4j).

## Authors

* **Otso Björklund** - [otsob](https://github.com/otsob)
* **Matias Wargelin**

See also the list of [contributors](https://github.com/otsob/wmn4j/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details.

## Citation

If you use this project for research, or need a reference to cite for other reasons, please use:
@inproceedings{wmn4j2022,
author = {Björklund, Otso},
title = {{Western Music Notation for Java: A library for music notation on the JVM}},
booktitle={Extended Abstracts for the Late-Breaking Demo Session of the 23rd International Society for Music Information Retrieval Conference},
year = {2022},
}
