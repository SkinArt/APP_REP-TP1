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

            InetAddress IA = InetAddress.getByName("10.26.14.146");
            InetSocketAddress ISA = new InetSocketAddress(IA,1234);

            Socket client = new Socket();
            client.connect(ISA);

            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader br = new BufferedReader(isr);

            System.out.println("connected");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Please enter an integer1: ");
            String nb1 = scanner.nextLine();
            System.out.print("Please enter an integer2: ");
            String nb2 = scanner.nextLine();

            String op ;
            do{
                System.out.print("Please enter an operator: ");
                op = scanner.nextLine();
            }while (!(op.equals("+")) && !(op.equals("-")) && !(op.equals("*")) && !(op.equals("/")));

            PrintWriter pw = new PrintWriter(output, true);

            pw.println(nb1);
            pw.println(nb2);
            pw.println(op);



            System.out.println("Recieved result from server = "+ br.readLine());

        }catch(Exception e){
            System.out.println("CLient here");
            throw new RuntimeException(e);
        }
    }
}