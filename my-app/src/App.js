import React, {Component} from 'react';
import logo from './logo.png';
import './App.css';


class App extends React.Component {

  render () {
    return (
    <div className="App">
      <header className="App-header">
      <div class="topnav">
        <a class="active" href="#home">Home</a>
        <a href="#project">Project Details</a>
        <a href="#contact">Contact</a>
        <a href="#about">About</a>
      </div>
        <h2> CLOUDFLOW </h2>
          Enter the company name to perform sentiment analysis.
        <form>
          <label>
            Company :
            <input type="text" name="name" />
          </label>
          <button onClick={() => alert('Click')}>
            Visualize
          </button>
        </form>
    {/*    <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Generate graph
        </a> */}
      </header>
    </div>
  );
}
}

export default App;
//<img src={logo} className="App-logo" alt="logo" />
