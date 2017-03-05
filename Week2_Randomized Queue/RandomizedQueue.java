package com.company;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by kyrie on 3/4/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int N;
    private Item[] items;

    public Iterator<Item> iterator(){
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item>{
        private int index = 0;
        private int newLength = N;
        private Item[] newArray = (Item[]) new Object[N];

        private QueueIterator(){
            for (int i = 0; i < newLength; i++){
                newArray[i] = items[i];
            }
        }

        public boolean hasNext(){
            return index <= newLength - 1;
        }

        public Item next(){
            if (newArray[index] == null){
                throw new NoSuchElementException();
            }
            int random = StdRandom.uniform(newLength);
            Item item = newArray[random];
            if (random != newLength -1){
                newArray[random] = newArray[newLength - 1];
            }

            newLength--;
            newArray[newLength] = null;

            return item;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

    }
    //cast needed since no generic array creation in Java
    public RandomizedQueue(){
        items = (Item[]) new Object[2];
        N = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    private void resize(int n){
        Item[] newItems = (Item[]) new Object[n];
        for (int i = 0; i < N; i++){
            newItems[i] = items[i];
        }

        items = newItems;
    }
    public void enqueue(Item item){
        if (item == null){
            throw new NullPointerException();
        }

        if (N == items.length){
            resize(items.length * 2);
        }

        items[N] = item;
        N++;
    }

    private void dequeue(Item item){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        //Double size the array if necessary and recopy to the front of the array
        if (N == items.length){
            resize(items.length * 2);
        }

        items[N] = item;
        N--;

    }

    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(N);
        Item item = items[index];
        if (index != N - 1){
            items[index] = items[N - 1];
        }

        items[N - 1] = null;
        N--;

        if (N > 0 && N == items.length/4){
            resize(items.length/2);
        }

        return item;
    }

    public Item sample(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(N);
        Item item = items[index];

        return item;
    }



    public static void main(String[] args){
        RandomizedQueue queue = new RandomizedQueue<>();
        while(!StdIn.isEmpty()){
            String str = StdIn.readString();
            if (!str.equals("-")){
                queue.enqueue(str);
            }else if(!queue.isEmpty()){
                System.out.println(queue.dequeue() + " ");
            }
        }
        System.out.println("(" + queue.size() + "left on the queue");
    }
}
