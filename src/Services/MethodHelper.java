package Services;

import Services.Communication.OutgoingCommunication;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;

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

    public static boolean isNumeric(String strNum) {
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static void SendMessageToServer(String message){
        Preferences settingsPrefs = Preferences.userNodeForPackage(SettingsDialog.class);

        new OutgoingCommunication(settingsPrefs.get(Constants.SERVER_IP,String.class.toString()),
                Integer.parseInt(settingsPrefs.get(Constants.SERVER_PORT,String.class.toString())),
                message).start();
    }

    public static boolean usernamePasswordValidation(String username , String password)
    {
        return (username.equals("") || password.equals(""));
    }

    public static void switchScene(Constants.eRoleTypes roleType)
    {
        String title = "";
        Stage stage =  Constants.stage;
        Parent root = null;

        try {
            if (roleType == Constants.eRoleTypes.ADMINROLE) {
                Constants.roleType = Constants.eRoleTypes.ADMINROLE.toString();
                root = FXMLLoader.load(MethodHelper.class.getResource("/View/Admin.fxml"));
                title = "הנהלה";
                }
                else if (roleType == Constants.eRoleTypes.PHARMROLE){
                Constants.roleType = Constants.eRoleTypes.PHARMROLE.toString();
                root = FXMLLoader.load(MethodHelper.class.getResource("/View/Pharm_Nurse.fxml"));
                title = "בית מרקחת";
                }
                else if(roleType == Constants.eRoleTypes.NURSEROLE) {
                Constants.roleType = Constants.eRoleTypes.NURSEROLE.toString();
                root = FXMLLoader.load(MethodHelper.class.getResource("/View/Pharm_Nurse.fxml"));
                title = "חדר אחיות";
                }
                else if (roleType == Constants.eRoleTypes.LOGIN){
                    Constants.roleType = "";
                    root = FXMLLoader.load(MethodHelper.class.getResource("/View/Login.fxml"));
                    title = "מסך התחברות";
                }

            stage.setTitle(title);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            }
            catch (IOException e) {
            //e.printStackTrace();
            }


    }
}
