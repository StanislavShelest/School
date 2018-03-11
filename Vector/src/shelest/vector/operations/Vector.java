package shelest.vector.operations;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        this.components = new double[getExclusionUnacceptableDimension(dimension)];
    }

    public Vector(Vector vector) {
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int dimension, double[] components) {
        this.components = Arrays.copyOf(components, getExclusionUnacceptableDimension(dimension));
    }

    private static int getExclusionUnacceptableDimension(int dimension) {
        if (dimension <= 0) throw new IllegalArgumentException("Введена недопустимая размерность");
        return dimension;

    }

    public double getCompanents(int index) {
        return this.components[index];
    }

    public void setComponents(int index, double component) {
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
        return line + "}";
    }

    public Vector getAddition(Vector vector) {
        double[] resultAddition;
        if (this.components.length >= vector.components.length) {
            for (int i = 0; i < vector.components.length; i++) {
                this.components[i] = this.components[i] + vector.components[i];
            }
        } else {
            this.components = Arrays.copyOf(this.components, vector.components.length);
            for (int i = 0; i < this.components.length; i++) {
                this.components[i] = this.components[i] + vector.components[i];
            }

        }
        return this;
    }

    public Vector getSubtraction(Vector vector) {
        if (this.components.length >= vector.components.length) {
            for (int i = 0; i < vector.components.length; i++) {
                this.components[i] = this.components[i] - vector.components[i];
            }
        } else {
            this.components = Arrays.copyOf(this.components, vector.components.length);
            for (int i = 0; i < vector.components.length; i++) {
                this.components[i] = this.components[i] - vector.components[i];
            }
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
        getMultiplicationByScalar(-1);
        return this;
    }

    public double getLength() {
        double sum = 0;
        for (double component : this.components) {
            sum = sum + Math.pow(component, 2);
        }
        return Math.sqrt(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
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
        final int prime = 31;
        int result = 1;
        result = prime * result + (Arrays.equals(components, null) ? 0 : Arrays.hashCode(components));
        return result;
    }
}
