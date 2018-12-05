package org.yakimov.denis.countriesservice.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yakimov.denis.countriesservice.dtos.ArchiveDto;
import org.yakimov.denis.countriesservice.support.Constants;

@Controller
public class GreetingController {

    @MessageMapping("/private")
    public void greeting2(ArchiveDto incoming) throws Exception {
        String zipName = incoming.getFileName();
        System.out.println("incomed: "+incoming);

//        try {
//            countryService.getContent(file, session);
//        } catch (IOException e) {
//            LOGGER.warn(Constants.UNKNOWN, e);
//        } catch (EmptyFileException e) {
//            LOGGER.warn(Constants.EMPTY_FILE);
//        }
    }
}
