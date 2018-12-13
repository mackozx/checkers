package checkers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameController {

	private SimpleBoard board;
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ServerSocket listener;
	private int playerNumber;
	private ArrayList<ClientThread> clientsList;
	
	public GameController(int playerSize) {
		playerNumber = playerSize;
		board = new SimpleBoard(playerSize);
		clientsList = new ArrayList<ClientThread>();
		try {
			runServer();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getBoardSize() {
		return board.getSize();
	}
	
	public ArrayList<Field> getBoardFields() {
		return board.getFields();
	}
	
	public String getFieldsAsString() {
		String fieldsstring = "";
		for(Field field : getBoardFields()) {
			fieldsstring += field.toString() + "+";
		}
		
		return fieldsstring;
	}
	
	public void boardAction(int x, int y, int id) {
		if(id == board.getActivePlayerId()) {
			board.selectField(x, y, id);
		}
	}
	
	public void runServer() throws IOException {
		ServerSocket listener = new ServerSocket(9090);
		while(true) {
			socket = listener.accept();
			out = new PrintWriter(socket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        ClientThread ct = new ClientThread(socket, in, out, this);
	        clientsList.add(ct);
	        ct.start();
		}
	}
	
	public void sendToAll(String s) {
		for(ClientThread ct : clientsList) {
			ct.sendOutput(s);
		}
	}
	
}
