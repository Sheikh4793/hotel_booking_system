import com.hotel.factory.ObjectFactory;
import com.hotel.model.Operator;
import com.hotel.util.TableCreator;

public class Main {
    public static void main(String[] args) {

        try {
            TableCreator.createTable();
            ObjectFactory.getHomeVIew().showMenu();
        }
        catch (Exception e) {
            System.out.println("Error while initializing data base");
            e.printStackTrace();
        }
    }
}
