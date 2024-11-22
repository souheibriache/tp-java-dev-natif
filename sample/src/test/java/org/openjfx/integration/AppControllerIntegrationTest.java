package org.openjfx.integration;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.openjfx.controller.MainController;
import org.openjfx.models.AudioModel;
import org.openjfx.view.MainView;

public class AppControllerIntegrationTest {
    private MainController controller;
    private MainView mockView;
    private AudioModel mockAudioModel;

    @BeforeEach
    void setUp() {
        mockView = Mockito.mock(MainView.class);
        mockAudioModel = Mockito.mock(AudioModel.class);
        controller = new MainController(mockView);
    }

    
}
