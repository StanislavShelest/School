package shelest.list.listItem;

public class ListItem<T> {
    private T data;
    private ListItem<T> next;

    public ListItem(){
    }

    public ListItem(T data) {
        this.data = data;
    }

    public ListItem(T data, ListItem<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData(){
        return this.data;
    }

    public void  setData (T data){
        this.data = data;
    }

    public ListItem<T> getNext(){
        return this.next;
    }

    public void setNext (ListItem<T> next){
        this.next = next;
    }
}
