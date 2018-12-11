package checkers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private GameController gc;
	
	public ClientThread(Socket s, BufferedReader input, PrintWriter output, GameController con) {
		socket = s;
		in = input;
		out = output;
		gc = con;
	}
	
	public void run() {
		while(true) {
			try {
				String line = in.readLine();
				if(line == null) {
					socket.close();
					return;
				} else {
					String[] inputAsStringArray = line.split("\\s+");
					int[] toSend = new int[inputAsStringArray.length];
					for(int i = 0; i < inputAsStringArray.length; i++) {
						toSend[i] = Integer.parseInt(inputAsStringArray[i]);
					}
					gc.boardAction(toSend[0], toSend[1], toSend[2]);
					String output = gc.getFieldsAsString();
					out.println(output);
				}
			} catch(IOException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
