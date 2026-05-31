package controlador;

import modelo.*;
import vista.VistaConsola;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;


public class ControladorEmpresa {
    private final Empresa empresa;
    private final Autenticador autenticador;

    public ControladorEmpresa(Empresa empresa, Autenticador autenticador) {
        this.empresa = empresa;
        this.autenticador = autenticador;
    }

    public void menuPrincipal () {
        int perfil;

        do {
            perfil = VistaConsola.menuPrincipal();

            switch (perfil) {
                case 1:
                    if (autenticador.loginAdministrador()) {
                        menuAdministrador();
                    }
                    break;

                case 2:
                    Empleado empleado = autenticador.loginEmpleado();
                    if (empleado != null) {
                        menuEmpleado(empleado);
                    }
                    break;

                case 3:
                    VistaConsola.mensajeSalidaAplicacion();
                    break;

                default:
                    VistaConsola.mensajeOpcionNoValidaMenu(1, 3);
            }

        } while (perfil != 3);
    }

    // ==========================================
    //          MENÚ DEL ADMINISTRADOR
    // ==========================================
    private void menuAdministrador() {
        int opcion;

        List<Empleado> empleados;
        HashMap<String, String> departamentos = empresa.getDepartamentos();

        do {
            opcion = VistaConsola.menuAdministrador();

            switch (opcion) {
                case 1:
                    // CASO DE USO: Mostrar empleados / Mostrar empleados ordenados (List)
                    int opcionMostrar;
                    do {
                        opcionMostrar = VistaConsola.menuMostrarEmpleados();

                        switch (opcionMostrar) {
                            case 1:
                                //CASO DE USO: Mostrar todos los empleados
                                empleados = empresa.getEmpleados();
                                VistaConsola.mostrarListaEmpleados("LISTA DE TODOS LOS EMPLEADOS", empleados, departamentos);
                                break;
                            case 2:
                                empleados = empresa.getEmpleadosOrdenadosPorAntiguedad();
                                VistaConsola.mostrarListaEmpleados("LISTA DE EMPLEADOS ORDENADOS POR ANTIGUEDAD", empleados, departamentos);
                                break;
                            case 3:
                                empleados = empresa.getEmpleadosOrdenadosPorDesempenio();
                                VistaConsola.mostrarListaEmpleados("LISTA DE EMPLEADOS ORDENADOS POR DESEMPEÑO", empleados, departamentos);
                                break;
                            case 4:
                                break;
                            default:
                                VistaConsola.mensajeOpcionNoValidaMenu(1, 4);
                                break;
                        }
                    } while (opcionMostrar != 4);
                    break;
                case 2:
                    // CASO DE USO: Buscar empleado / Buscar empleados
                    int opcionBuscar;
                    do {
                        opcionBuscar = VistaConsola.menuBuscarEmpleados();

                        switch (opcionBuscar) {
                            case 1:
                                buscarPorId();
                                break;
                            case 2:
                                buscarPorDNI();
                                break;
                            case 3:
                                buscarPorNombre();
                                break;
                            case 4:
                                buscarPorApellido();
                                break;
                            case 5:
                                buscarPorEmail();
                                break;
                            case 6:
                                buscarPorDepartamento();
                                break;
                            case 7:
                                break;
                            default:
                                VistaConsola.mensajeOpcionNoValidaMenu(1, 7);
                        }
                    } while (opcionBuscar != 7);
                    break;
                case 3:
                    int opcionGastos;
                    do {
                        opcionGastos = VistaConsola.menuCalcularGastos();

                        switch (opcionGastos) {
                            case 1:
                                // CASO DE USO: Calcular gastos de empleados
                                double gastoTotalEmpresa = calcularGastoTotalEmpleados();
                                VistaConsola.mostrarGastoTotalEmpresa(gastoTotalEmpresa);
                                break;
                            case 2:
                                // CASO DE USO: Calcular gasto de empleado
                                calcularGastoEmpleado();
                                break;
                            case 3:
                                // CASO DE USO: Calcular gastos de departamento
                                calcularGastosDepartamento(departamentos);
                                break;
                            case 4:
                                break;
                            default:
                                VistaConsola.mensajeOpcionNoValidaMenu(1, 4);
                        }
                    } while (opcionGastos != 4);
                    break;
                case 4:
                    // CASO DE USO: Añadir nuevo empleado
                    formularioAltaEmpleado();
                    break;
                case 5:
                    // CASO DE USO: Modificar empleado
                    VistaConsola.menuModificarEmpleado();
                    modificarEmpleado();
                    break;
                case 6:
                    // CASO DE USO: Eliminar empleado
                    VistaConsola.menuEliminarEmpleado();
                    eliminarEmpleado();
                    break;
                case 7:
                    // CASO DE USO: Mostrar departamentos (HashMap)
                    VistaConsola.menuMostrarDepartamentos();
                    empleados = empresa.getEmpleados();
                    calcularEmpleadosPorDepartamento(departamentos, empleados);
                    break;
                case 8:
                    if (!VistaConsola.preguntaCerrarSesion("admin")) {
                        opcion = -1;
                    }
                    break;
                default:
                    VistaConsola.mensajeOpcionNoValidaMenu(1, 8);
            }
        } while (opcion != 8);
    }

