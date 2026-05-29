import modelo.*;
import vista.MenuConsola;
import controlador.*;

import javax.swing.plaf.metal.MetalBorders;
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
                                mostrarGastosDepartamento();
                                break;
                            case 4:
                                System.out.println("\nVolviendo al menú de administración...\n");
                                break;
                            default:
                                System.out.println("Opción no válida. Introduzca un número 1-4.");
                        }
                    } while (opcionGastos != 4);
                    break;
                case 4:
                    // CASO DE USO: Añadir nuevo empleado (Formulario simplificado de consola)
                    formularioAltaEmpleado();
                    break;
                case 5:
                    System.out.println("\n --- MODIFICAR EMPLEADO ---");
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


    private static void mostrarGastoEmpleado() {
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

    private static void mostrarGastosDepartamento() {
        System.out.print("Introduzca el departamento que consultar: ");
        String departamento = teclado.nextLine();

        List<Empleado> empleados = new ArrayList<>();
        empleados.addAll(empresa.buscarPorDepartamento(departamento));

        if (empleados.isEmpty()) {
            System.out.println("\nDepartamento no existente.");
        } else {
            double gastoDepartamento = 0;
            for (Empleado empleado : empleados) {
                gastoDepartamento += empleado.calcularCosteTotalEmpresa();
            }
            System.out.println("\nGasto total de la empresa en el departamento [" + departamento + "]: " + gastoDepartamento);
        }
    }

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

    private static void modificarEmpleado() {
        System.out.print("\nIntroduzca el ID del empleado a modificar: ");
        String id = teclado.nextLine();
        Empleado empleado = empresa.buscarPorId(id);
        if (empleado != null) {
            System.out.print("\nEmpleado encontrado: [" + empleado.getId() + "] -> " + empleado.getNombre() + " " + empleado.getApellidos());
            if (empleado instanceof EmpleadoAsalariado asalariado) {
                System.out.println(" ( Empleado ASALARIADO )");
            } else if (empleado instanceof EmpleadoPorHoras porHoras) {
                System.out.println(" ( Empleado POR HORAS )");
            } else {
                System.out.println(" ( Empleado COMISIONISTA )");
            }

            int opcion = -1;
            do {
                System.out.println("\nCampos a modificar:\n");
                System.out.println("0. Volver");
                System.out.println("1. ID: " + empleado.getId());
                System.out.println("2. DNI: " + empleado.getDni());
                System.out.println("3. Nombre: " + empleado.getNombre());
                System.out.println("4. Apellidos: " + empleado.getApellidos());
                System.out.println("5. Email: " + empleado.getEmail());
                System.out.println("6. Fecha Alta: " + empleado.getFechaAlta());
                System.out.println("7. Departamento: " + empleado.getDepartamento());
                System.out.println("8. Desempeño del empleado: " + empleado.obtenerEstadoDesempenio());

                if (empleado instanceof EmpleadoAsalariado asalariado) { // Es asalariado
                    System.out.println("9. Salario Base Mensual: " + ((EmpleadoAsalariado) empleado).getSalarioBaseMensual());
                    System.out.println("10. Complemento Puesto: " + ((EmpleadoAsalariado) empleado).getComplementoPuesto());
                } else if (empleado instanceof EmpleadoPorHoras porHoras) { // Es por horas
                    System.out.println("9. Horas Trabajadas: " + ((EmpleadoPorHoras) empleado).getHorasTrabajadas());
                    System.out.println("10. Precio Hora: " + ((EmpleadoPorHoras) empleado).getPrecioHora());
                } else { // Es comisionista
                    System.out.println("9. Salario Mínimo Garantizado: " + ((EmpleadoComisionista) empleado).getSalarioMinimoGarantizado());
                    System.out.println("10. Ventas Realizadas: " + ((EmpleadoComisionista) empleado).getVentasRealizadas());
                    System.out.println("11. Porcentaje Comisión: " + ((EmpleadoComisionista) empleado).getPorcentajeComision());
                }

                System.out.print("\nIntroduzca el número del campo a modificar: ");
                opcion = leerEnteroSeguro();

                switch (opcion) {
                    case 0:
                        System.out.println("\nVolviendo al menú de administración...");
                        break;
                    case 1:
                        System.out.print("Nuevo ID:" );
                        String nuevoId = teclado.nextLine();
                        if (empresa.modificarIdEmpleado(empleado.getId(), nuevoId))
                            System.out.println("\n  - ID modificado a [" + nuevoId + "] -");
                        break;
                    case 2:
                        System.out.print("Nuevo DNI: ");
                        String nuevoDNI = teclado.nextLine();
                        empleado.setDni(nuevoDNI);
                        System.out.println("\n  - DNI modificado a [" + nuevoDNI + "] -");
                        break;
                    case 3:
                        System.out.print("Nuevo Nombre: ");
                        String nuevoNombre = teclado.nextLine();
                        empleado.setNombre(nuevoNombre);
                        System.out.println("\n  - Nombre modificado a [" + nuevoNombre + "] -");
                        break;
                    case 4:
                        System.out.print("Nuevos Apellidos: ");
                        String nuevosApellidos = teclado.nextLine();
                        empleado.setApellidos(nuevosApellidos);
                        System.out.println("\n  - Apellidos modificados a [" + nuevosApellidos + "] -");
                        break;
                    case 5:
                        System.out.print("Nuevo Email: ");
                        String nuevoEmail = teclado.nextLine();
                        empleado.setEmail(nuevoEmail);
                        System.out.println("\n  - Email modificado a [" + nuevoEmail + "] -");
                        break;
                    case 6:
                        System.out.print("Nueva Fecha Alta (DD/MM/AAAA): ");
                        LocalDate nuevaFechaAlta = leerFechaSegura();
                        empleado.setFechaAlta(nuevaFechaAlta);
                        System.out.println("\n  - Fecha Alta modificada a [" + nuevaFechaAlta + "] -");
                        break;
                    case 7:
                        System.out.print("Nuevo Departamento: ");
                        String nuevoDepartamento = teclado.nextLine();
                        empleado.setDepartamento(nuevoDepartamento);
                        System.out.println("\n  - Departamento modificado a [" + nuevoDepartamento + "] -");
                        break;
                    case 8:
                        boolean bandera = false;
                        do {
                            System.out.print("Nuevo desempeño: ");
                            double nuevoDesempenio = leerDoubleSeguro();
                            if (nuevoDesempenio >= 0 && nuevoDesempenio <= 10) {
                                empleado.registrarEvaluacion(nuevoDesempenio);
                                System.out.println("\n  - Desempeño modificado a [" + nuevoDesempenio + "] -");
                                bandera = true;
                            } else {
                                System.out.println("\nEl desempeño no es válido, ha de estar entre 0 y 10.");
                            }
                        } while (!bandera);
                        break;
                    case 9:
                        if (empleado instanceof EmpleadoAsalariado asalariado) { // Es asalariado
                            System.out.print("Nuevo Salario Base Mensual: ");
                            double nuevoSalarioBase = leerDoubleSeguro();
                            asalariado.setSalarioBaseMensual(nuevoSalarioBase);
                            System.out.println("\n  - Salario Base Mensual modificado a [" + nuevoSalarioBase + "] -");
                        } else if (empleado instanceof EmpleadoPorHoras porHoras) { // Es por horas
                            System.out.print("Nuevas Horas Trabajadas: ");
                            int nuevasHoras = leerEnteroSeguro();
                            porHoras.setHorasTrabajadas(nuevasHoras);
                            System.out.println("\n  - Horas Trabajadas modificadas a [" + nuevasHoras + "] -");
                        } else { // Es comisionista
                            System.out.print("Nuevo Salario Mínimo Garantizado: ");
                            double nuevoSalarioMinimo = leerDoubleSeguro();
                            ((EmpleadoComisionista) empleado).setSalarioMinimoGarantizado(nuevoSalarioMinimo);
                            System.out.println("\n  - Salario Mínimo Garantizado modificado a [" + nuevoSalarioMinimo + "] -");
                        }
                        break;
                    case 10:
                        if (empleado instanceof EmpleadoAsalariado asalariado) { // Es asalariado
                            System.out.print("Nuevo Complemento Puesto: ");
                            double nuevoComplemento = leerDoubleSeguro();
                            asalariado.setComplementoPuesto(nuevoComplemento);
                            System.out.println("\n  - Complemento Puesto modificado a [" + nuevoComplemento + "] -");
                        } else if (empleado instanceof EmpleadoPorHoras porHoras) { // Es por horas
                            System.out.print("Nuevo Precio por Hora: ");
                            int nuevoPrecioHora = leerEnteroSeguro();
                            porHoras.setPrecioHora(nuevoPrecioHora);
                            System.out.println("\n  - Precio por Hora modificado a [" + nuevoPrecioHora + "] -");
                        } else { // Es comisionista
                            System.out.print("Nuevas Ventas Realizadas: ");
                            double nuevasVentas = leerDoubleSeguro();
                            ((EmpleadoComisionista) empleado).setVentasRealizadas(nuevasVentas);
                            System.out.println("\n  - Ventas Realizadas modificadas a [" + nuevasVentas + "] -");
                        }
                        break;
                    case 11:
                        if (empleado instanceof EmpleadoComisionista comisionista) {
                            System.out.print("Nuevo Porcentaje de Comisión: ");
                            double nuevoPorcentaje = leerDoubleSeguro();
                            comisionista.setPorcentajeComision(nuevoPorcentaje);
                            System.out.println("\n  - Porcentaje de Comisión modificado a [" + nuevoPorcentaje + "] -");
                        } else {
                            System.out.println("\nOpción no válida. Introduzca un número 0-10.");
                        }
                        break;
                    default:
                        System.out.println("\nOpción no válida. Introduzca un número 0-11.");
                        break;
                }
            } while (opcion != 0);
        } else {
            System.out.println("\nNo existe ningún empleado con ese ID.");
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