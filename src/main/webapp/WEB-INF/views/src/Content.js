import React from "react"
import {Route, Switch} from "react-router-dom";
import Passwords from "./Passwords/Passwords";
import Homepage from "./Homepage";
import Users from "./Users/Users";
import Allusers from "./Users/Allusers";
import Apps from "./Apps/Apps";
import CreateUser from "./Users/CreateUser";
import CreateApp from "./Apps/CreateApp";

function Content() {
    return (
        <div id="main" class="container">

            <Switch>
                <Route path='/passwords' component={Passwords}/>
                <Route exact path='/' component={Homepage}/>
                <Route path='/allusers' component={Allusers}/>
                <Route path='/apps' component={Apps}/>
                <Route path='/createuser' component={CreateUser}/>
                <Route path='/createapp' component={CreateApp}/>
            </Switch>

        </div>
    )
}

export default Content