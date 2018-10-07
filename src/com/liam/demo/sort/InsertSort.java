package com.liam.demo.sort;


/**
 * 插入排序
 * 每次都从未排序的列表中选择一个元素从后往前插入到已排序的列表
 */
public class InsertSort extends BaseSort {
    @Override
    public void doSort(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {

            //select the elements wait to insert
            int waitToInsert = nums[i + 1];
            for (int j = i; j >= -1; j--) {
                //insert
                if (j == -1 || nums[j] <= waitToInsert){
                    nums[j + 1] = waitToInsert;
                    break;
                }else {
                    //move bigger element to the right
                    nums[j + 1] = nums[j];
                }
            }
        }
    }


    public static void main(String args[]) {
        int[] nums = new int[]{4, 7, 1, 0, 2, 9, 5, 3, 2};

        Sort insertSort = new InsertSort();
        insertSort.sort(nums);

        for (int num : nums) {
            System.out.print(num);
        }
    }
}
