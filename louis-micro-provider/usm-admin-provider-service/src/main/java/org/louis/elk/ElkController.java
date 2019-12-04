package org.louis.elk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * @author John·Louis
 * @date create in 2019/9/15
 * description:
 */
@Slf4j
@RestController
public class ElkController {


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/elkdemo")
    public String helloworld() {
        String response = "Hello user ! " + new Date();
        log.info( "/elkdemo - &gt; " + response);

        return response;

    }
    @RequestMapping(value = "/elk")
    public String helloWorld1() {

        String response =  restTemplate.exchange("http://localhost:8081/elkdemo", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        }).getBody();
        log.info( "/elk - &gt; " + response);

        try {
            String exceptionrsp =  restTemplate.exchange("http://localhost:8081/exception", HttpMethod.GET, null,new ParameterizedTypeReference<String>() {
            }).getBody();
            log.info( "/elk trying to print exception - &gt; " + exceptionrsp);
            response = response + " === " + exceptionrsp;
        } catch (Exception e) {
            // exception should not reach here. Really bad practice :)
        }

        return response;
    }

    @RequestMapping(value = "/exception")
    public String exception() {
        String rsp = "";
        try {
            int i = 1 / 0;
            // should get exception
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            log.error("Exception As String :: - &gt; "+sStackTrace);

            rsp = sStackTrace;
        }

        return rsp;
    }





}
