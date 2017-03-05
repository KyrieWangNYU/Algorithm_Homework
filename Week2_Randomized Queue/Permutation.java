package com.company;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by kyrie on 3/4/17.
 */
public class Permutation {
    public static void main(String[] args){
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        int n = Integer.parseInt(args[0]);
        int count = 0;

        while (!StdIn.isEmpty()){
            queue.enqueue(StdIn.readString());
            count++;
        }

        while(count - n > 0){
            queue.dequeue();
            count--;
        }

        while (n > 0){
            System.out.println(queue.dequeue());
            n--;
        }
    }
}

