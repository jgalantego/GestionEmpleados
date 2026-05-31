package modelo;

import java.time.LocalDate;

public abstract class Empleado implements Evaluable {
    private String id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private LocalDate fechaAlta;
    private String departamento;
    protected double puntuacionDesempenio; // Atributo para la interfaz
    private String password;

    public Empleado(String id, String dni, String nombre, String apellidos, String email, LocalDate fechaAlta, String departamento, String password) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.departamento = departamento;
        this.puntuacionDesempenio = 5.0;
        this.password = password;
    }

    // Metodo abstracto
    public abstract double calcularSalarioBruto();

    // Métodos de la Interfaz
    @Override
    public void registrarEvaluacion(double puntuacion) {
        if (puntuacion >= 0 && puntuacion <= 10) {
            this.puntuacionDesempenio = puntuacion;
        }
    }

    @Override
    public String obtenerEstadoDesempenio() {
        if (puntuacionDesempenio >= 8) return "Excelente";
        if (puntuacionDesempenio >= 5) return "Satisfactorio";
        return "Requiere Mejora";
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDni() { return dni; }
    public void  setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) {this.apellidos = apellidos; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public double getDesempenio() { return puntuacionDesempenio; }
    public LocalDate getFechaAlta() { return this.fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }

    // toString
    @Override
    public String toString() {
        return "[" + id + "] " + apellidos + ", " + nombre + " | DNI: " + dni + " | Email: " + email + " | Dept: " + departamento;
    }

    private static final double TIPO_GENERAL_EMPRESA = 0.3;
    private static final double TIPO_ACCIDENTES_DEFECTO = 0.015; // 1.5% por defecto para oficina (DEV, HR, MK)
    private static final double TIPO_ACCIDENTES_FISICO = 0.02; // 2.0% para Sistemas (Riesgo físico/mantenimiento de servidores)
    private static final double TIPO_ACCIDENTES_ITINERANTE = 0.025; // 2.5% para Comerciales (Riesgo itinerante en carretera)
    private static final double BASE_MAXIMA_COTIZACION = 4720.5;

    // CASO DE USO: Calcular Gastos de Empresa por Trabajador
    public double calcularCosteTotalEmpresa() {
        double tipo_accidentes;

        double sueldoBruto = this.calcularSalarioBruto();

        if (sueldoBruto <= 0) {
            return 0.0;
        }

        tipo_accidentes = TIPO_ACCIDENTES_DEFECTO;

        if (this.getDepartamento().equalsIgnoreCase("SYS")) {
            tipo_accidentes = TIPO_ACCIDENTES_FISICO;
        } else if (this.getDepartamento().equalsIgnoreCase("SALES")) {
            tipo_accidentes = TIPO_ACCIDENTES_ITINERANTE;
        }

        double baseCotizacion = sueldoBruto;

        if (baseCotizacion > BASE_MAXIMA_COTIZACION)
            baseCotizacion = BASE_MAXIMA_COTIZACION;

        double tipoTotalImpuestos = TIPO_GENERAL_EMPRESA + tipo_accidentes;
        double seguridadSocialEmpresarial = baseCotizacion * tipoTotalImpuestos;

        return sueldoBruto + seguridadSocialEmpresarial;
    }

}
