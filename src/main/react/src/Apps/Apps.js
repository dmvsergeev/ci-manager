import {Component} from "react";

import regeneratorRuntime from "regenerator-runtime";

class Apps extends Component {
    state = {
        apps_list: []
    };

    async componentDidMount() {
        const response = await fetch('/api/apps');
        const body = await response.json();
        this.setState({apps_list: body});
    }

    render() {
        const {apps_list} = this.state;
        return (

            <div>
                <h2>Список приложений</h2>
                <table id="apps_list" class="table table-striped text-left">
                    <thead>
                    <tr><th>Название приложения</th><th>URL</th><th>Действие</th></tr>
                    </thead>
                    <tbody>
                {apps_list.map(app_item =>
                    <tr key={app_item.id_app}>
                        <td><b>{app_item.name}</b></td>
                        <td>{app_item.url}</td>
                        /*<td>
                            <div className="btn btn-primary btn-sm">Редактировать</div>
                            <div className="btn btn-danger btn-sm">Удалить</div>
                        </td>*/
                    </tr>
                )}
                    </tbody>
                </table>
            </div>

        );
    }
}
export default Apps;