package shelest.list.singlyLinkedList;

import shelest.list.listItem.ListItem;

public class SinglyLinkedList<T> {
    private int listLength;
    private ListItem<T> head;

    public int getLength() {
        return listLength;
    }

    public T getFirstElement() {
        return head.getData();
    }

    public void addFront(T firstData) {
        head = new ListItem<>(firstData,head);
        listLength++;
    }

    public T getElement(int index) {
        ListItem<T> listElement = head;
        for (int i = 0; i < index; i++) {
            listElement = listElement.getNext();
        }
        return listElement.getData();
    }

    public T setElement(int index, T data){
        ListItem<T> listElement = head;
        for (int i = 0; i < index; i++) {
            listElement = listElement.getNext();
        }
        T oldData = listElement.getData();
        listElement.setData(data);
        return oldData;
    }
}