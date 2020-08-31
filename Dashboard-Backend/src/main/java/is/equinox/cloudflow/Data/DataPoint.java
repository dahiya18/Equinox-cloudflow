package is.equinox.cloudflow.Data;

import java.util.Date;

public class DataPoint {
    private Date date;
    private long sentiment;
    private long id;

    public DataPoint( long id, Date date, long sentiment){
        this.date = date;
        this.sentiment = sentiment;
        this.id = id;
    }

    public Date getDate(){
        return this.date;
    }

    public long getSentiment(){
        return this.sentiment;
    }

    public long getID(){
        return this.id;
    }
}

