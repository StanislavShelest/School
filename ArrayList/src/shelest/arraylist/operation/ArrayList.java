package shelest.arraylist.operation;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] data;
    private int listLength;
    private int modCount = 0;

    public ArrayList() {
        //noinspection unchecked
        this.data = (T[]) new Object[10];
    }

    public ArrayList(int capacity) {
        //noinspection unchecked
        this.data = (T[]) new Object[capacity];
    }

    private void checkIncorrectIndex(int index) {
        if (index < 0 || index >= listLength) {
            throw new IndexOutOfBoundsException("Введен некорректный индекс");
        }
    }

    private void checkIncorrectIndexLengthInclusive(int index) {
        if (index < 0 || index > listLength) {
            throw new IndexOutOfBoundsException("Введен некорректный индекс");
        }
    }

    private void ensureCapacity() {
        if (listLength >= this.data.length) {
            this.data = Arrays.copyOf(this.data, this.listLength * 2);
        }
    }

    @Override
    public int size() {
        return this.listLength;
    }

    @Override
    public boolean isEmpty() {
        return listLength == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.listLength; i++) {
            if (Objects.equals(this.data[i], o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(this.data, this.listLength);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < listLength) {
            return (T1[]) Arrays.copyOf(this.data, listLength, a.getClass());
        }
        System.arraycopy(this.data, 0, a, 0, listLength);
        if (a.length > listLength) {
            a[listLength] = null;
        }
        return a;
    }

    @Override
    public boolean add(T data) {
        add(listLength, data);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.listLength; i++) {
            if (Objects.equals(this.data[i], o)) {
                System.arraycopy(this.data, i + 1, this.data, i, listLength - i - 1);
                listLength--;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.size() != 0) {
            if (this != c) {
                for (Object element : c) {
                    if (!this.contains(element)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return c.size() != 0 && this.addAll(this.listLength, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIncorrectIndexLengthInclusive(index);
        if (c.size() != 0) {
            if (this != c) {
                for (T element : c) {
                    add(index, element);
                    index++;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean delete = false;
        if (c.size() != 0) {
            if (this != c) {
                for (Object element : c) {
                    for (int i = 0; i < this.listLength; i++) {
                        if (Objects.equals(this.data[i], element)) {
                            System.arraycopy(this.data, i + 1, this.data, i, listLength - i - 1);
                            this.listLength--;
                            delete = true;
                            modCount++;
                            i--;
                        }
                    }
                }
            }
        }
        return delete;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() != 0) {
            if (this != c) {
                for (Object element : c) {
                    for (int i = 0; i < this.listLength; i++) {
                        if (Objects.equals(this.data[i], element)) {
                            break;
                        } else {
                            if (i == this.listLength - 1) {
                                System.arraycopy(this.data, i + 1, this.data, i, listLength - i - 1);
                                this.listLength--;
                                i--;
                            }
                        }
                    }
                }
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        modCount++;
        this.listLength = 0;
    }

    @Override
    public T get(int index) {
        checkIncorrectIndex(index);
        return this.data[index];
    }

    @Override
    public T set(int index, T element) {
        checkIncorrectIndex(index);
        T primaryElement = this.data[index];
        this.data[index] = element;
        return primaryElement;
    }

    @Override
    public void add(int index, T data) {
        checkIncorrectIndexLengthInclusive(index);
        ensureCapacity();
        System.arraycopy(this.data, index, this.data, index + 1, listLength - index);
        this.data[index] = data;
        listLength++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        checkIncorrectIndex(index);
        T elementDeleted = data[index];
        System.arraycopy(this.data, index + 1, this.data, index, listLength - 1 - index);
        listLength--;
        modCount++;
        return elementDeleted;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.listLength; i++) {
            if (Objects.equals(this.data[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = listLength - 1; i >= 0; i--) {
            if (Objects.equals(this.data[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ArrayListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ArrayListIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return this;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        if (listLength != 0) {
            line.append("{");
            for (int i = 0; i < listLength; i++) {
                if (this.data[i] == null) {
                    line.append("null,");
                } else {
                    line.append(this.data[i].toString());
                    line.append(",");
                }
            }
            line.delete(line.length() - 1, line.length());
            line.append("}");
        }
        return line.toString();
    }

    private class ArrayIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCountPrimary;

        private ArrayIterator() {
            this.modCountPrimary = modCount;
        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < listLength;
        }

        @Override
        public T next() {
            if (currentIndex == listLength) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCountPrimary != modCount) {
                throw new ConcurrentModificationException("За время обхода были внесены изменения в список");
            }
            currentIndex++;
            return data[currentIndex];
        }
    }

    private class ArrayListIterator implements ListIterator<T> {
        private int currentIndex = -1;
        private int modCountPrimary;

        private ArrayListIterator() {
            this.modCountPrimary = modCount;
        }

        private ArrayListIterator(int index) {
            currentIndex = index;
            this.modCountPrimary = modCount;
        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < listLength;
        }

        @Override
        public T next() {
            if (currentIndex == listLength) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCountPrimary != modCount) {
                throw new ConcurrentModificationException("За время обхода были внесены изменения в список");
            }
            currentIndex++;
            return data[currentIndex];
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex - 1 >= 0;
        }

        @Override
        public T previous() {
            if (currentIndex == 0) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCountPrimary != modCount) {
                throw new ConcurrentModificationException("За время обхода были внесены изменения в список");
            }
            currentIndex--;
            return data[currentIndex];
        }

        @Override
        public int nextIndex() {
            return currentIndex++;
        }

        @Override
        public int previousIndex() {
            return currentIndex--;
        }

        @Override
        public void remove() {
            System.arraycopy(data, currentIndex + 1, data, currentIndex, listLength - 1 - currentIndex);
            listLength--;
        }

        @Override
        public void set(T t) {
            data[currentIndex] = t;
        }

        @Override
        public void add(T t) {
            data[currentIndex] = t;
            listLength++;
        }
    }
}

