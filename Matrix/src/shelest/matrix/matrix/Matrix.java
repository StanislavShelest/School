package shelest.matrix.matrix;

import shelest.vector.operations.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] components;

    public Matrix(int lines, int columns) {
        this.components = new Vector[lines];
        Vector vector = new Vector(columns);
        for (int i = 0; i < lines; i++) {
            components[i] = vector;
        }
    }

    public Matrix(Matrix matrix) {
        this.components = matrix.components;
    }

    public Matrix(double[][] array) {
        this.components = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            Vector vector = new Vector(array[i]);
            this.components[i] = vector;
        }
    }

    public Matrix(Vector[] arrayVectors) {
        this.components = new Vector[arrayVectors.length];
        System.arraycopy(arrayVectors, 0, this.components, 0, arrayVectors.length);
    }

    public int[] getSize() {
        int[] size = new int[2];
        size[0] = this.components.length;
        size[1] = this.components[0].getSize();
        return size;
    }

    public void setComponent(int line, int column, double component) {
        Vector vector = new Vector(this.components[line]);
        vector.setComponent(column, component);
        this.components[line] = vector;
    }

    public double getComponent(int line, int column) {
        return this.components[line].getComponent(column);
    }

    public double[] getVectorColumn(int column) {
        double[] vectorColumn = new double[this.components.length];
        for (int i = 0; i < this.components.length; i++) {
            vectorColumn[i] = this.getComponent(i, column);
        }
        return vectorColumn;
    }

    public Matrix getTranspose() {
        Matrix transposeMatrix = new Matrix(this.components[0].getSize(), this.components.length);
        for (int i = 0; i < transposeMatrix.components.length; i++) {
            for (int j = 0; j < transposeMatrix.components[i].getSize(); j++) {
                transposeMatrix.setComponent(i, j, this.getComponent(j, i));
            }
        }
        return transposeMatrix;
    }

    public Matrix getMultiplicationByScalar(double scalar) {
        for (Vector component : this.components) {
            component.getMultiplicationByScalar(scalar);
        }
        return this;
    }

    public double getDeterminant(Matrix matrix) {
        double determinant = 0;
        int sizeLineMatrix = matrix.components[0].getSize();
        if (sizeLineMatrix == 2) {
            determinant = matrix.getComponent(0, 0) * matrix.getComponent(1, 1) - matrix.getComponent(1, 0) * matrix.getComponent(0, 1);
        } else {
            int coefficient;
            for (int i = 0; i < sizeLineMatrix; i++) {
                if (i % 2 == 1) {
                    coefficient = -1;
                } else {
                    coefficient = 1;
                }
                determinant += coefficient * matrix.getComponent(0, i) * getDeterminant(GetMinor(matrix, 0, i));
            }
        }
        return determinant;
    }

    private static Matrix GetMinor(Matrix matrix, int row, int column) {
        int minorSize = matrix.components.length - 1;
        Matrix minor = new Matrix(minorSize, minorSize);
        int exceptionRow = 0;
        for (int i = 0; i <= minorSize; i++) {
            int exceptionColumn = 0;
            for (int j = 0; j <= minorSize; j++) {
                if (i == row) {
                    exceptionRow = 1;
                } else {
                    if (j == column) {
                        exceptionColumn = 1;
                    } else {
                        minor.setComponent(i - exceptionRow, j - exceptionColumn, matrix.getComponent(i, j));
                    }
                }
            }
        }
        return minor;
    }

    public Matrix getMultiplicationByVector(Vector vector) {
        int vectorSize = vector.getSize();
        for (Vector component : this.components) {
            for (int j = 0; j < vectorSize; j++) {
                component.setComponent(j, component.getComponent(j) * vector.getComponent(j));
            }
        }
        return this;
    }

    public Matrix getAddition(Matrix matrix) {
        int rowSize = this.components.length;
        for (int i = 0; i < rowSize; i++) {
            int columnSize = this.components[i].getSize();
            for (int j = 0; j < columnSize; j++) {
                this.setComponent(i, j, this.getComponent(i, j) + matrix.getComponent(i, j));
            }
        }
        return this;
    }

    public Matrix getSubtraction(Matrix matrix) {
        int rowSize = this.components.length;
        for (int i = 0; i < rowSize; i++) {
            int columnSize = this.components[i].getSize();
            for (int j = 0; j < columnSize; j++) {
                this.setComponent(i, j, this.getComponent(i, j) - matrix.getComponent(i, j));
            }
        }
        return this;
    }

    public static Matrix getAddition(Matrix matrix1, Matrix matrix2) {
        Matrix resultAddition = new Matrix(matrix1);
        return resultAddition.getAddition(matrix2);
    }

    public static Matrix getSubtraction(Matrix matrix1, Matrix matrix2) {
        Matrix resultSubtraction = new Matrix(matrix1);
        return resultSubtraction.getSubtraction(matrix2);
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        Matrix resultMultiplication = new Matrix(matrix1);
        int rowSize = resultMultiplication.components.length;
        for (int i = 0; i < rowSize; i++) {
            int columnSize = resultMultiplication.components[i].getSize();
            for (int j = 0; j < columnSize; j++) {
                resultMultiplication.setComponent(i, j, resultMultiplication.getComponent(i, j) * matrix2.getComponent(i, j));
            }
        }
        return resultMultiplication;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("{");
        for (Vector component : this.components) {
            line.append(component.toString());
            line.append(",");
        }
        line.delete(line.length() - 2, line.length());
        line.append("}");
        return line.toString();
    }

}
