package shelest.csv.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner( new FileInputStream("./Csv/csvText.txt"))){
            String line = scanner.nextLine();
        }

    }
}
