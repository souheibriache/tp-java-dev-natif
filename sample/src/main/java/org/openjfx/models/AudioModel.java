package org.openjfx.models;

import javax.sound.sampled.*;
import java.io.File;

public class AudioModel {
    private TargetDataLine line;
    private final AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    private final File audioFile = new File("recording.wav");
    private boolean isRecording = false;

    public void startRecording() throws LineUnavailableException {
        AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

        isRecording = true;

        Thread recordingThread = new Thread(() -> {
            try {
                AudioSystem.write(new AudioInputStream(line), fileType, audioFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        recordingThread.start();
    }

    public void stopRecording() {
        isRecording = false;
        line.stop();
        line.close();
    }

    public boolean isRecording() {
        return isRecording;
    }
}