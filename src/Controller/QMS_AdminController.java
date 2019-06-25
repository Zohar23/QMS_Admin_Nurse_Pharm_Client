package Controller;

import Services.Communication.OutgoingCommunication;
import Services.Constants;
import Services.MethodHelper;
import Services.SettingsDialog;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.prefs.Preferences;

public class QMS_AdminController {

    @FXML
    private ImageView imageViewLogo;
    @FXML
    private Label lblTime , lblPharmTotal , lblNurseTotal;

    private static Label s_lblPharmTotal , s_lblNurseTotal;

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

        s_lblPharmTotal = lblPharmTotal;
        s_lblNurseTotal = lblNurseTotal;

    }

    public void showReport()
    {
       //TODO: update label with last update time

        new OutgoingCommunication(settingsPrefs.get(Constants.SERVER_IP,String.class.toString()),
                Integer.parseInt(settingsPrefs.get(Constants.SERVER_PORT,String.class.toString())),
                Constants.REPORT).start();

    }

    public static void SetReport(String totalPaharm , String totalNurse)
    {
        s_lblNurseTotal.setText(totalNurse);
        s_lblPharmTotal.setText(totalPaharm);
    }
    public void logout()
    {
        MethodHelper.switchScene(Constants.LOGIN);
    }
}