    // ==========================================
    //             MENÚ EMPLEADO
    // ==========================================

    private void menuEmpleado(Empleado empleado) {
        int opcion;
        do {
            opcion = VistaConsola.menuEmpleado(empleado);

            switch (opcion) {

                case 0:
                    if (!VistaConsola.preguntaCerrarSesion("empleado")) {
                        opcion = -1;
                    }
                    break;
                case 1:
                    // CASO DE USO: Consultar nómina
                    VistaConsola.mostrarNominaEmpleado(empleado);
                    break;
                case 2:
                    // CASO DE USO: Consultar horario
                    VistaConsola.menuMostrarHorario();
                    if (empleado instanceof EmpleadoPorHoras) {
                        VistaConsola.mostrarHorarioEmpleadoPorHoras();
                    } else if (empleado instanceof  EmpleadoAsalariado empleadoAsalariado) {
                        VistaConsola.mostrarHorarioEmpleadoAsalariado(empleadoAsalariado);
                    } else if (empleado instanceof  EmpleadoComisionista empleadoComisionista) {
                        VistaConsola.mostrarHorarioEmpleadoComisionista(empleadoComisionista);
                    }
                    break;
                case 3:
                    // CASO DE USO: Registrar fichaje diario / Registrar horas trabajadas
                    if (empleado instanceof EmpleadoPorHoras empleadoPorHoras) {
                        int horasARegistrar = VistaConsola.pedirHorasARegistrar();
                        empleadoPorHoras.registrarHoras(horasARegistrar);
                        VistaConsola.mostrarHorasTrabajadasSumadas(empleadoPorHoras.getHorasTrabajadas(), horasARegistrar);
                    } else {
                        registrarFichajeDiario(empleado);
                    }
                    break;
                case 4:
                    // CASO DE USO: Cambiar contraseña
                    cambiarContrasenia(empleado);
                    break;
                case 5:
                    // CASO DE USO: Registrar ventas realizadas
                    if (empleado instanceof EmpleadoComisionista empleadoComisionista) {
                        double ventasARegistrar = VistaConsola.pedirVentasARegistrar();
                        empleadoComisionista.registrarVenta(ventasARegistrar);
                        VistaConsola.mostrarVentasRealizadasSumadas(empleadoComisionista.getVentasRealizadas(), ventasARegistrar);
                    } else {
                        VistaConsola.mensajeOpcionNoValidaMenu(0, 4);
                    }
                    break;
                default:
                    if (empleado instanceof EmpleadoComisionista) {
                        VistaConsola.mensajeOpcionNoValidaMenu(0, 5);
                    } else {
                        VistaConsola.mensajeOpcionNoValidaMenu(0, 4);
                    }
                    break;
            }
        } while (opcion != 0);
    }

    // ==========================================
    //      MÉTODOS DEL ADMINISTRADOR
    // ==========================================

    // Búsquedas ----------------------------------------------------------------------------

