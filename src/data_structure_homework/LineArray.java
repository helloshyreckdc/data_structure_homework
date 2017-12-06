package data_structure_homework;



public class LineArray {
	int max = 5000000;
	MyLine[] lineArray = new MyLine[max];
	int length = 0;
	
	
	public void push(MyLine l) {
		if (length < max) {
			lineArray[length] = l;
			++length;
		} else {
			System.err.println("Too much lines!");
		}
	}
	
	
}
