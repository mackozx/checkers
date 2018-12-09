package checkersclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import checkers.Field;

import java.io.PrintWriter;

public class CheckersClient {

	private BufferedReader in;
	private PrintWriter out;
	private CheckersFrame frame;
	
	public ArrayList<checkers.Field> connect(int a, int b) throws IOException {
		String serverAddress = "127.0.0.1";
		Socket socket = new Socket(serverAddress, 9090);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		out.println(a + " " + b);
		String answer = in.readLine();
		ArrayList<checkers.Field> flist = new ArrayList<checkers.Field>();
		for(String s : answer.split("\\+")) {
			flist.add(Field.buildFromString(s));
		}
		return flist;
	}
	
	public void initgui() {
		frame = new CheckersFrame(this);
	}
		
	public static void main(String[] args) throws IOException {
		CheckersClient c = new CheckersClient();
		c.initgui();
    }
}
