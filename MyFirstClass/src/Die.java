
public class Die {
	// instance data
	
	private final int MAX = 6; // final -> 상
	private final int MIN = 1;
	public int m_nFaceValue;
	
	
	// method
	public Die() { // default constructor
		m_nFaceValue = (int)(Math.random() * MAX) + 1;
	}
	public Die(int value) { // parameter constructor
		m_nFaceValue = (value<MIN || value>MAX)?(int)(Math.random() * MAX) + 1:value;
	}
	public int roll() {
		m_nFaceValue = (int)(Math.random() * MAX) + 1;
		return m_nFaceValue;
	}
	
} // Die class
