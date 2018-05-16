package shelest.csv.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2){
            System.out.print("Необходимо ввести два аргумента, содержащих пути к файлам: для получения текста формата CSV и для сохранения текста в формате HTML");
        }else {
            try (Scanner scanner = new Scanner(new FileInputStream(args[0]), "windows-1251")) {
                StringBuilder textHtml = new StringBuilder();
                textHtml.append("<!DOCTYPE html>")
                        .append(System.lineSeparator()).append(" <html>")
                        .append(System.lineSeparator()).append("  <head>")
                        .append(System.lineSeparator()).append("   <title>Таблица</title>")
                        .append(System.lineSeparator()).append("  </head>")
                        .append(System.lineSeparator()).append("  <body>")
                        .append(System.lineSeparator()).append("   <table>");

                int quotesCount = 0;
                while (scanner.hasNextLine()) {
                    if (quotesCount % 2 != 1) {
                        textHtml.append(System.lineSeparator()).append("    <tr>")
                                .append(System.lineSeparator()).append("     <td>");
                    }

                    String row = scanner.nextLine();

                    for (int j = 0; j < row.length(); j++) {
                        char symbol = row.charAt(j);

                        switch (symbol) {
                            case ',':
                                if (quotesCount % 2 != 1) {
                                    textHtml.append("</td>")
                                            .append(System.lineSeparator())
                                            .append("     <td>");
                                } else {
                                    textHtml.append(symbol);
                                }
                                break;

                            case '"':
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

                            case '<':
                                textHtml.append("&lt;");
                                break;

                            case '>':
                                textHtml.append("&gt;");
                                break;

                            case '&':
                                textHtml.append("&amp;");
                                break;

                            default:
                                textHtml.append(symbol);
                        }
                    }

                    if (quotesCount % 2 != 1) {
                        textHtml.append("</td>")
                                .append(System.lineSeparator())
                                .append("    </tr>");
                    } else {
                        textHtml.append("<br/>");
                    }
                }
                textHtml.append(System.lineSeparator()).append("   </table>")
                        .append(System.lineSeparator()).append("  </body>")
                        .append(System.lineSeparator()).append(" </html>");

                try (PrintWriter writer = new PrintWriter(args[1])) {
                    writer.print(textHtml);
                }

            } catch (FileNotFoundException e) {
                System.out.print("Данный файл не был найден");
            }
        }
    }
}
