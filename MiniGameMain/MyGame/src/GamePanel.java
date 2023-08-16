import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private JButton btnLove, btnBad, btnBore;
	private ImageIcon ic1, ic2, ic3, icLove, icBad, icBore, icSay, icEff1, icEff2, icEff3, icWrong;
	private JLabel lblScore, lblPeo, lblSay, lblWrong;
	private int nScore, nRandom; // nRandom이 정답
	
	GameListener gameL;
	GameMouseListener gameML;
	TimeLabel lblTime;
	JFrame frame;
	
	
	public GamePanel(JFrame fr) {
		
		frame=fr; // 얘가 속해있는 프레임 정보 데려오기
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(630, 420));
		setLayout(null);
		
		// 리스너 객체 생성
		gameL=new GameListener();
		gameML = new GameMouseListener();
		
		// 틀렸을 때 나올 label
		icWrong = new ImageIcon("myeongeon.jpg");
		lblWrong = new JLabel();
		lblWrong.setIcon(icWrong);
		lblWrong.setVisible(false);
		lblWrong.setBounds(115,64,400,292);
		add(lblWrong);
		
		
		
		// 총 점수 0으로 초기화
		nScore=0;
		
		// 점수 텍스트
		lblScore = new JLabel("SCORE :   "+nScore);
		lblScore.setBounds(155,10,320,54);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		add(lblScore);
		
		// 다음 나올 소개팅 상대 정하기
		nRandom = (int)(Math.random()*3)+1; // 현재 정답
		
		
		// 소개팅 상대 이미지 보여주는 레이블 3개 차례대로 배치
		ic1 = new ImageIcon("love.png"); // love
		ic2 = new ImageIcon("bad.png"); // bad
		ic3 = new ImageIcon("bore.png"); // bore
		
		lblPeo = new JLabel(); // 소개팅 상대 띄우기
		if (nRandom==1) {lblPeo.setIcon(ic1);}
		else if (nRandom==2) {lblPeo.setIcon(ic2);}
		else {lblPeo.setIcon(ic3);}
		lblPeo.setBounds(265,130,95,95);
		add(lblPeo);
		
		
		// 인삿말 사진
		icSay = new ImageIcon("insa.png");
		lblSay = new JLabel();
		lblSay.setIcon(icSay);
		lblSay.setBounds(373,102,220,129);
		add(lblSay);
		
		
		// 남은 시간 텍스트
		lblTime = new TimeLabel(this, frame);
		lblTime.setBounds(160, 360, 320, 54);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		lblTime.setForeground(Color.red);
		add(lblTime);
		
		// 3개 버튼 이미지 아이콘 정의
		icLove = new ImageIcon("btnlove.png"); // love
		icBad = new ImageIcon("btnbad.png"); // bad
		icBore = new ImageIcon("btnbore.png"); // bore
		
		// 버튼 클릭시 효과 아이콘
		icEff1 = new ImageIcon("effLove.png");
		icEff2 = new ImageIcon("effBad.png");
		icEff3 = new ImageIcon("effBore.png");

		
		// love 버튼
		btnLove = new JButton();
		btnLove.setIcon(icLove);
		btnLove.setBounds(71, 260, 103, 89);
		btnLove.addActionListener(gameL);
		btnLove.addMouseListener(gameML);
		add(btnLove);
		
		// bad 버튼
		btnBad = new JButton();
		btnBad.setIcon(icBad);
		btnBad.setBounds(263, 260, 103, 89);
		btnBad.addActionListener(gameL);
		btnBad.addMouseListener(gameML);
		add(btnBad);
		
		// bore 버튼
		btnBore = new JButton();
		btnBore.setIcon(icBore);
		btnBore.setBounds(455, 260, 103, 89);
		btnBore.addActionListener(gameL);
		btnBore.addMouseListener(gameML);
		add(btnBore);
		
		
		
		
	} // 생성자
	
	public void initThread() {
		// thread 다시 생성해주기.
		lblTime.stop();
		lblTime.setVisible(false);
		
		lblTime = new TimeLabel(this, frame);
		lblTime.setBounds(160, 360, 320, 54);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		lblTime.setForeground(Color.red);
		add(lblTime);
		
		nScore=0; // 점수 초기화.
		lblScore.setText("SCORE :   "+nScore); // 다시 텍스트 정리하기..
		
		// 소개팅 상대도 reset
		nRandom = (int)(Math.random()*3)+1; // 현재 정답
		if (nRandom==1) {lblPeo.setIcon(ic1);} // 그에 따른 아이콘 재설정
		else if (nRandom==2) {lblPeo.setIcon(ic2);}
		else {lblPeo.setIcon(ic3);}
		
	}
	public int getScore() {return nScore;}
	// 틀릴 때 나오는 label 제어
	public void setWrong(boolean tf) {lblWrong.setVisible(tf);}
	// 틀릴 때 버튼 제어
	public void setLove(boolean tf) {btnLove.setVisible(tf);}
	public void setBad(boolean tf) {btnBad.setVisible(tf);}
	public void setBore(boolean tf) {btnBore.setVisible(tf);}
	
	
	private class GameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			
			if ((nRandom==1 && obj==btnLove)||
					(nRandom==2 && obj==btnBad)||
					(nRandom==3 && obj==btnBore)) {
				// score+=1 
				nScore+=1;
				lblScore.setText("SCORE :   "+nScore);
			}
			else {
				// thread interrupt -> 시간 3초 뻇기
				lblTime.interrupt();
			}
			
			// random2->1, random3->2, random3 새로뽑기
			nRandom = (int)(Math.random()*3)+1; // 현재 정답
			if (nRandom==1) {lblPeo.setIcon(ic1);} // 그에따른 아이콘 재설정
			else if (nRandom==2) {lblPeo.setIcon(ic2);}
			else {lblPeo.setIcon(ic3);}
			
			// random 순서대로 label icon 다시 설정하기
			
		}
		
	}
	
	private class GameMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// 버튼 영역 안으로 들어왔을 때 효과주기
			if ((JButton) e.getSource()==btnLove) {btnLove.setIcon(icEff1);}
			else if ((JButton) e.getSource()==btnBad) {btnBad.setIcon(icEff2);}
			else if ((JButton) e.getSource()==btnBore) {btnBore.setIcon(icEff3);}
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// 다시 마우스 바깥으로 간다면 효과 원래대로
			if ((JButton) e.getSource()==btnLove) {btnLove.setIcon(icLove);}
			else if ((JButton) e.getSource()==btnBad) {btnBad.setIcon(icBad);}
			else if ((JButton) e.getSource()==btnBore) {btnBore.setIcon(icBore);}
			
		}
		
	}

}
