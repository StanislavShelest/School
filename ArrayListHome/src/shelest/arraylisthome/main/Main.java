package shelest.arraylisthome.main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> poem = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream("./ArrayListHome/poem.txt"))) {
            while (scanner.hasNextLine()) {
                poem.add(scanner.nextLine());
            }
        }
        System.out.println("Задание 1: " + poem);

        ArrayList<Integer> listNumber = new ArrayList<>(Arrays.asList(1, 3, 4, 6, 7, 5, 4));
        for (int i = 0; i < listNumber.size(); i++) {
            if (listNumber.get(i) % 2 == 0) {
                listNumber.remove(i);
                i--;
            }
        }
        System.out.println("Задание 2: " + listNumber);

        ArrayList<Integer> listPrimary = new ArrayList<>(Arrays.asList(1, 8, 2, 3, 1, 8, 5, 8, 2, 12, 23, 5));
        ArrayList<Integer> listFinal = new ArrayList<>();
        for (Integer listPrimaryElement: listPrimary) {
            if (!listFinal.contains(listPrimaryElement)) {
                listFinal.add(listPrimaryElement);
            }
        }
        System.out.println("Задание 3: " + listFinal);
    }
}
