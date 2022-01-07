import {Component} from "react";
import axios from 'axios';

import regeneratorRuntime from "regenerator-runtime";

class CreateUser extends Component {

constructor() {
        super();
        this.state = {
          name: '',
          email: '',
          username: '',
          id_user: 0
        };
      }

      onChange = (e) => {

        this.setState({ [e.target.name]: e.target.value });
      }

      onSubmit = (e) => {
        e.preventDefault();

        const { name, email, username, id_user } = this.state;

        axios.post('/api/user/create', { name, email, username, id_user })
          .then((res => this.setState({id_user: res.data})))
          //.then((result) => {});
      }



      render() {
        const { name, email, username, id_user } = this.state;
        return (
          <form onSubmit={this.onSubmit}>
          <div className="form-group text-left">
              <label htmlFor="emailField">Email address</label>
                <input
                              className="form-control"
                              type="email"
                              name="email"
                              id="emailField"
                              placeholder="Email"
                              value={email}
                              onChange={this.onChange}
                            />

              </div>

<div className="form-group text-left">
              <label htmlFor="usernameField">Имя пользователя</label>

            <input
              className="form-control"
              type="text"
              name="username"
              id="usernameField"
              value={username}
              placeholder="Имя пользователя"
              onChange={this.onChange}
            />
            </div>

            <div className="form-group text-left">
                          <label htmlFor="nameField">ФИО</label>
            <input
              className="form-control"
              type="text"
              name="name"
              id="nameField"
              value={name}
              placeholder="ФИО"
              onChange={this.onChange}
            />
            </div>

            <input
            type="hidden"
            id="id_user"
            name="id_user"
            value={id_user}
            />

            <button className="btn btn-default" type="submit">Сохранить</button>
          </form>
        );
      }
}
export default CreateUser;