package shelest.csv.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    private static char[][][] trimToSize(char[][][] textCsv) {
        int i = 0;
        while (textCsv[i][0][0] != '\0') {
            int j = 0;
            while (textCsv[i][j][0] != '\0') {
                int k = 0;
                while (textCsv[i][j][k] != '\0') {
                    k++;
                }
                textCsv[i][j] = Arrays.copyOf(textCsv[i][j], k);
                j++;
            }
            textCsv[i] = Arrays.copyOf(textCsv[i], j);
            i++;
        }
        textCsv = Arrays.copyOf(textCsv, i);
        return textCsv;
    }

    private static char[][][] splitCells(char[][][] textCsv) {
        int quotesCount = 2;
        for (int i = 0; i < textCsv.length; i++) {
            for (int j = 0; j < textCsv[i].length; j++) {
                for (int k = 0; k < textCsv[i][j].length; k++) {
                    if (textCsv[i][j][k] == '"') {
                        quotesCount++;
                    }
                    if (textCsv[i][j][k] == ',') {
                        if (quotesCount % 2 != 1) {
                            textCsv[i] = Arrays.copyOf(textCsv[i], textCsv[i].length + 1);
                            textCsv[i][j + 1] = Arrays.copyOf(textCsv[i][j], textCsv[i][j].length - 1 - k);
                            System.arraycopy(textCsv[i][j], k + 1, textCsv[i][j + 1], 0, textCsv[i][j].length - 1 - k);
                            textCsv[i][j] = Arrays.copyOf(textCsv[i][j], k);
                            break;
                        }
                    }
                }
            }
        }
        return textCsv;
    }

    private static char[][][] processQuotes(char[][][] textCsv) {
        for (int i = 0; i < textCsv.length; i++) {
            for (int j = 0; j < textCsv[i].length; j++) {
                int cellLength = textCsv[i][j].length;
                if (textCsv[i][j].length != 0) {
                    if (textCsv[i][j][0] == '"') {
                        if (textCsv[i][j][cellLength - 1] != '"') {
                            textCsv = mergeAdjacentCells(textCsv, i, j);
                        }
                        System.arraycopy(textCsv[i][j], 1, textCsv[i][j], 0, textCsv[i][j].length - 1);
                        textCsv[i][j] = Arrays.copyOf(textCsv[i][j], textCsv[i][j].length - 2);
                        for (int k = 0; k < textCsv[i][j].length - 1; k++) {
                            if (textCsv[i][j][k] == '"' && textCsv[i][j][k + 1] == '"') {
                                System.arraycopy(textCsv[i][j], k + 1, textCsv[i][j], k, textCsv[i][j].length - k - 1);
                                textCsv[i][j] = Arrays.copyOf(textCsv[i][j], textCsv[i][j].length - 1);
                            }
                        }
                    }
                }
            }
        }
        return textCsv;
    }

    private static char[][][] mergeAdjacentCells(char[][][] textCsv, int i, int j) {
        if (j == textCsv[i].length - 1) {
            textCsv[i] = Arrays.copyOf(textCsv[i], textCsv[i].length + textCsv[i + 1].length);
            System.arraycopy(textCsv[i + 1], 0, textCsv[i], j + 1, textCsv[i + 1].length);

            System.arraycopy(textCsv, i + 2, textCsv, i + 1, textCsv.length - i - 2);
            textCsv = Arrays.copyOf(textCsv, textCsv.length - 1);

            textCsv[i][j] = Arrays.copyOf(textCsv[i][j], textCsv[i][j].length + 1);
            textCsv[i][j][textCsv[i][j].length - 1] = '\n';

            textCsv[i][j] = Arrays.copyOf(textCsv[i][j], textCsv[i][j].length + textCsv[i][j + 1].length);
            System.arraycopy(textCsv[i][j + 1], 0, textCsv[i][j], textCsv[i][j].length - textCsv[i][j + 1].length, textCsv[i][j + 1].length);

            System.arraycopy(textCsv[i], j + 2, textCsv[i], j + 1, textCsv[i].length - j - 2);
            textCsv[i] = Arrays.copyOf(textCsv[i], textCsv[i].length - 1);
        } else {
            textCsv[i][j] = Arrays.copyOf(textCsv[i][j], textCsv[i][j].length + textCsv[i][j + 1].length);
            System.arraycopy(textCsv[i][j + 1], 0, textCsv[i][j], textCsv[i][j].length - textCsv[i][j + 1].length, textCsv[i][j + 1].length - 1);

            System.arraycopy(textCsv[i], j + 2, textCsv[i], j + 1, textCsv[i].length - j - 2);
            textCsv[i] = Arrays.copyOf(textCsv[i], textCsv[i].length - 1);
        }
        return textCsv;
    }

    private static String toString(char[][][] textCsv) {
        StringBuilder textHtml = new StringBuilder("<table>");
        for (int i = 0; i < textCsv.length; i++) {
            textHtml.append("<tr>");
            for (int j = 0; j < textCsv[i].length; j++) {
                textHtml.append("<td>");
                for (int k = 0; k < textCsv[i][j].length; k++) {
                    textHtml.append(textCsv[i][j][k]);
                }
                textHtml.append("</td>");
            }
            textHtml.append("</tr>");
            textHtml.append(System.lineSeparator()); //убрать
        }
        textHtml.append("</table>");
        return textHtml.toString();
    }




    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("Csv/csvText.txt"), "windows-1251")) {
            char[][][] textCsv = new char[100][100][100];
            for (int i = 0; scanner.hasNextLine(); i++) {
                String row = scanner.nextLine();
                for (int j = 0; j < row.length(); j++) {
                    textCsv[i][0][j] = row.charAt(j);
                }
            }
            textCsv = trimToSize(textCsv);
            textCsv = splitCells(textCsv);
            textCsv = processQuotes(textCsv);
            System.out.println(toString(textCsv));
        }
    }
}
