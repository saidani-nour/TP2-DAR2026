package serverPackage;
import commonPackage.Operation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        try {ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Je suis un serveur en attente la connexion d'un client ");
            Socket socket = serverSocket.accept();
            System.out.println("un client est connecté");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Operation operation = (Operation) in.readObject();
            double a = operation.getOperande1();
            char op = operation.getOperateur();
            double b = operation.getOperande2();
            System.out.println("Objet reçu : " + a + " " + op + " " + b);
            double resultat;
            switch (op) {
                case '+': resultat = a + b; break;
                case '-': resultat = a - b; break;
                case '*': resultat = a * b; break;
                case '/':
                    resultat = (b == 0) ? Double.NaN : a / b;break;
                default:  resultat = Double.NaN;}
            System.out.println("Résultat envoyé : " + resultat);
            out.writeDouble(resultat);
            out.flush();
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
    }
}