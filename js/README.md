# JS

Implementation of several concepts in JS (including nodeJS).

## Typical global npm packages for nodeJS ###
These are some useful npm packages for nodeJS development .Just enter `npm update <package name> -g` for each (you may need `sudo` in front for certain systems).

* express
* express-generator
* jasmine
* jasmine-node
* node-inspector
* nodemon

## Winston Logger Settings ###
A typical logger setup. Notable features are:

* Full timestamps including milliseconds eg: 15/7/2015 12:57:23.923
* Handles express http console logs as trace events
* Saves logs to `./logs/all-logs.log`
* Rolls over to a new log file every 1MB
* A nice custom colour scheme!

### Setup notes for Winson ###
* `app.js` needs to replace `var logger = require('morgan');` with `var logger = require('./logger');`
* Replace `app.use(logger('dev'));` with
`app.use(require('morgan')('dev', {
    "stream": logger.stream
}));` in `app.js`
* Don't forget to add Winston to your package.json file
