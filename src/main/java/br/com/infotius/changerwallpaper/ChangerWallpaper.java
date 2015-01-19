package br.com.infotius.changerwallpaper;

import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
public class ChangerWallpaper implements Runnable{
    public void changeWallpaper (String image, String message){
        SPI.INSTANCE.SystemParametersInfo(
            new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
            new UINT_PTR(0), 
            image, 
            new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
        JOptionPane.showMessageDialog(null, message);
    }
    
    public String randomWallpaper(String path){
        File file = new File(path);
        String[] listWallpaper = file.list();
        
        int max = listWallpaper.length;
        int min = 1;
        int position = (int) (Math.random()*(max-min))+min;
        String wallpaper = listWallpaper[position];
        return path + wallpaper;
    }
    
    public void startChangeWallpaper(String path) {
        String pathWallpaper = randomWallpaper(path);
        while(pathWallpaper.equals(WindowsUtils.getCurrentWallpaperPath())){
            pathWallpaper = randomWallpaper(path);
        }
        
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        
        if(hours<11){
            changeWallpaper(pathWallpaper, "Bom dia!! :)\nVamos trocar de wallpaper?");
        }else if(hours>11&&hours<17){
            changeWallpaper(pathWallpaper, "Boa tarde!! :)\nVamos trocar de wallpaper?");
        }else{
            changeWallpaper(pathWallpaper, "Boa noite!! :)\nVamos trocar de wallpaper?");
        }
    }

    @Override
    public void run() {
        startChangeWallpaper("C:\\Users\\matheus.geres\\Pictures\\wallpaper\\");
    }
    
    public static void main (String[] args){
        Scheduler scheduler = new Scheduler();
        scheduler.start();
    }
}
