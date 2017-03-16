package com.company;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kyrie on 3/16/17.
 */
public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points){
        checkDuplicates(points);

        int n = points.length;
        Point[] copied = Arrays.copyOf(points,n);
        Arrays.sort(copied);
        ArrayList<LineSegment> segmentsCollection = new ArrayList<>();


        for (int p = 0; p < n - 3; p++) {
            for (int q = p + 1; q < n - 2; q++) {
                for (int r = q + 1; r < n - 1; r++) {
                    for (int s = r + 1; s < n; s++) {
                        if (copied[p].slopeTo(copied[q]) == copied[p].slopeTo(copied[r]) &&
                                copied[p].slopeTo(copied[q]) == copied[p].slopeTo(copied[s])){
                            segmentsCollection.add(new LineSegment(copied[p],copied[s]));
                        }
                    }
                }
            }
        }

        segments = segmentsCollection.toArray(new LineSegment[segmentsCollection.size()]);
    }

    public int numberOfSegments(){
        return segments.length;
    }

    public LineSegment[] segments(){
        return Arrays.copyOf(segments, segments.length);
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
}
