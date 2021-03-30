package com.company;

import java.lang.ArrayIndexOutOfBoundsException;
import java.util.EmptyStackException;

public class SuperList<E> {

    private ListNode<E> root;
    private ListNode<E> end;
    private int size;

    public SuperList() {
        root = null;
        end = null;
        size = 0;
    }

    public SuperList(E val) {
        this.root = new ListNode<E>(val);
        this.end = this.root;
        size = 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(E val) {
        ListNode<E> newNode = new ListNode<E>(val);
        if (root == null) {
            root = newNode;
            end = root;
        }
        else {
            newNode.setPrevious(end);
            end.setNext(newNode);
            end = newNode;
        }
        size++;
    }

    public void push(E val) {
        add(val);
    }

    public void add(int index, E value) {
        if (index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();

        ListNode<E> newNode = new ListNode<E>(value);

        if (index == size()) {
            add(value);
        }
        else if (index == 0) {
            root.setPrevious(newNode);
            newNode.setNext(root);
            root = newNode;
            size++;
        }
        else {
            ListNode<E> temp = root;
            for (int i = 0; i < index; i++)
                temp = temp.getNext();
            ListNode<E> prev = temp.getPrevious();
            newNode.setNext(temp);
            newNode.setPrevious(prev);
            prev.setNext(newNode);
            newNode.setNext(temp);
            temp.setPrevious(newNode);
            size++;
        }
    }

    public E get(int index) {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        ListNode<E> temp = root;
        for (int i = 0; i < index; i++)
            temp = temp.getNext();

        return temp.getValue();
    }

    public boolean contains(E val) {
        if (size == 0)
            return false;

        ListNode<E> temp = root;
        for (int i = 0; i < size; i++) {
            if (temp.getValue().equals(val))
                return true;
            temp = temp.getNext();
        }

        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException();

        if (index == 0)
            return poll();
        if (index == size()-1)
            return pop();

        ListNode<E> temp = root;
        for(int i = 0; i < index; i++)
            temp = temp.getNext();

        E val = temp.getValue();
        temp.getPrevious().setNext(temp.getNext());
        temp.getNext().setPrevious(temp.getPrevious());
        size--;

        return val;
    }

    public E poll() {
        if (size == 0)
            return null;

        E val = root.getValue();
        if (size == 1)
            clear();
        else {
            root = root.getNext();
            root.setPrevious(null);
            size--;
        }

        return val;
    }

    public E peekQueue() {
        if (root == null)
            return null;
        return root.getValue();
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();

        E val = end.getValue();
        if (size == 1)
            clear();
        else {
            end = end.getPrevious();
            end.setNext(null);
            size--;
        }

        return val;
    }

    public E peekStack() {
        if (end == null)
            throw new EmptyStackException();
        return end.getValue();
    }

    public void clear() {
        root = null;
        end = null;
        size = 0;
    }

    public String toString() {
        String output = "[";
        ListNode<E> temp = root;
        for (int i = 0; i < size; i++) {
            output += temp.getValue();
            if (i < size-1)
                output += ",";
            temp = temp.getNext();
        }
        output += "]";

        return output;
    }

    public class ListNode<E> {

        private E value;
        private ListNode<E> next;
        private ListNode<E> previous;

        public ListNode(E value) {
            this.value = value;
            next = null;
            previous = null;
        }

        public E getValue() {
            return value;
        }

        public ListNode<E> getNext() {
            return next;
        }

        public ListNode<E> getPrevious() {
            return previous;
        }

        public void setNext(ListNode<E> newNode) {
            this.next = newNode;
        }

        public void setPrevious(ListNode<E> newNode) {
            this.previous = newNode;
        }

    }

}