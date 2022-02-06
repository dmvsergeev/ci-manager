import {Component} from "react";

import regeneratorRuntime from "regenerator-runtime";
import { Link } from 'react-router-dom'

class TopMenu extends Component {
    state = {
        links_list: []
    };

    async componentDidMount() {
        const response = await fetch('/api/topmenu');
        const body = await response.json();
        this.setState({links_list: body});
    }

    render() {
        const {links_list} = this.state;
        return (

            <div>
                <ul className="topnav">
                {links_list.map(link_item =>
                    <li><Link className="btn btn-default btn-sm" to={link_item.link}>{link_item.name}</Link></li>
                )}
                </ul>

            </div>

        );
    }
}
export default TopMenu;