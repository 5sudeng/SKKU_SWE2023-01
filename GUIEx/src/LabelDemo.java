import java.awt.*;
import javax.swing.*;

public class LabelDemo {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Label Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon("cat.png");
		
		JLabel lbl1, lbl2, lbl3;
		lbl1 = new JLabel("Cat Left", icon, SwingConstants.CENTER);
		
		lbl2 = new JLabel("Cat Rignt", icon, SwingConstants.CENTER);
		lbl2.setHorizontalTextPosition(SwingConstants.LEFT);
		lbl2.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		lbl3 = new JLabel("Cat Above", icon, SwingConstants.CENTER);
		lbl3.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl3.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.cyan);
		panel.setPreferredSize(new Dimension(200,250));
		panel.add(lbl1);
		panel.add(lbl2);
		panel.add(lbl3);
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		

	} // main()

} // LabelDemo class
