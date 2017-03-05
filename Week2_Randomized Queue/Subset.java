package com.company;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by kyrie on 3/4/17.
 */
public class Subset {
    public static void main(String args[]){
        int n = Integer.parseInt(args[0]);
        RandomizedQueue<String> str = new RandomizedQueue<>();
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            str.enqueue(item);
        }
        while (n > 0){
            System.out.println(str.dequeue());
            n--;
        }
    }
}
