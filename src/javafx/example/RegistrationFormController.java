package javafx.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationFormController {
    
    @FXML
    private TextField nameField, emailField;
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
        } else {
            String nameRegex = "[a-zA-ZæøåÆØÅ]*";
            Pattern pattern = Pattern.compile(nameRegex);
            Matcher matcher = pattern.matcher(nameField.getText());
            if (!matcher.matches()){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter a valid name");
                return;
            }
        }

        // Validation for Email Field
        if (emailField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your email id");
            return;
        } else {
            String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(emailField.getText());
            if (!matcher.matches()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter a valid email");
                return;
            }
        }

        // Validation for Password Field
        if (passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter a password");
            return;
        } else {
            String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{5,12}";
            Pattern pattern = Pattern.compile(passwordRegex);
            Matcher matcher = pattern.matcher(passwordField.getText());
            if (!matcher.matches()){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Password must be between 5 and 12 characters and include at least one uppercase letter, one lowercase letter, and one number.");
                return;
            }
        }

        // Registration Successful
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!", "Welcome " + nameField.getText());
    }
}
