package netcracker.intensive.rover;

public class Point {

    //не забудьте реализовать equals, hashCode, toString!
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return getX() == point.getX() && getY() == point.getY();
    }

    @Override
    public int hashCode() {
        return 31 * getX() + getY();
    }
}
