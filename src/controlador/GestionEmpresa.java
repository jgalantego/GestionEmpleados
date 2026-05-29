package controlador;

import modelo.*;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

public class GestionEmpresa {
    private List<Empleado> listaEmpleados;
    private HashMap<String, String> departamentos; // Clave: Código (DEV), Valor: Nombre (Desarrollo)

    public GestionEmpresa() {
        this.listaEmpleados = new ArrayList<>();
        this.departamentos = new HashMap<>();

        departamentos.put("DEV", "Desarrollo de Software");
        departamentos.put("SYS", "Sistemas");
        departamentos.put("MK", "Marketing Digital");
        departamentos.put("SALES", "Departamento de Ventas");
        departamentos.put("HR", "Recursos Humanos");

        precargarDatos();
    }

    // CASO DE USO: Carga de datos
    private void precargarDatos() {
        listaEmpleados.add(new EmpleadoAsalariado("E01", "12345678A", "Samuel", "García", "samuel@empresa.com", LocalDate.of(2024, 1, 15), "DEV", "empleado", 1800.0, 200.0));
        listaEmpleados.add(new EmpleadoPorHoras("E02", "87654321B", "Elena", "Martín", "elena@empresa.com", LocalDate.of(2025, 3, 1), "SYS", "empleado",15.5));
        listaEmpleados.add(new EmpleadoComisionista("E03", "45678912C", "Carlos", "López", "carlos@empresa.com", LocalDate.of(2023, 6, 20), "MK", "empleado", 1100.0, 0.10));
// EMPLEADOS ASALARIADOS
        listaEmpleados.add(new EmpleadoAsalariado("E04", "23456789D", "Laura", "Sánchez", "laura@empresa.com", LocalDate.of(2022, 5, 12), "DEV", "empleado", 2100.0, 300.0));
        listaEmpleados.add(new EmpleadoAsalariado("E05", "34567890E", "David", "Rodríguez", "david@empresa.com", LocalDate.of(2021, 11, 1), "SYS", "empleado", 1950.0, 150.0));
        listaEmpleados.add(new EmpleadoAsalariado("E06", "45678901F", "Marta", "Fernández", "marta@empresa.com", LocalDate.of(2023, 2, 28), "HR", "empleado", 1750.0, 100.0));
        listaEmpleados.add(new EmpleadoAsalariado("E07", "56789012G", "Alejandro", "González", "alejandro@empresa.com", LocalDate.of(2020, 8, 15), "DEV", "empleado", 2500.0, 450.0));
        listaEmpleados.add(new EmpleadoAsalariado("E08", "67890123H", "Sofía", "Gómez", "sofia@empresa.com", LocalDate.of(2024, 6, 10), "MK", "empleado", 1600.0, 120.0));
        listaEmpleados.add(new EmpleadoAsalariado("E09", "78901234I", "Javier", "Díaz", "javier@empresa.com", LocalDate.of(2023, 9, 21), "SALES", "empleado", 1500.0, 200.0));
        listaEmpleados.add(new EmpleadoAsalariado("E10", "89012345J", "Lucía", "Álvarez", "lucia@empresa.com", LocalDate.of(2022, 1, 10), "SYS", "empleado", 2050.0, 250.0));
        listaEmpleados.add(new EmpleadoAsalariado("E11", "90123456K", "Manuel", "Moreno", "manuel@empresa.com", LocalDate.of(2021, 4, 5), "DEV", "empleado", 2200.0, 350.0));
        listaEmpleados.add(new EmpleadoAsalariado("E12", "01234567L", "Paula", "Jiménez", "paula@empresa.com", LocalDate.of(2025, 1, 15), "HR", "empleado", 1800.0, 100.0));
        listaEmpleados.add(new EmpleadoAsalariado("E13", "11223344M", "Marcos", "Muñoz", "marcos@empresa.com", LocalDate.of(2019, 12, 1), "DEV", "empleado", 2800.0, 500.0));
        listaEmpleados.add(new EmpleadoAsalariado("E14", "22334455N", "Sara", "Romero", "sara@empresa.com", LocalDate.of(2023, 7, 14), "MK", "empleado", 1650.0, 150.0));
        listaEmpleados.add(new EmpleadoAsalariado("E15", "33445566O", "Álvaro", "Alonso", "alvaro@empresa.com", LocalDate.of(2020, 3, 22), "SYS", "empleado", 2400.0, 400.0));
        listaEmpleados.add(new EmpleadoAsalariado("E16", "44556677P", "Irene", "Gutiérrez", "irene@empresa.com", LocalDate.of(2022, 10, 5), "SALES", "empleado", 1450.0, 180.0));
        listaEmpleados.add(new EmpleadoAsalariado("E17", "55667788Q", "Pablo", "Navarro", "pablo@empresa.com", LocalDate.of(2024, 2, 18), "DEV", "empleado", 1900.0, 200.0));
        listaEmpleados.add(new EmpleadoAsalariado("E18", "66778899R", "Alba", "Torres", "alba@empresa.com", LocalDate.of(2021, 6, 30), "HR", "empleado", 1900.0, 120.0));
        listaEmpleados.add(new EmpleadoAsalariado("E19", "77889900S", "Ignacio", "Domínguez", "ignacio@empresa.com", LocalDate.of(2023, 5, 12), "SYS", "empleado", 2150.0, 220.0));
        listaEmpleados.add(new EmpleadoAsalariado("E20", "88990011T", "Julia", "Vázquez", "julia@empresa.com", LocalDate.of(2025, 2, 1), "MK", "empleado", 1700.0, 130.0));
        listaEmpleados.add(new EmpleadoAsalariado("E21", "99001122U", "Hugo", "Ramos", "hugo@empresa.com", LocalDate.of(2022, 8, 24), "DEV", "empleado", 2300.0, 380.0));
        listaEmpleados.add(new EmpleadoAsalariado("E22", "13579246A", "Natalia", "Gil", "natalia@empresa.com", LocalDate.of(2020, 11, 15), "SALES", "empleado", 1600.0, 250.0));
        listaEmpleados.add(new EmpleadoAsalariado("E23", "24681357B", "Diego", "Ramírez", "diego@empresa.com", LocalDate.of(2024, 8, 1), "SYS", "empleado", 1850.0, 140.0));
        listaEmpleados.add(new EmpleadoAsalariado("E24", "35792468C", "Emma", "Blanco", "emma@empresa.com", LocalDate.of(2023, 11, 20), "DEV", "empleado", 2000.0, 280.0));
        listaEmpleados.add(new EmpleadoAsalariado("E25", "46813579D", "Mateo", "Suárez", "mateo@empresa.com", LocalDate.of(2021, 5, 5), "HR", "empleado", 2000.0, 150.0));
        listaEmpleados.add(new EmpleadoAsalariado("E26", "57924681E", "Clara", "Ortega", "clara@empresa.com", LocalDate.of(2022, 3, 14), "MK", "empleado", 1800.0, 160.0));
        listaEmpleados.add(new EmpleadoAsalariado("E27", "68135792F", "Adrián", "Morales", "adrian@empresa.com", LocalDate.of(2018, 7, 19), "DEV", "empleado", 3100.0, 600.0));
        listaEmpleados.add(new EmpleadoAsalariado("E28", "79246813G", "Marina", "Crespo", "marina@empresa.com", LocalDate.of(2024, 10, 1), "SYS", "empleado", 1750.0, 110.0));
        listaEmpleados.add(new EmpleadoAsalariado("E29", "81357924H", "Lucas", "Guerrero", "lucas@empresa.com", LocalDate.of(2023, 4, 30), "SALES", "empleado", 1550.0, 210.0));
        listaEmpleados.add(new EmpleadoAsalariado("E30", "92468135I", "Claudia", "Ibáñez", "claudia@empresa.com", LocalDate.of(2022, 12, 15), "DEV", "empleado", 2150.0, 310.0));

// EMPLEADOS POR HORAS
        listaEmpleados.add(new EmpleadoPorHoras("E31", "12345670X", "Rubén", "Ferrari", "ruben@empresa.com", LocalDate.of(2024, 2, 10), "DEV", "empleado", 16.0));
        listaEmpleados.add(new EmpleadoPorHoras("E32", "12345671Y", "Eva", "Garrido", "eva@empresa.com", LocalDate.of(2025, 4, 1), "SYS", "empleado", 14.5));
        listaEmpleados.add(new EmpleadoPorHoras("E33", "12345672Z", "Óscar", "Santos", "oscar@empresa.com", LocalDate.of(2023, 8, 15), "MK", "empleado", 15.0));
        listaEmpleados.add(new EmpleadoPorHoras("E34", "12345673W", "Mónica", "Castillo", "monica@empresa.com", LocalDate.of(2022, 6, 20), "HR", "empleado", 15.5));
        listaEmpleados.add(new EmpleadoPorHoras("E35", "12345674V", "Youssef", "Mansour", "youssef@empresa.com", LocalDate.of(2021, 10, 5), "DEV", "empleado", 18.5));
        listaEmpleados.add(new EmpleadoPorHoras("E36", "12345675U", "Inés", "Pena", "ines@empresa.com", LocalDate.of(2024, 1, 12), "SALES", "empleado", 13.0));
        listaEmpleados.add(new EmpleadoPorHoras("E37", "12345676T", "Felipe", "Flores", "felipe@empresa.com", LocalDate.of(2023, 3, 25), "SYS", "empleado", 14.0));
        listaEmpleados.add(new EmpleadoPorHoras("E38", "12345677S", "Nerea", "Benítez", "nerea@empresa.com", LocalDate.of(2025, 5, 18), "MK", "empleado", 15.0));
        listaEmpleados.add(new EmpleadoPorHoras("E39", "12345678R", "Samuel", "Méndez", "samuel2@empresa.com", LocalDate.of(2022, 9, 30), "DEV", "empleado", 17.0));
        listaEmpleados.add(new EmpleadoPorHoras("E40", "12345679Q", "Celia", "Ríos", "celia@empresa.com", LocalDate.of(2020, 12, 14), "HR", "empleado", 16.5));
        listaEmpleados.add(new EmpleadoPorHoras("E41", "87654320A", "Rodrigo", "Calvo", "rodrigo@empresa.com", LocalDate.of(2023, 1, 22), "SALES", "empleado", 13.5));
        listaEmpleados.add(new EmpleadoPorHoras("E42", "87654322C", "Ainhoa", "Serra", "ainhoa@empresa.com", LocalDate.of(2021, 7, 8), "SYS", "empleado", 15.0));
        listaEmpleados.add(new EmpleadoPorHoras("E43", "87654323D", "Xavier", "Vila", "xavier@empresa.com", LocalDate.of(2024, 5, 2), "DEV", "empleado", 19.0));
        listaEmpleados.add(new EmpleadoPorHoras("E44", "87654324E", "Lidia", "Sanz", "lidia@empresa.com", LocalDate.of(2022, 11, 11), "MK", "empleado", 14.8));
        listaEmpleados.add(new EmpleadoPorHoras("E45", "87654325F", "Aitor", "Pastor", "aitor@empresa.com", LocalDate.of(2023, 10, 19), "HR", "empleado", 15.0));
        listaEmpleados.add(new EmpleadoPorHoras("E46", "87654326G", "Iris", "Soler", "iris@empresa.com", LocalDate.of(2025, 2, 27), "DEV", "empleado", 16.5));
        listaEmpleados.add(new EmpleadoPorHoras("E47", "87654327H", "Ismael", "Moya", "ismael@empresa.com", LocalDate.of(2021, 2, 15), "SALES", "empleado", 14.0));
        listaEmpleados.add(new EmpleadoPorHoras("E48", "87654328I", "Noelia", "Delgado", "noelia@empresa.com", LocalDate.of(2020, 5, 24), "SYS", "empleado", 16.0));
        listaEmpleados.add(new EmpleadoPorHoras("E49", "87654329J", "Ruben", "Marín", "ruben2@empresa.com", LocalDate.of(2024, 7, 7), "MK", "empleado", 15.2));
        listaEmpleados.add(new EmpleadoPorHoras("E50", "98765432K", "Mireia", "Muñoz", "mireia@empresa.com", LocalDate.of(2023, 12, 12), "DEV", "empleado", 17.5));
        listaEmpleados.add(new EmpleadoPorHoras("E51", "98765433L", "Joel", "Vidal", "joel@empresa.com", LocalDate.of(2022, 4, 3), "HR", "empleado", 15.5));
        listaEmpleados.add(new EmpleadoPorHoras("E52", "98765434M", "Berta", "Bravo", "berta@empresa.com", LocalDate.of(2024, 9, 16), "SYS", "empleado", 13.8));
        listaEmpleados.add(new EmpleadoPorHoras("E53", "98765435N", "Victor", "Maldonado", "victor@empresa.com", LocalDate.of(2021, 9, 23), "SYS", "empleado", 15.5));
        listaEmpleados.add(new EmpleadoPorHoras("E54", "98765436O", "Estela", "Gallardo", "estela@empresa.com", LocalDate.of(2023, 6, 30), "DEV", "empleado", 18.0));
        listaEmpleados.add(new EmpleadoPorHoras("E55", "98765437P", "Isaac", "Cruz", "isaac@empresa.com", LocalDate.of(2025, 3, 11), "MK", "empleado", 14.5));

// EMPLEADOS COMISIONISTAS
        listaEmpleados.add(new EmpleadoComisionista("E56", "11112222A", "Gemma", "Cano", "gemma@empresa.com", LocalDate.of(2022, 3, 15), "SALES", "empleado", 1000.0, 0.12));
        listaEmpleados.add(new EmpleadoComisionista("E57", "22223333B", "Raúl", "Rey", "raul@empresa.com", LocalDate.of(2021, 8, 20), "SALES", "empleado", 1100.0, 0.10));
        listaEmpleados.add(new EmpleadoComisionista("E58", "33334444C", "Silvia", "Pardo", "silvia@empresa.com", LocalDate.of(2023, 1, 10), "MK", "empleado", 1050.0, 0.08));
        listaEmpleados.add(new EmpleadoComisionista("E59", "44445555D", "Fabián", "Estepa", "fabian@empresa.com", LocalDate.of(2024, 5, 5), "SALES", "empleado", 1200.0, 0.15));
        listaEmpleados.add(new EmpleadoComisionista("E60", "55556666E", "Patricia", "Soria", "patricia@empresa.com", LocalDate.of(2020, 10, 12), "SALES", "empleado", 1150.0, 0.11));
        listaEmpleados.add(new EmpleadoComisionista("E61", "66667777F", "Fernando", "Dura", "fernando@empresa.com", LocalDate.of(2022, 12, 1), "MK", "empleado", 1000.0, 0.09));
        listaEmpleados.add(new EmpleadoComisionista("E62", "77778888G", "Olga", "Redondo", "olga@empresa.com", LocalDate.of(2023, 4, 18), "HR", "empleado", 1100.0, 0.10));
        listaEmpleados.add(new EmpleadoComisionista("E63", "88889999H", "Borja", "Prieto", "borja@empresa.com", LocalDate.of(2025, 1, 20), "SALES", "empleado", 1250.0, 0.14));
        listaEmpleados.add(new EmpleadoComisionista("E64", "99990000I", "Teresa", "Cobos", "teresa@empresa.com", LocalDate.of(2021, 2, 25), "MK", "empleado", 1050.0, 0.08));
        listaEmpleados.add(new EmpleadoComisionista("E65", "00001111J", "Arturo", "Sáez", "arturo@empresa.com", LocalDate.of(2019, 6, 30), "SYS", "empleado", 1300.0, 0.15));
        listaEmpleados.add(new EmpleadoComisionista("E66", "12312312A", "Diana", "Milla", "diana@empresa.com", LocalDate.of(2024, 9, 5), "SYS", "empleado", 1000.0, 0.12));
        listaEmpleados.add(new EmpleadoComisionista("E67", "23423423B", "Guillermo", "Pascual", "guillermo@empresa.com", LocalDate.of(2023, 2, 14), "MK", "empleado", 1100.0, 0.10));
        listaEmpleados.add(new EmpleadoComisionista("E68", "34534534C", "Valeria", "Hidalgo", "valeria@empresa.com", LocalDate.of(2022, 7, 22), "SALES", "empleado", 1050.0, 0.09));
        listaEmpleados.add(new EmpleadoComisionista("E69", "45645645D", "Asier", "Mateos", "asier@empresa.com", LocalDate.of(2021, 5, 17), "SALES", "empleado", 1200.0, 0.13));
        listaEmpleados.add(new EmpleadoComisionista("E70", "56756756E", "Alicia", "Lorenzo", "alicia@empresa.com", LocalDate.of(2025, 4, 10), "MK", "empleado", 1000.0, 0.08));
        listaEmpleados.add(new EmpleadoComisionista("E71", "67867867F", "Gonzalo", "Guerra", "gonzalo@empresa.com", LocalDate.of(2020, 11, 1), "SALES", "empleado", 1250.0, 0.12));
        listaEmpleados.add(new EmpleadoComisionista("E72", "78978978G", "Miriam", "Bueno", "miriam@empresa.com", LocalDate.of(2023, 11, 11), "SALES", "empleado", 1100.0, 0.11));
        listaEmpleados.add(new EmpleadoComisionista("E73", "89089089H", "Cristian", "Domenech", "cristian@empresa.com", LocalDate.of(2024, 3, 28), "MK", "empleado", 1050.0, 0.10));
        listaEmpleados.add(new EmpleadoComisionista("E74", "90190190I", "Lara", "Casal", "lara@empresa.com", LocalDate.of(2022, 1, 15), "SALES", "empleado", 1150.0, 0.12));
        listaEmpleados.add(new EmpleadoComisionista("E75", "01201201J", "Santi", "Luque", "santi@empresa.com", LocalDate.of(2021, 4, 20), "SALES", "empleado", 1200.0, 0.14));
        listaEmpleados.add(new EmpleadoComisionista("E76", "98798798A", "Paula", "Maza", "paula2@empresa.com", LocalDate.of(2023, 5, 2), "MK", "empleado", 1100.0, 0.09));
        listaEmpleados.add(new EmpleadoComisionista("E77", "87687687B", "Enrique", "Vera", "enrique@empresa.com", LocalDate.of(2024, 12, 12), "SALES", "empleado", 1000.0, 0.10));
        listaEmpleados.add(new EmpleadoComisionista("E78", "76576576C", "Raquel", "Cardona", "raquel@empresa.com", LocalDate.of(2022, 8, 30), "DEV", "empleado", 1150.0, 0.11));
        listaEmpleados.add(new EmpleadoComisionista("E79", "65465465D", "Alfonso", "Aranda", "alfonso@empresa.com", LocalDate.of(2020, 1, 15), "MK", "empleado", 1300.0, 0.15));
        listaEmpleados.add(new EmpleadoComisionista("E80", "54354354E", "Zoe", "Vila", "zoe@empresa.com", LocalDate.of(2025, 5, 1), "SALES", "empleado", 1050.0, 0.12));
// EVALUACIONES
        listaEmpleados.get(0).registrarEvaluacion(9.0);  // Samuel
        listaEmpleados.get(1).registrarEvaluacion(4.5);  // Elena
        listaEmpleados.get(2).registrarEvaluacion(7.5);  // Carlos
        listaEmpleados.get(3).registrarEvaluacion(8.2);  // Laura
        listaEmpleados.get(4).registrarEvaluacion(6.0);  // David
        listaEmpleados.get(5).registrarEvaluacion(5.5);  // Marta
        listaEmpleados.get(6).registrarEvaluacion(9.8);  // Alejandro (Top Admin)
        listaEmpleados.get(7).registrarEvaluacion(4.2);  // Sofía
        listaEmpleados.get(8).registrarEvaluacion(7.0);  // Javier
        listaEmpleados.get(9).registrarEvaluacion(6.8);  // Lucía
        listaEmpleados.get(10).registrarEvaluacion(8.9); // Manuel
        listaEmpleados.get(11).registrarEvaluacion(5.0); // Paula
        listaEmpleados.get(12).registrarEvaluacion(9.2); // Marcos
        listaEmpleados.get(13).registrarEvaluacion(6.4); // Sara
        listaEmpleados.get(14).registrarEvaluacion(7.8); // Álvaro
        listaEmpleados.get(15).registrarEvaluacion(3.5); // Irene
        listaEmpleados.get(16).registrarEvaluacion(8.1); // Pablo
        listaEmpleados.get(17).registrarEvaluacion(7.3); // Alba
        listaEmpleados.get(18).registrarEvaluacion(5.9); // Ignacio
        listaEmpleados.get(19).registrarEvaluacion(6.2); // Julia
        listaEmpleados.get(20).registrarEvaluacion(9.0); // Hugo
        listaEmpleados.get(21).registrarEvaluacion(4.8); // Natalia
        listaEmpleados.get(22).registrarEvaluacion(7.1); // Diego
        listaEmpleados.get(23).registrarEvaluacion(8.5); // Emma
        listaEmpleados.get(24).registrarEvaluacion(6.7); // Mateo
        listaEmpleados.get(25).registrarEvaluacion(5.2); // Clara
        listaEmpleados.get(26).registrarEvaluacion(10.0);// Adrián
        listaEmpleados.get(27).registrarEvaluacion(4.0); // Marina
        listaEmpleados.get(28).registrarEvaluacion(6.9); // Lucas
        listaEmpleados.get(29).registrarEvaluacion(7.6); // Claudia
        listaEmpleados.get(30).registrarEvaluacion(8.0); // Rubén
        listaEmpleados.get(31).registrarEvaluacion(5.8); // Eva
        listaEmpleados.get(32).registrarEvaluacion(6.3); // Óscar
        listaEmpleados.get(33).registrarEvaluacion(7.2); // Mónica
        listaEmpleados.get(34).registrarEvaluacion(9.1); // Youssef
        listaEmpleados.get(35).registrarEvaluacion(3.9); // Inés
        listaEmpleados.get(36).registrarEvaluacion(6.6); // Felipe
        listaEmpleados.get(37).registrarEvaluacion(5.1); // Nerea
        listaEmpleados.get(38).registrarEvaluacion(8.3); // Samuel 2
        listaEmpleados.get(39).registrarEvaluacion(7.4); // Celia
        listaEmpleados.get(40).registrarEvaluacion(4.9); // Rodrigo
        listaEmpleados.get(41).registrarEvaluacion(6.1); // Ainhoa
        listaEmpleados.get(42).registrarEvaluacion(9.4); // Xavier
        listaEmpleados.get(43).registrarEvaluacion(5.7); // Lidia
        listaEmpleados.get(44).registrarEvaluacion(7.0); // Aitor
        listaEmpleados.get(45).registrarEvaluacion(8.2); // Iris
        listaEmpleados.get(46).registrarEvaluacion(4.5); // Ismael
        listaEmpleados.get(47).registrarEvaluacion(6.8); // Noelia
        listaEmpleados.get(48).registrarEvaluacion(7.5); // Ruben 2
        listaEmpleados.get(49).registrarEvaluacion(8.7); // Mireia
        listaEmpleados.get(50).registrarEvaluacion(5.3); // Joel
        listaEmpleados.get(51).registrarEvaluacion(6.0); // Berta
        listaEmpleados.get(52).registrarEvaluacion(7.9); // Victor
        listaEmpleados.get(53).registrarEvaluacion(8.6); // Estela
        listaEmpleados.get(54).registrarEvaluacion(4.1); // Isaac
        listaEmpleados.get(55).registrarEvaluacion(7.3); // Gemma
        listaEmpleados.get(56).registrarEvaluacion(8.4); // Raúl
        listaEmpleados.get(57).registrarEvaluacion(6.2); // Silvia
        listaEmpleados.get(58).registrarEvaluacion(9.0); // Fabián
        listaEmpleados.get(59).registrarEvaluacion(5.6); // Patricia
        listaEmpleados.get(60).registrarEvaluacion(6.5); // Fernando
        listaEmpleados.get(61).registrarEvaluacion(7.7); // Olga
        listaEmpleados.get(62).registrarEvaluacion(8.1); // Borja
        listaEmpleados.get(63).registrarEvaluacion(3.8); // Teresa
        listaEmpleados.get(64).registrarEvaluacion(9.5); // Arturo
        listaEmpleados.get(65).registrarEvaluacion(6.9); // Diana
        listaEmpleados.get(66).registrarEvaluacion(7.2); // Guillermo
        listaEmpleados.get(67).registrarEvaluacion(5.4); // Valeria
        listaEmpleados.get(68).registrarEvaluacion(8.0); // Asier
        listaEmpleados.get(69).registrarEvaluacion(4.7); // Alicia
        listaEmpleados.get(70).registrarEvaluacion(7.6); // Gonzalo
        listaEmpleados.get(71).registrarEvaluacion(8.3); // Miriam
        listaEmpleados.get(72).registrarEvaluacion(6.1); // Cristian
        listaEmpleados.get(73).registrarEvaluacion(7.0); // Lara
        listaEmpleados.get(74).registrarEvaluacion(8.8); // Santi
        listaEmpleados.get(75).registrarEvaluacion(5.0); // Paula 2
        listaEmpleados.get(76).registrarEvaluacion(6.3); // Enrique
        listaEmpleados.get(77).registrarEvaluacion(7.4); // Raquel
        listaEmpleados.get(78).registrarEvaluacion(9.2); // Alfonso
        listaEmpleados.get(79).registrarEvaluacion(5.9); // Zoe
    }


