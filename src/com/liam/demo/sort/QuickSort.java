package com.liam.demo.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 快速排序
 * 1 选择一个基数，对基数进行分区
 * 2 分别对左右分区进行递归快速排序
 */
public class QuickSort extends BaseSort {

    @Override
    public void doSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {

        if (left < right){
            //分区，分别对左边和右边进行递归排序
            int base = partion(nums, left, right);
            quickSort(nums, left, base - 1);
            quickSort(nums, base + 1, right);
        }
    }

    /**
     * 分区算法
     * @param nums 数组
     * @param left 左侧指针
     * @param right 右侧指针
     * @return 分区后基数的位置
     */
    private int partion(int[] nums, int left, int right) {

        //取第一个数为基数
        int base = nums[left];
        
        //如果基数在左侧，与右侧指针比较，否则与左侧指针比较
        int baseIndex = left;
        while (left < right) {

            //如果右侧数值比基数小，则将基数交换到右边
            if (nums[right] < base) {
                swap(nums, left, right);
                baseIndex = right;
            }

            //如果左侧数值比基数大，则基数交换到左边
            if (nums[left] > base) {
                swap(nums, left, right);
                baseIndex = left;
            }

            //基数在左边，则更新右侧指针进行比较，否则反过来
            if (baseIndex == left) {
                right--;
            } else {
                left++;
            }
        }

        return baseIndex;
    }


    public static void main(String args[]) {

        int[] nums = new int[]{4, 7, 1, 0, 2, 9, 5, 3, 2};
        QuickSort quickSort = new QuickSort();

        quickSort.sort(nums);
        for (int num : nums) {
            System.out.print(num);
        }

    }
}
