package studyCafe;

public class TEst {
	public static void main(String[] args) {
		System.out.println(twoDigit(1));
	}

	static String twoDigit(int t) {
		return (0 <= t && t < 10) ? "0" + t : "" + t;
	}

}
