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

    private void checkNullElement() {
        if (contains(null)) {
            throw new NullPointerException("В списке присутсвуют элементы содержащие null");
        }
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
        //noinspection unchecked
        T data = (T) o;
        for (int i = 0; i < this.listLength; i++) {
            if (Objects.equals(this.data[i], data)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T[] toArray() {
        checkNullElement();
        //noinspection unchecked
        T[] array = (T[]) new Object[this.listLength];
        System.arraycopy(this.data, 0, array, 0, this.listLength);
        Arrays.sort(array);
        modCount++;
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        checkNullElement();
        //noinspection unchecked
        a = (T1[]) new Object[this.listLength];
        System.arraycopy(this.data, 0, a, 0, this.listLength);
        Arrays.sort(a);
        modCount++;
        return a;
    }

    @Override
    public boolean add(T data) {
        add(listLength, data);
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        //noinspection unchecked
        T data = (T) o;
        for (int i = 0; i < this.listLength; i++) {
            if (Objects.equals(this.data[i], data)) {
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
        //noinspection unchecked
        ArrayList<T> transmittedList = (ArrayList<T>) c;
        if (transmittedList.listLength != 0) {
            if (this != transmittedList) {
                for (int i = 0; i < transmittedList.listLength; i++) {
                    if (!contains(transmittedList.data[i])) {
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
        //noinspection unchecked
        ArrayList<T> transmittedList = (ArrayList<T>) c;
        return transmittedList.listLength != 0 && this.addAll(this.listLength, transmittedList);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIncorrectIndexLengthInclusive(index);
        //noinspection unchecked
        ArrayList<T> transmittedList = (ArrayList<T>) c;
        if (transmittedList.listLength != 0) {
            if (this != transmittedList) {
                if (transmittedList.listLength + index >= this.data.length) {
                    this.data = Arrays.copyOf(this.data, transmittedList.listLength + index);
                }
                System.arraycopy(transmittedList.data, 0, this.data, index, transmittedList.listLength);
                this.listLength = index + transmittedList.listLength;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        //noinspection unchecked
        ArrayList<T> transmittedList = (ArrayList<T>) c;
        boolean delete = false;
        if (transmittedList.listLength != 0) {
            if (this != transmittedList) {
                for (int i = 0; i < this.listLength; i++) {
                    for (int j = 0; j < transmittedList.listLength; j++) {
                        if (Objects.equals(this.data[i], transmittedList.data[j])) {
                            System.arraycopy(this.data, i + 1, this.data, i, listLength - i - 1);
                            this.listLength--;
                            delete = true;
                            modCount++;
                            j--;
                        }
                    }
                }
            }
        }
        return delete;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        //noinspection unchecked
        ArrayList<T> transmittedList = (ArrayList<T>) c;
        if (transmittedList.listLength != 0) {
            if (this != transmittedList) {
                for (int i = 0; i < this.listLength; i++) {
                    boolean delete = false;
                    for (int j = 0; j < transmittedList.listLength; j++) {
                        if (Objects.equals(this.data[i], transmittedList.data[j])) {
                            break;
                        } else {
                            if (j == transmittedList.listLength - 1) {
                                delete = true;
                            }
                        }
                    }
                    if (delete) {
                        System.arraycopy(this.data, i + 1, this.data, i, listLength - i - 1);
                        this.listLength--;
                        i--;
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
        //noinspection unchecked
        T data = (T) o;
        for (int i = 0; i < this.listLength; i++) {
            if (Objects.equals(this.data[i], data)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        //noinspection unchecked
        T data = (T) o;
        for (int i = listLength - 1; i >= 0; i--) {
            if (Objects.equals(this.data[i], data)) {
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
        return null;
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

