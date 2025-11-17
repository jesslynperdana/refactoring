package theater;

/**
 * This class is a placeholder for performance data.
 */
public class PerformanceData {
    private final Performance performance;
    private final Play play;

    public PerformanceData(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public int getAudience() {
        return performance.getAudience();
    }

    public String getName() {
        return play.getName();
    }

    public String getType() {
        return play.getType();
    }

    public int amountFor() {
        int result;
        final int audience = getAudience();

        switch (getType()) {
            case "tragedy":
                result = Constants.TRAGEDY_BASE_AMOUNT;
                if (audience > Constants.TRAGEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.TRAGEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (audience - Constants.TRAGEDY_AUDIENCE_THRESHOLD);
                }
                break;
            case "comedy":
                result = Constants.COMEDY_BASE_AMOUNT;
                if (audience > Constants.COMEDY_AUDIENCE_THRESHOLD) {
                    result += Constants.COMEDY_OVER_BASE_CAPACITY_AMOUNT
                            + (Constants.COMEDY_OVER_BASE_CAPACITY_PER_PERSON
                            * (audience - Constants.COMEDY_AUDIENCE_THRESHOLD));
                }
                result += Constants.COMEDY_AMOUNT_PER_AUDIENCE * audience;
                break;
            default:
                throw new RuntimeException(String.format("unknown type: %s", getType()));
        }
        return result;
    }

    public int volumeCreditsFor() {
        int result = 0;
        final int audience = getAudience();
        result += Math.max(audience - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        // add extra credit for every five comedy attendees
        if ("comedy".equals(getType())) {
            result += audience / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return result;
    }
}
