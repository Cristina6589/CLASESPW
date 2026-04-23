package org.example.tiedafx.dao;

import org.example.tiedafx.database.DBConnection;
import org.example.tiedafx.database.DBSchem;
import org.example.tiedafx.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // acciones contra la base de datos
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserDAO(){
        connection = DBConnection.getConnection();
    }

    public int[] getLogin(String mail, String pass){

        int[] datos = null;

        String query = String.format("SELECT %s,%s FROM %s WHERE %s=? AND %s=?",
                DBSchem.COL_ID, DBSchem.COL_PROFILE,
                DBSchem.TAB_CLIENT,
                DBSchem.COL_MAIL, DBSchem.COL_PASS);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,mail);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(DBSchem.COL_ID);
                int profile = resultSet.getInt(DBSchem.COL_PROFILE);
                datos = new int[]{id,profile};
            }

        } catch (SQLException e) {
            System.out.println("Error en la sentencia QUERY");
            System.out.println(e.getMessage());
        }
        return datos;
    }
}
