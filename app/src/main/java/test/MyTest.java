package test;

/**
 * Created by Administrator on 2018/3/7.
 */

public class MyTest {


    public static void main(String[] args) {
        int[] arr = new int[]{};
        BubbleSort(arr);
    }


    public static void BubbleSort(int[] arr) {
        int temp;//临时变量
        for (int i = 0; i < arr.length - 1; i++) {
            //表示趟数，一共arr.length-1次。
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    System.err.println("j:" + arr[j] + "j-1:" +  arr[j - 1] );
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }


}
