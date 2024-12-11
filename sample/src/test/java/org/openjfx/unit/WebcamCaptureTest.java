package org.openjfx.unit;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.models.CameraModel;

public class WebcamCaptureTest {

    private CameraModel cameraModel;

    @BeforeEach
    void setUp() {
        this.cameraModel = new CameraModel();
    }

    @Test
    void testCaptureImageCreatesValidFile() {
        try {
            cameraModel.capturePhoto();
            File file = new File("capture.jpg");
            assertTrue(file.exists() && file.length() > 0, "The image file should exist and have content.");
        } catch (Exception e) {
            fail("Exception while capturing photo: " + e.getMessage());
        }
    }

    @Test
    void testCapturePhotoHandlesNoWebcam() throws Exception {
        try {
            CameraModel cameraModel = new CameraModel(null); // Simulate no webcam
            cameraModel.capturePhoto();
            fail("Exception expected when webcam is not available.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            assertTrue(e.getMessage().contains("No webcam detected"), "Expected 'No webcam detected' exception.");
        }
    }
}
