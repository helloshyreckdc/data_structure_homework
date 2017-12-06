package data_structure_homework;
import java.awt.*; 
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.*; 
/** 
 *  
 * @author shyreckdc
 * 
 */ 
public class GraphicsTest extends JFrame implements ActionListener { 
	public static final double PI = Math.PI / 180; 
	JPanel panel; 
	JPanel pnlCtl; 
	JButton btnStart; 
	JButton btnClear; 
	JButton btnFirstLine;

	MyPoint startPoint;
	MyPoint endPoint;
	MyLine firstLine;
	PointArray points = new PointArray();
	LineArray lines = new LineArray();
	TriangleArray triangles = new TriangleArray();

	Graphics2D g2;
	int iterateTimes = 20; // maximum is 21, if want more, data structure should be proved
	boolean first_draw_triange = true;

	public GraphicsTest(String string) { 
		super(string); 
	} 

	public void init() { 
		panel = new JPanel(); 
		pnlCtl = new JPanel(); 
		btnFirstLine = new JButton("add first line"); 
		btnStart = new JButton("start"); 
		btnClear = new JButton("clear"); 

		this.add(panel, BorderLayout.CENTER); 
		btnStart.addActionListener(this); 
		btnClear.addActionListener(this); 
		btnFirstLine.addActionListener(this);
		pnlCtl.add(btnFirstLine);
		pnlCtl.add(btnStart); 
		pnlCtl.add(btnClear); 
		this.add(pnlCtl, BorderLayout.NORTH); 
		setSize(800, 600); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true); 
		Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		this.setLocation((winSize.width - this.getWidth()) / 2, 
				(winSize.height - this.getHeight()) / 2); 
		g2 = (Graphics2D) panel.getGraphics(); 
		startPoint = new MyPoint(300, 300, 0);
		endPoint = new MyPoint(500, 300, 0);
		firstLine = new MyLine(startPoint, endPoint);
		points.push(startPoint);
		points.push(endPoint);
		lines.push(firstLine);
	} 

	public static void main(String[] args) throws ClassNotFoundException, 
	InstantiationException, IllegalAccessException, 
	UnsupportedLookAndFeelException { 
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		GraphicsTest testPanel = new GraphicsTest("iteration"); 
		testPanel.init(); 
	} 


	@Override 
	public void actionPerformed(ActionEvent e) { 
		if ("start".equals(e.getActionCommand())) { 
			new Thread(new Runnable() {

				@Override
				public void run() {
					if (first_draw_triange) {
						long sysDate = System.currentTimeMillis();
						iterate(firstLine, iterateTimes);
						System.out.println("Time used to compute: " + (System.currentTimeMillis()-sysDate) + " milliseconds");
						
//						triangles.sort();
//						System.out.println(System.currentTimeMillis()-sysDate);
					}
					System.out.println(points.length + " points");
					System.out.println(lines.length + " lines");
					System.out.println(triangles.length + " triangles");
					first_draw_triange = false;
					drawTriangles(g2, triangles);
				}
			}).start();
			
		} else if ("clear".equals(e.getActionCommand())) { 
			panel.getGraphics().clearRect(0, 0, 800, 800); 
		} else if ("add first line".equals(e.getActionCommand())) {
			drawLine(g2, firstLine);


		}
	} 

	public void drawLine(Graphics g, MyLine line) {
		g.setColor(Color.BLACK); 
		g.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
	}
	
	public void drawTriangles(Graphics g, TriangleArray tArray) {
		int iterationTimes = 0;
		for (int i = 0; i < tArray.length; i++) {
			if (tArray.triangleArray[i].generation != iterationTimes) {
				iterationTimes = tArray.triangleArray[i].generation;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			tArray.triangleArray[i].fillTriangle(g);
		}
	}



	public void  iterate(MyLine line, int total) { 
		if (line.generation < total) {
			MyPoint third = line.calculateThirdPoint();
			points.push(third);
			MyLine line1 = new MyLine(line.p1, third);
			MyLine line2 = new MyLine(third, line.p2);
			lines.push(line1);
			lines.push(line2);
			MyTriangle triangle = new MyTriangle(line.p1, third, line.p2);
			triangles.push(triangle);
			iterate(line1, total);
			iterate(line2, total);
		}
	} 
} 