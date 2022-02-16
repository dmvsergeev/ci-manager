import React from "react"

import Header from "./layouts/Header"
import Footer from "./layouts/Footer"
import Content from "./Content";

import regeneratorRuntime from "regenerator-runtime";

const App = () => (

        <div className="mainapp">
          <Header />
            <div className="maincontent">
              <Content />
            </div>
          <Footer />
        </div>

)

export default App;