package javafx.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationFormController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Window owner = submitButton.getScene().getWindow();

        // Validation for Name Field
        if (nameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your name");
            return;
        }

        // Validation for Email Field
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(emailField.getText());
        if (emailField.getText().isEmpty() || !emailMatcher.matches()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter a valid email id");
            return;
        }

        // Validation for Password Field
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{5,12}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(passwordField.getText());
        if (passwordField.getText().isEmpty() || !passwordMatcher.matches()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!","Password must be between 5 and 12 characters and include at least one uppercase letter, one lowercase letter, and one number.");
            return;
        }

        // Save User Information to File
        try (FileWriter fileWriter = new FileWriter("data/users.txt", true); // Open in append mode
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            // Write user information to the file
            printWriter.println("Name: " + nameField.getText() + ", Email: " + emailField.getText() + ", Password: " + passwordField.getText()); // Remember to hash the password in a real application

            // Show confirmation alert
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!", "Welcome " + nameField.getText());
        } catch (IOException e) {
            e.printStackTrace(); // For debugging. In production, log this error or handle it appropriately.
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Registration Error", "Failed to save user data.");
        }
    }
}