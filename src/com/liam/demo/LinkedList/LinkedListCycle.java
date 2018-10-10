package com.liam.demo.LinkedList;

import java.util.HashSet;
import java.util.Set;

/**
 *  链表中环检测
 */
public class LinkedListCycle {

    //单向链表节点
    private class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 快慢指针法
     * 时间复杂度 O（n + k) = O(n), 其中n为节点数，k为环内指针的最大距离
     * 由于快慢指针速度差1，距离至多为环长度k, 两指针汇合至多需要O(k)的时间
     * 空间复杂度O(1)
     * @param head 头结点
     * @return 是否循环
     */
    public boolean isCycle(ListNode head){

        //fast-slow-pointer
        ListNode fastCursor = head;
        ListNode slowCursor = head;

        while (fastCursor != null && fastCursor.next != null){
            fastCursor = fastCursor.next.next;
            slowCursor = slowCursor.next;
            if (fastCursor == slowCursor) return true;
        }

        return false;
    }


    /**
     * hash缓存
     * 时间复杂度On，所有节点仅被遍历一次
     * @param head 头结点
     * @return 是否成环
     */
    public boolean isCycle2(ListNode head){

        Set<ListNode> nodeSet = new HashSet<>();
        ListNode cursor = head;
        while (cursor != null){
            if (nodeSet.contains(cursor)){
                return true;
            }else {
                nodeSet.add(cursor);
            }

            cursor = cursor.next;
        }

        return false;
    }


}
