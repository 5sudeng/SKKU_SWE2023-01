import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle{
	
	int id; // player id ; 1 or 2 (각각 paddle1, paddle2)
	int yVelocity; // paddle이 움직이는 속도 
	int speed = 7; // 초당 60번의 게임 업데이트가 되고, move()는 한번의 업데이트당 한 번 호출되므로 초당 7 * 60 픽셀 움직임 
	
	// constructor
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
	}
	
	// method
	public void keyPressed(KeyEvent e) {
		switch(id) {
		// player 1
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) { // W 입력 시 실행 
				setYDirection(-speed); // y축 -7 픽셀만큼 이동 
			}
			if(e.getKeyCode() == KeyEvent.VK_S) { // S 입력 시 실행 
				setYDirection(speed); // y축 +7 픽셀만큼 이동 
			}
			break;
		// player 2
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) { // Down Key 입력 시 실행 
				setYDirection(-speed); // y축 -7 픽셀만큼 이동 
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) { // Up Key 입력 시 실행 
				setYDirection(speed); // y축 +7 픽셀만큼 이동 
			}
			break;
		}
	}
	
	// 키를 누르지 않으면 정지하도록 함 (키에서 손을 놨을 때) 
	public void keyReleased(KeyEvent e) {
		switch(id) {
		// player 1
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0); // 정지
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0); 
			}
			break;
		// player 2
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;
		}
	}

	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	} // paddle은 위아래로만 움직이므로 y좌표만 변함 
	
	public void move() {
		y = y + yVelocity;
	}
	
	public void draw(Graphics g) {
		// player 1
		if(id == 1)
			g.setColor(new Color(190, 226, 235));
		
		// player 2
		else
			g.setColor(new Color(235, 190, 230));
		
		g.fillRect(x, y, width, height);
	}
	
} // Paddle class
