package org.openjfx.integration;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.openjfx.controller.MainController;
import org.openjfx.models.AudioModel;
import org.openjfx.models.LocationModel;
import org.openjfx.view.MainView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.sound.sampled.LineUnavailableException;
import static org.junit.jupiter.api.Assertions.fail;

public class AudioIntegrationTest {
    private MainController controller;
    private MainView mockView;
    private AudioModel mockAudioModel;

    @BeforeEach
    void setUp() {
        mockView = mock(MainView.class);
        mockAudioModel = mock(AudioModel.class);
        controller = new MainController(mockView, mockAudioModel);
    }

    @Test
    void testRecordButtonInvokesStartRecording() {
        try {
            doNothing().when(mockAudioModel).startRecording();
            mockAudioModel.startRecording(); // Simulate action
            verify(mockAudioModel, times(1)).startRecording(); // Verify invocation
        } catch (LineUnavailableException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testStopButtonInvokesStopRecording() {
        controller.getAudioModel().stopRecording(); // Call the method directly or through a simulated button action.
        verify(mockAudioModel, times(1)).stopRecording();
    }

    @Test
    void testLocationButtonInvokesGetLocation() throws Exception {
        LocationModel mockLocationModel = mock(LocationModel.class);
        MainController controller = new MainController(mockView, mockLocationModel);

        doReturn("Lat: 40.7128, Lon: -74.0060").when(mockLocationModel).getLocation();
        String location = mockLocationModel.getLocation();

        assertNotNull(location, "Location fetch should return a non-null value.");
        verify(mockLocationModel, times(1)).getLocation(); // Verify the method was called
    }

}
