import javax.swing.*;
import java.awt.*;

public class NestedPanels {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Nested Panels");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel subpanel1 = new JPanel();
		subpanel1.setPreferredSize(new Dimension(150,100));
		subpanel1.setBackground(Color.green);
		
		JLabel label1 = new JLabel("One");
		subpanel1.add(label1);
		
		JPanel subpanel2 = new JPanel();
		subpanel2.setPreferredSize(new Dimension(150,100));
		subpanel2.setBackground(Color.red);
		
		JLabel label2 = new JLabel("Two");
		subpanel2.add(label2);
		
		JPanel primary = new JPanel();
		primary.setBackground(Color.blue);
		primary.add(subpanel1);
		primary.add(subpanel2);
		
		frame.getContentPane().add(primary);
		frame.pack();
		frame.setVisible(true);
		

	} // main()

} // NestedPanels class
