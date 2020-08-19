import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import DataPointService from '../service/DataPointService'
import {feather} from 'feather-icons';
import logo from '../equinox-logo.svg';

import Highcharts from 'highcharts/highstock';
import HighchartsReact from 'highcharts-react-official';
import DataPointsComponent from './DataPointsComponent';

import Chart from 'chart.js';
import {Helmet} from "react-helmet";
import './GraphApp.css';


class GraphApp extends Component {

    constructor(props) {
        super(props);
        this.getData = this.getData.bind(this);

        this.optionsStock = {

              chart: {
                zoomType: 'x'
              },

              title: {
                text: 'Highcharts drawing ' + 50000 + ' points'
              },

              subtitle: {
                text: 'Stock Market'
              },

              tooltip: {
                valueDecimals: 2
              },

              xAxis: {
                type: 'datetime'
              },

              series: [{
                data: this.getData(),
                lineWidth: 0.5,
                name: 'Hourly data points'
              }]

          };
    }

    /* Dummy function to get data points */
       getData() {
        var arr = [],
          i,
          x,
          a,
          b,
          c,
          spike;
        var n = 50000;
        for (
          i = 0, x = Date.UTC(new Date().getUTCFullYear(), 0, 1) - n * 36e5;
          i < n;
          i = i + 1, x = x + 36e5
        ) {
          if (i % 100 === 0) {
            a = 2 * Math.random();
          }
          if (i % 1000 === 0) {
            b = 2 * Math.random();
          }
          if (i % 10000 === 0) {
            c = 2 * Math.random();
          }
          if (i % 50000 === 0) {
            spike = 10;
          } else {
            spike = 0;
          }
          arr.push([
            x,
            2 * Math.sin(i / 100) + a + b + c + spike + Math.random()
          ]);
        }
        return arr;
    }

    render () {
        return (
        <div>

              <meta charset="utf-8" />
              <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
              <meta name="description" content="" />
              <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
              <meta name="generator" content="Jekyll v4.0.1" />
              <title>Dashboard Template · Bootstrap</title>


             { /* Bootstrap core CSS */ }
            <Helmet>
            <link href="https://getbootstrap.com/docs/4.5/dist/css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous" />
            <link rel="apple-touch-icon" href="https://getbootstrap.com/docs/4.5/assets/img/favicons/apple-touch-icon.png" sizes="180x180" />
            <link rel="icon" href="https://getbootstrap.com/docs/4.5/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png" />
            <link rel="icon" href="https://getbootstrap.com/docs/4.5/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png" />
            <link rel="manifest" href="https://getbootstrap.com/docs/4.5/assets/img/favicons/manifest.json" />
            <link rel="mask-icon" href="https://getbootstrap.com/docs/4.5/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c" />
            <link rel="icon" href="https://getbootstrap.com/docs/4.5/assets/img/favicons/favicon.ico" />
            </Helmet>
            <Helmet>
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous" />
                  if (typeof jQuery == 'undefined') {
                    document.write('<script src="https://getbootstrap.com/docs/4.5/assets/js/vendor/jquery.slim.min.js" />')
                  }
                <script src="https://getbootstrap.com/docs/4.5/dist/js/bootstrap.bundle.min.js"  crossorigin="anonymous"/>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"/>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"/>
                <script src="https://getbootstrap.com/docs/4.5/examples/dashboard/dashboard.js"/>
                <script src="https://code.highcharts.com/highcharts.js" />
                <script src="https://code.highcharts.com/modules/boost.js" />
                <script src="https://code.highcharts.com/modules/exporting.js" />
            </Helmet>
            <nav class="navbar navbar-dark sticky-top flex-md-nowrap p-0 shadow" style = {{backgroundColor:"#5F3EA7"}}>
              <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#"> <img class="container" id="logo" src={logo} height="auto" width="auto"/></a>
              <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
              </button>
                <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search" />
                <ul class="navbar-nav px-3">
                  <li class="nav-item text-nowrap">
                    <a class="nav-link" href="#">Sign out</a>
                  </li>
                </ul>
            </nav>

            <div class="container-fluid">
              <div class="row">
                <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                  <div class="sidebar-sticky pt-3">
                    <ul class="nav flex-column">
                      <li class="nav-item">
                        <a class="nav-link active" href="#">
                          <span data-feather="home"></span>
                          Dashboard
                           <span class="sr-only">(current)</span>
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#">
                          <span data-feather="file"></span>
                          Details
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#">
                          <span data-feather="briefcase"></span>
                          Company
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#">
                          <span data-feather="users"></span>
                          Contributors
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#">
                          <span data-feather="bar-chart-2"></span>
                          Reports
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#">
                          <span data-feather="layers"></span>
                          Integrations
                        </a>
                      </li>
                    </ul>

                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                      <span>Saved reports</span>
                      <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
                        <span data-feather="plus-circle"></span>
                      </a>
                    </h6>
                    <ul class="nav flex-column mb-2">
                      <li class="nav-item">
                        <a class="nav-link" href="#">
                          <span data-feather="file-text"></span>
                          Current month
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#">
                          <span data-feather="file-text"></span>
                          Last quarter
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#">
                          <span data-feather="file-text"></span>
                          Social response
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#">
                          <span data-feather="file-text"></span>
                          Year-end stats
                        </a>
                      </li>
                    </ul>
                  </div>
                </nav>

                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">Dashboard</h1>
                    <div class="btn-toolbar mb-2 mb-md-0">
                      <div class="btn-group mr-2">
                        <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
                        <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
                      </div>
                      <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <span data-feather="calendar"></span>
                        This week
                      </button>
                    </div>
                  </div>

                 {/* <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas> */}
                  <DataPointsComponent/>
                  <div id = "container">
                  <HighchartsReact
                  class = "highcharts-figure"
                  highcharts={Highcharts}
                  constructorType={'stockChart'}
                  options={this.optionsStock}

                 />
                  </div>


                  <p class="highcharts-description">
                Using the Highcharts Boost module, it is possible to render large amounts
                of data on the client side. These chart shows a line series with large set of
                data points. Click and drag in the chart to zoom in.
                  </p>



                </main>
              </div>
            </div>


        </div>
      );
    }
}

export default GraphApp