package com.liam.demo.sort;

/**
 * count sort is a linear sort, which has onlu O(n）time complexity
 * <p>
 * 1 confirm the data range
 * 2 count data using bucket
 * 3 scan back to forward from the array and sort by the count bucket
 */
public class CountSort extends BaseSort {


    @Override
    public void doSort(int[] nums) {

        if (nums.length == 1) return;


        //confirm the data range
        int max = nums[0];
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }

        //count data using bucket
        int[] buckets = new int[max + 1];
        for (int num : nums) {
            buckets[num]++;
        }

        int sum = 0;
        for (int i = 0; i < buckets.length; i++) {
            sum += buckets[i];
            buckets[i] = sum;
        }

        //scan back to forward and sort
        int[] sortNums = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int count = buckets[nums[i]]--;
            sortNums[count - 1] = nums[i];
        }

        System.arraycopy(sortNums, 0, nums, 0, nums.length);
    }


    public static void main(String args[]) {
        int[] nums = new int[]{2,5,3,9,0,2,3,0,3,6};
        Sort countSort = new CountSort();
        countSort.sort(nums);
        printAll(nums);

    }
}
