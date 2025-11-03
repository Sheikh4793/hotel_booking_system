import com.hotel.model.Hotel;
import com.hotel.model.Operator;
import com.hotel.util.TableCreator;

public class Main {
    public static void main(String[] args) {
        Operator hotelOperator = new Operator();
        System.out.println("HotelOperator"+hotelOperator);

        try {
            TableCreator.createTable();
            Hotel hotel = new Hotel();
            System.out.println(hotel);
        }
        catch (Exception e) {
            System.out.println("Error while initializing data base");
            e.printStackTrace();
        }
    }
}
