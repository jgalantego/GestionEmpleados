import modelo.*;
import vista.MenuConsola;
import controlador.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static GestionEmpresa empresa = new GestionEmpresa();
    //Eliminar el Scanner
    private final static Scanner teclado = new Scanner(System.in);
    private final static Autenticador autenticador = new Autenticador(empresa, teclado);

    public static void main(String[] args) {
        int perfil = 0;

        do {
            perfil = MenuConsola.menuPrincipal();

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
                    MenuConsola.mensajeSalidaAplicacion();
                    break;

                default:
                    MenuConsola.mensajeOpcionNoValidaMenu(1, 3);
            }

        } while (perfil != 3);
    }

    // ==========================================
    //          MENÚ DEL ADMINISTRADOR
    // ==========================================
    private static void menuAdministrador() {
        int opcion = 0;

        List<Empleado> empleados = new ArrayList<>();
        HashMap<String, String> departamentos = empresa.obtenerDepartamentos();

        do {
            opcion = MenuConsola.menuAdministrador();

            switch (opcion) {
                case 1:
                    int opcionMostrar;
                    do {
                        opcionMostrar = MenuConsola.menuMostrarEmpleados();

                        switch (opcionMostrar) {
                            case 1:
                                //CASO DE USO: Mostrar todos los empleados
                                empleados = empresa.obtenerEmpleados();
                                MenuConsola.mostrarListaEmpleados("LISTA DE TODOS LOS EMPLEADOS", empleados, departamentos);
                                break;
                            case 2:
                                empleados = empresa.obtenerEmpleadosOrdenadosPorAntiguedad();
                                MenuConsola.mostrarListaEmpleados("LISTA DE EMPLEADOS ORDENADOS POR ANTIGUEDAD", empleados, departamentos);
                                break;
                            case 3:
                                empleados = empresa.obtenerEmpleadosOrdenadosPorDesempenio();
                                MenuConsola.mostrarListaEmpleados("LISTA DE EMPLEADOS ORDENADOS POR DESEMPEÑO", empleados, departamentos);
                                break;
                            case 4:
                                // Volver al menu administración
                                break;
                            default:
                                MenuConsola.mensajeOpcionNoValidaMenu(1, 4);
                                break;
                        }
                    } while (opcionMostrar != 4);
                    break;
                case 2:
                    int opcionBuscar;
                    do {
                        opcionBuscar = MenuConsola.menuBuscarEmpleados();

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
                                // Volver al menú de administración
                                break;
                            default:
                                MenuConsola.mensajeOpcionNoValidaMenu(1, 7);
                        }
                    } while (opcionBuscar != 7);
                    break;
                case 3:
                    int opcionGastos;
                    do {
                        opcionGastos = MenuConsola.menuCalcularGastos();

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
                                MenuConsola.mensajeOpcionNoValidaMenu(1, 4);
                        }
                    } while (opcionGastos != 4);
                    break;
                case 4:
                    // CASO DE USO: Añadir nuevo empleado (Formulario simplificado de consola)
                    formularioAltaEmpleado();
                    break;
                case 5:
                    MenuConsola.menuModificarEmpleado();
                    modificarEmpleado();
                    break;
                case 6:
                    // CASO DE USO: Eliminar empleado
                    System.out.print("Introduzca el ID del empleado a eliminar: ");
                    String idEliminar = teclado.nextLine();
                    if (empresa.eliminarEmpleado(idEliminar)) {
                        System.out.println("\nEmpleado con ID = " + idEliminar + " eliminado.");
                    } else {
                        System.out.println("\nNo existe ningún empleado con el ID = " + idEliminar);
                    }
                    break;
                case 7:
                    // CASO DE USO: Lista del HashMap
                    empresa.mostrarDepartamentos();
                    break;
                case 8:
                    System.out.println("\nCerrando sesión de administrador...");
                    break;
                default:
                    System.out.println("\nOpción no válida. Introduzca un número 1-8.");
            }
        } while (opcion != 8);
    }

    // ==========================================
    //          MENÚ DEL EMPLEADO
    // ==========================================

    private static void menuEmpleado(Empleado empleado) {
        System.out.println("\n--- MENÚ DE ACCESO EMPLEADO ---");

        int opcion = 0;
        do {
            System.out.println("\n1. Consultar Nómina");
            System.out.println("2. Registrar Fichaje Diario (Simulado)");
            System.out.println("3. Cambiar contraseña");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = leerEnteroSeguro();

            switch (opcion) {
                case 1:
                    // CASO DE USO: Consultar nómina propia
                    System.out.println("\n--- NÓMINA EMPLEADO ---");
                    System.out.println("Empleado: " + empleado.getNombre() + " " + empleado.getApellidos());
                    System.out.println("Salario Bruto: " + empleado.calcularSalarioBruto() + "€");
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
                    System.out.println("Cerrando sesión de empleado...");
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

    private static void buscarPorId () {
        String dniBuscado = MenuConsola.buscarEmpleadoPor("ID");
        Empleado empleado = empresa.buscarPorId(dniBuscado);
        MenuConsola.mostrarEmpleadoEncontradoPor(empleado, "ID");
    }

    private static void buscarPorDNI () {
        String dniBuscado = MenuConsola.buscarEmpleadoPor("DNI");
        Empleado empleado = empresa.buscarPorDni(dniBuscado);
        MenuConsola.mostrarEmpleadoEncontradoPor(empleado, "DNI");
    }

    private static void buscarPorNombre () {
        String nombre = MenuConsola.buscarEmpleadoPor("NOMBRE");
        List<Empleado> empleados = empresa.buscarPorNombre(nombre);
        MenuConsola.mostrarEmpleadosEncontradosPor(empleados, "NOMBRE");
    }

    private static void buscarPorApellido () {
        String apellido = MenuConsola.buscarEmpleadoPor("APELLIDO");
        List<Empleado> empleados = empresa.buscarPorApellido(apellido);
        MenuConsola.mostrarEmpleadosEncontradosPor(empleados, "APELLIDO");
    }

    private static void buscarPorEmail () {
        String email = MenuConsola.buscarEmpleadoPor("EMAIL");
        List<Empleado> empleados = empresa.buscarPorEmail(email);
        MenuConsola.mostrarEmpleadosEncontradosPor(empleados, "EMAIL");
    }

    private static void buscarPorDepartamento () {
        String departamento = MenuConsola.buscarEmpleadoPor("DEPARTAMENTO (abreviatura)");
        List<Empleado> empleados = empresa.buscarPorDepartamento(departamento);
        MenuConsola.mostrarEmpleadosEncontradosPor(empleados, "DEPARTAMENTO");
    }

    // Calcular gastos -----------------------------------------------------------------------

    private static void calcularGastoEmpleado() {
        String id = MenuConsola.buscarEmpleadoPor("ID");
        Empleado empleado = empresa.buscarPorId(id);
        MenuConsola.mostrarGastoEmpleado(empleado);
    }

    private static void calcularGastoTotalEmpleados() {
        double gastoTotalEmpresa = 0.0;
        List<Empleado> empleados = empresa.obtenerEmpleados();
        for (Empleado empleado : empleados) {
            gastoTotalEmpresa += empleado.calcularCosteTotalEmpresa();
        }
        MenuConsola.mostrarGastoTotalEmpresa(gastoTotalEmpresa);
    }

    private static void calcularGastosDepartamento(HashMap<String, String> departamentos) {
        String departamento = MenuConsola.preguntarDepartamento();

        List<Empleado> empleados = empresa.buscarPorDepartamento(departamento);
        if (empleados.isEmpty()) {
            MenuConsola.mostrarNoExisteDepartamento();
        } else {
            double gastoDepartamento = 0;
            for (Empleado empleado : empleados) {
                gastoDepartamento += empleado.calcularCosteTotalEmpresa();
            }
            MenuConsola.mostrarGastoDepartamento(departamentos.get(departamento), gastoDepartamento);
        }
    }

    // Modificar empleado ------------------------------------------------------------------------

    private static void modificarEmpleado() {
        String id = MenuConsola.pedirIdEmpleadoAModificar();
        Empleado empleado = empresa.buscarPorId(id);
        if (empleado != null) {
            MenuConsola.mostrarTipoEmpleadoEncontrado(empleado);

            int opcion = -1;
            do {
                opcion = MenuConsola.mostrarCamposEmpleado(empleado);

                switch (opcion) {
                    case 0:
                        break;
                    case 1:
                        String nuevoId = MenuConsola.pedirNuevoCampoString("ID");
                        if (empresa.modificarIdEmpleado(empleado.getId(), nuevoId))
                            MenuConsola.mostrarCampoModificado("ID", nuevoId);
                        break;
                    case 2:
                        String nuevoDNI = MenuConsola.pedirNuevoCampoString("DNI");
                        empleado.setDni(nuevoDNI);
                        MenuConsola.mostrarCampoModificado("DNI", nuevoDNI);
                        break;
                    case 3:
                        String nuevoNombre = MenuConsola.pedirNuevoCampoString("NOMBRE");
                        empleado.setNombre(nuevoNombre);
                        MenuConsola.mostrarCampoModificado("NOMBRE", nuevoNombre);
                        break;
                    case 4:
                        String nuevosApellidos = MenuConsola.pedirNuevoCampoString("APELLIDOS");
                        empleado.setApellidos(nuevosApellidos);
                        MenuConsola.mostrarCampoModificado("APELLIDOS", nuevosApellidos);
                        break;
                    case 5:
                        String nuevoEmail = MenuConsola.pedirNuevoCampoString("EMAIL");
                        empleado.setEmail(nuevoEmail);
                        MenuConsola.mostrarCampoModificado("EMAIL", nuevoEmail);
                        break;
                    case 6:
                        LocalDate nuevaFechaAlta = MenuConsola.pedirNuevoCampoFecha();
                        empleado.setFechaAlta(nuevaFechaAlta);
                        MenuConsola.mostrarCampoModificado("FECHA ALTA", nuevaFechaAlta.toString());
                        break;
                    case 7:
                        String nuevoDepartamento = MenuConsola.pedirNuevoCampoString("DEPARTAMENTO");
                        empleado.setDepartamento(nuevoDepartamento);
                        MenuConsola.mostrarCampoModificado("DEPARTAMENTO", nuevoDepartamento);
                        break;
                    case 8:
                        boolean bandera = false;
                        do {
                            MenuConsola.pedirNuevoCampoString("DESEMPEÑO");
                            double nuevoDesempenio = leerDoubleSeguro();
                            if (nuevoDesempenio >= 0 && nuevoDesempenio <= 10) {
                                empleado.registrarEvaluacion(nuevoDesempenio);
                                MenuConsola.mostrarCampoModificado("DESEMPEÑO", String.valueOf(nuevoDesempenio));
                                bandera = true;
                            } else {
                                MenuConsola.mensajeDesempenioNoValido();
                            }
                        } while (!bandera);
                        break;
                    case 9:
                        if (empleado instanceof EmpleadoAsalariado asalariado) { // Es asalariado
                            double nuevoSalarioBase = MenuConsola.pedirNuevoCampoDouble("SALARIO BASE MENSUAL");
                            asalariado.setSalarioBaseMensual(nuevoSalarioBase);
                            MenuConsola.mostrarCampoModificado("SALARIO BASE MENSUAL", String.valueOf(nuevoSalarioBase));

                        } else if (empleado instanceof EmpleadoPorHoras porHoras) { // Es por horas
                            int nuevasHoras = MenuConsola.pedirNuevoCampoInt("HORAS TRABAJADAS");
                            porHoras.setHorasTrabajadas(nuevasHoras);
                            MenuConsola.mostrarCampoModificado("HORAS TRABAJADAS", String.valueOf(nuevasHoras));

                        } else { // Es comisionista
                            double nuevoSalarioMinimo = MenuConsola.pedirNuevoCampoDouble("SALARIO MÍNIMO GARANTIZADO");
                            ((EmpleadoComisionista) empleado).setSalarioMinimoGarantizado(nuevoSalarioMinimo);
                            MenuConsola.mostrarCampoModificado("SALARIO MÍNIMO GARANTIZADO", String.valueOf(nuevoSalarioMinimo));

                        }
                        break;
                    case 10:
                        if (empleado instanceof EmpleadoAsalariado asalariado) { // Es asalariado
                            double nuevoComplemento = MenuConsola.pedirNuevoCampoDouble("COMPLEMENTO PUESTO");
                            asalariado.setComplementoPuesto(nuevoComplemento);
                            MenuConsola.mostrarCampoModificado("COMPLEMENTO PUESTO", String.valueOf(nuevoComplemento));

                        } else if (empleado instanceof EmpleadoPorHoras porHoras) { // Es por horas
                            int nuevoPrecioHora = MenuConsola.pedirNuevoCampoInt("PRECIO POR HORA");
                            porHoras.setPrecioHora(nuevoPrecioHora);
                            MenuConsola.mostrarCampoModificado("PRECIO POR HORA", String.valueOf(nuevoPrecioHora));

                        } else { // Es comisionista
                            double nuevasVentas = MenuConsola.pedirNuevoCampoDouble("VENTAS REALIZADAS");
                            ((EmpleadoComisionista) empleado).setVentasRealizadas(nuevasVentas);
                            MenuConsola.mostrarCampoModificado("VENTAS REALIZADAS", String.valueOf(nuevasVentas));
                        }
                        break;
                    case 11:
                        if (empleado instanceof EmpleadoComisionista comisionista) {
                            double nuevoPorcentaje = MenuConsola.pedirNuevoCampoDouble("PORCENTAJE DE COMISIÓN");
                            comisionista.setPorcentajeComision(nuevoPorcentaje);
                            MenuConsola.mostrarCampoModificado("PORCENTAJE DE COMISIÓN", String.valueOf(nuevoPorcentaje));
                        } else {
                            MenuConsola.mensajeOpcionNoValidaMenu(0, 10);
                        }
                        break;
                    default:
                        MenuConsola.mensajeOpcionNoValidaMenu(0, 11);
                        break;
                }
            } while (opcion != 0);
        } else {
            MenuConsola.mostrarEmpleadoEncontradoPor(null,"ID");
        }
    }

    private static void formularioAltaEmpleado() {
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

    // ==========================================
    //      MÉTODOS DEL EMPLEADO
    // ==========================================

    private static void cambiarContrasenia (Empleado empleado) {
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
    private static int leerEnteroSeguro() {
        while (!teclado.hasNextInt()) {
            System.out.println("Error: Debe introducir un número entero válido.");
            System.out.print("Inténtelo de nuevo: ");
            teclado.next();
        }
        int numero = teclado.nextInt();
        teclado.nextLine(); // Limpia el buffer
        return numero;
    }

    private static double leerDoubleSeguro() {
        while (!teclado.hasNextDouble()) {
            System.out.println("Error: Debe introducir un valor numérico decimal válido.");
            System.out.print("Inténtelo de nuevo: ");
            teclado.next();
        }
        double numero = teclado.nextDouble();
        teclado.nextLine(); // Limpia el buffer
        return numero;
    }

    private static LocalDate leerFechaSegura() {
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