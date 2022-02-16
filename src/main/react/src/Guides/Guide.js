import {Component} from "react";

import regeneratorRuntime from "regenerator-runtime";

class Guide extends Component {
    state = {
        guides: []
    };

    async componentDidMount() {

    var id_guide = new URLSearchParams(this.props.location.search).get("id_guide")

        const response = await fetch('/api/guides/get/' + id_guide);
        const body = await response.json();
        this.setState({guides: body});
    }

    render() {
        const {guides} = this.state;
        return (

            <div>

            <h2>{guides.title}</h2>
            <div class="maintext">
                {guides.content}
            </div>

            </div>

        );
    }
}
export default Guide;