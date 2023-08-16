import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMain {

	public static void main(String[] args) { 
		

		// 프레임 설정
		JFrame frame = new JFrame("Who is Dongle's Lover?");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
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
		
		
	}
}    