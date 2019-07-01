package Services.Communication;

import Controller.QMS_AdminController;
import Controller.QMS_Pharm_NurseController;
import Services.Constants;
import Services.MethodHelper;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import javafx.application.Platform;

public class OutgoingCommunication
        extends Thread {
    String serverIP = "";
    String message;
    int serverPort;
    ObjectOutputStream toServer;
    ObjectInputStream fromServer;

    public OutgoingCommunication(String serverIP, int serverPort, String message) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.message = message;
    }

    @Override
    public void run() {
        Socket client = null;
        try {

            //To Server
            InetAddress neighborAddress = InetAddress.getByName(this.serverIP);
            client = new Socket(neighborAddress, this.serverPort);

            // Create an output stream to the server
            toServer = new ObjectOutputStream(client.getOutputStream());
            fromServer = new ObjectInputStream(client.getInputStream());
            toServer.writeUTF(this.message);
            toServer.flush();


            //From Server
            String incomingCommand = fromServer.readUTF();
            String[] response = incomingCommand.split(" ");
            switch (response[0]) {
                case Constants.LOGIN: {
                    if (response[1].equals(Constants.NOUSER)) {
                        Platform.runLater(() -> MethodHelper.ShowAlert(Constants.NOUSER_MESSAGE));
                        break;
                    }
                    Platform.runLater(() -> MethodHelper.switchScene(Constants.eRoleTypes.fromString(response[1])));
                    break;
                }
                case Constants.REPORT: {
                    Platform.runLater(() -> QMS_AdminController.SetReport(response[1], response[2]));
                    break;
                }
                case Constants.NOPTIENTS: {
                    Platform.runLater(() -> MethodHelper.ShowAlert("אין מטופלים בתור"));
                    break;
                }
                case Constants.PATIENT_NUMBER: {
                    Platform.runLater(() -> QMS_Pharm_NurseController.updatePatientlbl(response[1]));
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
                toServer.close();
                fromServer.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}