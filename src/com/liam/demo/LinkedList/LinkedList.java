package com.liam.demo.LinkedList;

public class LinkedList {

    //单向链表节点
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 构建单向链表
     *
     * @param nums 数组
     * @return 链表头节点
     */
    public static ListNode buildSingleLinkedList(int[] nums) {
        ListNode head = new ListNode(0);
        ListNode cursor = head;
        for (int num : nums) {
            cursor.next = new ListNode(num);
            cursor = cursor.next;
        }

        return head.next;
    }

    /**
     * 打印单向链表
     *
     * @param head 头节点
     */
    public static void printSingleLinkedList(ListNode head) {

        ListNode cursor = head;
        while (cursor != null) {
            System.out.print(cursor.val);
            System.out.print("->");
            cursor = cursor.next;
        }

        System.out.print("null");
    }

    public static void main(String args[]) {

        printSingleLinkedList(buildSingleLinkedList(new int[]{1,3,4,6,8,2}));
    }
}
