
package com.machd.sentineldemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @version 2.0
 * @since 2.0
 */
@RestController
public class TestController {

    @GetMapping("/testsentinel")
    public String test() {
        return "now you test sentinel success";
    }
}
