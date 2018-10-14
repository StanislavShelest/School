package shelest.fine.main;

import shelest.fine.data.Fine;

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

        ArrayList<Fine> arrayFine = new ArrayList<>();
        SimpleDateFormat formatDebtDayForPeriod = new SimpleDateFormat("dd.MM.yyyy");

        try (Scanner scanner = new Scanner(new FileInputStream(WAY_TO_File), "windows-1251")) {
            String headLine = scanner.nextLine();

            int countSymbol = 1;
            for (int i = 0; i < headLine.length(); i++) {
                if (headLine.charAt(i) == ';') {
                    countSymbol++;
                }
            }
            String[] arrayColumn = headLine.split(";", countSymbol);

            int indexAccountingMonth = 0;
            int indexSum = 0;
            for (int i = 0; i < arrayColumn.length; i++) {
                if (arrayColumn[i].equals("Учетный месяц")) {
                    indexAccountingMonth = i;
                }
                if (arrayColumn[i].equals("Сумма")) {
                    indexSum = i;
                }
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arrayValues = line.split(";", countSymbol);

                arrayValues[1] = DAY_FINE + "." + arrayValues[indexAccountingMonth];
                Date debtDayForPeriod = formatDebtDayForPeriod.parse(arrayValues[1]);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(debtDayForPeriod);
                calendar1.add(Calendar.MONTH, 1);
                calendar1.add(Calendar.DAY_OF_MONTH, COUNT_DAY_DELAY_FROM_DAY_FINE);
                debtDayForPeriod = calendar1.getTime();

                double sum = Double.parseDouble(arrayValues[indexSum]);

                Fine fine = new Fine(debtDayForPeriod, sum);
                arrayFine.add(fine);
            }
        } catch (FileNotFoundException e) {
            System.out.print("Данный файл не был найден");
        }


        String firstDayPeriodString = "01." + PERIOD_BILLING;
        Date firstDayPeriod = formatDebtDayForPeriod.parse(firstDayPeriodString);

        double fineForPeriod = 0.0;
        double increaseFine;
        boolean isOccurrence = false;
        Date dayIncreaseFine;
        Date firstDebtDayForPeriod = arrayFine.get(0).getDebtDayForPeriod();
        Date lastDebtDayForPeriod = arrayFine.get(arrayFine.size() - 1).getDebtDayForPeriod();
        if (firstDebtDayForPeriod.before(lastDebtDayForPeriod)) {
            int index = 0;
            for (int i = 0; arrayFine.get(i).getDebtDayForPeriod().before(firstDayPeriod); i++) {
                fineForPeriod += arrayFine.get(i).getSum();
                index = i;
                isOccurrence = true;
            }
            if (isOccurrence) {
                dayIncreaseFine = arrayFine.get(index + 1).getDebtDayForPeriod();
                increaseFine = arrayFine.get(index + 1).getSum();
            } else {
                dayIncreaseFine = arrayFine.get(index).getDebtDayForPeriod();
                increaseFine = arrayFine.get(index).getSum();
            }
        } else {
            int index = arrayFine.size() - 1;
            for (int i = arrayFine.size() - 1; arrayFine.get(i).getDebtDayForPeriod().before(firstDayPeriod); i--) {
                fineForPeriod += arrayFine.get(i).getSum();
                index = i;
                isOccurrence = true;
            }
            if (isOccurrence) {
                dayIncreaseFine = arrayFine.get(index - 1).getDebtDayForPeriod();
                increaseFine = arrayFine.get(index - 1).getSum();
            } else {
                dayIncreaseFine = arrayFine.get(index).getDebtDayForPeriod();
                increaseFine = arrayFine.get(index).getSum();
            }
        }

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(firstDayPeriod);
        int daysOfMonth = calendar2.getActualMaximum(Calendar.DAY_OF_MONTH);

        try (PrintWriter calculation = new PrintWriter("CalculationFine.csv")) {
            StringBuilder row = new StringBuilder("день;сумма;пени");
            Date currentDay = firstDayPeriod;
            double sumFine = 0.0;
            for (int i = 1; i <= daysOfMonth; i++) {
                double sum;

                if (currentDay.before(dayIncreaseFine)) {
                    sum = fineForPeriod;
                } else {
                    sum = fineForPeriod + increaseFine;
                }
                double fine = RATE_REFINANCING * sum / 100 / RATE_REDUCTION_RATIO;
                row.append(System.lineSeparator())
                        .append(formatDebtDayForPeriod.format(currentDay)).append(";")
                        .append(String.format("%.2f", sum)).append(";")
                        .append(String.format("%.2f", fine));

                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(currentDay);
                calendar3.add(Calendar.DAY_OF_MONTH, 1);
                currentDay = calendar3.getTime();

                sumFine += fine;
            }
            row.append(System.lineSeparator())
                    .append("; ИТОГО;")
                    .append(String.format("%.2f", sumFine));
            calculation.println(row);

        } catch (FileNotFoundException e) {
            System.out.print("Данный файл не был найден");
        }
    }
}

