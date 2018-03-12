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

    public static int getSize(Vector vector) {
        return vector.components.length;
    }

    @Override
    public String toString() {
        String line = Arrays.toString(this.components);
        line = line.replace("[", "{");
        line = line.replace("]", "}");
        return line;
    }

    public Vector getAddition(Vector vector) {
        int minLength;
        if (this.components.length >= vector.components.length) {
            minLength = vector.components.length;
        } else {
            minLength = this.components.length;
            this.components = Arrays.copyOf(this.components, vector.components.length);
        }
        for (int i = 0; i < minLength; i++) {
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

    public static double getLength(Vector vector) {
        double sum = 0;
        for (double component : vector.components) {
            sum = sum + Math.pow(component, 2);
        }
        return Math.sqrt(sum);
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
