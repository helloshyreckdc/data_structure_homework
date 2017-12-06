package data_structure_homework;



public class PointArray {
	int max = 5000000;
	MyPoint[] pArray = new MyPoint[max];
	int length = 0;
	
	
	public void push(MyPoint p) {
		if (length < max) {
			pArray[length] = p;
			++length;
		} else {
			System.err.println("Too much points!");
		}
	}
	
	
}
