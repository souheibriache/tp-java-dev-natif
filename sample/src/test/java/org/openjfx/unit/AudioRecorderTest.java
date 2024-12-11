package org.openjfx.unit;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.openjfx.models.AudioModel;

public class AudioRecorderTest {
    private AudioModel audioModel;
    private TargetDataLine mockLine;

    @BeforeEach
    void setUp() {
        mockLine = mock(TargetDataLine.class);
        audioModel = new AudioModel(mockLine);
    }

    @Test
    void testStartRecordingCreatesFile() {
        try {
            // Argument captor for AudioFormat
            ArgumentCaptor<AudioFormat> captor = ArgumentCaptor.forClass(AudioFormat.class);

            // Mock behavior for TargetDataLine
            doNothing().when(mockLine).open(captor.capture());
            doNothing().when(mockLine).start();

            // Start recording
            audioModel.startRecording();

            // Verify methods are called
            verify(mockLine, times(1)).open(captor.capture());
            verify(mockLine, times(1)).start();

            // Validate the captured AudioFormat
            AudioFormat format = captor.getValue();
            assertTrue(format.getSampleRate() == 44100, "Sample rate should be 44100 Hz.");
            assertTrue(format.getSampleSizeInBits() == 16, "Sample size should be 16 bits.");
            assertTrue(format.isBigEndian(), "Audio format should be big-endian.");

            // Stop recording
            audioModel.stopRecording();

            // Verify stop and close methods are called
            verify(mockLine, times(1)).stop();
            verify(mockLine, times(1)).close();

            // Validate file creation
            File audioFile = new File("recording.wav");
            assertTrue(audioFile.exists() && audioFile.isFile(), "Audio file should be created after recording.");
        } catch (LineUnavailableException e) {
            fail("Unexpected LineUnavailableException: " + e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testStopRecordingStopsRecording() {
        try {
            audioModel.startRecording();
            audioModel.stopRecording();
            assertFalse(audioModel.isRecording(), "Recording should stop when stopRecording is called.");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testAudioModelInitialization() {
        assertNotNull(audioModel, "AudioModel should initialize with a TargetDataLine.");
    }

    @Test
    void testStartRecordingThrowsExceptionForNullLine() {
        AudioModel nullAudioModel = new AudioModel(null);
        Exception exception = assertThrows(LineUnavailableException.class, nullAudioModel::startRecording);
        assertTrue(exception.getMessage().contains("TargetDataLine is null"));
    }
}
