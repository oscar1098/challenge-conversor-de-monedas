import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    private String [][] conversiones =  {
            {"USD","ARS"},
            {"ARS","USD"},
            {"USD","BRL"},
            {"BRL","USD"},
            {"USD","COP"},
            {"COP","USD"},
            {"USD","EUR"},
            {"EUR","USD"},
            {"USD","GBP"},
            {"GBP","USD"}
    };

    private String monedaBase;
    private String monedaDestino;
    private String apiKey = "60e074df19cd57df1e440ab8";
    private double monto;


    public void escribirMenu() {
        System.out.println("**************************************************\n\n" +
                "Bienvenido al conversor de mondea\n\n"+
                "1) Dólar =>> Peso aregentino\n"+
                "2) Peso aregentino =>> Dólar\n"+
                "3) Dólar =>> Real brasileño\n"+
                "4) Real brasileño =>> Dólar\n"+
                "5) Dólar =>> Peso colombiano\n"+
                "6) Peso colombiano =>> Dólar\n"+
                "7) Dólar =>> Euro\n"+
                "8) Euro =>> Dólar\n"+
                "9) Dólar =>> Libra esterlina\n"+
                "10) Libra esterlina =>> Dólar\n"+
                "11) Historial de conversiones\n" +
                "11) Salir\n"+
                "\nElija una opción valida: ");
    }

    public void creaParConversion ( int numPareja ) {

        this.monedaBase = conversiones[numPareja][0];
        this.monedaDestino = conversiones[numPareja][1];
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Conversion conversion (){

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + this.apiKey + "/pair/" + this.monedaBase + "/" + this.monedaDestino + "/" + this.monto);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Conversion.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