    private void buscarPorId () {
        String idBuscado = VistaConsola.buscarEmpleadoPor("ID");
        Empleado empleado = empresa.getEmpleadoPorId(idBuscado);
        VistaConsola.mostrarEmpleadoEncontradoPor(empleado, "ID");
    }

    private void buscarPorDNI () {
        String dniBuscado = VistaConsola.buscarEmpleadoPor("DNI");
        Empleado empleado = empresa.getEmpleadoPorDni(dniBuscado);
        VistaConsola.mostrarEmpleadoEncontradoPor(empleado, "DNI");
    }

    private void buscarPorNombre () {
        String nombre = VistaConsola.buscarEmpleadoPor("NOMBRE");
        List<Empleado> empleados = empresa.getEmpleadosPorNombre(nombre);
        VistaConsola.mostrarEmpleadosEncontradosPor(empleados, "NOMBRE");
    }

    private void buscarPorApellido () {
        String apellido = VistaConsola.buscarEmpleadoPor("APELLIDO");
        List<Empleado> empleados = empresa.getEmpleadosPorApellido(apellido);
        VistaConsola.mostrarEmpleadosEncontradosPor(empleados, "APELLIDO");
    }

    private void buscarPorEmail () {
        String email = VistaConsola.buscarEmpleadoPor("EMAIL");
        List<Empleado> empleados = empresa.getEmpleadosPorEmail(email);
        VistaConsola.mostrarEmpleadosEncontradosPor(empleados, "EMAIL");
    }

    private void buscarPorDepartamento () {
        String departamento = VistaConsola.buscarEmpleadoPor("DEPARTAMENTO (abreviatura)");
        List<Empleado> empleados = empresa.getEmpleadosPorDepartamento(departamento);
        VistaConsola.mostrarEmpleadosEncontradosPor(empleados, "DEPARTAMENTO");
    }

    // Calcular gastos -----------------------------------------------------------------------

    private void calcularGastoEmpleado() {
        String id = VistaConsola.buscarEmpleadoPor("ID");
        Empleado empleado = empresa.getEmpleadoPorId(id);
        if (empleado != null) {
            VistaConsola.mostrarGastoEmpleado(empleado);
        } else {
            VistaConsola.mensajeNoExisteEmpleado("ID");
        }

    }

    private double calcularGastoTotalEmpleados() {
        double gastoTotalEmpresa = 0.0;
        List<Empleado> empleados = empresa.getEmpleados();
        for (Empleado empleado : empleados) {
            gastoTotalEmpresa += empleado.calcularCosteTotalEmpleado();
        }
        return gastoTotalEmpresa;
    }

    private void calcularGastosDepartamento(HashMap<String, String> departamentos) {
        String departamento = VistaConsola.preguntarDepartamento();

        List<Empleado> empleados = empresa.getEmpleadosPorDepartamento(departamento);
        if (empleados.isEmpty()) {
            VistaConsola.mostrarNoExisteDepartamento();
        } else {
            double gastoDepartamento = 0;
            for (Empleado empleado : empleados) {
                gastoDepartamento += empleado.calcularCosteTotalEmpleado();
            }
            VistaConsola.mostrarGastoDepartamento(departamentos.get(departamento), gastoDepartamento);
        }
    }

    // Modificar empleado ------------------------------------------------------------------------

