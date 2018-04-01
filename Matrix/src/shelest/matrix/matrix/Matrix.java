package shelest.matrix.matrix;

import shelest.vector.operations.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int countRows, int countColumns) {
        ExclusionUnacceptableSize(countRows);
        ExclusionUnacceptableSize(countColumns);
        this.rows = new Vector[countRows];
        for (int i = 0; i < countRows; i++) {
            Vector vector = new Vector(countColumns);
            this.rows[i] = vector;
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.rows.length];
        for (int i = 0; i < matrix.rows.length; i++) {
            Vector vector = new Vector(matrix.rows[i].getSize());
            this.rows[i] = vector;
        }
        for (int i = 0; i < matrix.rows.length; i++) {
            for (int j = 0; j < matrix.rows[i].getSize(); j++) {
                this.rows[i].setComponent(j, matrix.rows[i].getComponent(j));
            }
        }
    }

    public Matrix(double[][] array) {
        ExclusionUnacceptableSize(array.length);
        this.rows = new Vector[array.length];
        int maxCountColumnArray = 0;
        for (double[] row : array) {
            maxCountColumnArray = Math.max(maxCountColumnArray, row.length);
        }
        for (int i = 0; i < array.length; i++) {
            Vector vector = new Vector(maxCountColumnArray, array[i]);
            this.rows[i] = vector;
        }
    }

    public Matrix(Vector[] arrayVectors) {
        ExclusionUnacceptableSize(arrayVectors.length);
        this.rows = new Vector[arrayVectors.length];
        int maxSizeVector = 0;
        for (Vector arrayVector : arrayVectors) {
            maxSizeVector = Math.max(maxSizeVector, arrayVector.getSize());
        }
        Vector vectorNull = new Vector(maxSizeVector);
        for (int i = 0; i < arrayVectors.length; i++) {
            this.rows[i] = Vector.getAddition(vectorNull, arrayVectors[i]);
        }
    }

    private void ExclusionUnacceptableSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Передана отрицательная или нулевая размерность");
        }
    }

    private void ExclusionDimensionCountColumn(int indexRow) {
        if (indexRow > this.rows[0].getSize() || indexRow < 0) {
            throw new IllegalArgumentException("Некорректный размер строки");
        }
    }

    private void ExclusionDimensionCountRow(int indexColumn) {
        if (indexColumn > this.rows.length || indexColumn < 0) {
            throw new IllegalArgumentException("Некорректный размер столбца");
        }
    }

    private static void ExclusionDimensionMatrix(int dimension1, int dimension2) {
        if (dimension1 != dimension2) {
            throw new IllegalArgumentException("Недопустимые размеры матриц");
        }
    }

    public int getCountColumn() {
        return this.rows[0].getSize();
    }

    public int getCountRow() {
        return this.rows.length;
    }

    public void setComponent(int indexRow, int indexColumn, double component) {
        ExclusionDimensionCountColumn(indexRow);
        ExclusionDimensionCountRow(indexColumn);
        this.rows[indexRow].setComponent(indexColumn, component);
    }

    public double getComponent(int indexRow, int indexColumn) {
        ExclusionDimensionCountColumn(indexRow);
        ExclusionDimensionCountRow(indexColumn);
        return this.rows[indexRow].getComponent(indexColumn);
    }

    public Vector getVectorColumn(int indexRow) {
        ExclusionDimensionCountColumn(indexRow);
        Vector vectorColumn = new Vector(this.rows.length);
        for (int i = 0; i < this.rows.length; i++) {
            vectorColumn.setComponent(i, this.getComponent(i, indexRow));
        }
        return vectorColumn;
    }

    public Matrix getTranspose() {
        Matrix transposeMatrix = new Matrix(this.getCountColumn(), this.getCountRow());
        int maxSize = Math.max(transposeMatrix.getCountRow(), transposeMatrix.getCountColumn());
        for (int i = 0; i < maxSize; i++) {
            transposeMatrix.rows[i] = this.getVectorColumn(i);
        }
        this.rows = transposeMatrix.rows;
        return this;
    }

    public Matrix getMultiplicationByScalar(double scalar) {
        for (Vector component : this.rows) {
            component.getMultiplicationByScalar(scalar);
        }
        return this;
    }

    public double getDeterminant(Matrix matrix) {
        double determinant = 0;
        int sizeLineMatrix = matrix.rows[0].getSize();
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
        matrix.ExclusionDimensionCountColumn(column);
        matrix.ExclusionDimensionCountRow(row);
        int minorSize = matrix.rows.length - 1;
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

    public Vector getMultiplicationByVector(Vector vector) {
        ExclusionUnacceptableSize(vector.getSize());
        Vector resultVector = new Vector(vector.getSize());
        for (int i = 0; i < vector.getSize(); i++) {
            for (int j = 0; j < this.rows.length; j++) {
                resultVector.setComponent(i, resultVector.getComponent(i) + this.getComponent(j, i) * vector.getComponent(i));
            }
        }
        return resultVector;
    }

    public Matrix getAddition(Matrix matrix) {
        int sizeColumn = this.getCountRow();
        for (int i = 0; i < sizeColumn; i++) {
            this.rows[i] = this.rows[i].getAddition(matrix.rows[i]);
        }
        return this;
    }

    public Matrix getSubtraction(Matrix matrix) {
        int sizeColumn = this.getCountRow();
        for (int i = 0; i < sizeColumn; i++) {
            this.rows[i] = this.rows[i].getSubtraction(matrix.rows[i]);
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
        int countRow1 = matrix1.getCountRow();
        int countColumn1 = matrix1.getCountColumn();
        int countColumn2 = matrix2.getCountColumn();
        Matrix.ExclusionDimensionMatrix(countRow1, countColumn2);
        Matrix resultMultiplication = new Matrix(countRow1, countColumn2);
        for (int i = 0; i < countRow1; i++) {
            for (int j = 0; j < countColumn2; j++) {
                double elementResultMatrix = 0;
                for (int k = 0; k < countColumn1; k++) {
                    elementResultMatrix = elementResultMatrix + matrix1.getComponent(i, k) * matrix2.getComponent(k, j);
                }
                resultMultiplication.setComponent(i, j, elementResultMatrix);
            }
        }
        return resultMultiplication;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("{");
        for (Vector component : this.rows) {
            line.append(component.toString());
            line.append(",");
        }
        line.delete(line.length() - 2, line.length());
        line.append("}");
        return line.toString();
    }

}
