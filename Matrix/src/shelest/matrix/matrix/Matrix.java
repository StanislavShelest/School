package shelest.matrix.matrix;

import shelest.vector.operations.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Введена некорректная размерность матрицы");
        }
        this.rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            Vector vector = new Vector(columnsCount);
            this.rows[i] = vector;
        }
    }

    public Matrix(Matrix matrix) {
        int rowsCount = matrix.rows.length;
        int columnsCount = matrix.rows[0].getSize();
        this.rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            Vector vector = new Vector(matrix.rows[i].getSize());
            this.rows[i] = vector;
        }
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                this.setComponent(i, j, matrix.getComponent(i, j));
            }
        }
    }

    public Matrix(double[][] array) {
        int arrayLength = array.length;
        if (arrayLength == 0) {
            throw new IllegalArgumentException("Передан нулевой массив");
        }
        this.rows = new Vector[arrayLength];
        int arrayColumnsMaxCount = 0;
        for (double[] row : array) {
            arrayColumnsMaxCount = Math.max(arrayColumnsMaxCount, row.length);
        }
        if (arrayColumnsMaxCount == 0) {
            throw new IllegalArgumentException("Передан массив с нулевыми элементами");
        }
        for (int i = 0; i < array.length; i++) {
            Vector vector = new Vector(arrayColumnsMaxCount, array[i]);
            this.rows[i] = vector;
        }
    }

    public Matrix(Vector[] arrayVectors) {
        if (arrayVectors.length == 0) {
            throw new IllegalArgumentException("Передан нулевой массив векторов");
        }
        this.rows = new Vector[arrayVectors.length];
        int vectorMaxSize = 0;

        for (Vector vector : arrayVectors) {
            vectorMaxSize = Math.max(vectorMaxSize, vector.getSize());
        }
        Vector vectorNull = new Vector(vectorMaxSize);
        for (int i = 0; i < arrayVectors.length; i++) {
            this.rows[i] = Vector.getAddition(vectorNull, arrayVectors[i]);
        }
    }

    private static void checkIncorrectMatrixSizes(int rowsCount1, int columnsCount1, int rowsCount2, int columnsCount2) {
        if (rowsCount1 != rowsCount2 || columnsCount1 != columnsCount2) {
            throw new IllegalArgumentException("Введены разные по размерности матрицы");
        }
    }

    public static Matrix getAddition(Matrix matrix1, Matrix matrix2) {
        checkIncorrectMatrixSizes(matrix1.rows.length, matrix1.rows[0].getSize(), matrix2.rows.length, matrix2.rows[0].getSize());
        Matrix resultAddition = new Matrix(matrix1);
        return resultAddition.getAddition(matrix2);
    }

    public static Matrix getSubtraction(Matrix matrix1, Matrix matrix2) {
        checkIncorrectMatrixSizes(matrix1.rows.length, matrix1.rows[0].getSize(), matrix2.rows.length, matrix2.rows[0].getSize());
        Matrix resultSubtraction = new Matrix(matrix1);
        return resultSubtraction.getSubtraction(matrix2);
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        int rowsCount1 = matrix1.rows.length;
        int rowsCount2 = matrix2.rows.length;
        int columnsCount1 = matrix1.rows[0].getSize();
        int columnsCount2 = matrix2.rows[0].getSize();

        if (columnsCount1 != rowsCount2) {
            throw new IllegalArgumentException("Недопустимые размеры матриц");
        }

        Matrix resultMultiplication = new Matrix(rowsCount1, columnsCount2);

        for (int i = 0; i < rowsCount1; i++) {
            for (int j = 0; j < columnsCount2; j++) {
                double elementMatrixResult = 0;
                for (int k = 0; k < columnsCount1; k++) {
                    elementMatrixResult = elementMatrixResult + matrix1.getComponent(i, k) * matrix2.getComponent(k, j);
                }
                resultMultiplication.setComponent(i, j, elementMatrixResult);
            }
        }
        return resultMultiplication;
    }

    private void checkIncorrectRowIndex(int rowIndex) {
        if (rowIndex >= this.rows.length || rowIndex < 0) {
            throw new IllegalArgumentException("Введен некорректный индекс строки");
        }
    }

    private void checkIncorrectColumnIndex(int columnIndex) {
        if (columnIndex >= this.rows[0].getSize() || columnIndex < 0) {
            throw new IllegalArgumentException("Введен некорректный индекс столбца");
        }
    }

    public int getColumnsCount() {
        return this.rows[0].getSize();
    }

    public int getRowsCount() {
        return this.rows.length;
    }

    public void setComponent(int rowIndex, int columnIndex, double component) {
        checkIncorrectColumnIndex(columnIndex);
        checkIncorrectRowIndex(rowIndex);
        this.rows[rowIndex].setComponent(columnIndex, component);
    }

    public double getComponent(int rowIndex, int columnIndex) {
        checkIncorrectColumnIndex(columnIndex);
        checkIncorrectRowIndex(rowIndex);
        return this.rows[rowIndex].getComponent(columnIndex);
    }

    public void setRow(int rowIndex, Vector row) {
        checkIncorrectRowIndex(rowIndex);
        if (row.getSize() != this.rows[0].getSize()) {
            throw new IllegalArgumentException("Размер переданной строки не совпадает с размерностью матрицы");
        }
        this.rows[rowIndex] = row;
    }

    public Vector getRow(int rowIndex) {
        checkIncorrectRowIndex(rowIndex);
        return this.rows[rowIndex];
    }

    public Vector getColumn(int columnIndex) {
        checkIncorrectColumnIndex(columnIndex);
        int rowCount = this.rows.length;
        Vector vectorColumn = new Vector(rowCount);
        for (int i = 0; i < rowCount; i++) {
            vectorColumn.setComponent(i, this.getComponent(i, columnIndex));
        }
        return vectorColumn;
    }

    public Matrix getTranspose() {
        int columnsCount = this.rows[0].getSize();
        Vector[] arrayTranspose = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; i++) {
            arrayTranspose[i] = this.getColumn(i);
        }
        this.rows = arrayTranspose;
        return this;
    }

    public Matrix getMultiplicationByScalar(double scalar) {
        for (Vector vector : this.rows) {
            vector.getMultiplicationByScalar(scalar);
        }
        return this;
    }

    public double getDeterminant() {
        int rowsCount = this.rows.length;
        int columnsCount = this.rows[0].getSize();

        if (rowsCount != columnsCount) {
            throw new IllegalArgumentException("Введена неквадратная матрица");
        }

        double determinant = 0;
        if (columnsCount == 2) {
            determinant = this.getComponent(0, 0) * this.getComponent(1, 1) - this.getComponent(1, 0) * this.getComponent(0, 1);
        } else {
            int coefficient;
            for (int i = 0; i < columnsCount; i++) {
                if (i % 2 == 1) {
                    coefficient = -1;
                } else {
                    coefficient = 1;
                }
                determinant += coefficient * this.getComponent(0, i) * getMinor(0, i).getDeterminant();
            }
        }
        return determinant;
    }

    private Matrix getMinor(int rowIndex, int columnIndex) {
        checkIncorrectColumnIndex(columnIndex);
        checkIncorrectRowIndex(rowIndex);

        int minorSize = this.rows.length - 1;
        Matrix minor = new Matrix(minorSize, minorSize);
        int rowException = 0;

        for (int i = 0; i <= minorSize; i++) {
            int columnException = 0;
            for (int j = 0; j <= minorSize; j++) {
                if (i == rowIndex) {
                    rowException = 1;
                } else {
                    if (j == columnIndex) {
                        columnException = 1;
                    } else {
                        minor.setComponent(i - rowException, j - columnException, this.getComponent(i, j));
                    }
                }
            }
        }
        return minor;
    }

    public Vector getMultiplicationByVector(Vector vector) {
        int rowsCount = this.rows.length;
        int columnsCount = this.rows[0].getSize();

        if (columnsCount != vector.getSize()){
            throw new IllegalArgumentException("Количество элементов вектора не соответсвует количеству столбцов матрицы");
        }

        Vector vectorResult = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                vectorResult.setComponent(i, vectorResult.getComponent(i) + this.getComponent(i, j) * vector.getComponent(j));
            }
        }
        return vectorResult;
    }

    public Matrix getAddition(Matrix matrix) {
        checkIncorrectMatrixSizes(this.rows.length, this.rows[0].getSize(), matrix.rows.length, matrix.rows[0].getSize());
        int rowsCount = this.rows.length;
        for (int i = 0; i < rowsCount; i++) {
            this.rows[i] = this.rows[i].getAddition(matrix.rows[i]);
        }
        return this;
    }

    public Matrix getSubtraction(Matrix matrix) {
        checkIncorrectMatrixSizes(this.rows.length, this.rows[0].getSize(), matrix.rows.length, matrix.rows[0].getSize());
        int rowsCount = this.rows.length;
        for (int i = 0; i < rowsCount; i++) {
            this.rows[i] = this.rows[i].getSubtraction(matrix.rows[i]);
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("{");
        for (Vector vector : this.rows) {
            line.append(vector.toString());
            line.append(",");
        }
        line.delete(line.length() - 2, line.length());
        line.append("}");
        return line.toString();
    }

}
