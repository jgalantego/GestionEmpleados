package vista;

import modelo.Empleado;

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

    //Mensaje de salida de la aplicación =====================================================

    public static void mensajeSalidaAplicacion() {
        System.out.println("\nSaliendo de la aplicación...");
    }

    // Mensaje opcion no valida para cualquier menú =====================================================

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

    // Menú MOSTRAR EMPLEADOS
    public static int menuMostrarEmpleados() {
        limpiarPantalla();

        System.out.println(" -------------------------");
        System.out.println(" --- MOSTRAR EMPLEADOS ---");
        System.out.println(" -------------------------\n");
        System.out.println("1. Mostrar todos los empleados");
        System.out.println("2. Mostrar todos los empleados ordenados por antiguedad");
        System.out.println("3. Mostrar todos los empleados ordenados por desempeño");
        System.out.println("4. Volver");
        System.out.print("\nSeleccione una opcion: ");

        return leerEnteroSeguro();
    }

    // Mostrar lista de empleados
    public static void mostrarListaEmpleados (String titulo, List<Empleado> empleados, HashMap<String, String> departamentos) {
        if (empleados.isEmpty()) {
            System.out.println("\nNo hay empleados en el sistema.");
        } else {
            limpiarPantalla();

            System.out.println("================================================================================================================");
            System.out.print("                                       ");
            System.out.println(titulo);
            System.out.println("================================================================================================================");

            for (Empleado empleado : empleados) {
                String nombreDepto = departamentos.getOrDefault(empleado.getDepartamento(), "Desconocido");
                System.out.println(empleado.toString() + " | (" + nombreDepto + ") | Salario Bruto: " + empleado.calcularSalarioBruto() + "€");
                System.out.println("================================================================================================================");
            }
        }
        System.out.print("\nPresione INTRO para volver al menú anterior...");
        teclado.nextLine();
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
