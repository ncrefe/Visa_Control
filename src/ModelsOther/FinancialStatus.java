package ModelsOther;

public class FinancialStatus {
    private int income;
    private int savings;

    public FinancialStatus(String[] data) {
        this.income = Integer.parseInt(data[2]);
        this.savings = Integer.parseInt(data[3]);
    }

    public FinancialStatus(FinancialStatus financialStatus) {
        if (financialStatus == null)
            return;
        this.income = financialStatus.getIncome();
        this.savings = financialStatus.getSavings();
    }

    public int getIncome() {
        return income;
    }

    public int getSavings() {
        return savings;
    }

    @Override
    public String toString() {
        return "FinancialStatus [income=" + income + ", savings=" + savings + "]";
    }

}
