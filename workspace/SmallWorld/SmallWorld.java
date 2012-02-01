import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Angelo Kaichen Huang
 * Main class for reading the input file and then invoking
 * KDFactory to get K nearest neighbors
 * http://www.facebook.com/careers/puzzles.php?puzzle_id=6
 */
public class SmallWorld{
	
	public void findNearestNeighbour(String fileName) {
		List<Point> points = readFile(fileName);
		KDFactory2 kdf = KDFactory2.getInstance();
		KDTree tree = kdf.create(points);
		BufferedWriter out;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new
					FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);

			for (Point point: points) {
				int [] ids = tree.findNearest(point, 4);			
				StringBuilder sb = new StringBuilder(point.id + " ");
				for (int i = 1; i < ids.length; i++) { //skip itself
					sb.append(ids[i]);
					if (i<ids.length-1){
						sb.append(",");
					}
				}
				sb.append("\n");
				out.write(sb.toString());
			}
			out.close();
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	private List<Point> readFile(String fileName) {
		if (fileName == null)
			return null;
		List<Point> points = new ArrayList<Point>();
		try {
			BufferedReader br  = new BufferedReader(new FileReader(fileName));
			String currLine;			
			while((currLine = br.readLine())!=null) {
				points.add(parsePoint(currLine));
			}
		
		} catch (FileNotFoundException fnfe) {			 
			fnfe.printStackTrace();	 		
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return points;
	}
	
	private Point parsePoint(String line){
		String [] arr = line.split("\\s+");
		if (arr.length < 3)
			return null;
		int id = Integer.parseInt(arr[0]);
		double [] coords = new double [2];
		for (int i = 0; i < 2; i++) {
			double d = Double.parseDouble(arr[i+1]);
			coords[i] = d;
		}	
		 
		return new Point(coords, id);
	}
	
	public static void main(String [] args) {
		if (args.length == 0) {
			System.out.println("Please provide a input file!!");
			return;
		}
		String fileName = args[0];
		SmallWorld smallworld = new SmallWorld();
		smallworld.findNearestNeighbour(fileName);
//		smallworld.findNearestNeighbour("bin/SmallWorld_Input");
//		smallworld.findNearestNeighbour("bin/smallworld.txt");
//		smallworld.findNearestNeighbour("bin/in");
//		smallworld.findNearestNeighbour("bin/smallworld2.txt");
//		smallworld.findNearestNeighbour("bin/smallworld3.txt");
	}
}