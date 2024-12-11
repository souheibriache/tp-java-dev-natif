package org.openjfx.models;

import com.github.sarxos.webcam.Webcam;
import javax.imageio.ImageIO;
import java.io.File;

public class CameraModel {
    private final Webcam webcam;

    public CameraModel() {
        this.webcam = Webcam.getDefault();
    }

    public CameraModel(Webcam webcam) {
        if (webcam == null) {
            throw new IllegalArgumentException("No webcam detected");
        }
        this.webcam = webcam;
    }

    public void capturePhoto() throws Exception {
        if (webcam == null) {
            throw new IllegalStateException("No webcam detected");
        }
        if (!webcam.isOpen()) {
            webcam.open();
        }
        ImageIO.write(webcam.getImage(), "JPG", new File("capture.jpg"));
        webcam.close();
    }

    public void close() {
        if (webcam != null && webcam.isOpen()) {
            webcam.close();
        }
    }
}