package com.example.miniproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MembersInputController {

    @FXML
    private AnchorPane membersPane;
    @FXML
    private Spinner<Integer> memberCountSpinner;
    @FXML
    private VBox membersInputBox;
    private List<TextField> memberNameFields = new ArrayList<>();

    public void initialize() {
        memberCountSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, 5, 1));
    }

    @FXML
    public void generateInputFields() {
        membersInputBox.getChildren().clear();
        memberNameFields.clear();

        int count = memberCountSpinner.getValue();
        for (int i = 0; i < count; i++) {
            TextField memberField = new TextField();
            memberField.setPromptText("Enter member " + (i + 1) + "'s name");
            membersInputBox.getChildren().add(memberField);
            memberNameFields.add(memberField);
        }
    }

    @FXML
    public void submitMembers() {
        List<String> memberNames = new ArrayList<>();
        for (TextField tf : memberNameFields) {
            if (!tf.getText().trim().isEmpty()) {
                memberNames.add(tf.getText().trim());
            }
        }

        if (memberNames.size() < memberNameFields.size()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill out all member names.");
            alert.showAndWait();
            return;
        }

        // Switch to the ExpenseInput screen
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("expense_input.fxml"));
            AnchorPane expensePane = loader.load();
            ExpenseInputController expenseController = loader.getController();
            expenseController.setMemberNames(memberNames);

            Stage currentStage = (Stage) membersPane.getScene().getWindow();
            currentStage.setScene(new Scene(expensePane, 600, 400));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
