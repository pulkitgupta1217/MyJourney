package main.java.Server;

import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Scanner;

public class ServerHost extends Thread {

    private ServerSocket hostSocket;
    private TextArea output, users;
    private HashMap<String, String> configMap;
    //todo: decide timeout for server
    private int timeout = 0;

    public ServerHost(TextArea output, TextArea users) throws IOException {
        configMap = new HashMap<>();
        setup();
        hostSocket = new ServerSocket(Integer.parseInt(configMap.get("port")));
        hostSocket.setSoTimeout(timeout);
        this.output = output;
        this.users = users;
    }
    public ServerHost() throws IOException {
        configMap = new HashMap<>();
        setup();
        hostSocket = new ServerSocket(Integer.parseInt(configMap.get("port")));
        hostSocket.setSoTimeout(timeout);
    }
    public void run() {
        writeToOutput("starting server output");
        writeToUsers("user list: ");
        writeToOutput("waiting for client on port: " + hostSocket.getLocalPort() + "...");
        while (true) {
            try {
                Socket server = hostSocket.accept();
                writeToOutput("Connected to: " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                String userAdded = in.readUTF();
                userAdded = userAdded.substring(12, userAdded.indexOf(" from ")).trim();
                writeToUsers(userAdded);
                ObjectInputStream oIn = new ObjectInputStream(server.getInputStream());
                writeToOutput(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                ObjectOutputStream oOut = new ObjectOutputStream(server.getOutputStream());
                out.writeUTF("Thank You for connecting to " + server.getLocalSocketAddress() + "\nGoodBye");
                server.close();
            } catch (SocketTimeoutException ste) {
                System.out.println("couldnt connect to server. " + ste.getMessage());
                break;
            } catch (SocketException e) {
                System.out.println(e.getMessage());
            } catch (IOException ioe) {
                ioe.printStackTrace();
                break;
            }
        }

    }

    private void writeToUsers(String text) {
        users.setText(users.getText() + "\n" + text);
    }

    private void writeToOutput(String text) {
        output.setText(output.getText() + "\n" + text);
    }

    public void setup() {
        //System.out.println(System.getProperty("user.dir"));
        File configFile = new File(System.getProperty("user.dir") + "/server.config");
        try {
            Scanner configReader = new Scanner(configFile);
            while (configReader.hasNext()) {
                String[] currConfig = configReader.nextLine().split(" ");
                configMap.put(currConfig[0], currConfig[currConfig.length - 1]);
            }
            System.out.println(configMap.keySet());
            System.out.println(configMap.values());
            /*configs.forEach((String[] s) -> {
                System.out.println(Arrays.toString(s));
            });*/

        } catch (FileNotFoundException e) {
            generateConfigs();
            this.setup();
        }
    }

    private void generateConfigs() {
        File newConfigs = new File(System.getProperty("user.dir") + "/server.config");
        try {
            if (newConfigs.createNewFile()) {
                PrintStream printer = new PrintStream(newConfigs);
                printer.println("port = 1234");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        try {
            Thread server = new ServerHost();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public synchronized void close() {
        try {
            hostSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        interrupt();
        System.exit(0);
    }
}
