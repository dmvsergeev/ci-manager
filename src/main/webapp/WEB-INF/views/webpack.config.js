const path = require('path');

module.exports = {
  mode: 'development',
  entry: path.join(__dirname, "src", "main.js"),
  output: {
    path:path.resolve(__dirname, "../../../resources/WEB-INF/js"),
  },
  module: {
    rules: [
      {
        test: /\.?js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader",
          options: {
            presets: ['@babel/preset-env', '@babel/preset-react']
          }
        }
      },

    ]
  }
}