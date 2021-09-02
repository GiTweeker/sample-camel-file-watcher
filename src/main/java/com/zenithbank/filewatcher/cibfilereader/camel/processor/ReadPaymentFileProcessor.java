package com.zenithbank.filewatcher.cibfilereader.camel.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class ReadPaymentFileProcessor  implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        final File file  = exchange.getIn().getBody(File.class);
        log.info("File Name " +file.getName());

    }
}
