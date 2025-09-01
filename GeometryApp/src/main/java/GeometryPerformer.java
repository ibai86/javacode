public class GeometryPerformer {

    public static void main(String[] args) {
        Circle circle = new Circle(9.0);
        Rectangle rectangle = new Rectangle(12.3, 6.7);
        GeometricFigure figure = new Triangle(3.0, 4.0, 5.0);

        System.out.println(circle.calculateArea());
        System.out.println(rectangle.calculatePerimeter());
        System.out.println(figure.getName());
    }
}
