package shelest.list.main;

import shelest.list.singlyLinkedList.SinglyLinkedList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.addFirstElement(null);
        list1.addFirstElement(6);
        list1.addFirstElement(5);
        list1.addFirstElement(3);
        list1.addFirstElement(3);

        System.out.println("Список номер 1: " + list1.toString());
        System.out.println("Длина списка: " + list1.getLength());
        System.out.println("Первый элемент: " + list1.getFirstElement());


        System.out.print(System.lineSeparator() + "Введите индекс: ");
        Scanner scanner1 = new Scanner(System.in);
        int index1 = scanner1.nextInt();
        System.out.println("Элемент по индексу " + index1 + " равен: " + list1.getData(index1));


        System.out.print(System.lineSeparator() + "Введите индекс для изменения значения: ");
        Scanner scanner2 = new Scanner(System.in);
        int index2 = scanner2.nextInt();

        System.out.print("Введите целое число: ");
        Scanner scanner3 = new Scanner(System.in);
        int number1 = scanner3.nextInt();

        System.out.println("Элемент " + list1.setData(index2, number1) + " по индексу " + index2 + " заменен на " + number1);
        System.out.println("Список номер 1 после замены значения: " + list1.toString());


        System.out.print(System.lineSeparator() + "Введите индекс для удаления значения: ");
        Scanner scanner4 = new Scanner(System.in);
        int index3 = scanner4.nextInt();
        System.out.println("Элемент со значением \"" + list1.delElement(index3) + "\" по индексу " + index3 + " удален");
        System.out.println("Список номер 1 после удаления значения: " + list1.toString());


        System.out.print(System.lineSeparator() + "Введите целое число: ");
        Scanner scanner5 = new Scanner(System.in);
        int number2 = scanner5.nextInt();

        list1.addFirstElement(number2);
        System.out.println("Список номер 1 после добавления \"" + number2 + "\" в начало списка: " + list1.toString());


        System.out.print(System.lineSeparator() + "Введите индекс для добавления значения: ");
        Scanner scanner6 = new Scanner(System.in);
        int index4 = scanner6.nextInt();

        System.out.print("Введите целое число: ");
        Scanner scanner7 = new Scanner(System.in);
        int number3 = scanner7.nextInt();

        list1.addElement(index4, number3);
        System.out.println("Список номер 1 после добавления значения: " + list1.toString());


        System.out.print(System.lineSeparator() + "Введите целое число для поиска по списку: ");
        Scanner scanner8 = new Scanner(System.in);
        Integer number4 = scanner8.nextInt();

        System.out.println("Результат удаления: " + list1.delElementByData(number4));
        System.out.println("Список номер 1 после удаления значения: " + list1.toString());


        System.out.println(System.lineSeparator() + "Удаленный первый элемент: " + list1.delFirstElement());
        System.out.println("Список номер 1 после удаления первого элемента: " + list1.toString());


        list1.getTurn();
        System.out.println(System.lineSeparator() + "Список номер 1 после его разворота: " + list1.toString());


        System.out.println(System.lineSeparator() + "Список номер 2 (копия списка номер 1): " + list1.copyList().toString());
    }
}
