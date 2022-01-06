import {Component} from "react";
import axios from 'axios';

import regeneratorRuntime from "regenerator-runtime";

class CreateUser extends Component {

constructor() {
        super();
        this.state = {
          id: '3245',
          name: '',
          email: '',
          username: '',
          ModelAttribute: 'user'
        };
      }

      onChange = (e) => {

        this.setState({ [e.target.name]: e.target.value });
      }

      onSubmit = (e) => {
        e.preventDefault();

        const { id, name, email, username, ModelAttribute } = this.state;

        axios.post('/api/user/create', { id, name, email, username, ModelAttribute })
          .then((result) => {

          });
      }

      render() {
        const { id, name, email, username, ModelAttribute } = this.state;
        return (
          <form onSubmit={this.onSubmit}>
            <input
              type="text"
              name="email"
              value={email}
              onChange={this.onChange}
            />
            <input
              type="text"
              name="username"
              value={username}
              onChange={this.onChange}
            />
            <input
              type="text"
              name="name"
              value={name}
              onChange={this.onChange}
            />
            <input
                          type="hidden"
                          name="ModelAttribute"
                          value="user"
                        />
            <input
              type="text"
              name="id"
              value="123123"
            />
            <button type="submit">Submit</button>
          </form>
        );
      }
}
export default CreateUser;