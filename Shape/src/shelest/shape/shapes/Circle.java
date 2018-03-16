package shelest.shape.shapes;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }

    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override
    public String toString() {
        String line = "Круг";
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
        Circle circle = (Circle) o;
        return (this.radius == circle.radius);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(this.radius);
        return hash;
    }
}
