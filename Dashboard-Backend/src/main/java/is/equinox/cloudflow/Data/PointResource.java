package is.equinox.cloudflow.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200"})
@RestController
public class PointResource {

    @Autowired
    is.equinox.cloudflow.Data.PointService pointService;

    @GetMapping("/")
    public List<List<is.equinox.cloudflow.Data.DataPoint>> getPoints() {

        List<List<is.equinox.cloudflow.Data.DataPoint>> list = new ArrayList<>();
        pointService.check();
        pointService.ReadTwitter();
        pointService.check();
        pointService.ReadReddit();
        pointService.check();
        pointService.ReadStock();
        pointService.check();

        list.add(pointService.findAllTwitter());
        pointService.check();
        list.add(pointService.findAllReddit());
        pointService.check();
        list.add(pointService.findAllStock());

        pointService.delete();

        return list;
    }

}
