package shelest.shape.operations;

public class Square implements Shape {
    private double lengthSide;

    public Square(double lengthSide){
        this.lengthSide = lengthSide;
    }

    public double getWidth() {
        return this.lengthSide;
    }

    public double getHeight() {
        return this.lengthSide;
    }

    public double getArea() {
        return Math.pow(this.lengthSide, 2);
    }

    public double getPerimeter() {
        return this.lengthSide * 4;
    }

}
