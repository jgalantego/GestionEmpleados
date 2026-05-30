import modelo.Empresa;
import controlador.Autenticador;
import controlador.ControladorEmpresa;

public class Main {

    public static void main(String[] args) {
        Empresa empresa = new Empresa();
        Autenticador autenticador = new Autenticador(empresa);
        ControladorEmpresa controladorEmpresa = new ControladorEmpresa(empresa, autenticador);

        controladorEmpresa.menuPrincipal();
    }

}