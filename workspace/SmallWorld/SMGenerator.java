import java.util.Random;

public class Test {
	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("Usage: java Test <number of points> <random seed>");
			System.exit(1);
		}
		int n = Integer.parseInt(args[0]);
		Random r = new Random(Integer.parseInt(args[1]));
		double[] x = new double[1 + n];
		double[] y = new double[1 + n];
		for(int j = 1; j <= n; ++j) {
			int i = r.nextInt(j);
			double d = Math.pow(j, 2 * r.nextDouble());
			x[j] = x[i] + r.nextGaussian() / d;
			y[j] = y[i] + r.nextGaussian() / d;
			System.out.print(j + "\t" + 60 * x[j] + "\t" + 60 * y[j] + "\n");
		}
	}
}