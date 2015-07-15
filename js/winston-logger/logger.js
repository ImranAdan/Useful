/*
    Winston logger settings
*/

var winston = require('winston');
winston.emitErrs = true;

function currentTimeStamp() {
    var now = new Date();
    return now.getDate() + "/" + (now.getMonth() + 1) + "/" + now.getFullYear() +
    " " + now.toLocaleTimeString() + "." + now.getMilliseconds();
}

var customSettings = {
    //Emphasis choices: bold, italic, underline, inverse
    //Colour choices: yellow, cyan, white, magenta, green, red, grey, blue
    colours: {
        trace: 'grey',
        debug: 'cyan',
        info: 'white',
        warn: 'yellow bold',
        error: 'red bold underline'
    },
    levels: {
        trace: 0,
        debug: 1,
        info: 2,
        warn: 3,
        error: 4
    }
};

var logger = new (winston.Logger)({
    levels: customSettings.levels,
    colors: customSettings.colours,
    transports: [
        new winston.transports.File({
            level: 'info',
            filename: './logs/all-logs.log',
            handleExceptions: true,
            json: true,
            maxsize: 1048576, //1MB in bytes
            maxFiles: 5,
            timestamp: true,
            colorize: false
        }),
        new winston.transports.Console({
            level: 'trace',
            handleExceptions: true,
            timestamp: currentTimeStamp,
            json: false,
            colorize: true
        })
    ],
    exitOnError: false
});

winston.addColors(customSettings.colours);

module.exports = logger;
module.exports.stream = {
    write: function(message, encoding){
        logger.trace(message.slice(0, -1));
    }
};
