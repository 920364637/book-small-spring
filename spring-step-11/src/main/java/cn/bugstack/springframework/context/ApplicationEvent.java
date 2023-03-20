package cn.bugstack.springframework.context;

import java.util.EventObject;

public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event
     * @param source the object on which the Event initially occurred
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
