package shelest.tree.operations;

import java.util.*;

public class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T value = null;
    private int countElement = 1;
    private ArrayList<T> list = new ArrayList<>();

    public TreeNode(T value) {
        this.value = value;
    }

    private T getValue() {
        return this.value;
    }

    private TreeNode<T> getLeft() {
        return left;
    }

    private TreeNode<T> getRight() {
        return right;
    }

    private void setValue(T value) {
        this.value = value;
    }

    private void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    private void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public int getCountElement() {
        return countElement;
    }

    private boolean isLowerEnteredValue(T value1, T value2) {
        //noinspection unchecked
        T[] arrayValue = (T[]) new Object[2];
        arrayValue[0] = value1;
        arrayValue[1] = value2;
        Arrays.sort(arrayValue);
        return value1.equals(arrayValue[0]);
    }

    public void add(T value) {
        TreeNode<T> currentNode = this;
        boolean cycleRepeat = true;
        while (cycleRepeat) {
            if (isLowerEnteredValue(value, currentNode.getValue())) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(value));
                    cycleRepeat = false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(value));
                    cycleRepeat = false;
                }
            }
        }
        countElement++;
    }

    public boolean search(T value) {
        TreeNode<T> currentNode = this;
        for (; ; ) {
            if (Objects.equals(value, currentNode.getValue())) {
                return true;
            }
            if (isLowerEnteredValue(value, currentNode.getValue())) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    public boolean remove(T value) {
        TreeNode<T> currentNode = this;
        TreeNode<T> parentCurrentNode = currentNode;
        String sideSubTree = null;
        for (; ; ) {
            if (Objects.equals(value, currentNode.getValue())) {
                if (sideSubTree == null) {
                    sideSubTree = "root";
                }
                break;
            }
            parentCurrentNode = currentNode;
            if (isLowerEnteredValue(value, currentNode.value)) {
                sideSubTree = "left";
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                sideSubTree = "right";
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            switch (sideSubTree) {
                case ("root"):
                    parentCurrentNode = null;
                    break;
                case ("left"):
                    parentCurrentNode.setLeft(null);
                    break;
                case ("right"):
                    parentCurrentNode.setRight(null);
                    break;
            }
            countElement--;
            return true;
        }

        if (currentNode.getLeft() == null) {
            switch (sideSubTree) {
                case ("root"):
                    parentCurrentNode = currentNode.getRight();
                    break;
                case ("left"):
                    parentCurrentNode.setLeft(currentNode.getRight());
                    break;
                case ("right"):
                    parentCurrentNode.setRight(currentNode.getRight());
                    break;
            }
            countElement--;
            return true;
        }

        if (currentNode.getRight() == null) {
            switch (sideSubTree) {
                case ("root"):
                    parentCurrentNode = currentNode.getLeft();
                    break;
                case ("left"):
                    parentCurrentNode.setLeft(currentNode.getLeft());
                    break;
                case ("right"):
                    parentCurrentNode.setRight(currentNode.getLeft());
                    break;
            }
            countElement--;
            return true;
        }

        TreeNode<T> parentMinRightCurrentNode = currentNode;
        TreeNode<T> minRightCurrentNode = currentNode.getRight();
        boolean isLeftSide = false;
        while (minRightCurrentNode.getLeft() != null) {
            isLeftSide = true;
            parentMinRightCurrentNode = minRightCurrentNode;
            minRightCurrentNode = minRightCurrentNode.getLeft();
        }
        if (minRightCurrentNode.getRight() == null) {
            if (isLeftSide) {
                parentMinRightCurrentNode.setLeft(null);
            } else {
                parentMinRightCurrentNode.setRight(null);
            }
        } else {
            parentMinRightCurrentNode.setLeft(minRightCurrentNode.getRight());
        }
        currentNode.setValue(minRightCurrentNode.getValue());
        countElement--;
        return true;
    }

    public T[] bypassWidthArray() {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        //noinspection unchecked
        T[] array = (T[]) new Object[countElement];
        queue.add(this);
        for (int i = 0; queue.size() != 0; i++) {
            if (queue.element().getLeft() != null) {
                queue.add(queue.element().getLeft());
            }
            if (queue.element().getRight() != null) {
                queue.add(queue.element().getRight());
            }
            array[i] = queue.remove().getValue();
        }
        return array;
    }

    private TreeNode[] getChildren() {
        TreeNode[] arrayChildren = new TreeNode[2];
        arrayChildren[0] = this.getLeft();
        arrayChildren[1] = this.getRight();
        return arrayChildren;
    }

    public ArrayList<T> bypassDepthList(TreeNode<T> currentNode) {
        list.add(currentNode.getValue());
        //noinspection unchecked
        for (TreeNode<T> child : currentNode.getChildren()) {
            if (child != null) {
                bypassDepthList(child);

            }
        }
        return list;
    }

    public T[] bypassDepthArray() {
        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(this);
        //noinspection unchecked
        T[] array = (T[]) new Object[countElement];
        for (int i = 0; stack.size() != 0; i++) {
            TreeNode<T> currentNode = stack.removeLast();
            array[i] = currentNode.getValue();
            if (currentNode.getRight() != null) {
                stack.addLast(currentNode.getRight());
            }
            if (currentNode.getLeft() != null) {
                stack.addLast(currentNode.getLeft());
            }
        }
        return array;
    }
}

