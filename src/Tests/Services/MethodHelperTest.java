package Services;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class MethodHelperTest {

    @Test
    public void usernamePasswordValidation() {
        assertFalse("Password is empty",MethodHelper.usernamePasswordValidation("kuku",""));
        assertFalse("User is empty",MethodHelper.usernamePasswordValidation("","123"));
        assertFalse("Password and User are empty",MethodHelper.usernamePasswordValidation("",""));

        assertTrue("Password and User are not empty",MethodHelper.usernamePasswordValidation("kuuk","123"));
    }
}
