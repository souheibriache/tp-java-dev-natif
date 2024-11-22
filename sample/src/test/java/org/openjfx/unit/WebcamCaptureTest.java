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
    void testCaptureImageCreatesFile() {
        try {
            this.cameraModel.capturePhoto();
            File file = new File("capture.jpg");
            assertTrue(file.exists(), "The image file should exist after capture.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred while capturing the photo: " + e.getMessage());
        }
    }
}
