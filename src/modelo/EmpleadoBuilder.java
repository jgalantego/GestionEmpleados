package modelo;

import java.time.LocalDate;

public class EmpleadoBuilder {
    // Atributos comunes
    private String id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private LocalDate fechaAlta = LocalDate.now(); // Valor por defecto
    private String departamento;
    private String password;

    // Atributos específicos según el tipo de contrato
    private TipoContrato tipoContrato;
    private double salarioBaseMensual;
    private double complementoPuesto;
    private double precioHora;
    private int horasTrabajadas;
    private double salarioMinimoGarantizado;
    private double porcentajeComision;
    private double ventasRealizadas;

    public EmpleadoBuilder () {
        this.horasTrabajadas = -1;
        this.ventasRealizadas = -1.0;
    }

    // Métodos para asignar valores al builder
    public EmpleadoBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public EmpleadoBuilder setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public EmpleadoBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public EmpleadoBuilder setApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public EmpleadoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public EmpleadoBuilder setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
        return this;
    }

    public EmpleadoBuilder setDepartamento(String departamento) {
        this.departamento = departamento;
        return this;
    }

    public EmpleadoBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    // Asalariados
    public EmpleadoBuilder paraAsalariado(double salarioBase, double complemento) {
        this.tipoContrato = TipoContrato.ASALARIADO;
        this.salarioBaseMensual = salarioBase;
        this.complementoPuesto = complemento;
        return this;
    }

    // Empleados Por Horas
    public EmpleadoBuilder paraPorHoras(double precioHora, int horasTrabajadas) {
        this.tipoContrato = TipoContrato.HORAS;
        this.precioHora = precioHora;
        this.horasTrabajadas = horasTrabajadas;
        return this;
    }
    public EmpleadoBuilder paraPorHoras(double precioHora) {
        this.tipoContrato = TipoContrato.HORAS;
        this.precioHora = precioHora;
        return this;
    }

    // Comisionistas
    public EmpleadoBuilder paraComisionista(double minimoGarantizado, double porcentaje, double ventas) {
        this.tipoContrato = TipoContrato.COMISIONISTA;
        this.salarioMinimoGarantizado = minimoGarantizado;
        this.porcentajeComision = porcentaje;
        this.ventasRealizadas = ventas;
        return this;
    }
    public EmpleadoBuilder paraComisionista(double minimoGarantizado, double porcentaje) {
        this.tipoContrato = TipoContrato.COMISIONISTA;
        this.salarioMinimoGarantizado = minimoGarantizado;
        this.porcentajeComision = porcentaje;
        return this;
    }


    public Empleado build() {
        // Validaciones básicas (independientes del tipo de empleado)
        if (!validarTextoNoBlanco(id, "ID") || !validarTextoNoBlanco(dni, "DNI") || !validarTextoNoBlanco(nombre, "NOMBRE") || !validarTextoNoBlanco(apellidos, "APELLIDOS") || tipoContrato == null || !validarTextoNoBlanco(password, "CONTRASEÑA")) {
            throw new IllegalStateException("Error: Faltan datos críticos para construir el empleado.");
        }

        switch (tipoContrato) {
            case ASALARIADO:
                // Validaciones asalariado
                if (!validarDoubleNoNegativo(salarioBaseMensual, "SALARIO BASE MENSUAL") || !validarDoubleNoNegativo(complementoPuesto, "COMPLEMENTO PUESTO"))
                    throw new IllegalStateException("Error: Faltan datos críticos para construir el empleado.");

                return new EmpleadoAsalariado(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, salarioBaseMensual, complementoPuesto);

            case HORAS:
                // Validaciones por horas
                if (!validarDoublePositivo(precioHora, "PRECIO POR HORA"))
                    throw new IllegalStateException("Error: Faltan datos críticos para construir el empleado.");

                if (horasTrabajadas == -1)
                    return new EmpleadoPorHoras(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, precioHora);

                if (!validarDoubleNoNegativo(horasTrabajadas, "HORAS TRABAJADAS"))
                    throw new IllegalStateException("Error: Faltan datos críticos para construir el empleado.");

                return new EmpleadoPorHoras(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, precioHora, horasTrabajadas);

            case COMISIONISTA:
                // Validaciones comisionista
                if (!validarDoubleNoNegativo(salarioMinimoGarantizado, "SALARIO MÍNIMO GARANTIZADO") || !validarEntreCeroYUno(porcentajeComision, "PORCENTAJE COMISIÓN"))
                    throw new IllegalStateException("Error: Faltan datos críticos para construir el empleado.");

                if (ventasRealizadas == -1)
                    return new EmpleadoComisionista(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, salarioMinimoGarantizado, porcentajeComision);

                if (!validarDoubleNoNegativo(ventasRealizadas, "VENTAS REALIZADAS"))
                    throw new IllegalStateException("Error: Faltan datos críticos para construir el empleado.");

                return new EmpleadoComisionista(id, dni, nombre, apellidos, email, fechaAlta, departamento, password, salarioMinimoGarantizado, porcentajeComision, ventasRealizadas);

            default:
                throw new IllegalStateException("Tipo de contrato desconocido");
        }
    }

    // Validaciones --------------------------------------------------------------------------------------------------------------------------------------------

    private boolean validarTextoNoBlanco(String cadena, String campo) {
        if (cadena == null || cadena.isBlank()) {
            System.out.println("El campo " + campo + " no puede estar vacío.");
            return false;
        }
        return true;
    }

    private boolean validarIntPositivo(int valor, String campo) {
        if (valor <= 0) {
            System.out.println("El campo " + campo + " debe ser mayor que cero.");
            return false;
        }
        return true;
    }

    private boolean validarDoublePositivo(double valor, String campo) {
        if (valor <= 0) {
            System.out.println("El campo " + campo + " debe ser mayor que cero.");
            return false;
        }
        return true;
    }

    private boolean validarIntNoNegativo(int valor, String campo) {
        if (valor < 0) {
            System.out.println("El campo " + campo + " no puede ser negativo.");
            return false;
        }
        return true;
    }

    private boolean validarDoubleNoNegativo(double valor, String campo) {
        if (valor < 0) {
            System.out.println("El campo " + campo + " no puede ser negativo.");
            return false;
        }
        return true;
    }

    private boolean validarEntreCeroYUno (double valor, String campo) {
        if (valor < 0 || valor > 1) {
            System.out.println("El campo " + campo + " tiene que valer entre cero y uno.");
            return false;
        }
        return true;
    }

}
