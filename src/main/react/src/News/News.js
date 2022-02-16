import {Component} from "react";

import regeneratorRuntime from "regenerator-runtime";

class News extends Component {
    state = {
        news_list: []
    };

    async componentDidMount() {
        const response = await fetch('/api/news');
        const body = await response.json();
        this.setState({news_list: body});
    }

    render() {
        const {news_list} = this.state;
        return (

            <div>
                <h2>Список приложений</h2>
                <table id="news_list" class="table table-striped text-left">
                    <thead>
                    <tr><th>Тема</th><th>Содержание</th></tr>
                    </thead>
                    <tbody>
                {news_list.map(news_item =>
                    <tr key={news_item.id.id}>
                        <td>
                        <a href={ '/story?id_news=' + news_item.id.id}>
                            <b>{news_item.title}</b>
                        </a>

                        <a class="more" href={ '/story?id_news=' + news_item.id.id}>
                            (подробнее)
                        </a>

                        </td>
                        <td>{news_item.content}</td>
                    </tr>
                )}
                    </tbody>
                </table>
            </div>

        );
    }
}
export default News;