package shelest.range.main;

import shelest.range.operations.Range;

import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.print("Введите начало первого диапазона: ");
        Scanner start1 = new Scanner(System.in);
        double from1 = start1.nextDouble();

        System.out.print("Введите конец первого диапазона: ");
        Scanner end1 = new Scanner(System.in);
        double to1 = end1.nextDouble();

        System.out.print("Введите число для проверки: ");
        Scanner scanner = new Scanner(System.in);
        double number = scanner.nextDouble();

        System.out.print("Введите начало второго диапазона: ");
        Scanner start2 = new Scanner(System.in);
        double from2 = start2.nextDouble();

        System.out.print("Введите конец второго диапазона: ");
        Scanner end2 = new Scanner(System.in);
        double to2 = end2.nextDouble();

        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);

        System.out.println("Длина первого диапазона составляет: " + range1.getLength());

        if (range1.isInside(number)) {
            System.out.println("Введенное число входит в первый диапазон");
        } else {
            System.out.println("Введенное число не входит в первый диапазон");
        }

        if (range1.getIntersection(range2) == null) {
            System.out.println("Диапазоны не имеют пересечения");
        } else {
            Range resultIntersection = range1.getIntersection(range2);
            System.out.println("Результат пересечения диапазонов: от " + resultIntersection.getFrom() + " до " + resultIntersection.getTo());
        }

        Range[] resultUnionArray = (Range[]) Arrays.copyOf(range1.getUnion(range2), range1.getUnion(range2).length);
        if (resultUnionArray.length == 1) {
            Range resultUnion1 = resultUnionArray[0];
            double fromUnion1 = resultUnion1.getFrom();
            double toUnion1 = resultUnion1.getTo();
            System.out.println("Результат объединения равен: от " + fromUnion1 + " до " + toUnion1);
        } else {
            Range resultUnion1 = resultUnionArray[0];
            Range resultUnion2 = resultUnionArray[1];
            double fromUnion1 = resultUnion1.getFrom();
            double toUnion1 = resultUnion1.getTo();
            double fromUnion2 = resultUnion2.getFrom();
            double toUnion2 = resultUnion2.getTo();
            System.out.println("Результат объединения равен: от " + fromUnion1 + " до " + toUnion1 + " и от " + fromUnion2 + " до " + toUnion2);
        }

        Range[] resultDifferenceArray = (Range[]) Arrays.copyOf(range1.getDifference(range2), range1.getDifference(range2).length);
        if (resultDifferenceArray.length == 0) {
            System.out.println("Нет значений");
        } else {
            if (resultDifferenceArray.length == 1) {
                Range resultDifference1 = resultDifferenceArray[0];
                double fromDifference1 = resultDifference1.getFrom();
                double toDifference1 = resultDifference1.getTo();
                System.out.println("Результат разности диапазонов равен: от " + fromDifference1 + " до " + toDifference1);
            } else {
                Range resultDifference1 = resultDifferenceArray[0];
                Range resultDifference2 = resultDifferenceArray[1];
                double fromDifference1 = resultDifference1.getFrom();
                double toDifference1 = resultDifference1.getTo();
                double fromDifference2 = resultDifference2.getFrom();
                double toDifference2 = resultDifference2.getTo();
                System.out.println("Результат разности диапазонов равен: от " + fromDifference1 + " до " + toDifference1 + " и от " + fromDifference2 + " до " + toDifference2);
            }
        }
    }
}



