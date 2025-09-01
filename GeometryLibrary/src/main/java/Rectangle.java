public class Rectangle implements GeometricFigure{

    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculatePerimeter() {
        return (width + height) * 2;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}
