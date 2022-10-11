public class Picture {
    private final char[][] arr;
    private final int width;
    private final int height;

    public Picture(int width, int height){
        this.arr = new char[height][width];
        this. width = width;
        this.height = height;
    }

    public char[][] getArr() {
        return arr;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setArrElement(int x, int y, char newChar){
        this.arr[y][x] = newChar;
    }
}
