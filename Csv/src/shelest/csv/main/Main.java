package shelest.csv.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("Csv/csvText.txt"), "windows-1251")) {
            String[][] array = new String[10][];
            for (int i = 0; scanner.hasNextLine(); i++) {
                String lineEditable = scanner.nextLine();
                array[i] = lineEditable.split(",");


            }
            //System.out.println(getColumnCount(array));
            //System.out.println(getLineCount(array));
            //System.out.println(toString(array));

        }


    }
}
