package clientPackage;
import commonPackage.Operation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        try {System.out.println("Je suis un client pas encore connecté…");
            Socket socket = new Socket("127.0.0.1", 1234);
            System.out.println("je suis un client connecté");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
            System.out.print("Premier opérande : ");
            double a = sc.nextDouble();
            System.out.print("Opérateur (+, -, *, /) : ");
            char op = sc.next().charAt(0);
            System.out.print("Deuxième opérande : ");
            double b = sc.nextDouble();
            Operation operation = new Operation(a, op, b);
            out.writeObject(operation);
            out.flush();
            double resultat = in.readDouble();
            if (Double.isNaN(resultat)) {
                System.out.println("Opération impossible (division par zéro ou opérateur invalide)");} else {
                System.out.println(a + " " + op + " " + b + " = " + resultat);}
            in.close();
            out.close();
            socket.close();} catch (IOException e) {e.printStackTrace();
        }
    }
}