package com.ssc.service;


import java.util.ArrayList;
import java.util.List;

public class SimultaneousTimeDollarWeighting {

    public static void main(String[] args) {
        double initialBalance = 1000.0;
        double endingBalance = 1200.0;
        int year = 2023;

        List<CashFlow> cashFlows = createSampleCashFlows();


        double totalWeightedCashFlow = calculateWeightedCashFlows(cashFlows);
        //double totalWeightedCashFlow = cashFlows.stream().mapToDouble(n -> n.calculateWeightedCashFlow(n)).sum();
        double totalDollarWeightedCashFlow = cashFlows.stream().mapToDouble(n -> n.calculateDollarWeightedCashFlow(n)).sum();
        double totalCashFlow = calculateTotalCashFlow(cashFlows);
        System.out.println("initial balance "+ initialBalance);
        System.out.println("ending Blance " + endingBalance);
        System.out.println("total cashflow " + totalCashFlow);
        System.out.println("totalWeightedCashFlow " + totalWeightedCashFlow);
        System.out.println("totalDollarWeightedCashFlow " + totalDollarWeightedCashFlow);
        System.out.println("totalCashFlow " + totalCashFlow);

        double calculatedRateOfReturn = calculateModifiedDietzRate(initialBalance, endingBalance, totalCashFlow, cashFlows, totalDollarWeightedCashFlow);

        System.out.println("Quarterly Rate of Return: " + (calculatedRateOfReturn * 100) + "%");
    }

    private static List<CashFlow> createSampleCashFlows() {
        List<CashFlow> cashFlows = new ArrayList<>();
        cashFlows.add(new CashFlow("January", 10.0,15,2023));
        cashFlows.add(new CashFlow("February", 50.0,7,2023));
        cashFlows.add(new CashFlow("March", 20.0,20,2023));
        return cashFlows;
    }

    private static double calculateWeightedCashFlows(List<CashFlow> cashFlows) {
        double totalWeightedCashFlow = 0.0;
        for (CashFlow cashFlow : cashFlows) {
            totalWeightedCashFlow += cashFlow.calculateWeightedCashFlow(cashFlow);
        }
        return totalWeightedCashFlow;
    }
    private static double calculateTotalCashFlow(List<CashFlow> cashFlowList) {
        double totalCashFlows =0.0;
        for (CashFlow cashFlow : cashFlowList) {
            totalCashFlows = cashFlow.computeTotalCashFlow(cashFlow);
        }
        return totalCashFlows;
    }


    private static double calculateDollarWeightedCashFlows(List<CashFlow> cashFlows) {
        double totalDollarWeightedCashFlow = 0.0;
        for (CashFlow cashFlow : cashFlows) {
            totalDollarWeightedCashFlow += cashFlow.calculateDollarWeightedCashFlow(cashFlow);
            System.out.println("Summation of totalDollarWeightedCashFlow this is a test"+totalDollarWeightedCashFlow);
        }
        System.out.println("totalDollarWeightedCashFlow ="+totalDollarWeightedCashFlow);
        return totalDollarWeightedCashFlow;
    }

    private static double calculateModifiedDietzRate(double initialBalance, double endingBalance, double totalCashFlow, List<CashFlow> cashFlows, double totalDollarWeightedCashFl) {
        System.out.println("Dietzrate return totalDollarWeightedCashFl " + totalDollarWeightedCashFl);
        return (endingBalance - initialBalance - totalCashFlow) / (initialBalance + totalDollarWeightedCashFl);
    }
}



