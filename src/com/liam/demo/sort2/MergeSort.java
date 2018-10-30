package com.liam.demo.sort2;

import com.liam.demo.sort.BaseSort;

/**
 *   归并排序再实现
 *
 *   1 分治
 *   2 合并
 *   合并算法的优化
 *   时间复杂度O（nlogn)，空间复杂度O(n)
 */
public class MergeSort extends BaseSort {


    @Override
    public void doSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int p, int r) {
        if (p >= r) return;
        int q = (p + r) / 2;
        mergeSort(nums, p, q);
        mergeSort(nums, q + 1, r);
        merge(nums, p, q, r);
    }

    private void merge(int[] nums, int p, int q, int r) {
        int[] tmpNums = new int[r - p + 1];
        int i = p, j = q + 1, t = 0;
        while (i <= q || j <= r) {

            if (i > q){
                tmpNums[t++] = nums[j++];
            }else if (j > r){
                tmpNums[t++] = nums[i++];
            }else if (nums[i] <= nums[j]){
                tmpNums[t++] = nums[i++];
            }else {
                tmpNums[t++] = nums[j++];
            }
        }

        for (int f = 0; f < tmpNums.length; f++){
            nums[p + f] = tmpNums[f];
        }
    }

    public static void main(String args[]) {
       int[] nums = new int[]{2,9,4,7,3,1,3,5};
        new MergeSort().doSort(nums);
        for (int i = 0; i < nums.length; i++){
            System.out.print(nums[i]);
        }
    }
}
