import javax.swing.*;
import java.awt.*;

public class GUIExam extends JPanel {
	
	private JLabel lbl;
	
	public GUIExam() {
		setBackground(Color.pink);
		setPreferredSize(new Dimension(400,300));
		
		lbl = new JLabel("GUI Example!!");
		lbl.setFont(new Font("Verdana", Font.BOLD, 16));
		lbl.setForeground(Color.yellow);
		add(lbl);
		
	}
	
	@Override
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		
		page.setColor(Color.red);
		page.drawRect(100, 100, 200, 100);
	}
	
}
