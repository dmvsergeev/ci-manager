import {Component} from "react";

import regeneratorRuntime from "regenerator-runtime";

class Guides extends Component {
    state = {
        guides_list: []
    };

    async componentDidMount() {
        const response = await fetch('/api/guides');
        const body = await response.json();
        this.setState({guides_list: body});
    }

    render() {
        const {guides_list} = this.state;
        return (

            <div>
                <h2>Инструкции</h2>
                <table id="guides_list" class="table table-striped text-left">
                    <thead>
                    <tr><th>Название</th><th>Содержание</th></tr>
                    </thead>
                    <tbody>
                {guides_list.map(guide_item =>
                    <tr key={guide_item.id}>
                        <td>
                        <a href={ '/guide?id_guide=' + guide_item.id}>
                                                    <b>{guide_item.title}</b>
                                                </a>
                                                <a class="more" href={ '/guide?id_guide=' + guide_item.id}>
                                                    (подробнее)
                                                </a>
                        </td>
                        <td>{guide_item.content}</td>
                    </tr>
                )}
                    </tbody>
                </table>
            </div>

        );
    }
}
export default Guides;