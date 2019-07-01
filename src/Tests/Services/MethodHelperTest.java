package Services;

import Main.Main;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;

import static org.junit.Assert.*;

public class MethodHelperTest {

    @Before
    public void init() {

    }

    @Test
    public void usernamePasswordValidation() {
        assertFalse("Password is empty",MethodHelper.usernamePasswordValidation("kuku",""));
        assertFalse("User is empty",MethodHelper.usernamePasswordValidation("","123"));
        assertFalse("Password and User are empty",MethodHelper.usernamePasswordValidation("",""));

        assertTrue("Password and User are not empty",MethodHelper.usernamePasswordValidation("kuuk","123"));
    }

    @Test
    public void isNumeric(){
        assertFalse("Characters are not a number",MethodHelper.isNumeric("kuku"));
        assertFalse("Characters and numbers are not a number",MethodHelper.isNumeric("1kuku"));
        assertFalse("Empty string is not a number",MethodHelper.isNumeric(""));

        assertTrue("Is a number",MethodHelper.isNumeric("123"));

    }

    @Test
    public void SendMessageToServer(){
        assertTrue("Send successful",MethodHelper.SendMessageToServer("kuku"));
        assertFalse("Empty string - Send unsuccessful",MethodHelper.SendMessageToServer(""));
    }

}
