package data_structure_homework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;

public class MyTriangle {
	MyPoint p1;
	MyPoint p2;
	MyPoint p3;
	int generation = 0;

	public MyTriangle(MyPoint p1, MyPoint p2, MyPoint p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		int bigger = (p1.generation > p2.generation)?p1.generation:p2.generation;
		this.generation = ( bigger > p3.generation) ? bigger:p3.generation;
	}

	public void fillTriangle(Graphics g) {
		Polygon triangle=new Polygon();
		triangle.addPoint(p1.x, p1.y);
		triangle.addPoint(p2.x, p2.y);
		triangle.addPoint(p3.x, p3.y);
		triangle.addPoint(p1.x, p1.y);
//		g.setColor(Color.BLACK); 
		g.drawPolygon(triangle);
		Random random=new Random(generation);
		int red = random.nextInt(250); 
		int green = random.nextInt(250); 
		int blue = random.nextInt(250); 
		//随机颜色 
		g.setColor(new Color(red, green, blue)); 
		g.fillPolygon(triangle);
	}

	public static void main(String[] args) {
		
	}


}
