import java.awt.*;
import javax.swing.*;

public class NestedPanel {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Nested Panel Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 630x420 300x400
		JPanel primaryPanel = new JPanel();
		primaryPanel.setBackground(Color.white);
		primaryPanel.setPreferredSize(new Dimension(630, 420));
		primaryPanel.setLayout(null); // layout manager 없음 setbound로 위치 지정 해야

		JPanel leftPanel, rightPanel;
		leftPanel = new JPanel();
		leftPanel.setBounds(10, 10, 300, 400);
		leftPanel.setBackground(Color.pink);
		rightPanel = new JPanel();
		rightPanel.setBounds(320, 10, 300, 400);
		rightPanel.setBackground(Color.cyan);

		leftPanel.setLayout(null);
		rightPanel.setLayout(null);

		primaryPanel.add(leftPanel);
		primaryPanel.add(rightPanel);

		Font fnt = new Font("Monlo", Font.BOLD, 30);
		
		ImageIcon icon = new ImageIcon("images/image100x100.jpeg");

		JLabel lblOne, lblTwo;
		lblOne = new JLabel("ONE", icon, SwingConstants.CENTER);
		lblOne.setFont(fnt);
		lblOne.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOne.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblOne.setBounds(0, 0, 300, 400);
		lblOne.setHorizontalAlignment(SwingConstants.CENTER);
		lblOne.setVerticalAlignment(SwingConstants.BOTTOM);
		leftPanel.add(lblOne);
		

		lblTwo = new JLabel("TWO");
		lblTwo.setFont(fnt);
		lblTwo.setHorizontalAlignment(SwingConstants.CENTER);// L R C / U C B
		lblTwo.setBounds(0, 0, 300, 400);

		rightPanel.add(lblTwo);

		frame.getContentPane().add(primaryPanel);
		frame.pack();
		frame.setVisible(true);

	} // main()

} // NestedPanel class
