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
public class Dataset {
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
                 int index = mid;
                 while (index >= 0 && data.get(index).getCategory().equals(category)) {
                     result.add(data.get(index));
                     index--;
                 }
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
            while(left <= right) {
                int mid = (left + right)/2;
                int midQuality = data.get(mid).getQuality();
                if(midQuality == quality) {
                    int target = mid;
                    while(target >= 0 && data.get(target).getQuality() == quality) {
                        System.out.println(data.get(target).getName());
                        target--;
                    }
                    while(target < data.size() && data.get(target).getQuality() == quality) {
                        System.out.println(data.get(target).getName());
                        target++;
                    }
                    break;
                }
                else if (midQuality < quality) {
                    right = mid - 1;
                }
                else if(midQuality > quality)
                {
                    left = mid + 1;
                }
        }
    }
        else{
            for (Game game : data) {
                if (game.getQuality() == quality) {
                    System.out.println(game.getName());
                }
            }
        }
}
    public void sortByAlgorithm(String algorithm, String attribute){
        Comparator<Game> comparator;
        switch (attribute) {
            case "price":
                comparator = Comparator.comparingInt(Game :: getPrice);
                break;
            case "category":
                comparator = Comparator.comparing(Game :: getCategory);
                break;
            case "quality":
                comparator = Comparator.comparingInt(Game :: getQuality);
                break;
            default:
                System.out.println("Algoritmo no encontrado, se ocupara 'price' como default ");
                comparator = Comparator.comparingInt(Game :: getPrice);
                attribute = "price";
        }
        switch (algorithm) {
            case "bubbleSort":
                bubbleSort(comparator);
                break;
            case "insertionSort":
                insertionSort(comparator);
                break;
            case "selectionSort":
                selectionSort(comparator);
                break;
            case "mergeSort":
                mergeSort(comparator, 0, data.size() - 1);
                break;
            case "quickSort":
                quickSort(comparator, 0, data.size() - 1);
                break;
            default:
                System.out.println("Algoritmo no reconocido. Se usará Collections.sort().");
                Collections.sort(data, comparator);
        }
    }
     private void bubbleSort(Comparator<Game> comparator) {
        int n = data.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(data.get(j), data.get(j + 1)) > 0) {
                    // Intercambiar elementos
                    Game temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }
    private void selectionSort(Comparator<Game> comparator) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(data.get(j), data.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            Game temp = data.get(i);
            data.set(i, data.get(minIndex));
            data.set(minIndex, temp);
        }
    }
    private void insertionSort(Comparator<Game> comparator) {
        for (int i = 1; i < data.size(); i++) {
            Game key = data.get(i);
            int j = i - 1;
            while  (j >= 0 && comparator.compare(data.get(j), key) > 0) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
    }
}
/*
private void mergeSort(Comparator<Game> comparator, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;

        // Dividir la lista en dos mitades y ordenar cada una
        mergeSort(comparator, left, mid);
        mergeSort(comparator, mid + 1, right);

        // Combinar las dos mitades ordenadas
        merge(comparator, left, mid, right);
    }
}

private void merge(Comparator<Game> comparator, int left, int mid, int right) {
    // Tamaño de los subarrays
    int n1 = mid - left + 1;
    int n2 = right - mid;

    // Crear listas temporales
    ArrayList<Game> leftList = new ArrayList<>(n1);
    ArrayList<Game> rightList = new ArrayList<>(n2);

    // Copiar datos a las listas temporales
    for (int i = 0; i < n1; i++)
        leftList.add(data.get(left + i));

    for (int j = 0; j < n2; j++)
        rightList.add(data.get(mid + 1 + j));

    // Combinar las listas ordenadas
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0) {
            data.set(k, leftList.get(i));
            i++;
        } else {
            data.set(k, rightList.get(j));
            j++;
        }
        k++;
    }

    // Agregar los elementos restantes
    while (i < n1) {
        data.set(k, leftList.get(i));
        i++;
        k++;
    }
    while (j < n2) {
        data.set(k, rightList.get(j));
        j++;
        k++;
    }
}
 */
/*
private void quickSort(Comparator<Game> comparator, int left, int right) {
    if (left < right) {
        int pivotIndex = partition(comparator, left, right);

        // Ordenar recursivamente las sublistas
        quickSort(comparator, left, pivotIndex - 1);
        quickSort(comparator, pivotIndex + 1, right);
    }
}

private int partition(Comparator<Game> comparator, int left, int right) {
    Game pivot = data.get(right); // Usamos el último elemento como pivote
    int i = left - 1;

    for (int j = left; j < right; j++) {
        if (comparator.compare(data.get(j), pivot) <= 0) {
            i++;
            swap(i, j);
        }
    }

    swap(i + 1, right);
    return i + 1; // Retorna la posición final del pivote
}

private void swap(int i, int j) {
    Game temp = data.get(i);
    data.set(i, data.get(j));
    data.set(j, temp);
}
 */