    private void modificarEmpleado() {
        String id = VistaConsola.pedirIdEmpleadoAModificar();
        Empleado empleado = empresa.getEmpleadoPorId(id);
        if (empleado != null) {
            VistaConsola.mostrarTipoEmpleadoEncontrado(empleado);

            int opcion;
            do {
                opcion = VistaConsola.mostrarCamposEmpleado(empleado);

                switch (opcion) {
                    case 0:
                        break;
                    case 1:
                        String nuevoId = VistaConsola.pedirNuevoCampoString("ID");
                        if (modificarIdEmpleado(empleado.getId(), nuevoId))
                            VistaConsola.mostrarCampoModificado("ID", nuevoId);
                        break;
                    case 2:
                        String nuevoDNI = VistaConsola.pedirNuevoCampoString("DNI");
                        empleado.setDni(nuevoDNI);
                        VistaConsola.mostrarCampoModificado("DNI", nuevoDNI);
                        break;
                    case 3:
                        String nuevoNombre = VistaConsola.pedirNuevoCampoString("NOMBRE");
                        empleado.setNombre(nuevoNombre);
                        VistaConsola.mostrarCampoModificado("NOMBRE", nuevoNombre);
                        break;
                    case 4:
                        String nuevosApellidos = VistaConsola.pedirNuevoCampoString("APELLIDOS");
                        empleado.setApellidos(nuevosApellidos);
                        VistaConsola.mostrarCampoModificado("APELLIDOS", nuevosApellidos);
                        break;
                    case 5:
                        String nuevoEmail = VistaConsola.pedirNuevoCampoString("EMAIL");
                        empleado.setEmail(nuevoEmail);
                        VistaConsola.mostrarCampoModificado("EMAIL", nuevoEmail);
                        break;
                    case 6:
                        LocalDate nuevaFechaAlta = VistaConsola.pedirNuevoCampoFecha();
                        empleado.setFechaAlta(nuevaFechaAlta);
                        VistaConsola.mostrarCampoModificado("FECHA ALTA", nuevaFechaAlta.toString());
                        break;
                    case 7:
                        String nuevoDepartamento = VistaConsola.pedirNuevoCampoString("DEPARTAMENTO");
                        empleado.setDepartamento(nuevoDepartamento.toUpperCase());
                        VistaConsola.mostrarCampoModificado("DEPARTAMENTO", nuevoDepartamento);
                        break;
                    case 8:
                        boolean bandera = false;
                        do {
                            double nuevoDesempenio = VistaConsola.pedirNuevoCampoDouble("DESEMPEÑO (0-10)");
                            if (nuevoDesempenio >= 0 && nuevoDesempenio <= 10) {
                                empleado.registrarEvaluacion(nuevoDesempenio);
                                VistaConsola.mostrarCampoModificado("DESEMPEÑO", String.valueOf(nuevoDesempenio));
                                bandera = true;
                            } else {
                                VistaConsola.mensajeDesempenioNoValido();
                            }
                        } while (!bandera);
                        break;

                    case 9:
                        switch (empleado) {
                            case EmpleadoAsalariado asalariado -> {
                                double nuevoSalarioBase = VistaConsola.pedirNuevoCampoDouble("SALARIO BASE MENSUAL");
                                asalariado.setSalarioBaseMensual(nuevoSalarioBase);
                                VistaConsola.mostrarCampoModificado("SALARIO BASE MENSUAL", String.valueOf(nuevoSalarioBase));
                            }
                            case EmpleadoPorHoras porHoras -> {
                                int nuevasHoras = VistaConsola.pedirNuevoCampoInt("HORAS TRABAJADAS");
                                porHoras.setHorasTrabajadas(nuevasHoras);
                                VistaConsola.mostrarCampoModificado("HORAS TRABAJADAS", String.valueOf(nuevasHoras));
                            }
                            case EmpleadoComisionista comisionista -> {
                                double nuevoSalarioMinimo = VistaConsola.pedirNuevoCampoDouble("SALARIO MÍNIMO GARANTIZADO");
                                comisionista.setSalarioMinimoGarantizado(nuevoSalarioMinimo);
                                VistaConsola.mostrarCampoModificado("SALARIO MÍNIMO GARANTIZADO", String.valueOf(nuevoSalarioMinimo));
                            }
                            default -> { }
                        }
                        break;
                    case 10:
                        switch (empleado) {
                            case EmpleadoAsalariado asalariado -> {
                                double nuevoComplemento = VistaConsola.pedirNuevoCampoDouble("COMPLEMENTO PUESTO");
                                asalariado.setComplementoPuesto(nuevoComplemento);
                                VistaConsola.mostrarCampoModificado("COMPLEMENTO PUESTO", String.valueOf(nuevoComplemento));
                            }
                            case EmpleadoPorHoras porHoras -> {
                                int nuevoPrecioHora = VistaConsola.pedirNuevoCampoInt("PRECIO POR HORA");
                                porHoras.setPrecioHora(nuevoPrecioHora);
                                VistaConsola.mostrarCampoModificado("PRECIO POR HORA", String.valueOf(nuevoPrecioHora));
                            }
                            case EmpleadoComisionista comisionista -> {
                                double nuevasVentas = VistaConsola.pedirNuevoCampoDouble("VENTAS REALIZADAS");
                                comisionista.setVentasRealizadas(nuevasVentas);
                                VistaConsola.mostrarCampoModificado("VENTAS REALIZADAS", String.valueOf(nuevasVentas));
                            }
                            default -> { }
                        }
                        break;
                    case 11:
                        switch (empleado) {
                            case EmpleadoAsalariado asalariado -> {
                                LocalTime nuevaHoraEntrada = VistaConsola.pedirNuevoCampoHora("HORA ENTRADA");
                                asalariado.setHoraEntrada(nuevaHoraEntrada);
                                VistaConsola.mostrarHoraModificada("HORA ENTRADA", nuevaHoraEntrada);
                            }
                            case EmpleadoPorHoras porHoras -> VistaConsola.mensajeOpcionNoValidaMenu(0, 10);
                            case EmpleadoComisionista comisionista -> {
                                double nuevoPorcentaje = VistaConsola.pedirNuevoCampoDouble("PORCENTAJE DE COMISIÓN");
                                comisionista.setPorcentajeComision(nuevoPorcentaje);
                                VistaConsola.mostrarCampoModificado("PORCENTAJE DE COMISIÓN", String.valueOf(nuevoPorcentaje));
                            }
                            default -> { }
                        }
                        break;
                    case 12:
                        switch (empleado) {
                            case EmpleadoAsalariado asalariado -> {
                                LocalTime nuevaHoraSalida = VistaConsola.pedirNuevoCampoHora("HORA SALIDA");
                                asalariado.setHoraSalida(nuevaHoraSalida);
                                VistaConsola.mostrarHoraModificada("HORA SALIDA", nuevaHoraSalida);
                            }
                            case EmpleadoPorHoras porHoras -> VistaConsola.mensajeOpcionNoValidaMenu(0, 10);
                            case EmpleadoComisionista comisionista -> {
                                LocalTime nuevaHoraEntrada = VistaConsola.pedirNuevoCampoHora("HORA ENTRADA");
                                comisionista.setHoraEntrada(nuevaHoraEntrada);
                                VistaConsola.mostrarHoraModificada("HORA ENTRADA", nuevaHoraEntrada);
                            }
                            default -> { }
                        }
                        break;
                    case 13:
                        switch (empleado) {
                            case EmpleadoAsalariado asalariado -> VistaConsola.mensajeOpcionNoValidaMenu(0, 12);
                            case EmpleadoPorHoras porHoras -> VistaConsola.mensajeOpcionNoValidaMenu(0, 10);
                            case EmpleadoComisionista comisionista -> {
                                LocalTime nuevaHoraSalida = VistaConsola.pedirNuevoCampoHora("HORA SALIDA");
                                comisionista.setHoraSalida(nuevaHoraSalida);
                                VistaConsola.mostrarHoraModificada("HORA SALIDA", nuevaHoraSalida);
                            }
                            default -> { }
                        }
                        break;
                    default:
                        switch (empleado) {
                            case EmpleadoAsalariado asalariado -> VistaConsola.mensajeOpcionNoValidaMenu(0, 12);
                            case EmpleadoPorHoras porHoras -> VistaConsola.mensajeOpcionNoValidaMenu(0, 10);
                            case EmpleadoComisionista comisionista -> VistaConsola.mensajeOpcionNoValidaMenu(0, 13);
                            default -> { }
                        }
                        break;
                }
            } while (opcion != 0);
        } else {
            VistaConsola.mensajeNoExisteEmpleado("ID");
        }
    }

