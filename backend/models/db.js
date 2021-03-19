const low = require('lowdb');
const FileSync = require('lowdb/adapters/FileSync');
 
const adapter = new FileSync('./database/db.json');
const db = low(adapter);

// Set some defaults
// db.defaults({ users: [{}], tickets: [{}] }).write();

module.exports = db;