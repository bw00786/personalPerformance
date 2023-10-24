package com.ssc.service;


import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CashFlow {
    private  final  String month;
    private final double cashFlow;
    private final int year;
    private  final int dayOfMonth;

    private static final Map<String, Integer> daysInMonth = new HashMap<>();

    static {
        daysInMonth.put("January", 31);
        daysInMonth.put("February", 28); // Default to 28 days for non-leap years.
        daysInMonth.put("March", 31);
        daysInMonth.put("April",30);
        daysInMonth.put("May",31);
        daysInMonth.put("June",30);
        daysInMonth.put("July",31);
        daysInMonth.put("August",31);
        daysInMonth.put("September",30);
        daysInMonth.put("October",31);
        daysInMonth.put("November",30);
        daysInMonth.put("December",31);

        // Add the rest of the months
    }

    public CashFlow(String month, double cashFlow, int dayOfMonth, int year) {
        this.month = month;
        this.cashFlow = cashFlow;
        this.year = year;
        this.dayOfMonth = dayOfMonth;
    }

    public double calculateWeightedCashFlow(CashFlow cashFlows) {
        double days = daysInMonth.get(month);
        System.out.println("days "+days);
        if (month.equals("February") && isLeapYear(year)) {
            days = 29.0; // Adjust for leap year.
        }
        double ff = (days - cashFlows.dayOfMonth)/ days;
        System.out.println("Computeres WCH without cashFlows" + ff);
        double weightedCashFlow = cashFlow *  (( days - cashFlows.dayOfMonth)/ days);
        System.out.println("days from calculatedWeighted " + days);
        System.out.println("cashFlow " + cashFlow);
        System.out.println("cashFlows " + cashFlows.dayOfMonth);
        System.out.println("year is " + year);
        System.out.println("month is " + month);
        System.out.println("computed weightedCashFlow" + weightedCashFlow);
        //return cashFlow *  (( days - cashFlows.dayOfMonth)/ days);
        return weightedCashFlow;
    }

    public double calculateDollarWeightedCashFlow(CashFlow cashFlows) {

        return ((calculateWeightedCashFlow(cashFlows) / 3 * (3 - getMonthNumber(month) + 1)) + (calculateWeightedCashFlow(cashFlows) / 3 * ((cashFlow - (calculateWeightedCashFlow(cashFlows))/3) * (3 - getMonthNumber(month)))));

    }
    public  double computeTotalCashFlow(CashFlow cashFlows) {

        return cashFlows.cashFlow;
    }
    public void displayCashFlows(List<CashFlow> cashFlows) {
            cashFlows.stream()
                .forEach(System.out::println);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    public static int getMonthNumber(String monthName) {
        try {
            Month month = Month.valueOf(monthName.toUpperCase());
            return month.getValue();
        } catch (IllegalArgumentException e) {
            // Handle invalid input (month names that don't exist)
            return -1; // You can choose a different way to handle this error
        }
    }



    private int getTotalDaysInMonth(int year) {
        if (month.equals("February") && isLeapYear(year)) {
            return 29;
        }
        return daysInMonth.get(month);
    }
}
