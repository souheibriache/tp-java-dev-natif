package org.openjfx.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
    private final Button recordButton;
    private final Button stopRecordButton;
    private final Button cameraButton;
    private final Button locationButton;

    public MainView() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));

        recordButton = new Button("Enregistrer Audio");
        recordButton.setId("recordButton"); // Add ID
        stopRecordButton = new Button("Arreter l'enregistrement");
        stopRecordButton.setId("stopRecordButton"); // Add ID
        stopRecordButton.setDisable(true);
        cameraButton = new Button("Capturer image");
        cameraButton.setId("cameraButton"); // Add ID
        locationButton = new Button("Obtenir Coordonnées GPS");
        locationButton.setId("locationButton"); // Add ID

        getChildren().addAll(recordButton, stopRecordButton, cameraButton, locationButton);
    }

    public void setRecordButtonAction(EventHandler<ActionEvent> handler) {
        recordButton.setOnAction(handler);
    }

    public void setStopRecordButtonAction(EventHandler<ActionEvent> handler) {
        stopRecordButton.setOnAction(handler);
    }

    public void setCameraButtonAction(EventHandler<ActionEvent> handler) {
        cameraButton.setOnAction(handler);
    }

    public void setLocationButtonAction(EventHandler<ActionEvent> handler) {
        locationButton.setOnAction(handler);
    }

    public void toggleRecordButtonsDisabled() {
        recordButton.setDisable(!recordButton.isDisabled());
        stopRecordButton.setDisable(!stopRecordButton.isDisabled());
    }

}