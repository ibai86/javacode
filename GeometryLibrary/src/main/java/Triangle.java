public class Triangle implements GeometricFigure{

    private final double side1;
    private final double side2;
    private final double side3;

    public Triangle(double side1, double side2, double side3) {
        if (isValidTriangle(side1, side2, side3)) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        } else {
            throw new IllegalArgumentException("Cannot create triangle with such sides");
        }
    }

    private boolean isValidTriangle(double a, double b, double c) {
        return (a > 0 && b > 0 && c > 0) &&
                (a + b > c) && (a + c > b) && (b + c > a);
    }

    @Override
    public double calculatePerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public double calculateArea() {
        double s = calculatePerimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }
}
