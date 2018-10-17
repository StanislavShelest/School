package shelest.fine.data;

import java.util.Date;

public class FinancialDocument {
    private Date dayActionFinancialDocument;
    private Date dateDocument;
    private String type;
    private Double sum;

    public FinancialDocument(Date dayActionFinancialDocument, Date dateDocument, String type, Double sum) {
        this.dayActionFinancialDocument = dayActionFinancialDocument;
        this.dateDocument = dateDocument;
        this.type = type;
        this.sum = sum;
    }

    public Date getDayActionFinancialDocument() {
        return this.dayActionFinancialDocument;
    }

    public Date getDateDocument(){
        return this.dateDocument;
    }

    public String getType(){
     return this.type;
    }

    public double getSum() {
        return this.sum;
    }
}