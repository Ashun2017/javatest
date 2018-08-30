package AVLTreeTest;

import java.util.Arrays;

/**
 * Describe：平衡二叉树测试
 * Author：sunqiushun
 * Date：2018-08-10 14:19:40
 */
public class AVLTest {

    private static final int MAX = 50; // 数组最大值
    private static final int size = 10; // 数组长度

    public static void main(String[] args) {
        // int[] array = {8, 4, 9, 2, 1}; // LL
        // int[] array = {4, 2, 7, 8, 9}; // RR
        // int[] array = {4, 2, 7, 9, 8}; // RL
        // int[] array = {8, 4, 9, 2, 3}; // LR
        // int[] array = SetArray(size);
        int array[] = {26, 44, 42, 17, 41, 35, 36, 39, 32, 31};
        System.out.println("原数据：" + Arrays.toString(array));

        AVLTree avlTree = new AVLTree();
        System.out.print("avl旋转的次数：");
        for (int i = 0; i < array.length; i++) {
            avlTree.insert(array[i]);
        }
        // System.out.print(avlTree.getCountRotate() + " ");
        System.out.print("\n先序遍历：");
        avlTree.preOrder();
        System.out.print("\n中序遍历：");
        avlTree.inOrder();
        System.out.print("\n后序遍历：");
        avlTree.postOrder();

        System.out.print("\n删除节点：" + array[3]);
        avlTree.remove(array[3]);
        System.out.print("\n先序遍历：");
        avlTree.preOrder();
        System.out.print("\n中序遍历：");
        avlTree.inOrder();
        System.out.print("\n后序遍历：");
        avlTree.postOrder();
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
