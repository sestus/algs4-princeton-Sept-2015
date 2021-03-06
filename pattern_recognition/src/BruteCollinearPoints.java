import java.util.ArrayList;
import java.util.Arrays;

/******************************************************************************
 * Compilation: javac Point.java
 * Execution: java Point
 * Dependencies: none
 * 
 * An immutable data type for points in the plane.
 * For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

public class BruteCollinearPoints {


    private ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();
        Point[] tmp = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new NullPointerException();
            tmp[i] = points[i];
        }
        Arrays.sort(tmp);
        Point prev = tmp[0];
        for (int i = 1; i < tmp.length; i++) {
            if (tmp[i].compareTo(prev) == 0)
                throw new IllegalArgumentException();
            prev = tmp[i];
        }
        this.lineSegments = new ArrayList<LineSegment>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Point[] collinear = new Point[4];
                collinear[0] = points[i];
                collinear[1] = points[j];
                Point max;
                Point min;
                if (collinear[0].compareTo(collinear[1]) >= 0) {
                    max = collinear[0];
                    min = collinear[1];
                }
                else {
                    max = collinear[1];
                    min = collinear[0];
                }
                int collinears = 2;
                double slope = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length; k++) {
                    if (points[i].slopeTo(points[k]) == slope) {
                        collinear[collinears++] = points[k];
                        if (points[k].compareTo(min) < 0)
                            min = points[k];
                        else if (points[k].compareTo(max) > 0)
                            max = points[k];
                    }
                    if (collinears == 4) {
                        this.lineSegments.add(new LineSegment(min, max));
                        break;
                    }
                }
            }
        }
    }
    
    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] ret = new LineSegment[lineSegments.size()];
        ret = lineSegments.toArray(ret);
        return ret;
    }

}
