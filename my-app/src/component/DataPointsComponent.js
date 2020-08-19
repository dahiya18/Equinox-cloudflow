import React,{Component} from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import DataPointService from '../service/DataPointService';

import Highcharts from 'highcharts/highstock';
import HighchartsReact from 'highcharts-react-official';


class DataPointsComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
          points : [{id:1,date:(new Date(111)).getTime(),sentiment:100},{}]
        }
        this.refreshPoints = this.refreshPoints.bind(this)
    }


    refreshPoints() {

        DataPointService.getAllPoints()
            .then(response => this.setState({
                points : response.data
            }))

    }

    componentDidMount() {
        this.refreshPoints();
    }
    render(){

        return (
            <div>
            <h1> Graph points to be plotted </h1>
            <div className="conatiner">
                <table className="table">
                    <thead>
                        <tr>
                            <th> ID </th>
                            <th> Date </th>
                            <th> Sentiment </th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.points.map(
                            point =>
                                <tr key={point.id}>
                                    <td>{point.id}</td>
                                    <td>{point.date}</td>
                                    <td>{point.sentiment}</td>
                                </tr>
                        )
                    }

                    </tbody>
                </table>
                <div id="container">
                                  <HighchartsReact
                                  class = "highcharts-figure"
                                  highcharts={Highcharts}
                                  options={{

                                                               chart: {
                                                                 zoomType: 'x'
                                                               },

                                                               title: {
                                                                 text: 'Highcharts drawing ' + 50000 + ' points'
                                                               },

                                                               subtitle: {
                                                                 text: 'Social Response'
                                                               },

                                                               tooltip: {
                                                                 valueDecimals: 2
                                                               },

                                                               xAxis: {
                                                                 type: 'datetime'
                                                               },

                                                               series: [{
                                                                 data: this.state.points.map(p => [p.date,p.sentiment]),
                                                                 lineWidth: 0.5,
                                                                 name: 'Hourly data points'
                                                               }]

                                                           }}
                />
                </div>
            </div>
            </div>

        )
    }

}

export default DataPointsComponent

