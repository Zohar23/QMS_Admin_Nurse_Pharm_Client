package Services;

import javafx.stage.Stage;

public class Constants {

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

    /*role types*/
    public static final String ADMINROLE = "admin";
    public static final String PHARMROLE = "pharm";
    public static final String NURSEROLE = "nurse";


    /*Settings*/
    //the server ip
    public static String SERVER_IP = "ServerIP";

    //the server port
    public static String SERVER_PORT = "ServerPort";


}
