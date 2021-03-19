const express = require("express");

//const loginController = require("./login");

const router = express.Router();

//const auth = require("../services/auth");

//router.use("/login", loginController);

//Add user to route TBD
router.use((req, res, next) => {
/*  auth.getRoleAndID(req.auth.user, (err, data) => {
	res.status(500).send();
    } else {
      req.auth.role = data.role;
      req.auth.id = data.id;
    }
    next();
  });*/
  next();
});

//Add later other Routes here
//router.use("/post", postController);

module.exports = router;
