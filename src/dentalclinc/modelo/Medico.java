/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dentalclinc.modelo;

/**
 *
 * @author pereiras-house
 * Donde tengo los atributos que heredan de persona
 */
public class Medico extends Persona {

    // Constructor
    public Medico(String nombre, String apellidos, String nif, String direccion, String telefono, String genero, String fechaNacimiento) {
        super(nombre, apellidos, nif, direccion, telefono, genero, fechaNacimiento);
    }

    
}
