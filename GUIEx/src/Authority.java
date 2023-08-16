import java.awt.*;
import javax.swing.*;

public class Authority {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Authority");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel primary = new JPanel();
		primary.setBackground(Color.yellow);
		primary.setPreferredSize(new Dimension(250, 75));
		
		JLabel lbl1 = new JLabel("Question authority,");
		JLabel lbl2 = new JLabel("but raise your hand first.");
		
		primary.add(lbl1);
		primary.add(lbl2);
		
		frame.getContentPane().add(primary);
		frame.pack();
		frame.setVisible(true);
	} // main()

} // Authority class
