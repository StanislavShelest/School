package shelest.arraylist.operation;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class List<T> {
    private T[] data;
    private int listLength;

    public List() {
        //noinspection unchecked
        this.data = (T[]) new Object[10];
    }

    public List(int capacity) {
        //noinspection unchecked
        this.data = (T[]) new Object[capacity];
    }

    private void checkIncorrectIndex(int index) {
        if (index < 0 || index >= listLength) {
            throw new IndexOutOfBoundsException("Введен некорректный индекс");
        }
    }

    private void checkEmptinessList() {
        if (listLength == 0) {
            throw new NoSuchElementException("Список пуст");
        }
    }

    private void ensureCapacity() {
        if (listLength >= this.data.length) {
            T[] temp = this.data;
            //noinspection unchecked
            this.data = (T[]) new Object[temp.length * 2];
            System.arraycopy(temp, 0, this.data, 0, temp.length);
        }
    }

    public int getLength() {
        return this.listLength;
    }

    public T getFirstElement() {
        checkEmptinessList();
        return this.data[0];
    }

    public T getData(int index) {
        checkIncorrectIndex(index);
        return this.data[index];
    }

    public T setData(int index, T data) {
        checkIncorrectIndex(index);
        T elementOld = this.data[index];
        this.data[index] = data;
        return elementOld;
    }

    public T delElement(int index) {
        checkIncorrectIndex(index);
        T elementDeleted = data[index];
        if (index < listLength - 1) {
            System.arraycopy(data, index + 1, data, index, listLength - index - 1);
        }
        listLength--;
        return elementDeleted;
    }

    public void addFirstElement(T data) {
        ensureCapacity();
        System.arraycopy(this.data, 0, this.data, 1, listLength);
        this.data[0] = data;
        listLength++;
    }

    public void addElement(int index, T data) {
        ensureCapacity();
        if (index < 0 || index > listLength) {
            throw new IndexOutOfBoundsException("Введен некорректный индекс");
        }
        System.arraycopy(this.data, index, this.data, index + 1, listLength - index);
        this.data[index] = data;
        listLength++;
    }

    public boolean delElementByData(T data) {
        boolean resultDeleting = false;
        for (int i = 0; i < this.listLength; i++) {
            if (this.data[i].equals(data)) {
                System.arraycopy(this.data, i + 1, this.data, i, listLength - i - 1);
                resultDeleting = true;
                listLength--;
                break;
            }
        }
        return resultDeleting;
    }

    public T delFirstElement() {
        checkEmptinessList();
        T elementDeleting = data[0];
        System.arraycopy(data, 1, data, 0, listLength - 1);
        listLength--;
        return elementDeleting;
    }

    public List<T> getTurn() {
        int count = Math.round(listLength / 2);
        for (int i = 0; i < count; i++) {
            T temp = data[i];
            data[i] = data[listLength - 1 - i];
            data[listLength - 1 - i] = temp;
        }
        return this;
    }

    public List<T> copyList() {
        List<T> copy = new List<>();
        copy.data = Arrays.copyOf(this.data, this.listLength);
        copy.listLength = this.listLength;
        return copy;
    }

    public void trimToSize() {
        this.data = Arrays.copyOf(this.data, this.listLength);
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
}

