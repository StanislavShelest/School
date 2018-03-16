package shelest.shape.operations;

import shelest.shape.shapes.Shape;

import java.util.Comparator;

public class MaxArea implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        if (shape1.getArea() == shape2.getArea()) {
            return 0;
        }
        if (shape1.getArea() > shape2.getArea()) {
            return 1;
        } else {
            return -1;
        }
    }
}
