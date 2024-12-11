package org.openjfx.models;

import javax.sound.sampled.*;
import java.io.File;

public class AudioModel {
    private TargetDataLine line;
    private final AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    private final File audioFile = new File("recording.wav");
    private boolean isRecording = false;

    public AudioModel() {
    }

    public AudioModel(TargetDataLine line) {
        this.line = line;
    }

    public void startRecording() throws LineUnavailableException {
        if (line == null) {
            throw new LineUnavailableException("TargetDataLine is null");
        }

        AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
        line.open(format); // Initialize with format
        line.start();

        isRecording = true;

        Thread recordingThread = new Thread(() -> {
            try (AudioInputStream audioStream = new AudioInputStream(line)) {
                AudioSystem.write(audioStream, fileType, audioFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        recordingThread.start();
    }

    public void stopRecording() {
        if (line != null) {
            line.stop();
            line.close();
            isRecording = false;
        }
    }

    public boolean isRecording() {
        return isRecording;
    }
}