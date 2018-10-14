package shelest.fine.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fine {
    private Date debtDayForPeriod;
    private Double sum;

    public Fine (Date debtDayForPeriod, Double sum){
        this.debtDayForPeriod = debtDayForPeriod;
        this.sum = sum;
    }

    public Date getDebtDayForPeriod(){
        return this.debtDayForPeriod;
    }

    public double getSum(){
        return this.sum;
    }
}
