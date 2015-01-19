package br.com.infotius.changerwallpaper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 *
 * @author matheus.geres
 */
public class Scheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public void start(){
        ChangerWallpaper cw = new ChangerWallpaper();
        final ScheduledFuture<?> changerWallpaperHandle = scheduler.scheduleAtFixedRate(cw, 0, 1, HOURS);
        scheduler.schedule(new SchedulerHandle (changerWallpaperHandle), 60 * 60, SECONDS);
    }
}
