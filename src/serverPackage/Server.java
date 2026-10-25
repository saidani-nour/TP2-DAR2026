package serverPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        try {ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Je suis un serveur en attente la connexion d'un client ");
            Socket socket = serverSocket.accept();
            System.out.println("un client est connecté");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String ligne;
            while ((ligne = in.readLine()) != null) {
                if (ligne.equalsIgnoreCase("fin")) {System.out.println("« fin » reçu : fin de la communication");break;}
                System.out.println("Opération reçue : " + ligne);
                String[] parties = ligne.split(" ");
                double a = Double.parseDouble(parties[0]);
                String op = parties[1];
                double b = Double.parseDouble(parties[2]);
                String reponse;
                switch (op) {
                    case "+": reponse = String.valueOf(a + b); break;
                    case "-": reponse = String.valueOf(a - b); break;
                    case "*": reponse = String.valueOf(a * b); break;
                    case "/":
                        reponse = (b == 0) ? "Erreur : division par zéro" : String.valueOf(a / b);break;
                    default:  reponse = "Erreur : opérateur invalide";}
                System.out.println("Résultat envoyé : " + reponse);
                out.println(reponse);}
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
            System.out.println("Serveur fermé");
        } catch (IOException e) { e.printStackTrace();
        }
    }
}