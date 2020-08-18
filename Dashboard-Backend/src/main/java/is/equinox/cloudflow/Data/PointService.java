package is.equinox.cloudflow.Data;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class PointService {

    private List<DataPoint> points = new ArrayList<>();
    private long idCounter = 0;

    public PointService() {
        points.add(new DataPoint(idCounter, new Date(0).getTime() , 0));
        idCounter++;
        points.add(new DataPoint(idCounter,new Date(1).getTime(),1));
        idCounter++;
        points.add(new DataPoint(idCounter,new Date(2).getTime(), 2));
        System.out.println("Finished\n");
    }

    public void addPoints(Date d, int senti) {
        points.add( new DataPoint(idCounter, d.getTime(), senti));
        idCounter++;
    }

    public List<DataPoint> findAll() {
        return points;
    }

}
