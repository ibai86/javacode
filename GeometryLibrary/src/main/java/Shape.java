public interface Shape {

    double calculatePerimeter();

    double calculateArea();

    /**
     * Added changes for 1.1.0 version
     */
    default String getName() {
        return this.getClass().getName();
    }
}
