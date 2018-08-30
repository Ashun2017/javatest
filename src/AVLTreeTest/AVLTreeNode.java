package AVLTreeTest;

/**
 * Describe：AVL树节点
 * Author：sunqiushun
 * Date：2018-08-07 15:54:09
 */
public class AVLTreeNode {
    private int data;//结点的数据
    private int height;//树的高度
    private AVLTreeNode left;//指向左孩子结点
    private AVLTreeNode right;//指向左孩子结点

    public AVLTreeNode(int data) {
        this.data = data;
        this.height = 0;
        this.left = null;
        this.right = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLTreeNode getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode left) {
        this.left = left;
    }

    public AVLTreeNode getRight() {
        return right;
    }

    public void setRight(AVLTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "data=" + data +
                ", height=" + height +
                '}';
    }
}
