package br.com.dts.conceitosbasicos.listener;

/**
 * Created by diegosouza on 9/5/16.
 */
public class Client implements EventClickListener{


    public void teste(){
        Provider p = new Provider();

        p.setEventListener(new EventClickListener() {
            @Override
            public void onClick() {

            }
        });

    }

    @Override
    public void onClick() {

    }
}
