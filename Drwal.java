import java.util.Arrays;
import java.util.Scanner;

public class Drwal {

    private static int width;
    private static int height;

    public static void main(String[] args) {

        try{
        int xStart = Integer.parseInt(args[0])-1;
        int yStart = Integer.parseInt(args[1])-1;
        width = Integer.parseInt(args[3]);
        height = Integer.parseInt(args[4]);
        char color = args[2].charAt(0);

        if(1 > height || 50 < height || 1 > width || 50 < width){
            System.out.print("klops");
            return;
        }

        char[][] picture = new char[height][width];
        Scanner input = new Scanner(System.in);

        if(yStart > height || yStart < 0 || xStart > width || xStart < 0) {
            copy(picture,input);
            print(picture);
            return;
        }

        copy(picture,input);
        floodFill(picture,xStart,yStart,color);
        print(picture);
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.print("klops");
        }
    }

    private static void copy(char[][] picture, Scanner input) {

        for (char[] chars : picture) {
            Arrays.fill(chars, ' ');
        }

        for(int y = 0; input.hasNextLine() ; y++){

            String line = input.nextLine();

            for(int x = 0; line.length() > x; x++){

                try{
                    picture[y][x] = line.charAt(x);
                }catch(ArrayIndexOutOfBoundsException e) {
                    System.out.print("klops");
                    System.exit(0);
                }

            }
        }
    }

    private static void print(char[][] picture) {

        for (char[] row : picture) {
            for(char item : row){
                System.out.print(item);
            }
            System.out.println();
        }

    }

    private static void floodFill(char[][] picture, int x, int y, char newChar) {

        if (0 > y || height <= y || 0 > x || width <= x)
            return;
        if (picture[y][x] != ' ')
            return;

        picture[y][x] = newChar;

        floodFill(picture, x+1, y, newChar);
        floodFill(picture, x-1, y, newChar);
        floodFill(picture, x, y+1, newChar);
        floodFill(picture, x, y-1, newChar);
    }
}
