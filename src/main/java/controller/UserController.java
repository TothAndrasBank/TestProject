package controller;

import dataservice.UserDataService;
import json.Json;
import pojo.UserPOJO;
import utils.Log;

import java.util.List;
import java.util.logging.Logger;

public class UserController {
    private final Logger logger = Log.getLogger();
    private final UserDataService userDataService = new UserDataService();

    public void saveUsers() {
        final List<UserPOJO> listOfUserPOJO = Json.getListOfUserPOJO();
        logger.info("get Users done");
        userDataService.save(listOfUserPOJO);
    }
}
