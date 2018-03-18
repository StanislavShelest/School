package shelest.shape.shapes;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public double getArea() {
        return this.width * this.height;
    }

    public double getPerimeter() {
        return 2 * this.width + 2 * this.height;
    }

    @Override
    public String toString() {
        String line = "Прямоугольник";
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
        Rectangle rectangle = (Rectangle) o;
        return (this.width == rectangle.width && this.height == rectangle.height);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(this.width);
        hash = prime * hash + Double.hashCode(this.height);
        return hash;
    }


}
