package com.liam.demo.collections;


import java.awt.*;

/**
 * LRU缓存，用单向链表实现
 * 缓存淘汰：：删除最近未使用元素
 * least Recently Use
 * 最近最少使用策略
 *
 * 结合了：
 * 1 FIFO: 先进先出策略，删除最早插入的，和使用频率无关
 * 2 LFU: 最少使用策略，删除使用最少的，和插入时间无关
 * LRU 综合考虑了插入的时间和使用频率，删除较早插入且使用较少的元素，即最近最少使用的元素
 */
public class LRUCache<K, V> {

    private Node<K, V> first;

    //当前元素个数
    private int size;

    //最大容量
    private int maxCapacity;

    //单向链表
    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    //构造LRUCache，并指定容量
    public LRUCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * 缓存查找，时间复杂度On
     *
     * @param key 查找的数据键
     * @return 数据的位置
     */
    public V get(K key) {

        //key不能为空
        if (key == null) {
            throw new NullPointerException();
        }

        Node<K, V> cursor = first;
        Node<K, V> lastCursor = null;
        while (cursor != null) {
            if (cursor.key == key) {

                //缓存命中，删除该节点并插入到头节点
                if (lastCursor != null) {
                    lastCursor.next = cursor.next;
                    cursor.next = first;
                    first = cursor;
                }
                System.out.println("get result from cache: " + cursor.value);
                return cursor.value;
            }

            lastCursor = cursor;
            cursor = cursor.next;
        }


        //创建新节点
        V value = getFromDB(key);
        Node<K, V> newNode = new Node<>(key, value, first);
        newNode.next = first;
        first = newNode;
        size++;

        System.out.println("get result froma db: " + value);

        //插入后如果超过最大容量，则删除尾节点
        checkCapacity();
        return value;
    }


    /**
     * 写入缓存
     * @param key 缓存键
     * @param value 缓存值
     */
    public void set(K key, V value){
        if (key == null){
            throw new RuntimeException();
        }

        //写入头节点
        Node<K,V> newNode = new Node<>(key, value, first);
        first = newNode;
        size++;

        //如果超出容量，则删除尾部节点
        checkCapacity();
    }


    //容量检查，超过容量则移除最后一个元素
    private void checkCapacity(){
        if (size > maxCapacity){
            Node<K,V> cursor = first;
            Node<K,V> lastCursor = null;
            while (cursor.next != null){
                lastCursor = cursor;
                cursor = cursor.next;
            }

            if (lastCursor == null){
                first = null;
            }else {
                lastCursor.next = null;
            }

        }
    }

    public void print(){
        Node<K, V> cursor = first;
        while (cursor != null){
            System.out.print(cursor.value);
            cursor = cursor.next;
        }

    }

    private V getFromDB(K key) {
        return (V) key;
    }


    public static void main(String args[]) {
        LRUCache<String,String> cache = new LRUCache<>(3);
        cache.set("1", "1");
        cache.set("2", "2");
        cache.set("3", "3");
        cache.get("1");
        cache.get("2");
        cache.get("3");
        cache.get("4");
        cache.get("4");
        cache.get("1");
    }
}
