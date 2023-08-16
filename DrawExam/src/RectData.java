import java.awt.Point;
import java.awt.Color;

public class RectData {
	
	public Point 			ptOne, ptTwo;
	public Color 			selectedColor;
	public int 				width;
	public boolean 			bFill;
	
	public RectData() {
		ptOne = new Point();
		ptTwo = new Point();
		selectedColor = Color.black;
		width = 1;
		bFill = false;
	} // constructor
	
	public RectData(Point pt1, Point pt2, Color color, int w, boolean fill) {
		ptOne = pt1;
		ptTwo = pt2;
		selectedColor = color;
		width = w;
		bFill = fill;
	}
	
	public RectData(RectData data) {
		ptOne = data.ptOne;
		ptTwo = data.ptTwo;
		selectedColor = data.selectedColor;
		width = data.width;
		bFill = data.bFill;
	}
	
} // RectData class
