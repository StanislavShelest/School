package shelest.list.main;

import shelest.list.singlyLinkedList.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> example = new SinglyLinkedList<>();
        example.addFront(7);
        example.addFront(6);
        example.addFront(5);

        System.out.println(example.getFirstElement());
        System.out.println(example.getLength());
        System.out.println(example.getElement(2));
        System.out.println(example.setElement(2, 9));
        System.out.println(example.getElement(2));
    }
}
