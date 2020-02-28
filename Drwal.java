import java.util.Arrays;
import java.util.Scanner;

public class Drwal {

    private static int width;
    private static int height;

    public static void main(String[] args) {

        int xStart = Integer.parseInt(args[0])-1;
        int yStart = Integer.parseInt(args[1])-1;
        char color = args[2].charAt(0);
        width = Integer.parseInt(args[3]);
        height = Integer.parseInt(args[4]);

        if(height < 1 || width < 1){
            System.out.println("klops");
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
                    System.out.println("klops");
                    System.exit(1);
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

    private static void floodFillUtil(char[][] picture, int x, int y, char prevChar, char newChar) {

        if (0 > y || height <= y || 0 > x || width <= x)
            return;
        if (picture[y][x] != prevChar)
            return;
        if (prevChar == '#')
            return;

        picture[y][x] = newChar;

        floodFillUtil(picture, x+1, y, prevChar, newChar);
        floodFillUtil(picture, x-1, y, prevChar, newChar);
        floodFillUtil(picture, x, y+1, prevChar, newChar);
        floodFillUtil(picture, x, y-1, prevChar, newChar);
    }

    private static void floodFill(char[][] picture, int x, int y, char newChar) {

        char prevChar = picture[y][x];
        if(newChar == prevChar) return;
        floodFillUtil(picture, x, y, prevChar, newChar);
    }
}
