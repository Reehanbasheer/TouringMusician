package com.reehan2906.touringmusician;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.util.Log;
import android.widget.Toast;

import java.util.Iterator;

public class CircularLinkedList implements Iterable<Point> {

    Node head, currentNode;


    private class Node {
        Point point;
        Node prev, next;

        Node() {

            this.point = null;
            this.prev = null;
            this.next = null;
        }

    }

    public void insertBeginning(Point p) {

        Node node = new Node();
        node.point = p;

        if (head == null) {
            head = node;
            currentNode = head;

        } else {


            currentNode.next = node;
            node.prev = currentNode;
            currentNode = node;
            head.prev = currentNode;
            currentNode.next = head;

        }
    }

    private float distanceBetween(Point from, Point to) {
        return (float) Math.sqrt(Math.pow(from.y - to.y, 2) + Math.pow(from.x - to.x, 2));
    }

    public float totalDistance() {
        float total = 0;
        Node startNode;
        startNode = head;

        if (startNode.next == null) {
            Log.i("info", "Require 2 points to calculate distance!");
        } else {
            while (startNode.next != head) {
                Log.i("info", "more than two points");
                total = total + distanceBetween(startNode.point, startNode.next.point);
                startNode = startNode.next;
            }
        }
        return total;
    }

    public void insertNearest(Point p) {

        Node node = new Node();
        node.point = p;

        Node startNode = head;
        Node previousNode = head;

        if (head == null || startNode.next == null) {
            insertBeginning(p);
        } else {

            float min;
            min = distanceBetween(node.point, startNode.point);
            while (startNode.next != head) {

                if (min > distanceBetween(node.point, startNode.point)) {
                    previousNode = startNode;
                    min = distanceBetween(node.point, startNode.point);
                }

                startNode = startNode.next;

            }

            node.prev = previousNode;
            node.next = previousNode.next;
            previousNode.next = node;

        }


    }

    public void insertSmallest(Point p) {

        Node startNode = head;

        if (startNode == null || startNode.next == null) {
            insertBeginning(p);
        } else {

            Node prevNode = startNode;
            startNode = startNode.next;

            float prevDist = distanceBetween(prevNode.point, p);
            float nextDist = distanceBetween(p, startNode.point);


            while (startNode.next != head) {
                float presentTotalDistance = distanceBetween(startNode.point, p) + distanceBetween(p, startNode.next.point);
                if (presentTotalDistance < (prevDist + nextDist)) {
                    prevNode = startNode;
                }
                startNode = startNode.next;
            }

            Node node = new Node();
            node.point = p;

            node.prev = prevNode;
            node.next = prevNode.next;

            prevNode.next = node;

        }
    }

    public void reset() {
        head = null;
    }

    private class CircularLinkedListIterator implements Iterator<Point> {

        Node current;

        public CircularLinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Point next() {
            Point toReturn = current.point;
            current = current.next;
            if (current == head) {
                current = null;
            }
            return toReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new CircularLinkedListIterator();
    }


}