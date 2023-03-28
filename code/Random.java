//Made by Dylan Fritz October 2021
public class Random{

  public static int randInt(int min, int max){
    return ((int) (Math.random() * (max-min+1) + min));
  }

  public static double randDouble(double min, double max){
    return (Math.random() * (max-min+1) + min);
  }

  public static boolean coinFlip(){
    if (Math.random() < 0.5){
      return true;
    } else {
      return false;
    }
  }


  // Created by gywn January 2022
  public static int dice(){
    int ret = (int) (Math.random()*6 + 1);
    return ret;
  }
  
}
