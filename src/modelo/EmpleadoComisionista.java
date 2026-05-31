package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class EmpleadoComisionista extends Empleado {
    private static final int VENTAS_POR_DEFECTO = 5000;

    private double salarioMinimoGarantizado;
    private double ventasRealizadas;
    private double porcentajeComision;

    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private int contadorInfracciones;

    private static final LocalTime HORA_ENTRADA_POR_DEFECTO = LocalTime.of(9,0);
    private static final LocalTime HORA_SALIDA_POR_DEFECTO = LocalTime.of(17,0);


    public EmpleadoComisionista(String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double salarioMinimoGarantizado, double porcentajeComision) {
        this(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, salarioMinimoGarantizado, porcentajeComision, VENTAS_POR_DEFECTO, HORA_ENTRADA_POR_DEFECTO, HORA_SALIDA_POR_DEFECTO, 0);
    }

    public EmpleadoComisionista(String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double salarioMinimoGarantizado, double porcentajeComision, double ventasRealizadas) {
        this(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, salarioMinimoGarantizado, porcentajeComision, ventasRealizadas, HORA_ENTRADA_POR_DEFECTO, HORA_SALIDA_POR_DEFECTO, 0);
    }

    public EmpleadoComisionista(String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double salarioMinimoGarantizado, double porcentajeComision, LocalTime horaEntrada, LocalTime horaSalida, int contadorInfracciones) {
        this(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, salarioMinimoGarantizado, porcentajeComision, VENTAS_POR_DEFECTO, horaEntrada, horaSalida, contadorInfracciones);
    }

    public EmpleadoComisionista(String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double salarioMinimoGarantizado, double porcentajeComision, double ventasRealizadas, LocalTime horaEntrada, LocalTime horaSalida, int contadorInfracciones) {
        super(id, dni, nombre, apellidos, email, fechaAlta, departamento, password);
        this.salarioMinimoGarantizado = salarioMinimoGarantizado;
        this.porcentajeComision = porcentajeComision;
        this.ventasRealizadas = ventasRealizadas;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.contadorInfracciones = contadorInfracciones;
    }

    //Getters y setters
    public double getSalarioMinimoGarantizado() { return salarioMinimoGarantizado; }
    public double getVentasRealizadas() { return ventasRealizadas; }
    public double getPorcentajeComision() { return porcentajeComision; }
    public LocalTime getHoraEntrada () { return this.horaEntrada; }
    public LocalTime getHoraSalida () { return this.horaSalida; }
    public int getContadorInfracciones () { return this.contadorInfracciones; }

    public void setSalarioMinimoGarantizado(double salarioMinimoGarantizado) { this.salarioMinimoGarantizado = salarioMinimoGarantizado; }
    public void setVentasRealizadas(double ventasRealizadas) { this.ventasRealizadas = ventasRealizadas; }
    public void setPorcentajeComision(double porcentajeComision) { this.porcentajeComision = porcentajeComision; }
    public void setHoraEntrada (LocalTime horaEntrada) { this.horaEntrada = horaEntrada; }
    public void setHoraSalida (LocalTime horaSalida) { this.horaSalida = horaSalida; }

    @Override
    public double calcularSalarioBruto() {
        return this.salarioMinimoGarantizado + (this.ventasRealizadas * this.porcentajeComision);
    }

    public void registrarVenta(double monto) {
        this.ventasRealizadas += monto;
    }

    public int incrementarInfracciones (int incremento) {
        this.contadorInfracciones += incremento;
        return this.contadorInfracciones;
    }
}