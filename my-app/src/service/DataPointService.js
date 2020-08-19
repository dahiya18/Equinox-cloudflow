import axios from 'axios'

const URL = 'http://localhost:8080/'

class DataPointService {

    getAllPoints() {
        console.log('executed dataPointService in frontend \n');
        var res = axios.get(URL);
        console.log("RESPONSE RECEIVED FROM BACKEND: " + res + "\n\n\n");

        return axios.get(URL);
    }

}

export default new DataPointService();