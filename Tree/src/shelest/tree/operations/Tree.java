package shelest.tree.operations;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int elementsCount;

    public Tree(T value) {
        this.root = new TreeNode<>(value);
        this.elementsCount = 1;

    }

    public TreeNode<T> getRoot() {
        return this.root;
    }

    private void setRoot(TreeNode<T> root){
        this.root.setLeft(root.getLeft());
        this.root.setRight(root.getRight());
        this.root.setValue(root.getValue());
    }

    private void checkTreeNull (){
        if (this.getRoot() == null){
            throw new NullPointerException("Используется пустое дерево!");
        }
    }

    public int getElementsCount() {
        return elementsCount;
    }

    private Comparator<T> comparator = (Comparator<T>) (value1, value2) -> {
        if (Objects.equals(value1, value2)) {
            return 0;
        }
        //noinspection unchecked
        T[] arrayValue = (T[]) new Object[2];
        arrayValue[0] = value1;
        arrayValue[1] = value2;
        Arrays.sort(arrayValue);
        if (Objects.equals(value1, arrayValue[0])) {
            return 1;
        } else {
            return -1;
        }
    };

    public void add(T value) {
        if (this.root == null) {
            this.root = new TreeNode<>(value);
        } else {
            TreeNode<T> currentNode = root;
            boolean cycleRepeat = true;
            while (cycleRepeat) {
                if (comparator.compare(value, currentNode.getValue()) == 1) {
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
        checkTreeNull();
        TreeNode<T> currentNode = root;
        for (; ; ) {
            if (Objects.equals(value, currentNode.getValue())) {
                return true;
            }
            if (comparator.compare(value, currentNode.getValue()) == 1) {
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
        checkTreeNull();
        TreeNode<T> currentNode = root;
        TreeNode<T> parentCurrentNode = currentNode;
        for (; ; ) {
            if (comparator.compare(value, currentNode.getValue()) == 0) {
                break;
            }
            if (comparator.compare(value, currentNode.getValue()) == 1) {
                parentCurrentNode = currentNode;
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            }
            if (comparator.compare(value, currentNode.getValue()) == -1) {
                parentCurrentNode = currentNode;
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (comparator.compare(currentNode.getValue(), parentCurrentNode.getValue()) == 0) {
                root = null;
            }
            if (comparator.compare(currentNode.getValue(), parentCurrentNode.getValue()) == 1) {
                parentCurrentNode.setLeft(null);
            }
            if (comparator.compare(currentNode.getValue(), parentCurrentNode.getValue()) == -1) {
                parentCurrentNode.setRight(null);
            }
            elementsCount--;
            return true;
        }

        if (currentNode.getLeft() == null) {
            if (comparator.compare(currentNode.getValue(), parentCurrentNode.getValue()) == 0) {
                this.setRoot(currentNode.getRight());
            }
            if (comparator.compare(currentNode.getValue(), parentCurrentNode.getValue()) == 1) {
                parentCurrentNode.setLeft(currentNode.getRight());
            }
            if (comparator.compare(currentNode.getValue(), parentCurrentNode.getValue()) == -1) {
                parentCurrentNode.setRight(currentNode.getRight());
            }
            elementsCount--;
            return true;
        }

        if (currentNode.getRight() == null) {
            if (comparator.compare(currentNode.getValue(), parentCurrentNode.getValue()) == 0) {
                this.setRoot(currentNode.getLeft());
            }
            if (comparator.compare(currentNode.getValue(), parentCurrentNode.getValue()) == 1) {
                parentCurrentNode.setLeft(currentNode.getLeft());
            }
            if (comparator.compare(currentNode.getValue(), parentCurrentNode.getValue()) == -1) {
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

    public void bypassWidth() {
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
            Consumer<T> print = value -> System.out.print(value + " ");
            print.accept(queue.remove().getValue());
        }
    }

    public void bypassDepthRecursion(TreeNode<T> currentNode) {
        checkTreeNull();
        Consumer<T> print = value -> System.out.print(value + " ");
        print.accept(currentNode.getValue());
        if (currentNode.getLeft() != null) {
            bypassDepthRecursion(currentNode.getLeft());
        }
        if (currentNode.getRight() != null) {
            bypassDepthRecursion(currentNode.getRight());
        }
    }

    public void bypassDepth() {
        checkTreeNull();
        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);
        while (stack.size() != 0) {
            TreeNode<T> currentNode = stack.removeLast();
            Consumer<T> print = value -> System.out.print(value + " ");
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
