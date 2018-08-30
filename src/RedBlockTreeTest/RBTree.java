package RedBlockTreeTest;

/**
 * Describe：红黑树
 * Author：sunqiushun
 * Date：2018-08-10 14:33:43
 */
public class RBTree<T extends Comparable<T>> {

    private RBTNode<T> mRoot;    // 根结点
    public static final boolean RED = false;
    public static final boolean BLACK = true;

    public RBTree() {
        mRoot = null;
    }

    /**
     * 新建结点(key)，并将其插入到红黑树中
     *
     * @param key
     */
    public void insert(T key) {
        RBTNode<T> node = new RBTNode<T>(key, BLACK, null, null, null);
        if (node != null) insert(node);       // 如果新建结点失败，则返回。
    }

    /**
     * 删除结点(z)，并返回被删除的结点
     *
     * @param key
     */
    public void remove(T key) {
        RBTNode<T> node = search(mRoot, key);
        if (node != null) remove(node);
    }

    /**
     * 查找二叉树中是否存在节点
     *
     * @param key
     * @return
     */
    public RBTNode<T> search(T key) {
        return search(mRoot, key);
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node != null ? node.parent : null;
    }

    private boolean colorOf(RBTNode<T> node) {
        return node != null ? node.color : BLACK;
    }

    private boolean isRed(RBTNode<T> node) {
        return ((node != null) && (node.color == RED)) ? true : false;
    }

    private boolean isBlack(RBTNode<T> node) {
        return !isRed(node);
    }

    private void setBlack(RBTNode<T> node) {
        if (node != null) node.color = BLACK;
    }

    private void setRed(RBTNode<T> node) {
        if (node != null) node.color = RED;
    }

    private void setParent(RBTNode<T> node, RBTNode<T> parent) {
        if (node != null) node.parent = parent;
    }

    private void setColor(RBTNode<T> node, boolean color) {
        if (node != null) node.color = color;
    }

