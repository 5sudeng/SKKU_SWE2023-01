import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{
	
	Random random;
	int xVelocity;
	int yVelocity; // 공이 움직이는 속도
	int initialSpeed = 3; // 공이 너무 느리게 움직이는 것 방지 (3배 더 빠르게) 
	

	// constructor
	Ball(int x, int y, int width, int height){
		super(x, y, width, height); // 부모 클래스 (Rectangle)의 매개변수 4개를 가진 생성자 호출 위함?
		random = new Random();
		int randomXDirection = random.nextInt(2); // 0 or 1 (0: 왼쪽, 1: 오른쪽)
		if(randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection * initialSpeed);
		
		int randomYDirection = random.nextInt(2); // 0 or 1 (0: 왼쪽, 1: 오른쪽)
		if(randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection * initialSpeed);
		
	}
	
	// method
	// ball은 모든 방향으로 움직이므로 x, y 좌표 필요함 
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}
	
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}
