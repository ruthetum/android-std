const { User } = require('./models');

export const register = async (req, res) => {
    console.log("register 연결");
    const {
        body: { userID, userPW, userName, userAge }
    } = req;

    var numUserAge = parseInt(userAge);

    try {
        const newUser = await User.create({
            userID,
            userPW,
            userName,
            userAge: numUserAge
        });
        console.log(newUser);

        res.send({
            "success" : "OK"
        });
    } catch (err) {
        console.log(err);
        res.send({
            "success" : "FAILED"
        });
    }
}

export const login = async (req, res) => {
    console.log("login 연결");
    const {
        body: { userID, userPW}
    } = req;

    try {
        const checkUser = await User.findOne({
            where: {
                userID: userID
            }
        });
        console.log(checkUser);

        if (checkUser.userPW === userPW) {
            res.send({
                "success" : "OK"
            });
        } else {
            res.send({
                "success" : "FAILED"
            });
        }
    } catch (err) {
        console.log(err);
        res.send({
            "success" : "FAILED"
        });
    }
}

export const userlist = async (req, res) => {
    try {
        const userList = await User.findAll({});
        console.log(userList);
        res.send(userList);
    } catch (err){
        const userList = [];
        console.log(err);
        res.send(userList);
    }
    
}