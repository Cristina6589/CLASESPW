package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String nombre, correo;
    private int salario, idPerfil;

    public User(String nombre, String correo, int salario, int idPerfil) {
        this.nombre = nombre;
        this.correo = correo;
        this.salario = salario;
        this.idPerfil = idPerfil;
    }
}
