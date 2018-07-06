package shelest.tree.operations;

class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T value = null;

    TreeNode(T value) {
        this.value = value;
    }

    T getValue() {
        return this.value;
    }

    TreeNode<T> getLeft() {
        return left;
    }

    TreeNode<T> getRight() {
        return right;
    }

    void setValue(T value) {
        this.value = value;
    }

    void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    void setRight(TreeNode<T> right) {
        this.right = right;
    }
}

