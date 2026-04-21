package controller;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TiendaController {
    private UserDAO userDAO;

    public TiendaController() {
        userDAO = new UserDAO();
    }

    public void darAltaUsuario(User user) {
        // logica -> manda correo a RH
        // da de alta en ERP
        // activa las claves
        // inserta en base de datos
        try {
            userDAO.insertUserPS(user);
            System.out.println("Usuario dado de alta con exito");
        } catch (SQLException e) {
            System.out.println("Email duplicado, por favor mete otro mail");
            Scanner scanner = new Scanner(System.in);
            String email = scanner.next();
            user.setCorreo(email);
            darAltaUsuario(user);
        }
    }

    public void obtenerListado() {
        // sacar todos los datos de la base de datos
        List<User> lista = userDAO.getAllUsers();
        // mandar recordatorio de mandar nominas
        // necesito una lista de usuarios
        //lista.forEach(item-> System.out.println("Enviando correo al usuario "+item.getCorreo()));
        lista.stream()
                .filter(item -> item.getSalario() > 30000)
                .forEach(item -> System.out.println("Cobras demasiado, vas a tener que mandar el IRPF por anticipado al correo " + item.getCorreo()));
        // dar de baja a los usuarios que cobran mas de 80000
    }
}
