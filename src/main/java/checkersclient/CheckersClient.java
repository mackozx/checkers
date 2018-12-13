package checkersclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import checkers.Field;

import java.io.PrintWriter;

public class CheckersClient {

	private int clientId;
	private BufferedReader in;
	private PrintWriter out;
	private CheckersFrame frame;
	private Socket socket;
	private Thread connectionThread;
	public CheckersClient(int id) {
		clientId = id;
		String serverAddress = "127.0.0.1";
		try {
			socket = new Socket(serverAddress, 9090);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch(IOException e) {
			e.printStackTrace();
		}
		connectionThread = new Thread() {
			public void run() {
				while(true) {
					try {
						String answer = in.readLine();
						ArrayList<checkers.Field> flist = new ArrayList<checkers.Field>();
						for(String s : answer.split("\\+")) {
							flist.add(Field.buildFromString(s));
						}
						frame.getPanel().setFields(flist);
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		connectionThread.start();
	}
	
	public /*ArrayList<checkers.Field>*/void connect(int a, int b) throws IOException {
		out.println(a + " " + b + " " + clientId);
		//String answer = in.readLine();
		//ArrayList<checkers.Field> flist = new ArrayList<checkers.Field>();
		//for(String s : answer.split("\\+")) {
		//	flist.add(Field.buildFromString(s));
		//}
		//return flist;
	}
	
	public void initgui() {
		frame = new CheckersFrame(this);
	}
		
	public static void main(String[] args) throws IOException {
		String id = JOptionPane.showInputDialog("Id gracza");
		//String id = "0";
		CheckersClient c = new CheckersClient(Integer.parseInt(id));
		c.initgui();
    }
}
