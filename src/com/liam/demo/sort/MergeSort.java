package com.liam.demo.sort;

/**
 * 归并排序
 * 将数组一分为2，对左右两边分别递归排序，合并排序后的数组
 */
public class MergeSort extends BaseSort {

    @Override
    public void doSort(int[] nums) {

        mergeSort(nums, 0, nums.length - 1);

    }

    private void mergeSort(int[] nums, int low, int high) {

        int middle = (low + high) / 2;

        if (low < high) {
            mergeSort(nums, low, middle);
            mergeSort(nums, middle + 1, high);
            merge(nums, low, middle, high);
        }
    }

    //合并数组
    public void merge(int[] nums, int low, int middle, int high){

        int[] tmp  = new int[high - low + 1];
        int k = 0;
        int i = low;
        int j = middle + 1;

        //from low to high, select least one to tmp
        while (i <= middle && j <= high){
            if (nums[i] < nums[j]){
                tmp[k++] = nums[i++];
            }else {
                tmp[k++] = nums[j++];
            }
        }

        while (i <= middle){
            tmp[k++] = nums[i++];
        }

        while (j <= high){
            tmp[k++] = nums[j++];
        }

        //cover nunms
        for (int t = 0; t < tmp.length; t++){
            nums[low + t] = tmp[t];
        }

    }

    public static void main(String args[]) {
        int[] nums = new int[]{4, 7, 1, 0, 2, 9, 5, 3, 2};

        Sort mergeSort = new MergeSort();
        mergeSort.sort(nums);

        for (int num : nums) {
            System.out.print(num);
        }
    }


}
