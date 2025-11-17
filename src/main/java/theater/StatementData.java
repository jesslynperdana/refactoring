package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is a placeholder for statement data.
 */
public class StatementData {
    private final Invoice invoice;
    private final List<PerformanceData> performances;

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.performances = new ArrayList<>();
        for (Performance performance : invoice.getPerformances()) {
            final Play play = plays.get(performance.getPlayID());
            this.performances.add(new PerformanceData(performance, play));
        }
    }

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public List<PerformanceData> getPerformances() {
        return performances;
    }

    /**
     * Calculates the total amount owed for all performances.
     * Logic moved from StatementPrinter.getTotalAmount().
     * @return the total amount
     */
    public int totalAmount() {
        int totalAmount = 0;
        for (PerformanceData performanceData : performances) {
            totalAmount += performanceData.amountFor();
        }
        return totalAmount;
    }

    /**
     * Calculates the total volume credits for all performances.
     * Logic moved from StatementPrinter.getTotalVolumeCredits().
     * @return the total volume credits
     */
    public int totalVolumeCredits() {
        int volumeCredits = 0;
        for (PerformanceData performanceData : performances) {
            // add volume credits
            volumeCredits += performanceData.volumeCreditsFor();
        }
        return volumeCredits;
    }
}
