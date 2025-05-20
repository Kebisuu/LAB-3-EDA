import java.util.Scanner;
import java.util.ArrayList;
public class Game{
    String nombre;
    String categoria;
    int precio;   // 1000-7000
    int calidad; // del 1-100
    Game(String nombre, String categoria, int precio, int calidad){
        this.nombre=nombre;
        this.categoria=categoria;
        this.precio=precio;
        this.calidad=calidad;
    }
}
public class Dataset{
    ArrayList<Game> data ;
    String sortedByAttribute;

    public Dataset(ArrayList<Game> data){
        this.data=data;
        
    }
    public getGamesByPrice(int price){
      int i = 0;

        while(true) //Busqueda lineal
      {
          if(data.get(i).precio==price)
          {
              System.out.println(data.get(i).nombre);
          }
          i = i+1;
      }

    }
    public getGamesByPriceRange
}
