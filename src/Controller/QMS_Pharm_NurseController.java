package Controller;

import Services.Communication.OutgoingCommunication;
import Services.Constants;
import Services.MethodHelper;
import Services.SettingsDialog;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.prefs.Preferences;

public class QMS_Pharm_NurseController {

    @FXML
    private ImageView imageViewLogo;
    @FXML
    private Label lblTime, lblNursePharmNum , lblNextPatient;
    @FXML
    private TextField txtPharmNurseNum;

    private static Label s_lblNextPatient;

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

        if(Constants.roleType.equals(Constants.PHARMROLE))
            lblNursePharmNum.setText("דלפק מספר:");
        else
            lblNursePharmNum.setText("חדר מספר:");

        s_lblNextPatient = lblNextPatient;
    }

    public static void updatePatientlbl(String number)
    {
        s_lblNextPatient.setText(number);
    }


    public void NextPatient()
    {
       //TODO: update label with last update time

       String nextType = "";
       String pharmNurseNum = txtPharmNurseNum.getText();

       if(Constants.roleType.equals(Constants.NURSEROLE))
           nextType = Constants.NURSE_NEXT_PATIENT;
       else   if(Constants.roleType.equals(Constants.PHARMROLE))
           nextType = Constants.PHARM_NEXT_PATIENT;

        new OutgoingCommunication(settingsPrefs.get(Constants.SERVER_IP,String.class.toString()),
                Integer.parseInt(settingsPrefs.get(Constants.SERVER_PORT,String.class.toString())),
                nextType+" "+pharmNurseNum).start();
    }


    public void logout()
    {
        MethodHelper.switchScene(Constants.LOGIN);
    }
}
