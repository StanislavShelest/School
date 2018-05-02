package shelest.csv.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    private static String toString(char[][] textCsv, int rowCount) {
        StringBuilder textHtml = new StringBuilder("<table>");
        for (int i = 0; i < rowCount; i++) {
            textHtml.append("<tr><td>");
            for (int j = 0; textCsv[i][j] != '\0'; j++) {
                textHtml.append(textCsv[i][j]);
            }
            textHtml.append("</td></tr>");
        }
        textHtml.append("</table>");
        return textHtml.toString();
    }
    //доделать разделение на ячейки по запятым
    private static char[][] splitCells(char[][] textCsv, int rowCount) {
        //char[] cellSeparator = {'<','t','d','>','<','/','t','d','>'};
        for (int i = 0; textCsv[i] != null; i++) {
            for (int j = 0; textCsv[i][j] != '\0'; j++) {
                if (textCsv[i][j] == ',') {
                    System.arraycopy(textCsv, i + 1, textCsv, i+2, rowCount - 1-i);
                    //textCsv[i+1] = new char[]{'<', 't', 'd', '>', '<', '/', 't', 'd', '>'};
                }
            }
        }
        return textCsv;
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("Csv/csvText.txt"), "windows-1251")) {
            char[][] textCsv = new char[10][100];
            int rowCount = 0;
            for (int i = 0; scanner.hasNextLine(); i++) {
                String row = scanner.nextLine();
                for (int j = 0; j < row.length(); j++) {
                    textCsv[i][j] = row.charAt(j);
                }
                rowCount++;
            }
            splitCells(textCsv, rowCount);
            System.out.println(toString(textCsv, rowCount));

        }
    }
}
