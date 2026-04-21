package dao;

import database.DBConnection;
import database.DBSchem;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // metodos CRUD contra base de datos -> NO APLICO LOGICA
    // CREATE READ UPDATE DELETE
    private Connection connection;
    // para INSERT DELETE UPDATE -> execute() -> boolean executeUpdate() -> int
    private Statement statement;
    private PreparedStatement preparedStatement;
    // para SELECT -> executeQuery() -> ResultSet
    private ResultSet resultSet;

    public void insertUser(User user) {

        // INSERT INTO usuarios (nombre, correo, salario, id_perfil)
        // VALUES ('user.nombre', 'user.correo', user.salario, user.idPerfil)

        connection = DBConnection.getConnection();
        /*String query = "INSERT INTO usuarios (nombre, correo, salario, id_perfil) " +
                "VALUES ('"+user.getNombre()+"','"+user.getCorreo()+"')";*/
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('%s','%s',%d,%d)",
                DBSchem.TAB_NAME,
                DBSchem.COL_NAME, DBSchem.COL_EMAIL, DBSchem.COL_SALARY, DBSchem.COL_IDPROFILE
                , user.getNombre(), user.getCorreo(), user.getSalario(), user.getIdPerfil());
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Error en la sentencia SQL");
            System.out.println(e.getMessage());
        }
    }

    public void insertUserPS(User user) throws SQLException {

        connection = DBConnection.getConnection();
        /*String query = "INSERT INTO usuarios (nombre, correo, salario, id_perfil) " +
                "VALUES ('"+user.getNombre()+"','"+user.getCorreo()+"')";*/
        // esto no es un array ni lista, el primer parametro es el 1 (base 1)
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)",
                DBSchem.TAB_NAME,
                DBSchem.COL_NAME, DBSchem.COL_EMAIL, DBSchem.COL_SALARY, DBSchem.COL_IDPROFILE);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getNombre());
        preparedStatement.setString(2, user.getCorreo());
        preparedStatement.setInt(3, user.getSalario());
        preparedStatement.setInt(4, user.getIdPerfil());
        preparedStatement.executeUpdate();

    }

    public List<User> getAllUsers(){
        // SELECT * FROM usuarios
        List<User> lista = new ArrayList<>();
        connection = DBConnection.getConnection();
        String query = "SELECT * FROM "+DBSchem.TAB_NAME;
        try {
            preparedStatement = connection.prepareStatement(query);
            // preparedStatement.executeUpdate();
            // preparedStatement.execute();
            // quiero la tupla de datos que tiene la consulta SELECT
            //    v  -> rs.next()
            //    R1 R2 R3 R4
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(DBSchem.COL_ID);
                String nombre = resultSet.getString(DBSchem.COL_NAME);
                String correo = resultSet.getString(DBSchem.COL_EMAIL);
                int salario = resultSet.getInt(DBSchem.COL_SALARY);
                int idPerfil = resultSet.getInt(DBSchem.COL_IDPROFILE);
                lista.add(new User(nombre,correo,salario,idPerfil));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta");
        }
        return lista;
    }

}
