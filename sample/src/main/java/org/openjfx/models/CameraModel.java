package org.openjfx.models;

import com.github.sarxos.webcam.Webcam;
import javax.imageio.ImageIO;
import java.io.File;

public class CameraModel {
    private final Webcam webcam;

    public CameraModel() {
        this.webcam = Webcam.getDefault();
    }

    public void capturePhoto() throws Exception {
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