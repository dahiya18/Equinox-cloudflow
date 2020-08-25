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
              <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="https://www.equinox.is/company"> <img class="container" id="logo" src={logo} height="auto" width="auto"/></a>
              <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
              </button>

            </nav>

            <div class="container-fluid">
              <div class="row">
                <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                  <div class="sidebar-sticky pt-3">
                    <ul class="nav flex-column">
                      <li class="nav-item">
                        <a class="nav-link active" href="http://localhost:3000/">
                          <span data-feather="home"></span>
                          Dashboard
                           <span class="sr-only">(current)</span>
                        </a>
                      </li>

                      <li class="nav-item">
                        <a class="nav-link" href="https://www.equinox.is/company">
                          <span data-feather="briefcase"></span>
                          Company
                        </a>
                      </li>

                    </ul>

                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                      <span>Project Details</span>
                      <a class="d-flex align-items-center text-muted" href="https://equinoxis.atlassian.net/wiki/spaces/CDF/pages/1644888065/Syllabus" aria-label="Add a new report">
                        <span data-feather="plus-circle"></span>
                      </a>
                    </h6>
                    <ul class="nav flex-column mb-2">
                      <li class="nav-item">
                        <a class="nav-link" href="https://equinoxis.atlassian.net/wiki/spaces/CDF/pages/1645150216/Source+-+Twitter">
                          <span data-feather="file-text"></span>
                          Twitter Source
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="https://equinoxis.atlassian.net/wiki/spaces/CDF/pages/1645215745/Source+-+Reddit">
                          <span data-feather="file-text"></span>
                          Reddit Source
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="https://equinoxis.atlassian.net/wiki/spaces/CDF/pages/1644888081/Source+-+Stock+Exchange">
                          <span data-feather="file-text"></span>
                          Stock Market Source
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="https://stanfordnlp.github.io/CoreNLP/">
                          <span data-feather="file-text"></span>
                          Sentiment Analysis
                        </a>
                      </li>
                    </ul>

                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                      <span>Contributors</span>
                      <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
                        <span data-feather="users"></span>
                      </a>
                    </h6>

                    <ul class="nav flex-column mb-2">
                      <li class="nav-item">
                        <a class="nav-link" href="linkedin.com/in/danielpetyus">
                          <span data-feather="user"></span>
                          Daniel Petyus
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="linkedin.com/in/gbhatia30">
                          <span data-feather="user"></span>
                          Gagan Bhatia
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="www.linkedin.com/in/harmeeta-dahiya">
                          <span data-feather="user"></span>
                          Harmeeta Dahiya
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="linkedin.com/in/nikita-maksimov">
                          <span data-feather="user"></span>
                          Nikita Maksimov
                        </a>
                      </li>
                    </ul>

                  </div>
                </nav>

                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">Dashboard</h1>

                  </div>


                  <DataPointsComponent/>

                </main>
              </div>
            </div>


        </div>
      );
    }
}

export default GraphApp