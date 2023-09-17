package com.example.miniproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EventApp {

    @FXML
    public TextField eventNameInput;

    public Event currentEvent;

    @FXML
    public void handleSubmit() {
        String name = eventNameInput.getText();

        if (!name.isEmpty()) {
            currentEvent = new Event(name);

            // Display the alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Event Added!");
            alert.showAndWait();

            // Transition to members input screen
            switchToMembersScreen();

            // Optionally, clear the input field after adding the event
            eventNameInput.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Event name cannot be empty!");
            alert.showAndWait();
        }
    }

    private void switchToMembersScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("member_input.fxml"));
            AnchorPane membersPane = loader.load();

            Stage currentStage = (Stage) eventNameInput.getScene().getWindow();
            currentStage.setScene(new Scene(membersPane, 600, 400));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
