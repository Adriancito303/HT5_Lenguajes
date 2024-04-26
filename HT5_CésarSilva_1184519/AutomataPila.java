import java.util.Stack;

public class AutomataPila {
    
    private void S0(Stack<String> stack){
        stack.push("B");
    }

    private int S1(String input, int index, Stack<String> stack) throws Exception{

        while (index < input.length()) {

            String lookahead = "" + input.charAt(index);

            switch (lookahead) {
                case "$":
                {
                    String topo = stack.peek();
                    if (topo.equals("B")) {
                        stack.push("pelicula");
                        index++;
                    }
                    else
                    {
                        index++;
                    }
                }break;

                case "#":
                {
                    String topStack = stack.peek();
                    if (topStack.equals("B")) {
                        stack.push("libro");
                        index++;
                    }
                    else
                    {
                        index++;
                    }
                }break;

                case "%":
                {
                    String topStack = stack.peek();
                    if (topStack.equals("B")) {
                        stack.push("tesis");
                        index++;
                    }
                    else
                    {
                        index++;
                    }
                }break;

                case "*":
                {
                    String topStack = stack.peek();
                    if (topStack.equals("B")) {
                        stack.push("Recurso");
                        index++;
                    }
                    else
                    {
                        index++;
                    }
                }break;

                default:
                    if (stack.size() < 2) {
                        throw new Exception("El simbolo: " + lookahead + " No pertenece al alfabeto");
                    }
                    else{
                        index++;
                    }
            }
            
        } //Fin del while

        //if (stack.peek().equals("B") && stack.size() == 1){
        //    stack.pop();
        //}

        return index;
    }

    private void S2(String input, int index, Stack<String> stack) throws Exception{

        //if (index != input.length()){ //No llego al final
        //    throw new Exception("Simbolos pendientes de consumir cadena no aceptada");
        //}

        //if (!stack.isEmpty()){
        //    throw new Exception("Simbolos pendientes de procesar cadena no aceptada");
        //}
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

}
