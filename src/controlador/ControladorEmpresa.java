package controlador;

import modelo.*;
import vista.VistaConsola;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControladorEmpresa {
    private final Empresa empresa;
    private final Autenticador autenticador;
    private static final Scanner teclado = new Scanner(System.in);

    public ControladorEmpresa(Empresa empresa, Autenticador autenticador) {
        this.empresa = empresa;
        this.autenticador = autenticador;
    }

    public void menuPrincipal () {
        int perfil = 0;

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
        int opcion = -1;

        List<Empleado> empleados = new ArrayList<>();
        HashMap<String, String> departamentos = empresa.obtenerDepartamentos();

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
                                empleados = empresa.obtenerEmpleados();
                                VistaConsola.mostrarListaEmpleados("LISTA DE TODOS LOS EMPLEADOS", empleados, departamentos);
                                break;
                            case 2:
                                empleados = empresa.obtenerEmpleadosOrdenadosPorAntiguedad();
                                VistaConsola.mostrarListaEmpleados("LISTA DE EMPLEADOS ORDENADOS POR ANTIGUEDAD", empleados, departamentos);
                                break;
                            case 3:
                                empleados = empresa.obtenerEmpleadosOrdenadosPorDesempenio();
                                VistaConsola.mostrarListaEmpleados("LISTA DE EMPLEADOS ORDENADOS POR DESEMPEÑO", empleados, departamentos);
                                break;
                            case 4:
                                // Volver al menu administración
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
                                calcularGastoTotalEmpleados();
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
                    empleados = empresa.obtenerEmpleados();
                    calcularEmpleadosPorDepartamento(departamentos, empleados);
                    break;
                case 8:
                    if (VistaConsola.preguntaCerrarSesion("admin")) {
                        break;
                    } else {
                        opcion = -1;
                        break;
                    }
                default:
                    VistaConsola.mensajeOpcionNoValidaMenu(1, 8);
            }
        } while (opcion != 8);
    }

    // ==========================================
    //          MENÚ DEL EMPLEADO
    // ==========================================

    private void menuEmpleado(Empleado empleado) {
        int opcion = -1;
        do {
            opcion = VistaConsola.menuEmpleado();

            switch (opcion) {
                case 1:
                    // CASO DE USO: Consultar nómina propia
                    VistaConsola.mostrarNominaEmpleado(empleado);
                    break;
                case 2:
                    // CASO DE USO: Registrar fichaje diario
                    System.out.println("Fichaje de Entrada registrado correctamente a las " + java.time.LocalTime.now());
                    break;
                case 3:
                    // CASO DE USO: Cambiar contraseña
                    cambiarContrasenia(empleado);
                    break;
                case 4:
                    if (!VistaConsola.preguntaCerrarSesion("empleado")) {
                        opcion = -1;
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Introduzca un número 1-3.");
            }
        } while (opcion != 4);
    }

    // ==========================================
    //      MÉTODOS DEL ADMINISTRADOR
    // ==========================================

    // Búsquedas ----------------------------------------------------------------------------

    private void buscarPorId () {
        String dniBuscado = VistaConsola.buscarEmpleadoPor("ID");
        Empleado empleado = empresa.buscarPorId(dniBuscado);
        VistaConsola.mostrarEmpleadoEncontradoPor(empleado, "ID");
    }

    private void buscarPorDNI () {
        String dniBuscado = VistaConsola.buscarEmpleadoPor("DNI");
        Empleado empleado = empresa.buscarPorDni(dniBuscado);
        VistaConsola.mostrarEmpleadoEncontradoPor(empleado, "DNI");
    }

    private void buscarPorNombre () {
        String nombre = VistaConsola.buscarEmpleadoPor("NOMBRE");
        List<Empleado> empleados = empresa.buscarPorNombre(nombre);
        VistaConsola.mostrarEmpleadosEncontradosPor(empleados, "NOMBRE");
    }

    private void buscarPorApellido () {
        String apellido = VistaConsola.buscarEmpleadoPor("APELLIDO");
        List<Empleado> empleados = empresa.buscarPorApellido(apellido);
        VistaConsola.mostrarEmpleadosEncontradosPor(empleados, "APELLIDO");
    }

    private void buscarPorEmail () {
        String email = VistaConsola.buscarEmpleadoPor("EMAIL");
        List<Empleado> empleados = empresa.buscarPorEmail(email);
        VistaConsola.mostrarEmpleadosEncontradosPor(empleados, "EMAIL");
    }

    private void buscarPorDepartamento () {
        String departamento = VistaConsola.buscarEmpleadoPor("DEPARTAMENTO (abreviatura)");
        List<Empleado> empleados = empresa.buscarPorDepartamento(departamento);
        VistaConsola.mostrarEmpleadosEncontradosPor(empleados, "DEPARTAMENTO");
    }

    // Calcular gastos -----------------------------------------------------------------------

    private void calcularGastoEmpleado() {
        String id = VistaConsola.buscarEmpleadoPor("ID");
        Empleado empleado = empresa.buscarPorId(id);
        VistaConsola.mostrarGastoEmpleado(empleado);
    }

    private void calcularGastoTotalEmpleados() {
        double gastoTotalEmpresa = 0.0;
        List<Empleado> empleados = empresa.obtenerEmpleados();
        for (Empleado empleado : empleados) {
            gastoTotalEmpresa += empleado.calcularCosteTotalEmpresa();
        }
        VistaConsola.mostrarGastoTotalEmpresa(gastoTotalEmpresa);
    }

    private void calcularGastosDepartamento(HashMap<String, String> departamentos) {
        String departamento = VistaConsola.preguntarDepartamento();

        List<Empleado> empleados = empresa.buscarPorDepartamento(departamento);
        if (empleados.isEmpty()) {
            VistaConsola.mostrarNoExisteDepartamento();
        } else {
            double gastoDepartamento = 0;
            for (Empleado empleado : empleados) {
                gastoDepartamento += empleado.calcularCosteTotalEmpresa();
            }
            VistaConsola.mostrarGastoDepartamento(departamentos.get(departamento), gastoDepartamento);
        }
    }

    // Modificar empleado ------------------------------------------------------------------------

    private void modificarEmpleado() {
        String id = VistaConsola.pedirIdEmpleadoAModificar();
        Empleado empleado = empresa.buscarPorId(id);
        if (empleado != null) {
            VistaConsola.mostrarTipoEmpleadoEncontrado(empleado);

            int opcion = -1;
            do {
                opcion = VistaConsola.mostrarCamposEmpleado(empleado);

                switch (opcion) {
                    case 0:
                        break;
                    case 1:
                        String nuevoId = VistaConsola.pedirNuevoCampoString("ID");
                        if (empresa.modificarIdEmpleado(empleado.getId(), nuevoId))
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
                            double nuevoDesempenio = VistaConsola.pedirNuevoCampoDouble("DESEMPEÑO");
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
                        if (empleado instanceof EmpleadoAsalariado asalariado) { // Es asalariado
                            double nuevoSalarioBase = VistaConsola.pedirNuevoCampoDouble("SALARIO BASE MENSUAL");
                            asalariado.setSalarioBaseMensual(nuevoSalarioBase);
                            VistaConsola.mostrarCampoModificado("SALARIO BASE MENSUAL", String.valueOf(nuevoSalarioBase));

                        } else if (empleado instanceof EmpleadoPorHoras porHoras) { // Es por horas
                            int nuevasHoras = VistaConsola.pedirNuevoCampoInt("HORAS TRABAJADAS");
                            porHoras.setHorasTrabajadas(nuevasHoras);
                            VistaConsola.mostrarCampoModificado("HORAS TRABAJADAS", String.valueOf(nuevasHoras));

                        } else { // Es comisionista
                            double nuevoSalarioMinimo = VistaConsola.pedirNuevoCampoDouble("SALARIO MÍNIMO GARANTIZADO");
                            ((EmpleadoComisionista) empleado).setSalarioMinimoGarantizado(nuevoSalarioMinimo);
                            VistaConsola.mostrarCampoModificado("SALARIO MÍNIMO GARANTIZADO", String.valueOf(nuevoSalarioMinimo));

                        }
                        break;
                    case 10:
                        if (empleado instanceof EmpleadoAsalariado asalariado) { // Es asalariado
                            double nuevoComplemento = VistaConsola.pedirNuevoCampoDouble("COMPLEMENTO PUESTO");
                            asalariado.setComplementoPuesto(nuevoComplemento);
                            VistaConsola.mostrarCampoModificado("COMPLEMENTO PUESTO", String.valueOf(nuevoComplemento));

                        } else if (empleado instanceof EmpleadoPorHoras porHoras) { // Es por horas
                            int nuevoPrecioHora = VistaConsola.pedirNuevoCampoInt("PRECIO POR HORA");
                            porHoras.setPrecioHora(nuevoPrecioHora);
                            VistaConsola.mostrarCampoModificado("PRECIO POR HORA", String.valueOf(nuevoPrecioHora));

                        } else { // Es comisionista
                            double nuevasVentas = VistaConsola.pedirNuevoCampoDouble("VENTAS REALIZADAS");
                            ((EmpleadoComisionista) empleado).setVentasRealizadas(nuevasVentas);
                            VistaConsola.mostrarCampoModificado("VENTAS REALIZADAS", String.valueOf(nuevasVentas));
                        }
                        break;
                    case 11:
                        if (empleado instanceof EmpleadoComisionista comisionista) {
                            double nuevoPorcentaje = VistaConsola.pedirNuevoCampoDouble("PORCENTAJE DE COMISIÓN");
                            comisionista.setPorcentajeComision(nuevoPorcentaje);
                            VistaConsola.mostrarCampoModificado("PORCENTAJE DE COMISIÓN", String.valueOf(nuevoPorcentaje));
                        } else {
                            VistaConsola.mensajeOpcionNoValidaMenu(0, 10);
                        }
                        break;
                    default:
                        VistaConsola.mensajeOpcionNoValidaMenu(0, 11);
                        break;
                }
            } while (opcion != 0);
        } else {
            VistaConsola.mostrarEmpleadoEncontradoPor(null,"ID");
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
        System.out.println("\n--- FORMULARIO DE ALTA EMLPEADO ---");
        System.out.print("ID Único (ej: E101): ");
        String id = teclado.nextLine();
        System.out.print("DNI/NIE: ");
        String dni = teclado.nextLine();
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = teclado.nextLine();
        System.out.print("Email Corporativo: ");
        String email = teclado.nextLine();
        System.out.print("Código de Departamento (DEV, SYS, MK, SALES, HR): ");
        String dept = teclado.nextLine().toUpperCase();
        System.out.print("Contraseña: ");
        String password = teclado.nextLine();

        System.out.println("Seleccione Tipo de Contrato:");
        System.out.println("1. Asalariado");
        System.out.println("2. Por Horas");
        System.out.println("3. Comisionista");
        System.out.print("Seleccione una opción: ");
        int tipo = leerEnteroSeguro();

        try {
            switch (tipo) {
                case 1: // ASALARIADO
                    System.out.print("Salario Base Mensual (€): ");
                    double base = leerDoubleSeguro();
                    System.out.print("Complemento de Puesto (€): ");
                    double complemento = leerDoubleSeguro();

                    empresa.agregarEmpleadoAsalariado(id, dni, nombre, apellidos, email, dept, password, base, complemento);

                    System.out.println("Empleado Asalariado añadido.");
                    break;

                case 2: // POR HORAS
                    System.out.print("Precio de la hora (€): ");
                    double precioHora = leerDoubleSeguro();

                    System.out.println("¿Desea introducir horas específicas o aplicar el valor por defecto?");
                    System.out.println("1. Introducir horas manualmente");
                    System.out.println("2. Usar valor por defecto (160 horas)");
                    int decisionHoras = leerEnteroSeguro();

                    if (decisionHoras == 1) {
                        System.out.print("Horas trabajadas este mes: ");
                        int horas = leerEnteroSeguro();

                        empresa.agregarEmpleadoPorHoras(id, dni, nombre, apellidos, email, dept, password, precioHora, horas);
                    } else {
                        empresa.agregarEmpleadoPorHoras(id, dni, nombre, apellidos, email, dept, password, precioHora);
                    }

                    System.out.println("Empleado Por Horas añadido.");
                    break;

                case 3: // COMISIONISTA
                    System.out.print("Salario Mínimo Garantizado (€): ");
                    double minimoGarantizado = leerDoubleSeguro();
                    System.out.print("Porcentaje de Comisión (ej: 0,10 para un 10%): ");
                    double porcentaje = leerDoubleSeguro();

                    System.out.println("¿Desea introducir ventas iniciales acumuladas?");
                    System.out.println("1. Introducir ventas manualmente");
                    System.out.println("2. Usar valor por defecto (5000€ en ventas)");
                    int decisionVentas = leerEnteroSeguro();

                    if (decisionVentas == 1) {
                        System.out.print("Monto de ventas acumuladas (€): ");
                        double ventas = leerDoubleSeguro();
                        empresa.agregarEmpleadoComisionista(id, dni, nombre, apellidos, email, dept, password, minimoGarantizado, porcentaje, ventas);
                    } else {
                        empresa.agregarEmpleadoComisionista(id, dni, nombre, apellidos, email, dept, password, minimoGarantizado, porcentaje);
                    }

                    System.out.println("Empleado Comisionista añadido.");
                    break;

                default:
                    System.out.println("Opción de contrato inválida. No se creó ningún registro.");
                    break;
            }

        } catch (IllegalStateException exception) {
            System.out.println("Fallo en los valores de los campos del empleado. No se creó ningún registro.");
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
        System.out.println("\n--  CAMBIAR CONTRASEÑA  --");
        System.out.print("Escriba su contraseña actual: ");
        actual = teclado.nextLine();
        System.out.print("Escriba su nueva contraseña: ");
        nueva = teclado.nextLine();

        if (actual.equals(empleado.getPassword())) {
            empleado.setPassword(nueva);
            System.out.println("\n    - CONTRASEÑA CAMBIADA -");
        } else if (actual.equals(nueva)) {
            System.out.println("\nLa nueva contraseña es la misma que la actual. Fallo al cambiar contraseña.");
        } else {
            System.out.println("\nLa contraseña actual es errónea. Fallo al cambiar contraseña.");
        }
    }


    // ==========================================
    //          MÉTODOS AUXILIARES
    // ==========================================
    private int leerEnteroSeguro() {
        while (!teclado.hasNextInt()) {
            System.out.println("Error: Debe introducir un número entero válido.");
            System.out.print("Inténtelo de nuevo: ");
            teclado.next();
        }
        int numero = teclado.nextInt();
        teclado.nextLine(); // Limpia el buffer
        return numero;
    }

    private double leerDoubleSeguro() {
        while (!teclado.hasNextDouble()) {
            System.out.println("Error: Debe introducir un valor numérico decimal válido.");
            System.out.print("Inténtelo de nuevo: ");
            teclado.next();
        }
        double numero = teclado.nextDouble();
        teclado.nextLine(); // Limpia el buffer
        return numero;
    }

    private LocalDate leerFechaSegura() {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            String entrada = teclado.nextLine();
            try {
                return LocalDate.parse(entrada, formateador);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto.");
                System.out.print("Inténtelo de nuevo (DD/MM/AAAA): ");
            }
        }
    }

}
