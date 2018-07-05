package shelest.tree.operations;

import java.util.*;

public class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T value = null;

    public TreeNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    private TreeNode[] getChildren() {
        TreeNode[] arrayChildren = new TreeNode[2];
        arrayChildren[0] = this.getLeft();
        arrayChildren[1] = this.getRight();
        return arrayChildren;
    }


    public ArrayList<T> bypassDepthList(TreeNode<T> currentNode, ArrayList<T> currentList) {
        currentList.add(currentNode.getValue());
        //noinspection unchecked
        for (TreeNode<T> child : currentNode.getChildren()) {
            if (child != null) {
                bypassDepthList(child, currentList);
            }
        }
        return currentList;
    }
}

