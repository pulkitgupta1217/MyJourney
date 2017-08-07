package main.java.Controller;

import javafx.stage.Stage;
import main.java.Model.CallBack;
import main.java.Model.ServerInfo;
import main.java.Server.Client;
import main.java.View.ErrorWindow;
import main.java.View.PlayerView;
import main.java.View.UpdateSettings;

import java.io.*;
import java.util.*;

public class BattleShipController {
    private static HashMap<String, String> userSettings;
    private static HashSet<String> correctUserSettings = new HashSet<>(Arrays.asList("username"));
    private static Stage primaryStage;
    private static String defaultPort = "1234";
    private static int defaultGameID;
    private static String defaultIP = "127.0.0.1";
    private static Client user;
    private static List<ServerInfo> serverList;

    public static PlayerView getOpponent() {
        //TODO: replace with get opponent from ip
        return new PlayerView();
    }

    //TODO: make setup method
    public static void setUp(CallBack callBack) {
        //TODO: reade/create userConfigs File
        System.out.println("reading file");
        File userSettingsFile = new File(System.getProperty("user.dir") + "/userStuff/user.bsfxsettings");
        userSettings = new HashMap<>();
        try {
            Scanner settingsReader = new Scanner(userSettingsFile);
            while (settingsReader.hasNext()) {
                String[] currConfig = settingsReader.nextLine().split("=");
                userSettings.put(currConfig[0].trim(), currConfig[currConfig.length - 1].trim());
            }

            if (!userSettings.keySet().containsAll(correctUserSettings)) {
                System.out.println("couldnt fine element username");
                throw new NoSuchElementException("missing settings");
            }
            callBack.accept();
        } catch (FileNotFoundException fnfe) {
            System.out.println("couldnt find file");
            try {
                (new File(userSettingsFile.getParent())).mkdirs();
                if (!userSettingsFile.createNewFile()){
                    throw new IOException("failed to create missing userSettings file at: " + userSettingsFile.getPath() + " try running as admin");
                }
                UpdateSettings update = new UpdateSettings();
                //TODO: start new UpdateSettings
                update.updateSettings(userSettings, callBack);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.exit(1);
            }
        } catch (NoSuchElementException nsee) {
            UpdateSettings update = new UpdateSettings();
            update.updateSettings(userSettings, callBack);
        }
    }
    public static void startGame(Stage primaryStage, CallBack startGame) {
        BattleShipController.primaryStage = primaryStage;
        startGame.accept();
    }


    public static void writeUserFieldsToFile() {
        File userSettingsFile = new File(System.getProperty("user.dir") + "/userStuff/user.bsfxsettings");
        try {
            userSettingsFile.delete();
            userSettingsFile.createNewFile();
            DataOutputStream dout = new DataOutputStream(new FileOutputStream(userSettingsFile));
            Set<String> keys;
            keys = userSettings.keySet();
            userSettings.forEach((String k, String v) -> {
                try {
                    dout.writeBytes(k + " = " + v + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(6);
                }
            });
            dout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(5);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(6);
        }
    }

    public static HashMap<String,String> getUserSettings() {
        return userSettings;
    }

    public static String getDefaultPort() {
        return defaultPort;
    }

    public static String getDefaultGameID() {
        if (defaultGameID == 0) {
            defaultGameID = new Random().nextInt();
        }
        return Integer.toString(defaultGameID);
    }

    public static String getDefaultIP() {
        return defaultIP;
    }

    public static boolean createClientConnection(String iP, String port, String gameID, CallBack callback) {
        user = new Client(new ServerInfo(iP, port, gameID));
        String result = user.connectToServer();
        if (result.equals("Connected!")) {
            callback.accept();
            return true;
        } else {
            ErrorWindow window = new ErrorWindow("failed to connect try again \n" + result);
            return false;
        }
    }

    public static String getUsername() {
        return userSettings.get("username");
    }

    public static void close() {
        writeServerListToFile();
        primaryStage.close();
    }

    public static List<ServerInfo> getServerList() {
        File serverListFile = new File("file:userStuff/servers.bsfxstore");
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(serverListFile));
            serverList = (LinkedList<ServerInfo>) reader.readObject();
            reader.close();
        } catch (IOException e) {
            serverList = new LinkedList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serverList;
    }
    private static void writeServerListToFile() {
        File serverListFile = new File(System.getProperty("file:userStuff/servers.bsfxstore"));
        try {
            serverListFile.delete();
            serverListFile.createNewFile();
            ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(serverListFile));
            oOut.writeObject(serverList);
            oOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(5);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(6);
        }
    }
}
