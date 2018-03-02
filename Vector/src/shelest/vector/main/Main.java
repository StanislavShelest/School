package shelest.vector.main;

import shelest.vector.operations.Vector;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double[] componentsArray1 = {5, -7, 8.3, 5, 12};
        double[] componentsArray2 = {23, 45, 78, 12, -14, 98.8};
        Vector vector1 = new Vector(8);
        Vector vector2 = new Vector(componentsArray1);
        Vector vector3 = new Vector(vector2);
        Vector vector4 = new Vector(vector2);
        Vector vector5 = new Vector(8, componentsArray2);

        System.out.print("Введите скаляр: ");
        Scanner scanner1 = new Scanner(System.in);
        double scalar = scanner1.nextDouble();

        System.out.print("Введите индекс компонента: ");
        Scanner scanner2 = new Scanner(System.in);
        int index = scanner2.nextInt();

        System.out.print("Введите компонент: ");
        Scanner scanner3 = new Scanner(System.in);
        double addedComponent = scanner3.nextDouble();

        System.out.println("1. " + vector1.toString());
        System.out.println("2. " + vector2.toString());
        System.out.println("3. " + vector3.toString());
        System.out.println("4. " + vector3.toString());
        System.out.println("5. " + vector5.toString());

        System.out.println(System.lineSeparator() + "Длина третьего вектора составляет: " + vector3.getSize());
        System.out.println("Результат сложения второго и пятого векторов: " + vector2.getAddition(vector5).toString());
        System.out.println("Результат разности второго и пятого векторов: " + vector2.getSubtraction(vector5).toString());
        System.out.println("Результат умножения второго вектора на 2: " + vector2.getMultiplicationByScalar(scalar).toString());
        System.out.println("Результат разворота второго вектора: " + vector2.getTurn().toString());
        System.out.println("Длина второго вектора: " + vector2.getLength());
        System.out.println("Результат замены компонента пятого вектора: " + vector5.getInstallationComponent(index, addedComponent));
        System.out.println("Результат равенства второго и четвертого вектора: " + vector2.equals(vector4));
        System.out.println("hashCode второго вектора: " + vector2.hashCode());
        System.out.println("hashCode четвертого вектора: " + vector4.hashCode());
    }
}
