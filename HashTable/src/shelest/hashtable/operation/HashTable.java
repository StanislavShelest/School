package shelest.hashtable.operation;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] tableElement;
    private int elementsCount;
    private int modCount = 0;

    public HashTable() {
        this(50);
    }

    public HashTable(int tableLength) {
        //noinspection unchecked
        this.tableElement = new ArrayList[tableLength];
        for (int i = 0; i < tableLength; i++) {
            tableElement[i] = new ArrayList<>();
        }
    }

    @Override
    public int size() {
        return this.tableElement.length;
    }

    @Override
    public boolean isEmpty() {
        return this.tableElement.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return tableElement[getHashCode(o)].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new TableIterator();
    }

    private class TableIterator implements Iterator<T> {
        private T[] arrayTable;
        private int currentIndex = -1;
        private int modCountPrimary;

        private TableIterator() {
            this.modCountPrimary = modCount;
            //noinspection unchecked
            this.arrayTable = (T[]) new Object[elementsCount];
            this.arrayTable = HashTable.this.toArray(arrayTable);
        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < arrayTable.length;
        }

        @Override
        public T next() {
            if (currentIndex == arrayTable.length) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCountPrimary != modCount) {
                throw new ConcurrentModificationException("За время обхода были внесены изменения в список");
            }
            currentIndex++;
            return arrayTable[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[elementsCount];
        int index = 0;
        for (ArrayList<T> element : tableElement) {
            System.arraycopy(element.toArray(), 0, array, index, element.size());
            index += element.size();
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        //noinspection unchecked
        T1[] array = (T1[]) new Object[elementsCount];
        int index = 0;
        for (ArrayList<T> element : tableElement) {
            //noinspection SuspiciousToArrayCall
            System.arraycopy(element.toArray(a), 0, array, index, element.size());
            index += element.size();
        }
        return array;
    }

    @Override
    public boolean add(T t) {
        if (tableElement[getHashCode(t)].add(t)) {
            modCount++;
            elementsCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (tableElement[getHashCode(o)].remove(o)) {
            modCount++;
            elementsCount--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.size() != 0) {
            for (Object searchElement : c) {
                if (!this.contains(searchElement)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() != 0) {
            for (T addedElement : c) {
                this.add(addedElement);
            }
            modCount++;
            elementsCount += c.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean resultRemove = false;
        if (c.size() != 0) {
            for (ArrayList<T> element : tableElement) {
                if (element.removeAll(c)) {
                    resultRemove = true;
                }
            }
            if (resultRemove) {
                modCount++;
            }
        }
        return resultRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() != 0) {
            boolean resultRetain = false;
            for (ArrayList<T> element : tableElement) {
                int currentListLength = element.size();
                if (element.retainAll(c)) {
                    resultRetain = true;
                    elementsCount = elementsCount - (currentListLength - element.size());
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
        for (ArrayList<T> element : tableElement) {
            element.clear();
        }
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("[");
        for (ArrayList<T> element : tableElement) {
            line.append(element.toString());
            line.append(", ");
        }
        line.delete(line.length() - 2, line.length());
        line.append("]");
        return line.toString();
    }

    private int getHashCode(Object value) {
        return (Objects.hashCode(value)) % tableElement.length;
    }
}
