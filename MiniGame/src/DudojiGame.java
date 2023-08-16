import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.time.*;

public class DudojiGame extends JPanel {
	JPanel mainPanel, scorePanel, panel; 			// 패널 선언 
	GameListener gameL;					 			// 게임 리스너 선언  
	GameMouseListener gameML;			 			// 마우스 리스터 선언 

	static int score, time;				 			// score, time 변수 static으로 선언 
	
	static JLabel[] lbl = new JLabel[12];  			// 라벨들 (두더지 이미지) 저장할 라벨 배열 선언 
	static JLabel[] hole = new JLabel[12]; 			// 라벨들 (두더지 구멍 이미지) 저장할 라벨 배열 선언 
	
	static JLabel lblScore, lblTime, lblDone;		// 라벨 선언 (점수, 시간, 최종점수)
	
	JButton startBtn; 								// 시작 버튼 선언 
	boolean start = false;							// 시작 여부
	
	public DudojiGame() {							// 두더지게임 생성자 
		score = 0;									// 시작 점수 0점 
		time = 10;									// 게임 시간 10초 
			
		gameL = new GameListener();            	 	// 게임 리스너 생성 
		gameML = new GameMouseListener();			// 마우스 리스너 생성 
		
		setPreferredSize(new Dimension(630,420));	// 메인패널 사이즈 지정 
		setLayout(null);							// 레이아웃 -> null 
		
		panel = new JPanel();						// 패널 생성, 배경색, bound, 레이아웃 -> null
		panel.setBackground(Color.white);			 
		panel.setBounds(0,0,630,420);
		panel.setLayout(null);
		
		scorePanel = new JPanel();					// 점수 패널 (최종 점수 보여주는 패널)
		scorePanel.setBounds(80, 35, 480, 350);     // bound 설정, 배경색 지정, 레이아웃 -> null, 처음에는 안보이게 
		scorePanel.setBackground(Color.pink);
		scorePanel.setVisible(false);
		scorePanel.setLayout(null);

		lblDone = new JLabel("<html><center>SCORE<br/>" + score + "</center></html>"); 	// 점수패널에 추가할 최종점수 라벨 
		lblDone.setBounds(0, 0, 480, 350); 												// 위치 지정 및 폰트 설정, 점수패널에 추가 
		lblDone.setHorizontalAlignment(SwingConstants.CENTER);
		lblDone.setFont(new Font("Verdana", Font.BOLD, 80));
		scorePanel.add(lblDone);
		panel.add(scorePanel);

		startBtn = new JButton("START");						// 시작 버튼 생성 
		startBtn.setBackground(Color.green);					// 색, bound, 폰트 지정, 리스너 추가 -> 클릭했을 때 게임 시작 가능하도록, 패널에 추가 
		startBtn.setBounds(178, 160, 272, 90);
		startBtn.setFont(new Font("Verdana", Font.BOLD, 40));
		startBtn.addActionListener(gameL);
		panel.add(startBtn);
		
		lblScore = new JLabel("SCORE : "+score);				// 점수 라벨 생성
		lblScore.setBounds(180, 380, 150, 30);					// bound, 폰트 지정, 패널에 추가 
		lblScore.setFont(new Font("Verdana", Font.BOLD, 20));
		panel.add(lblScore);

		
		lblTime = new JLabel("TIME : "+time);					// 시간 라벨 생성 
		lblTime.setBounds(330, 380, 150, 30);					// bound, 폰트 지정 패널에 추가 
		lblTime.setFont(new Font("Verdana", Font.BOLD, 20));
		panel.add(lblTime);
		
		
		Image image1 = new ImageIcon("dudoji.png").getImage();			// 두더지 이미지 가져오기 
		image1 = image1.getScaledInstance(143,115, Image.SCALE_SMOOTH); // 사이즈 조절하기 
		ImageIcon icon = new ImageIcon(image1);							// 아이콘 생성 

		Image image2 = new ImageIcon("hole.png").getImage();			// 구멍 이미지 가져오기, 사이즈 조절, 아이콘2 생성 
		image2 = image2.getScaledInstance(143,88, Image.SCALE_SMOOTH); 
		ImageIcon icon2 = new ImageIcon(image2); 

		for(int idx=0; idx<12; idx++) { 								// 라벨 배열(두더지 라벨 배열) for문으로 순회해서 생성 
			lbl[idx] = new JLabel(icon, SwingConstants.CENTER);			// 레이아웃 -> null, 마우스리스너 추가, 패널에 추가 
			lbl[idx].setLayout(null);
			lbl[idx].addMouseListener(gameML);
			panel.add(lbl[idx]);
		}

		for(int idx=0; idx<12; idx++) {									// 라벨 배열(구멍 이미지 배열) for문으로 순회해서 생성 
			hole[idx] = new JLabel(icon2, SwingConstants.CENTER);		// 레이아웃 -> null, 패널에 추가 
			hole[idx].setLayout(null);
			panel.add(hole[idx]);
		}
		
		lbl[0].setBounds(27,9,143,115);					// 각 라벨의 bound 지정 
		lbl[1].setBounds(171,9,143,115); 
		lbl[2].setBounds(315,9,143,115); 
		lbl[3].setBounds(459,9,143,115); 
		lbl[4].setBounds(27,129,143,115); 
		lbl[5].setBounds(171,129,143,115); 
		lbl[6].setBounds(315,129,143,115); 
		lbl[7].setBounds(459,129,143,115); 
		lbl[8].setBounds(27,254,143,115); 
		lbl[9].setBounds(171,254,143,115); 	
		lbl[10].setBounds(315,254,143,115); 
		lbl[11].setBounds(459,254,143,115); 
	
		hole[0].setBounds(30,34,143,88); 
		hole[1].setBounds(174,34,143,88); 
		hole[2].setBounds(318,34,143,88); 
		hole[3].setBounds(462,34,143,88); 
		hole[4].setBounds(30,153,143,88); 
		hole[5].setBounds(174,153,143,88); 
		hole[6].setBounds(318,153,143,88); 
		hole[7].setBounds(462,153,143,88); 
		hole[8].setBounds(30,279,143,88); 
		hole[9].setBounds(174,279,143,88); 
		hole[10].setBounds(318,279,143,88); 
		hole[11].setBounds(462,279,143,88); 
	
		
		add(panel);									 // 패널 메인에 추가
		
	} // constructor
	
