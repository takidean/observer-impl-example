package com.reactiv.appreactive.service;

import com.reactiv.appreactive.model.ValueToPersist;
import com.reactiv.appreactive.repository.ValueToPersistRepository;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ObservableListenerImpl  {

    private ObserverListener observerListener;
    private ValueToPersistRepository valueToPersistRepository;
    private static String nameOfValue;
    private int lastValue=0;
    int times=0;
    @Autowired
    public ObservableListenerImpl(ObserverListener observerListener,ValueToPersistRepository valueToPersistRepository) {
        this.observerListener = observerListener;
        this.valueToPersistRepository=valueToPersistRepository;
    }

    public void createObserver(Integer valuereceived,String name){
        Observable observable = Observable.create(observableEmitter -> {
            for(int i=0;i<valuereceived;i++)
            observableEmitter.onNext(valuereceived-i);
            observableEmitter.onComplete();
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation())
                .doFinally(()->{
                    nameOfValue=name;
                log.info("finished");
          });
        observable.subscribe(observerListener);
    }

    @JmsListener(destination = "java2blog.queue")
    public void receiveQueue(String text) {
        if(times==lastValue) {
            valueToPersistRepository.save(new ValueToPersist(Integer.valueOf(text),nameOfValue));
            List values= valueToPersistRepository.findAll();
            System.out.println("Message Received: "+text);
            System.out.println("values: "+values.size());
            times=0;
        }
        else times++;
    }

}
