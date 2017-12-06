package data_structure_homework;

import java.awt.Point;

public class TriangleArray {
	int max = 5000000;
	MyTriangle[] triangleArray = new MyTriangle[max];
	int length = 0;
	int maxIteration = 22; //in this design, can only hold 21 iterations. If want more iteration, it's better to use linkList
	int[] generation_location = new int[maxIteration]; //store the current number of triangles of each generation 


	public void push(MyTriangle t) {
		if (t.generation >= maxIteration ) {
			System.err.println("Can't hold so many triangles");
			return ;
		}
		int storeIndex = (int) (Math.pow(2, t.generation-1) - 1 + generation_location[t.generation]);
		if (length < max && storeIndex < max) {
			triangleArray[storeIndex] = t;
//			System.out.println(storeIndex);
			generation_location[t.generation] += 1;
			++length;
		} else {
			System.err.println("Too much triangles!");
		}
	}

//	This sort algorithm waste too much time, it's better not to use it
	public void sort() {
		for (int i = 0; i < length-1; i++) {
			int min = i;
			for (int j = i+1; j < length; j++) {		
				if (triangleArray[j].generation < triangleArray[min].generation) {
					min = j;
				}
			}
			swap(i, min);
		}
	}

	public void swap(int i, int j) {
		MyTriangle temp = triangleArray[i];
		triangleArray[i] = triangleArray[j];
		triangleArray[j] = temp;
	}

	public static void main(String[] args) {
		MyPoint p1 = new MyPoint(0, 0, 0);
		MyPoint p2 = new MyPoint(0, 0, 0);
		MyPoint p3 = new MyPoint(0, 0, 0);
		MyPoint p4 = new MyPoint(0, 0, 1);
		MyTriangle t1 = new MyTriangle(p1, p2, p3);
		MyTriangle t2 = new MyTriangle(p1, p2, p4);
		TriangleArray triangles = new TriangleArray();
		triangles.push(t2);
		triangles.push(t1);
		for (int i = 0; i < triangles.length; i++) {

			System.out.println(triangles.triangleArray[i].generation);
		}
		triangles.sort();
		for (int i = 0; i < triangles.length; i++) {

			System.out.println(triangles.triangleArray[i].generation);
		}


	}


}
