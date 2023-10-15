package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void  main(String[] args){
        // Le serveur commence ici
        try(ServerSocket ss = new ServerSocket(1234))
        {
            // Attendez qu'un client se connecte
            Socket clientSocket = ss.accept();

            // Initialisation des flux d'entrée et de sortie
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            // Préparez les outils pour lire les données du client
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader br = new BufferedReader(isr);

            // Lire les numéros et l'opération envoyés par le client
            int nb1 = Integer.parseInt(br.readLine());
            int nb2 = Integer.parseInt(br.readLine());
            String op = br.readLine();
            int res= 0;

            // Effectuer l'opération appropriée en fonction de l'opérateur
            switch (op){
                case "+" : res = nb1 + nb2 ;break;
                case "-" : res = nb1 - nb2 ;break;
                case "*" : res = nb1 * nb2 ;break;
                case "/" : res = nb1 / nb2 ;break;
            }

            // Préparez un outil pour écrire la réponse au client
            PrintWriter pw = new PrintWriter(output, true);
            pw.println(res);

        } catch (IOException e) {
            // Gestion des erreurs
            System.out.println("here");
            throw new RuntimeException(e);
        } ;
    }
}
