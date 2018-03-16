package shelest.shape.main;

import shelest.shape.operations.*;
import shelest.shape.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        Square square1 = new Square(23);
        Square square2 = new Square(24);
        Triangle triangle1 = new Triangle(78, 23, 34, 63, 56, 67);
        Triangle triangle2 = new Triangle(76, 65, 89, 43, 98, 21);
        Rectangle rectangle1 = new Rectangle(23, 24);
        Rectangle rectangle2 = new Rectangle(23, 24);
        Circle circle1 = new Circle(11);
        Circle circle2 = new Circle(24);

        Shape[] shapes = {square1, square2, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2};

        Arrays.sort(shapes, new MaxArea());
        System.out.println("Параметры фигуры с большей площадью:");
        System.out.println(shapes[shapes.length - 1].toString());

        Arrays.sort(shapes, new MaxPerimeter());
        System.out.println(System.lineSeparator() + "Параметры фигуры со вторым по величине периметром:");
        System.out.println(shapes[shapes.length - 2].toString());

        System.out.println(rectangle1.equals(rectangle2));
        System.out.println(rectangle1.hashCode());
        System.out.println(rectangle2.hashCode());
    }
}
