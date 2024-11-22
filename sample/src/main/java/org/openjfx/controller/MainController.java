package org.openjfx.controller;

import org.openjfx.models.AudioModel;
import org.openjfx.models.CameraModel;
import org.openjfx.models.LocationModel;
import org.openjfx.view.MainView;

import javafx.application.Platform;

public class MainController {

    private final AudioModel audioModel;
    private final CameraModel cameraModel;
    private final LocationModel locationModel;
    private final MainView view;

    public MainController(MainView view) {
        this.audioModel = new AudioModel();
        this.cameraModel = new CameraModel();
        this.locationModel = new LocationModel();
        this.view = view;

        initializeEventHandlers();
    }

    private void initializeEventHandlers() {
        view.setRecordButtonAction(event -> {
            try {
                audioModel.startRecording();
                view.toggleRecordButtonsDisabled();
                System.out.println("Record commencé");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        view.setStopRecordButtonAction(event -> {
            try {
                audioModel.stopRecording();
                view.toggleRecordButtonsDisabled();
                System.out.println("Audio enregistré sous recording.wav");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        view.setCameraButtonAction(event -> {
            try {
                cameraModel.capturePhoto();
                System.out.println("Photo enregistrée sous capture.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        view.setLocationButtonAction(event -> {
            new Thread(() -> {
                try {
                    String location = locationModel.getLocation();
                    Platform.runLater(() -> System.out.println(location));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    public void shutdown() {
        cameraModel.close();
    }
}