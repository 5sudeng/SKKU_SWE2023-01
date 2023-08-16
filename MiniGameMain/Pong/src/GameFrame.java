import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	
	GamePanel panel;
	
	// constructor
	GameFrame(){
		panel = new GamePanel();
		this.add(panel); // frame에 Panel 추가 
		this.setTitle("PONG GAME");
		this.setResizable(false); // 사이즈 고정! 변경하지 못하도록 설정 
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null); // 컴퓨터 화면 중앙에 뜨도록 설정 
	}
}
