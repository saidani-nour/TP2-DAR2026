package clientPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    private static final String FORMAT = "-?\\d+(\\.\\d+)? [+\\-*/] -?\\d+(\\.\\d+)?";
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté…");
            Socket socket = new Socket("127.0.0.1", 1234);
            System.out.println("je suis un client connecté");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
            String saisie;
            do {System.out.print("Entrez une opération (ex. 55 * 25) ou « fin » : ");
                saisie = sc.nextLine().trim();
                if (saisie.equalsIgnoreCase("fin")) {out.println("fin");
                } else if (!saisie.matches(FORMAT)) {
                    System.out.println("Syntaxe incorrecte : écrivez  opérande opérateur opérande  (avec des espaces)");} else {
                    out.println(saisie);
                    String resultat = in.readLine();
                    System.out.println("Résultat : " + resultat);}
            } while (!saisie.equalsIgnoreCase("fin"));
            System.out.println("Fin de la communication");
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {e.printStackTrace();
        }
    }
}