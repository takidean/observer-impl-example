package com.reactiv.appreactive.resource;

import com.reactiv.appreactive.service.ObservableListenerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/take")
public class StartAction {

    ObservableListenerImpl observableListener;

    @Autowired
    public StartAction(ObservableListenerImpl observableListener) {
        this.observableListener = observableListener;
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public String validatePurchasing(@RequestParam Integer valueToAdd,@RequestParam String name) throws Exception {
        observableListener.createObserver(valueToAdd,name);
        return "add another value";
    }

}
