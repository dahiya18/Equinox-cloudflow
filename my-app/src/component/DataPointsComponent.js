import React,{Component} from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import DataPointService from '../service/DataPointService';

import Highcharts from 'highcharts/highstock';
import HighchartsReact from 'highcharts-react-official';

var twitter;
var reddit;
var stock;
var seriesOptions;
class DataPointsComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
          points : [
                        [{id:0,date:(new Date("2020/07/15")),sentiment:0}],
                        [{id:0,date:(new Date("2020/07/15")),sentiment:1}],
                        [{id:0,date:(new Date("2020/07/15")),sentiment:2}]
                   ]
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
         twitter = this.state.points[0].map(p => [Date.parse(p.date),parseInt(p.sentiment)])
         reddit = this.state.points[1].map(p => [Date.parse(p.date),parseInt(p.sentiment)])
         stock = this.state.points[2].map(p => [Date.parse(p.date),parseInt(p.sentiment)])
         seriesOptions = [
                            {name: 'TWITTER', data: twitter},
                            {name: 'REDDIT', data: reddit},
                            {name: 'STOCK', data: stock}
                         ]

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

                                                                rangeSelector: {
                                                                           selected: 4
                                                                       },



                                                               plotOptions: {
                                                                   series: {
                                                                       compare: 'percent',
                                                                       showInNavigator: true
                                                                   }
                                                               },

                                                               tooltip: {
                                                                   pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
                                                                   valueDecimals: 2,
                                                                   split: true
                                                               },



                                                               series: seriesOptions

                                                           }}
                                  />



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

