package shelest.matrix.matrix;

import shelest.vector.operations.Vector;

public class Matrix {
    private Vector[] vectorRow;

    public Matrix(int countRows, int countColumns) {
        getExclusionUnacceptableSize(countRows);
        getExclusionUnacceptableSize(countColumns);
        this.vectorRow = new Vector[countRows];
        Vector vector = new Vector(countColumns);
        for (int i = 0; i < countRows; i++) {
            this.vectorRow[i] = vector;
        }
    }

    public Matrix(Matrix matrix) {
        getExclusionUnacceptableSize(matrix.vectorRow.length);
        this.vectorRow = matrix.vectorRow;
    }

    public Matrix(double[][] array) {
        getExclusionUnacceptableSize(array.length);
        this.vectorRow = new Vector[array.length];
        int maxSizeRowArray = 0;
        for (double[] anArray : array) {
            maxSizeRowArray = Math.max(maxSizeRowArray, anArray.length);
        }
        for (int i = 0; i < array.length; i++) {
            Vector vector = new Vector(maxSizeRowArray, array[i]);
            this.vectorRow[i] = vector;
        }
    }

    public Matrix(Vector[] arrayVectors) {
        getExclusionUnacceptableSize(arrayVectors.length);
        this.vectorRow = new Vector[arrayVectors.length];
        int maxSizeVector = 0;
        for (Vector arrayVector : arrayVectors) {
            maxSizeVector = Math.max(maxSizeVector, arrayVector.getSize());
        }
        Vector vectorNull = new Vector(maxSizeVector);
        for (int i = 0; i < arrayVectors.length; i++) {
            this.vectorRow[i] = Vector.getAddition(vectorNull, arrayVectors[i]);
        }
    }

    private static int getExclusionUnacceptableSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Введен отрицательный или нулевой размер");
        }
        return size;
    }

    private void getExclusionDimensionSizeRow(int indexRow) {
        if (indexRow > this.vectorRow[0].getSize() || indexRow < 0) {
            throw new IllegalArgumentException("Некорректный размер строки");
        }
    }

    private void getExclusionDimensionSizeColumn(int indexColumn) {
        if (indexColumn > this.vectorRow.length || indexColumn < 0) {
            throw new IllegalArgumentException("Некорректный размер столбца");
        }
    }

    public int getSizeRow() {
        return this.vectorRow[0].getSize();
    }

    public int getSizeColumn() {
        return this.vectorRow.length;

    }

    public void setComponent(int indexRow, int indexColumn, double component) {
        getExclusionDimensionSizeRow(indexRow);
        getExclusionDimensionSizeColumn(indexColumn);
        Vector vector = new Vector(this.vectorRow[indexRow]);
        vector.setComponent(indexColumn, component);
        this.vectorRow[indexRow] = vector;
    }

    public double getComponent(int indexRow, int indexColumn) {
        getExclusionDimensionSizeRow(indexRow);
        getExclusionDimensionSizeColumn(indexColumn);
        return this.vectorRow[indexRow].getComponent(indexColumn);
    }

    public Vector getVectorColumn(int indexRow) {
        getExclusionDimensionSizeRow(indexRow);
        Vector vectorColumn = new Vector(this.vectorRow.length);
        for (int i = 0; i < this.vectorRow.length; i++) {
            vectorColumn.setComponent(i, this.getComponent(i, indexRow));
        }
        return vectorColumn;
    }

    public Matrix getTranspose() {
        Matrix transposeMatrix = new Matrix(this.getSizeRow(), this.getSizeColumn());
        int maxSize = Math.max(transposeMatrix.getSizeColumn(), transposeMatrix.getSizeRow());
        for (int i = 0; i < maxSize; i++) {
            transposeMatrix.vectorRow[i] = this.getVectorColumn(i);
        }
        this.vectorRow = transposeMatrix.vectorRow;
        return this;
    }

    public Matrix getMultiplicationByScalar(double scalar) {
        for (Vector component : this.vectorRow) {
            component.getMultiplicationByScalar(scalar);
        }
        return this;
    }

    public double getDeterminant(Matrix matrix) {
        double determinant = 0;
        int sizeLineMatrix = matrix.vectorRow[0].getSize();
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
                determinant += coefficient * matrix.getComponent(0, i) * getDeterminant(getMinor(matrix, 0, i));
            }
        }
        return determinant;
    }

    private static Matrix getMinor(Matrix matrix, int row, int column) {
        int minorSize = matrix.vectorRow.length - 1;
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
        for (Vector component : this.vectorRow) {
            for (int j = 0; j < vectorSize; j++) {
                component.setComponent(j, component.getComponent(j) * vector.getComponent(j));
            }
        }
        return this;
    }

    public Matrix getAddition(Matrix matrix) {
        int sizeColumn = this.getSizeColumn();
        for (int i = 0; i < sizeColumn; i++) {
            this.vectorRow[i] = Vector.getAddition(this.vectorRow[i], matrix.vectorRow[i]);
        }
        return this;
    }

    public Matrix getSubtraction(Matrix matrix) {
        int sizeColumn = this.getSizeColumn();
        for (int i = 0; i < sizeColumn; i++) {
            this.vectorRow[i] = Vector.getSubtraction(this.vectorRow[i], matrix.vectorRow[i]);
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
        int sizeColumn1 = matrix1.getSizeColumn();
        int sizeRow1 = matrix1.getSizeRow();
        int sizeColumn2 = matrix2.getSizeColumn();
        int sizeRow2 = matrix2.getSizeRow();
        Matrix resultMultiplication = new Matrix(Math.max(sizeColumn1, sizeColumn2), Math.max(sizeRow1, sizeRow2));
        for (int i = 0; i < sizeColumn1; i++) {
            for (int j = 0; j < sizeRow1; j++) {
                resultMultiplication.setComponent(i, j, matrix1.vectorRow[i].getComponent(j));
            }
        }
        for (int i = 0; i < sizeColumn2; i++) {
            for (int j = 0; j < sizeRow2; j++) {
                double componentsMultiplication = resultMultiplication.vectorRow[i].getComponent(j) * matrix2.vectorRow[i].getComponent(j);
                resultMultiplication.setComponent(i, j, componentsMultiplication);
            }
        }
        return resultMultiplication;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("{");
        for (Vector component : this.vectorRow) {
            line.append(component.toString());
            line.append(",");
        }
        line.delete(line.length() - 2, line.length());
        line.append("}");
        return line.toString();
    }

}
