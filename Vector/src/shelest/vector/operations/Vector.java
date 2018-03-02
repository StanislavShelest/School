package shelest.vector.operations;

import java.util.Arrays;

public class Vector {
    private int dimension;
    private double[] components;

    public int getDimension() {
        return dimension;
    }

    public double[] getComponents() {
        return components;
    }

    public Vector(int dimension) {
        this.dimension = dimension;
        this.components = new double[dimension];
    }

    public Vector(Vector vector) {
        this.dimension = vector.dimension;
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        this.dimension = components.length;
        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int dimension, double[] components) {
        this.dimension = dimension;
        this.components = Arrays.copyOf(components, dimension);
    }

    public int getSize() {
        return this.dimension;
    }

    public String toString() {
        return Arrays.toString(this.components);
    }

    public Vector getAddition(Vector vector) {
        int minDimension;
        double[] resultAddition;
        if (this.dimension >= vector.dimension) {
            minDimension = vector.dimension;
            resultAddition = Arrays.copyOf(this.components, this.components.length);
        } else {
            minDimension = this.dimension;
            resultAddition = Arrays.copyOf(vector.components, vector.components.length);
        }
        for (int i = 0; i < minDimension; i++) {
            resultAddition[i] = this.components[i] + vector.components[i];
        }
        return new Vector(resultAddition);
    }

    public Vector getSubtraction(Vector vector) {
        int count;
        double[] minuend;
        double[] subtrahend;
        if (this.dimension >= vector.dimension) {
            count = this.dimension;
            minuend = Arrays.copyOf(this.components, this.dimension);
            subtrahend = Arrays.copyOf(vector.components, this.dimension);
        } else {
            count = vector.dimension;
            minuend = Arrays.copyOf(this.components, vector.dimension);
            subtrahend = Arrays.copyOf(vector.components, vector.dimension);
        }
        double[] resultSubtraction = new double[count];
        for (int i = 0; i < count; i++) {
            resultSubtraction[i] = minuend[i] - subtrahend[i];
        }
        return new Vector(resultSubtraction);
    }

    public Vector getMultiplicationByScalar(double scalar) {
        double[] resultMultiplication = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++) {
            resultMultiplication[i] = this.components[i] * scalar;
        }
        return new Vector(resultMultiplication);
    }

    public Vector getTurn() {
        double[] resultTurn = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++) {
            resultTurn[i] = this.components[i] * (-1);
        }
        return new Vector(resultTurn);
    }

    public double getLength() {
        double sum = Math.pow(this.components[0], 2);
        for (int i = 1; i < this.dimension; i++) {
            sum = sum + Math.pow(this.components[i], 2);
        }
        return Math.sqrt(sum);
    }

    public Vector getInstallationComponent(int index, double installableComponent) {
        this.components[index] = installableComponent;
        return new Vector(this.components);
    }

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
        return (this.getDimension() == vector.getDimension() || Arrays.equals(this.getComponents(), vector.getComponents()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (Arrays.equals(getComponents(), null) ? 0 : Arrays.hashCode(getComponents()));
        result = prime * result + getDimension();
        return result;
    }
}
