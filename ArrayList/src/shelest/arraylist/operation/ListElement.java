package shelest.arraylist.operation;

public class ListElement<T> {
    private T data;
    private int index;

    public T getData(){
        return this.data;
    }

    public void setData(T data){
        this.data = data;
    }
}
