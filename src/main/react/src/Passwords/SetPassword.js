import {Component} from "react";
import axios from 'axios';

import regeneratorRuntime from "regenerator-runtime";

class SetPassword extends Component {

constructor() {
        super();
        this.state = {
          id_app: '',
          password: ''
        };
      }

      onChange = (e) => {

        this.setState({ [e.target.name]: e.target.value });
      }

      onSubmit = (e) => {
        e.preventDefault();

        const { id_app, password } = this.state;

        axios.post('/api/password/set', { id_app, password })
          .then((result) => {

          });
      }

      render() {
        const { id_app, password } = this.state;
        return (
          <form onSubmit={this.onSubmit}>

            <div className="form-group text-left">
                          <label for="passwordField">Пароль</label>

                        <input
                          className="form-control"
                          type="text"
                          name="password"
                          id="passwordField"
                          value="{password}"
                          placeholder="Пароль"
                          onChange={this.onChange}
                        />
            </div>
            <button className="btn btn-default" type="submit">Сохранить</button>
          </form>
        );
      }
}
export default SetPassword;