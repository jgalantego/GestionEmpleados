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

public class VistaConsola {
    public static final Scanner teclado = new Scanner(System.in);

    // =======================================================
    //          MENÚ PRINCIPAL DE LA APLICACIÓN
    // =======================================================

    public static int menuPrincipal () {
        limpiarPantalla();

        System.out.println("\n=========================================");
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
        introParaContinuar("\nPresione intro para volver a seleccionar una opción del menú anterior...");
    }

    // ==========================================
    //          MENÚ DEL ADMINISTRADOR
    // ==========================================

    public static int menuAdministrador() {
        limpiarPantalla();

        System.out.println("\n----- MENÚ DE ADMINISTRACIÓN -----\n");
        System.out.println("1. Mostrar lista de empleados");
        System.out.println("2. Buscar empleados");
        System.out.println("3. Calcular gastos empresa");
        System.out.println("4. Añadir nuevo empleado");
        System.out.println("5. Modificar empleado");
        System.out.println("6. Eliminar empleado");
        System.out.println("7. Lista de departamentos");
        System.out.println("8. Cerrar Sesión de Administrador");

        System.out.print("\nSeleccione una opción: ");

        return leerEnteroSeguro();
    }

    // =======================================================
    //                MENÚ MOSTRAR EMPLEADOS
    // =======================================================

