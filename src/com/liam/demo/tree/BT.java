package com.liam.demo.tree;

import java.util.LinkedList;

/**
 * binary tree
 * 二叉树
 *
 */
public class BT {

    /**
     * 二叉树节点
     */
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }


    /**
     * 深度前序遍历
     *
     * @param root 根节点
     */
    public void preOrderTraversel(TreeNode root) {
        if (root != null) {
            System.out.println(root.value);
            preOrderTraversel(root.left);
            preOrderTraversel(root.right);
        }
    }

    /**
     * 深度中序遍历
     *
     * @param root 根节点
     */
    public void inOrderTraversel(TreeNode root) {
        if (root != null) {
            inOrderTraversel(root.left);
            System.out.println(root.value);
            inOrderTraversel(root.right);
        }
    }

    /**
     * 深度后序遍历
     *
     * @param root 根节点
     */
    public void postOrderTraversel(TreeNode root) {
        if (root != null) {
            postOrderTraversel(root.left);
            postOrderTraversel(root.right);
            System.out.println(root.value);

        }
    }


    /**
     * breathe first search 广度优先遍历 BFS
     *
     * @param root 根节点
     */
    public void bfs(TreeNode root) {

        if (root == null) {
            return;
        }

        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.offer(root);
        while (!treeNodes.isEmpty()){
            root = treeNodes.poll();
            System.out.println(root.value);
            if (root.left != null){
                treeNodes.offer(root.left);
            }

            if (root.right != null){
                treeNodes.offer(root.right);
            }
        }
    }


}
