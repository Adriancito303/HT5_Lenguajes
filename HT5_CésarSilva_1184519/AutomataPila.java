import java.util.Stack;

public class AutomataPila {
    
    private void S0(Stack<String> stack){
        stack.push("B");
    }

    private int S1(String input, int index, Stack<String> stack) throws Exception{
        boolean urls = false;
        while (index < input.length()) {

            String lookahead = "" + input.charAt(index);
            switch (lookahead) {
                case "$": //peliculas
                {
                    String topo = stack.peek();
                    String[] partes = input.split("\\$");
                    if (topo.equals("B")) {
                        stack.push("Pelicula");
                        if (comb(partes[1]) == true) {
                            stack.push("Nombre");
                        }
                        else{
                            throw new Exception("El texto " + partes[1] + " No pertenece al alfabeto de Nombre para una pelicula");
                        }
                        index++;
                    }
                    else if (topo.equals("Nombre")) {
                        if (getnum(partes[2]) == true) {
                            stack.push("Lanzamiento");
                        }
                        else{
                            throw new Exception("El texto " + partes[2] + " No pertenece al alfabeto de Lanzamiento para una pelicula");
                        }
                        index++;
                    }
                    else if (topo.equals("Lanzamiento")) {
                        if (getcar(partes[3]) == true) {
                            stack.pop();//Lanzamiento
                            stack.pop();//Nombre
                            stack.pop();//pelicula
                        }
                        else{
                            throw new Exception("El texto " + partes[3] + " No pertenece al alfabeto de Lanzamiento para una pelicula");
                        }
                        index++;
                    }
                    else
                    {
                        index++;
                    }
                }break;

                case "#": //libros
                {
                    String topo = stack.peek();
                    String[] partes = input.split("\\#");
                    if (topo.equals("B")) {
                        stack.push("Libro");
                        if (getcar(partes[1]) == true) {
                            stack.push("Autor");
                        }
                        else{
                            throw new Exception("El texto " + partes[1] + " No pertenece al alfabeto de Autor para un libro");
                        }
                        index++;
                    }
                    else if (topo.equals("Autor")) {
                        if (comb(partes[2]) == true) {
                            stack.push("Titulo");
                        }
                        else{
                            throw new Exception("El texto " + partes[2] + " No pertenece al alfabeto de Titulo para una pelicula");
                        }
                        index++;
                    }
                    else if (topo.equals("Titulo")) {
                        if (getnum(partes[3]) == true) {
                            stack.pop();//Titulo
                            stack.pop();//Autor
                            stack.pop();//Libro
                        }
                        else{
                            throw new Exception("El texto " + partes[3] + " No pertenece al alfabeto de Publicacion para un libro");
                        }
                        index++;
                    }
                    else
                    {
                        index++;
                    }
                }break;

                case "%": //tesis
                {
                    String topo = stack.peek();
                    String[] partes = input.split("\\%");
                    if (topo.equals("B")) {
                        stack.push("Tesis");
                        if (getcar(partes[1]) == true) {
                            stack.push("Autor");
                        }
                        else{
                            throw new Exception("El texto " + partes[1] + " No pertenece al alfabeto de Autor para una tesis");
                        }
                        index++;
                    }
                    else if (topo.equals("Autor")) {
                        if (getnum(partes[2]) == true) {
                            stack.push("Año");
                        }
                        else{
                            throw new Exception("El texto " + partes[2] + " No pertenece al alfabeto de Año de realizacion para una tesis");
                        }
                        index++;
                    }
                    else if (topo.equals("Año")) {
                        if (getcar(partes[3]) == true) {
                            stack.pop();//Año
                            stack.pop();//Autor
                            stack.pop();//Tesis
                        }
                        else{
                            throw new Exception("El texto " + partes[3] + " No pertenece al alfabeto de Centro Educativo para una tesis");
                        }
                        index++;
                    }
                    else
                    {
                        index++;
                    }
                }break;

                case "*": //recursos electronicos
                {
                    urls = true;
                    String topo = stack.peek();
                    String[] partes = input.split("\\*");
                    if (topo.equals("B")) {
                        stack.push("Recurso Electronico");
                        if (getcar(partes[1]) == true) {
                            stack.push("Autor");
                        }
                        else{
                            throw new Exception("El texto " + partes[1] + " No pertenece al alfabeto de Autor para un Recurso Electronico");
                        }
                        index++;
                    }
                    else if (topo.equals("Autor")) {
                        if (url(partes[2]) == true) {
                            stack.push("URL");
                        }
                        else{
                            throw new Exception("El texto " + partes[2] + " No pertenece al alfabeto de URL para un Recurso Electronico");
                        }
                        index++;
                    }
                    else if (topo.equals("URL")) {
                        if (getcar(partes[3]) == true) {
                            stack.pop();//URL
                            stack.pop();//Autor
                            stack.pop();//Recurso Electronico
                        }
                        else{
                            throw new Exception("El texto " + partes[3] + " No pertenece al alfabeto de Tipo para un Recurso Electronico");
                        }
                        index++;
                    }
                    else
                    {
                        index++;
                    }
                }break;

                default:
                if (comb(lookahead) == true && urls == false) {
                    index++;
                }
                else if (url(lookahead) == true && urls == true) {
                    index++;
                }
                else{
                    throw new Exception("El Simbolo " + lookahead + " No pertenece al alfabeto");
                }
            }
            
        } //Fin del while

        if (stack.peek().equals("B") && stack.size() == 1){
            stack.pop();
        }

        return index;
    }

    private void S2(String input, int index, Stack<String> stack) throws Exception{

        if (index != input.length()){ //No llego al final
            throw new Exception("Simbolos pendientes de consumir cadena no aceptada");
        }

        if (!stack.isEmpty()){
            throw new Exception("Simbolos pendientes de procesar cadena no aceptada");
        }
    }


    public boolean esAceptada(String input){

        boolean cadenaAceptada = false;
        int index = 0;
        Stack<String> stack = new Stack<String>();

        try {
            S0(stack);
            index = S1(input, index, stack);
            S2(input, index, stack);
            cadenaAceptada = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cadenaAceptada;
    }

    private boolean getnum(String numero){
        int cont = 0;
        while (cont < numero.length()) {
            String combis = ""+numero.charAt(cont);
            if ("0123456789".contains(combis)) {
                cont++;
            }
            else {
                return false;
            }
        }
        if (cont == numero.length()) {
            return true;
        }
        else{
            return false;
        }
    }

    private boolean getcar(String letra){
        int cont = 0;
        while (cont < letra.length()) {
            String combis = ""+letra.charAt(cont);
            if ("abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(combis)) {
                cont++;
            }
            else {
                return false;
            }
        }
        if (cont == letra.length()) {
            return true;
        }
        else{
            return false;
        }
    }

    private boolean comb(String combi){
        int cont = 0;
        while (cont < combi.length()) {
            String combis = ""+combi.charAt(cont);
            if ("abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(combis)) {
                cont++;
            }
            else if ("0123456789".contains(combis)){
                cont++;
            } 
            else {
                return false;
            }
        }
        if (cont == combi.length()) {
            return true;
        }
        else{
            return false;
        }
    }

    private boolean url(String combi){
        int cont = 0;
        while (cont < combi.length()) {
            String combis = ""+combi.charAt(cont);
            if ("abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\\.".contains(combis)) {
                cont++;
            }
            else if ("0123456789".contains(combis)){
                cont++;
            } 
            else {
                return false;
            }
        }
        if (cont == combi.length()) {
            return true;
        }
        else{
            return false;
        }
    }
}