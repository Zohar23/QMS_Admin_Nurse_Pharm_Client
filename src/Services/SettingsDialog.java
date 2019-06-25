package Services;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;
import java.util.prefs.Preferences;

public class SettingsDialog {

    public static void ShowSettingsDialog() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        Scene sceneAlert = dialog.getDialogPane().getScene();
        sceneAlert.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        dialog.setTitle("הגדרות");
        dialog.setHeaderText("הגדרות שרת נתונים");

        // Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("שמור", ButtonBar.ButtonData.OK_DONE);
        ButtonType loginButtonCancel = new ButtonType("ביטול", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, loginButtonCancel);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        //Preferences
        Preferences pref;
        pref = Preferences.userNodeForPackage(SettingsDialog.class);

        TextField IP = new TextField();
        IP.setPromptText("כתובת אייפי");
        IP.setText(pref.get(Constants.SERVER_IP,String.class.toString()));
        TextField Port = new TextField();
        Port.setText(pref.get(Constants.SERVER_PORT,String.class.toString()));
        Port.setPromptText("פורט");

        grid.add(new Label("כתובת אייפי:"), 0, 0);
        grid.add(IP, 1, 0);
        grid.add(new Label("פורט:"), 0, 1);
        grid.add(Port, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        IP.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> IP.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(IP.getText(), Port.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(IP_Port -> {
            System.out.println("IP=" + IP_Port.getKey() + ", Port=" + IP_Port.getValue());
            Preferences prefs;
            prefs = Preferences.userNodeForPackage(SettingsDialog.class);
            prefs.put(Constants.SERVER_IP,IP_Port.getKey());
            prefs.put(Constants.SERVER_PORT,IP_Port.getValue());
        });
    }
}
