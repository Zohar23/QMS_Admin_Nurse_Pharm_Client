package Services.Communication;

import Controller.QMS_AdminController;
import Controller.QMS_Pharm_NurseController;
import Services.Constants;
import Services.MethodHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import javafx.application.Platform;

public class OutgoingCommunication
        extends Thread {
    String serverIP = "";
    String message;
    int serverPort;

    public OutgoingCommunication(String serverIP, int serverPort, String message) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.message = message;
    }

    @Override
    public void run() {
        Socket client = null;
        PrintWriter pwOut = null;
        BufferedReader brIn = null;
        try {
            InetAddress neighborAddress = InetAddress.getByName(this.serverIP);
            client = new Socket(neighborAddress, this.serverPort);
            pwOut = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            brIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
            pwOut.write(this.message + "\n");
            pwOut.flush();
            String incomingCommand = brIn.readLine();
            String[] response = incomingCommand.split(" ");
            switch (response[0]) {
                case "LOGIN": {
                    if (response[1].equals("NOUSER")) {
                        Platform.runLater(() -> MethodHelper.ShowAlert((String)Constants.NOUSER_MESSAGE));
                        break;
                    }
                    Platform.runLater(() -> MethodHelper.switchScene((String)response[1]));
                    break;
                }
                case "REPORT": {
                    Platform.runLater(() -> QMS_AdminController.SetReport((String)response[1], (String)response[2]));
                    break;
                }
                case "NOPTIENTS": {
                    Platform.runLater(() -> MethodHelper.ShowAlert((String)"\u05d0\u05d9\u05df \u05de\u05d8\u05d5\u05e4\u05dc\u05d9\u05dd \u05d1\u05ea\u05d5\u05e8"));
                    break;
                }
                case "PATIENT_NUMBER": {
                    Platform.runLater(() -> QMS_Pharm_NurseController.updatePatientlbl((String)response[1]));
                }
            }
            System.out.println(incomingCommand);
        }
        catch (IOException neighborAddress) {
            // empty catch block
        }
        try {
            if (client != null) {
                client.close();
                pwOut.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}