    private boolean modificarIdEmpleado(String idActual, String nuevoId) {
        List<Empleado> empleados = empresa.getEmpleados();
        for (Empleado empleado : empleados) {
            if (empleado.getId().equalsIgnoreCase(nuevoId)) {
                VistaConsola.mensajeIdEmpleadoRepetidoModificar();
                return false;
            }
        }

        Empleado empleado = empresa.getEmpleadoPorId(idActual);
        if (empleado != null) {
            empleado.setId(nuevoId);
            return true;
        } else {
            VistaConsola.mensajeNoExisteEmpleado("ID");
            return false;
        }
    }

    // Calcular número de empleados por departamento ----------------------------------------------------------

    public void calcularEmpleadosPorDepartamento(HashMap<String, String> departamentos, List<Empleado> empleados) {

        if (departamentos.isEmpty()) {
            VistaConsola.mostrarNoExistenDepartamentos();
            VistaConsola.finMostrarDepartamentos();
            return;
        }

        for (HashMap.Entry<String, String> departamento : departamentos.entrySet()) {
            String codigo = departamento.getKey();
            String nombre = departamento.getValue();

            int contador = 0;
            for (Empleado empleado : empleados) {
                if (empleado.getDepartamento().equalsIgnoreCase(codigo)) {
                    contador++;
                }
            }

            VistaConsola.mostrarDatosDepartamento(codigo, nombre, contador);
        }

        VistaConsola.finMostrarDepartamentos();
    }

