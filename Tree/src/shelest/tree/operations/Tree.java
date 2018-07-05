package shelest.tree.operations;

import java.util.*;

public class Tree<T> {
    private TreeNode<T> root;
    private int countElement;

    public Tree(T value) {
        this.root = new TreeNode<>(value);
        this.countElement = 1;
    }

    public int getCountElement() {
        return countElement;
    }

    private boolean isFirstValueLower(T value1, T value2) {
        //noinspection unchecked
        T[] arrayValue = (T[]) new Object[2];
        arrayValue[0] = value1;
        arrayValue[1] = value2;
        Arrays.sort(arrayValue);
        return value1.equals(arrayValue[0]);
    }

    public void add(T value) {
        if (root.getValue() == null) {
            root.setValue(value);
        } else {
            TreeNode<T> currentNode = root;
            boolean cycleRepeat = true;
            while (cycleRepeat) {
                if (isFirstValueLower(value, currentNode.getValue())) {
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
    }

    public boolean search(T value) {
        TreeNode<T> currentNode = root;
        for (; ; ) {
            if (Objects.equals(value, currentNode.getValue())) {
                return true;
            }
            if (isFirstValueLower(value, currentNode.getValue())) {
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
        TreeNode<T> currentNode = root;
        TreeNode<T> parentCurrentNode = currentNode;
        for (; ; ) {
            if (Objects.equals(value, currentNode.getValue())) {
                break;
            }
            parentCurrentNode = currentNode;
            if (isFirstValueLower(value, currentNode.getValue())) {
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

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (isFirstValueLower(currentNode.getValue(), parentCurrentNode.getValue())) {
                parentCurrentNode.setLeft(null);
            } else {
                if (isFirstValueLower(parentCurrentNode.getValue(), currentNode.getValue())) {
                    parentCurrentNode.setRight(null);
                } else {
                    parentCurrentNode = null;
                }
            }
            countElement--;
            return true;
        }

        if (currentNode.getLeft() == null) {
            if (isFirstValueLower(currentNode.getValue(), parentCurrentNode.getValue())) {
                parentCurrentNode.setLeft(currentNode.getRight());
            } else {
                if (isFirstValueLower(parentCurrentNode.getValue(), currentNode.getValue())) {
                    parentCurrentNode.setRight(currentNode.getRight());
                } else {
                    parentCurrentNode = currentNode.getRight();
                }
            }
            countElement--;
            return true;
        }

        if (currentNode.getRight() == null) {
            if (isFirstValueLower(currentNode.getValue(), parentCurrentNode.getValue())) {
                parentCurrentNode.setLeft(currentNode.getLeft());
            } else {
                if (isFirstValueLower(parentCurrentNode.getValue(), currentNode.getValue())) {
                    parentCurrentNode.setRight(currentNode.getLeft());
                } else {
                    parentCurrentNode = currentNode.getLeft();
                }
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
        queue.add(root);
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

    public ArrayList<T> bypassDepthList() {
        ArrayList<T> list = new ArrayList<>();
        list = root.bypassDepthList(root, list);
        return list;
    }

    public T[] bypassDepthArray() {
        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);
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
