package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi(){
        try {
            System.out.println("1 server partito in esecuzione");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la l'istanza del server");
            System.exit(1);
            // TODO: handle exception
        }
        return client;
    }

    public void comunica(){
        try {
            System.out.println("3- scrivi una frase e la trasformo in maiuscolo");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("6- Stringa ricevuta "+ stringaRicevuta);

            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("7- invio la stringa modificata al client");
            outVersoClient.writeBytes(stringaModificata+'\n');
            System.out.println("9- fine elaborazione");
            client.close();
        } catch (Exception e) {
            System.out.println("errore");
        }
    }
    public static void main(){
        Server servente =  new Server();
        servente.attendi();
        servente.comunica();
    }
}
