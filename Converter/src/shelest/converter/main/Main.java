package shelest.converter.main;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    private static void sendFile(String nameOutputFile, String pathOutputFile) throws IOException {
        try {
            String boundary = Long.toHexString(System.currentTimeMillis());
            String encoding = "UTF-8";
            URL addressSite = new URL("http://www.site.com");

            HttpURLConnection connection = (HttpURLConnection) addressSite.openConnection();
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            OutputStream streamDst = connection.getOutputStream();
            PrintWriter writerDst = new PrintWriter(new OutputStreamWriter(streamDst, encoding), true);

            writerDst.println("--" + boundary);
            writerDst.println("Content-Disposition: form-data; name=\"output_file\"; filename=\"" + nameOutputFile + "\"");
            writerDst.println("Content-Type: application/octet-stream");
            writerDst.println();
            Scanner scannerDts = new Scanner(new FileInputStream(pathOutputFile));
            while (scannerDts.hasNextLine()) {
                writerDst.println(scannerDts.nextLine());
            }
            writerDst.println("--" + boundary + "--");

        } catch (MalformedURLException e) {
            System.out.println("Указан неверный URL");
        } catch (UnsupportedEncodingException | ProtocolException e) {
            System.out.println("Кодировка символов не поддерживается или в протоколе есть ошибки ");
        }
    }

    private static String getValueParameter(String lineParametersField, String nameParameter) {
        int indexStartValueParameter = lineParametersField.indexOf(nameParameter) + nameParameter.length() + 1;
        int indexEndValueParameter = lineParametersField.indexOf("</" + nameParameter);
        return lineParametersField.substring(indexStartValueParameter, indexEndValueParameter);
    }


    private static String getNameParameter(String lineParametersField, int indexStarSearchNameParameter) {
        int indexStartNameParameter = lineParametersField.indexOf("<", indexStarSearchNameParameter) + 1;
        int indexEndNameParameter = lineParametersField.indexOf(">", indexStarSearchNameParameter);
        return lineParametersField.substring(indexStartNameParameter, indexEndNameParameter);
    }


    private static String getLineDst(String lineParametersField) {
        StringBuilder lineDst = new StringBuilder();
        StringBuilder lineValueField = new StringBuilder();
        int indexStarSearchNameParameter = 0;
        boolean isAddress = false;

        while (indexStarSearchNameParameter != lineParametersField.length()) {
            String nameParameter = getNameParameter(lineParametersField, indexStarSearchNameParameter);
            String valueParameter = getValueParameter(lineParametersField, nameParameter);

            if (nameParameter.equals("type") && valueParameter.equals("Address")) {
                isAddress = true;
            }

            if (!nameParameter.equals("name") && !nameParameter.equals("value") && valueParameter.equals("1")) {
                valueParameter = "true";
            }

            if (isAddress && nameParameter.equals("value")) {
                String[] arrayAddress = valueParameter.split(",", 3);
                lineValueField.append("street=\"").append(arrayAddress[0]).append("\" ")
                        .append("house=\"").append(arrayAddress[1]).append("\" ")
                        .append("flat=\"").append(arrayAddress[2]).append("\"/>");
            }

            switch (nameParameter) {
                case "type":
                    StringBuilder tempLine = new StringBuilder("\t<");
                    tempLine.append(valueParameter).append(" ").append(lineDst);
                    lineDst = tempLine;
                    break;
                case "value":
                    if (!isAddress) {
                        lineValueField.append(nameParameter).append("=\"").append(valueParameter).append("\"/>");
                    }
                    break;
                default:
                    lineDst.append(nameParameter).append("=\"").append(valueParameter).append("\" ");
                    break;
            }
            indexStarSearchNameParameter = lineParametersField.indexOf("</" + nameParameter) + nameParameter.length() + 3;
        }
        lineDst.append(lineValueField);
        return lineDst.toString();
    }

    public static void main(String[] args) throws IOException {
        try (Scanner scannerSrc = new Scanner(new FileInputStream("Converter/src/shelest/converter/catalogs/input/Src.xml"), "utf-8");
             PrintWriter writerDst = new PrintWriter("Converter/src/shelest/converter/catalogs/output/Dst.xml")) {

            writerDst.println(scannerSrc.nextLine());
            writerDst.println("<Data>");

            String lineParametersField = "";
            while (scannerSrc.hasNextLine()) {
                String lineSrc = scannerSrc.nextLine().trim();

                if (lineSrc.equals("<Field>")) {
                    lineSrc = scannerSrc.nextLine().trim();
                    while (!lineSrc.equals("</Field>")) {
                        lineParametersField += lineSrc;
                        lineSrc = scannerSrc.nextLine().trim();
                    }

                    writerDst.println(getLineDst(lineParametersField));
                    lineParametersField = "";
                }

            }
            writerDst.println("</Data>");
        } catch (FileNotFoundException e) {
            System.out.print("Данный файл не был найден");
        }

        sendFile("Dst.xml","Converter/src/shelest/converter/catalogs/output/Dst.xml");
    }
}
