package BSTreeTest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Describe：二叉树
 * Author：sunqiushun
 * Date：2018-08-02 15:34:16
 */
public class BinaryTree {
    private TreeNode root;

    public BinaryTree(int val) {
        root = new TreeNode(val);
    }
    // --------------------------------------- 二叉树的增删改查 --------------------------------------------------

    /**
     * 添加节点
     *
     * @param val
     */
    public void Add(int val) {
        add(root, val);
    }

    /**
     * 先序遍历
     */
    public void PreOrder(boolean isRecursive) {
        if (isRecursive) {
            preOrder(root);
        } else {
            noRecursivePreOrder(root);
        }
    }

    /**
     * 中序遍历
     */
    public void InOrder(boolean isRecursive) {
        if (isRecursive) {
            inOrder(root);
        } else {
            noRecursiveInOrder(root);
        }
    }

    /**
     * 后序遍历
     */
    public void PostOrder(boolean isRecursive) {
        if (isRecursive) {
            postOrder(root);
        } else {
            noRecursivePostOrder(root);
        }
    }

    /**
     * 树的节点数
     *
     * @return
     */
    public int Size() {
        return size(root);
    }

    /**
     * 树的高度
     *
     * @return
     */
    public int Height() {
        return height(root);
    }

    /**
     * 查找
     *
     * @param val
     * @return
     */
    public TreeNode Find(int val) {
        return find(root, val);
    }

    /**
     * 查找树中的最小值
     *
     * @return
     */
    public TreeNode FindMinVal() {
        return findMinVal(root);
    }

    /**
     * 查找树中的最大值
     *
     * @return
     */
    public TreeNode FindMaxVal() {
        return findMaxVal(root);
    }

    /**
     * 查找树中指定节点的父节点
     *
     * @return
     */
    public TreeNode GetTreeNodeParent(TreeNode element) {
        if (Find(element.data) == null) return null;
        return getTreeNodeParent(root, element);
    }

    /**
     * 删除树中指定的节点
     *
     * @return
     */
    public TreeNode RemoveTreeNode(int val) {
        return removeTreeNode(val);
    }

