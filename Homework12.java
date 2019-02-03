import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

public class Homework12 {

	private ArrayList<RectangularShape> arr;

	/* Finish the constructor and create any necessary instance
	 * variables. The constructor should create and save an
	 * ArrayList of RectangularShape values
	 */
	public Homework12() {
		arr = new ArrayList<RectangularShape>();
	}

	/* Adds the parameter s to the ArrayList
	 */
	public void addShape(RectangularShape s) {
		arr.add(s);
	}

	/* Returns the number of RectangularShape objects
	 * in the ArrayLists that intersect the
	 * parameter r
	 */
	public int problem1(Rectangle2D r) {
		int counter = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).intersects(r)) {
				counter++;
			}
		}
		return counter;
	}

	/* Returns the number of RectangularShape objects
	 * in the ArrayLists that do NOT contain the
	 * parameter p
	 */
	public int problem2(Point2D p) {
		int counter = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (!arr.get(i).contains(p)) {
				counter++;
			}
		}
		return counter;
	}

	/* Returns the number of Ellipse2D objects that
	 * are in the ArrayList
	 */
	public int problem3() {
		int counter = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) instanceof Ellipse2D) {
				counter++;
			}
		}
		return counter;
	}

	/* Returns true if any of the Rectangle2D objects
	 * in the ArrayList intersects the parameter Line2D
	 */
	public boolean problem4(Line2D l) {
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) instanceof Rectangle2D) {
				if (l.intersects((Rectangle2D) arr.get(i))) {
					return true;
				}
			}
		}
		return false;
	}

	/* Return the total area of the RectangularShape objects in
	 * the AraryList
	 * For this problem, you may assume that the only shapes
	 * that have area are arrs and ellipses
	 * Note that not all ellipses are circles
	 * If two objects intersect, the intersected area
	 * counts for both (i.e. do not account for intersection)
	 */
	public double problem5() {
		double total = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) instanceof Rectangle2D) {
				total += arr.get(i).getWidth() * arr.get(i).getHeight();
			} else if (arr.get(i) instanceof Ellipse2D) {
				total += (arr.get(i).getWidth()/2) * (arr.get(i).getHeight()/2) * Math.PI;
			}
		}
		return total;
	}

	public static void main(String[] args) {
		boolean passed = true;

		Homework12 hw12 = new Homework12();
		hw12.addShape(new Ellipse2D.Double(20, 20, 10, 10));
		hw12.addShape(new Rectangle2D.Double(30, 30, 20, 20));
		hw12.addShape(new Rectangle2D.Double(40, 20, 20, 40));

		Rectangle2D  r = new Rectangle2D.Double(25, 25, 10, 10);
		if (hw12.problem1(r) == 2) {
			System.out.println("Pass 1");
		} else {
			System.out.println("Fail 1");
			passed = false;
		}

		Point2D p = new Point2D.Double(45, 35);
		if (hw12.problem2(p) == 1) {
			System.out.println("Pass 2");
		} else {
			System.out.println("Fail 2");
			passed = false;
		}

		if (hw12.problem3() == 1) {
			System.out.println("Pass 3");
		} else {
			System.out.println("Fail 3");
			passed = false;
		}

		Line2D l1 = new Line2D.Double(0, 0, 10, 10);
		Line2D l2 = new Line2D.Double(35, 35, 45, 45);
		if (hw12.problem4(l1)) {
			System.out.println("Fail 4.1");
			passed = false;
		} else if (!hw12.problem4(l2)) {
			System.out.println("Fail 4.2");
			passed = false;
		} else {
			System.out.println("Pass 4");
		}

		if (closeEnough(hw12.problem5(), 1278.53981634)) {
			System.out.println("Pass 5");
		} else {
			System.out.println("Fail 5");
			passed = false;
		}

		if (passed) {
			System.out.println("All Pass");
		} else {
			System.out.println("Fail");
		}

	}

	private static boolean closeEnough(double a, double b) {
		return Math.abs(a - b) < 0.00001;
	}
}
