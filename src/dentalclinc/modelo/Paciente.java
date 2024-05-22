/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dentalclinc.modelo;

/**
 *
 * @author pereiras-house
 * Clase java donde tengo los atributos de paciente
 */
public class Paciente extends Persona {
    private String alergia;

    // Constructor
    public Paciente(String nombre, String apellidos, String nif, String direccion, String telefono, String genero, String alergia, String fechaNacimiento) {
        super(nombre, apellidos, nif, direccion, telefono, genero, fechaNacimiento);
        this.alergia = alergia;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

}
