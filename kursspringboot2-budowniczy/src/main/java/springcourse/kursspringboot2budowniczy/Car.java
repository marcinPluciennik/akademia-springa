package springcourse.kursspringboot2budowniczy;

public class Car {
    private final int wheel;
    private final String color;

    public Car(int wheel, String color) {
        this.wheel = wheel;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "wheel=" + wheel +
                ", color='" + color + '\'' +
                '}';
    }
}
