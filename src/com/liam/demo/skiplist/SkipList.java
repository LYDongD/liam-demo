package com.liam.demo.skiplist;

import java.util.Random;

public class SkipList {


    //actual level for skip list, and may change when new node being insert, default 1
    private int listLevel = 1;

    //default max level
    private static int MAX_LEVEL = 16;

    //randomly generate level for new node to insert
    private Random random = new Random();

    private Node head = new Node();

    //skip list node, a node can have more than 1 level
    private class Node {

        private int val = -1;

        //node level pointers, each level has a forward pointer
        private Node[] forwards = new Node[MAX_LEVEL];

        private int maxLevel = 0;

        Node(){}

        private Node(int val, Node[] forwards, int maxLevel) {
            this.val = val;
            this.forwards = forwards;
            this.maxLevel = maxLevel;
        }

    }


    /**
     * print the bottom level nodes
     */
    public void printAll() {
        Node cursor = head;
        while (cursor.forwards[0] != null) {
            System.out.print(cursor.forwards[0].val);
            System.out.print("->");
            cursor = cursor.forwards[0];
        }
    }

    /**
     * insert a new node
     *
     * @param value the value to be inserted
     */
    public void insert(int value) {

        //1 generate node level and construct a new node
        int level = generateLevel();
        Node newNode = new Node(value, new Node[level], level);

        //use update to mark the place to insert
        Node[] update = new Node[level];

        //2 find the place to insert on each level
        Node cursor = head;
        //level index begins from 0 to level - 1, iterate level from up to bottom
        for (int i = level - 1; i >= 0; i--) {
            while (cursor.forwards[i] != null && cursor.forwards[i].val < value) {
                cursor = cursor.forwards[i];
            }

            update[i] = cursor;
        }

        //3 insert according to update
        for (int i = 0; i < update.length; i++) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        //4 update list level
        if (level > listLevel) {
            listLevel = level;
        }
    }


    /**
     * delete a node, may not find the node
     *
     * @param value the node value
     */
    public void delete(int value) {

        //mark the node to delete using update array
        Node[] update = new Node[listLevel];

        //mark the node to delete at each level
        Node cursor = head;
        for (int i = listLevel - 1; i >= 0; i--) {
            while (cursor.forwards[i] != null && cursor.forwards[i].val < value) {
                cursor = cursor.forwards[i];
            }

            update[i] = cursor;
        }

        //delete node and relative level pointer
        if (cursor.forwards[0] != null && cursor.forwards[0].val == value) {
            for (int i = listLevel - 1; i >= 0; i--) {
                if (update[i].forwards[i] != null && update[i].forwards[i].val == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

    }

    /**
     * find node, which may not exist
     *
     * @param value node value
     */
    public Node find(int value) {

        Node cursor = head;
        for (int i = listLevel - 1; i >= 0; i--) {
            while (cursor.forwards[i] != null && cursor.forwards[i].val < value) {
                cursor = cursor.forwards[i];
            }
        }

        if (cursor.forwards[0] != null && cursor.forwards[0].val == value) {
            return cursor.forwards[0];
        }

        return null;
    }


    //randomly decide if add level for each possible level to the max
    private int generateLevel() {
        //from 1 -> max_level
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; i++) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }

        return level;
    }


    public static void main(String args[]) {
        SkipList skipList = new SkipList();
        skipList.insert(1);
        skipList.insert(3);
        skipList.insert(5);
        skipList.insert(6);

        skipList.printAll();

        System.out.println();

        System.out.println(skipList.find(5).val);

        skipList.delete(5);

        skipList.printAll();

        System.out.println();

        System.out.println(skipList.find(1).val);
    }


}
