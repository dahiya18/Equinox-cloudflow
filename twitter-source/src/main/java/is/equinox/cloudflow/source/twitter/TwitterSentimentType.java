package is.equinox.cloudflow.source.twitter;

enum SentimentType {

    VERY_NEGATIVE(0), NEGATIVE(1), NEUTRAL(2), POSITIVE(3), VERY_POSITIVE(4);

    int value;

    SentimentType(int value) {
        this.value = value;
    }

    public static SentimentType fromValue(int value) {
        for (SentimentType typeSentiment: values()) {
            if (typeSentiment.value == value) {
                return typeSentiment;
            }
        }

        return SentimentType.NEUTRAL;
    }
}
