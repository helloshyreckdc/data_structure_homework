package data_structure_homework;
import java.awt.*; 
import java.awt.event.*; 
import java.util.Random; 
import javax.swing.*; 
/** 
 *  
 * @author http://javaflex.iteye.com/ 
 * 
 */ 
public class GraphicsTest extends JFrame implements ActionListener { 
    public static final double PI = Math.PI / 180; 
    JPanel panel; 
    JPanel pnlCtl; 
    JButton button; 
    JButton button2; 
    Graphics2D g2; 
 
    public GraphicsTest(String string) { 
        super(string); 
    } 
 
    public void init() { 
        panel = new JPanel(); 
        pnlCtl = new JPanel(); 
        button = new JButton("tree"); 
        button2 = new JButton("clear"); 
        this.add(panel, BorderLayout.CENTER); 
        button.addActionListener(this); 
        button2.addActionListener(this); 
        pnlCtl.add(button); 
        pnlCtl.add(button2); 
        this.add(pnlCtl, BorderLayout.NORTH); 
        setSize(800, 600); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setVisible(true); 
        Dimension winSize = Toolkit.getDefaultToolkit().getScreenSize(); 
        this.setLocation((winSize.width - this.getWidth()) / 2, 
                (winSize.height - this.getHeight()) / 2); 
        g2 = (Graphics2D) panel.getGraphics(); 
    } 
 
    public static void main(String[] args) throws ClassNotFoundException, 
            InstantiationException, IllegalAccessException, 
            UnsupportedLookAndFeelException { 
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        GraphicsTest testPanel = new GraphicsTest("tree：QQ：328247676"); 
        testPanel.init(); 
    } 
 
     
    @Override 
    public void actionPerformed(ActionEvent e) { 
        if ("tree".equals(e.getActionCommand())) { 
            drawLeaf(g2, 400, 500, 100, 210+random.nextInt(100)); 
        } else if ("clear".equals(e.getActionCommand())) { 
            panel.getGraphics().clearRect(0, 0, 800, 800); 
        } 
    } 
    Random random=new Random(); 
    public void  drawLeaf(Graphics g, double x, double y, double L, double a) { 
        //random=new Random(); 
        //可以方面速度画以了解其算法 
//      try { 
//          Thread.sleep(1000); 
//      } catch (InterruptedException e) { 
//          // TODO Auto-generated catch block 
//          e.printStackTrace(); 
//      } 
        int red = random.nextInt(127); 
        int green = random.nextInt(127); 
        int blue = random.nextInt(127); 
//随机颜色 
        g.setColor(new Color(red, green, blue)); 
        double x1, x2, x1L, x2L, x2R, x1R, y1, y2, y1L, y2L, y2R, y1R; 
        float deflection = 50-random.nextInt(20);//侧干主干的夹角 
        float intersection = random.nextInt(40)-20;//主干偏转角度 
        float depth = 2+random.nextInt(2);//限制递归深度 
        float ratio = 3f;//主干侧干长度比(可调整使其更茂密或稀疏) 
        float ratio2 = 1.2f;//上级主干与本级主干长度比（可调整使其变高低） 
        if (L > depth) { 
            x2=x+L*Math.cos(a*PI); 
            y2=y+L*Math.sin(a*PI); 
            x2R=x2+L/ratio*Math.cos((a+deflection)*PI); 
            y2R=y2+L/ratio*Math.sin((a+deflection)*PI); 
            x2L=x2+L/ratio*Math.cos((a-deflection)*PI); 
            y2L=y2+L/ratio*Math.sin((a-deflection)*PI); 
            x1=x+L/ratio*Math.cos(a*PI); 
            y1=y+L/ratio*Math.sin(a*PI); 
            x1L=x1+L/ratio*Math.cos((a-deflection)*PI); 
            y1L=y1+L/ratio*Math.sin((a-deflection)*PI); 
            x1R=x1+L/ratio*Math.cos((a+deflection)*PI); 
            y1R=y1+L/ratio*Math.sin((a+deflection)*PI); 
            g.drawLine((int)x,(int)y,(int)x2,(int)y2); 
            g.drawLine((int)x2,(int)y2,(int)x2R,(int)y2R); 
            g.drawLine((int)x2,(int)y2,(int)x2L,(int)y2L); 
            g.drawLine((int)x1,(int)y1,(int)x1L,(int)y1L);          g.drawLine((int)x1,(int)y1,(int)x1R,(int)y1R); 
            drawLeaf(g,x2,y2,L/ratio2,a+intersection); 
            drawLeaf(g,x2R,y2R,L/ratio,a+deflection); 
            drawLeaf(g,x2L,y2L,L/ratio,a-deflection); 
            drawLeaf(g,x1L,y1L,L/ratio,a-deflection); 
            drawLeaf(g,x1R,y1R,L/ratio,a+deflection); 
        } 
    } 
} 