package com.company;

import java.util.*;

/**
 * Created by kyrie on 3/16/17.
 */
public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<>();
    private HashMap<Double, ArrayList<Point>> segmentsCollection = new HashMap<>();


    public FastCollinearPoints(Point[] points){
        checkNull(points);
        checkDuplicates(points);

        int n = points.length;
        Point[] copied = Arrays.copyOf(points, n);

        for (Point start : points){
            Arrays.sort(copied, start.slopeOrder());
            //Sort copied by slope;

            ArrayList<Point> temp = new ArrayList<>();
            double slope = 0;
            double preSlope = Double.NEGATIVE_INFINITY;

            for (int i = 0; i < copied.length; i++){
                slope = start.slopeTo(copied[i]);
                if (slope == preSlope){
                    temp.add(copied[i]);
                }else{
                    if (temp.size() >= 3){
                        temp.add(start);
                        if(checkIfNew(temp,slope)){
                            addSegment(temp,slope);
                        }
                        temp.clear();
                        temp.add(copied[i]);
                    }
                }
                preSlope = slope;
            }

            if (temp.size() >= 3){
                temp.add(start);
                if(checkIfNew(temp,slope)){
                    addSegment(temp,slope);
                }
            }
        }

    }

    public int numberOfSegments(){
        return segments.size();
    }

    public LineSegment[] segments(){
        return segments.toArray(new LineSegment[segments.size()]);
    }

    private void checkNull(Point[] points){
        if (points == null){
            throw new NullPointerException();
        }else{
            for (Point p : points){
                if (p == null){
                    throw new NullPointerException();
                }
            }
        }
    }

    private void checkDuplicates(Point[] points){
        for (int i = 0; i < points.length; i++){
            for (int j = i + 1; j < points.length; j++){
                if (points[i].compareTo(points[j]) == 0){
                    throw new IllegalArgumentException("Found duplicated entries in input");
                }
            }
        }
    }

    private boolean checkIfNew(ArrayList<Point> slopePoints, double slope){
        ArrayList<Point> endPoints = segmentsCollection.get(slope);
        Collections.sort(slopePoints);

        Point test = slopePoints.get(slopePoints.size() - 1);

        if (endPoints == null){
            return true;
        }else{
            for (Point cur : endPoints){
                if (cur.compareTo(test) == 0){
                    return false;
                }
            }
        }
        return true;
    }

    private void addSegment(ArrayList<Point> slopePoints, double slope){
        ArrayList<Point> addPoints = segmentsCollection.get(slope);
        Collections.sort(slopePoints);

        Point start = slopePoints.get(0);
        Point end = slopePoints.get(slopePoints.size() - 1);

        if (addPoints == null){
            addPoints = new ArrayList<>();
            addPoints.add(end);
            segmentsCollection.put(slope,addPoints);
            segments.add(new LineSegment(start, end));
        }else{
            addPoints.add(end);
            segments.add(new LineSegment(start, end));
        }
    }


}
