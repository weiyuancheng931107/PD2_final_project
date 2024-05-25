

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.midi.*;

class Metronome1 {
    private Sequence sequence;
    private Track track;
    int bpm;
    static int program;
    ArrayList<Integer> notename;
    int pitch;
    int velocity;
    ArrayList<Integer> beat;
    int channel;
    int tick = 0;
    private Sequencer sequencer;

    public Metronome1(int bpm, int program, ArrayList<Integer> notename, int pitch, int velocity, ArrayList<Double> beat, int channel) throws InvalidMidiDataException, MidiUnavailableException {
        this.bpm = bpm;
        Metronome.program = program;
        this.notename = new ArrayList<>();
        for (int i = 0; i < notename.size(); i++) {
            this.notename = notename;
        }
        this.pitch = pitch;
        this.velocity = velocity;
        this.channel = channel;
        this.beat = new ArrayList<>();
        for (int i = 0; i < beat.size(); i++) {
            this.beat.add((int) (120 / beat.get(i)));
        }
        this.sequence = new Sequence(Sequence.PPQ, bpm);
        this.track = (this.sequence).createTrack();
        sequencer = MidiSystem.getSequencer(); // Get sequencer
        sequencer.open(); // Open sequencer
        
        // Add the Program Change message to set the instrument to a pipe organ
        track.add(createProgramChangeMessage());
    }

    public int getBpm() {
        return bpm;
    }

    public int getProgram() {
        return program;
    }

    public ArrayList<Integer> getNotename() {
        return notename;
    }

    public int getPitch() {
        return pitch;
    }

    public int getVelocity() {
        return velocity;
    }

    public ArrayList<Integer> getBeat() {
        return this.beat;
    }

    public int getTick() {
        return tick;
    }

    public void rhythmchord() throws InvalidMidiDataException {
        // Add MIDI events: note ON
        for (int j = 0; j < beat.size(); j++) {
                    if (notename.get(j) == -1) {
                        tick+=this.beat.get(j);
                        continue;
                    }
                    ShortMessage noteOn = new ShortMessage();
                    noteOn.setMessage(ShortMessage.NOTE_ON, channel, notename.get(j), velocity);
                    track.add(new MidiEvent(noteOn, tick));
                
                // Add MIDI events: note OFF

                if (notename.get(j) == -1) {
                    continue;
                }
                ShortMessage noteOff = new ShortMessage();
                noteOff.setMessage(ShortMessage.NOTE_OFF, channel, notename.get(j), velocity);
                track.add(new MidiEvent(noteOff, tick + this.beat.get(j)));
                tick+=this.beat.get(j);
            }
        }
    public static MidiEvent createProgramChangeMessage() throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        // Set to pipe organ sound (program number 19 in General MIDI)
        message.setMessage(ShortMessage.PROGRAM_CHANGE, 0, program, 0);
        return new MidiEvent(message, 0);
    }

    public void writeToFile(String filename) throws IOException {
        File outputFile = new File(filename + ".mid");
        MidiSystem.write(sequence, 1, outputFile);
        // Ensure to close the sequencer when it's no longer needed
        sequencer.close();
    }

    public Track getTrack() {
        return track;
    }
}
