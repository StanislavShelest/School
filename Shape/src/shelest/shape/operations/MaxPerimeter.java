package shelest.shape.operations;

import shelest.shape.shapes.Shape;

import java.util.Comparator;

public class MaxPerimeter implements Comparator<Shape>{
    public int compare(Shape shape1,Shape shape2){
        if (shape1.getPerimeter() == shape2.getPerimeter()) {
            return 0;
        }
        if (shape1.getPerimeter() > shape2.getPerimeter()){
            return 1;
        }else{
            return -1;
        }
    }

}
