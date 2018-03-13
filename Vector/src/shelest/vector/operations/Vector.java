package shelest.vector.operations;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        this.components = new double[getExclusionUnacceptableDimension(dimension)];
    }

    public Vector(Vector vector) {
        this.components = Arrays.copyOf(vector.components, getExclusionUnacceptableDimension(vector.components.length));
    }

    public Vector(double[] components) {
        this.components = Arrays.copyOf(components, getExclusionUnacceptableDimension(components.length));
    }

    public Vector(int dimension, double[] components) {
        this.components = Arrays.copyOf(components, getExclusionUnacceptableDimension(dimension));
    }

    private static int getExclusionUnacceptableDimension(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Введена недопустимая размерность");
        }
        return dimension;

    }

    public double getCompanent(int index) {
        return this.components[index];
    }

    public void setComponent(int index, double component) {
        this.components[index] = component;
    }

    public int getSize() {
        return this.components.length;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder("{");
        for (double component : this.components) {
            line.append(component).append(", ");
        }
        line.delete(line.length() - 2, line.length());
        line.append("}");
        return line.toString();
    }

    public Vector getAddition(Vector vector) {
        if (this.components.length < vector.components.length) {
            this.components = Arrays.copyOf(this.components, vector.components.length);
        }
        for (int i = 0; i < vector.components.length; i++) {
            this.components[i] = this.components[i] + vector.components[i];
        }
        return this;
    }

    public Vector getSubtraction(Vector vector) {
        if (this.components.length < vector.components.length) {
            this.components = Arrays.copyOf(this.components, vector.components.length);
        }
        for (int i = 0; i < vector.components.length; i++) {
            this.components[i] = this.components[i] - vector.components[i];
        }
        return this;
    }

    public Vector getMultiplicationByScalar(double scalar) {
        for (int i = 0; i < this.components.length; i++) {
            this.components[i] = this.components[i] * scalar;
        }
        return this;
    }

    public Vector getTurn() {
        return getMultiplicationByScalar(-1);
    }

    public double getLength() {
        double sum = 0;
        for (double component : this.components) {
            sum = sum + Math.pow(component, 2);
        }
        return Math.sqrt(sum);
    }

    public static Vector getAddition(Vector vector1, Vector vector2) {
        Vector resultAddition = new Vector(vector1);
        return resultAddition.getAddition(vector2);
    }

    public static Vector getSubtraction(Vector vector1, Vector vector2) {
        Vector resultSubtraction = new Vector(vector1);
        return resultSubtraction.getSubtraction(vector2);
    }

    public static double getScalarMultiplication(Vector vector1, Vector vector2) {
        double resultMultiplication = 0;
        for (int i = 0; i < Math.min(vector1.components.length, vector2.components.length); i++) {
            resultMultiplication = resultMultiplication + vector1.components[i] * vector2.components[i];
        }
        return resultMultiplication;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return false;
        }
        if (o == null) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;
        return (Arrays.equals(this.components, vector.components));
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }
}
