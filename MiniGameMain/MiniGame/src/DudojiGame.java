import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.time.*;

public class DudojiGame extends JPanel {
	JPanel mainPanel, scorePanel, panel;
	GameListener gameL;
	GameMouseListener gameML;
	
	static int score, time;
	
	static JLabel[] lbl = new JLabel[12];
	static JLabel[] hole = new JLabel[12];
	
	static JLabel lblScore, lblTime, lblDone;
	
	JButton startBtn;
	boolean start = false;
	
	public DudojiGame() {
		score = 0;
		time = 10;
		
		gameL = new GameListener();
		gameML = new GameMouseListener();
		
		//setBackground(Color.white);
		setPreferredSize(new Dimension(630,420));
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(0,0,630,420);
		panel.setLayout(null);
		
		scorePanel = new JPanel();
		scorePanel.setBounds(80, 35, 480, 350);
		scorePanel.setBackground(Color.pink);
		scorePanel.setVisible(false);
		scorePanel.setLayout(null);

		lblDone = new JLabel("<html><center>SCORE<br/>" + score + "</center></html>");
		lblDone.setBounds(0, 0, 480, 350);
		lblDone.setHorizontalAlignment(SwingConstants.CENTER);
		lblDone.setFont(new Font("Verdana", Font.BOLD, 80));
		scorePanel.add(lblDone);
		panel.add(scorePanel);

		startBtn = new JButton("START");
		startBtn.setBackground(Color.green);
		startBtn.setBounds(178, 160, 272, 90);
		startBtn.setFont(new Font("Verdana", Font.BOLD, 40));
		startBtn.addActionListener(gameL);
		panel.add(startBtn);
		
		lblScore = new JLabel("SCORE : "+score);
		lblScore.setBounds(180, 380, 150, 30);
		lblScore.setFont(new Font("Verdana", Font.BOLD, 20));
		panel.add(lblScore);

		
		lblTime = new JLabel("TIME : "+time);
		lblTime.setBounds(330, 380, 150, 30);
		lblTime.setFont(new Font("Verdana", Font.BOLD, 20));
		panel.add(lblTime);
		
		Image image1 = new ImageIcon("dudoji.png").getImage();
		image1 = image1.getScaledInstance(143,115, Image.SCALE_SMOOTH); 
		ImageIcon icon = new ImageIcon(image1);

		Image image2 = new ImageIcon("hole.png").getImage();
		image2 = image2.getScaledInstance(143,88, Image.SCALE_SMOOTH); 
		ImageIcon icon2 = new ImageIcon(image2); 

		for(int idx=0; idx<12; idx++) {
			lbl[idx] = new JLabel(icon, SwingConstants.CENTER);
			lbl[idx].setLayout(null);
			lbl[idx].addMouseListener(gameML);
			panel.add(lbl[idx]);
		}

		for(int idx=0; idx<12; idx++) {
			hole[idx] = new JLabel(icon2, SwingConstants.CENTER);
			hole[idx].setLayout(null);
			panel.add(hole[idx]);
		}
		
		lbl[0].setBounds(27,9,143,115);
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
	
		
		add(panel);
		
	} // constructor
	
	private static void mClicked(JLabel mole) {
		mole.setVisible(false);
		score += 1;
		lblScore.setText("SCORE : "+score);

	}
	
	private class Game {
		public Game() {
			Random random = new Random();
			int num = random.nextInt(12);
			
			if (lbl[num].isVisible())
				return;
			
			lbl[num].setVisible(true);
			
			LocalDateTime startTime = LocalDateTime.now();
			
			int endY = lbl[num].getY();
			int step = 1;
			int delay = 2;
			lbl[num].setLocation(lbl[num].getX(),lbl[num].getY() + 25);

			Timer timer = new Timer(delay, e -> {
			    int currentY = lbl[num].getY();
			    if (currentY > endY) {
			        lbl[num].setLocation(lbl[num].getX(), currentY - step);
			    } else {
			        ((Timer) e.getSource()).stop();
			    }
			});
			timer.start();
			
			Timer over = new Timer(100, ee -> {
				LocalDateTime endTime = LocalDateTime.now();
				if (Duration.between(startTime, endTime).getSeconds() > 1.3 || time<=0) {
					lbl[num].setVisible(false);
					((Timer) ee.getSource()).stop();
				}
			});
			over.start();
			
		} // constructor
	} // Game class
	
	private class GameListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if (obj == startBtn) {
				start = true;
				for (int i=0;i<12;i++) {
					lbl[i].setVisible(false);
				}
				
				startBtn.setVisible(false);
				
				Timer timetimer = new Timer(1000, t -> {
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
				
				Timer gtimer = new Timer(700, ex -> {
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
	
	private class GameMouseListener implements MouseListener {

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
