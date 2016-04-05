package org.yagna.samples.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yagna.samples.jwt.model.Profile;

/**
 * Created by asish on 4/3/16.
 */
@RestController
public class ProfileController {

    private Logger LOG = LoggerFactory.getLogger(ProfileController.class);

    @RequestMapping(value = "/api/profile/get/{id}", method = RequestMethod.GET)
    public Profile getProfile(@PathVariable("id") int id) {
        LOG.info("Getting user by id {}", id);
        Profile p = new Profile("John", "David", "john.david@test.com");
        return p;
    }
}
