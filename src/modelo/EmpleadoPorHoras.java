package modelo;

import java.time.LocalDate;

public class EmpleadoPorHoras extends Empleado {

    private static final int HORAS_POR_DEFECTO = 160;

    private int horasTrabajadas;
    private double precioHora;

    public EmpleadoPorHoras (String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double precioHora) {
        this(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, precioHora, HORAS_POR_DEFECTO);
    }

    public EmpleadoPorHoras (String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double precioHora, int horasTrabajadas) {
        super(id, dni, nombre, apellidos, email, fechaAlta, departamento, password);
        this.precioHora = precioHora;
        this.horasTrabajadas = horasTrabajadas;
    }

    // Getters y setters
    public int getHorasTrabajadas() { return horasTrabajadas; }
    public double getPrecioHora() { return precioHora; }
    public void setHorasTrabajadas(int horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }
    public void setPrecioHora(double precioHora) { this.precioHora = precioHora; }

    @Override
    public double calcularSalarioBruto() {
        return this.horasTrabajadas * this.precioHora;
    }

    public void registrarHoras(int horas) {
        this.horasTrabajadas += horas;
    }
}