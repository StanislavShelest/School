package shelest.shape.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getWidth() {
        return getLengthSide(x1, x2, x3);
    }

    public double getHeight() {
        return getLengthSide(y1, y2, y3);
    }

    public double getArea() {
        return 0.5 * Math.abs((this.x1 - this.x3) * (this.y2 - this.y3) - (this.x2 - this.x3) * (this.y1 - this.y3));
    }

    public double getPerimeter() {
        return Math.sqrt(Math.pow(this.x1 - this.x2, 2) + Math.pow(this.y1 - this.y2, 2)) +
                Math.sqrt(Math.pow(this.x2 - this.x3, 2) + Math.pow(this.y2 - this.y3, 2)) +
                Math.sqrt(Math.pow(this.x1 - this.x3, 2) + Math.pow(this.y1 - this.y3, 2));
    }

    private double getLengthSide(double argument1, double argument2, double argument3) {
        return Math.max(argument1, Math.max(argument2, argument3)) - Math.min(argument1, Math.min(argument2, argument3));
    }

    @Override
    public String toString() {
        String line = "Треугольник";
        line += (System.lineSeparator() + "Высота: " + getWidth() + "; ");
        line += (System.lineSeparator() + "Ширина: " + getHeight() + "; ");
        line += (System.lineSeparator() + "Площадь: " + getArea() + "; ");
        line += (System.lineSeparator() + "Периметр: " + getPerimeter() + ";");
        return line;
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
        Triangle triangle = (Triangle) o;
        return (this.x1 == triangle.x1 && this.y1 == triangle.y1 &
                this.x2 == triangle.x2 && this.y2 == triangle.y2 &
                this.x3 == triangle.x3 && this.y3 == triangle.y3);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(this.x1);
        hash = prime * hash + Double.hashCode(this.y1);
        hash = prime * hash + Double.hashCode(this.x2);
        hash = prime * hash + Double.hashCode(this.y2);
        hash = prime * hash + Double.hashCode(this.x3);
        hash = prime * hash + Double.hashCode(this.y3);
        return hash;
    }
}
