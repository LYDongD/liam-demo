package com.liam.demo.sort;

/**
 * 冒泡排序
 * 每次排序，都把最大的交换到最右边
 */
public class BubbleSort extends BaseSort {


    @Override
    public void doSort(int[] nums) {
        //define the iterator count
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void main(String args[]) {

        int[] nums = new int[]{4, 7, 1, 0, 2, 9, 5, 3, 2};

        Sort bubbleSort = new BubbleSort();
        bubbleSort.sort(nums);

        for (int num : nums) {
            System.out.print(num);
        }
    }
}
