package shelest.csv.operation;

public class Csv {

    private static int getColumnCount(String[][] array){
        int columnCount = 0;
        for(int i = 1; array[i] != null; i++){
            columnCount = Math.max(array[i].length, array[i-1].length);
        }
        return columnCount;
    }

    private static int getLineCount(String[][] array){
        int lineCount = 0;
        for(int i = 0; array[i] != null; i++){
            lineCount = i;
        }
        return lineCount;
    }

    private static String[][] getProcessQuotes(String[][] array) {
        for (int i = 0; array[i] != null; i++) {
            for (int j = 0; j < array[i].length; j++) {
                String cell = array[i][j];
                int countSingleQuotes =0;
                for (int k = 0; k < cell.length(); k++) {
                    if (cell.charAt(k) == '\"' && cell.charAt(k+1) != '\"'){
                        countSingleQuotes++;
                    }
                }
                if (countSingleQuotes == 1){
                    // надо слить две ближайшие ячейки и проверить заново
                }
            }
        }
        return array;
    }

    private static String toString(String[][] array) {
        StringBuilder line = new StringBuilder("<table>");
        for (int i = 0; array[i] != null; i++) {
            line.append("<tr>");
            for (int j = 0; j < array[i].length; j++) {
                line.append("<td>");
                line.append(array[i][j]);
                line.append("</td>");
            }
            line.append("</tr>");
            line.append(System.lineSeparator());
        }
        line.append("</table>");
        return line.toString();
    }
}
