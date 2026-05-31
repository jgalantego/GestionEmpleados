package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class EmpleadoAsalariado extends Empleado {
    private double salarioBaseMensual;
    private double complementoPuesto;

    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private int contadorInfracciones;

    private static final LocalTime HORA_ENTRADA_POR_DEFECTO = LocalTime.of(9,0);
    private static final LocalTime HORA_SALIDA_POR_DEFECTO = LocalTime.of(17,0);


    public EmpleadoAsalariado(String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double salarioBaseMensual, double complementoPuesto) {
        this(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, salarioBaseMensual, complementoPuesto, HORA_ENTRADA_POR_DEFECTO, HORA_SALIDA_POR_DEFECTO, 0);
    }

    public EmpleadoAsalariado(String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password, double salarioBaseMensual, double complementoPuesto, LocalTime horaEntrada, LocalTime horaSalida, int contadorInfracciones) {
        super(id, dni, nombre, apellidos, email, fechaAlta, departamento, password);
        this.salarioBaseMensual = salarioBaseMensual;
        this.complementoPuesto = complementoPuesto;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.contadorInfracciones = contadorInfracciones;
    }

    //Getters y setters
    public double getSalarioBaseMensual() { return this.salarioBaseMensual; }
    public void setSalarioBaseMensual (double salarioBaseMensual) { this.salarioBaseMensual = salarioBaseMensual; }
    public double getComplementoPuesto() { return this.complementoPuesto; }
    public LocalTime getHoraEntrada () { return this.horaEntrada; }
    public LocalTime getHoraSalida () { return this.horaSalida; }
    public int getContadorInfracciones () { return this.contadorInfracciones; }

    public void setComplementoPuesto (double complementoPuesto) { this.complementoPuesto = complementoPuesto; }
    public void setHoraEntrada (LocalTime horaEntrada) { this.horaEntrada = horaEntrada; }
    public void setHoraSalida (LocalTime horaSalida) { this.horaSalida = horaSalida; }

    @Override
    public double calcularSalarioBruto() {
        return this.salarioBaseMensual + this.complementoPuesto;
    }

    public int incrementarInfracciones (int incremento) {
        this.contadorInfracciones += incremento;
        return this.contadorInfracciones;
    }
}
