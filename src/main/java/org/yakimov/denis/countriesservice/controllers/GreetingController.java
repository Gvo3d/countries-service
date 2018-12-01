//package org.yakimov.denis.countriesservice.controllers;
//
//import com.unboltsoft.meteomap.dto.IncomingData;
//import com.unboltsoft.meteomap.dto.OutcomingData;
//import com.unboltsoft.meteomap.service.DataService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.SimpMessageType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@Scope("request")
//public class GreetingController {
//
//    @Autowired
//    private SimpMessageSendingOperations messagingTemplate;
//
//    @Autowired
//    private DataService dataService;
//
//    @MessageMapping("/private")
//    public void greeting2(IncomingData incoming) throws Exception {
//        String sessionId = incoming.getIdentity();
//        OutcomingData outcoming = dataService.getData(incoming);
//        messagingTemplate.convertAndSendToUser(sessionId, "/queue/reply", outcoming, createHeaders(sessionId));
//    }
//
//    private MessageHeaders createHeaders(String sessionId) {
//        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
//        headerAccessor.setSessionId(sessionId);
//        headerAccessor.setLeaveMutable(true);
//        return headerAccessor.getMessageHeaders();
//    }
//
//    @CrossOrigin("*")
//    @PostMapping("/private/rest")
//    public @ResponseBody
//    OutcomingData getWeather(@RequestBody IncomingData incoming) {
//        System.out.println("Incoming data: " + incoming);
//        OutcomingData outcomingData = dataService.getData(incoming);
//        System.out.println("Outcoming data: " + outcomingData);
//        return outcomingData;
//    }
//}
