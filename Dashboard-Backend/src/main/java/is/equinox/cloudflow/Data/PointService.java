package is.equinox.cloudflow.Data;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service

public class PointService {

    private List<DataPoint> pointsTwitter = new ArrayList<>();
    private List<DataPoint> pointsReddit = new ArrayList<>();
    private List<DataPoint> pointsStock = new ArrayList<>();

    private long idCounterTwitter = 0;
    private long idCounterReddit = 0;
    private long idCounterStock = 0;

    private long sentimentTwitter = 0;
    private long sentimentReddit = 0;

    private long twitterLikes = 0;
    private long redditLikes = 0;

    public PointService() {

    }

    public void check(){
        if(idCounterTwitter==0 && pointsTwitter.size()>0) pointsTwitter.clear();
        if(idCounterReddit==0 && pointsReddit.size()>0) pointsReddit.clear();
        if(idCounterStock==0 && pointsReddit.size()>0) pointsStock.clear();
    }

    public void ReadTwitter(){
        check();
        String csvFile ="C:/Users/harme/Desktop/cloudflow/Dashboard-Backend/src/main/java/is/equinox/cloudflow/Data/twitter_data.csv" ;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);

                SimpleDateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
                Date timestamp = df.parse(data[0]);
                long sentiment = (Long.parseLong(data[3])+1)*sentimentValue(data[2]);
                if(sentimentValue(data[2]) > 0) twitterLikes ++;

                pointsTwitter.add(new DataPoint(idCounterTwitter,timestamp,sentiment));
                idCounterTwitter++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void ReadReddit(){
        check();
        String csvFile ="C:/Users/harme/Desktop/cloudflow/Dashboard-Backend/src/main/java/is/equinox/cloudflow/Data/reddit_data.csv" ;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);

                SimpleDateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
                Date timestamp = df.parse(data[0]);
                long sentiment = (Long.parseLong(data[3])+1)*sentimentValue(data[2]);
                if(sentimentValue(data[2]) > 0) redditLikes++;

                pointsReddit.add(new DataPoint(idCounterReddit,timestamp,sentiment));
                idCounterReddit++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void ReadStock(){
        check();
        String csvFile ="./Dashboard-Backend/src/main/java/is/equinox/cloudflow/Data/stock_data.csv" ;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);

                SimpleDateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
                Date timestamp = df.parse(data[0]);
                double value = Double.parseDouble(data[1]);
                pointsStock.add(new DataPoint(idCounterStock,timestamp,(long)value));
                idCounterStock++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private long sentimentValue(String s){
        if(s.equals("VERY_NEGATIVE")) return 0;
        if(s.equals("NEGATIVE")) return 1;
        if(s.equals("NEUTRAL")) return 2;
        if(s.equals("POSITIVE")) return 3;
        if(s.equals("VERY_POSITIVE")) return 4;
        else return 0;
    }

    public List<DataPoint> findAllTwitter() {

        /*** sort the list in ascending order according to date *****/
        Collections.sort(pointsTwitter, new Comparator<DataPoint>() {
            @Override
            public int compare(DataPoint o1, DataPoint o2) {
                if(o1.getDate().before(o2.getDate())){
                    return -1;
                }
                return 1;
            }
        });
        /******Add the previous sentiment to get a series ******/

        for(int i = 1; i<pointsTwitter.size(); i++){

            long id = pointsTwitter.get(i).getID();
            Date date = pointsTwitter.get(i).getDate();
            long sentiment = pointsTwitter.get(i).getSentiment() + pointsTwitter.get(i-1).getSentiment();

            pointsTwitter.set(i, new DataPoint(id,date,sentiment));
        }

        return pointsTwitter;
    }

    public List<DataPoint> findAllReddit() {

        Collections.sort(pointsReddit, new Comparator<DataPoint>() {
            @Override
            public int compare(DataPoint o1, DataPoint o2) {
                if(o1.getDate().before(o2.getDate())){
                    return -1;
                }
                return 1;
            }
        });
        /********************************************/

        for(int i = 1; i<pointsReddit.size(); i++){

            long id = pointsReddit.get(i).getID();
            Date date = pointsReddit.get(i).getDate();
            long sentiment = pointsReddit.get(i).getSentiment() + pointsReddit.get(i-1).getSentiment();

            pointsReddit.set(i, new DataPoint(id,date,sentiment));
        }
        return pointsReddit;
    }

    public List<DataPoint> findAllStock(){
        return pointsStock;
    }

    public long getTwitterLikes(){
        return twitterLikes;
    }

    public long getRedditLikes(){
        return redditLikes;
    }


    public void delete(){

        //pointsTwitter.clear();
        //pointsReddit.clear();
        //pointsStock.clear();

        idCounterTwitter = 0;
        idCounterReddit = 0;
        idCounterStock = 0;

        sentimentTwitter = 0;
        sentimentReddit = 0;

        twitterLikes = 0;
        redditLikes = 0;
        System.out.println("\n\n\n\n\n\n\n finalize done\n\n\n\n\n");
    }


}
