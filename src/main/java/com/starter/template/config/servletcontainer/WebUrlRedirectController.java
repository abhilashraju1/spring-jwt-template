package com.starter.template.config.servletcontainer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebUrlRedirectController {
	@RequestMapping("/{path:[^\\.]+}/**")
    public String forward() {
        return "forward:/";
    }
}
