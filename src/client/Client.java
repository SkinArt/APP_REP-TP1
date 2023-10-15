package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Client {
    public static void main(String[] args){
        try
        {
            // Définir l'adresse IP du serveur
            InetAddress IA = InetAddress.getByName("10.26.14.146");

            // Définir l'adresse IP et le port du serveur
            InetSocketAddress ISA = new InetSocketAddress(IA,1234);

            // Créer un socket client et se connecter au serveur
            Socket client = new Socket();
            client.connect(ISA);

            // Initialiser les flux d'entrée et de sortie pour communiquer avec le serveur
            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader br = new BufferedReader(isr);

            // Afficher un message de confirmation de la connexion
            System.out.println("connected");

            // Créer un scanner pour obtenir les entrées de l'utilisateur
            Scanner scanner = new Scanner(System.in);

            // Demander à l'utilisateur d'entrer deux entiers
            System.out.print("Please enter an integer1: ");
            String nb1 = scanner.nextLine();
            System.out.print("Please enter an integer2: ");
            String nb2 = scanner.nextLine();

            String op ;
            do{
                // Demander à l'utilisateur d'entrer un opérateur valide
                System.out.print("Please enter an operator (+, -, *, /): ");
                op = scanner.nextLine();
            }while (!(op.equals("+")) && !(op.equals("-")) && !(op.equals("*")) && !(op.equals("/")));

            // Initialiser un PrintWriter pour envoyer les données au serveur
            PrintWriter pw = new PrintWriter(output, true);

            // Envoyer les nombres et l'opérateur au serveur
            pw.println(nb1);
            pw.println(nb2);
            pw.println(op);

            // Recevoir et afficher la réponse du serveur
            System.out.println("Received result from server = "+ br.readLine());

        }catch(Exception e){
            // Gestion des erreurs
            System.out.println("Client here");
            throw new RuntimeException(e);
        }
    }
}
