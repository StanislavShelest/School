package shelest.vector.main;

import shelest.vector.operations.Vector;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите размерность: ");
        Scanner scanner1 = new Scanner(System.in);
        int dimension = scanner1.nextInt();

        System.out.print("Введите скаляр: ");
        Scanner scanner2 = new Scanner(System.in);
        double scalar = scanner2.nextDouble();

        System.out.print("Введите индекс компонента: ");
        Scanner scanner3 = new Scanner(System.in);
        int index = scanner3.nextInt();

        System.out.print("Введите компонент: ");
        Scanner scanner4 = new Scanner(System.in);
        double addedComponent = scanner4.nextDouble();

        double[] componentsArray1 = {5, -7, 8.3, 5, 12};
        double[] componentsArray2 = {23, 45, 78, 12, -14, 98.8};
        double[] componentsArray3 = {34, 34, 35, 90, 78, 54, 23};
        double[] componentsArray4 = {32, 55, 66, 65, 23};
        double[] componentsArray5 = {65, 767, 54, -98, 90, 100};
        double[] componentsArray6 = {12, 54, 623, 82, 84, 59};

        Vector vector1 = new Vector(componentsArray1);
        Vector vector2 = new Vector(componentsArray2);
        Vector vector3 = new Vector(componentsArray3);
        Vector vector4 = new Vector(componentsArray4);
        Vector vector5 = new Vector(componentsArray5);
        Vector vector6 = new Vector(componentsArray6);
        Vector vector7 = new Vector(8);
        Vector vector8 = new Vector(vector6);
        Vector vector9 = new Vector(dimension, componentsArray1);

        System.out.println("1. " + vector1.toString());
        System.out.println("2. " + vector2.toString());
        System.out.println("3. " + vector3.toString());
        System.out.println("4. " + vector4.toString());
        System.out.println("5. " + vector5.toString());
        System.out.println("6. " + vector6.toString());
        System.out.println("7. " + vector7.toString());
        System.out.println("8. " + vector8.toString());
        System.out.println("9. " + vector9.toString());

        System.out.println(System.lineSeparator() + "Размерность первого вектора составляет: " + vector1.getSize());
        System.out.println("Результат сложения первого и второго векторов: " + vector1.getAddition(vector2).toString());
        System.out.println("Результат разности второго и третьего векторов: " + vector2.getSubtraction(vector3).toString());
        System.out.println("Результат умножения третьего вектора на 2: " + vector3.getMultiplicationByScalar(scalar).toString());
        System.out.println("Результат разворота четвертого вектора: " + vector4.getTurn().toString());
        System.out.println("Длина пятого вектора: " + vector5.getLength());
        vector5.setComponents(index, addedComponent);
        System.out.println("Результат замены компонента пятого вектора: " + vector5.toString());
        System.out.println("Результат равенства шестого и восьмого векторов: " + vector6.equals(vector8));
        System.out.println("hashCode шестого вектора: " + vector6.hashCode());
        System.out.println("hashCode восьмого вектора: " + vector8.hashCode());
    }
}
