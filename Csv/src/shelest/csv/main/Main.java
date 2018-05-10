package shelest.csv.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String nameInput = "Csv/csvText.txt";
        String nameOutput = "Csv/htmlText.txt";
        try (Scanner scanner = new Scanner(new FileInputStream(nameInput), "windows-1251")) {
            StringBuilder textHtml = new StringBuilder("<!DOCTYPE html><table>");
            int quotesCount = 0;
            while (scanner.hasNextLine()) {
                if (quotesCount % 2 != 1) {
                    textHtml.append("<tr><td>");
                }
                String row = scanner.nextLine();
                char symbol;
                for (int j = 0; j < row.length(); j++) {
                    symbol = row.charAt(j);
                    switch (symbol) {
                        case (','):
                            if (quotesCount % 2 != 1) {
                                textHtml.append("</td><td>");
                            } else {
                                textHtml.append(symbol);
                            }
                            break;
                        case ('"'):
                            quotesCount++;
                            if (j == row.length() - 1 || row.charAt(j + 1) != '"') {
                                break;
                            } else {
                                if (quotesCount % 2 == 1) {
                                    quotesCount += 2;
                                    j += 2;
                                    textHtml.append('"');
                                    break;
                                } else {
                                    quotesCount++;
                                    j++;
                                    textHtml.append('"');
                                    break;
                                }
                            }
                        case ('<'):
                            textHtml.append("&lt");
                            break;
                        case ('>'):
                            textHtml.append("&gt");
                            break;
                        case ('&'):
                            textHtml.append("&amp");
                            break;
                        default:
                            textHtml.append(symbol);
                    }
                }
                if (quotesCount % 2 != 1) {
                    textHtml.append("</td></tr>");
                } else {
                    textHtml.append("<br/>");
                }
            }
            textHtml.append("</table>");
            System.out.print(textHtml);
            try (PrintWriter writer = new PrintWriter(nameOutput)) {
                writer.print(textHtml);
            }
        } catch (FileNotFoundException e) {
            System.out.print("Данный файл не был найден");
        }
    }
}
