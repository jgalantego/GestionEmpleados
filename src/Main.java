import modelo.Empresa;
import controlador.Autenticador;
import controlador.ControladorEmpresa;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        Empresa empresa = new Empresa();
        Autenticador autenticador = new Autenticador(empresa);
        ControladorEmpresa controladorEmpresa = new ControladorEmpresa(empresa, autenticador);

        controladorEmpresa.menuPrincipal();
    }

}