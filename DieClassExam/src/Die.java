
public class Die {

	// instance data
	private int m_nFaceValue; // m_ or _ -> member n -> int type

	// member data 초기화 해주는 생성자 (constructor) / 파라미터 유무로 default, parameter로 나뉨
	// 1. constructor
	// 1-1. default constructor 필히 넣기
	public Die() {
		m_nFaceValue = 1;
	} // return타입이 없고 클래스이름과 완전 동일

	// 1-2. parameter constructor 선택
	public Die(int value) {
		m_nFaceValue = (value < 1) ? 1 : value;
		m_nFaceValue = (m_nFaceValue > 6) ? 6 : value;
	} // 파라미터 받아서

	// 2. get/set method
	public int getFaceValue() {
		return m_nFaceValue;
	}

	public void setFaceValue(int value) {
		m_nFaceValue = (value < 1) ? 1 : value;
		m_nFaceValue = (m_nFaceValue > 6) ? 6 : value;
	}

	// 3. function (기능)
	public int roll() {
		m_nFaceValue = (int) (Math.random() * 6) + 1;
		return m_nFaceValue;
	} // roll method

	// die1 객체를 그냥출력하면 die1 객체의 주소값이 16진수로
	// 4. toString()
	public String toString() {
		String result = "FACE : " + m_nFaceValue;
		return result;
	}

} // Die class
// class file