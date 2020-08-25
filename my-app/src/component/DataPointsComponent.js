import React,{Component} from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import DataPointService from '../service/DataPointService';

import Highcharts from 'highcharts/highstock';
import HighchartsReact from 'highcharts-react-official';


class DataPointsComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
          points : [
                        [{id:1,date:(new Date(0)),sentiment:0},{}],
                        [{id:1,date:(new Date(0)),sentiment:0},{}],
                        [{id:1,date:(new Date(0)),sentiment:0},{}]
                   ],
          twitterPoints : [{id:1, date: (new Date(0)),sentiment: 0}, {}],
          redditPoints : [{id:1,date: (new Date(0)),sentiment: 0}, {}],
          stockPoints : [{id:1,date: (new Date(0)),sentiment: 0}, {}]
        }
        this.refreshPoints = this.refreshPoints.bind(this)
    }


    refreshPoints() {

        DataPointService.getPoints()
            .then(response => this.setState({
                points : response.data
            }));

    }

    componentDidMount() {
        this.refreshPoints();
    }
    render(){

        return (
            <div>

            <div className="conatiner">
                <div id="container">
                                  <HighchartsReact
                                  class = "highcharts-figure"
                                  highcharts={Highcharts}
                                  constructorType={'stockChart'}
                                  options={{

                                                               chart: {
                                                                 zoomType: 'x',


                                                               },

                                                               title: {
                                                                 text: 'Twitter Social Response',

                                                               },

                                                               subtitle: {
                                                                 text: 'tweet sentiment'
                                                               },

                                                               tooltip: {
                                                                 valueDecimals: 2
                                                               },

                                                               xAxis: {
                                                                 type: 'datetime'
                                                               },



                                                               series: [{
                                                                 data: this.state.points[0].map(p => [Date.parse(p.date),parseInt(p.sentiment)]),
                                                                 lineWidth: 1.5,
                                                                 name: ''
                                                               }]

                                                           }}
                                  />


                                   <p> </p>
                                  <HighchartsReact
                                  class = "highcharts-figure"
                                  highcharts={Highcharts}
                                  constructorType={'stockChart'}
                                  options={{

                                                               chart: {
                                                                 zoomType: 'x',

                                                               },

                                                               title: {
                                                                 text: 'Reddit Social Response'
                                                               },

                                                               subtitle: {
                                                                 text: 'reddit sentiment'
                                                               },

                                                               tooltip: {
                                                                 valueDecimals: 0
                                                               },

                                                               xAxis: {
                                                                 type: 'datetime'
                                                               },

                                                               series: [{
                                                                 data: this.state.points[1].map(p => [Date.parse(p.date),parseInt(p.sentiment)]),
                                                                 lineWidth: 1.5,
                                                                 name: ''
                                                               }]

                                                           }}
                                  />

                                   <p> </p>
                                  <HighchartsReact
                                  class = "highcharts-figure"
                                  highcharts={Highcharts}
                                  constructorType={'stockChart'}
                                  options = {{
                                                                  chart: {
                                                                    zoomType: 'x',



                                                                  },

                                                                  title: {
                                                                    text: ' Stock Market Behaviour'
                                                                  },

                                                                  subtitle: {
                                                                    text: 'Stock prices'
                                                                  },

                                                                  tooltip: {
                                                                    valueDecimals: 0
                                                                  },

                                                                  xAxis: {
                                                                    type: 'datetime'
                                                                  },

                                                                  series: [{
                                                                    data: this.state.points[2].map(p => [Date.parse(p.date), p.sentiment]),
                                                                    lineWidth: 1.5,
                                                                    name: ''
                                                                  }]

                                                               }}

                               />
                                <p> </p>

                </div>

            </div>
            </div>

        )
    }

}

export default DataPointsComponent

