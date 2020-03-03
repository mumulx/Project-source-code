package com.mumulx.service;

import com.mumulx.entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {


    //当消息队列中有新内容进来时，就会触发该函数
    @RabbitListener(queues = "mumu.news")
    public void receive(Book book) {
        System.out.println("=========收到消息");

    }
    //当消息队列中有新内容进来时，就会触发该函数
    @RabbitListener(queues = "mumu.news")
    public void receive1(Message message) {
        System.out.println(message.getBody());//消息内容
        System.out.println(message.getMessageProperties());//消息头信息

    }



}
