exports.logToConsole = (req, res, next) => {
    //Colored output https://stackoverflow.com/questions/9781218/how-to-change-node-jss-console-font-color
    console.info('\x1b[35m%s: \x1b[33m\x1b[1m%s \x1b[0m%s \x1b[36m%s\x1b[0m', new Date().toISOString(), req.method, `Request on`, req.url);
    next();
}