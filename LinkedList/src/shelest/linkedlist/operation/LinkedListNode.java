package shelest.linkedlist.operation;

class LinkedListNode<T> {
    private T data;
    private LinkedListNode<T> prev;
    private LinkedListNode<T> next;

    LinkedListNode(T data){
        this.data = data;
    }

    LinkedListNode(T data, LinkedListNode<T> prev, LinkedListNode<T> next){
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    T getData(){
        return this.data;
    }

    LinkedListNode<T> getPrev(){
        return this.prev;
    }

    LinkedListNode<T> getNext(){
        return this.next;
    }

    void setData(T data){
        this.data = data;
    }

    void setPrev(LinkedListNode<T> prev){
        this.prev = prev;
    }

    void setNext(LinkedListNode<T> next){
        this.next = next;
    }
}
