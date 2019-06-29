package Services;

import javafx.stage.Stage;

import java.util.Arrays;

public class Constants {

    public enum eRoleTypes {
        ADMINROLE("admin"),
        PHARMROLE("pharm"),
        NURSEROLE("nurse"),
        LOGIN("");

        private String value = "";

        eRoleTypes(String value) {
            this.value = value;
        }

        /**
         * @return the Enum representation for the given string.
         * @throws IllegalArgumentException if unknown string.
         */
        public static eRoleTypes fromString(String s) throws IllegalArgumentException {
            return Arrays.stream(eRoleTypes.values())
                    .filter(v -> v.value.equals(s))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
        }
    }

    /*helper to switch screens*/
    public static Stage stage;
    /*logged user type*/
    public static String roleType;


    /*Protocol Headers*/
    public final static String LOGIN = "LOGIN";
    public final static String REPORT = "REPORT";
    public final static String NURSE_NEXT_PATIENT = "NURSE_NEXT_PATIENT";
    public final static String PHARM_NEXT_PATIENT = "PHARM_NEXT_PATIENT";

    /*Protocol After Headers*/
    public final static String PATIENT_NUMBER = "PATIENT_NUMBER";

    public final static String NOUSER = "NOUSER";
    public final static String NOPTIENTS = "NOPTIENTS";

    /*messages*/
    public static String LOGIN_FIELDS_VALIDATION = "שם המשתמש או הסיסמא חסרים";
    public static String NOUSER_MESSAGE = "פרטי המשתמש לא נכונים";
    public final static String NOPTIENTS_MESSAGE = "אין מטופלים בתור";

    /*Settings*/
    //the server ip
    public static String SERVER_IP = "ServerIP";

    //the server port
    public static String SERVER_PORT = "ServerPort";


}
