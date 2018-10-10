package com.liam.demo.LinkedList;


/**
 *  合并两个有序链表
 */
public class LinkedListOrderedMerge extends LinkedList{

    /**
     * 合并两个有序链表
     *
     * 1 准备哨兵节点
     * 2 哨兵指针总是指向两个链表未比较的节点中较小的一个
     * 3 更新哨兵前更新最新未比较节点
     * @param l1 链表1
     * @param l2 链表2
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){

        ListNode dummy = new ListNode(0);
        ListNode cursor = dummy;

        //总是指向相对小的节点
        while (l1 != null && l2 != null){
           if (l1.val < l2.val){
               cursor.next = l1;
               l1 = l1.next;
           }else{
               cursor.next = l2;
               l2 = l2.next;
           }
           cursor = cursor.next;
        }

        //剩余另外一条不为空的链表
        cursor.next = l1 == null ? l2 : l1;

        //消除哨兵节点
        ListNode head = dummy.next;
        dummy.next = null;

        return head;
    }


}
