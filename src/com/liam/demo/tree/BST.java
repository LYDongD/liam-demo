package com.liam.demo.tree;

/**
 * binary search tree
 * 二叉查找树
 */
public class BST extends BT{


    /**
     * 构造一棵BST
     * 循环插入节点
     *
     * @param nums 节点数据
     * @return 二叉查找树根节点
     */
    public TreeNode buidlBST(int[] nums) {

        TreeNode node = null;
        for (int num : nums) {
            node = insert(node, num);
        }
        return node;
    }



    /**
     * 插入节点
     *
     * @param root BST 根节点
     * @param key  待插入节点值
     * @return BST根节点
     */
    public TreeNode insert(TreeNode root, int key) {

        if (root == null) { //init root
            return new TreeNode(key);
        }

        if (root.value > key) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }

        return root;
    }


    /**
     * BST查找
     *
     * @param root BST根节点
     * @param key  查找目标key
     * @return 目标节点
     */
    public TreeNode search(TreeNode root, int key) {

        //recurse root until root is null
        if (root == null) {
            return null;
        }

        if (root.value == key) {
            return root;
        }

        TreeNode nextRoot = root.value > key ? root.left : root.right;

        return search(nextRoot, key);
    }


    /**
     * 删除最小节点
     *
     * 1 沿左子树查找到最小节点
     * 2 让最小节点的根节点指向最小节点的右节点
     * @param root 根节点
     */
    public void deleteMin(TreeNode root){
        root = deleteMinAndReturn(root);
    }

    private TreeNode deleteMinAndReturn(TreeNode root){
        if (root.left == null){
            return root.right;
        }
        root.left = deleteMinAndReturn(root.left);
        return root;
    }



    /**
     * 删除节点
     * 删除后返回剩下的根节点
     * @param root 根节点
     * @param key 待删除元素
     */
    public void delete(TreeNode root, int key){
        root = deleteAndReturn(root, key);
    }


    public TreeNode deleteAndReturn(TreeNode root, int key){

        if (root.value == key){

            if (root.left == null && root.right == null){
                return null;
            }else if (root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }else {

                //查找右子树的最小节点
                TreeNode rightMinNode = root.right;
                while (rightMinNode.left != null){
                    rightMinNode = rightMinNode.left;
                }

                //将该节点作为根节点，调整左右孩子
                rightMinNode.left = root.left;
                rightMinNode.right = deleteMinAndReturn(root.right);
                return rightMinNode;
            }
        }else if (key < root.value){
            root.left = deleteAndReturn(root.left, key);
        }else {
            root.right = deleteAndReturn(root.right, key);
        }

        return root;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{4, 2, 6, 1, 3, 5, 7};


        BST bst = new BST();
        TreeNode node = bst.buidlBST(nums);
        bst.preOrderTraversel(node);
        System.out.println("-----");
        bst.inOrderTraversel(node);
        System.out.println("-----");
        bst.postOrderTraversel(node);
        System.out.println("-----");
        bst.bfs(node);
        System.out.println("-----");
        TreeNode result = bst.search(node, 2);
        System.out.println(result == null ? null : result.value);
    }

}
