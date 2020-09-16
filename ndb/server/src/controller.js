const { User } = require('./models');

export const register = async (req, res) => {
    console.log("register 연결");
    const {
        body: { userID, userPW, userName, userAge }
    } = req;

    var numUserAge = parseInt(userAge)

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
    }
}

export const login = async (req, res) => {
    const userlist = await User.findAll({});
    console.log(userlist);
    res.json(userlist);
}