    private void formularioAltaEmpleado() {
        VistaConsola.menuAnadirEmpleado();

        String id;
        boolean repetido;
        do {
            id = VistaConsola.pedirIdEmpleado();

            if (empresa.getEmpleadoPorId(id) != null) {
                VistaConsola.mensajeIdEmpleadoRepetidoAnadir();
                repetido = true;
            } else {
                repetido = false;
            }
        } while (repetido);

        String dni = VistaConsola.pedirDNIEmpleado();
        String nombre = VistaConsola.pedirNombreEmpleado();
        String apellidos = VistaConsola.pedirApellidosEmpleado();
        String email = VistaConsola.pedirEmailEmpleado();
        String dept = VistaConsola.pedirDepartamentoEmpleado();
        String password = VistaConsola.pedirContraseniaEmpleado();

        int tipo;
        do {
            tipo = VistaConsola.menuSeleccionContrato();
            if (tipo != 1 && tipo != 2 && tipo != 3) {
                VistaConsola.mensajeOpcionNoValidaMenu(1, 3);
            }
        } while (tipo != 1 && tipo != 2 && tipo != 3);

        Empleado empleadoAnadido;
        int decisionHorario, decisionHoras;
        LocalTime horaEntrada, horaSalida;

        try {
            switch (tipo) {
                case 1: // ASALARIADO
                    do {
                        decisionHorario = VistaConsola.menuHorarioEmpleado();
                        if (decisionHorario != 1 && decisionHorario != 2) {
                            VistaConsola.mensajeOpcionNoValidaMenu(1, 2);
                        }
                    } while (decisionHorario != 1 && decisionHorario != 2);

                    if (decisionHorario == 1) {
                        horaEntrada = VistaConsola.pedirHoraEntradaEmpleado();
                        horaSalida = VistaConsola.pedirHoraSalidaEmpleado();
                        double base = VistaConsola.pedirSalarioBaseEmpleado();
                        double complemento = VistaConsola.pedirComplementoPuestoEmpleado();
                        empleadoAnadido = empresa.agregarEmpleadoAsalariado(id, dni, nombre, apellidos, email, dept, password, base, complemento, horaEntrada, horaSalida, 0);

                    } else {
                        double base = VistaConsola.pedirSalarioBaseEmpleado();
                        double complemento = VistaConsola.pedirComplementoPuestoEmpleado();
                        empleadoAnadido = empresa.agregarEmpleadoAsalariado(id, dni, nombre, apellidos, email, dept, password, base, complemento);
                    }

                    VistaConsola.mostrarEmpleadoAnadido(empleadoAnadido, "ASALARIADO");
                    break;

                case 2: // POR HORAS
                    double precioHora = VistaConsola.pedirPrecioHoraEmpleado();

                    do {
                        decisionHoras = VistaConsola.menuHorasEmpleado();
                        if (decisionHoras != 1 && decisionHoras != 2) {
                            VistaConsola.mensajeOpcionNoValidaMenu(1, 2);
                        }
                    } while (decisionHoras != 1 && decisionHoras != 2);

                    if (decisionHoras == 1) {
                        int horas = VistaConsola.pedirHorasTrabajadasEmpleado();
                        empleadoAnadido = empresa.agregarEmpleadoPorHoras(id, dni, nombre, apellidos, email, dept, password, precioHora, horas);
                    } else {
                        empleadoAnadido = empresa.agregarEmpleadoPorHoras(id, dni, nombre, apellidos, email, dept, password, precioHora);
                    }

                    VistaConsola.mostrarEmpleadoAnadido(empleadoAnadido, "POR HORAS");
                    break;

                case 3: // COMISIONISTA
                    do {
                        decisionHorario = VistaConsola.menuHorarioEmpleado();
                        if (decisionHorario != 1 && decisionHorario != 2) {
                            VistaConsola.mensajeOpcionNoValidaMenu(1, 2);
                        }
                    } while (decisionHorario != 1 && decisionHorario != 2);

                    if (decisionHorario == 1) {
                        horaEntrada = VistaConsola.pedirHoraEntradaEmpleado();
                        horaSalida = VistaConsola.pedirHoraSalidaEmpleado();
                        double minimoGarantizado = VistaConsola.pedirSalarioMinimoEmpleado();
                        double porcentaje;
                        do {
                            porcentaje = VistaConsola.pedirPorcentajeComisionEmpleado();
                            if (porcentaje < 0 || porcentaje > 1)
                                VistaConsola.mensajePorcentajeErroneo();
                        } while (porcentaje < 0 || porcentaje > 1);

                        int decisionVentas;
                        do {
                            decisionVentas = VistaConsola.menuVentasEmpleado();
                            if (decisionVentas != 1 && decisionVentas != 2)
                                VistaConsola.mensajeOpcionNoValidaMenu(1, 2);
                        } while (decisionVentas != 1 && decisionVentas != 2);


                        if (decisionVentas == 1) {
                            double ventas = VistaConsola.pedirVentasEmpleado();
                            empleadoAnadido = empresa.agregarEmpleadoComisionista(id, dni, nombre, apellidos, email, dept, password, minimoGarantizado, porcentaje, ventas, horaEntrada, horaSalida, 0);
                        } else {
                            empleadoAnadido = empresa.agregarEmpleadoComisionista(id, dni, nombre, apellidos, email, dept, password, minimoGarantizado, porcentaje, horaEntrada, horaSalida, 0);
                        }

                    } else {
                        double minimoGarantizado = VistaConsola.pedirSalarioMinimoEmpleado();
                        double porcentaje;
                        do {
                            porcentaje = VistaConsola.pedirPorcentajeComisionEmpleado();
                            if (porcentaje < 0 || porcentaje > 1)
                                VistaConsola.mensajePorcentajeErroneo();
                        } while (porcentaje < 0 || porcentaje > 1);

                        int decisionVentas;
                        do {
                            decisionVentas = VistaConsola.menuVentasEmpleado();
                            if (decisionVentas != 1 && decisionVentas != 2)
                                VistaConsola.mensajeOpcionNoValidaMenu(1, 2);
                        } while (decisionVentas != 1 && decisionVentas != 2);


                        if (decisionVentas == 1) {
                            double ventas = VistaConsola.pedirVentasEmpleado();
                            empleadoAnadido = empresa.agregarEmpleadoComisionista(id, dni, nombre, apellidos, email, dept, password, minimoGarantizado, porcentaje, ventas);
                        } else {
                            empleadoAnadido = empresa.agregarEmpleadoComisionista(id, dni, nombre, apellidos, email, dept, password, minimoGarantizado, porcentaje);
                        }
                    }

                    VistaConsola.mostrarEmpleadoAnadido(empleadoAnadido, "COMISIONISTA");
                    break;
            }

        } catch (IllegalStateException exception) {
            VistaConsola.mensajeFalloCrearEmpleado();
        }
    }

