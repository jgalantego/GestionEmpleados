package modelo;

import java.time.LocalDate;

public class EmpleadoAsalariado extends Empleado {
    private double salarioBaseMensual;
    private double complementoPuesto;

    public EmpleadoAsalariado(String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double salarioBaseMensual, double complementoPuesto) {
        super(id, dni, nombre, apellidos, email, fechaAlta, departamento, password); // Llama al constructor de Empleado
        this.salarioBaseMensual = salarioBaseMensual;
        this.complementoPuesto = complementoPuesto;
    }

    //Getters y setters
    public double getSalarioBaseMensual() { return this.salarioBaseMensual; }
    public void setSalarioBaseMensual (double salarioBaseMensual) { this.salarioBaseMensual = salarioBaseMensual; }
    public double getComplementoPuesto() { return this.complementoPuesto; }
    public void setComplementoPuesto (double complementoPuesto) { this.complementoPuesto = complementoPuesto; }

    @Override
    public double calcularSalarioBruto() {
        return this.salarioBaseMensual + this.complementoPuesto;
    }
}
