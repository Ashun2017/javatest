package BSTreeTest;

/**
 * Describe：树节点
 * Author：sunqiushun
 * Date：2018-08-02 15:31:32
 */
public class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;
    public int leftMaxDistance; // 左子树的最大距离
    public int rightMaxDistance; // 右子树的最大距离
    public int leftMaxDepth;// 左子树的最大深度
    public int righMaxDepth; // 右子树的最大深度

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode node = (TreeNode) o;
        return data == node.data;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                '}';
    }
}
