package graphicLayer.Modele;

public class Position {

    private int width;
    private int height;

    public Position(int x, int y) {
        this.width = x;
        this.height = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Position{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
