import {Component} from "react";

import regeneratorRuntime from "regenerator-runtime";

class Passwords extends Component {
    state = {
        passwords: []
    };

    async componentDidMount() {
        const response = await fetch('/api/passwords');
        const body = await response.json();
        this.setState({passwords: body});
    }

    render() {
        const {passwords} = this.state;
        return (

            <div>
                <h2>Пароли</h2>
                <table id="passwordslist" className="table table-striped text-left">
                    <thead>
                    <tr><th>Id</th><th>Приложение</th><th>Мои Пароли</th><th>Действия</th></tr>
                    </thead>
                    <tbody>
                {passwords.map(password_item =>
                    <tr key={password_item.app.id}>
                        <td>{password_item.app.id}</td>
                        <td><b>{password_item.app.name}</b> (<a href={password_item.app.url} className="morebutton" target="_blank">перейти</a>)</td>
                        <td>{password_item.password}</td>
                        <td>
                            <div className="btn btn-primary">Редактировать</div>
                            <div className="btn btn-danger">Удалить</div>
                        </td>
                    </tr>
                )}
                    </tbody>
                </table>
            </div>

        );
    }
}
export default Passwords;