package edu.ucsd.cse110.lab5_nearby;


import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.MessageListener;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class FakedMessageListener extends MessageListener{

    private final MessageListener messageListener;
    private final ScheduledExecutorService excutor;

    public FakedMessageListener(MessageListener realMessageListener, int frequency, String messageStr){

        this.messageListener = realMessageListener;
        this.excutor = Executors.newSingleThreadScheduledExecutor();

        excutor.scheduleAtFixedRate(()->{
            Message message = new Message( messageStr.getBytes(StandardCharsets.UTF_8) );
            this.messageListener.onFound(message);
            this.messageListener.onLost(message);
        }, 0, frequency, TimeUnit.SECONDS);

    };

}
