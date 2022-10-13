package org.example.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang
 */
@RestController
@RequestMapping("/module01")
public class Module01EchoApiImpl implements Module01EchoApi {
    @Override
    public String echo(String str) {
        return "Hello OpenFeign " + str;
    }
}
