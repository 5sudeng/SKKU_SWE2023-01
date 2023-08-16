import javax.swing.JFrame;

public class BRGame {

	public static void main(String[] args) {
		// main frame
		JFrame frame = new JFrame("31th DDONGCARON GAME");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); // window 크기 변경 불가
		
		// PrimaryPanel
		PrimaryPanel primary = new PrimaryPanel();
		frame.getContentPane().add(primary);
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); // 컴퓨터 화면 가운데에 띄우기

	}// main()

}// BRGame class
