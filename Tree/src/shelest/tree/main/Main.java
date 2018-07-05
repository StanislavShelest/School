package shelest.tree.main;

import shelest.tree.operations.Tree;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(54);
        tree.add(23);
        tree.add(43);
        tree.add(78);
        tree.add(98);
        tree.add(76);
        tree.add(13);
        tree.add(40);
        tree.add(42);


        System.out.print("Введите число для поиска по дереву: ");
        Scanner scanner1 = new Scanner(System.in);
        Integer number1 = scanner1.nextInt();
        System.out.println("Содержит ли дерево введенное число: " + tree.search(number1));

        System.out.print(System.lineSeparator() + "Введите число для удаления из дерева: ");
        Scanner scanner2 = new Scanner(System.in);
        Integer number2 = scanner2.nextInt();
        System.out.println("Удалено ли число из дерева: " + tree.remove(number2));

        System.out.println(System.lineSeparator() + "Количество элементов в дереве: " + tree.getCountElement());

        System.out.println(System.lineSeparator() + "Массив полученный обходом в ширину: " + Arrays.toString(tree.bypassWidthArray()));

        System.out.println(System.lineSeparator() + "Список полученный обходом в глубину с рекурсией: " + tree.bypassDepthList());

        System.out.println(System.lineSeparator() + "Массив полученный обходом в глубину без рекурсии: " + Arrays.toString(tree.bypassDepthArray()));
    }
}