    /*
     * 前序遍历"红黑树"
     */
    private void preOrder(RBTNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /*
     * 中序遍历"红黑树"
     */
    private void inOrder(RBTNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    /*
     * 后序遍历"红黑树"
     */
    private void postOrder(RBTNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    /*
     * (递归实现)查找"红黑树x"中键值为key的节点
     */
    private RBTNode<T> search(RBTNode<T> x, T key) {
        if (x == null)
            return x;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return search(x.left, key);
        else if (cmp > 0)
            return search(x.right, key);
        else
            return x;
    }

    private void insert(RBTNode<T> node) {
        int cmp;
        RBTNode<T> tempNode = null;
        RBTNode<T> curNode = this.mRoot;

        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (curNode != null) { // 找到要插入节点的父节点
            tempNode = curNode;
            cmp = node.key.compareTo(curNode.key);
            if (cmp < 0) curNode = curNode.left;
            else curNode = curNode.right;
        }
        node.parent = tempNode; // 设置要插入节点的父节点
        if (tempNode == null) { // 树为空 则设置要插入的节点为根节点
            this.mRoot = node;
        } else { // 插入节点
            cmp = node.key.compareTo(tempNode.key);
            if (cmp < 0) tempNode.left = node;
            else tempNode.right = node;
        }

        node.color = RED;  // 2. 设置节点的颜色为红色
        insertFixUp(node);  // 3. 将它重新修正为一颗二叉查找树
    }

    /*
     * 红黑树插入修正函数
     */
    private void insertFixUp(RBTNode<T> node) {
        RBTNode<T> parent, uncle, gparent;
        while (((parent = parentOf(node)) != null) && isRed(parent)) { // 若父节点存在并且父节点的颜色是红色
            gparent = parentOf(parent); // 获取祖父节点
            uncle = (parent == gparent.left) ? gparent.right : gparent.left; // 获取uncle节点
            if ((uncle != null) && isRed(uncle)) {   // Case 1条件：uncle不为空且为红色
                setBlack(uncle); // 设置uncle节点颜色为黑
                setBlack(parent); // 设置parent节点颜色为黑
                setRed(gparent); // 设置gprent节点颜色为红
                node = gparent; // 把node节点执行gparent节点 继续递归修改
            } else {   // Case 2条件：uncle为空即为黑色
                if (parent == gparent.left) {  //若父节点是祖父节点的左孩子
                    // Case 2-1-1 条件：叔叔是黑色 且当前节点是右孩子
                    if (node == parent.right) leftRotate(node = parentOf(node));
                    // Case 2-1-2 条件：叔叔是黑色 且当前节点是左孩子
                    rightRotate(gparent); // 对gparent进行右旋转
                    setBlack(parentOf(node)); // 设置节点的父节点颜色为黑
                    setRed(gparent); // 设置节点的祖父节点颜色为红
                } else {  //若父节点是祖父节点的右孩子
                    // Case 2-2-1 条件：叔叔是黑色 且当前节点是左孩子
                    if (node == parent.left) rightRotate(node = parentOf(node));
                    // Case 2-2-2 条件：叔叔是黑色 且当前节点是右孩子。
                    leftRotate(gparent); // 对祖父节点进行左旋转
                    setBlack(parentOf(node)); // 设置父节点颜色为黑
                    setRed(gparent); // 设置祖父节点颜色为红
                }
            }
        }
        // 将根节点设为黑色
        setBlack(this.mRoot);
    }

    /**
     * 删除结点(node)，并返回被删除的结点
     * 后继节点: 即中序遍历时被删除节点的后一位 也称为取代节点 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
     *
     * @param node
     */
    private void remove(RBTNode<T> node) {
        RBTNode<T> replaceRight, replaceParent;
        boolean replaceColor; // replace 的颜色
        // 一、被删除节点的左右孩子都不为空
        if ((node.left != null) && (node.right != null)) {
            // 1. 获取后继结点replace及其属性
            RBTNode<T> replace = findNextNode(node); // 获取后继节点 即取代节点
            replaceRight = replace.right;   // 取代节点的右孩子（也是需要调整的节点）
            replaceParent = parentOf(replace); // 取代节点的父节点
            replaceColor = colorOf(replace);  // 取代节点的颜色

            // 2. 处理node节点的父节点和replace节点之间的指向关系
            if (parentOf(node) == null) { // node节点是根节点 更新根节点
                this.mRoot = replace;
            } else {  // node节点不是根节点
                if (parentOf(node).left == node) parentOf(node).left = replace;
                else parentOf(node).right = replace;
            }
            replace.parent = node.parent; // 设置replace的父节点

            // 3. 处理replace节点和node右孩子节点之间的指向关系
            if (node != replaceParent) { // 被删除节点不是它的后继节点的父节点
                // replaceRight不为空 replaceRight节点parent指向replace的parent
                if (replaceRight != null) replaceRight.parent = replaceParent;
                // replace的parent指向replace的右孩子节点
                replaceParent.left = replaceRight;
                // 处理replace的右节点和node的右节点指向关系
                replace.right = node.right;
                node.right.parent = replace;
            } else {
                replaceParent = replace; // 此时需要replace和它的右孩子节点进行旋转校正
            }

            // 4. 处理replace节点和node的左孩子之间的关系
            replace.left = node.left;
            node.left.parent = replace;

            // 5. 处理replace节点和node节点之间的颜色关系
            replace.color = node.color; // 设置replace的颜色为node节点的颜色

            // 6. replace节点顶替了删除的节点 若replace节点的颜色为黑色  则红黑树的特性（5）可能不满足 需要进行旋转
            if (replaceColor == BLACK) removeFixUp(replaceRight, replaceParent);
            // node = null;
        } else { // 二、 node只有一个子树或者没有节点
            // 1. 设置child节点的父节点指向
            RBTNode child = node.left != null ? node.left : node.right;
            if (child != null) child.parent = node.parent; // 有一个子节点
            // 2. 设置设置node的父节点的指向
            if (node.parent == null) {  // node节点是根节点
                this.mRoot = child;
            } else {
                if (node.parent.left == node) node.parent.left = child;
                else node.parent.right = child;
            }
            // 3. 删除的节点为黑色，则红黑树的特性（5）可能不满足 需要进行进行旋转
            if (node.color == BLACK) removeFixUp(child, node.parent);
            //  node = null;
        }

    }

    /**
     * 红黑树删除修正函数
     *
     * @param node：删除节点的孩子节点  或者 后继结点的右孩子节点（删除节点有两个孩子节点）
     * @param parent：删除节点的父节点 或者 后继结点的父节点（删除节点有两个孩子节点）
     */
    private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
        while (isBlack(node) && (node != this.mRoot)) { // node节点颜色为黑色 且不为root节点
            RBTNode<T> brother = (node == parent.left ? parent.right : parent.left); // 获取node 的兄弟节点
            if (node == parent.left) { // 一、 node为父节点的左孩子节点
                if (isRed(brother)) {  // Case 1: node的兄弟brother是红色的  处理之后满足Case2、Case3或者Case4
                    setBlack(brother);      // 设置brother颜色为黑色
                    setRed(parent);         // 设置parent颜色为红色
                    leftRotate(parent);     // 对parent进行左旋
                    brother = parent.right; // 左旋后 重新设置node的兄弟节点
                }
                if (isBlack(brother.left) && isBlack(brother.right)) { // Case 2: brother的两个孩子节点都是黑色的（此时brother已为黑色） 向上传递node
                    setRed(brother);         // 设置brother为红色
                    node = parent;           // 设置node的parent节点为新的node节点
                    parent = parentOf(node); // 重新获取parent节点
                } else {
                    if (isBlack(brother.right)) { // Case 3: brother的右孩子为黑色（则左孩子一定为红色，brother已为黑色）
                        setBlack(brother.left);   // 设置brother的左孩子节点为黑色
                        setRed(brother);          // 设置brother节点为红色
                        rightRotate(brother);     // 对brother进行右旋
                        brother = parent.right;   // 右旋后 重新设置node的兄弟节点
                    }
                    // Case 4: brother的右孩子是红色（左孩子可红、可黑，brother已为黑色） 最终Case1、Case2和Case3 都转化为Case4来处理
                    setColor(brother, colorOf(parent));  // 设置brother的颜色为parent的颜色 brother和parent交换颜色
                    setBlack(parent);                    // 设置parent的颜色为brother的颜色 brother和parent交换颜色
                    setBlack(brother.right);             // 设置brother的右孩子节点为黑色
                    leftRotate(parent);                  // 对parent节点进行左旋
                    node = this.mRoot;                   // 设置node为根节点  结束循环
                }
            } else {  // 二、 node为父节点的右孩子节点
                if (isRed(brother)) {  // Case 1: node的brother是红色的
                    setBlack(brother); // 设置brother颜色为黑色
                    setRed(parent); // 设置parent颜色为红色
                    rightRotate(parent); // 对parent进行右旋
                    brother = parent.left; // 旋转后 重置node的brother节点
                }
                if (isBlack(brother.left) && isBlack(brother.right)) { // Case 2: brother的两个孩子都是黑色 （brother已为黑色）
                    setRed(brother); // 设置brother 为红色
                    node = parent;   // 将parent节点置为node节点
                    parent = parentOf(node); // 重新获取parent节点
                } else {
                    if (isBlack(brother.left)) { // Case 3: brother的左孩子为黑色（则右孩子一定为红色，brother已为黑色）
                        setBlack(brother.right); // 设置brother的右孩子为黑色
                        setRed(brother);  // 设置brother为红色
                        leftRotate(brother); // 对brother进行左旋
                        brother = parent.left; // 重新获取brother节点
                    }
                    // Case 4: brother的左孩子是红色（右孩子可红、可黑，brother已为黑色）
                    setColor(brother, colorOf(parent)); // 设置brother的颜色为parent的颜色 brother和parent交换颜色
                    setBlack(parent); // 设置parent的颜色为brother的颜色 brother和parent交换颜色 brother节点的颜色为黑色
                    setBlack(brother.left); // 设置brother的左孩子节点为黑色
                    rightRotate(parent); // 对parent进行右旋转
                    node = this.mRoot;   // 设置node为根节点  结束循环
                }
            }
        }
        if (node != null) setBlack(node); // node不为空 设置node的颜色为黑色
    }


    /**
     * 获取node节点的后继节点(中序遍历)
     *
     * @param node
     * @return
     */
    private RBTNode findNextNode(RBTNode node) {
        if (node == null) return null;
        node = node.right;
        while (node != null && node.left != null) node = node.left;
        return node;
    }
    private void leftRotate(RBTNode<T> node) {
        RBTNode<T> right = node.right; // 获取node的右孩子right

        // 1. 处理right的左孩子
        node.right = right.left;  // 将right的左孩子节点设为node的右孩子
        if (right.left != null) right.left.parent = node; // 如果right的左孩子非空 将node设为right的左孩子的父节点

        // 2. 处理right节点
        right.parent = node.parent;  // 将right的父节点设为node的父节点
        if (node.parent == null) {
            this.mRoot = right; // 如果node的父节点是空节点 则将right设为根节点
        } else {
            if (node.parent.left == node) node.parent.left = right;// 如果node是它父节点的左孩子 则将right设为node的父节点的左孩子
            else node.parent.right = right;// 如果node是它父节点的左孩子 则将right设为node父节点的左孩子
        }

        // 3. 处理node节点
        right.left = node;  // 将node设为right的左孩子
        node.parent = right;  // 将node的父节点设为right
    }

    private void rightRotate(RBTNode<T> node) {
        RBTNode<T> left = node.left; // 设置x是当前节点的左孩子。

        // 1. 处理left的右孩子
        node.left = left.right; // 将left的右孩子设为node的左孩子
        if (left.right != null) left.right.parent = node; // 如果left的右孩子不为空的话 将node设为left右孩子的父节点

        // 2. 处理left节点
        left.parent = node.parent;  // 将left的父节点设置为node的父节点
        if (node.parent == null) { // 如果node的父节点是空节点 则将left设为根节点
            this.mRoot = left;
        } else {
            if (node == node.parent.right) node.parent.right = left;// 如果node是它父节点的右孩子 则将left设为node的父节点的右孩子
            else node.parent.left = left; // 如果node是它父节点的左孩子 则将left设为node的父节点的左孩子
        }

        // 3. 处理node节点
        left.right = node;  // 将node节点设为left的右孩子
        node.parent = left;  // 将node的父节点设为left
    }

    /*
     * 打印"红黑树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(RBTNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (direction == 0)    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRed(tree) ? "R" : "B", key, direction == 1 ? "right" : "left");
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }

}
