import java.awt.Point;
import java.awt.Color;

public class DrawData {

	public Point ptDot; // get set 메소드 생성 하지 않고, 구조체 형태로 사용
	public Color color;
	public int dotSize;
	
	public DrawData() {
		ptDot = new Point();
		dotSize = 10;
		color = Color.black;
	}
	
	public DrawData(Point pt, Color c, int size) {
		ptDot = pt;
		color = c;
		dotSize = size;
	}
} // DrawData class
