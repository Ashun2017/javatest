package BSTreeTest;

import java.util.Arrays;
import java.util.Stack;

/**
 * Describe：二叉树测试类
 * Author：sunqiushun
 * Date：2018-08-02 16:18:30
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        int max = 10;
        int[] arr = new int[5];
        setArrayData(arr, max);
        System.out.println(Arrays.toString(arr));
        BinaryTree bTree = new BinaryTree(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            bTree.Add(arr[i]);
        }

        System.out.print("先序遍历：");
        bTree.PreOrder(false);
        // bTree.PreOrder(true);
        System.out.print("\n中序遍历：");
        bTree.InOrder(false);
        // bTree.InOrder(true);
        System.out.print("\n后序遍历：");
        bTree.PostOrder(false);
        // bTree.PostOrder(true);
       /* System.out.println("\n统计节点数：" + bTree.Size());
        System.out.println("树的高度：" + bTree.Height());
        System.out.println("查找3是否存在：" + bTree.Find(3));
        System.out.println("查找7是否存在：" + bTree.Find(7));
        System.out.println("树中的最小值：" + bTree.FindMinVal().data);
        System.out.println("树中的最大值：" + bTree.FindMaxVal().data);
        int data = arr[(int) (Math.random() * arr.length)]; // 待删除的节点
        System.out.println("待删除的节点：" + data);
        TreeNode node = bTree.GetTreeNodeParent(new TreeNode(data));
        System.out.println("查找待删除节点的父节点：" + (node != null ? node.toString() : null));
        TreeNode delNode = bTree.RemoveTreeNode(data);
        System.out.println("要删除的节点： " + (delNode != null ? delNode.data : null));
        System.out.println("统计节点数：" + bTree.Size());
        System.out.print("先序序遍历： ");
        bTree.PreOrder(false);
        System.out.print("中序遍历： ");
        bTree.InOrder(false);
        System.out.print("后序遍历： ");
        bTree.PostOrder(false);
        System.out.println("获取树的最大宽度：" + bTree.GetTreeMaxWidth(true));
        System.out.println("获取树的最大宽度：" + bTree.GetTreeMaxWidth(false));
        System.out.println("获取树的最大距离：" + bTree.FindMaxDistance());
        System.out.println("获取树的最大深度：" + bTree.FindMaxDepth());
        int val = (int) (Math.random() * max);
        Stack<TreeNode> path = bTree.FindPathOfNodeToRoot(new TreeNode(val));
        System.out.println("\n获取节点 " + val + " 到根结单的路径：" + path );*/

        TreeNode first = new TreeNode(arr[(int) (Math.random() * 5)]);
        TreeNode second = new TreeNode(arr[(int) (Math.random() * 5)]);
        TreeNode closetParentNode = bTree.FindClosestParentNode(first, second);
        System.out.println("\n找到 " + first + " 和 " + second + " 节点的最近的父节点：" + closetParentNode);
        Stack stackPath = bTree.FindPathOfNodeToNode(first, second);
        System.out.println("\n找到 " + first + " 到 " + second + " 节点的路径：" + stackPath);


    }

    private static void setArrayData(int[] arr, int max) {
        for (int i = 0; i < arr.length; i++) {
            int random = (int) (Math.random() * max);
            boolean isRepete = checkRepete(arr, random);
            if (!isRepete) {
                arr[i] = random;
            } else {
                i--;
            }
        }
    }

    private static boolean checkRepete(int[] arr, int data) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data) {
                return true;
            }
        }
        return false;
    }

}
