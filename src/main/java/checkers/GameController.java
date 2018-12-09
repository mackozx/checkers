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
	
	public GameController() {
		board = new SimpleBoard();
		runServer();
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
	
	public void boardAction(int x, int y) {
		board.selectField(x, y);
	}
	
	public void runServer() {
		try {
            ServerSocket listener = new ServerSocket(9090);
            try {
                while(true) {
                    Socket socket = listener.accept();
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String input = in.readLine();
                        String[] inputAsStringArray = input.split("\\s+");
                        int[] toSend = new int[inputAsStringArray.length];
                        for(int i = 0; i < inputAsStringArray.length; i++) {
                        	toSend[i] = Integer.parseInt(inputAsStringArray[i]);
                        }
                        boardAction(toSend[0], toSend[1]);
                        
                        String output = getFieldsAsString();
                        out.println(output);
                        
                    } finally {
                        socket.close();
                    }
                }    
            } finally {
                listener.close();
            }
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}
	
}
