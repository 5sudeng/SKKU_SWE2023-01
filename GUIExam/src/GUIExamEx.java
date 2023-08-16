import javax.swing.*;

public class GUIExamEx extends JPanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("GUI Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		GUIExam primary = new GUIExam();
		frame.getContentPane().add(primary);
		
		frame.pack();
		frame.setVisible(true);
	}

}
