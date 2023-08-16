import javax.swing.JFrame;

public class DrawExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Draw Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//DrawPanel primary = new DrawPanel();
		//LinePanel primary = new LinePanel();
		RectPanel primary = new RectPanel();
		frame.getContentPane().add(primary);
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