    public HashMap<String, String> obtenerDepartamentos () {
        return this.departamentos;
    }

    // CASO DE USO: Obtener la lista de todos los empleados
    public List<Empleado> obtenerEmpleados() {
        return this.listaEmpleados;
    }

    // CASO DE USO: Obtener lista ordenada de empleados por antiguedad
    public List<Empleado> obtenerEmpleadosOrdenadosPorAntiguedad() {
        List<Empleado> listaOrdenada = new ArrayList<>(this.listaEmpleados);
        listaOrdenada.sort(Comparator.comparing(Empleado::getFechaAlta));
        return listaOrdenada;
    }
    // CASO DE USO: Obtener lista ordenada de empleados por desempeño
    public List<Empleado> obtenerEmpleadosOrdenadosPorDesempenio() {
        List<Empleado> listaOrdenada = new ArrayList<>(this.listaEmpleados);
        listaOrdenada.sort(Comparator.comparing(Empleado::getDesempenio));
        //listaOrdenada.sort((e1, e2) -> Double.compare(e2.getDesempenio(), e1.getDesempenio()));
        return listaOrdenada;
    }

    // CASO DE USO: Buscar Empleado por ID
    public Empleado buscarPorId(String id) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getId().equalsIgnoreCase(id)) {
                return empleado;
            }
        }
        return null;
    }
    // CASO DE USO: Buscar Empleado por DNI
    public Empleado buscarPorDni(String dni) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getDni().equalsIgnoreCase(dni)) {
                return empleado;
            }
        }
        return null;
    }
    // CASO DE USO: Buscar empleados por nombre
    public List<Empleado> buscarPorNombre(String nombre) {
        String nombreLower = nombre.toLowerCase();
        List<Empleado> empleados = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getNombre().toLowerCase().contains(nombreLower)) {
                empleados.add(empleado);
            }
        }
        return empleados;
    }
    // CASO DE USO: Buscar empleados por apellido
    public List<Empleado> buscarPorApellido(String apellido) {
        String apellidoLower = apellido.toLowerCase();
        List<Empleado> empleados = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getApellidos().toLowerCase().contains(apellidoLower)) {
                empleados.add(empleado);
            }
        }
        return empleados;
    }
    // CASO DE USO: Buscar empleados por email
    public List<Empleado> buscarPorEmail(String email) {
        String emailLower = email.toLowerCase();
        List<Empleado> empleados = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getEmail().toLowerCase().contains(emailLower)) {
                empleados.add(empleado);
            }
        }
        return empleados;
    }
    // CASO DE USO: Buscar empleados por departamento
    public List<Empleado> buscarPorDepartamento(String departamento) {
        String deptLower = departamento.toLowerCase();
        List<Empleado> empleados = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getDepartamento().toLowerCase().contains(deptLower)) {
                empleados.add(empleado);
            }
        }
        return empleados;
    }

    // CASO DE USO: Modificar empleado
    public boolean modificarIdEmpleado(String idActual, String nuevoId) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getId().equals(nuevoId)) {
                System.out.println("\nNo es posible modificar el Id porque ya está repetido.");
                return false;
            }
        }
        Empleado empleado = buscarPorId(idActual);
        if (empleado != null) {
            empleado.setId(nuevoId);
            System.out.println("\nEl Id del empleado ha sio modificado a [" + nuevoId + "]");
            return true;
        }
        System.out.println("\nNo es posible modificar el Id porque no existe el empleado.");
        return false;
    }

    // CASO DE USO: Eliminar Empleado por ID
    public boolean eliminarEmpleado(String id) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getId().equalsIgnoreCase(id)) {
                listaEmpleados.remove(i);
                return true;
            }
        }
        return false;
    }

    // CASO DE USO: Mostrar los departamentos mapeados
    public void mostrarDepartamentos() {
        System.out.println("\n--- LISTA DE DEPARTAMENTOS ---");
        departamentos.forEach( (codigo, nombre) -> { System.out.println("• Código: [" + codigo + "] -> Área: " + nombre); } );
    }


    // CASO DE USO: Añadir Empleado
    private EmpleadoBuilder crearNuevoEmpleado(String id, String dni, String nombre, String apellidos, String email, String dept, String password) {
        return new EmpleadoBuilder().setId(id)
                .setDni(dni)
                .setNombre(nombre)
                .setApellidos(apellidos)
                .setEmail(email)
                .setDepartamento(dept)
                .setPassword(password);
    }

    private void agregarEmpleado(Empleado emp) {
        listaEmpleados.add(emp);
    }

    public void agregarEmpleadoAsalariado(String id, String dni, String nombre, String apellidos, String email, String dept, String password, double base, double complemento) {
        EmpleadoBuilder builder = crearNuevoEmpleado(id, dni, nombre, apellidos, email, dept, password);
        Empleado nuevoAsalariado = builder.paraAsalariado(base, complemento).build();
        agregarEmpleado(nuevoAsalariado);
    }

    public void agregarEmpleadoPorHoras(String id, String dni, String nombre, String apellidos, String email, String dept, String password, double precioHora, int horas) {
        EmpleadoBuilder builder = crearNuevoEmpleado(id, dni, nombre, apellidos, email, dept, password);
        Empleado nuevoPorHoras = builder.paraPorHoras(precioHora, horas).build();
        agregarEmpleado(nuevoPorHoras);
    }

    public void agregarEmpleadoPorHoras(String id, String dni, String nombre, String apellidos, String email, String dept, String password, double precioHora) {
        EmpleadoBuilder builder = crearNuevoEmpleado(id, dni, nombre, apellidos, email, dept, password);
        Empleado nuevoPorHoras = builder.paraPorHoras(precioHora).build();
        agregarEmpleado(nuevoPorHoras);
    }

    public void agregarEmpleadoComisionista(String id, String dni, String nombre, String apellidos, String email, String dept, String password, double minimoGarantizado, double porcentaje, double ventas) {
        EmpleadoBuilder builder = crearNuevoEmpleado(id, dni, nombre, apellidos, email, dept, password);
        Empleado nuevoComisionista = builder.paraComisionista(minimoGarantizado, porcentaje, ventas).build();
        agregarEmpleado(nuevoComisionista);
    }

    public void agregarEmpleadoComisionista(String id, String dni, String nombre, String apellidos, String email, String dept, String password, double minimoGarantizado, double porcentaje) {
        EmpleadoBuilder builder = crearNuevoEmpleado(id, dni, nombre, apellidos, email, dept, password);
        Empleado nuevoComisionista = builder.paraComisionista(minimoGarantizado, porcentaje).build();
        agregarEmpleado(nuevoComisionista);
    }
}
