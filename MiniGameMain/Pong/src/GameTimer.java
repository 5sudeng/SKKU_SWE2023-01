import java.awt.*;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class GameTimer{
	
	private GamePanel gamePanel;
	
    private int TIMER_WIDTH = 100;
    private int TIMER_HEIGHT = 30;
    private static final DecimalFormat TWO_DIGIT_FORMAT = new DecimalFormat("00");
    static int GAME_WIDTH;
	static int GAME_HEIGHT;

    private int minutes;
    private int seconds;
    
    private Timer timer;
    private TimerTask timerTask;
    private int interval;
    
	public boolean isTimerFinished() {
	    return minutes == 0 && seconds == 0;
	}

    public GameTimer(int minutes, int seconds, int GAME_WIDTH, int GAME_HEIGHT, GamePanel gamePanel) {
    	this.minutes = minutes;
    	this.seconds = seconds;
    	this.gamePanel = gamePanel;
    	//this.gamePanel = gamePanel;
    	GameTimer.GAME_WIDTH = GAME_WIDTH;
    	GameTimer.GAME_HEIGHT = GAME_HEIGHT;
    	
    	// Calculate the total time in seconds
        // int totalTimeInSeconds = minutes * 60 + seconds;
        interval = 1000; // 1 second
        
        // Create a new Timer and TimerTask
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                update(); // Update the timer
            }
        };

        // Schedule the TimerTask to run at fixed intervals
        timer.scheduleAtFixedRate(timerTask, interval, interval);
    }

    // 시간 초과 시 팝업 
 	public void handleTimerFinish() {
 		int result;
		result = JOptionPane.showConfirmDialog(null, "CONTINUE?"); // 대화상자를 띄울 기준 오브젝트, 문자열 
		if (result == JOptionPane.YES_OPTION) {
			// 게임 초기화
			gamePanel.newPaddles();
			gamePanel.newBall();
			gamePanel.resetScore();
			gamePanel.resetGameTimer();
			seconds = 59; // 초가 0이면 계속 대화상자가 반복됨 ;; 59로 설정하여 reset이 잘 되도록 함 
		} else if (result == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}
 	
    public void update() {
        seconds--;
        if (seconds < 0) {
            minutes--;
            if (minutes < 0) {
                minutes = 0;
                seconds = 0;
                // 시간초과!
                handleTimerFinish();
            } else {
                seconds = 59;
            }
        }
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.BOLD, 20));

        String timeString = TWO_DIGIT_FORMAT.format(minutes) + ":" + TWO_DIGIT_FORMAT.format(seconds);
        int timerX = GAME_WIDTH - TIMER_WIDTH - 10;
        int timerY = GAME_HEIGHT - TIMER_HEIGHT - 10;

        g.drawString(timeString, timerX, timerY);
    }
}
