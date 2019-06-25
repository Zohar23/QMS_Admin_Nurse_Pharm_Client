package Services;

import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MethodHelper {

    /**
     * Show an alert
     * @param message  patient's number
     *
     */
    public static void ShowAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Scene sceneAlert = alert.getDialogPane().getScene();
        sceneAlert.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        alert.setTitle("התחברות");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static boolean usernamePasswordValidation(String username , String password)
    {
        if(username.equals("") || password.equals(""))
            return true;

            return false;
    }

    public static void switchScene(String clientType)
    {
        String title = "";
        Stage stage =  Constants.stage;
        Parent root = null;
        try {

            // scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

            switch(clientType) {
                case Constants.ADMINROLE:
                    Constants.roleType = Constants.ADMINROLE;
                    root = FXMLLoader.load(MethodHelper.class.getResource("/View/Admin.fxml"));
                    title = "הנהלה";
                    break;
                case Constants.PHARMROLE:
                    Constants.roleType = Constants.PHARMROLE;
                    root = FXMLLoader.load(MethodHelper.class.getResource("/View/Pharm_Nurse.fxml"));
                    title = "בית מרקחת";

                    break;
                case Constants.NURSEROLE:
                    Constants.roleType = Constants.NURSEROLE;
                    root = FXMLLoader.load(MethodHelper.class.getResource("/View/Pharm_Nurse.fxml"));
                    title = "חדר אחיות";

                    break;
                case Constants.LOGIN:
                    Constants.roleType = "";
                    root = FXMLLoader.load(MethodHelper.class.getResource("/View/Login.fxml"));
                    title = "מסך התחברות";
                    break;
            }


            stage.setTitle(title);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }






    }
}
