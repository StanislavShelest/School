package shelest.hashtable.operation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class HashTable<T> implements Collection<T> {
    private Collection<T>[] collection;
    private int tableSize;

    public HashTable() {
        this.collection = new Collection[100];
    }

    public HashTable(int tableSize) {
        this.collection = new Collection[tableSize];
    }

    @Override
    public int size() {
        return this.tableSize;
    }

    @Override
    public boolean isEmpty() {
        return this.tableSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        int elementHash = leadToHashCodeTable(Objects.hashCode(o));
        Collection neededCollection = this.collection[elementHash];

        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.collection, tableSize);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < tableSize) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(this.collection, tableSize, a.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(this.collection, 0, a, 0, tableSize);
        if (a.length > tableSize) {
            a[tableSize] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        int tHash = leadToHashCodeTable(Objects.hashCode(t));
        collection[tHash].add(t);
        return false;
    }

    @Override
    public boolean remove(Object o) {
        int oHash = leadToHashCodeTable(Objects.hashCode(o));
        collection[oHash].remove(o);
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element: c){
            int elementHash = leadToHashCodeTable(Objects.hashCode(element));
            if (!collection[elementHash].contains(element)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    private int leadToHashCodeTable(int elementHash) {
        if (elementHash >= tableSize) {
            elementHash = Math.abs(elementHash % tableSize);
        }
        return elementHash;
    }
}
