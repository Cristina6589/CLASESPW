import controller.TiendaController;
import database.DBConnection;
import model.User;

import java.sql.Connection;

public class Main {
    public static void main(String[] args)  {
        TiendaController controller = new TiendaController();
        // controller.darAltaUsuario(new User("lucas","lucas@gmail.com",24000,1));
        controller.obtenerListado();
    }
}
