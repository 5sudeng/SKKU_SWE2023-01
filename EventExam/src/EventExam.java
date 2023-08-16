import javax.swing.JFrame;

public class EventExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Event Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PrimaryPanel primary = new PrimaryPanel() ;
		frame.getContentPane().add(primary);
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
