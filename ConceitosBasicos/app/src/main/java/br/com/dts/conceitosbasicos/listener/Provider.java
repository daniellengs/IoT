package br.com.dts.conceitosbasicos.listener;

import java.util.List;

/**
 * Created by diegosouza on 9/5/16.
 */
public class Provider {

    private List<EventClickListener> listener;


    public void setEventListener(EventClickListener listener){
        this.listener.add(listener);
    }

    public void doSomething(){
        notifyListener();
    }

    public void notifyListener(){
        for ( EventClickListener l : listener) {
            l.onClick();
        }

    }
}
