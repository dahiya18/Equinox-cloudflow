package is.equinox.cloudflow.source.twitter;


import java.util.Date;


class SentimentScore {
    private final Date timestamp;
    private final long id;
    private final SentimentType polarity;
    private final int user;

    SentimentScore(Date timestamp, long id, SentimentType polarity, int user) {
        this.timestamp = timestamp;
        this.id = id;
        this.polarity = polarity;
        this.user = user;
    }

    Date getTimestamp() {
        return timestamp;
    }

    long getId() {
        return id;
    }

    SentimentType getPolarity() {
        return polarity;
    }

    int getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "{" +
                "timestamp=" + timestamp +
                ", id='" + id + '\'' +
                ", sentiment=" + polarity +
                ", score=" + user +
                '}'+ '\n';
    }

}


