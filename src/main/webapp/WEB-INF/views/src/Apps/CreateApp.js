import {Component} from "react";
import axios from 'axios';

import regeneratorRuntime from "regenerator-runtime";

class CreateApp extends Component {

constructor() {
        super();
        this.state = {
          name: '',
          url: ''
        };
      }

      onChange = (e) => {

        this.setState({ [e.target.name]: e.target.value });
      }

      onSubmit = (e) => {
        e.preventDefault();

        const { name, url } = this.state;

        axios.post('/api/app/create', { name, url })
          .then((result) => {

          });
      }

      render() {
        const { name, url } = this.state;
        return (
          <form onSubmit={this.onSubmit}>

            <div className="form-group text-left">
                          <label for="nameField">Название Приложения/Системы</label>
            <input
              className="form-control"
              type="text"
              name="name"
              id="nameField"
              value={name}
              placeholder="Название Приложения/Системы"
              onChange={this.onChange}
            />
            </div>

            <div className="form-group text-left">
                          <label for="urlField">URL</label>

                        <input
                          className="form-control"
                          type="text"
                          name="url"
                          id="urlField"
                          value={url}
                          placeholder="URL"
                          onChange={this.onChange}
                        />
            </div>

            <button className="btn btn-default" type="submit">Сохранить</button>
          </form>
        );
      }
}
export default CreateApp;