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
            this.setRow(i, vector);
        }
    }

    public Matrix(Matrix matrix) {
        int rowsCount = matrix.getRowsCount();
        int columnsCount = matrix.getColumnsCount();
        this.rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            Vector vector = new Vector(matrix.getRow(i).getSize());
            this.setRow(i, vector);
        }
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                this.setComponent(i, j, matrix.getComponent(i, j));
            }
        }
    }

    public Matrix(double[][] arrayTwoDimension) {
        int arrayLength = arrayTwoDimension.length;
        if (arrayLength == 0) {
            throw new IllegalArgumentException("Передан нулевой массив");
        }

        for (double[] array : arrayTwoDimension) {
            if (array.length == 0) {
                throw new IllegalArgumentException("Передан массив с нулевым элементом");
            }
        }

        this.rows = new Vector[arrayLength];
        int arrayColumnsMaxCount = 0;
        for (double[] row : arrayTwoDimension) {
            arrayColumnsMaxCount = Math.max(arrayColumnsMaxCount, row.length);
        }
        for (int i = 0; i < arrayTwoDimension.length; i++) {
            Vector vector = new Vector(arrayColumnsMaxCount, arrayTwoDimension[i]);
            this.setRow(i, vector);
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
            this.setRow(i, Vector.getAddition(vectorNull, arrayVectors[i]));
        }
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
        int rowsCount1 = matrix1.getRowsCount();
        int rowsCount2 = matrix2.getRowsCount();
        int columnsCount1 = matrix1.getColumnsCount();
        int columnsCount2 = matrix2.getColumnsCount();

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

    private void excludeIncorrectRowIndex(int rowIndex) {
        if (rowIndex > this.getRowsCount() || rowIndex < 0) {
            throw new IllegalArgumentException("Введен некорректный индекс строки");
        }
    }

    private void excludeIncorrectColumnIndex(int columnIndex) {
        if (columnIndex > this.getColumnsCount() || columnIndex < 0) {
            throw new IllegalArgumentException("Введен некорректный индекс столбца");
        }
    }

    private void excludeIncorrectMatrixsSizes(int rowsCount1, int columnsCount1, int rowsCount2, int columnsCount2) {
        if (rowsCount1 != rowsCount2 || columnsCount1 != columnsCount2) {
            throw new IllegalArgumentException("Введены разные по размерности матрицы");
        }
    }

    private void excludeMatrixNotSquare(int rowsCount, int columnsCount) {
        if (rowsCount != columnsCount) {
            throw new IllegalArgumentException("Введена неквадратная матрица");
        }
    }

    public int getColumnsCount() {
        return this.getRow(0).getSize();
    }

    public int getRowsCount() {
        return this.rows.length;
    }

    public void setComponent(int rowIndex, int columnIndex, double component) {
        excludeIncorrectColumnIndex(columnIndex);
        excludeIncorrectRowIndex(rowIndex);
        this.rows[rowIndex].setComponent(columnIndex, component);
    }

    public double getComponent(int rowIndex, int columnIndex) {
        excludeIncorrectColumnIndex(columnIndex);
        excludeIncorrectRowIndex(rowIndex);
        return this.rows[rowIndex].getComponent(columnIndex);
    }

    public void setRow(int rowIndex, Vector row) {
        this.rows[rowIndex] = row;
    }

    public Vector getRow(int indexRow) {
        return this.rows[indexRow];
    }

    public Vector getVectorColumn(int columnIndex) {
        excludeIncorrectColumnIndex(columnIndex);
        int rowCount = this.getRowsCount();
        Vector vectorColumn = new Vector(rowCount);
        for (int i = 0; i < rowCount; i++) {
            vectorColumn.setComponent(i, this.getComponent(i, columnIndex));
        }
        return vectorColumn;
    }

    public Matrix getTranspose() {
        int rowsCount = this.getRowsCount();
        int columnsCount = this.getColumnsCount();

        excludeMatrixNotSquare(rowsCount, columnsCount);

        for (int i = 0; i < columnsCount; i++) {
            for (int j = i + 1; j < rowsCount; j++) {
                double temp = this.getComponent(i, j);
                this.setComponent(i, j, this.getComponent(j, i));
                this.setComponent(j, i, temp);
            }
        }
        return this;
    }

    public Matrix getMultiplicationByScalar(double scalar) {
        for (Vector vector : this.rows) {
            vector.getMultiplicationByScalar(scalar);
        }
        return this;
    }

    public double getDeterminant() {
        int rowsCount = this.getRowsCount();
        int columnsCount = this.getColumnsCount();

        excludeMatrixNotSquare(rowsCount, columnsCount);

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
        excludeIncorrectColumnIndex(columnIndex);
        excludeIncorrectRowIndex(rowIndex);

        int minorSize = this.getRowsCount() - 1;
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
        int rowsCount = this.getRowsCount();
        int columnsCount = this.getColumnsCount();

        Vector vectorResult = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                vectorResult.setComponent(i, vectorResult.getComponent(i) + this.getComponent(i, j) * vector.getComponent(j));
            }
        }
        return vectorResult;
    }

    public Matrix getAddition(Matrix matrix) {
        excludeIncorrectMatrixsSizes(this.getRowsCount(), this.getColumnsCount(), matrix.getRowsCount(), matrix.getColumnsCount());
        int rowsCount = this.getRowsCount();
        for (int i = 0; i < rowsCount; i++) {
            this.setRow(i, this.getRow(i).getAddition(matrix.getRow(i)));
        }
        return this;
    }

    public Matrix getSubtraction(Matrix matrix) {
        excludeIncorrectMatrixsSizes(this.getRowsCount(), this.getColumnsCount(), matrix.getRowsCount(), matrix.getColumnsCount());
        int rowsCount = this.getRowsCount();
        for (int i = 0; i < rowsCount; i++) {
            this.setRow(i, this.getRow(i).getSubtraction(matrix.getRow(i)));
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
