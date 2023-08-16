import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PrimaryPanel extends JPanel {
	
	private int nCount;
	private JLabel lblCount;
	private JButton btnIncrement, btnDecrement;
	// 2. declaration of listener object
	// 리스너 객체 선언 및 생성 
	private ButtonListener buttonL;
	
	public PrimaryPanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(400,200));
		setLayout(null);
		
		// 2. creating of listener object
		buttonL = new ButtonListener();
		
		nCount = 0;
		
		lblCount = new JLabel("COUNT : "+nCount);
		lblCount.setBounds(50, 20, 200, 50);
		lblCount.setFont(new Font("Verdana", Font.BOLD, 20));
		lblCount.setForeground(Color.gray);
		add(lblCount);
		
		btnIncrement = new JButton("INCREMENT");
		btnIncrement.setBounds(50, 80, 150, 40);
		btnIncrement.setForeground(Color.blue);
		btnIncrement.setBackground(Color.lightGray);
		btnIncrement.setFont(new Font("Verdana", Font.PLAIN, 12));
		// 3. add the listener object to the componenet
		// 리스널 객체를 해당 컴포넌트에 add
		btnIncrement.addActionListener(buttonL);
		add(btnIncrement);
		
		btnDecrement = new JButton("DECREMENT");
		btnDecrement.setBounds(205, 80, 150, 40);
		btnDecrement.setForeground(Color.red);
		btnDecrement.setBackground(Color.lightGray);
		btnDecrement.setFont(new Font("Verdana", Font.PLAIN, 12));
		// 3. add the listener object
		btnDecrement.addActionListener(buttonL);
		add(btnDecrement);
		
	} // constructor
	
	// Event handling (inner class)
	// 1. listener class 
	// 이벤트 핸들러를 갖는 리스너 클래스 구현 
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) { // 이벤트가 발생한 컴포넌트의 각종 정보가 담겨있음 
			// 4. event handling
			Object obj = event.getSource(); // 이벤트가 발생한 컴포넌트가 됨
			
			if (obj == btnIncrement) nCount++;
			else if (obj == btnDecrement) nCount--;
			
			lblCount.setText("COUNT : "+nCount);
			
			if (nCount > 0) lblCount.setForeground(Color.blue);
			else if (nCount < 0) lblCount.setForeground(Color.red);
			else lblCount.setForeground(Color.black);
			
		}
		
	} // ButtonLister class
	
} // PrimaryPanel class
