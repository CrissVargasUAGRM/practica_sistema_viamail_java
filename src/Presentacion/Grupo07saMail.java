package Presentacion;

/**
 *
 * @author Oni
 */
public class Grupo07saMail {
    public static void main(String[] args) {

        Manejador mail = new Manejador();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ///leer
                    mail.leer();
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        System.out.println("error al iniciar el ciclo de escucha");
                    }
                }
            }
        }).start();
    }
}
