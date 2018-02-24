package shelest.range.shelest.range.main;
import shelest.range.Range;

import java.util.*;

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

            System.out.println("Длина первого диапазона составляет: " + range1.getLengthRange());

            if (range1.isInside(number)) {
                System.out.println("Введенное число входит в первый диапазон");
            } else {
                System.out.println("Введенное число не входит в первый диапазон");
            }


            if (range2.getIntersectionRanges(from1, to1, from2, to2) == null) {
                System.out.println("Диапазоны не имеют пересечения");
            } else {
                double[] resultIntersection = range2.getIntersectionRanges(from1, to1, from2, to2);
                if (resultIntersection[0] == resultIntersection[1]) {
                    System.out.println("Результат пересечения диапазонов: " + resultIntersection[0]);
                } else {
                    System.out.println("Результат пересечения диапазонов: от " +  resultIntersection[0] + " до " +  resultIntersection[1]);
                }
            }

            double[] resultUnion = range2.getUnionRange(from1, to1, from2, to2);
            if (resultUnion[2] == 0 & resultUnion[3] == 0) {
                System.out.println("Результат объединение диапазонов: от " +  resultUnion[0] + " до " + resultUnion[1]);
            } else {
                System.out.println("Результат объединение диапазонов: от " +  resultUnion[0] + " до " + resultUnion[1] + " и от " +  resultUnion[2] + " до " + resultUnion[3]);
            }

            double[] resultDifference = range2.getDifferenceRange(from1, to1, from2, to2);
            System.out.print( "Результат разности диапазонов: от " +  resultDifference[0] + " до " + resultDifference[1]);
        }

    }

