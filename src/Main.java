import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Film> films= new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        //Получение списка фильмов
        try(FileInputStream fin=new FileInputStream("C:/Users//User//Desktop//Программирование//Информатика//Films.txt"))
        {
            StringBuilder stringBuffer = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(fin, "CP1251"));
            String str;
            in.readLine();
            String[] ss;
            while ((str = in.readLine()) != null) {
                ss=str.split(",");
                Film film = new Film(ss[0],ss[1],ss[2],ss[3],Integer.parseInt(ss[4]),Double.parseDouble(ss[5]));
                films.add(film);
            }
            in.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        //А) Списое фильмов по стране
        System.out.println("Введите страну для поиска фильмов");
        String country = scanner.nextLine();

        ArrayList<Film> filmsA = new ArrayList<>();
        for (Film f : films) {
            if(f.getCountry().equalsIgnoreCase(country)){
                filmsA.add(f);
            }
        }

        System.out.println("Список фильмов:");
        for (Film f : filmsA) {
            System.out.println(f.getName());
        }

        //Б Список фильмов Режиссера, сделанных на одной студии, стоимостью от миллона до 15 миллионов
        System.out.println("\nВведите режиссера для поиска фильмов:");
        String producer = scanner.nextLine();

        ArrayList<Film> filmsB = new ArrayList<>();
        for (Film f : films) {
           if(f.getProducer().equalsIgnoreCase(producer)&&f.getPrice()>=1000000&&f.getPrice()<15000000){
                filmsB.add(f);
           }
        }

        ArrayList<String> Studios = new ArrayList<>();
        for (Film f : filmsB) {
            Studios.add(f.getStudio());
        }
        Set<String> uniqueStudios = new HashSet<String>(Studios);

        System.out.println("\nСписок фильмов:");

        for (String s : uniqueStudios) {
            System.out.println("\nСтудия - "+s+":");
            for (Film f : filmsB) {
                if(s.equalsIgnoreCase(f.getStudio())){
                    System.out.println(f.getName());
                }
            }
        }

    }

}
