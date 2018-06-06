package shelest.hashtable.main;


import shelest.hashtable.operation.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table1 = new HashTable<>();
        table1.add(null);
        table1.add(9);
        table1.add(0);
        table1.add(7);
        table1.add(2);
        table1.add(2);
        table1.add(8);


        System.out.println("Таблица №1: " + table1.toString());
        System.out.println("Нулевая ли таблица: " + table1.isEmpty());
        System.out.println("Размер таблицы равен: " + table1.size());


        Iterator<Integer> iterator1 = table1.iterator();
        System.out.print(System.lineSeparator() + "Проверка итератора: ");
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }
        System.out.print(System.lineSeparator());


        System.out.print(System.lineSeparator() + "Введите число для поиска по таблице: ");
        Scanner scanner1 = new Scanner(System.in);
        int number1 = scanner1.nextInt();
        System.out.println("Есть ли введеное число в таблице: " + table1.contains(number1));


        System.out.println(System.lineSeparator() + "Массив, полученный из таблицы: " + Arrays.toString(table1.toArray()));


        Integer[] array2 = new Integer[2];
        System.out.println("Массив, полученный c приведением к типу из таблицы: " + Arrays.toString(table1.toArray(array2)));


        System.out.print(System.lineSeparator() + "Введите число для удаления из таблицы: ");
        Scanner scanner2 = new Scanner(System.in);
        int number2 = scanner2.nextInt();

        System.out.println("Удалено ли введеное число из таблицы: " + table1.remove(number2));
        System.out.println("Таблица №1 после изменений: " + table1.toString());


        ArrayList<Integer> list1 = new ArrayList<>(4);
        list1.add(6);
        list1.add(8);
        list1.add(null);
        list1.add(7);

        System.out.println(System.lineSeparator() + "Список №1: " + list1.toString());
        System.out.println("Все ли элементы списка №1 присутвуют в таблице: " + table1.containsAll(list1));


        ArrayList<Integer> list2 = new ArrayList<>(4);
        list2.add(1);
        list2.add(null);
        list2.add(0);
        list2.add(9);

        System.out.println(System.lineSeparator() + "Список №2: " + list2.toString());
        System.out.println("Добавлены ли элементы списка №2 в таблицу: " + table1.addAll(list2));
        System.out.println("Таблица №1 после изменений: " + table1.toString());


        ArrayList<Integer> list3 = new ArrayList<>(4);
        list3.add(0);
        list3.add(17);
        list3.add(null);
        list3.add(19);

        System.out.println(System.lineSeparator() + "Список №3: " + list3.toString());
        System.out.println("Удален ли хоть один элемент списка №3 из таблицы: " + table1.removeAll(list3));
        System.out.println("Таблица №1 после изменений: " + table1.toString());


        ArrayList<Integer> list4 = new ArrayList<>(4);
        list4.add(2);
        list4.add(7);
        list4.add(null);
        list4.add(9);

        System.out.println(System.lineSeparator() + "Список №4: " + list4.toString());
        System.out.println("Удален ли хоть один элемент из таблицы невходящий в список №4: " + table1.retainAll(list4));
        System.out.println("Таблица №1 после изменений: " + table1.toString());


        table1.clear();
        System.out.println(System.lineSeparator() + "Таблица №1 после очистки: " + table1.toString());
    }
}
