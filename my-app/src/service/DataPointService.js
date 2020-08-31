import axios from 'axios'

class DataPointService {

    getPoints(){
        return axios.get('http://localhost:8080/');
    }
}

export default new DataPointService();