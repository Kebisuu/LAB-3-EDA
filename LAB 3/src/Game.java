import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class Game{
    String name;
    String category;
    int price;   // 1000-7000
    int quality; // del 1-100

    Game(String name, String category, int price, int quality){
        this.name=name;
        this.category=category;
        this.price=price;
        this.quality=quality;
    }
    String getName(){ return name; }

    String getCategory(){ return category; }

    int getPrice(){ return price; }

    int getQuality(){ return quality; }
}

class Dataset {
    ArrayList<Game> data;
    String sortedByAttribute;

    public Dataset(ArrayList<Game> data) {
        this.data = data;
        this.sortedByAttribute = "none";
    }

    public ArrayList<Game> getGamesByPrice(int price) {
        ArrayList<Game> result = new ArrayList<>();
        if (sortedByAttribute.equals("price")) {
            int left = 0, right = data.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midPrice = data.get(mid).getPrice();
                if (midPrice == price) {
                    int index = mid;
                    while (index >= 0 && data.get(index).getPrice() == price) {
                        result.add(data.get(index));
                        index--;
                    }
                    index = mid + 1;
                    while (index < data.size() && data.get(index).getPrice() == price) {
                        result.add(data.get(index));
                        index++;
                    }
                    break;
                } else if (midPrice < price) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return result;
        }
        else
        {
            for (Game game : data) {
                if (game.getPrice() == price) {
                    result.add(game);
                }
            }
            return result;
        }
    }
    public void getGameByPriceRange(int lowerPrice, int higherPrice){
       if(sortedByAttribute.equals("price")){
        int left =0;
        int right = data.size()-1;
        while(left <= right) {
            int mid = (left + right)/2;
            int midPrice= data.get(mid).getPrice();
            if(midPrice < lowerPrice) {
                left = mid + 1;
            }
            else if(midPrice > higherPrice) {
                right = mid - 1;
            }
        }
        if(left >= data.size() || data.get(left).getPrice() < lowerPrice || data.get(left).getPrice() > higherPrice) {
            System.out.println("No se encontraron juegos con ese rango de precio ");
            return;
        }
        boolean found = false;
        for(int i = left; i < data.size() && data.get(i).price <=higherPrice; i++) {
            System.out.println(data.get(i).name);
            found=true;

        }
        if(!found) {
            System.out.println("No se encontraron juegos en ese rango de precio");
        }

    }
    else{
        boolean found = false;
           for (Game game : data) {
               int price = game.getPrice();
               if (price >= lowerPrice && price <= higherPrice) {

           }                 System.out.println(game.getName());
                   found = true;
               }
           if (!found) {
               System.out.println("No se encontraron juegos en ese rango de precio.");
           }
       }
    }
    public ArrayList<Game> getGameByCategory(String category){
        ArrayList<Game> result = new ArrayList<>();
        if(sortedByAttribute.equals("category")) {
         int left = 0, right = data.size() - 1;
         while (left <= right) {
             int mid = left + (right - left) / 2;
             String midCategory = data.get(mid).getCategory();
             if (midCategory.equals(category)) {
                 // Encontramos un elemento, ahora buscamos los demás con la misma categoría.
                 int index = mid;
                 // Buscar hacia la izquierda.
                 while (index >= 0 && data.get(index).getCategory().equals(category)) {
                     result.add(data.get(index));
                     index--;
                 }
                 // Buscar hacia la derecha.
                 index = mid + 1;
                 while (index < data.size() && data.get(index).getCategory().equals(category)) {
                     result.add(data.get(index));
                     index++;
                 }
                 break;
             } else if (midCategory.compareTo(category) < 0) {
                 left = mid + 1;
             } else {
                 right = mid - 1;
             }
         }
         return result;
     }
     else {
         for (Game game : data) {
             if (game.getCategory().equals(category)) {
                 result.add(game);
             }
         }
         return result;
     }
    }
    public void getGameByQuality(int quality){
        if(sortedByAttribute.equals("quality")) {
            int left = 0;
            int right = data.size() - 1;
            int target = -1;
            while(left <= right) {
                int mid = (left + right)/2;
                int midQuality = data.get(mid).quality;
                if(midQuality < quality){
                    right= mid - 1;
            }
                else if(midQuality > quality) {
                    left = mid + 1;
                }


        }
    }
}
}
