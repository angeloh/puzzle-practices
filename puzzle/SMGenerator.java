import java.util.Random;

/**
 * @author Angelo
 *  Usage: java Test <number of points> <random seed>
	$ java Test 100000 42 >in
	$ md5sum in
	a5539de63e7aaefab283e9757407a704 in
	$ time ./smallworld in >out	
	real	0m0.886s
	user	0m0.846s
	sys	0m0.031s
	$ md5sum out
	12a46f2ad69d1a186d25cc326ad5a32c out
 */
public class SMGenerator {
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