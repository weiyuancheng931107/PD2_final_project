package com.innerjudge.MusicGenerator;
import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MidiMerger {
    public static void mergeMidiFiles(String[] midiFiles, String outputFile) throws IOException, InvalidMidiDataException {
        Sequence mergedSequence = null;

        for (String midiFile : midiFiles) {
            Sequence sequence = MidiSystem.getSequence(new File(midiFile));
            if (mergedSequence == null) {
                mergedSequence = new Sequence(sequence.getDivisionType(), sequence.getResolution());
            }
            addSequenceToMerged(sequence, mergedSequence);
        }

        if (mergedSequence != null) {
            MidiSystem.write(mergedSequence, 1, new File(outputFile));
        }
    }

    private static void addSequenceToMerged(Sequence sequence, Sequence mergedSequence) throws InvalidMidiDataException {
        for (Track track : sequence.getTracks()) {
            Track mergedTrack = mergedSequence.createTrack();
            for (int i = 0; i < track.size(); i++) {
                MidiEvent event = track.get(i);
                MidiMessage message = event.getMessage();
                if (message instanceof MetaMessage) {
                    MetaMessage metaMessage = (MetaMessage) message;
                    if (metaMessage.getType() == 0x2F) {
                        continue;  // Ignore end of track meta messages
                    }
                }
                mergedTrack.add(event);
            }
        }
    }
}
