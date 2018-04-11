package shelest.list.listElement;

public class ListElement<T> {
    private T data;
    private ListElement<T> next;

    public ListElement(T data) {
        this.data = data;
    }

    public ListElement(T data, ListElement<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListElement<T> getNext() {
        return this.next;
    }

    public void setNext(ListElement<T> next) {
        this.next = next;
    }
}
