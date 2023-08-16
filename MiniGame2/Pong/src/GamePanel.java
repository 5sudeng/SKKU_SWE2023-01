import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{ // Runnable: 스레드 생성하기 위한 인터페이스 
										//(run() 메서드에 병렬로 실행해야 할 코드 포함, 스레드 실행 시 수행됨)
	
	static final int GAME_WIDTH = 630; // size -> (630, 420)
	static final int GAME_HEIGHT = 420;
	
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 15;
	static final int PADDLE_WIDTH = 15;
	static final int PADDLE_HEIGHT = 90;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	GameTimer gameTimer;
	
	// constructor
	GamePanel(){
		
		setLayout(null);
		
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		gameTimer = new GameTimer(0, 10, GAME_WIDTH, GAME_HEIGHT, this); // 분, 초 
		this.setFocusable(true); // key press를 focus(==read해오기!)
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		// Thread
		gameThread = new Thread(this);
		gameThread.start();
	}

	// method
	public void newBall() {
		ball = new Ball((GAME_WIDTH - BALL_DIAMETER) / 2, (GAME_HEIGHT - BALL_DIAMETER) / 2, BALL_DIAMETER, BALL_DIAMETER);
	} // 이 함수 호출 시 화면 중앙에 새로운 공 생성 
	
	public void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1); // paddle의 좌표(왼쪽끝), paddle의 너비, 높이, unique id(1)
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2); // 오른쪽 끝. unique id (2)
	}
	
	// 게임 패널 전체를 그림 
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight()); // 너비와 높이를 인자로 전달하여 이미지 객체 생성 
		graphics = image.getGraphics();
		draw(graphics); // to draw all of the components
		g.drawImage(image, 0, 0, this); // image를 (0, 0)을 기준으로 넘김, this(Game Panel)로 넘김 
	}
	
	// 각각의 구성 요소를 그림 
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		gameTimer.draw(g);
	} // 화면에 draw할 수 있는 method
	
	public void move() {
	    if (!gameTimer.isTimerFinished()) { // 타이머가 종료되지 않았을 때만 공을 움직이도록 조건 추가
	        paddle1.move(); 
	        paddle2.move();
	        ball.move();
	    }
	}// 각 iter마다 object를 움직이는 함수 - 더 빠르고 부드러운 작동 위함 
	
    public void resetScore() {
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
    }

    public void resetGameTimer() {
        gameTimer = new GameTimer(0, 10, GAME_WIDTH, GAME_HEIGHT, this);
    }
	
    public void resetGame() {
        newPaddles(); // 패들 초기화
        newBall(); // 공 초기화
        resetScore(); // 점수 초기화
        resetGameTimer(); // 타이머 초기화
    }
    
	public void checkCollision() {
		// 공이 게임창의 위, 아래 edge와 만났을 때 튕기도록 함
		if(ball.y <= 0) 
			ball.setYDirection(-ball.yVelocity); // 반대 방향으로 진행 
		if(ball.y >= GAME_HEIGHT - BALL_DIAMETER) 
			ball.setYDirection(-ball.yVelocity);
		
		// paddle과 만났을 때 공 튕기기 
		if(ball.intersects(paddle1)) // paddle이 Rectangle 클래스 상속받기 때문에 intersects도 상속받음. 
			ball.xVelocity = - ball.xVelocity; // paddle1과 부딪히는 것은 음수의 속도를 갖음 -> 양수로 바꿔주기 (방향 전환) - 그냥 마이너스 곱해줘도 됨 
		
		if(ball.intersects(paddle2))
			ball.xVelocity = - ball.xVelocity;
		
		// 패들이 게임창의 위, 아래 edge와 만났을 때 멈추도록 함 (넘어가지 않도록)
		if(paddle1.y <= 0) 
			paddle1.y = 0;
		if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		if(paddle2.y <= 0) 
			paddle2.y = 0;
		if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		
		// 공을 쳐내지 못한 경우, 점수 부여 후 새로운 공과 패들 생성 
		if(ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
		}
		if(ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime(); // lastTime 변수를 현재의 나노초 시간으로 초기
		double amountOfTicks = 60.0; // 초당 60번의 게임 업데이트 위함 
		double ns = 1000000000 / amountOfTicks; // nano seconds -> 게임 업데이터 사이의 시간 간격 
		double delta = 0; // 누적 시간 추적하기 위한 변수, 0으로 초기
		
		// 게임 루프 
		while(true) {
			long now = System.nanoTime(); // now 변수에 현재의 나노초 시간 할당 
			delta += (now - lastTime)/ns; // 경과한 시간 추적 ; 게임 업데이트가 얼마나 자주 되어야 하는지 계산 
										// (now - lastTime)/ns : 경과한 시간을 게임 사이 간격(ns)로 나눈 값 -> 업데이트 사이클 수 
			lastTime = now; // lastTime을 현재의 나노초 시간으로 업데이트 
			if(delta >= 1) { // 사이클 수만큼 반복 
				move(); // 객체 이동 
				checkCollision(); // 충돌 확인 
				repaint(); // 다시 그리기 
				delta--; 
			}
		}	
	}

	// action listener ( 키 이벤트 핸들링 ) -> 어떻게 할지 메소드는 paddle 클래스에 정의 
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	} // AL class
	
} // GamePanel class
