package com.mumulx.service;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

    public String getTicket(){
        System.out.println("8002");
        return "《厉害了，我的国》";
    }
}
