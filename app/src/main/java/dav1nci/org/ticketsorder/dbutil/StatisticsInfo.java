package dav1nci.org.ticketsorder.dbutil;

import java.util.Date;

/**
 * Created by dav1nci on 10.12.15.
 */
public class StatisticsInfo
{
    private Date date;
    private double profit;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
