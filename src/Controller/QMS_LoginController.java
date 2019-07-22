package Controller;

import Services.Communication.OutgoingCommunication;
import Services.Constants;
import Services.MethodHelper;
import Services.SettingsDialog;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.prefs.Preferences;

public class QMS_LoginController {

    @FXML
    private ImageView imageViewLogo;
    @FXML
    private Label lblTime;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    private Image imageLogo;
    Calendar calendar;
    String currentTime;
    Preferences settingsPrefs;

    public void initialize(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            currentTime = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(Calendar.getInstance().getTime());
            lblTime.setText(currentTime);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        imageLogo = new Image(getClass().getResource("/logo.png").toString());
        imageViewLogo.setImage(imageLogo);
        calendar = Calendar.getInstance(TimeZone.getDefault());
        settingsPrefs = Preferences.userNodeForPackage(SettingsDialog.class);

    }
    public void ShowSettings(){
        SettingsDialog.ShowSettingsDialog();
    }

    public void login()
    {
        /*fields validation*/
        if(!MethodHelper.usernamePasswordValidation(txtUsername.getText(),txtPassword.getText()))
            MethodHelper.ShowAlert(Constants.LOGIN_FIELDS_VALIDATION);
        else {
            MethodHelper.SendMessageToServer(Constants.LOGIN+" "+txtUsername.getText()+" "+txtPassword.getText());
        }
    }


}
