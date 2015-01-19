package br.com.infotius.changerwallpaper;

/**
 *
 * @author matheus.geres
 */
public class WindowsUtils {
  private static final String REGQUERY_UTIL = "reg query ";
  private static final String REGSTR_TOKEN = "REG_SZ";
  private static final String DESKTOP_FOLDER_CMD = REGQUERY_UTIL 
     + "\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\" 
     + "Explorer\\Shell Folders\" /v DESKTOP";
  private static final String WALLPAPER_PATH = REGQUERY_UTIL 
     + "\"HKCU\\Control Panel\\Desktop\" /v Wallpaper";

  private WindowsUtils() {}

  public static String getCurrentUserDesktopPath() {
    try {
      Process process = Runtime.getRuntime().exec(DESKTOP_FOLDER_CMD);
      StreamReader reader = new StreamReader(process.getInputStream());

      reader.start();
      process.waitFor();
      reader.join();
      String result = reader.getResult();
      int p = result.indexOf(REGSTR_TOKEN);

      if (p == -1) return null;
      return result.substring(p + REGSTR_TOKEN.length()).trim();
    }
    catch (Exception e) {
      return null;
    }
  }
  
  public static String getCurrentWallpaperPath() {
    try {
      Process process = Runtime.getRuntime().exec(WALLPAPER_PATH);
      StreamReader reader = new StreamReader(process.getInputStream());

      reader.start();
      process.waitFor();
      reader.join();
      String result = reader.getResult();
      int p = result.indexOf(REGSTR_TOKEN);

      if (p == -1) return null;
      return result.substring(p + REGSTR_TOKEN.length()).trim();
    }
    catch (Exception e) {
      return null;
    }
  }
  
  /**
   * TEST
   */
  public static void main(String[] args) {
    System.out.println("Desktop directory : " 
       + getCurrentUserDesktopPath());
    System.out.println("Wallpaper directory : " 
       + getCurrentWallpaperPath());
    System.out.println(WALLPAPER_PATH);
  }
}
