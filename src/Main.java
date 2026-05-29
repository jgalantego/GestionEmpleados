import modelo.*;
import controlador.*;

import java.util.Scanner;

public class Main {




    public static void main(String[] args) {
        //Eliminar el Scanner
        Scanner teclado = new Scanner(System.in);

        Empresa empresa = new Empresa();
        Autenticador autenticador = new Autenticador(empresa, teclado);
        ControladorEmpresa controladorEmpresa = new ControladorEmpresa(empresa, autenticador);

        controladorEmpresa.menuPrincipal();
    }

}