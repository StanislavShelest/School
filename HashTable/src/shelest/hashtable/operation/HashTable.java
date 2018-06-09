package shelest.hashtable.operation;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] tableLists;
    private int elementsCount;
    private int modCount = 0;

    public HashTable() {
        this(10);
    }

    public HashTable(int tableLength) {
        //noinspection unchecked
        this.tableLists = new ArrayList[tableLength];
        for (int i = 0; i < tableLength; i++) {
            tableLists[i] = new ArrayList<>();
        }
    }

    @Override
    public int size() {
        return this.elementsCount;
    }

    @Override
    public boolean isEmpty() {
        return this.elementsCount == 0;
    }

    @Override
    public boolean contains(Object o) {
        return tableLists[getHashCode(o)].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new TableIterator();
    }

    private class TableIterator implements Iterator<T> {
        private int currentListIndex = -1;
        private int currentIndexInList = -1;
        private int countUsedElement = 0;
        private int modCountPrimary;

        private TableIterator() {
            for (int i = 0; i < tableLists.length; i++) {
                if (tableLists[i].size() != 0) {
                    currentListIndex = i;
                    break;
                }
            }
            this.modCountPrimary = modCount;
        }

        @Override
        public boolean hasNext() {
            if (currentListIndex == -1) {
                return false;
            }
            if (currentIndexInList + 1 < tableLists[currentListIndex].size()) {
                return true;
            } else {
                for (int i = 1; currentListIndex + i < tableLists.length; i++) {
                    if (tableLists[currentListIndex + i].size() != 0) {
                        return true;
                    }
                }
                return false;
            }
        }

        @Override
        public T next() {
            if (countUsedElement == elementsCount) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCountPrimary != modCount) {
                throw new ConcurrentModificationException("За время обхода были внесены изменения в список");
            }
            if (currentIndexInList + 1 < tableLists[currentListIndex].size()) {
                currentIndexInList++;
            } else {
                for (int i = 1; currentListIndex + i < tableLists.length; i++) {
                    if (tableLists[currentListIndex + i].size() != 0) {
                        currentListIndex += i;
                        currentIndexInList = 0;
                        break;
                    }
                }
            }
            countUsedElement++;
            return tableLists[currentListIndex].get(currentIndexInList);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[elementsCount];
        int index = 0;
        for (T list : this) {
            array[index] = list;
            index++;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < elementsCount) {
            //noinspection unchecked
            T1[] array = (T1[]) new Object[elementsCount];
            int index = 0;
            for (T list : this) {
                //noinspection unchecked
                array[index] = (T1) list;
                index++;
            }
            return array;
        }

        int index = 0;
        for (T list : this) {
            //noinspection unchecked
            a[index] = (T1) list;
            index++;
        }
        if (a.length > elementsCount) {
            a[elementsCount] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        if (tableLists[getHashCode(t)].add(t)) {
            modCount++;
            elementsCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (tableLists[getHashCode(o)].remove(o)) {
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
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean resultRemove = false;
        if (c.size() != 0) {
            for (ArrayList<T> element : tableLists) {
                int elementSize = element.size();
                if (element.removeAll(c)) {
                    elementsCount = elementsCount - elementSize + element.size();
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
            for (ArrayList<T> element : tableLists) {
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
        for (ArrayList<T> element : tableLists) {
            element.clear();
        }
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("[");
        for (ArrayList<T> element : tableLists) {
            line.append(element.toString());
            line.append(", ");
        }
        line.delete(line.length() - 2, line.length());
        line.append("]");
        return line.toString();
    }

    private int getHashCode(Object value) {
        return Math.abs(Objects.hashCode(value) % tableLists.length);
    }
}
