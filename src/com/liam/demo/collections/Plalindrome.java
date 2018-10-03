package com.liam.demo.collections;

import java.util.LinkedList;

/**
 * 用单向链表实现回文串的判断
 */
public class Plalindrome {

    //头节点
    private Node first;

    //尾节点
    private Node last;


    //单向链表
    private class Node {
        String value;
        Node next;

        public Node(String character) {
            this.value = character;
        }

        public Node(String character, Node next) {
            this.value = character;
            this.next = next;
        }
    }

    //添加元素
    public void add(String character) {

        Node newNode = new Node(character);
        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }


    /**
     * 判断字符串是否为回文串
     * O(n)的时间和空间复杂度
     *
     * @return 是否为回文串
     */
    public boolean isPlaindromeOnSpace() {

        //装入栈
        Node cursor = first;
        LinkedList<String> stack = new LinkedList<>();
        while (cursor != null) {
            stack.push(cursor.value);
            cursor = cursor.next;
        }

        //比较栈和链表
        cursor = first;
        while (cursor != null){
            if (!cursor.value.equals(stack.pop())) return false;
            cursor = cursor.next;
        }

        return true;
    }

    public boolean isPlaindromeO1Spce(){


        //1 找到中间节点
        Node middle = findMiddle(first);

        //2 对后半部分链表逆序
        reverseLastHalfPart(middle);

        //3 比较前后半部分
        boolean result = checkPlaindrome(first, middle);

        //后半部分逆序回来
        reverseLastHalfPart(middle);

        return result;
    }


    //定位中间节点O(n/2)，采用快慢指针
    public Node findMiddle(Node first){
        Node slowCursor = first;
        Node fastCursor = first;
        while (fastCursor != null && fastCursor.next != null && fastCursor.next.next != null){
            fastCursor = fastCursor.next.next;
            slowCursor = slowCursor.next;
        }

        return slowCursor;
    }

    public void reverseLastHalfPart(Node middle){
        Node previous = null;
        Node cursor = middle.next;

        while (cursor != null){
            Node nextCursor = cursor.next;
            cursor.next = previous;
            previous = cursor;
            cursor = nextCursor;
        }

        middle.next = previous;
    }

    public boolean checkPlaindrome(Node first, Node middle){
        Node cursor1 = first;
        Node cursor2 = middle.next;

        while (cursor2 != null){
            if (!cursor1.value.equals(cursor2.value)){
                return false;
            }

            cursor1 = cursor1.next;
            cursor2 = cursor2.next;
        }

        return true;
    }


    public void print(){
        Node cursor = first;
        while (cursor != null){
            System.out.print(cursor.value);
            cursor = cursor.next;
        }

    }

    public static void main(String args[]) {



        //1 构建链表
        Plalindrome plalindrome = new Plalindrome();
        plalindrome.add("a");
        plalindrome.add("b");
        plalindrome.add("c");
        plalindrome.add("d");
        plalindrome.add("e");
        plalindrome.add("d");
        plalindrome.add("c");
        plalindrome.add("b");
        plalindrome.add("a");
        System.out.println(plalindrome.isPlaindromeO1Spce());
        plalindrome.print();


//        System.out.println(plalindrome.isPlaindromeUsingStack());

    }

}
