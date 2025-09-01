public class GeometryPerformer {

    public static void main(String[] args) {
        Shape circle = new Circle(9.0);
        Shape rectangle = new Rectangle(12.3, 6.7);
        Shape figure = new Triangle(3.0, 4.0, 5.0);

        System.out.println(circle.calculateArea());
        System.out.println(rectangle.calculatePerimeter());
        System.out.println(figure.getName());
    }
}
