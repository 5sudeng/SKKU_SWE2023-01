import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class PrimaryPanel extends JPanel {

	JPanel gamePanel, comPanel, userPanel, imagePanel;
	JLabel lblTitle, lblDdong, lblComimage, lblComname, lblUserimage, lblUsername, lblUsersays;
	JButton btnOne, btnTwo, btnThree, btnStart;
	ImageIcon[] icons;
	JLabel[] lblImages;
	MotionLabel lblComsays;
	int nMacaron, nRandom, nLastEat;
	boolean bFirstClick;
	EatThread threadEat;
	EatComThread threadComEat;

	// 2. declaration of listener object
	GameListener gameL;

	public PrimaryPanel() {
		// 게인 전체 화면 panel
		setBackground(Color.white);
		setPreferredSize(new Dimension(630, 420));
		setLayout(null);

		// 2. creating of listener object
		gameL = new GameListener();
		
		// 게임 실행 화면 panel
		gamePanel = new JPanel();
		gamePanel.setBounds(0, 0, 360, 420);
		gamePanel.setBackground(new Color(212, 237, 214));
		gamePanel.setLayout(null);
		add(gamePanel);
		
		// 게임 제목 및 게임 결과(WIN/LOSE) 나타내는 label
		lblTitle = new JLabel("31th DDONGCARON");
		lblTitle.setBounds(40, 0, 280, 60);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 23));
		gamePanel.add(lblTitle);
		
		// 똥카롱 이미지
		lblDdong = new JLabel(new ImageIcon("images/ddong.png"));
		lblDdong.setBounds(150, 350, 60, 60);
		gamePanel.add(lblDdong);

		// 시작 버튼
		btnStart = new JButton("START!");
		btnStart.setBounds(20, 350, 110, 60);
		btnStart.setBackground(Color.red);
		btnStart.setFont(new Font("Verdana", Font.BOLD, 16));
		btnStart.addActionListener(gameL);
		gamePanel.add(btnStart);
		
		// 마카롱 이미지 30개 배열
		icons = new ImageIcon[30];
		for (int i = 0; i < 30; i++) {
			icons[i] = new ImageIcon("images/macaron.png");
		}
		
		// 5X6 배열로 배치
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

		// 변수 초기화
		nMacaron = nLastEat= 0;
		bFirstClick = false;
		
		// 기본 마카롱을 먹은 마카롱 이미지로 바꾸어 주는 Thread
		threadEat = new EatThread();
		threadEat.setIcons(icons);
		threadEat.setImages(lblImages);
		
		// computer 게임 조작 부분 Panel
		comPanel = new JPanel();
		comPanel.setBounds(360, 0, 270, 180);
		comPanel.setBackground(new Color(209, 209, 209));
		comPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		comPanel.setLayout(null);
		add(comPanel);

		// computer 캐릭터 이미지
		lblComimage = new JLabel(new ImageIcon("images/computer.png"));
		lblComimage.setBounds(60, 10, 150, 100);
		comPanel.add(lblComimage);
		
		// computer 캐릭터 이름
		lblComname = new JLabel("COMPUTER");
		lblComname.setBounds(85, 110, 100, 20);
		lblComname.setFont(new Font("Verdana", Font.BOLD, 15));
		comPanel.add(lblComname);
		
		// computer가 말한 수 보여주는 label
		lblComsays = new MotionLabel();
		lblComsays.setText(" ");
		lblComsays.setHorizontalAlignment(MotionLabel.CENTER);
		lblComsays.setBounds(60, 130, 150, 50);
		lblComsays.setFont(new Font("Verdana", Font.BOLD, 26));
		lblComsays.setBackground(new Color(209, 209, 209));
		lblComsays.setOpaque(true);
		comPanel.add(lblComsays);

		// user 게임 조작 부분 Panel
		userPanel = new JPanel();
		userPanel.setBounds(360, 180, 270, 240);
		userPanel.setBackground(new Color(209, 209, 209));
		userPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		userPanel.setLayout(null);
		add(userPanel);

		// user 캐릭터 이미지
		lblUserimage = new JLabel(new ImageIcon("images/user.png"));
		lblUserimage.setBounds(85, 10, 100, 100);
		userPanel.add(lblUserimage);

		// user 캐릭터 이름
		lblUsername = new JLabel("ME", JLabel.CENTER);
		lblUsername.setBounds(85, 110, 100, 20);
		lblUsername.setFont(new Font("Verdana", Font.BOLD, 15));
		userPanel.add(lblUsername);

		// user가 말한 수 보여주는 label
		lblUsersays = new JLabel("YOU FIRST", JLabel.CENTER);
		lblUsersays.setBounds(30, 130, 210, 50);
		lblUsersays.setFont(new Font("Verdana", Font.BOLD, 26));
		lblUsersays.setVisible(false);
		userPanel.add(lblUsersays);

		// 마카롱을 한 개 먹는 버튼
		btnOne = new JButton("EAT 1");
		btnOne.setBounds(15, 180, 78, 55);
		btnOne.setFont(new Font("Verdana", Font.BOLD, 13));
		btnOne.setBackground(Color.red);
		btnOne.setBorder(BorderFactory.createRaisedBevelBorder());
		btnOne.setEnabled(false);
		btnOne.addActionListener(gameL);
		userPanel.add(btnOne);

		// 마카롱을 두 개 먹는 버튼
		btnTwo = new JButton("EAT 2");
		btnTwo.setBounds(97, 180, 78, 55);
		btnTwo.setFont(new Font("Verdana", Font.BOLD, 13));
		btnTwo.setBackground(Color.yellow);
		btnTwo.setBorder(BorderFactory.createRaisedBevelBorder());
		btnTwo.setEnabled(false);
		btnTwo.addActionListener(gameL);
		userPanel.add(btnTwo);

		// 마카롱을 세 개 먹는 버튼
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
		// 게임 초기화 함수
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
			
			threadEat = new EatThread();
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
		
		// Comsays thread 초기화 함수
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
		}// initMotionLabel()

		// 이미지 변경 함수(user가 마카롱을 먹을 때)
		 public void eatMacaron(int n) { 
			threadEat = new EatThread();
			threadEat.setIcons(icons);
			threadEat.setImages(lblImages);
			threadEat.setnMacaron(nMacaron);
			threadEat.setnEat(n);
			threadEat.start();		 
		 }// eatMacaron
		 
		 // 이미지 변경 함수(computer가 마카롱을 먹을 때)
		 public void eatComMacaron(int n) { 
				threadComEat = new EatComThread();
				threadComEat.setIcons(icons);
				threadComEat.setImages(lblImages);
				threadComEat.setnMacaron(nMacaron);
				threadComEat.setnEat(n);
				threadComEat.setnLastEat(nLastEat);
				threadComEat.start();		 
		}// eatComMacaron
		 
		// 재시작 창
		public void rePlay() {
			int result;
			result = JOptionPane.showConfirmDialog(imagePanel, "CONTINUE?");
			if (result == JOptionPane.YES_OPTION) {
				gameInit();
			}else if (result == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		}//rePlay()
		
		// computer가 말할 숫자 랜덤으로 설정하는 함수
		public void comSays() {
			// 마지막으로 먹은 마카롱이 27 이상일 경우, 
			// computer에게 유리한 방향으로 computer가 마카롱을 먹을 수 있게 함.
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
			
			// 마지막으로 먹은 마카롱이 27 미만인 경우, 1~3개 중 랜덤으로 숫자를 외치게 됨.
			else if (nMacaron<27) {
				nRandom = (int)(Math.random()*3)+1;
				nMacaron += nRandom;
			}
			
			// computer가 외친 숫자를 보여주기 위한 코드
			lblComsays.setnMacaron(nMacaron);
			lblComsays.setnRandom(nRandom);
			lblComsays.setnLastEat(nLastEat);
			lblComsays.start();	
		}// comSays()

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
			
			if (obj == btnOne) { // 1개 먹기 버튼을 눌렀을 때
				if (bFirstClick == true) { // 이미 이전에 먹기 버튼을 한 번이라도 눌렀을 경우, lblComsays 초기화
					initMotionLabel();
				}
				bFirstClick = true;
				
				nMacaron += 1; // 지금까지 먹은 마카롱의 수 업데이트
				nLastEat = 1; // 현재 턴에서 먹은 마카롱의 수 저장
				lblUsersays.setText("" + nMacaron);// 외친 수 출력
				if (nMacaron >= 31) { // 게임 종료 조건
					lblTitle.setText("YOU LOSE...");
					lblUserimage.setIcon(new ImageIcon("images/user_lose.png"));
					lblComimage.setIcon(new ImageIcon("images/computer_win.png"));
					rePlay();
				}
				else {
					eatMacaron(1); // 마카롱 먹기
					comSays(); // computer의 턴
					
					if (nMacaron >= 31) { // 게임 종료 조건
						lblTitle.setText("YOU WIN!!!");
						lblUserimage.setIcon(new ImageIcon("images/user_win.png"));
						lblComimage.setIcon(new ImageIcon("images/computer_lose.png"));
						rePlay();
					}
					else eatComMacaron(nRandom); // computer가 마카롱 먹기
					
				}
				
				
			} 
			else if (obj == btnTwo) {// 2개 먹기 버튼을 눌렀을 때
				if (bFirstClick == true) {// 이미 이전에 먹기 버튼을 한 번이라도 눌렀을 경우, lblComsays 초기화
					initMotionLabel();
				}
				bFirstClick = true;
				
				nMacaron += 2;// 지금까지 먹은 마카롱의 수 업데이트
				nLastEat = 2;// 현재 턴에서 먹은 마카롱의 수 저장
				lblUsersays.setText((nMacaron - 1) + ", " + nMacaron);// 외친 수 출력
				if (nMacaron >= 31) {// 게임 종료 조건
					lblTitle.setText("YOU LOSE...");
					lblUserimage.setIcon(new ImageIcon("images/user_lose.png"));
					lblComimage.setIcon(new ImageIcon("images/computer_win.png"));
					rePlay();
				}
				else {
					eatMacaron(2);// 마카롱 먹기
					comSays();// computer의 턴
					
					
					if (nMacaron >= 31) {// 게임 종료 조건
						lblTitle.setText("YOU WIN!!!");
						lblUserimage.setIcon(new ImageIcon("images/user_win.png"));
						lblComimage.setIcon(new ImageIcon("images/computer_lose.png"));
						rePlay();
					}
					else eatComMacaron(nRandom);// computer가 마카롱 먹기
				}
				
			} 
			
			else if (obj == btnThree) {// 3개 먹기 버튼을 눌렀을 때
				if (bFirstClick == true) {// 이미 이전에 먹기 버튼을 한 번이라도 눌렀을 경우, lblComsays 초기화
					initMotionLabel();
				}
				bFirstClick = true;
				
				nMacaron += 3;// 지금까지 먹은 마카롱의 수 업데이트
				nLastEat = 3;// 현재 턴에서 먹은 마카롱의 수 저장
				// 외친 수 출력
				lblUsersays.setText((nMacaron - 2) + ", " + (nMacaron - 1) + ", " + nMacaron);
				if (nMacaron >= 31) {// 게임 종료 조건
					lblTitle.setText("YOU LOSE...");
					lblUserimage.setIcon(new ImageIcon("images/user_lose.png"));
					lblComimage.setIcon(new ImageIcon("images/computer_win.png"));
					rePlay();
				}
				else {
					eatMacaron(3);// 마카롱 먹기
					comSays();// computer의 턴
					
					if (nMacaron >= 31) {// 게임 종료 조건
						lblTitle.setText("YOU WIN!!!");
						lblUserimage.setIcon(new ImageIcon("images/user_win.png"));
						lblComimage.setIcon(new ImageIcon("images/computer_lose.png"));
						rePlay();
					}
					else eatComMacaron(nRandom);// computer가 마카롱 먹기
				}
					
			}

		}// actionPerformed()

	}// GameListener class

}// PrimaryPanel class
