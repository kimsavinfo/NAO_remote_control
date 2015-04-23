package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static void main(String[] args) {
		int portNumber = Integer.parseInt(args[0]);
		Socket socket;
		BufferedReader in;
		try {
			ServerSocket socketserver = new ServerSocket(portNumber);
			socket = socketserver.accept();
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			String message_distant = in.readLine();
			System.out.println(message_distant);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