    private TreeNode add(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        } else if (val > node.data) {
            node.right = add(node.right, val);
            return node;
        } else if (val < node.data) {
            node.left = add(node.left, val);
            return node;
        }
        return node;
    }

    /**
     * 先序遍历 - 递归实现
     */
    private void preOrder(TreeNode node) {
        if (node != null) {
            visted(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历 - 递归实现
     */
    private void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            visted(node);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历 - 递归实现
     */
    private void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            visted(node);
        }
    }

    /**
     * 先序遍历 - 非递归实现
     */
    private void noRecursivePreOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                visted(node);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    /**
     * 中序遍历 - 非递归实现
     */
    private void noRecursiveInOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                visted(node);
                node = node.right;
            }
        }
    }

    /**
     * 后序遍历 - 非递归实现
     */
    private void noRecursivePostOrder(TreeNode root) {
        if (null == root) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode lastVisited = root;
        while (true) {
            // 左路入栈
            while (node.left != null) {
                stack.push(node);    //第一次压栈, 为了访问左路后退出
                node = node.left;
            }
            // 连续处理从右路返回的节点
            // node.right == lastVisited 说明右孩子已经处理过了
            while (node.right == null || node.right == lastVisited) {
                // 访问并纪录本次访问节点
                visted(node);
                lastVisited = node;
                if (stack.empty()) { // 退出的条件
                    return;
                }
                node = stack.pop();
            }
            // 处理从左路返回的节点
            stack.push(node);  // 第二次压栈, 为了访问右路后退出
            node = node.right;
        }
    }

    private int size(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.left) + size(node.right);
        }
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
        }
    }

    private TreeNode find(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        if (val > node.data) {
            return find(node.right, val);
        } else if (val < node.data) {
            return find(node.left, val);
        } else {
            return node;
        }
    }

    private TreeNode findMinVal(TreeNode node) {
        if (root == null) {
            return null;
        } else if (node.left == null) {
            return node;
        } else {
            return findMinVal(node.left);
        }
    }

    private TreeNode findMaxVal(TreeNode node) {
        if (root == null) {
            return null;
        } else if (node.right == null) {
            return node;
        } else {
            return findMaxVal(node.right);
        }
    }

    private TreeNode getTreeNodeParent(TreeNode node, TreeNode element) {
        if (node == null) {
            return null;
        }
        if ((node.left != null && node.left.data == element.data) || (node.right != null && node.right.data == element.data)) {
            return node;
        }
        TreeNode tempTreeNode;
        if ((tempTreeNode = getTreeNodeParent(node.left, element)) != null) {
            return tempTreeNode;
        } else {
            return getTreeNodeParent(node.right, element);
        }
    }

    private TreeNode removeTreeNode(int val) {
        TreeNode prevNodeInOrder; // 待删除节点在中序遍历中的前一个节点
        TreeNode parentOfDelNode; // 待删除节点的父节点
        TreeNode delNode; // 获取要删除的节点
        if ((delNode = Find(val)) == null) return null;

        if (delNode.left == null || delNode.right == null) { // 待删除节点只有一个子节点或者没有子节点
            parentOfDelNode = GetTreeNodeParent(delNode);  // 获取待删除节点的父节点
        } else { // 待删除节点有两个子节点 则对待删除节点和其中序遍历的前一个节点进行交换
            prevNodeInOrder = getPreNodeUseInOrder(root, val); // 获取待删除节点的中序遍历的前一个节点 preNodeInOrder
            parentOfDelNode = GetTreeNodeParent(prevNodeInOrder);  // 获取待删除节点在中序遍历中的前一个节点的父节点
            swapNodesData(delNode, prevNodeInOrder); // 交换待删除节点在中序遍历中的前一个节点和待删除节点的值
            delNode = prevNodeInOrder; // 设置中序遍历的前一个节点为待删除节点
        }

        // 删除的操作
        if (delNode == root) { // 待删除的节点为根节点
            if (delNode.left != null) { // 根节点有左子树
                root = root.left;
            } else { // 根节点有右子树
                root = root.right;
            }
        } else { // 待删除的节点为子节点
            TreeNode subTree = delNode.left != null ? delNode.left : delNode.right; // 待删除节点的子树
            if (parentOfDelNode.left == delNode) { // 待删除节点为其父节点的左子树
                parentOfDelNode.left = subTree;
            } else { // 待删除节点为其父节点的右子树
                parentOfDelNode.right = subTree;
            }
        }
        // delNode.left = delNode.right = null; // 设置待删除节点的关为null
        return delNode;
    }

    /**
     * 交换两个节点的数据
     *
     * @param delNode
     * @param preNode
     */
    private void swapNodesData(TreeNode delNode, TreeNode preNode) {
        int tempNodeData = preNode.data;
        preNode.data = delNode.data;
        delNode.data = tempNodeData;
    }

    /**
     * 获取待删除的节点的中序遍历的前一个节点
     *
     * @param root
     * @param val
     * @return
     */
    private TreeNode getPreNodeUseInOrder(TreeNode root, int val) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode prevNodeInOreder = null;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                if (node.data == val) {
                    break;
                } else {
                    prevNodeInOreder = node;
                }
                node = node.right;
            }
        }
        return prevNodeInOreder;
    }

    /**
     * 遍历操作
     *
     * @param node
     */
    private void visted(TreeNode node) {
        System.out.print(node.data + " ");
    }

    // --------------------------------------- 二叉树的其他算法 --------------------------------------------------

    /**
     * 获取树的最大宽度
     *
     * @return
     */
    public int GetTreeMaxWidth(Boolean isStack) {
        if (isStack)
            return getTreeMaxWidth(root);
        else
            return getTreeMaxWidth2(root);
    }

    /**
     * 获取树的节点的最大距离
     */
    private int maxLen = 0;

    public int FindMaxDistance() {
        findMaxDistance(root);
        return maxLen;
    }

    /**
     * 获取树的最大深度
     */
    public int FindMaxDepth() {
        return findMaxDepth(root);
    }


    /**
     * 获取指定节点到根节点的路径
     */
    public Stack<TreeNode> FindPathOfNodeToRoot(TreeNode node) {
        if (!hasNode(root, node)) return null;
        Stack<TreeNode> path = new Stack<TreeNode>();
        findPathOfNodeToRoot(root, node, path);
        return path;
    }

    /**
     * 找到两个节点最近的父节点
     *
     * @param first
     * @param second
     * @return
     */
    public TreeNode FindClosestParentNode(TreeNode first, TreeNode second) {
        return findClosestParentNode(first, second);
    }

    /**
     * 两个节点的最近公共父节点
     * @param p
     * @param q
     * @return
     */
    public TreeNode Ancestor(TreeNode p, TreeNode q) {
        return closestCommonAncestor(root, p, q);
    }

    public static TreeNode closestCommonAncestor(TreeNode node, TreeNode first, TreeNode second) {
        if (node == null || node.equals(first) || node.equals(second))
            return node;
        TreeNode left = closestCommonAncestor(node.left, first, second);
        TreeNode right = closestCommonAncestor(node.right, first, second);
        if ((left != null) && (right != null)) {
            return node;
        }
        return left != null ? left : right;
    }

    /**
     * 找到两个节点之间的路径
     *
     * @param first
     * @param second
     * @return
     */
    public Stack FindPathOfNodeToNode(TreeNode first, TreeNode second) {
        return findPathOfNodeToNode(first, second);
    }

    public Stack findPathOfNodeToNode(TreeNode first, TreeNode second) {
        Stack<TreeNode> firstPathStack = FindPathOfNodeToRoot(first);
        Stack<TreeNode> secondPathStack = FindPathOfNodeToRoot(second);
        Stack<TreeNode> firstPath = new Stack<TreeNode>();
        firstPath.push(first);
        Stack<TreeNode> secondPath = new Stack<TreeNode>();

        if (firstPathStack.size() == 0 || secondPathStack.size() == 0) {
            firstPath.push(second);
            return firstPath;
        } else {
            secondPath.push(second);
        }
        while (firstPathStack.size() > secondPathStack.size()) {
            firstPath.push(firstPathStack.pop());
        }
        while (firstPathStack.size() < secondPathStack.size()) {
            secondPath.push(secondPathStack.pop());
        }
        while (firstPathStack.size() > 0) {
            TreeNode one = firstPathStack.pop();
            TreeNode two = secondPathStack.pop();
            if (!one.equals(two)) {
                firstPath.push(one);
                secondPath.push(two);
            } else {
                firstPath.push(one);
                break;
            }
        }
        while (secondPath.size() > 0) {
            firstPath.push(secondPath.pop());
        }
        return firstPath;
    }

    private TreeNode findClosestParentNode(TreeNode first, TreeNode second) {
        Stack<TreeNode> firstPathStack = FindPathOfNodeToRoot(first);
        Stack<TreeNode> secondPathStack = FindPathOfNodeToRoot(second);

        while (firstPathStack.size() > secondPathStack.size()) firstPathStack.pop();
        while (firstPathStack.size() < secondPathStack.size()) secondPathStack.pop();
        for (TreeNode node; firstPathStack.size() > 0; ) {
            if ((node = firstPathStack.pop()).equals(secondPathStack.pop())) return node;
        }
        return null;
    }

    /**
     * 判断节点是否存在
     *
     * @param root
     * @param node
     * @return
     */
    private boolean hasNode(TreeNode root, TreeNode node) {
        if (root.equals(node)) return true;
        boolean has = false;
        if (root.left != null) has = hasNode(root.left, node);
        if (!has && root.right != null) has = hasNode(root.right, node);
        return has;
    }


    /**
     * 获取二叉树的最大宽度 - 双栈实现
     *
     * @param root
     * @return
     */
    private int getTreeMaxWidth(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stackA = new Stack<TreeNode>();
        Stack<TreeNode> stackB = new Stack<TreeNode>();
        int maxWidth = 1;
        stackA.push(root);
        while (stackA.size() > 0) {
            int currRowWidth = 0;
            while (stackA.size() > 0) {
                TreeNode pop = stackA.pop();
                if (pop.left != null) {
                    stackB.push(pop.left);
                    currRowWidth++;
                }
                if (pop.right != null) {
                    stackB.push(pop.right);
                    currRowWidth++;
                }
            }
            maxWidth = Math.max(currRowWidth, maxWidth);
            while (stackB.size() > 0) {
                stackA.push(stackB.pop());
            }
        }
        return maxWidth;
    }

    /**
     * 获取二叉树的最大宽度 - 队列实现
     *
     * @param root
     * @return
     */
    private int getTreeMaxWidth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int maxWidth = 1;
        while (true) {
            int size = queue.size();
            if (size == 0) break;
            for (; size > 0; size--) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            maxWidth = Math.max(maxWidth, queue.size());
        }
        return maxWidth;
    }

    private void findMaxDistance(TreeNode root) {
        if (root == null) return;
        if (root.left == null) root.leftMaxDistance = 0;
        if (root.right == null) root.rightMaxDistance = 0;
        if (root.left != null) findMaxDistance(root.left);
        if (root.right != null) findMaxDistance(root.right);
        if (root.left != null) {
            root.leftMaxDistance = Math.max(root.left.leftMaxDistance, root.left.rightMaxDistance) + 1;
        }
        if (root.right != null) {
            root.rightMaxDistance = Math.max(root.right.leftMaxDistance, root.right.rightMaxDistance) + 1;
        }
        if (root.leftMaxDistance + root.rightMaxDistance > maxLen) {
            maxLen = root.leftMaxDistance + root.rightMaxDistance;
        }
    }

    private int findMaxDepth(TreeNode node) {
        if (node == null)
            return 0;
        else {
            int left = findMaxDepth(node.left);
            int right = findMaxDepth(node.right);
            return 1 + Math.max(left, right);
        }
    }

    private boolean findPathOfNodeToRoot(TreeNode root, TreeNode node, Stack<TreeNode> path) {
        if (root.equals(node)) return true;
        path.push(root);
        boolean found = false;
        if (root.left != null) found = findPathOfNodeToRoot(root.left, node, path);
        if (!found && root.right != null) found = findPathOfNodeToRoot(root.right, node, path);
        if (!found) path.pop();
        return found;
    }
}
