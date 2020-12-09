package com.reactiv.appreactive.service;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ObservableListenerImpl  {

    private ObserverListener observerListener;

    @Autowired
    public ObservableListenerImpl(ObserverListener observerListener) {
        this.observerListener = observerListener;
    }

    public void createObserver(Integer valuereceived){
        Observable observable = Observable.create(observableEmitter -> {
            for(int i=0;i<valuereceived;i++)
            observableEmitter.onNext(valuereceived-i);
            observableEmitter.onComplete();
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation()).doFinally(()->log.info("finished"));
        observable.subscribe(observerListener);
    }

}
