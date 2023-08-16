import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyGUI {

	public static void main(String[] args) {
		// heavyweight container
		JFrame frame = new JFrame("My GUI Example"); // 생성 (메모리에)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 끌 시 terminate

		// frame -> heavy weight container (운영체제가 관리하는 중량 컨테이너)
		// lightweight container (경량 컨테이너) -> 즉, panel을 만들어서 관리하는 게 편함
		JPanel primary = new JPanel();
		primary.setPreferredSize(new Dimension(380, 270));
		primary.setBackground(Color.pink);
		primary.setLayout(null); // layout manager disable

		// component
		JLabel lbl1, lbl2;
		Font fnt = new Font("Monlo", Font.BOLD, 20);

		lbl1 = new JLabel("SKKU University");
		lbl1.setFont(fnt);
		lbl1.setForeground(Color.red);
		lbl1.setBounds(115, 50, 200, 50); // (100,50)부터 시작해서 width 200, height 50
		primary.add(lbl1);

		lbl2 = new JLabel("Applied Artificial Intelligence");
		lbl2.setFont(fnt);
		lbl2.setForeground(Color.white);
		lbl2.setBounds(35, 80, 350, 50);
		primary.add(lbl2);

		lbl2 = new JLabel("2021313031");
		lbl2.setFont(fnt);
		lbl2.setForeground(Color.magenta);
		lbl2.setBounds(133, 110, 200, 50);
		primary.add(lbl2);

		lbl2 = new JLabel("Oh, sujeong");
		lbl2.setFont(fnt);
		lbl2.setForeground(Color.white);
		lbl2.setBounds(135, 140, 200, 50);
		primary.add(lbl2);

		frame.getContentPane().add(primary); // 경량컨테이너라서 내가 관리
		frame.pack();
		frame.setVisible(true); // 생성된 객체 화면에 띄우기

	} // main()

} // MyGUI class
