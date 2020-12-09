package com.reactiv.appreactive.service;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ObserverListener implements Observer<Integer> {

    //static List<Integer> sum = Collections.synchronizedList(new ArrayList<>());
    static Integer sum =0;

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
        log.info("Completed");
    }

    public Integer returnSum(){
        return sum;
    }






}
