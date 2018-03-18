package shelest.shape.operations;

import shelest.shape.shapes.Shape;

import java.util.Comparator;

public class MaxPerimeterComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }

}
