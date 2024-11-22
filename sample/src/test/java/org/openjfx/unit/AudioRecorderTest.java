package org.openjfx.unit;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.models.AudioModel;

public class AudioRecorderTest {
    private AudioModel audioModel;

    @BeforeEach
    void setUp() {
        this.audioModel = new AudioModel();
    }

    @Test
    void testStartRecordingCreatesFile() {
        try {
            this.audioModel.startRecording();
            File file = new File("recording.wav");
            assertTrue(file.exists(), "The audio file should exist after recording starts.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred while starting the recording: " + e.getMessage());
        }
    }

    @Test
    void testStopRecordingStopsRecording() {
        try {
            this.audioModel.startRecording();
            this.audioModel.stopRecording();
            assertFalse(audioModel.isRecording(), "Recording should stop when stopRecording is called.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred while stopping the recording: " + e.getMessage());
        }
    }
}
