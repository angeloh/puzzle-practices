
public class recursiveSum {
	public recursiveSum() {

	}

	public static int sum(int N) {
		if (N == 0)
			return 0;
		if (N == 1)
			return 1;
		return N + sum(N - 1);
	}
	
	public static void main(String[] args) {
		System.out.println(recursiveSum.sum(6));
	}
}