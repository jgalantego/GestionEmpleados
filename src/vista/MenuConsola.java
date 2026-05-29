package vista;

import modelo.Empleado;
import modelo.EmpleadoAsalariado;
import modelo.EmpleadoComisionista;
import modelo.EmpleadoPorHoras;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MenuConsola {
    public static final Scanner teclado = new Scanner(System.in);

    // =======================================================
    //          MENÚ PRINCIPAL DE LA APLICACIÓN
    // =======================================================

    public static int menuPrincipal () {
        limpiarPantalla();

        System.out.println("=========================================");
        System.out.println("     SISTEMA DE GESTIÓN DE EMPLEADOS");
        System.out.println("=========================================");
        System.out.println("\n--- SELECCIÓN DE PERFIL DE ACCESO ---\n");
        System.out.println("1. Entrar como Administrador");
        System.out.println("2. Entrar como Empleado");
        System.out.println("3. Salir");

        System.out.print("\nSeleccione una opción: ");

        return leerEnteroSeguro();
    }

    public static void mensajeSalidaAplicacion() {
        System.out.println("\nSaliendo de la aplicación...");
    }

    public static void mensajeOpcionNoValidaMenu(int primeraOpcion, int ultimaOpcion) {
        System.out.println("\nOpción no válida. Introduzca un número entre " + primeraOpcion + " y " + ultimaOpcion + ".");
    }

    // ==========================================
    //          MENÚ DEL ADMINISTRADOR
    // ==========================================

    public static int menuAdministrador() {
        limpiarPantalla();

        System.out.println("----- MENÚ DE ADMINISTRACIÓN -----\n");
        System.out.println("1. Mostrar lista de empleados");
        System.out.println("2. Buscar empleados");
        System.out.println("3. Calcular gastos empresa");
        System.out.println("4. Añadir nuevo empleado");
        System.out.println("5. Modificar empleado");
        System.out.println("6. Eliminar empleado");
        System.out.println("7. Lista de departamentos");
        System.out.println("8. Volver");

        System.out.print("\nSeleccione una opción: ");

        return leerEnteroSeguro();
    }

    // =======================================================
    //                MENÚ MOSTRAR EMPLEADOS
    // =======================================================

    public static int menuMostrarEmpleados() {
        limpiarPantalla();

        System.out.println(" -------------------------");
        System.out.println(" --- MOSTRAR EMPLEADOS ---");
        System.out.println(" -------------------------\n");
        System.out.println("1. Mostrar todos los empleados");
        System.out.println("2. Mostrar todos los empleados ordenados por antigüedad");
        System.out.println("3. Mostrar todos los empleados ordenados por desempeño");
        System.out.println("4. Volver");

        System.out.print("\nSeleccione una opcion: ");

        return leerEnteroSeguro();
    }

    public static void mostrarListaEmpleados (String titulo, List<Empleado> empleados, HashMap<String, String> departamentos) {
        if (empleados.isEmpty()) {
            System.out.println("\nNo hay empleados en el sistema.");
        } else {
            limpiarPantalla();

            System.out.println("========================================================================================================================================================================");
            System.out.print("                                       ");
            System.out.println(titulo);
            System.out.println("========================================================================================================================================================================");

            for (Empleado empleado : empleados) {
                String nombreDepto = departamentos.getOrDefault(empleado.getDepartamento(), "Desconocido");
                System.out.println(empleado.toString() + " | (" + nombreDepto + ") | Fecha Alta: " + empleado.getFechaAlta()  + " | Desempeño: " + empleado.getDesempenio() + " | Salario Bruto: " + empleado.calcularSalarioBruto() + "€" );
            }
        }
        System.out.println("========================================================================================================================================================================");
        System.out.print("\nPresione INTRO para volver al menú anterior...");
        teclado.nextLine();
    }

    // =======================================================
    //          MENÚ BÚSQUEDA DE EMPLEADOS
    // =======================================================

    public static int menuBuscarEmpleados() {
        limpiarPantalla();

        System.out.println("-----------------------------");
        System.out.println(" --- BÚSQUEDA DE EMPLEADOS ---");
        System.out.println(" -----------------------------\n");
        System.out.println("1. Buscar empleado por ID");
        System.out.println("2. Buscar empleado por DNI");
        System.out.println("3. Buscar empleado por Nombre");
        System.out.println("4. Buscar empleado por Apellidos");
        System.out.println("5. Buscar empleado por Email");
        System.out.println("6. Buscar empleado por Departamento");
        System.out.println("7. Volver");

        System.out.print("\nSeleccione una opcion: ");

        return leerEnteroSeguro();
    }

    public static String buscarEmpleadoPor (String buscarPor) {
        limpiarPantalla();

        System.out.print("\nIntroduzca el " + buscarPor + " a buscar: ");
        return teclado.nextLine();
    }

    public static void mostrarEmpleadoEncontradoPor (Empleado empleado, String buscadoPor) {
        if (empleado == null) {
            System.out.println("\nNo existe ningún empleado con ese " + buscadoPor + ".");
        } else {
            System.out.println("\nEmpleado encontrado:\n" + empleado);
        }
        System.out.print("\nPresione INTRO para volver al menú anterior...");
        teclado.nextLine();
    }

    public static void mostrarEmpleadosEncontradosPor (List<Empleado> empleados, String buscadoPor) {
        if (empleados.isEmpty()) {
            System.out.println("\nNo existe ningún empleado con ese " + buscadoPor + ".");
        } else {
            System.out.println("\nEmpleados encontrados:\n");
            for (Empleado empleado : empleados) {
                System.out.println(empleado);
            }
        }
        System.out.print("\nPresione INTRO para volver al menú anterior...");
        teclado.nextLine();
    }

    // =======================================================
    //                MENÚ CALCULAR GASTOS
    // =======================================================

    public static int menuCalcularGastos () {
        limpiarPantalla();

        System.out.println("-------------------------");
        System.out.println(" --- CALCULAR GASTOS ---");
        System.out.println(" -------------------------");
        System.out.println("1. Calcular gastos totales de empresa (de todos los empleados)");
        System.out.println("2. Calcular gastos de empresa de un empleado (por ID)");
        System.out.println("3. Calcular gastos de empresa de un departamento");
        System.out.println("4. Volver");

        System.out.print("\nSeleccione una opcion: ");

        return leerEnteroSeguro();
    }

    public static void mostrarGastoTotalEmpresa(double gastoTotalEmpresa) {
        limpiarPantalla();

        System.out.println("Gasto total de la empresa en empleados: " + gastoTotalEmpresa + "€");

        System.out.print("\nPresione INTRO para volver al menú anterior...");
        teclado.nextLine();
    }

    public static void mostrarGastoEmpleado (Empleado empleado) {
        if (empleado == null) {
            System.out.println("No existe ningún empleado con ese ID.");
        } else {
            System.out.println("\nEmpleado encontrado:\n" + empleado);
            System.out.println("\nGasto total de empresa para ese empleado: " + empleado.calcularCosteTotalEmpresa());
        }
    }

    public static String preguntarDepartamento() {
        limpiarPantalla();

        System.out.print("Introduzca el departamento que consultar: ");
        return teclado.nextLine().toUpperCase();
    }

    public static void mostrarNoExisteDepartamento() {
        System.out.println("\nNo existe el departamento buscado.");

        System.out.print("\nPresione INTRO para volver al menú anterior...");
        teclado.nextLine();
    }

    public static void mostrarGastoDepartamento(String departamento, double gastoDepartamento) {
        System.out.println("\nLos gastos del departamento (" + departamento + ") son: " + gastoDepartamento );

        System.out.print("\nPresione INTRO para volver al menú anterior...");
        teclado.nextLine();
    }

    // =======================================================
    //                MODIFICAR EMPLEADO
    // =======================================================

    public static void menuModificarEmpleado () {
        limpiarPantalla();

        System.out.println("---------------------------");
        System.out.println(" --- MODIFICAR EMPLEADO ---");
        System.out.println(" ---------------------------\n");
    }

    public static String pedirIdEmpleadoAModificar () {
        System.out.print("Introduzca el Id del empleado que desea modificar: ");
        return teclado.nextLine();
    }

    public static void mostrarTipoEmpleadoEncontrado (Empleado empleado) {
        System.out.print("Empleado encontrado: " + empleado);
        if (empleado instanceof EmpleadoAsalariado asalariado) {
            System.out.println(" ( Empleado ASALARIADO )");
        } else if (empleado instanceof EmpleadoPorHoras porHoras) {
            System.out.println(" ( Empleado POR HORAS )");
        } else {
            System.out.println(" ( Empleado COMISIONISTA )");
        }
    }

    public static int mostrarCamposEmpleado (Empleado empleado) {
        System.out.println("-----------------------------------------");
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
            System.out.println("9. Salario Base Mensual: " + asalariado.getSalarioBaseMensual());
            System.out.println("10. Complemento Puesto: " + asalariado.getComplementoPuesto());
            System.out.println("-----------------------------------------");

            System.out.print("\nSeleccione un campo a modificar: ");
            return leerEnteroSeguro();

        } else if (empleado instanceof EmpleadoPorHoras porHoras) { // Es por horas
            System.out.println("9. Horas Trabajadas: " + porHoras.getHorasTrabajadas());
            System.out.println("10. Precio Hora: " + porHoras.getPrecioHora());
            System.out.println("-----------------------------------------");

            System.out.print("\nSeleccione un campo a modificar: ");
            return leerEnteroSeguro();

        } else { // Es comisionista
            System.out.println("9. Salario Mínimo Garantizado: " + ((EmpleadoComisionista) empleado).getSalarioMinimoGarantizado());
            System.out.println("10. Ventas Realizadas: " + ((EmpleadoComisionista) empleado).getVentasRealizadas());
            System.out.println("11. Porcentaje Comisión: " + ((EmpleadoComisionista) empleado).getPorcentajeComision());
            System.out.println("-----------------------------------------");

            System.out.print("\nSeleccione un campo a modificar: ");
            return leerEnteroSeguro();
        }
    }
    public static int pedirNuevoCampoInt(String campo) {
        System.out.print("\nNuevo " + campo + ":" );
        return leerEnteroSeguro();
    }

    public static double pedirNuevoCampoDouble(String campo) {
        System.out.print("\nNuevo " + campo + ":" );
        return leerDoubleSeguro();
    }

    public static String pedirNuevoCampoString (String campo) {
        System.out.print("\nNuevo " + campo + ":" );
        return teclado.nextLine();
    }

    public static LocalDate pedirNuevoCampoFecha() {
        System.out.print("\nNueva Fecha Alta (DD/MM/AAAA): ");
        return leerFechaSegura();
    }

    public static void mostrarCampoModificado (String campo, String modificacion) {
        System.out.println("\n  - " + campo + " modificado a [ " + modificacion + " ] -");
    }

    public static void mensajeDesempenioNoValido () {
        System.out.println("\nEl desempeño no es válido, ha de estar entre 0 y 10.");
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

    public static void limpiarPantalla() {
        try {
            String sistemaOperativo = System.getProperty("os.name");

            if (sistemaOperativo.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

}
