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
                    <tr><th>Приложение</th><th>Пароль</th><th>Действия</th></tr>
                    </thead>
                    <tbody>
                {passwords.map(password_item =>
                    <tr key={password_item.app.id}>
                        <td><b>{password_item.app.name}</b> (<a href={password_item.app.url} className="morebutton" target="_blank">перейти</a>)</td>
                        <td><span className="passwordSet" id={ 'password_' + password_item.app.id}>{password_item.password}</span><div class="btn btn-default" data-password-id={ 'password_' + password_item.app.id}>Скопировать</div></td>
                        <td>
                            <a href={ '/setpassword?id_app=' + password_item.app.id}>
                                <div className="btn btn-primary">Сменить пароль</div>
                            </a>
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