	private static void mClicked(JLabel mole) {		 // 두더지 클릭 했을 때 사라지게 하는 함수 선언 (클릭했을 때 호출됨)
		mole.setVisible(false);						 // 호출되면 두더지 안보이게함 
		score += 1;									 // 점수 + 1 
		lblScore.setText("SCORE : "+score);			 // score 다시 지정 

	} // mClicked()
	
	private class Game {							 // 게임 클래스 생성 (두더지 랜덤으로 보이게 하는 클래스)
		public Game() {								 // 생성자 
			Random random = new Random();			 
			int num = random.nextInt(12);			 // 랜덤으로 0~11의 정수 생성 
			
			if (lbl[num].isVisible())				 // 이미 보이는 두더지가 선택된 경우 return 
				return;
			
			lbl[num].setVisible(true);				 // 랜덤으로 선택된 두더지 보이게 지정 
			
			LocalDateTime startTime = LocalDateTime.now();
			
			int endY = lbl[num].getY();				 
			int step = 1;
			int delay = 2;
			lbl[num].setLocation(lbl[num].getX(),lbl[num].getY() + 25);

			Timer timer = new Timer(delay, e -> {    // timer 객체 생성 -> 두더지가 나타날 때 조금씩 올라가면서 나타나도록 함 
			    int currentY = lbl[num].getY();
			    if (currentY > endY) {
			        lbl[num].setLocation(lbl[num].getX(), currentY - step);
			    } else {
			        ((Timer) e.getSource()).stop();
			    }
			});
			timer.start();
			
			Timer over = new Timer(100, ee -> {    // 두더지가 일정시간 이내로 잡히지 않으면 사라지도록 지정 
				LocalDateTime endTime = LocalDateTime.now();
				if (Duration.between(startTime, endTime).getSeconds() > 1.3 || time<=0) {
					lbl[num].setVisible(false);
					((Timer) ee.getSource()).stop();
				}
			});
			over.start();
			
		} // constructor
	} // Game class
	
	private class GameListener implements ActionListener{      // 게임 리스너 생성 
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if (obj == startBtn) {							   // 시작 버튼 눌렀을 때, 게임 시작 
				start = true;
				for (int i=0;i<12;i++) {
					lbl[i].setVisible(false);
				}
				
				startBtn.setVisible(false);
				
				Timer timetimer = new Timer(1000, t -> {	   // 1초에 한번 time이 줄어들게 하고 시간 라벨 새로 설정 
				    if (time > 0) {
				    	time -= 1;
				    	lblTime.setText("TIME : "+time);
				    } else {
				        ((Timer) t.getSource()).stop();
				        lblScore.setVisible(false);
				        lblTime.setVisible(false);
				        lblDone.setText("<html><center>SCORE<br/>" + score + "</center></html>");
				        scorePanel.setVisible(true);
				        
				    }
				});
				timetimer.start();
				
				Timer gtimer = new Timer(700, ex -> {        // time이 0보다 클 때 특정 시간마다 Game 객체를 생성하게 함 
				    if (time > 0) {
				    	Game game, game1;
						game = new Game();
						game1 = new Game();
				    } else {
				        ((Timer) ex.getSource()).stop();
				    }
				});
				
				Timer g2timer = new Timer(800, ex2 -> {    
				    if (time > 0) {
				    	Game game2;
						game2 = new Game();
				    } else {
				        ((Timer) ex2.getSource()).stop();
				    }
				});
				
				gtimer.start();
				g2timer.start();
				
			}
		}
		
	} // GameListener
	
	private class GameMouseListener implements MouseListener { // 두더지가 클릭 될 경우 함수 호

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel mole = (JLabel)e.getSource();
			if (start == false) {
				return;
			} else {
				mClicked(mole);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			JLabel mole = (JLabel)e.getSource();
			if (start == false) {
				return;
			} else {
				mClicked(mole);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
} // Dudoji Game
