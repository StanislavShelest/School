package shelest.tree.main;

import shelest.tree.operations.TreeNode;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(54);
        root.add(23);
        root.add(43);
        root.add(78);
        root.add(98);
        root.add(76);
        root.add(13);
        root.add(40);
        root.add(40);


        System.out.print("Введите число для поиска по дереву: ");
        Scanner scanner1 = new Scanner(System.in);
        Integer number1 = scanner1.nextInt();
        System.out.println("Содержит ли дерево введенное число: " + root.search(number1));

        System.out.print(System.lineSeparator() + "Введите число для удаления из дерева: ");
        Scanner scanner2 = new Scanner(System.in);
        Integer number2 = scanner2.nextInt();
        System.out.println("Удалено ли число из дерева: " + root.remove(number2));

        System.out.println(System.lineSeparator() + "Количество элементов в дереве: " + root.getCountElement());

        System.out.println(System.lineSeparator() + "Массив полученный обходом в ширину: " + Arrays.toString(root.bypassWidthArray()));

        System.out.println(System.lineSeparator() + "Список полученный обходом в глубину с рекурсией: " + root.bypassDepthList(root));

        System.out.println(System.lineSeparator() + "Массив полученный обходом в глубину без рекурсии: " + Arrays.toString(root.bypassDepthArray()));
    }
}
