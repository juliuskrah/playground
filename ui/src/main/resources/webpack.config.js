const path = require('path');

module.exports = {
    "mode": "development",
    entry: './asset/js/app.js',
    output: {
        filename: 'turbo.js',
        path: path.resolve(__dirname, 'static/js')
    }
};