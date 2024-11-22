package org.openjfx.gui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.openjfx.view.MainView;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.control.Button;

public class AudioButtonTest extends ApplicationTest {
     @Test
    void testRecordButtonUpdatesUI() {
        MainView view = new MainView();
        Button recordButton = (Button) view.lookup("#recordButton");
        assertNotNull(recordButton, "Record button should exist in the view.");
    }
}
