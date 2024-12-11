package org.openjfx.gui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.openjfx.view.MainView;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class AudioButtonTest extends ApplicationTest {
    private MainView view;

    @Override
    public void start(Stage stage) {
        view = new MainView();
        Scene scene = new Scene(view, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void testRecordButtonUpdatesUI() {
        Button recordButton = lookup(".button").query();
        assertNotNull(recordButton, "Record button should exist in the view.");
    }
}
