package shelest.linkedlist.operation;

import java.util.*;

public class LinkedList<T> implements List<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;
    private int modCount = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T dataNode : this) {
            if (Objects.equals(dataNode, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }


    @Override
    public Object[] toArray() {
        int i = 0;
        Object[] array = new Object[this.size()];
        for (T dataNode : this) {
            array[i] = dataNode;
            i++;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        int i = 0;
        //noinspection unchecked
        T1[] array = (T1[]) new Object[this.size()];
        for (T dataNode : this) {
            //noinspection unchecked
            array[i] = (T1) dataNode;
            i++;
        }
        return array;
    }

    @Override
    public boolean add(T t) {
        if (this.size == 0) {
            this.head = new LinkedListNode<>(t);
            this.tail = head;
            size++;
            modCount++;
            return true;
        }
        if (this.size == 1) {
            LinkedListNode<T> addedNode = new LinkedListNode<>(t);
            this.head.setNext(addedNode);
            this.tail = addedNode;
            size++;
            modCount++;
            return true;
        }
        LinkedListNode<T> addedNode = new LinkedListNode<>(t, tail, null);
        this.tail.setNext(addedNode);
        this.tail = addedNode;
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (Objects.equals(head.getData(), o)) {
            head = head.getNext();
            head.setPrev(null);
            size--;
            modCount++;
            return true;
        }

        if (Objects.equals(tail.getData(), o)) {
            tail = tail.getPrev();
            tail.setNext(null);
            size--;
            modCount++;
            return true;
        }

        for (LinkedListNode<T> currentNode = head.getNext(); currentNode.getNext() != null; currentNode = currentNode.getNext()) {
            if (Objects.equals(currentNode.getData(), o)) {
                LinkedListNode<T> prevNode = currentNode.getPrev();
                LinkedListNode<T> nextNode = currentNode.getNext();
                prevNode.setNext(nextNode);
                nextNode.setPrev(prevNode);
                size--;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elementCollection : c) {
            if (!this.contains(elementCollection)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T elementCollection : c) {
            if (!this.add(elementCollection)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean isFirstElement = true;
        LinkedListNode<T> firstElementCollection = new LinkedListNode<>(null);
        LinkedListNode<T> currentElementCollection = new LinkedListNode<>(null);
        for (T elementCollection : c) {
            if (isFirstElement) {
                firstElementCollection = new LinkedListNode<>(elementCollection);
                currentElementCollection = firstElementCollection;
                isFirstElement = false;
            } else {
                LinkedListNode<T> addedElementCollection = new LinkedListNode<>(elementCollection);
                currentElementCollection.setNext(addedElementCollection);
                addedElementCollection.setPrev(currentElementCollection);
                currentElementCollection = addedElementCollection;
            }
        }
        if (index == 0) {
            currentElementCollection.setNext(head);
            head.setPrev(currentElementCollection);
            head = firstElementCollection;
        } else {
            LinkedListNode<T> currentNode = head;
            for (int i = 1; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            LinkedListNode<T> nextNode = currentNode.getNext();
            currentNode.setNext(firstElementCollection);
            firstElementCollection.setPrev(currentNode);
            currentElementCollection.setNext(nextNode);
            nextNode.setPrev(currentElementCollection);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoveAll = false;
        for (Object elementCollection : c) {
            if (this.remove(elementCollection)) {
                size--;
                isRemoveAll = true;
            }
        }
        return isRemoveAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRetainAll = false;
        int primarySize = size();
        LinkedListNode<T> currentNode = head;
        for (int i = 1; i <= primarySize; i++) {
            boolean isEqual = false;
            for (Object elementCollection : c) {
                isEqual = Objects.equals(currentNode.getData(), elementCollection);
            }
            if (!isEqual) {
                if (size() == 1) {
                    head = null;
                    size--;
                    return isRetainAll;
                }
                LinkedListNode<T> prevNode = currentNode.getPrev();
                LinkedListNode<T> nextNode = currentNode.getNext();
                if (prevNode == null) {
                    currentNode.getNext().setPrev(null);
                    head = currentNode.getNext();
                } else if (nextNode == null) {
                    currentNode.getPrev().setNext(null);
                    tail = currentNode.getPrev();
                } else {
                    prevNode.setNext(nextNode);
                    nextNode.setPrev(prevNode);
                }
                size--;
                isRetainAll = true;
            }
            currentNode = currentNode.getNext();
        }
        return isRetainAll;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        LinkedListNode<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    @Override
    public T set(int index, T element) {
        LinkedListNode<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        T oldData = currentNode.getData();
        currentNode.setData(element);
        return oldData;
    }

    @Override
    public void add(int index, T element) {
        if (index == 0) {
            LinkedListNode<T> addedNode = new LinkedListNode<>(element);
            addedNode.setNext(head);
            head.setPrev(addedNode);
            head = addedNode;
        } else {
            LinkedListNode<T> currentNode = head;
            for (int i = 1; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            LinkedListNode<T> nextNode = currentNode.getNext();
            LinkedListNode<T> addedNode = new LinkedListNode<>(element);
            currentNode.setNext(addedNode);
            addedNode.setPrev(currentNode);
            addedNode.setNext(nextNode);
            nextNode.setPrev(addedNode);
        }
        size++;
    }

    @Override
    public T remove(int index) {
        LinkedListNode<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        LinkedListNode<T> prevNode = currentNode.getPrev();
        LinkedListNode<T> nextNode = currentNode.getNext();
        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
        return currentNode.getData();
    }

    @Override
    public int indexOf(Object o) {
        LinkedListNode<T> currentNode = head;
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(currentNode.getData(), o)) {
                return i;
            }
            currentNode = currentNode.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        LinkedListNode<T> currentNode = tail;
        for (int i = size() - 1; i >= 0; i--) {
            if (Objects.equals(currentNode.getData(), o)) {
                return i;
            }
            currentNode = currentNode.getPrev();
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new LinkedListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new LinkedListIterator();// исправить
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private class LinkedListIterator implements ListIterator<T> {
        LinkedListNode<T> currentNode = new LinkedListNode<>(null, null, head);
        int currentIndex = -1;
        int modCountPrimary;

        LinkedListIterator() {
            this.modCountPrimary = modCount;
        }

        @Override
        public boolean hasNext() {
            return currentNode.getNext() != null;
        }

        @Override
        public T next() {
            currentNode = currentNode.getNext();
            currentIndex++;
            return currentNode.getData();
        }

        @Override
        public boolean hasPrevious() {
            return currentNode.getPrev() != null;
        }

        @Override
        public T previous() {
            currentNode = currentNode.getPrev();
            currentIndex--;
            return currentNode.getData();
        }

        @Override
        public int nextIndex() {
            return currentIndex + 1;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            LinkedListNode<T> prevNode = currentNode.getPrev();
            LinkedListNode<T> nextNode = currentNode.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }

        @Override
        public void set(T t) {
            currentNode.setData(t);
        }

        @Override
        public void add(T t) {

        }
    }

    private class LinkedIterator implements Iterator<T> {
        private LinkedListNode<T> currentNode = new LinkedListNode<>(null, null, head);
        private int modCountPrimary;

        private LinkedIterator() {
            this.modCountPrimary = modCount;
        }

        @Override
        public boolean hasNext() {
            return currentNode.getNext() != null;
        }

        @Override
        public T next() {
            if (Objects.equals(currentNode, tail)) {
                throw new NoSuchElementException("Достигнут хвост списка!");
            }
            if (this.modCountPrimary != modCount) {
                throw new ConcurrentModificationException("Во время прохождения списка были внесены изменения!");
            }
            currentNode = currentNode.getNext();
            return currentNode.getData();
        }
    }

    public String toString() {
        StringBuilder line = new StringBuilder("{");
        if (size() != 0) {
            for (T dataNode : this) {
                line.append(dataNode);
                line.append(", ");
            }
            line.delete(line.length() - 2, line.length());
        }
        line.append("}");
        return line.toString();
    }
}
