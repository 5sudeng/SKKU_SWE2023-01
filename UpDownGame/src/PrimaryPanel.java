import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PrimaryPanel extends JPanel {
	
	JPanel leftPanel, rightPanel;
	JLabel lblTitle, /*lblMark,*/ lblHint;
	//---------------------------------------
	// label 2 변수 3
	JLabel lblRange, lblCount;
	int nUp = 100;
	int nDown = 1;
	int nCount = 0;
	JButton btnRandom, btnInput;
	JTextField txtInput;
	int nRandom, nInput;
	//---------------------------------------
	// 2. declaration of listener object
	GameListener gameL;
	GameMouseListener gameML;
	//---------------------------------------
	MarkLabel lblMark;
	
	public PrimaryPanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(630,420));
		setLayout(null);
		// 2. creating of listener object
		gameL = new GameListener();
		gameML = new GameMouseListener();
		
		Font font = new Font("Verdana", Font.BOLD,26);
		
		leftPanel = new JPanel();
		leftPanel.setBounds(10, 10, 300, 400);
		leftPanel.setBackground(Color.cyan);
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		leftPanel.setLayout(null);
		add(leftPanel);
		
		//lblMark = new JLabel("?");
		lblMark = new MarkLabel();
		lblMark.setBounds(10, 90, 280, 240);
		lblMark.setHorizontalAlignment(SwingConstants.CENTER);
		lblMark.setFont(new Font("Verdana", Font.BOLD,120));
		lblMark.setVisible(false);
		leftPanel.add(lblMark);
		
		lblHint = new JLabel("RIGHT!!");
		lblHint.setBounds(10, 330, 280, 60);
		lblHint.setHorizontalAlignment(SwingConstants.CENTER);
		lblHint.setFont(new Font("Verdana", Font.BOLD,26));
		lblHint.setVisible(false);
		leftPanel.add(lblHint);
		
		lblTitle = new JLabel("UP-DOWN GAME");
		lblTitle.setBounds(10, 20, 280, 60);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(font);
		leftPanel.add(lblTitle);
		
		rightPanel = new JPanel();
		rightPanel.setBounds(320, 10, 300, 400);
		rightPanel.setBackground(Color.pink);
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		rightPanel.setLayout(null);
		add(rightPanel);
		
		nRandom = nInput = 0; //둘다 0으로 초기화
		
		Font bfont = new Font("Verdana", Font.BOLD, 20);
		btnRandom = new JButton("GENERATE..");
		btnRandom.setBounds(20, 80, 260, 60);
		btnRandom.setFont(font);
		// 3. add the object to the component
		btnRandom.addActionListener(gameL);
		btnRandom.addMouseListener(gameML);
		rightPanel.add(btnRandom);
		
		txtInput = new JTextField(); // layout 매니저 있을때 숫자 넣어서 가능
		txtInput.setBounds(20, 150, 125, 40);
		txtInput.setFont(bfont);
		// 3. add
		txtInput.addActionListener(gameL);
		txtInput.setEnabled(false);
		rightPanel.add(txtInput);
		
		btnInput = new JButton("INPUT");
		btnInput.setBounds(150, 150, 130, 40);
		btnInput.setEnabled(false);
		btnInput.setFont(bfont);
		btnInput.addMouseListener(gameML);
		// 3. add
		btnInput.addActionListener(gameL);
		rightPanel.add(btnInput);
		
		lblRange = new JLabel(nDown+"~"+nUp);
		lblRange.setBounds(30, 200, 260, 30);
		lblRange.setFont(bfont);
		lblRange.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(lblRange);
		
		lblCount = new JLabel("Count : "+nCount);
		lblCount.setBounds(30, 240, 260, 30);
		lblCount.setFont(bfont);
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(lblCount);
		
		
		
	} // constructor
	
	// 1. listener class (inner class의 경우 private class로)
	private class GameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) { // event handler를 갖는 listener class 만드는게 첫번째?
			// 4. event handling
			Object obj = event.getSource(); // event가 발생한 object 어떤 component인지를 받아옴
			if (obj == btnRandom) {
				nRandom = (int)(Math.random()*100) + 1 ; //0.0~1.0까지중에 실수 한개 만듦 -> 1~100
				//System.out.println(""+nRandom); // 문자열 + 숫자 -> 문자열 -> Integer.toString(nRandom) 해도 됨 
				lblMark.setText("?");
				lblMark.setVisible(true);
				lblHint.setVisible(false);
				txtInput.setEnabled(true);
				btnInput.setEnabled(true);
				btnRandom.setEnabled(false);
				lblRange.setVisible(true);
				nDown = 1;
				nUp = 100;
				nCount = 0;
				lblRange.setText((nDown+"~"+nUp));
				lblCount.setText("Count : "+nCount);
				
			} else if (obj == txtInput || obj == btnInput){
				nInput = Integer.parseInt(txtInput.getText()); 
				nCount++;
				lblCount.setText("Count : "+nCount);
				if (nRandom < nInput) {
					lblHint.setText("DOWN");
					nUp = nInput<nUp ? nInput : nUp;
					lblRange.setText((nDown+"~"+nUp));
					lblHint.setVisible(true);
				} else if (nRandom > nInput) {
					lblHint.setText("UP");
					nDown = nInput>nDown ? nInput : nDown;
					lblRange.setText((nDown+"~"+nUp));
					lblHint.setVisible(true);
				} else { // nRandom == nInput
					lblHint.setText("RIGHT!!");
					//lblMark.setText(""+nRandom);
					lblMark.setFinish(nRandom);
					lblMark.start();
					txtInput.setEnabled(false);
					btnInput.setEnabled(false);
					btnRandom.setEnabled(true);
					lblRange.setVisible(false);
					lblHint.setVisible(true);
					
					int result = JOptionPane.showConfirmDialog(lblRange, "Countinue?");
					if (result == JOptionPane.NO_OPTION)
						System.exit(0); //yes 하면 게임 다시 시작하게끔.. 초기화 -> 생성자 함수 참 / 객체 초기화.. thread는 새로 생성?
					else /*if (result == JOptionPane.YES_OPTION) */{
						lblMark.setVisible(false);
						lblMark = new MarkLabel();
						lblMark.setBounds(10, 90, 280, 240);
						lblMark.setHorizontalAlignment(SwingConstants.CENTER);
						lblMark.setFont(new Font("Verdana", Font.BOLD,120));
						lblMark.setVisible(false);
						leftPanel.add(lblMark);
						nDown = 1;
						nUp = 100;
						nCount = 0;
						lblRange.setText((nDown+"~"+nUp));
						lblCount.setText("Count : "+nCount);
						lblRange.setVisible(true);
						lblHint.setVisible(false);
					}
				} // if..else
				txtInput.setText(""); // get 다음에 set
				
			} //if..else if
			
		} // actionPerformed()
		
	} // GameListener class
	
	private class GameMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {} // 같은 좌표에서 눌렀다 떼면 클릭 
		@Override
		public void mousePressed(MouseEvent e) {} // 누르고 한픽셀이라도 움직이면 클릭 x -> 프레스, 릴리즈 
		@Override
		public void mouseReleased(MouseEvent e) {}

		
		@Override
		public void mouseEntered(MouseEvent e) {
			//Object obj = e.getSource(); // button으로 받아도됨 
			JButton btn = (JButton)e.getSource(); //-> 객체는 두개지만 인스턴스 한개 
			btn.setForeground(Color.red);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton btn = (JButton)e.getSource(); //-> 객체는 두개지만 인스턴스 한개 
			btn.setForeground(Color.black);
		}
		
	}
	
} // PrimaryPanel class
