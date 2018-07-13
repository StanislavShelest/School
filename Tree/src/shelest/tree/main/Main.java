package shelest.tree.main;

import shelest.tree.operations.Tree;

import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = (value1, value2) -> value2 - value1;
        Consumer<Integer> print = value -> System.out.print(value + " ");

        Tree<Integer> tree = new Tree<>(54, comparator);
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

        System.out.println(System.lineSeparator() + "Количество элементов в дереве: " + tree.getElementsCount());

        System.out.print(System.lineSeparator() + "Ряд значений полученный обходом в ширину: ");
        tree.bypassWidth(print);
        System.out.println();

        System.out.print(System.lineSeparator() + "Ряд значений полученный обходом в глубину с рекурсией: ");
        tree.bypassDepthRecursion(tree.getRoot(), print);
        System.out.println();

        System.out.print(System.lineSeparator() + "Ряд значений полученный обходом в глубину без рекурсии: ");
        tree.bypassDepth(print);
    }

}
