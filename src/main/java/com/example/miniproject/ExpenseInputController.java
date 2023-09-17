package com.example.miniproject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ExpenseInputController {

    @FXML
    private TextField expenseInput;
    @FXML
    private VBox membersExpenseBox;
    @FXML
    private VBox resultsBox;

    private List<String> memberNames = new ArrayList<>();
    private List<TextField> memberExpenseFields = new ArrayList<>();

    public void setMemberNames(List<String> names) {
        this.memberNames.clear();
        this.memberNames.addAll(names);
        generateMemberExpenseFields();
    }

    @FXML
    private void initialize() {
        // As per our previous discussion, this will be empty for now.
    }

    private void generateMemberExpenseFields() {
        membersExpenseBox.getChildren().clear();
        memberExpenseFields.clear();

        for (String memberName : memberNames) {
            TextField memberExpenseField = new TextField();
            memberExpenseField.setPromptText("Amount paid by " + memberName);
            membersExpenseBox.getChildren().add(memberExpenseField);
            memberExpenseFields.add(memberExpenseField);
        }
    }

    @FXML
    public void calculateOwedAmounts() {
        System.out.println("Function called"); // Debug line
        System.out.println("Member names size: " + memberNames.size()); // Debug line
        System.out.println("Expense fields size: " + memberExpenseFields.size()); // Debug line

        resultsBox.getChildren().clear();

        try {
            double totalExpense = Double.parseDouble(expenseInput.getText());
            double amountPerMember = totalExpense / memberNames.size();
            double sumPaidByMembers = 0;



            for (int i = 0; i < memberNames.size(); i++) {
                double amountPaid = Double.parseDouble(memberExpenseFields.get(i).getText());
                sumPaidByMembers += amountPaid;

                double difference = amountPaid - amountPerMember;
                Label resultLabel = new Label();

                if (difference < 0) {
                    resultLabel.setText(memberNames.get(i) + " owes: $" + String.format("%.2f", -difference));
                } else if (difference > 0) {
                    resultLabel.setText(memberNames.get(i) + " will get back: $" + String.format("%.2f", difference));
                } else {
                    resultLabel.setText(memberNames.get(i) + " is settled!");
                }

                resultLabel.setStyle("-fx-text-fill: #d2f011;");
                resultsBox.getChildren().add(resultLabel);
            }

            if (sumPaidByMembers != totalExpense) {
                double missingAmount = totalExpense - sumPaidByMembers;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Mismatch in Amount");
                alert.setHeaderText(null);
                alert.setContentText("There's a mismatch in total amounts. $" + String.format("%.2f", missingAmount) + " is missing.");
                alert.showAndWait();
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid amounts.");
            alert.showAndWait();
        }
    }
}
