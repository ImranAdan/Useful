//Authentication functions

var bcrypt = require('bcrypt');

/*
    Generates a hashed password given a plaintext password. The function uses bcrypt
    to hash the passsword over a number of rounds with a randomly generated
    cryptographic salt. The more rounds, the more secure the hash with the trade-off
    of time to generate the hash. The hashed password is returned ready to be saved.
*/
var generatePassword = function(password, callback) {

    var hashRounds = 12;    //More rounds == more secure but takes longer to generate
    //A cryptographic salt is first generated...
    bcrypt.genSalt(hashRounds, function(err, salt) {
        //...then the salt is combined with the plaintext password...
        bcrypt.hash(password, salt, function(err, hash) {
            //...to form the hash
            callback(hash);
        });
    });

};

/*
    Compares a plaintext password (typically given by the user) with hashed
    password (stored on the database). Simply returns true or false depending
    on whether the hash computed from the given plaintext password matches the
    hash stored on the db.
*/
var checkPassword = function(givenPassword, dbHashedPassword, callback) {

    bcrypt.compare(givenPassword, dbHashedPassword, function(err, res){
        callback(res);
    });

};

module.exports = {
    generatePassword: generatePassword,
    checkPassword: checkPassword
};
