package org.openjfx.gui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openjfx.view.MainView;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.control.Button;

public class MainViewButtonTest extends ApplicationTest {
    @Test
    void testAllButtonsExist() {
        MainView view = new MainView();

        Button recordButton = (Button) view.lookup("#recordButton");
        Button stopButton = (Button) view.lookup("#stopRecordButton");
        Button cameraButton = (Button) view.lookup("#cameraButton");
        Button locationButton = (Button) view.lookup("#locationButton");

        assertNotNull(recordButton, "Record button should exist.");
        assertNotNull(stopButton, "Stop Record button should exist.");
        assertNotNull(cameraButton, "Camera button should exist.");
        assertNotNull(locationButton, "Location button should exist.");
    }

    @Test
    void testCameraButtonIsVisible() {
        MainView view = new MainView();
        Button cameraButton = (Button) view.lookup("#cameraButton");
        assertNotNull(cameraButton, "Camera button should exist.");
        assertTrue(cameraButton.isVisible(), "Camera button should be visible.");
    }
}
