package lucastadaieski.itinerario_de_viagens.util;

public class LoggerSingleton {
    private static LoggerSingleton instancia;

    private LoggerSingleton() {}

    public static LoggerSingleton getInstancia() {
        if (instancia == null) {
            instancia = new LoggerSingleton();
        }
        return instancia;
    }

    public void registrar(String mensagem) {
        System.out.println("[LOG] " + mensagem);
    }
}
