package checkers;

import java.util.ArrayList;

public interface Board {
	
	void createFields();
	
	ArrayList<Field> getFields();
	
	void selectField(int x, int y, int id);
	
	void onStart();
	
	int getSize();
	
	Field getFieldByPosition(int x, int y);
	
	Field getFieldByLinePosition(int x, int y);
}
