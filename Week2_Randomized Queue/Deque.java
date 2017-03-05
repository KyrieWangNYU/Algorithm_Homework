package com.company;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by kyrie on 3/4/17.
 */
public class Deque<Item> implements Iterable<Item> {

    private int N;
    private Node start;
    private Node end;

    private class Node{
        private Item item;
        private Node next;
        private Node prev;
    }

    //Initializes an empty Deque
    public Deque(){
        N = 0;
        start = null;
        end = null;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void addFirst(Item item){
        if (item == null){
            throw new NullPointerException();
        }

        Node temp = start;
        start = new Node();
        start.prev = null;
        start.item = item;
        if (isEmpty()){
            end = start;
        }
        else{
            start.next = temp;
            temp.prev = start;
        }
        N++;
    }

    public void addLast(Item item){
        if (item == null){
            throw new NullPointerException();
        }

        Node temp = end;
        end = new Node();
        end.next = null;
        end.item = item;
        if (isEmpty()){
            start = end;
        }
        else{
            temp.next = end;
            end.prev = temp;
        }
        N++;
    }

    public Item removeFirst(){
        if (size() == 0){
            throw new NoSuchElementException();
        }

        Item item = start.item;
        start = start.next;
        N--;
        if (size() == 0){
            end = null;
        }else{
            start.prev = null;
        }

        return item;
    }

    public Item removeLast(){
        if (size() == 0){
            throw new NoSuchElementException();
        }

        Item item = end.item;
        end = end.prev;
        N--;
        if (size() == 0){
            start = null;
        }else{
            end.next = null;
        }
        return item;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node cur = start;

        public boolean hasNext(){
            return cur != null;
        }

        public Item next(){
            if (hasNext()){
                throw new NoSuchElementException();
            }

            Item item = cur.item;
            cur = cur.next;
            return item;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args){
        Deque<String> deque = new Deque<String>();

        deque.addFirst("study");
        deque.addFirst("I");
        deque.addFirst("and");
        deque.addFirst("You");
        deque.addLast("in");
        deque.addLast("NYU");
        deque.addLast("Poly");
//            deque.removeLast();
//            deque.removeFirst();
//            deque.removeFirst();
//            deque.removeFirst();
//            deque.removeFirst();
//            deque.removeFirst();
//            deque.removeFirst();

        StdOut.println("output");
        for (String x : deque){
            StdOut.print(x + ' ');
        }

    }



}

