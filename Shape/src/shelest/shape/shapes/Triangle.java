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
        return Math.max(this.x1, Math.max(this.x2, this.x3)) - Math.min(this.x1, Math.min(this.x2, this.x3));
    }

    public double getHeight() {
        return Math.max(this.y1, Math.max(this.y2, this.y3)) - Math.min(this.y1, Math.min(this.y2, this.y3));
    }

    public double getArea() {
        return 0.5 * Math.abs((this.x1 - this.x3) * (this.y2 - this.y3) - (this.x2 - this.x3) * (this.y1 - this.y3));
    }

    public double getPerimeter() {
        return Math.sqrt(Math.pow(this.x1 - this.x2, 2) + Math.pow(this.y1 - this.y2, 2)) +
                Math.sqrt(Math.pow(this.x2 - this.x3, 2) + Math.pow(this.y2 - this.y3, 2)) +
                Math.sqrt(Math.pow(this.x1 - this.x3, 2) + Math.pow(this.y1 - this.y3, 2));
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
        return (this.x1 == triangle.x1 & this.y1 == triangle.y1 &
                this.x2 == triangle.x2 & this.y2 == triangle.y2 &
                this.x3 == triangle.x3 & this.y3 == triangle.y3);
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