    private void eliminarEmpleado () {
        String idEliminar = VistaConsola.pedirIdEmpleadoAEliminar();
        Empleado empleadoEliminado = empresa.eliminarEmpleado(idEliminar);
        if (empleadoEliminado != null) {
            VistaConsola.mostrarEmpleadoEliminado(empleadoEliminado);
        } else {
            VistaConsola.mostrarEmpleadoEncontradoPor(null, "ID");
        }
    }

    // ==========================================
    //      MÉTODOS DEL EMPLEADO
    // ==========================================

    private void cambiarContrasenia (Empleado empleado) {
        String actual, nueva;
        VistaConsola.menuCambiarContrasenia();
        actual = VistaConsola.pedirContrasenia("contraseña actual");
        nueva = VistaConsola.pedirContrasenia("nueva contraseña");

        if (actual.equals(empleado.getPassword())) {
            empleado.setPassword(nueva);
            VistaConsola.mensajeContraseniaCambiada();
        } else if (actual.equals(nueva)) {
            VistaConsola.mensajeMismaContrasenia();
        } else {
            VistaConsola.mensajeContraseniaErronea();
        }
    }

    // CASO DE USO: Registrar fichaje diario
    private void registrarFichajeDiario(Empleado empleado) {
        if (empleado instanceof EmpleadoPorHoras) {
            VistaConsola.mostrarExitoFichajeEntrada(LocalTime.now());
            return;
        }

        if (empleado instanceof EmpleadoAsalariado emp) {
            procesarControlHorario(emp, emp.getHoraEntrada(), emp.getHoraSalida());
        } else if (empleado instanceof EmpleadoComisionista emp) {
            procesarControlHorario(emp, emp.getHoraEntrada(), emp.getHoraSalida());
        }
    }


