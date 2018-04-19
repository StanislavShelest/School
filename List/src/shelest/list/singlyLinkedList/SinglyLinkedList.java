package shelest.list.singlyLinkedList;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private int listLength;
    private ListElement<T> head;

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

    public int getLength() {
        return listLength;
    }

    public T getFirstElement() {
        this.checkEmptinessList();
        return head.getData();
    }

    public void addFirstElement(T data) {
        head = new ListElement<>(data, head);
        listLength++;
    }

    private ListElement<T> getElement(int index) {
        ListElement<T> element = head;
        for (int i = 0; i < index; i++) {
            element = element.getNext();
        }
        return element;
    }

    public T getData(int index) {
        checkIncorrectIndex(index);
        return getElement(index).getData();
    }

    public T setData(int index, T data) {
        checkIncorrectIndex(index);
        ListElement<T> element = getElement(index);
        T oldData = element.getData();
        element.setData(data);
        return oldData;
    }

    public T delElement(int index) {
        checkIncorrectIndex(index);
        T dataDeleted;
        if (index == 0) {
            dataDeleted = head.getData();
            head = head.getNext();
        } else {
            ListElement<T> element = getElement(index - 1);
            dataDeleted = element.getNext().getData();
            element.setNext(element.getNext().getNext());
        }
        listLength--;
        return dataDeleted;
    }

    public void addElement(int index, T data) {
        if (index < 0 || index > listLength) {
            throw new IndexOutOfBoundsException("Введен некорректный индекс");
        }
        if (index == 0) {
            head = new ListElement<>(data, head);
        } else {
            ListElement<T> element = getElement(index - 1);
            ListElement<T> elementAdded = new ListElement<>(data);
            elementAdded.setNext(element.getNext());
            element.setNext(elementAdded);
        }
        listLength++;
    }

    public boolean delElementByData(T data) {
        boolean resultDeletion = false;
        if (listLength != 0) {
            boolean headDelete = false;
            ListElement<T> element = head;
            int listLengthPrimary = listLength;
            if (Objects.equals(head.getData(), data)) {
                headDelete = true;
            }
            for (int i = 0; i < listLengthPrimary - 1; i++) {
                if (Objects.equals(element.getNext().getData(), data)) {
                    element.setNext(element.getNext().getNext());
                    resultDeletion = true;
                    listLength--;
                    continue;
                }
                element = element.getNext();
            }
            if (headDelete) {
                head = head.getNext();
                listLength--;
            }
        }
        return resultDeletion;
    }

    /*public boolean delElementByData(T data) {
        boolean resultDeletion = false;
        addFirstElement(null);
        ListElement<T> element = head;
        int listLengthPrimary = listLength;
        for (int i = 0; i < listLengthPrimary - 1; i++) {
            //if (element.getNext().getData() != null) {
                if (element.getNext().getData().equals(data)) {
                    element.setNext(element.getNext().getNext());
                    resultDeletion = true;
                    listLength--;
                    continue;
                }
           // } else {
                //if (data == null) {
                    //element.setNext(element.getNext().getNext());
                   // resultDeletion = true;
                   // listLength--;
                   // continue;
                //}
            //}
            element = element.getNext();
        }
        delFirstElement();
        return resultDeletion;
    }*/

    public T delFirstElement() {
        this.checkEmptinessList();
        T dataDeleted = head.getData();
        head = head.getNext();
        listLength--;
        return dataDeleted;
    }

    public void getTurn() {
        ListElement<T> elementPrevious = head;
        ListElement<T> element = head.getNext();
        ListElement<T> elementNext = element.getNext();
        elementPrevious.setNext(null);
        for (int i = 0; i < listLength - 1; i++) {
            element.setNext(elementPrevious);
            elementPrevious = element;
            if (elementNext != null) {
                element = elementNext;
                elementNext = elementNext.getNext();
            }
        }
        head = element;
    }

    public SinglyLinkedList<T> copyList() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();
        if (this.listLength != 0) {
            ListElement<T> listElementPrimary = this.head;
            ListElement<T> listElementPrevious = new ListElement<>(this.head.getData());
            copy.head = listElementPrevious;
            for (int i = 0; i < this.listLength - 1; i++) {
                listElementPrimary = listElementPrimary.getNext();
                ListElement<T> listElement = new ListElement<>(listElementPrimary.getData());
                listElementPrevious.setNext(listElement);
                listElementPrevious = listElementPrevious.getNext();
            }
            copy.listLength = this.listLength;
        }
        return copy;
    }

    @Override
    public String toString() {
        ListElement<T> element = head;
        StringBuilder line = new StringBuilder();
        if (this.listLength != 0) {
            line.append("{");
            for (int i = 0; i < listLength; i++) {
                if (element.getData() == null) {
                    line.append("null,");
                } else {
                    line.append(element.getData().toString());
                    line.append(",");
                }
                element = element.getNext();
            }
            line.delete(line.length() - 1, line.length());
            line.append("}");
        }
        return line.toString();

    }
}