    public static int menuMostrarEmpleados() {
        limpiarPantalla();

        System.out.println("\n -------------------------");
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

            System.out.println("\n========================================================================================================================================================================");
            System.out.print("                                                                ");
            System.out.println(titulo);
            System.out.println("========================================================================================================================================================================\n");

            for (Empleado empleado : empleados) {
                String nombreDepto = departamentos.getOrDefault(empleado.getDepartamento(), "Desconocido");
                System.out.println(empleado + " | (" + nombreDepto + ") | Fecha Alta: " + empleado.getFechaAlta()  + " | Desempeño: " + empleado.getDesempenio() + " | Salario Bruto: " + empleado.calcularSalarioBruto() + "€\n" );
            }
        }
        System.out.println("\n========================================================================================================================================================================");
        introParaContinuar("\nPresione INTRO para volver al menú anterior...");
    }

    // =======================================================
    //          MENÚ BÚSQUEDA DE EMPLEADOS
    // =======================================================

    public static int menuBuscarEmpleados() {
        limpiarPantalla();

        System.out.println("\n -----------------------------");
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
        introParaContinuar("\nPresione INTRO para volver al menú anterior...");
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
        introParaContinuar("\nPresione INTRO para volver al menú anterior...");
    }

    public static void mensajeNoExisteEmpleado (String campo) {
        System.out.println("\nNo existe ningún empleado con ese " + campo + ".");

        introParaContinuar("\nPresione INTRO para volver al menu anterior...");
    }

    // =======================================================
    //                MENÚ CALCULAR GASTOS
    // =======================================================

    public static int menuCalcularGastos () {
        limpiarPantalla();

        System.out.println("\n -------------------------");
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

        System.out.println("\nGasto total de la empresa en empleados: " + gastoTotalEmpresa + "€");

        introParaContinuar("\nPresione INTRO para volver al menú anterior...");
    }

    public static void mostrarGastoEmpleado (Empleado empleado) {
        System.out.println("\nEmpleado encontrado:\n" + empleado);
        System.out.println("\nGasto total de empresa para ese empleado: " + empleado.calcularCosteTotalEmpresa());

        introParaContinuar("\nPresione INTRO para volver al menú anterior...");
    }

    public static String preguntarDepartamento() {
        limpiarPantalla();

        System.out.print("\nIntroduzca el departamento que consultar: ");
        return teclado.nextLine().toUpperCase();
    }

    public static void mostrarNoExisteDepartamento() {
        System.out.println("\nNo existe el departamento buscado.");

        introParaContinuar("\nPresione INTRO para volver al menú anterior...");
    }

    public static void mostrarGastoDepartamento(String departamento, double gastoDepartamento) {
        System.out.println("\nLos gastos del departamento (" + departamento + ") son: " + gastoDepartamento );

        introParaContinuar("\nPresione INTRO para volver al menú anterior...");
    }

    // =======================================================
    //                MODIFICAR EMPLEADO
    // =======================================================

    public static void menuModificarEmpleado () {
        limpiarPantalla();

        System.out.println("\n ---------------------------");
        System.out.println(" --- MODIFICAR EMPLEADO ---");
        System.out.println(" ---------------------------");
    }

    public static String pedirIdEmpleadoAModificar () {
        System.out.print("\nIntroduzca el Id del empleado que desea modificar: ");
        return teclado.nextLine();
    }

    public static void mensajeIdEmpleadoRepetidoModificar() {
        System.out.println("\nNo es posible modificar el Id porque ya está repetido.");

        introParaContinuar("\nPresione INTRO para volver a la selección de campos...");
        limpiarPantalla();
    }

    public static void mostrarTipoEmpleadoEncontrado (Empleado empleado) {
        System.out.print("\nEmpleado encontrado: " + empleado);
        if (empleado instanceof EmpleadoAsalariado) {
            System.out.println(" ( Empleado ASALARIADO )");
        } else if (empleado instanceof EmpleadoPorHoras) {
            System.out.println(" ( Empleado POR HORAS )");
        } else {
            System.out.println(" ( Empleado COMISIONISTA )");
        }
    }

    public static int mostrarCamposEmpleado (Empleado empleado) {
        System.out.println("\n-----------------------------------------");
        System.out.println("Campos a modificar:\n");
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
        return introducirInt("\nNuevo " + campo);
    }

    public static double pedirNuevoCampoDouble(String campo) {
        return introducirDouble("\nNuevo " + campo);

    }

    public static String pedirNuevoCampoString (String campo) {
        return introducirString("\nNuevo " + campo);

    }

    public static LocalDate pedirNuevoCampoFecha() {
        return introducirFecha("\nNueva Fecha Alta (DD/MM/AAAA)");
    }

    public static void mostrarCampoModificado (String campo, String modificacion) {
        System.out.println("\n  - " + campo + " modificado a [ " + modificacion + " ] -");

        introParaContinuar("\nPresione INTRO para volver al selector de campos...");

        limpiarPantalla();
    }

    public static void mensajeDesempenioNoValido () {
        System.out.println("\nEl desempeño no es válido, ha de estar entre 0 y 10.");
    }

    // =======================================================
    //                  ELIMINAR EMPLEADO
    // =======================================================

    public static void menuEliminarEmpleado () {
        limpiarPantalla();

        System.out.println("\n --------------------------");
        System.out.println(" --- ELIMINAR EMPLEADO ---");
        System.out.println(" --------------------------");
    }

    public static String pedirIdEmpleadoAEliminar () {
        limpiarPantalla();

        System.out.print("\nIntroduzca el Id del empleado que desea eliminar: ");
        return teclado.nextLine();
    }

    public static void mostrarEmpleadoEliminado (Empleado empleadoEliminado) {
        System.out.println("\nEmpleado ( " + empleadoEliminado + " ) eliminado con éxito.");

        introParaContinuar("\nPresione INTRO para volver al menú anterior...");

        limpiarPantalla();
    }

    // =======================================================
    //                  MOSTRAR DEPARTAMENTOS
    // =======================================================

    public static void menuMostrarDepartamentos () {
        limpiarPantalla();

        System.out.println("\n ------------------------------");
        System.out.println(" --- MOSTRAR DEPARTAMENTOS ---");
        System.out.println(" -----------------------------\n");
    }

    public static void mostrarDatosDepartamento(String codigo, String nombre, int numEmpleados) {
        System.out.println( codigo + " ( " + nombre + " ) -> " + numEmpleados + " empleados.");
    }

    public static void finMostrarDepartamentos () {
        introParaContinuar("\nPresione INTRO para volver al menú anterior...");
    }

    public static void mostrarNoExistenDepartamentos() {
        System.out.println("\nNo existen departamentos");
    }

    // =======================================================
    //                     CERRAR SESIÓN
    // =======================================================

    public static boolean preguntaCerrarSesion (String cuenta) {
        limpiarPantalla();

        String opcion;
        do {
            System.out.print("\n¿Está seguro de querer cerrar sesión como ( " + cuenta + " )? (S/N): ");
            opcion = teclado.nextLine();

            if (opcion.equalsIgnoreCase("N")) {
                return false;
            }

            if (!opcion.equalsIgnoreCase("S")) {
                System.out.println("\nOpcion no válida, vuelva a intentarlo.");
            }

        } while (!opcion.equalsIgnoreCase("S"));
        return true;
    }

    // =======================================================
    //                     MENÚ EMPLEADO
    // =======================================================

    public static int menuEmpleado () {
        limpiarPantalla();

        System.out.println("\n--- MENÚ DE EMPLEADO ---\n");
        System.out.println("1. Consultar Nómina");
        System.out.println("2. Registrar Fichaje Diario (Simulado)");
        System.out.println("3. Cambiar contraseña");
        System.out.println("4. Cerrar sesión de empleado");

        System.out.print("\nSeleccione una opción: ");
        return leerEnteroSeguro();
    }

    // =======================================================
    //                    Consultar nómina
    // =======================================================

    public static void mostrarNominaEmpleado (Empleado empleado) {
        limpiarPantalla();

        System.out.println("\n ----------------------");
        System.out.println(" --- MOSTRAR NÓMINA ---");
        System.out.println(" ----------------------\n");

        System.out.print(empleado + " | Tipo de contrato: ");
        switch (empleado) {
            case EmpleadoAsalariado empleadoAsalariado -> System.out.println("ASALARIADO");
            case EmpleadoPorHoras empleadoPorHoras -> System.out.println("POR HORAS");
            case EmpleadoComisionista empleadoComisionista -> System.out.println("COMISIONISTA");
            case null, default -> System.out.println("No se conoce el tipo de contrato.");
        }
        assert empleado != null;
        System.out.println("\nNómina (Salario Bruto Mensual): " + empleado.calcularSalarioBruto() + "€");

        System.out.print("Desglose: ");

        switch (empleado) {
            case EmpleadoAsalariado empleadoAsalariado ->
                    System.out.println("( Salario Base Mensual = " + empleadoAsalariado.getSalarioBaseMensual() + "€ ) + ( Complemento Puesto = " + empleadoAsalariado.getComplementoPuesto() + "€ )");
            case EmpleadoPorHoras empleadoPorHoras ->
                    System.out.println("( Horas trabajadas = " + empleadoPorHoras.getHorasTrabajadas() + " ) * ( Precio por hora = " + empleadoPorHoras.getPrecioHora() + "€ )");
            case EmpleadoComisionista empleadoComisionista ->
                    System.out.println("( Salario Mínimo Garantizado = " + empleadoComisionista.getSalarioMinimoGarantizado() + "€ ) + [ ( Ventas Realizadas = " + empleadoComisionista.getVentasRealizadas() + "€ ) * ( Porcentaje Comisión = " + empleadoComisionista.getPorcentajeComision() * 100 + "% ) ]");
            default -> System.out.println("No se conoce el desglose de la nómina.");
        }

        introParaContinuar("\nPresione INTRO para volver al menú anterior...");
    }

    // =======================================================
    //                    Cambiar contraseña
    // =======================================================

    public static void menuCambiarContrasenia () {
        limpiarPantalla();

        System.out.println("\n --------------------------");
        System.out.println(" --- CAMBIAR CONTRASEÑA ---");
        System.out.println(" --------------------------\n");
    }

    public static String pedirContrasenia (String tipoContrasenia) {
        System.out.print("Escriba su " + tipoContrasenia + ": ");
        return teclado.nextLine();
    }

    public static void mensajeContraseniaCambiada () {
        System.out.println("\n - CONTRASEÑA CAMBIADA CON ÉXITO -");

        introParaContinuar("\nPresione INTRO para volver al menú anterior...");
    }

    public static void mensajeMismaContrasenia () {
        System.out.println("\nLa nueva contraseña es la misma que la actual. Fallo al cambiar contraseña.");

        introParaContinuar("\nPresione INTRO para volver a introducir contraseñas...");
    }

    public static void mensajeContraseniaErronea () {
        System.out.println("\nLa contraseña actual es errónea. Fallo al cambiar contraseña.");

        introParaContinuar("\nPresione INTRO para volver a introducir contraseñas...");
    }

    // =======================================================
    //                    AÑADIR EMPLEADO
    // =======================================================

    public static void menuAnadirEmpleado () {
        limpiarPantalla();

        System.out.println("\n -----------------------------------");
        System.out.println(" --- FORMULARIO DE ALTA EMPLEADO ---");
        System.out.println(" -----------------------------------\n");
    }

    public static int menuSeleccionContrato () {
        System.out.println("\n - Seleccione Tipo de Contrato -\n");
        System.out.println("1. Asalariado");
        System.out.println("2. Por Horas");
        System.out.println("3. Comisionista");
        System.out.print("\nSeleccione una opción: ");

        int opcion = leerEnteroSeguro();

        System.out.println();
        return opcion;
    }

    public static String pedirIdEmpleado () {
        return introducirString("ID Único (ej: E01)");
    }

    public static void mensajeIdEmpleadoRepetidoAnadir () {
        System.out.println("\nNo es posible añadir ese Id porque ya está repetido. Pruebe de nuevo.\n");
    }

    public static String pedirDNIEmpleado () {
        return introducirString("DNI/NIE");
    }

    public static String pedirNombreEmpleado () {
        return introducirString("Nombre");
    }

    public static String pedirApellidosEmpleado () {
        return introducirString("Apellidos");
    }

    public static String pedirEmailEmpleado () {
        return introducirString("Email corporativo");
    }

    public static String pedirDepartamentoEmpleado () {
        return introducirString("Código de Departamento (DEV, SYS, MK, SALES, HR)").toUpperCase();
    }

    public static String pedirContraseniaEmpleado () {
        return introducirString("Contraseña");
    }

    // Asalariado -------------------------------------------------------------------------------

    public static Double pedirSalarioBaseEmpleado () {
        return introducirDouble("Salario Base Mensual (€)");
    }

    public static Double pedirComplementoPuestoEmpleado () {
        return introducirDouble("Complemento de Puesto (€)");
    }

    // Por horas ------------------------------------------------------------------------------

    public static Double pedirPrecioHoraEmpleado () {
        return introducirDouble("Precio de la hora (€)");
    }

    public static int menuHorasEmpleado () {
        System.out.println("\n¿Desea introducir horas específicas o aplicar el valor por defecto?\n");
        System.out.println("1. Introducir horas manualmente");
        System.out.println("2. Usar valor por defecto (160 horas)");
        System.out.print("\nSeleccione una opción: ");

        int opcion = leerEnteroSeguro();

        System.out.println();
        return opcion;
    }

    public static int pedirHorasTrabajadasEmpleado () {
        return introducirInt("Horas trabajadas este mes: ");
    }

    // Comisionista -------------------------------------------------------------------------

    public static Double pedirSalarioMinimoEmpleado () {
        return introducirDouble("Salario Mínimo Garantizado (€)");
    }

    public static Double pedirPorcentajeComisionEmpleado () {
        return introducirDouble("Porcentaje de Comisión (ej: 0,10 para un 10%)");
    }

    public static void mensajePorcentajeErroneo () {
        System.out.println("\nEl porcentaje ha de estar entre 0 y 1 (ej: 0,15 para un 15%), inténtelo de nuevo.\n");
    }

    public static int menuVentasEmpleado () {
        System.out.println("\n¿Desea introducir ventas iniciales acumuladas?\n");
        System.out.println("1. Introducir ventas manualmente");
        System.out.println("2. Usar valor por defecto (5000€ en ventas)");
        System.out.print("\nSeleccione una opción: ");

        int opcion = leerEnteroSeguro();

        System.out.println("\n");
        return opcion;
    }

    public static Double pedirVentasEmpleado () {
        return introducirDouble("Monto de ventas acumuladas (€)");
    }

    // Mostrar empleado añadido --------------------------------------------------------------

    public static void mostrarEmpleadoAnadido (Empleado empleadoAnadido, String tipoEmpleado) {
        System.out.println("\nEmpleado " + tipoEmpleado + " añadido con éxito: " + empleadoAnadido);

        introParaContinuar("\nPresione INTRO para volver al menu anterior...");
    }

    // Fallo en la creación del empleado

    public static void mensajeFalloCrearEmpleado () {
        System.out.println("\nFallo en los valores de los campos del empleado. No se creó ningún registro.\n");
    }

    // =======================================================
    //                      LOGIN ADMIN
    // =======================================================

    public static void menuLoginAdministrador () {
        limpiarPantalla();

        System.out.println("\n ---------------------------");
        System.out.println(" --- LOGIN ADMINISTRADOR ---");
        System.out.println(" ---------------------------\n");
    }

    public static String pedirUsuarioLogin () {
        return introducirString(" -> Usuario");
    }

    public static String pedirContraseniaLogin () {
        return introducirString(" -> Contraseña");
    }

    public static void accesoConcedidoLogin () {
        System.out.println("\n     - ACCESO CONCEDIDO -\n");
    }

    public static void accesoBloqueadoLogin () {
        System.out.println("\n     - ACCESO BLOQUEADO -\n");
    }

    public static void intentosRestantesLogin (int intentos) {
        System.out.println("\nIncorrecto. Intentos restantes: " + intentos);
        System.out.println("----------------------------------------------\n");
    }


    // =======================================================
    //                     LOGIN EMPLEADO
    // =======================================================

    public static void menuLoginEmpleado () {
        limpiarPantalla();

        System.out.println("\n ----------------------");
        System.out.println(" --- LOGIN EMPLEADO ---");
        System.out.println(" ----------------------\n");
    }

    public static String pedirIdLogin () {
        return introducirString(" -> ID (ej: E01)");
    }





























    // ==========================================
    //          MÉTODOS AUXILIARES
    // ==========================================
    private static int leerEnteroSeguro() {
        while (!teclado.hasNextInt()) {
            System.out.println("\nError: Debe introducir un número entero válido.");
            System.out.print("\nInténtelo de nuevo: ");
            teclado.next();
        }
        int numero = teclado.nextInt();
        teclado.nextLine(); // Limpia el buffer
        return numero;
    }

    private static double leerDoubleSeguro() {
        while (!teclado.hasNextDouble()) {
            System.out.println("\nError: Debe introducir un valor numérico decimal válido.");
            System.out.print("\nInténtelo de nuevo: ");
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
                System.out.println("\nError: Formato de fecha incorrecto.");
                System.out.print("\nInténtelo de nuevo (DD/MM/AAAA): ");
            }
        }
    }

    private static void limpiarPantalla() {
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

    private static int introducirInt (String peticion) {
        System.out.print(peticion + ": ");
        return leerEnteroSeguro();
    }

    private static Double introducirDouble (String peticion) {
        System.out.print(peticion + ": ");
        return leerDoubleSeguro();
    }

    private static String introducirString (String peticion) {
        System.out.print(peticion + ": ");
        return teclado.nextLine();
    }

    private static LocalDate introducirFecha (String peticion) {
        System.out.print(peticion + ": ");
        return leerFechaSegura();
    }

    private static void introParaContinuar (String mensaje) {
        System.out.println(mensaje);
        teclado.nextLine();
    }

}
