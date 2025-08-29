/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.model;

/**
 *
 * @author kithu
 */
public class BranchProfit {
    private String branchName;
    private String month;        // e.g. "2025-01"
    private double income;
    private double expense;
    private double profit;       // income - expense

    public BranchProfit(String branchName, String month, double income, double expense) {
        this.branchName = branchName;
        this.month = month;
        this.income = income;
        this.expense = expense;
        this.profit = income - expense;
    }

    public String getBranchName() { return branchName; }
    public String getMonth() { return month; }
    public double getIncome() { return income; }
    public double getExpense() { return expense; }
    public double getProfit() { return profit; }

    public String getProfitFormatted() {
        return (profit >= 0 ? "+" : "-") + Math.abs(profit);
    }
}
