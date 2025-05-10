import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner lectura = new Scanner(System.in);
        int numero;
        String validarMostrarHistorial = "";
        ConsultaMoneda consultaMoneda = new ConsultaMoneda();

        List<String> historialConversiones = new ArrayList<>();

        while (true){

            consultaMoneda.escribirMenu();
            numero = lectura.nextInt();

            if ( numero == 11 ) {
                break;
            }

            consultaMoneda.creaParConversion(numero-1);

            System.out.println("\nIngrese el valor que desea convertir:");

            double monto = lectura.nextDouble();
            consultaMoneda.setMonto(monto);

            Conversion conversion = consultaMoneda.conversion();
            String mensaje = "\nEl valor de " + monto + conversion + "\n";
            System.out.println(mensaje);

            historialConversiones.add(mensaje);
        }

        if ( !historialConversiones.isEmpty() ){
            System.out.println("Si desea ver el historial de las conversiones realizadas ingrese 's' si on ingrese cualquier letra ");

            validarMostrarHistorial = lectura.next();
        }

        if (validarMostrarHistorial.equals("s")) {

            System.out.println("\n***************************************************");
            System.out.println("\nHistorial de las ultimas conversiones");

            historialConversiones.forEach(System.out::println);
        }
    }
}