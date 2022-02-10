import React from "react"
import {Route, Switch} from "react-router-dom";
import Passwords from "./Passwords/Passwords";
import Homepage from "./Homepage";
import Users from "./Users/Users";
import Allusers from "./Users/Allusers";
import Apps from "./Apps/Apps";
import News from "./News/News";
import Story from "./News/Story";
import Guides from "./Guides/Guides";
import Guide from "./Guides/Guide";
import CreateUser from "./Users/CreateUser";
import CreateApp from "./Apps/CreateApp";
import SetPassword from "./Passwords/SetPassword";
import TopMenu from "./Menu/TopMenu";

function Content() {
    return (
        <div id="main" class="container">

            <Switch>
                <Route path='/passwords' component={Passwords}/>
                <Route exact path='/' component={Homepage}/>
                <Route path='/allusers' component={Allusers}/>
                <Route path='/apps' component={Apps}/>
                <Route path='/news' component={News}/>
                <Route path='/story' component={Story}/>
                <Route path='/guides' component={Guides}/>
                <Route path='/guide' component={Guide}/>
                <Route path='/createuser' component={CreateUser}/>
                <Route path='/createapp' component={CreateApp}/>
                <Route path='/setpassword' component={SetPassword}/>
            </Switch>

        </div>
    )
}

export default Content