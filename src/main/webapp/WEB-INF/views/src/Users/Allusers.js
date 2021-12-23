import {Component} from "react";

import regeneratorRuntime from "regenerator-runtime";

class Allusers extends Component {
    state = {
        clients: []
    };

    async componentDidMount() {
        const response = await fetch('/api/users');
        const body = await response.json();
        this.setState({clients: body});
    }

    render() {
        const {clients} = this.state;
        return (

            <div id="usersblock">

            <h2>Пользователи</h2>

                    <table id="userslist" class="table table-striped text-left">
                        <thead>
                        <tr>
                            <th>Id пользователя</th><th>ФИО</th><th>Email</th><th>Действие</th>
                        </tr>
                        </thead>
                    <tbody>
                        {clients.map(client =>
                            <tr key={client.id}>
                                <td>{client.id}</td>
                                <td><b>{client.name}</b></td>
                                <td>{client.email}</td>
                                <td><div class="btn btn-danger">Удалить</div></td>
                            </tr>
                        )}
                    </tbody>
                    </table>

            </div>

        );
    }
}
export default Allusers;