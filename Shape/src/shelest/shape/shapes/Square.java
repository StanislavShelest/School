package shelest.shape.shapes;

public class Square implements Shape {
    private double lengthSide;

    public Square(double lengthSide) {
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

    @Override
    public String toString() {
        String line = "Квадрат";
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
        Square square = (Square) o;
        return (this.lengthSide == square.lengthSide);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(this.lengthSide);
        return hash;
    }


}
