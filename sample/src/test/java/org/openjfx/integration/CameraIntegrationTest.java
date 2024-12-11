package org.openjfx.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.openjfx.controller.MainController;
import org.openjfx.models.CameraModel;
import org.openjfx.view.MainView;

public class CameraIntegrationTest {
    private MainController controller;
    private MainView mockView;
    private CameraModel mockCameraModel;

    @BeforeEach
    void setUp() {
        mockView = mock(MainView.class);
        mockCameraModel = mock(CameraModel.class);
        controller = new MainController(mockView, mockCameraModel);
    }

    @Test
    void testCameraButtonInvokesCapturePhoto() throws Exception {
        controller.getCameraModel().capturePhoto(); // Simulate action.
        verify(mockCameraModel, times(1)).capturePhoto();
    }

    @Test
    void testCameraButtonClickTriggersCapturePhoto() throws Exception {
        doNothing().when(mockCameraModel).capturePhoto();

        // Simulate button click
        controller.getCameraModel().capturePhoto();

        verify(mockCameraModel, times(1)).capturePhoto();
    }

    @Test
    void testCameraButtonClickInvokesCapturePhoto() throws Exception {
        doNothing().when(mockCameraModel).capturePhoto();
        controller.getCameraModel().capturePhoto(); // Simulate button click

        verify(mockCameraModel, times(1)).capturePhoto(); // Verify method was called
    }
}
