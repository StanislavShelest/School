package shelest.arraylist.operation;

public class ListElement<T> {
    private T data;
    private int index;

    public ListElement(T data){
        this.data = data;
    }

    public ListElement(T data, int index){
        this.data = data;
        this.index = index;
    }

    public T getData(){
        return this.data;
    }

    public void setData(T data){
        this.data = data;
    }


}
