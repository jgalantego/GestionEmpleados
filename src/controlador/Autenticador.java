package controlador;

import modelo.Empleado;

import java.util.Scanner;

public class Autenticador {
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin";

    GestionEmpresa empresa;
    Scanner teclado;

    public Autenticador (GestionEmpresa empresa, Scanner teclado) {
        this.empresa = empresa;
        this.teclado = teclado;
    }

    public boolean loginAdministrador() {
        System.out.println("\n   --- LOGIN ADMINISTRADOR ---");
        int intentos = 3;

        while (intentos > 0) {
            System.out.print(" -> Usuario: ");
            String usuario = teclado.nextLine();
            System.out.print(" -> Contraseña: ");
            String password = teclado.nextLine();

            if (ADMIN_USER.equals(usuario) && ADMIN_PASS.equals(password)) {
                System.out.println("\n     - ACCESO CONCEDIDO -\n");
                return true;
            } else {
                intentos--;
                System.out.println("Incorrecto. Intentos restantes: " + intentos);
                System.out.println("----------------------------------------------");
            }
        }

        System.out.println("\n     - ACCESO BLOQUEADO -\n");
        return false;
    }

    public Empleado loginEmpleado() {
        System.out.println("\n     --- LOGIN EMPLEADO ---");
        int intentos = 3;

        while (intentos > 0) {
            System.out.print(" -> ID: ");
            String id = teclado.nextLine();
            System.out.print(" -> Contraseña: ");
            String password = teclado.nextLine();

            Empleado empleado = empresa.buscarPorId(id);

            if (empleado != null && empleado.getPassword().equals(password)) {
                System.out.println("\n     - ACCESO CONCEDIDO -\n");
                return empleado;
            } else {
                intentos--;
                System.out.println("Incorrecto. Intentos restantes: " + intentos);
                System.out.println("----------------------------------------------");
            }
        }

        System.out.println("\n     - ACCESO BLOQUEADO -\n");
        return null;
    }
}
