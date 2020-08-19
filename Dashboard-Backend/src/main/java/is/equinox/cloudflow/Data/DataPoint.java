package is.equinox.cloudflow.Data;

public class DataPoint {
    private long date;
    private int sentiment;
    private long id;

    public DataPoint( long id, long date, int sentiment){
        this.date = date;
        this.sentiment = sentiment;
        this.id = id;
    }

    public long getDate(){
        return this.date;
    }

    public int getSentiment(){
        return this.sentiment;
    }

    public long getID(){
        return this.id;
    }
}

