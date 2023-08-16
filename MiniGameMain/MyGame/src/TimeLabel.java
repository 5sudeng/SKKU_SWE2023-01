import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class TimeLabel extends JLabel implements Runnable{
	
	Thread myThread;
	private int nTime; // 남은 시간
	GamePanel game; // 게임 score 얻어올려고..
	JFrame frame;
	
	
	
	public TimeLabel(GamePanel n, JFrame fr) {
		frame=fr;
		myThread = new Thread(this);
		nTime=30; // 30초 제한
		game = n; // GamePanel 객체 얻어오기.
	} //생성자
	
	public void start() { // 스레드 시작하게
		if (myThread != null)
			myThread.start();
	}
	@SuppressWarnings("deprecation")
	public void stop() { // 스레드 중단시키기..
		myThread.stop();
	}
	
	public void interrupt() {
		myThread.interrupt();
	}
	
	

	@Override
	public void run() {
		
		nTime = 30;
		setForeground(Color.black);
		
        while (nTime >= 0) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    setText("Time :   "+nTime);
                }
            });
            if (nTime<=5) {
            	//5초 이하면 time Label의 색 변경..
            	setForeground(Color.red);
            }
            
            try {
                Thread.sleep(1000); // 1초 대기
                game.setWrong(false);
                game.setLove(true);
            	game.setBad(true);
            	game.setBore(true);
            } catch (InterruptedException e) { // 중간에 틀렸을 경우.
            	nTime-=5; // 시간 5초 감소
            	// 뭔가 거대한 이벤트를 줘보고 싶다. -> 명언을 잠시 띄우자.
            	game.setWrong(true);
            	game.setLove(false);
            	game.setBad(false);
            	game.setBore(false);
            	
                //e.printStackTrace();
            }

            nTime--;
        }
        // 시간 다 되면 -> 팝업창 생성
        int result;
		result = JOptionPane.showConfirmDialog(null, "Your score: "+game.getScore()+"\nCONTINUE?");
		if (result==JOptionPane.YES_OPTION) { // StartMenu로 돌아가기
			// game 초기화
			/*
			 
			
			// 게임 설명+시작 버튼 화면 띄우기
			StartMenu startPanel = new StartMenu();
			
			// 홈버튼 (미니게임 모음 페이지로)
			ImageIcon icHome = new ImageIcon("home.png");
			JButton btnHome = new JButton(icHome);
			btnHome.setBounds(13,13,50,50);
			startPanel.add(btnHome);
			
			// 게임 시작 버튼
			JButton btnStart = new JButton("GAME START");
			btnStart.setFont(new Font("Cooper Black", Font.PLAIN, 25));
			btnStart.setBounds(167, 340, 289, 53);
			btnStart.setBackground(new Color(251,209,251)); // 연핑크
			btnStart.setForeground(Color.white);
			
			// 본격 게임 화면 패널 객체 가져오기
			GamePanel gamePanel = new GamePanel(frame);
			
			// 버튼 누르면 게임 화면으로 넘어감.
			btnStart.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // 다음 페이지로 전환하는 로직을 작성
	                frame.getContentPane().removeAll(); // 현재 페이지의 컴포넌트 제거
	                frame.getContentPane().add(gamePanel); // 다음 페이지의 컴포넌트 추가
	                frame.getContentPane().revalidate(); // 프레임을 다시 그리기 위해 revalidate() 호출
	                frame.getContentPane().repaint(); // 프레임을 다시 그리기 위해 repaint() 호출
	                
	                gamePanel.lblTime.start(); // thread 시작
	            }
	        });
			startPanel.add(btnStart);
			
			// panel2의 게임 종료 버튼 (GameMain으로 돌아감.)
			ImageIcon icBack = new ImageIcon("finish.png");
			JButton btnBack = new JButton(icBack);
			btnBack.setBounds(13,13,50,50);
			btnBack.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	gamePanel.initThread();
	            	
	                // 이전 페이지로 전환하는 로직을 작성
	                frame.getContentPane().removeAll(); // 현재 페이지의 컴포넌트 제거
	                frame.getContentPane().add(startPanel); // 이전 페이지의 컴포넌트 추가
	                frame.getContentPane().revalidate(); // 프레임을 다시 그리기 위해 revalidate() 호출
	                frame.getContentPane().repaint(); // 프레임을 다시 그리기 위해 repaint() 호출
	            }
	        });
	        gamePanel.add(btnBack);
			
			
			frame.getContentPane().add(startPanel);
			frame.pack();
			frame.setVisible(true);
			*/
		
		} else if (result==JOptionPane.NO_OPTION) { // 게임 종료
			System.exit(0);
		}
	}

}
