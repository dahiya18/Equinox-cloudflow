package is.equinox.cloudflow.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200"})
@RestController
public class PointResource {

    @Autowired
    is.equinox.cloudflow.Data.PointService pointService;

    @GetMapping("/")
    public List<is.equinox.cloudflow.Data.DataPoint> getAllPoints(){
        return pointService.findAll();
    }

}