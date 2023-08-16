
public class MyRect {

	// data
	private double m_lfWidth, m_lfHeight;

	// method
	// 1. constructor
	public MyRect() {
		m_lfWidth = 5;
		m_lfHeight = 5;
	} // 1-1 default

	public MyRect(double v1, double v2) {
		m_lfWidth = (v1 > 0) ? v1 : 1;
		m_lfHeight = (v2 > 0) ? v2 : 1;
	} // 1-2 parameter + 와 값 하다 더.. ㄷ ㄷ

	public MyRect(double size) {
		m_lfWidth = m_lfHeight = (size > 0) ? size : 1;
	}

	// 2. get/set 인스턴스 개수만큼 기본으로 만들고 추가하기
	public double getWidth() {
		return m_lfWidth;
	}

	public double getHeight() {
		return m_lfHeight;
	}

	public void setWidth(double w) {
		m_lfWidth = (w > 0) ? w : 1;
	}

	public void setHeight(double h) {
		m_lfHeight = (h > 0) ? h : 1;
	}

	public void setSize(double v1, double v2) {
		m_lfWidth = (v1 > 0) ? v1 : 1;
		m_lfHeight = (v2 > 0) ? v2 : 1;
	}

	public void setSize(double size) {
		m_lfWidth = m_lfHeight = (size > 0) ? size : 1;
	}

	// 3. function 넓이/둘레 ㄷ ㄷ
	public double calcArea() {
		return m_lfWidth * m_lfHeight;
	}

	public double calcPerimeter() {
		return m_lfWidth * 2 + m_lfHeight * 2;
	}

	public int compareRect(double w, double h) {
		int result;
		double area1 = calcArea(); // this 생략
		double area2 = w * h;

		if (area1 > area2) {
			result = 1;
		} else if (area1 == area2) {
			result = 0;
		} else {
			result = -1;
		}

		return result;
	}

	public int compareRect(MyRect rect) {
		int result;
		double area1 = calcArea(); // this 생략
		double area2 = rect.calcArea();

		if (area1 > area2) {
			result = 1;
		} else if (area1 == area2) {
			result = 0;
		} else {
			result = -1;
		}

		return result;
	}

	// 4. toString()
	public String toString() {
		return "RECT >> WIDTH : " + m_lfWidth + " HEIGHT : " + m_lfHeight;
	}

} // MyRect class
