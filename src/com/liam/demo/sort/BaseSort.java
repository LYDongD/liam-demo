package com.liam.demo.sort;

public abstract class BaseSort implements Sort {

    public void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[j] ^ nums[i];
        nums[i] = nums[i] ^ nums[j];
    }


    @Override
    public void sort(int[] nums) {
        doSort(nums);
    }

    public abstract void doSort(int[] nums);


}
