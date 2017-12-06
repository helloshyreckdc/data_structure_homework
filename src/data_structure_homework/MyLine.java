package data_structure_homework;



public class MyLine {
	MyPoint p1;
	MyPoint p2;
	int generation = 0;
	
	public MyLine(MyPoint p1, MyPoint p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.generation = (p1.generation > p2.generation)?p1.generation:p2.generation;
	}
	
	public MyPoint calculateThirdPoint() {
		MyPoint third = new MyPoint(0, 0, this.generation+1);
		double reverse_p1_y = 600 - p1.y;
		double reverse_p2_y = 600 - p2.y;
		double length= Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (reverse_p1_y-reverse_p2_y)*(reverse_p1_y-reverse_p2_y));
		double x_before_rotate, y_before_rotate;
		x_before_rotate = y_before_rotate = length / 2;
		double a, b, c, d, theta;
		if (p1.x != p2.x) {
			theta = Math.atan2(reverse_p2_y-reverse_p1_y, p2.x-p1.x);
			
		}else if (reverse_p2_y > reverse_p1_y) {
			theta = Math.PI / 2;
		}else {
			theta = -Math.PI / 2;
		}
		//System.out.println(theta);
		a = Math.cos(theta);
		b = -Math.sin(theta);
		c = Math.sin(theta);
		d = a;
		third.x = (int) (p1.x + (a*x_before_rotate + b*y_before_rotate));
		third.y = 600 - (int) (reverse_p1_y + (c*x_before_rotate + d*y_before_rotate));
		return third;
	}
	
	public static void main(String[] args) {
		MyPoint p1 = new MyPoint(0, 0, 0);
		MyPoint p2 = new MyPoint(1000, 0, 0);
		MyLine line = new MyLine(p1, p2);
		MyPoint p3 = line.calculateThirdPoint();
		System.out.println(p3.x + " " + p3.y + " " + p3.generation);
	}
}
