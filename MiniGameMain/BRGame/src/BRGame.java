import javax.swing.JFrame;

public class BRGame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("31th DDONGCARON GAME");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); // window 크기 변경 불가
		
		PrimaryPanel primary = new PrimaryPanel();
		frame.getContentPane().add(primary);
		
		frame.pack();
		frame.setVisible(true);

	}//main()

}// BRGame class
