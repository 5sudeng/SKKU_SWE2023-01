import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class PrimaryPanel extends JPanel {

	JPanel gamePanel, comPanel, userPanel, imagePanel;
	JLabel lblTitle, lblDdong, lblComimage, lblComname, lblUserimage, lblUsername, lblUsersays;
	JButton btnOne, btnTwo, btnThree, btnHome, btnStart;
	ImageIcon[] icons;
	JLabel[] lblImages;
	MotionLabel lblComsays;
	int nMacaron, nRandom, nLastEat;
	boolean bFirstClick, bEatFinish;
	EatLabel threadEat;
	EatComThread threadComEat;

	// 2. declaration of listener object
	GameListener gameL;

	public PrimaryPanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(630, 420));
		setLayout(null);

		// 2. creating of listener object
		gameL = new GameListener();

		gamePanel = new JPanel();
		gamePanel.setBounds(0, 0, 360, 420);
		gamePanel.setBackground(new Color(212, 237, 214));
		gamePanel.setLayout(null);
		add(gamePanel);

		btnHome = new JButton("<");
		btnHome.setBounds(5, 10, 40, 40);
		btnHome.setBackground(Color.green);
		btnHome.setFont(new Font("Verdana", Font.BOLD, 7));
		btnHome.addActionListener(gameL);
		gamePanel.add(btnHome);

		lblTitle = new JLabel("31th DDONGCARON");
		lblTitle.setBounds(40, 0, 280, 60);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 23));
		gamePanel.add(lblTitle);

		lblDdong = new JLabel(new ImageIcon("images/ddong.png"));
		lblDdong.setBounds(150, 350, 60, 60);
		gamePanel.add(lblDdong);

		btnStart = new JButton("START!");
		btnStart.setBounds(20, 350, 110, 60);
		btnStart.setBackground(Color.red);
		btnStart.setFont(new Font("Verdana", Font.BOLD, 16));
		btnStart.addActionListener(gameL);
		gamePanel.add(btnStart);

		icons = new ImageIcon[30];
		for (int i = 0; i < 30; i++) {
			icons[i] = new ImageIcon("images/macaron.png");
		}

		imagePanel = new JPanel(new GridLayout(5, 6));
		imagePanel.setBounds(20, 60, 320, 290);
		gamePanel.add(imagePanel);

		lblImages = new JLabel[30];
		for (int i = 0; i < 30; i++) {
			lblImages[i] = new JLabel();
			lblImages[i].setIcon(icons[i]);
			imagePanel.setOpaque(false); // png 배경 투명화
			imagePanel.add(lblImages[i]);

		}

		nMacaron = nLastEat= 0;
		bFirstClick = false;
		bEatFinish = false;
		
		threadEat = new EatLabel();
		threadEat.setIcons(icons);
		threadEat.setImages(lblImages);

		comPanel = new JPanel();
		comPanel.setBounds(360, 0, 270, 180);
		comPanel.setBackground(new Color(209, 209, 209));
		comPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		comPanel.setLayout(null);
		add(comPanel);

		lblComimage = new JLabel(new ImageIcon("images/computer.png"));
		lblComimage.setBounds(60, 10, 150, 100);
		comPanel.add(lblComimage);

		lblComname = new JLabel("COMPUTER");
		lblComname.setBounds(85, 110, 100, 20);
		lblComname.setFont(new Font("Verdana", Font.BOLD, 15));
		comPanel.add(lblComname);
		
		lblComsays = new MotionLabel();
		lblComsays.setText(" ");
		lblComsays.setHorizontalAlignment(MotionLabel.CENTER);
		lblComsays.setBounds(60, 130, 150, 50);
		lblComsays.setFont(new Font("Verdana", Font.BOLD, 26));
		lblComsays.setBackground(new Color(209, 209, 209));
		lblComsays.setOpaque(true);
		comPanel.add(lblComsays);

		userPanel = new JPanel();
		userPanel.setBounds(360, 180, 270, 240);
		userPanel.setBackground(new Color(209, 209, 209));
		userPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		userPanel.setLayout(null);
		add(userPanel);

		lblUserimage = new JLabel(new ImageIcon("images/user.png"));
		lblUserimage.setBounds(85, 10, 100, 100);
		userPanel.add(lblUserimage);

		lblUsername = new JLabel("ME", JLabel.CENTER);
		lblUsername.setBounds(85, 110, 100, 20);
		lblUsername.setFont(new Font("Verdana", Font.BOLD, 15));
		userPanel.add(lblUsername);

		lblUsersays = new JLabel("YOU FIRST", JLabel.CENTER);
		lblUsersays.setBounds(30, 130, 210, 50);
		lblUsersays.setFont(new Font("Verdana", Font.BOLD, 26));
		lblUsersays.setVisible(false);
		userPanel.add(lblUsersays);

		btnOne = new JButton("EAT 1");
		btnOne.setBounds(15, 180, 78, 55);
		btnOne.setFont(new Font("Verdana", Font.BOLD, 13));
		btnOne.setBackground(Color.red);
		btnOne.setBorder(BorderFactory.createRaisedBevelBorder());
		btnOne.setEnabled(false);
		btnOne.addActionListener(gameL);
		userPanel.add(btnOne);

		btnTwo = new JButton("EAT 2");
		btnTwo.setBounds(97, 180, 78, 55);
		btnTwo.setFont(new Font("Verdana", Font.BOLD, 13));
		btnTwo.setBackground(Color.yellow);
		btnTwo.setBorder(BorderFactory.createRaisedBevelBorder());
		btnTwo.setEnabled(false);
		btnTwo.addActionListener(gameL);
		userPanel.add(btnTwo);

		btnThree = new JButton("EAT 3");
		btnThree.setBounds(179, 180, 78, 55);
		btnThree.setFont(new Font("Verdana", Font.BOLD, 13));
		btnThree.setBackground(Color.green);
		btnThree.setBorder(BorderFactory.createRaisedBevelBorder());
		btnThree.setEnabled(false);
		btnThree.addActionListener(gameL);
		userPanel.add(btnThree);
	}// constructor

	// 1. listener class
	private class GameListener implements ActionListener {
		public void gameInit() {
			lblTitle.setText("31th DDONGCARON");
			
			for (int i = 0; i < 30; i++) {
				icons[i] = new ImageIcon("images/macaron.png");
			}
			
			for (int i = 0; i < 30; i++) {
				lblImages[i].setIcon(icons[i]);
			}
			
			nMacaron = nLastEat= 0;
			bFirstClick = false;
			bEatFinish = false;
			
			threadEat = new EatLabel();
			threadEat.setIcons(icons);
			threadEat.setImages(lblImages);
			
			
			threadComEat = new EatComThread();
			
			lblComimage.setIcon(new ImageIcon("images/computer.png"));
			
			
			lblComsays = new MotionLabel();
			lblComsays.setText(" ");
			lblComsays.setHorizontalAlignment(MotionLabel.CENTER);
			lblComsays.setBounds(60, 130, 150, 50);
			lblComsays.setFont(new Font("Verdana", Font.BOLD, 26));
			lblComsays.setBackground(new Color(209, 209, 209));
			lblComsays.setOpaque(true);
			comPanel.add(lblComsays);
			
			lblUserimage.setIcon(new ImageIcon("images/user.png"));
			lblUsersays.setText("YOU FIRST");
			lblUsersays.setHorizontalAlignment(MotionLabel.CENTER);
			lblUsersays.setVisible(false);
			
			btnOne.setEnabled(false);
			btnTwo.setEnabled(false);
			btnThree.setEnabled(false);
			
			btnStart.setEnabled(true);
			
			
		}//gameInit()
		// Comsays thread 초기화
		public void initMotionLabel() {
			lblComsays = new MotionLabel();
			lblComsays.setText(" ");
			lblComsays.setHorizontalAlignment(MotionLabel.CENTER);
			lblComsays.setBounds(60, 130, 150, 50);
			lblComsays.setBackground(new Color(209, 209, 209));
			lblComsays.setOpaque(true);
			lblComsays.setFont(new Font("Verdana", Font.BOLD, 26));
			lblComsays.setVisible(true);
			comPanel.add(lblComsays);
		}

		// 이미지 변경
		
		 public void eatMacaron(int n) { 
			threadEat = new EatLabel();
			threadEat.setIcons(icons);
			threadEat.setImages(lblImages);
			threadEat.setnMacaron(nMacaron);
			threadEat.setnEat(n);
			threadEat.start();		 
		 }// eatMacaron
		 
		 public void eatComMacaron(int n) { 
				threadComEat = new EatComThread();
				threadComEat.setIcons(icons);
				threadComEat.setImages(lblImages);
				threadComEat.setnMacaron(nMacaron);
				threadComEat.setnEat(n);
				threadComEat.setnLastEat(nLastEat);
				threadComEat.start();		 
			 }// eatComMacaron
		 
		//replay?
		public void rePlay() {
			int result;
			result = JOptionPane.showConfirmDialog(imagePanel, "CONTINUE?");
			if (result == JOptionPane.YES_OPTION) {
				gameInit();
			}else if (result == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		}//Replay()
		
		public void comSays() {
			
			if (nMacaron==27) {
				nMacaron += 3;
				nRandom = 3;
			}
			else if (nMacaron==28) {
				nMacaron += 2;
				nRandom = 2;
			}
			else if (nMacaron==29) {
				nMacaron += 1;
				nRandom = 1;
			}
			else if (nMacaron==30) {
				nMacaron += 1;
				nRandom = 1;
			}
			
			else if (nMacaron<28) {
				nRandom = (int)(Math.random()*3)+1;
				nMacaron += nRandom;
			}
			
			lblComsays.setnMacaron(nMacaron);
			lblComsays.setnRandom(nRandom);
			lblComsays.setnLastEat(nLastEat);
			lblComsays.start();
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// 4. event handling
			Object obj = e.getSource();
			if (obj == btnStart) { // 시작버튼 누르면 나부터 시작
				btnOne.setEnabled(true);
				btnTwo.setEnabled(true);
				btnThree.setEnabled(true);

				lblComsays.setText("-");
				lblUsersays.setVisible(true);

				btnStart.setEnabled(false);

				
			}

			if (obj == btnOne) {
				if (bFirstClick == true) {
					initMotionLabel();
				}
				bFirstClick = true;
				
				
				nMacaron += 1;
				nLastEat = 1;
				lblUsersays.setText("" + nMacaron);
				if (nMacaron >= 31) {
					lblTitle.setText("YOU LOSE...");
					lblUserimage.setIcon(new ImageIcon("images/user_lose.png"));
					lblComimage.setIcon(new ImageIcon("images/computer_win.png"));
					rePlay();
				}
				else {
					eatMacaron(1);
					comSays();
					
					
					if (nMacaron >= 31) {
						lblTitle.setText("YOU WIN!!!");
						lblUserimage.setIcon(new ImageIcon("images/user_win.png"));
						lblComimage.setIcon(new ImageIcon("images/computer_lose.png"));
						rePlay();
					}
					else eatComMacaron(nRandom);
					
				}
				//내가 다 먹으면 com 시작
				
				
				
				
			} 
			else if (obj == btnTwo) {
				if (bFirstClick == true) {
					initMotionLabel();
				}
				bFirstClick = true;
				
				nMacaron += 2;
				nLastEat = 2;
				lblUsersays.setText((nMacaron - 1) + ", " + nMacaron);
				if (nMacaron >= 31) {
					lblTitle.setText("YOU LOSE...");
					lblUserimage.setIcon(new ImageIcon("images/user_lose.png"));
					lblComimage.setIcon(new ImageIcon("images/computer_win.png"));
					rePlay();
				}
				else {
					eatMacaron(2);
					comSays();
					
					
					if (nMacaron >= 31) {
						lblTitle.setText("YOU WIN!!!");
						lblUserimage.setIcon(new ImageIcon("images/user_win.png"));
						lblComimage.setIcon(new ImageIcon("images/computer_lose.png"));
						rePlay();
					}
					else eatComMacaron(nRandom);
				}
				
			} 
			
			else if (obj == btnThree) {
				if (bFirstClick == true) {
					initMotionLabel();
				}
				bFirstClick = true;
				
				nMacaron += 3;
				nLastEat = 3;
				
				lblUsersays.setText((nMacaron - 2) + ", " + (nMacaron - 1) + ", " + nMacaron);
				if (nMacaron >= 31) {
					lblTitle.setText("YOU LOSE...");
					lblUserimage.setIcon(new ImageIcon("images/user_lose.png"));
					lblComimage.setIcon(new ImageIcon("images/computer_win.png"));
					rePlay();
				}
				else {
					eatMacaron(3);
					comSays();
					
					if (nMacaron >= 31) {
						lblTitle.setText("YOU WIN!!!");
						lblUserimage.setIcon(new ImageIcon("images/user_win.png"));
						lblComimage.setIcon(new ImageIcon("images/computer_lose.png"));
						rePlay();
					}
					else eatComMacaron(nRandom);
				}
				
				
				
			}
			
			
			

		}// actionPerformed()

	}// GameListener class

}// PrimaryPanel class
