package shelest.linkedlist.main;

import shelest.linkedlist.operation.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main (String [] args){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(8);
        list.add(11);
        list.add(null);
        list.add(11);
        list.add(12);

        //System.out.println("Длина списка составляет: " + list.size());

        System.out.println("Массив из списка: " + Arrays.toString(list.toArray()));

        /*System.out.print(System.lineSeparator() + "Введите цифру для поиска: ");
        Scanner scanner1 = new Scanner(System.in);
        Integer number1 = scanner1.nextInt();
        System.out.println("Результат поиска: " + list.contains(number1));

        System.out.print(System.lineSeparator() + "Введите цифру для удаления: ");
        Scanner scanner2 = new Scanner(System.in);
        Integer number2 = scanner2.nextInt();
        System.out.println("Результат удаления: " + list.remove(number2));
        System.out.println("Список после удаления" + list.toString());*/

        ArrayList<Integer> array = new ArrayList<>();
        array.add(10);
        array.add(7);
        array.add(4);
        //System.out.println("Присутствует ли все элементы из коллекции в списке: " + list.containsAll(array));

        /*list.addAll(4, array);
        System.out.println("Список после добавления коллекции к нему: " + list.toString());

        list.add(0, 342);
        System.out.println("Список после добавления элемента по индексу: " + list.toString());

        System.out.println("Результат удаления коллекции из списка: " + list.removeAll(array));
        System.out.println("Список после удаления из него коллекции: " + list.toString());

        System.out.println("Результат сохранения объектов коллекции в списке: " + list.retainAll(array));
        System.out.println("Список после сохранения объектов коллекции в списке: " + list.toString());

        list.clear();
        System.out.println("Список после его очистки: " + list.toString());

        System.out.println("Значение под индексом 3 равно: " + list.get(0));


        System.out.println("Старое значение до замены значение под индексом 2 на 32: " + list.set(2, 32));
        System.out.println("Список после изменений: " + list.toString());

        System.out.println("Удаленное значение под индексом 2: " + list.remove(2));
        System.out.println("Список после изменений: " + list.toString());*/

        System.out.println("Индекс первого вхождения со значением 11: " + list.indexOf(11));

        System.out.println("Индекс последнего вхождения со значением 11: " + list.lastIndexOf(11));
    }
}
