package controlador;

import modelo.Empleado;
import modelo.Empresa;
import vista.VistaConsola;

public class Autenticador {
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin";

    Empresa empresa;

    public Autenticador (Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean loginAdministrador() {
        VistaConsola.menuLoginAdministrador();
        int intentos = 3;

        while (intentos > 0) {
            String usuario = VistaConsola.pedirUsuarioLogin();
            String password = VistaConsola.pedirContraseniaLogin();

            if (ADMIN_USER.equals(usuario) && ADMIN_PASS.equals(password)) {
                VistaConsola.accesoConcedidoLogin();
                return true;
            } else {
                intentos--;
                VistaConsola.intentosRestantesLogin(intentos);
            }
        }

        VistaConsola.accesoBloqueadoLogin();
        return false;
    }

    public Empleado loginEmpleado() {
        VistaConsola.menuLoginEmpleado();
        int intentos = 3;

        while (intentos > 0) {
            String id = VistaConsola.pedirIdLogin();
            String password = VistaConsola.pedirContraseniaLogin();

            Empleado empleado = empresa.buscarPorId(id);

            if (empleado != null && empleado.getPassword().equals(password)) {
                VistaConsola.accesoConcedidoLogin();
                return empleado;
            } else {
                intentos--;
                VistaConsola.intentosRestantesLogin(intentos);
            }
        }

        VistaConsola.accesoBloqueadoLogin();
        return null;
    }
}
