package shelest.hashtable.operation;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] element;
    private int lengthTable;
    private int modCount = 0;

    public HashTable() {
        this.lengthTable = 50;
        //noinspection unchecked
        this.element = new ArrayList[50];
        for (int i = 0; i < lengthTable; i++) {
            element[i] = new ArrayList<>();
        }
    }

    public HashTable(int lengthTable) {
        this.lengthTable = lengthTable;
        //noinspection unchecked
        this.element = new ArrayList[lengthTable];
        for (int i = 0; i < lengthTable; i++) {
            element[i] = new ArrayList<>();
        }
    }

    @Override
    public int size() {
        return lengthTable;
    }

    @Override
    public boolean isEmpty() {
        return lengthTable == 0;
    }

    @Override
    public boolean contains(Object o) {
        return element[getHashCode(o)].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new TableIterator();
    }

    private class TableIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCountPrimary;

        private TableIterator() {

        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < lengthTable;
        }

        @Override
        public T next() {
            /*if (currentIndex == lengthTable) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCountPrimary != modCount) {
                throw new ConcurrentModificationException("За время обхода были внесены изменения в список");
            }
            currentIndex++;*/
            return null;
        }
    }

    @Override
    public Object[] toArray() {
        //return Arrays.copyOf(this.element, lengthTable);
        return null;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        /*if (a.length < lengthTable) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(this.element, lengthTable, a.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(this.element, 0, a, 0, lengthTable);
        if (a.length > lengthTable) {
            a[lengthTable] = null;
        }*/
        return null;
    }

    @Override
    public boolean add(T t) {
        modCount++;
        return element[getHashCode(t)].add(t);
    }

    @Override
    public boolean remove(Object o) {
        modCount++;
        return element[getHashCode(o)].remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.size() != 0) {
            for (Object searchElement : c) {
                if (!this.contains(searchElement)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() != 0) {
            for (T addedElement : c) {
                this.add(addedElement);
            }
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() != 0) {
            boolean resultRemove = false;
            for (Object deleteElement : c) {
                if (this.remove(deleteElement)) {
                    resultRemove = true;
                }
            }
            if (resultRemove) {
                modCount++;
            }
            return resultRemove;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() != 0) {
            boolean resultRetain = false;
            for (int i = 0; i < lengthTable; i++) {
                if (element[i].retainAll(c)) {
                    resultRetain = true;
                }
            }
            if (resultRetain) {
                modCount++;
            }
            return resultRetain;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < lengthTable; i++) {
            element[i] = new ArrayList<>();
        }
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("[");
        for (int i = 0; i < lengthTable; i++) {
            line.append(element[i].toString());
            line.append(", ");
        }
        line.delete(line.length() - 2, line.length());
        line.append("]");
        return line.toString();
    }

    private int getHashCode(Object value) {
        return (Objects.hashCode(value)) % lengthTable;
    }
}
