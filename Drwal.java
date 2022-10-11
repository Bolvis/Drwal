import java.util.Arrays;
import java.util.Scanner;

public class Drwal {

    private static final int X_START_POSITION = 0;
    private static final int Y_START_POSITION = 1;
    private static final int COLOR_POSITION = 2;
    private static final int WIDTH_POSITION = 3;
    private static final int HEIGHT_POSITION = 4;

    private static final String ERROR_MESSAGE = "klops";

    public static void main(String[] args) {

        try{
        int xStart = Integer.parseInt(args[X_START_POSITION])-1;
        int yStart = Integer.parseInt(args[Y_START_POSITION])-1;
        int width = Integer.parseInt(args[WIDTH_POSITION]);
        int height = Integer.parseInt(args[HEIGHT_POSITION]);
        char color = args[COLOR_POSITION].charAt(0);

        if(!meetsExerciseExpectations(width, height)){
            System.out.print(ERROR_MESSAGE);
            return;
        }

        Picture picture = new Picture(width, height);
        Scanner input = new Scanner(System.in);
        copy(picture, input);

        if(positionWithinPicture(xStart, yStart, width, height)) {
            floodFill(picture, xStart, yStart,color);
        }

        print(picture);
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.print(ERROR_MESSAGE);
        }
    }

    private static void copy(Picture picture, Scanner input) {

        for (char[] chars : picture.getArr()) {
            Arrays.fill(chars, ' ');
        }

        for(int y = 0; input.hasNextLine() ; y++){

            String line = input.nextLine();

            for(int x = 0; line.length() > x; x++){

                try{
                    picture.getArr()[y][x] = line.charAt(x);
                }catch(ArrayIndexOutOfBoundsException e) {
                    System.out.print(ERROR_MESSAGE);
                    System.exit(0);
                }

            }
        }
    }

    private static void print(Picture picture) {
        for (char[] row : picture.getArr()) {
            for(char item : row){
                System.out.print(item);
            }
            System.out.println();
        }
    }

    private static void floodFill(Picture picture, int x, int y, char newChar) {

        if (0 > y || picture.getHeight() <= y || 0 > x || picture.getWidth() <= x)
            return;
        if (picture.getArr()[y][x] != ' ')
            return;

        picture.setArrElement(x, y, newChar);

        floodFill(picture, x+1, y, newChar);
        floodFill(picture, x-1, y, newChar);
        floodFill(picture, x, y+1, newChar);
        floodFill(picture, x, y-1, newChar);
    }

    private static boolean meetsExerciseExpectations(int width, int height) {
        return 0 < height && 50 > height && 0 < width && 50 > width;
    }

    private static boolean positionWithinPicture(int x, int y, int width, int height) {
        return y <= height && 0 <= y && x <= width && 0 <= x;
    }
}
