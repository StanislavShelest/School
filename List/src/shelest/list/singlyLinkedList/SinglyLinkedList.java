package shelest.list.singlyLinkedList;

import shelest.list.listElement.ListElement;

public class SinglyLinkedList<T> {
    private int listLength;
    private ListElement<T> head;

    private void checkIncorrectIndex(int index) {
        if (index < 0 || index > listLength - 1) {
            throw new IllegalArgumentException("Введен некорректный индекс");
        }
    }

    public int getLength() {
        return listLength;
    }

    public T getFirstElement() {
        return head.getData();
    }

    public void addFirstElement(T data) {
        head = new ListElement<>(data, head);
        listLength++;
    }

    public T getElement(int index) {
        checkIncorrectIndex(index);
        ListElement<T> element = head;
        for (int i = 0; i < index; i++) {
            element = element.getNext();
        }
        return element.getData();
    }

    public T setElement(int index, T data) {
        checkIncorrectIndex(index);
        ListElement<T> element = head;
        for (int i = 0; i < index; i++) {
            element = element.getNext();
        }
        T oldData = element.getData();
        element.setData(data);
        return oldData;
    }

    public T delElement(int index) {
        checkIncorrectIndex(index);
        ListElement<T> element = head;
        T dataDeleted;
        if (index == 0) {
            dataDeleted = head.getData();
            head = head.getNext();
        } else {
            for (int i = 0; i < index - 1; i++) {
                element = element.getNext();
            }
            dataDeleted = element.getNext().getData();
            element.setNext(element.getNext().getNext());
        }
        listLength--;
        return dataDeleted;
    }

    public void addElement(int index, T data) {
        if (index < 0 || index > listLength) {
            throw new IllegalArgumentException("Введен некорректный индекс");
        }

        ListElement<T> element = head;
        if (index == 0) {
            head = new ListElement<>(data, head);
        } else {
            for (int i = 0; i < index - 1; i++) {
                element = element.getNext();
            }
            ListElement<T> elementAdded = new ListElement<>(data);
            elementAdded.setNext(element.getNext());
            element.setNext(elementAdded);
        }
        listLength++;
    }

    public boolean delData(T data) {
        boolean resultDeletion = false;
        ListElement<T> element = head;
        if (head.getData().equals(data)) {
            head = head.getNext();
            resultDeletion = true;
            listLength--;
        } else {
            for (int i = 0; i < listLength - 1; i++) {
                if (element.getNext().getData().equals(data)) {
                    element.setNext(element.getNext().getNext());
                    resultDeletion = true;
                    listLength--;
                }
                element = element.getNext();
            }
        }
        return resultDeletion;
    }

    public T delFirstElement() {
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

    public void copyList(SinglyLinkedList<T> copy) {
        ListElement<T> elementPrimaryList = this.head;
        while (elementPrimaryList.getNext() != null) {
            elementPrimaryList = elementPrimaryList.getNext();
        }
        copy.head = elementPrimaryList;
        copy.listLength = 1;
        elementPrimaryList = this.head;
        for (int i = 0; i < this.listLength - 1; i++) {
            copy.addElement(i, elementPrimaryList.getData());
            elementPrimaryList = elementPrimaryList.getNext();
        }
    }

    @Override
    public String toString() {
        ListElement<T> element = head;
        StringBuilder line = new StringBuilder("{");
        for (int i = 0; i < listLength; i++) {
            line.append(element.getData().toString());
            line.append(",");
            element = element.getNext();
        }
        line.delete(line.length() - 1, line.length());
        line.append("}");
        return line.toString();
    }
}