import express from "express";
import {
    register,
    login,
    userlist,
    deleteUser
} from "./controller";

export const router = express.Router();

router.post('/register', register);
router.post('/login', login);
router.get('/userlist', userlist)
router.post('/delete', deleteUser);