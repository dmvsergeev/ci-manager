import React from "react"
import {Route, Switch} from "react-router-dom";
import Passwords from "./Passwords/Passwords";
import Homepage from "./Homepage";
import Users from "./Users/Users";
import Allusers from "./Users/Allusers";

function Content() {
    return (
        <div id="main" class="container">

            <Switch>
                <Route path='/passwords' component={Passwords}/>
                <Route exact path='/' component={Homepage}/>
                <Route path='/allusers' component={Allusers}/>
            </Switch>

        </div>
    )
}

export default Content