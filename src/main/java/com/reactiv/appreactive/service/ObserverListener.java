package com.reactiv.appreactive.service;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jms.Queue;


@Slf4j
@Service
public class ObserverListener implements Observer<Integer> , CommandLineRunner {

    //static List<Integer> sum = Collections.synchronizedList(new ArrayList<>());
    static Integer sum =0;
    private JmsMessagingTemplate jmsMessagingTemplate;

    private Queue queue;

    @Autowired
    public ObserverListener(JmsMessagingTemplate jmsMessagingTemplate,Queue queue){
        this.jmsMessagingTemplate=jmsMessagingTemplate;
        this.queue=queue;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        sum=0;
        log.info("subscribe");
    }

    @Override
    public void onNext(Integer integer) {
        sum+=Integer.valueOf(integer);
        log.info("sum is now "+sum);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error here");
    }

    @Override
    public void onComplete() {
        try {
            run(String.valueOf(sum));
            log.info("Completed");
        } catch (Exception e) {
            log.error("exception in sending ",e);
        }
    }

    public Integer returnSum(){
        return sum;
    }


    @Override
    public void run(String... args) throws Exception {
        if(args.length>0)
        this.jmsMessagingTemplate.convertAndSend(this.queue, (args[0]));
    }
}
