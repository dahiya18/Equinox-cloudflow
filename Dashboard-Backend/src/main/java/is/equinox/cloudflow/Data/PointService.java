package is.equinox.cloudflow.Data;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class PointService {

    private static List<DataPoint> points = new ArrayList<>();
    private static long idCounter = 0;

    static {
        points.add(new DataPoint(idCounter, new Date(0).getTime() , 0));
        idCounter++;
        points.add(new DataPoint(idCounter,new Date(1).getTime(),1));
        idCounter++;
        points.add(new DataPoint(idCounter,new Date(2).getTime(), 2));
        System.out.println("Finished\n");
    }

    public static void addPoints(Date d, int senti) {
        points.add( new DataPoint(idCounter, d.getTime(), senti));
        idCounter++;
    }

    public List<DataPoint> findAll() {
        return points;
    }

}
