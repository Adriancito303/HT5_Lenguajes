import java.util.Scanner;

public class UI {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        AutomataPila apParentesis = new AutomataPila();

        try {
            System.out.println("Ingrese una cadena para identificarla");
            
            String comando = in.nextLine();

            if (apParentesis.esAceptada(comando)){
                System.out.println("Cadena aceptada");
            } else {
                System.out.println("Cadena NO aceptada");    
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            in.close();
        }

    }
    
}
