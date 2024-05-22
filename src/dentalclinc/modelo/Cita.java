/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dentalclinc.modelo;

/**
 *
 * @author pereiras-house
 * los atributos de cita
 */
public class Cita {
    private int id;
    private String nifPaciente;
    private String medico;
    private String dientes;
    private String hora;
    private String fecha;
    private String procedimiento;

    public Cita(int id, String nifPaciente, String medico, String dientes, String hora, String fecha, String procedimiento) {
        this.id = id;
        this.nifPaciente = nifPaciente;
        this.medico = medico;
        this.dientes = dientes;
        this.hora = hora;
        this.fecha = fecha;
        this.procedimiento = procedimiento;
    }

    public Cita(String nifPaciente, String medico, String dientes, String hora, String fecha, String procedimiento) {
        this.nifPaciente = nifPaciente;
        this.medico = medico;
        this.dientes = dientes;
        this.hora = hora;
        this.fecha = fecha;
        this.procedimiento = procedimiento;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getNifPaciente() {
        return nifPaciente;
    }

    public void setNifPaciente(String nifPaciente) {
        this.nifPaciente = nifPaciente;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getDientes() {
        return dientes;
    }

    public void setDientes(String dientes) {
        this.dientes = dientes;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }
    
    
    
    
}
