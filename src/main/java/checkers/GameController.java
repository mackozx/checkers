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
	
	public GameController(int playerSize) {
		playerNumber = playerSize;
		board = new SimpleBoard(playerSize);
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
		board.selectField(x, y, id);
	}
	
	public void runServer() throws IOException {
		ServerSocket listener = new ServerSocket(9090);
		/*Socket socket = listener.accept();        
		out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        */
		while(true) {
			/*String input = in.readLine();
			String[] inputAsStringArray = input.split("\\s+");
			int[] toSend = new int[inputAsStringArray.length];
			for(int i = 0; i < inputAsStringArray.length; i++) {
				toSend[i] = Integer.parseInt(inputAsStringArray[i]);
			}
			boardAction(toSend[0], toSend[1], toSend[2]);
			String output = getFieldsAsString();
			out.println(output);*/
			socket = listener.accept();
			out = new PrintWriter(socket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			new ClientThread(socket, in, out, this).start();
		}
	}
	
}
