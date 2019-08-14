/*
how to use this program:
compile:    javac UDPSender.java
run:        java UDPSender

Several parameters can be changed to configure this for your needs (remember to recompile).
PORT                    -pick an unused port in your environment.
MS_TO_SLEEP             -delay between sending packets
NUM_PACKETS_TO_SEND     -count of packets to send.

 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {
  final static int MS_TO_SLEEP = 1;
  final static int NUM_PACKETS_TO_SEND = 1_000_000;
  final static int PORT = 55555;
  final static String partOfMessage = "this is a friendly UDP packet!\n";

  public static void main(String[] args) throws IOException, InterruptedException {


    byte[] IP = InetAddress.getLocalHost().getAddress();
    InetAddress address = InetAddress.getByAddress(IP);
    System.out.println("IP for this host " + InetAddress.getLocalHost());

    DatagramSocket datagramSocket = new DatagramSocket();

    for (int i = 0; i < NUM_PACKETS_TO_SEND; i++) {
      byte[] buffer = (i + " " + partOfMessage).getBytes();
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
      datagramSocket.send(packet);

      if (MS_TO_SLEEP > 0) {
        Thread.sleep(MS_TO_SLEEP);
      }
    }

  }
}
