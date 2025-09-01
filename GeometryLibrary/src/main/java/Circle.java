public class Circle implements GeometricFigure{

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculatePerimeter() {
        return radius * 2 * Math.PI;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
