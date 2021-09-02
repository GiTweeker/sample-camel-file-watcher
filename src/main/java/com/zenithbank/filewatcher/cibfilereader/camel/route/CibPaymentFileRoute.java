package com.zenithbank.filewatcher.cibfilereader.camel.route;


import com.zenithbank.filewatcher.cibfilereader.camel.processor.ReadPaymentFileProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class CibPaymentFileRoute extends RouteBuilder {


    @Autowired
    ReadPaymentFileProcessor readPaymentFileProcessor;

    @Override
    public void configure() throws Exception {
        ThreadPoolExecutor splittingPool =
                new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        onException(Exception.class).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                log.error(" ==========Error Occured===========");

                Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
                log.info(exception.getMessage());
                exception.printStackTrace();

            }
        }).handled(false);








         from("{{app.camel.sample.path-uri}}")
         .id("app.camel.sample.path-uri")
        .process(readPaymentFileProcessor);
        //.log("===Starting==");


    }
}
