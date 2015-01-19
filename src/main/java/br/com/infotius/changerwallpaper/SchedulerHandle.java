package br.com.infotius.changerwallpaper;

import java.util.concurrent.ScheduledFuture;

/**
 *
 * @author matheus.geres
 */
public class SchedulerHandle implements Runnable {
    private ScheduledFuture scheduledFuture;
    
    public SchedulerHandle(ScheduledFuture scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }
    
    @Override
    public void run() {
        scheduledFuture.cancel(true);
    }
}
