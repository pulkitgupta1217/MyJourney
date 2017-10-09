package main.java.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            String ip = socket.getLocalAddress().getHostAddress();
            System.out.println(ip);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.print("external: ");
        try {
            System.out.println((new BufferedReader(new InputStreamReader(new URL("http://checkip.amazonaws.com").openStream()))).readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * public static void main(String[] args)
      throws IOException, InterruptedException {
    InetAddress addr =
      InetAddress.getByName(null);
    while(true) {
      if(JabberClientThread.threadCount()
         < MAX_THREADS)
        new JabberClientThread(addr);
      Thread.currentThread().sleep(100);
    }
  }
    * */
}
