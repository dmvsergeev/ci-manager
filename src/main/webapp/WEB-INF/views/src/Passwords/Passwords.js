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
                <table id="passwordslist" class="table table-striped text-left">
                    <thead>
                    <tr><th>Id</th><th>Приложение</th><th>Пароль</th><th>Действие</th></tr>
                    </thead>
                    <tbody>
                {passwords.map(password_item =>
                    <tr key={password_item.id}>
                        <td>{password_item.id}</td>
                        <td><b>{password_item.app}</b></td>
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