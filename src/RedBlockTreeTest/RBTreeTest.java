package RedBlockTreeTest;

import java.util.Arrays;

/**
 * Describe：红黑树 测试类
 * Author：sunqiushun
 * Date：2018-08-10 14:34:12
 */
public class RBTreeTest {
    private static final int MAX = 30; // 数组最大值
    private static final int size = 15; // 数组长度

    public static void main(String[] args) {
        // int arr[] = {1, 4, 3, 6, 9, 7, 2, 5, 8};
        int arr[] = {11, 12, 13, 22, 29, 6, 1, 17, 15, 20, 5, 16, 7, 28, 26};
        // int[] arr = SetArray(size);
        RBTree<Integer> tree = new RBTree<Integer>();
        System.out.print("元数据： " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            tree.insert(arr[i]);
        }
        System.out.printf("\n前序遍历: ");
        tree.preOrder();
        System.out.printf("\n中序遍历: ");
        tree.inOrder();
        System.out.printf("\n后序遍历: ");
        tree.postOrder();
        System.out.printf("\n树的详细信息: \n");
        tree.print();

        int index = 1;
        tree.remove(arr[index]);
        System.out.print("\n删除节点 " + arr[index] + " 后的遍历：");
        System.out.printf("\n前序遍历: ");
        tree.preOrder();
        System.out.printf("\n中序遍历: ");
        tree.inOrder();
        System.out.printf("\n后序遍历: ");
        tree.postOrder();
        System.out.printf("\n树的详细信息: \n");
        tree.print();
    }

    /**
     * 生成指定大小的数组
     *
     * @param size
     * @return
     */
    private static int[] SetArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            int val = (int) (Math.random() * MAX);
            if (!checkValExist(arr, val)) {
                arr[i] = val;
            } else {
                i--;
            }
        }
        return arr;
    }

    private static boolean checkValExist(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) return true;
        }
        return false;
    }
}
