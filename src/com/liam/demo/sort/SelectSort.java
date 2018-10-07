package com.liam.demo.sort;

/**
 * 选择排序
 * 每次都把最小的选出来放在左边
 */
public class SelectSort extends BaseSort {


    @Override
    public void doSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    public static void main(String args[]) {
        int[] nums = new int[]{4, 7, 1, 0, 2, 9, 5, 3, 2};

        Sort selectSort = new SelectSort();
        selectSort.sort(nums);

        for (int num : nums) {
            System.out.print(num);
        }
    }
}
