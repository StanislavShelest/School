package shelest.fine.main;

import shelest.fine.data.FinancialDocument;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        System.out.print("Введите размер ставки рефинансирования: ");
        Scanner scannerRateRefinancing = new Scanner(System.in);
        final double RATE_REFINANCING = scannerRateRefinancing.nextDouble();

        System.out.print("Введите день начисления пени: ");
        Scanner scannerDayFine = new Scanner(System.in);
        final int DAY_FINE = scannerDayFine.nextInt();

        System.out.print("Введите количество дней отсрочки от дня начисления пени: ");
        Scanner scannerCountDayDelayFromDayFine = new Scanner(System.in);
        final int COUNT_DAY_DELAY_FROM_DAY_FINE = scannerCountDayDelayFromDayFine.nextInt();

        System.out.print("Введите коэффициент уменьшения ставки: ");
        Scanner scannerRateReductionRatio = new Scanner(System.in);
        final int RATE_REDUCTION_RATIO = scannerRateReductionRatio.nextInt();

        System.out.print("Введите период расчета в формате мм.гггг: ");
        Scanner scannerPeriodBilling = new Scanner(System.in);
        final String PERIOD_BILLING = scannerPeriodBilling.nextLine();

        final String WAY_TO_File = "LS1.csv";


        ArrayList<FinancialDocument> arrayFinancialDocuments = new ArrayList<>();
        SimpleDateFormat formatDates = new SimpleDateFormat("dd.MM.yyyy");

        try (Scanner scannerInputTable = new Scanner(new FileInputStream(WAY_TO_File), "windows-1251")) {
            String headLine = scannerInputTable.nextLine();

            int countColumn = getCountColumn(headLine);
            int[] assignmentColumns = getAssignmentColumns(headLine, countColumn);
            int indexAccountingMonth = assignmentColumns[0];
            int indexDateDocument = assignmentColumns[1];
            int indexType = assignmentColumns[2];
            int indexSum = assignmentColumns[3];

            while (scannerInputTable.hasNextLine()) {
                String line = scannerInputTable.nextLine();
                arrayFinancialDocuments.add(getFinancialDocument(line, countColumn, indexAccountingMonth,
                        indexDateDocument, indexType, indexSum, DAY_FINE, COUNT_DAY_DELAY_FROM_DAY_FINE));
            }

        } catch (FileNotFoundException e) {
            System.out.print("Данный файл не был найден");
        }


        try (PrintWriter calculation = new PrintWriter("CalculationFine.csv")) {
            StringBuilder row = new StringBuilder("день;сумма;пени");

            String currentDayPeriodString = "01." + PERIOD_BILLING;
            Date currentDayPeriod = formatDates.parse(currentDayPeriodString);
            double sumFine = 0.0;
            int daysOfMonth = getDayOfMonth(currentDayPeriod);
            for (int i = 1; i <= daysOfMonth; i++) {

                double sumDebts = 0.0;
                for (FinancialDocument document : arrayFinancialDocuments) {
                    if (document.getDayActionFinancialDocument().compareTo(currentDayPeriod) <= 0) {
                        sumDebts = Math.round((sumDebts + document.getSum()) * 100) / 100.0;
                    }
                }

                double fine = Math.round(RATE_REFINANCING * sumDebts / RATE_REDUCTION_RATIO) / 100.0;

                row.append(System.lineSeparator())
                        .append(formatDates.format(currentDayPeriod)).append(";")
                        .append(sumDebts).append(";")
                        .append(fine);

                currentDayPeriod = getNextDay(currentDayPeriod);
                sumFine = Math.round((sumFine + fine) * 100) / 100.0;
            }
            row.append(System.lineSeparator())
                    .append("; ИТОГО;")
                    .append(sumFine);
            calculation.println(row);
        } catch (FileNotFoundException e) {
            System.out.print("Данный файл не был найден");
        }
    }

    private static int getCountColumn(String line) {
        int countColumn = 1;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ';') {
                countColumn++;
            }
        }
        return countColumn;
    }

    private static int[] getAssignmentColumns(String line, int countColumn) {
        String[] arrayColumn = line.split(";", countColumn);
        int indexAccountingMonth = 0;
        int indexDateDocument = 0;
        int indexType = 0;
        int indexSum = 0;
        for (int i = 0; i < arrayColumn.length; i++) {
            if (arrayColumn[i].equals("Учетный месяц")) {
                indexAccountingMonth = i;
            }
            if (arrayColumn[i].equals("Дата документа")) {
                indexDateDocument = i;
            }
            if (arrayColumn[i].equals("Тип")) {
                indexType = i;
            }
            if (arrayColumn[i].equals("Сумма")) {
                indexSum = i;
            }
        }
        return new int[]{indexAccountingMonth, indexDateDocument, indexType, indexSum};
    }

    private static FinancialDocument getFinancialDocument(String line, int countColumn, int indexAccountingMonth,
                                                          int indexDateDocument, int indexType, int indexSum, int DAY_FINE,
                                                          int COUNT_DAY_DELAY_FROM_DAY_FINE) throws ParseException {
        String[] arrayValues = line.split(";", countColumn);
        SimpleDateFormat formatDates = new SimpleDateFormat("dd.MM.yyyy");

        double sum = Double.parseDouble(arrayValues[indexSum]);

        String type = arrayValues[indexType];

        Date dateDocument = formatDates.parse(arrayValues[indexDateDocument]);

        Date dayActionFinancialDocument;
        if (type.equals("Оплата") || type.equals("Перерасчет")) {
            dayActionFinancialDocument = dateDocument;
        } else {
            dayActionFinancialDocument = formatDates.parse(DAY_FINE + "." + arrayValues[indexAccountingMonth]);
            Calendar calendarDebtDayForPeriod = Calendar.getInstance();
            calendarDebtDayForPeriod.setTime(dayActionFinancialDocument);
            calendarDebtDayForPeriod.add(Calendar.MONTH, 1);
            calendarDebtDayForPeriod.add(Calendar.DAY_OF_MONTH, COUNT_DAY_DELAY_FROM_DAY_FINE);
            dayActionFinancialDocument = calendarDebtDayForPeriod.getTime();
        }
        return new FinancialDocument(dayActionFinancialDocument, dateDocument, type, sum);
    }

    private static Date getNextDay(Date currentDay) {
        Calendar calendarCurrentDay = Calendar.getInstance();
        calendarCurrentDay.setTime(currentDay);
        calendarCurrentDay.add(Calendar.DAY_OF_MONTH, 1);
        return calendarCurrentDay.getTime();
    }

    private static int getDayOfMonth(Date firstDayPeriod) {
        Calendar calendarFirstDayPeriod = Calendar.getInstance();
        calendarFirstDayPeriod.setTime(firstDayPeriod);
        return calendarFirstDayPeriod.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}

