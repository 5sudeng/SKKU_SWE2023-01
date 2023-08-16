import java.util.Random;
import java.awt.Point;

public class MyRect {
	Random random = new Random(); // 크기 랜덤 설정을 위한 random객체 생성 및 선언 
	
	// instance data
	private int m_nWidth, m_nHeight; // rect의 width와 height 생성
	private Point point = new Point(random.nextInt(200)+1,random.nextInt(200)+1); // 좌표를 의미하는 Point 객체 생성 및 선언 -> 랜덤으로 초기화 
	private final int MIN = 1; // 크기의 최소값과 최대값 MIN과 MAX 상수로 선언 
	private final int MAX = 200;
	
	// method
	// 1. constructor
	public MyRect() {
		m_nWidth = random.nextInt(200)+1 ; // 별도의 사이즈 지정 없이 생성시 랜덤 크기로 rect 지정 
		m_nHeight = random.nextInt(200)+1 ;
	} // 1-1 default constructor

	public MyRect(int v1, int v2) {
		m_nWidth = (v1 >= MIN) ? v1 : MIN; // Width 와 Height 모두 MIN 과 MAX 사의의 값을 가지도록 함
		m_nWidth = (v1 <= MAX) ? v1 : MAX;
		m_nHeight = (v2 >= MIN) ? v2 : MIN;
		m_nHeight = (v2 <= MAX) ? v2 : MAX;
	} // 1-2 parameter constructor (constructor들은 overloading함)

	public MyRect(int size) {
		m_nWidth = (size >= MIN) ? size : MIN; // 사이즈 한개만 입력할 경우 정사각형으로 간주하고 width, height 모두 size로 설정 
		m_nWidth = (size <= MAX) ? size : MAX;
		m_nHeight = m_nWidth;
	} // 1-3 parameter constructor 
	
	public MyRect(int w, int h, int x, int y) {
		this(w, h); // w, h는 앞에서 작성한 생성자 호출하여 지정 
		x = (x>=MIN)?x:MIN; // x, y좌표도 MIN과 MAX 사이의 값을 가짐 
		x = (x<=MAX)?x:MAX;
		y = (y>=MIN)?y:MIN;
		y = (y<=MAX)?y:MAX;
		point.setLocation(x, y);
	} // 1-4 parameter constructor

	// 2. get/set 인스턴스 개수만큼 기본으로 만들고 추가하기
	public int getWidth() {
		return m_nWidth;
	} // getter of width

	public int getHeight() {
		return m_nHeight;
	} // getter of height

	public void setWidth(int w) {
		m_nWidth = (w >= MIN) ? w : MIN;
		m_nWidth = (w <= MAX) ? w : MAX;
	} // setter of width

	public void setHeight(int h) {
		m_nHeight = (h >= MIN) ? h : MIN;
		m_nHeight = (h <= MAX) ? h : MAX;
	} // setter of height

	public void setSize(int v1, int v2) {
		m_nWidth = (v1 >= MIN) ? v1 : MIN;
		m_nWidth = (v1 <= MAX) ? v1 : MAX;
		m_nHeight = (v2 >= MIN) ? v2 : MIN;
		m_nHeight = (v2 <= MAX) ? v2 : MIN;
	} // setter of width and height

	public void setSize(int size) {
		m_nWidth = (size >= MIN) ? size : MIN; // 사이즈 한개만 입력할 경우 정사각형으로 간주하고 width, height 모두 size로 설정 
		m_nWidth = (size <= MAX) ? size : MAX;
		m_nHeight = m_nWidth;
	} // setter of size
	
	public void setPoint(int x, int y) {
		x = (x>=MIN)?x:MIN; // x, y좌표도 MIN과 MAX 사이의 값을 가짐 
		x = (x<=MAX)?x:MAX;
		y = (y>=MIN)?y:MIN;
		y = (y<=MAX)?y:MAX;
		point.setLocation(x, y);
	} // setter of point
	
	public int getX() { return point.x; } // getter of x
	public int getY() { return point.y; } // getter of y
	public String getPoint() { return point.toString();} // getter of point
	
	// 3. function 넓이/둘레
	public int calcArea() {
		return m_nWidth * m_nHeight;
	} // calcArea()

	public int calcPerimeter() {
		return m_nWidth * 2 + m_nHeight * 2;
	} // calcPerimeter()
	
	public int checkQuardrant() {
		int quad;
		if (point.x > 0 && point.y > 0)
			quad = 1;
		else if (point.x < 0 && point.y > 0)
			quad = 2;
		else if (point.x < 0 && point.y < 0)
			quad = 3;
		else if (point.x > 0 && point.y < 0)
			quad = 4;
		else
			quad = 0; // x축 또는 y축상에 위치 
		return quad;
	} // checkQuadrant()
	
	public boolean isQuadrant(MyRect rect) {
		if (this.checkQuardrant() == rect.checkQuardrant()) // check quardrant를 기준으로 비교 
			return true;
		return false;
	} // isQuadrant()

	public int compareRectArea(MyRect rect) {
		int result;
		int area1 = calcArea(); // this 생략
		int area2 = rect.calcArea();

		if (area1 > area2) {
			result = 1;
		} else if (area1 == area2) {
			result = 0;
		} else {
			result = -1;
		}
		return result;
	} // compareRectArea()
	
	public boolean isPtInRect(Point pt) {
		if (pt.x > point.x && pt.x < point.x + m_nWidth) {
			if (pt.y > point.y && pt.y < point.y + m_nHeight)
				return true;
		}
		return false;
	} // isPtInRect()

	// 4. toString()
	public String toString() {
		return "RECT => WIDTH : " + m_nWidth + " HEIGHT : " + m_nHeight+" / Point : "+"("+point.x+", "+point.y+")";
	} // toString()

} // MyRect class
