package org.yagna.samples.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asish on 3/28/16.
 */
@RestController
public class PingController {

    private Logger LOG = LoggerFactory.getLogger(PingController.class);

    @org.springframework.web.bind.annotation.RequestMapping(value = "/ping")
    public String ping(){
        LOG.info("Ping Controller **************************");
        return "I am up";
    }
}