    private void procesarControlHorario(Empleado empleado, LocalTime entradaEstipulada, LocalTime salidaEstipulada) {
        LocalTime horaActual = LocalTime.now();

        boolean esEntrada = horaActual.isBefore(entradaEstipulada.plusHours(4));
        if (esEntrada) {
            // REGLA: Más de 15 minutos tarde
            if (horaActual.isAfter(entradaEstipulada.plusMinutes(15))) {
                int actuales;
                if (empleado instanceof EmpleadoAsalariado empleadoAsalariado){
                    actuales = empleadoAsalariado.incrementarInfracciones(1);
                } else if (empleado instanceof  EmpleadoComisionista empleadoComisionista) {
                    actuales = empleadoComisionista.incrementarInfracciones(1);
                } else {
                    VistaConsola.mensajeErrorRegistroEntrada();
                    return;
                }

                int restantes = 10 - actuales;
                VistaConsola.mostrarAvisoRetraso(horaActual, restantes);

                if (restantes <= 0) {
                    empleado.registrarEvaluacion(empleado.getDesempenio() - 1);
                    VistaConsola.mostrarAvisoPenalizacionEvaluacion();
                }
            } else {
                VistaConsola.mostrarExitoFichajeEntrada(horaActual);
            }
        } else {
            // REGLA: Más de 15 minutos antes de la hora de salida
            if (horaActual.isBefore(salidaEstipulada.minusMinutes(15))) {
                int actuales;
                if (empleado instanceof EmpleadoAsalariado empleadoAsalariado){
                    actuales = empleadoAsalariado.incrementarInfracciones(1);
                } else if (empleado instanceof  EmpleadoComisionista empleadoComisionista) {
                    actuales = empleadoComisionista.incrementarInfracciones(1);
                } else {
                    VistaConsola.mensajeErrorRegistroSalida();
                    return;
                }
                int restantes = 10 - actuales;

                VistaConsola.mostrarAvisoSalidaAnticipada(horaActual, restantes);
                if (restantes <= 0) {
                    empleado.registrarEvaluacion(empleado.getDesempenio() - 1);
                    VistaConsola.mostrarAvisoPenalizacionEvaluacion();
                }
            } else {
                VistaConsola.mostrarExitoFichajeSalida(horaActual);
            }
        }
    }

}
