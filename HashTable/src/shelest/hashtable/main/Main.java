package shelest.hashtable.main;


import shelest.hashtable.operation.HashTable;

public class Main {
    public static void main(String[] args){
        HashTable<Integer> table1 = new HashTable<>(5);
        table1.add(8);
        table1.add(9);
        table1.add(6);
        table1.add(7);
        table1.add(2);
    }
}
