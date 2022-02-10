import {Component} from "react";

import regeneratorRuntime from "regenerator-runtime";

class Story extends Component {
    state = {
        news: []
    };

    async componentDidMount() {

    var id_news = new URLSearchParams(this.props.location.search).get("id_news")

    console.log("#" + id_news);

        const response = await fetch('/api/news/get/' + id_news);
        const body = await response.json();
        this.setState({news: body});
    }

    render() {
        const {news} = this.state;
        return (

            <div>

            <h2>{news.title}</h2>
            <div class="maintext">
                {news.content}
            </div>

            </div>

        );
    }
}
export default Story;