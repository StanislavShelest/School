package shelest.tree.operations;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int elementsCount;
    private Comparator<T> comparator;

    public Tree(T value, Comparator<T> comparator) {
        this.root = new TreeNode<>(value);
        this.elementsCount = 1;
        this.comparator = comparator;
    }

    public TreeNode<T> getRoot() {
        return this.root;
    }

    private void setRoot(TreeNode<T> root) {
        this.root.setLeft(root.getLeft());
        this.root.setRight(root.getRight());
        this.root.setValue(root.getValue());
    }

    private void checkTreeNull() {
        if (this.getRoot() == null) {
            throw new NullPointerException("Используется пустое дерево!");
        }
    }

    private void checkValueNull(T value) {
        if (value == null) {
            throw new NullPointerException("Введено значение: null");
        }
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public void add(T value) {
        checkValueNull(value);
        if (this.root == null) {
            this.root = new TreeNode<>(value);
        } else {
            TreeNode<T> currentNode = root;
            boolean cycleRepeat = true;
            while (cycleRepeat) {
                if (comparator.compare(value, currentNode.getValue()) > 0) {
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
        }
        elementsCount++;
    }

    public boolean search(T value) {
        checkValueNull(value);
        checkTreeNull();
        TreeNode<T> currentNode = root;
        for (; ; ) {
            int comparatorSearch = comparator.compare(value, currentNode.getValue());
            if (comparatorSearch == 0) {
                return true;
            }
            if (comparatorSearch > 0) {
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
        checkValueNull(value);
        checkTreeNull();
        TreeNode<T> currentNode = root;
        TreeNode<T> parentCurrentNode = currentNode;
        for (; ; ) {
            int comparatorRemove = comparator.compare(value, currentNode.getValue());
            if (comparatorRemove == 0) {
                break;
            }
            if (comparatorRemove > 0) {
                parentCurrentNode = currentNode;
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            }
            if (comparatorRemove < 0) {
                parentCurrentNode = currentNode;
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (currentNode == parentCurrentNode) {
                root = null;
            }
            if (currentNode == parentCurrentNode.getLeft()) {
                parentCurrentNode.setLeft(null);
            }
            if (currentNode == parentCurrentNode.getRight()) {
                parentCurrentNode.setRight(null);
            }
            elementsCount--;
            return true;
        }

        if (currentNode.getLeft() == null) {
            if (currentNode == parentCurrentNode) {
                this.setRoot(currentNode.getRight());
            }
            if (currentNode == parentCurrentNode.getLeft()) {
                parentCurrentNode.setLeft(currentNode.getRight());
            }
            if (currentNode == parentCurrentNode.getRight()) {
                parentCurrentNode.setRight(currentNode.getRight());
            }
            elementsCount--;
            return true;
        }

        if (currentNode.getRight() == null) {
            if (currentNode == parentCurrentNode) {
                this.setRoot(currentNode.getLeft());
            }
            if (currentNode == parentCurrentNode.getLeft()) {
                parentCurrentNode.setLeft(currentNode.getLeft());
            }
            if (currentNode == parentCurrentNode.getRight()) {
                parentCurrentNode.setRight(currentNode.getLeft());
            }
            elementsCount--;
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
        elementsCount--;
        return true;
    }

    public void bypassWidth(Consumer<T> print) {
        checkTreeNull();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0) {
            if (queue.element().getLeft() != null) {
                queue.add(queue.element().getLeft());
            }
            if (queue.element().getRight() != null) {
                queue.add(queue.element().getRight());
            }
            print.accept(queue.remove().getValue());
        }
    }

    public void bypassDepthRecursion(TreeNode<T> currentNode, Consumer<T> print) {
        checkTreeNull();
        print.accept(currentNode.getValue());
        if (currentNode.getLeft() != null) {
            bypassDepthRecursion(currentNode.getLeft(), print);
        }
        if (currentNode.getRight() != null) {
            bypassDepthRecursion(currentNode.getRight(), print);
        }
    }

    public void bypassDepth(Consumer<T> print) {
        checkTreeNull();
        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);
        while (stack.size() != 0) {
            TreeNode<T> currentNode = stack.removeLast();
            print.accept(currentNode.getValue());
            if (currentNode.getRight() != null) {
                stack.addLast(currentNode.getRight());
            }
            if (currentNode.getLeft() != null) {
                stack.addLast(currentNode.getLeft());
            }
        }
    }
}
