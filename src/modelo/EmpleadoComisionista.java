package modelo;

import java.time.LocalDate;

public class EmpleadoComisionista extends Empleado {
    private double salarioMinimoGarantizado;
    private double ventasRealizadas;
    private double porcentajeComision;

    public EmpleadoComisionista(String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double salarioMinimoGarantizado, double porcentajeComision) {
        this(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, salarioMinimoGarantizado, porcentajeComision, 5000.0);
    }

    public EmpleadoComisionista(String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double salarioMinimoGarantizado, double porcentajeComision, double ventasRealizadas) {
        super(id, dni, nombre, apellidos, email, fechaAlta, departamento, password);
        this.salarioMinimoGarantizado = salarioMinimoGarantizado;
        this.porcentajeComision = porcentajeComision;
        this.ventasRealizadas = ventasRealizadas;
    }

    public void registrarVenta(double monto) {
        this.ventasRealizadas += monto;
    }

    @Override
    public double calcularSalarioBruto() {
        return this.salarioMinimoGarantizado + (this.ventasRealizadas * this.porcentajeComision);
    }

    //Getters y setters
    public double getSalarioMinimoGarantizado() { return salarioMinimoGarantizado; }
    public double getVentasRealizadas() { return ventasRealizadas; }
    public double getPorcentajeComision() { return porcentajeComision; }
    public void setSalarioMinimoGarantizado(double salarioMinimoGarantizado) { this.salarioMinimoGarantizado = salarioMinimoGarantizado; }
    public void setVentasRealizadas(double ventasRealizadas) { this.ventasRealizadas = ventasRealizadas; }
    public void setPorcentajeComision(double porcentajeComision) { this.porcentajeComision = porcentajeComision; }
}