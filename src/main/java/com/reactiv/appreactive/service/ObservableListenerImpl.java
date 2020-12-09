package com.reactiv.appreactive.service;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ObservableListenerImpl  {

    private ObserverListener observerListener;
    private int lastValue=0;
    int times=0;
    @Autowired
    public ObservableListenerImpl(ObserverListener observerListener) {
        this.observerListener = observerListener;
    }

    public void createObserver(Integer valuereceived){
        Observable observable = Observable.create(observableEmitter -> {
            for(int i=0;i<valuereceived;i++)
            observableEmitter.onNext(valuereceived-i);
            observableEmitter.onComplete();
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation())
                .doFinally(()->{
            try {
                wait();
                log.info("finished");
            }catch (InterruptedException e)  {
            Thread.currentThread().interrupt();
            }});
        observable.subscribe(observerListener);
    }

    @JmsListener(destination = "java2blog.queue")
    public void receiveQueue(String text) {
        if(times==lastValue) {System.out.println("Message Received: "+text);times=0;notify();}
        else times++;
    }

}
