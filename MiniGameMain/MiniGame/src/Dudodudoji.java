import javax.swing.JFrame;

public class Dudodudoji {

	public static void main(String[] args) {
		JFrame frame = new JFrame("DUDOJI GAME");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		DudojiGame dudoji = new DudojiGame() ;
		frame.getContentPane().add(dudoji);
		
		frame.pack();
		frame.setVisible(true);

	}

}
