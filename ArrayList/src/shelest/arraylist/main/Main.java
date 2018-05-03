package shelest.arraylist.main;

import shelest.arraylist.operation.ArrayList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(8);
        arrayList1.add(9);
        arrayList1.add(6);
        arrayList1.add(6);
        arrayList1.add(4);

        System.out.println("Список №1: " + arrayList1.toString());
        System.out.println("Длина списка: " + arrayList1.size());
        System.out.println("Пустой ли список: " + arrayList1.isEmpty());


        System.out.print(System.lineSeparator() + "Введите значение для поиска по списку: ");
        Scanner scanner1 = new Scanner(System.in);
        Integer number1 = scanner1.nextInt();
        System.out.println("Содержит ли список элемент \"" + number1 + "\": " + arrayList1.contains(number1));


        System.out.println("Массив, полученный из списка №1: " + Arrays.toString(arrayList1.toArray()));


        Integer[] array = new Integer[3];
        System.out.println("Массив, полученный из списка №1: " + Arrays.toString(arrayList1.toArray(array)));


        System.out.print(System.lineSeparator() + "Введите значение для удаления из списка: ");
        Scanner scanner2 = new Scanner(System.in);
        Integer number2 = scanner2.nextInt();
        System.out.println("Удален ли элемент \"" + number2 + "\" из списка: " + arrayList1.remove(number2));
        System.out.println("Список №1 после изменений: " + arrayList1.toString());


        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(6);
        arrayList2.add(4);
        arrayList2.add(7);
        arrayList2.add(4);
        arrayList2.add(null);

        System.out.println(System.lineSeparator() + "Список №2: " + arrayList2.toString());
        System.out.println("Все ли элементы списка №2 присутсвуют в списке №1: " + arrayList1.containsAll(arrayList2));

        ArrayList<Integer> arrayList3 = new ArrayList<>();
        arrayList3.add(null);
        arrayList3.add(4);
        arrayList3.add(5);
        arrayList3.add(6);
        arrayList3.add(7);

        System.out.println(System.lineSeparator() + "Список №3: " + arrayList3.toString());
        System.out.println("Добавлен ли список №3 в конец списка №1: " + arrayList1.addAll(arrayList3));
        System.out.println("Список №1 после изменений: " + arrayList1.toString());


        ArrayList<Integer> arrayList4 = new ArrayList<>();
        arrayList4.add(null);
        arrayList4.add(4);
        arrayList4.add(5);
        arrayList4.add(6);
        arrayList4.add(7);

        System.out.println(System.lineSeparator() + "Список №4: " + arrayList4.toString());
        System.out.print("Введите индекс, с которого начать добавление списка: ");
        Scanner scanner3 = new Scanner(System.in);
        int index1 = scanner3.nextInt();
        System.out.println("Добавлен ли список №4 в конец списка №1: " + arrayList1.addAll(index1, arrayList4));
        System.out.println("Список №1 после изменений: " + arrayList1.toString());


        ArrayList<Integer> arrayList5 = new ArrayList<>();
        arrayList5.add(0);
        arrayList5.add(3);
        arrayList5.add(8);
        arrayList5.add(6);
        arrayList5.add(null);


        System.out.println(System.lineSeparator() + "Список №5: " + arrayList5.toString());
        System.out.println("Удалены в списке №1 лишь элементы входящие в список №5: " + arrayList1.removeAll(arrayList5));
        System.out.println("Список №1 после изменений: " + arrayList1.toString());


        ArrayList<Integer> arrayList6 = new ArrayList<>();
        arrayList6.add(null);
        arrayList6.add(4);
        arrayList6.add(8);
        arrayList6.add(9);
        arrayList6.add(5);


        System.out.println(System.lineSeparator() + "Список №6: " + arrayList6.toString());
        System.out.println("Сохранены в списке №1 лишь элементы входящие в список №6: " + arrayList1.retainAll(arrayList6));
        System.out.println("Список №1 после изменений: " + arrayList1.toString());


        System.out.print(System.lineSeparator() + "Введите индекс для получения значения элемента: ");
        Scanner scanner4 = new Scanner(System.in);
        int index2 = scanner4.nextInt();
        System.out.println("Значение элемента под идексом \"" + index2 + "\": " + arrayList1.get(index2));


        System.out.print(System.lineSeparator() + "Введите индекс для изменения значения элемента: ");
        Scanner scanner5 = new Scanner(System.in);
        int index3 = scanner5.nextInt();
        System.out.print("Введите значение элемента: ");
        Scanner scanner6 = new Scanner(System.in);
        Integer number3 = scanner6.nextInt();

        arrayList1.set(index3, number3);
        System.out.println("Список №1 после изменения значения: " + arrayList1.toString());


        System.out.print(System.lineSeparator() + "Введите индекс для добавления значения элемента: ");
        Scanner scanner7 = new Scanner(System.in);
        int index4 = scanner7.nextInt();
        System.out.print("Введите значение элемента: ");
        Scanner scanner8 = new Scanner(System.in);
        Integer number4 = scanner8.nextInt();

        arrayList1.add(index4, number4);
        System.out.println("Список №1 после изменения значения: " + arrayList1.toString());


        System.out.print(System.lineSeparator() + "Введите индекс для удаления элемента: ");
        Scanner scanner9 = new Scanner(System.in);
        int index5 = scanner9.nextInt();

        System.out.println("Элемент со значением \"" + arrayList1.remove(index5) + "\" удален");
        System.out.println("Список №1 после удаления значения: " + arrayList1.toString());


        System.out.print(System.lineSeparator() + "Введите значение элемента для поиска по списку: ");
        Scanner scanner10 = new Scanner(System.in);
        Integer number5 = scanner10.nextInt();

        System.out.println("Индекс первого вхождения элемента с этим значением: " + arrayList1.indexOf(number5));


        System.out.print(System.lineSeparator() + "Введите значение элемента для поиска по списку: ");
        Scanner scanner11 = new Scanner(System.in);
        Integer number6 = scanner11.nextInt();

        System.out.println("Индекс последнего вхождения элемента с этим значением: " + arrayList1.lastIndexOf(number6));


        Iterator<Integer> iterator1 = arrayList1.iterator();
        System.out.print(System.lineSeparator() + "Проверка итератора: ");
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }


        ListIterator<Integer> listIterator1 = arrayList1.listIterator(0);
        System.out.print(System.lineSeparator() + "Проверка итератора списка: ");
        while (listIterator1.hasNext()) {
            System.out.print(listIterator1.next() + " ");
        }


        arrayList1.clear();
        System.out.println(System.lineSeparator() + "Список №1 после очистки элементов: " + arrayList1.toString());
    }
}
