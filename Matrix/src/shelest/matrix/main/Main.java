package shelest.matrix.main;

import shelest.matrix.matrix.Matrix;
import shelest.vector.operations.Vector;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Matrix matrix1 = new Matrix(5, 6);
        System.out.println("Матрица номер 1:" + matrix1.toString());

        System.out.println("Размер строки первой матрицы: " + matrix1.getColumnsCount());

        System.out.println("Размер столбца первой матрицы: " + matrix1.getRowsCount());

        System.out.print("Введите номер строки матрицы для ввода значения: ");
        Scanner scanner1 = new Scanner(System.in);
        int numberRowEnter = scanner1.nextInt();

        System.out.print("Введите номер элемента в векторе для ввода значения: ");
        Scanner scanner2 = new Scanner(System.in);
        int numberColumnEnter = scanner2.nextInt();

        System.out.print("Введите значение элемента: ");
        Scanner scanner3 = new Scanner(System.in);
        double elementEnter = scanner3.nextDouble();

        matrix1.setComponent(numberRowEnter, numberColumnEnter, elementEnter);
        System.out.println("Матрица номер 1 после изменения значения: " + matrix1.toString());

        System.out.print("Введите номер строки для ее замены: ");
        Scanner scanner4 = new Scanner(System.in);
        int numberRow = scanner4.nextInt();

        double[] array1 = {12, 13, 14, 15, 16, 17};
        Vector vector1 = new Vector(array1);

        matrix1.setRow(numberRow, vector1);
        System.out.println("Матрица номер 1 после изменения строки: " + matrix1.toString());

        System.out.println("В матрице номер 1 в строке номер " + numberRowEnter + " элементом под номером " +
                numberColumnEnter + " является: " + matrix1.getComponent(numberRowEnter, numberColumnEnter));

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println(System.lineSeparator() + "Матрица номер 2:" + matrix2.toString());

        double[][] arrayTwoDimensional1 = {{2, 3, 4}, {12, 11, 13, 12}, {8, 5, 3, 14}};
        Matrix matrix3 = new Matrix(arrayTwoDimensional1);
        System.out.println(System.lineSeparator() + "Матрица номер 3:" + matrix3.toString());

        System.out.print("Введите номер столбца для получения вектора-столбца: ");
        Scanner scanner5 = new Scanner(System.in);
        int numberColumn = scanner5.nextInt();

        System.out.println("Вектор-столбец под номером " + numberColumn + " матрицы номер 3: " + matrix3.getColumn(numberColumn).toString());

        System.out.print("Введите номер строки для ее вывода: ");
        Scanner scanner6 = new Scanner(System.in);
        int numberRowOutput = scanner6.nextInt();

        System.out.println("Строка под номером " + numberRowOutput + " матрицы номер 3: " + matrix3.getRow(numberRowOutput).toString());

        System.out.println("Матрица номер 3 после транспонирования: " + matrix3.getTranspose().toString());

        double[] array2 = {23, 45, 67};
        double[] array3 = {43, 54};
        double[] array4 = {98, 12, 32};
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);
        Vector vector4 = new Vector(array4);
        Vector[] arrayVectors = new Vector[3];
        arrayVectors[0] = vector2;
        arrayVectors[1] = vector3;
        arrayVectors[2] = vector4;
        Matrix matrix4 = new Matrix(arrayVectors);
        System.out.println(System.lineSeparator() + "Матрица номер 4:" + matrix4.toString());

        System.out.print("Введите скаляр для умножения матрицы: ");
        Scanner scanner7 = new Scanner(System.in);
        double scalar = scanner7.nextDouble();

        System.out.println("Матрица номер 4 после умножения на скаляр " + scalar + ": " + matrix4.getMultiplicationByScalar(scalar).toString());

        double[][] arrayTwoDimensional2 = {{2, 3, 4, 67, 13, 2}, {3, 7, 12, 15, 13, 3}, {5, 6, 7, 3, 2, 5}, {12, 11, 13, 45, 5, 8}, {2, 4, 5, 6, 2, 9}, {3, 4, 1, 9, 10, 12}};
        Matrix matrix5 = new Matrix(arrayTwoDimensional2);
        System.out.println(System.lineSeparator() + "Матрица номер 5:" + matrix5.toString());

        System.out.println("Определитель матрицы номер 5 равен: " + matrix5.getDeterminant());

        double[] array5 = {2, 3, 4, 2};
        Vector vector5 = new Vector(array5);

        double[][] arrayTwoDimensional3 = {{23, 43, 65, 78}, {12, 54, 76, 43}, {78, 98, 65, 23}};
        Matrix matrix6 = new Matrix(arrayTwoDimensional3);
        System.out.println(System.lineSeparator() + "Матрица номер 6:" + matrix6.toString());

        System.out.println("Результат произведения матрицы номер 6 на вектор " + vector5.toString() + ": " + matrix6.getMultiplicationByVector(vector5));

        double[][] arrayTwoDimensional4 = {{2, 5, 6,}, {5, 7, 9, 10, 12}, {7, 5, 4}};
        Matrix matrix7 = new Matrix(arrayTwoDimensional4);
        System.out.println(System.lineSeparator() + "Матрица номер 7:" + matrix7.toString());

        double[][] arrayTwoDimensional5 = {{2, 3, 4}, {3, 2, 4, 5, 3}, {2, 5, 3}};
        Matrix matrix8 = new Matrix(arrayTwoDimensional5);
        System.out.println("Матрица номер 8:" + matrix8.toString());

        System.out.println("Результат сложения матрицы 7 и матрицы 8: " + matrix7.getAddition(matrix8).toString());

        double[][] arrayTwoDimensional6 = {{1, 2, 3}, {4, 5, 6, 7, 2}, {2, 3, 4}};
        Matrix matrix9 = new Matrix(arrayTwoDimensional6);
        System.out.println(System.lineSeparator() + "Матрица номер 8:" + matrix8.toString());
        System.out.println("Матрица номер 9:" + matrix9.toString());

        System.out.println("Результат вычитания из матрицы 8 матрицы 9: " + matrix8.getSubtraction(matrix9).toString());

        double[][] arrayTwoDimensional7 = {{3, 4, 5, 1}, {4, 3, 2, 1}, {2, 3, 4, 7}};
        Matrix matrix10 = new Matrix(arrayTwoDimensional7);
        System.out.println(System.lineSeparator() + "Матрица номер 10:" + matrix10.toString());

        double[][] arrayTwoDimensional8 = {{8, 7, 5, 2}, {6, 4, 3, 2}, {6, 5, 4, 0}};
        Matrix matrix11 = new Matrix(arrayTwoDimensional8);
        System.out.println("Матрица номер 11:" + matrix11.toString());

        System.out.println("Результат сложения статическим методом матриц 10 и 11: " + Matrix.getAddition(matrix10, matrix11).toString());

        double[][] arrayTwoDimensional9 = {{3, 4, 5, 1}, {4, 3, 2, 1}, {2, 3, 4, 7}};
        Matrix matrix12 = new Matrix(arrayTwoDimensional9);
        System.out.println(System.lineSeparator() + "Матрица номер 11:" + matrix11.toString());
        System.out.println("Матрица номер 12:" + matrix12.toString());

        System.out.println("Результат вычитания статическим методом из матрицы 11 матрицы 12: " + Matrix.getSubtraction(matrix11, matrix12).toString());

        double[][] arrayTwoDimensional10 = {{8, 7, 5}, {6, 4, 3}, {3, 6, 9}, {2, 0, 7}};
        Matrix matrix13 = new Matrix(arrayTwoDimensional10);
        System.out.println(System.lineSeparator() + "Матрица номер 12:" + matrix12.toString());
        System.out.println("Матрица номер 13:" + matrix13.toString());

        System.out.println("Результат умножения статическим методом матриц 12 и 13: " + Matrix.getMultiplication(matrix12, matrix13).toString());
    }
}
