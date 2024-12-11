package org.openjfx.gui;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.openjfx.controller.MainController;
import org.openjfx.models.AudioModel;
import org.openjfx.view.MainView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import javax.sound.sampled.LineUnavailableException;

public class ButtonActionTest extends ApplicationTest {
    private MainView view;

    @Override
    public void start(Stage stage) {
        view = new MainView(); // Initialize MainView
        Scene scene = new Scene(view, 300, 200); // Add MainView to a Scene
        stage.setScene(scene); // Set the Scene on the Stage
        stage.show(); // Show the Stage
    }

    @Test
    void testRecordButtonClickChangesState() throws LineUnavailableException {
        // Mock AudioModel to prevent actual recording and exceptions
        AudioModel mockAudioModel = mock(AudioModel.class);
        doNothing().when(mockAudioModel).startRecording(); // Configure mock to do nothing

        // Create MainController with the mocked AudioModel
        MainController controller = new MainController(view);
        controller.setAudioModel(mockAudioModel);

        // Get the record button
        Button recordButton = (Button) view.lookup("#recordButton");

        // Simulate clicking the record button
        clickOn(recordButton);

        // Assert that the record button is disabled after the click
        assertTrue(recordButton.isDisabled(), "Record button should be disabled after clicking.");
    }

    @Test
    void testRecordButtonToggleState() throws Exception {
        // Mock AudioModel to prevent actual recording
        AudioModel mockAudioModel = mock(AudioModel.class);
        doNothing().when(mockAudioModel).startRecording();

        // Create MainController and set the mocked AudioModel
        MainController controller = new MainController(view);
        controller.setAudioModel(mockAudioModel);

        Button recordButton = (Button) view.lookup("#recordButton");
        Button stopRecordButton = (Button) view.lookup("#stopRecordButton");

        assertNotNull(recordButton, "Record button should exist.");
        assertNotNull(stopRecordButton, "Stop Record button should exist.");

        // Initial state
        assertFalse(recordButton.isDisabled(), "Record button should be enabled initially.");
        assertTrue(stopRecordButton.isDisabled(), "Stop Record button should be disabled initially.");

        // Simulate clicking the record button
        clickOn(recordButton);

        // After clicking record
        assertTrue(recordButton.isDisabled(), "Record button should be disabled after clicking.");
        assertFalse(stopRecordButton.isDisabled(), "Stop Record button should be enabled after clicking record.");
    }
}
