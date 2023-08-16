import javax.swing.JFrame;

public class UpDownGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("UP-DOWN GAME");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		PrimaryPanel primary = new PrimaryPanel() ;
		frame.getContentPane().add(primary);
		
		frame.pack();
		frame.setVisible(true);
	}

}
