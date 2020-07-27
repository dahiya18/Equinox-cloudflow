package is.equinox.cloudflow.source.reddit;


import java.util.Date;


class SentimentScore {
    private final Date timestamp;
    private final String id;
    private final SentimentType polarity;
    private final int weight;

    SentimentScore(Date timestamp, String id, SentimentType polarity, int weight) {
        this.timestamp = timestamp;
        this.id = id;
        this.polarity = polarity;
        this.weight = weight;
    }

    Date getTimestamp() {
        return timestamp;
    }

    String getId() {
        return id;
    }

    SentimentType getPolarity() {
        return polarity;
    }

    int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "SentimentScore {" +
                "timestamp=" + timestamp +
                ", id='" + id + '\'' +
                ", sentiment=" + polarity +
                ", score=" + weight +
                '}'+ '\n';
    }

}


