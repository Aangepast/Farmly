package dev.aangepast.farmly.webserver;


import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.utilities.Utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MarketWebServer extends Thread {

    private int port;
    private boolean running;
    private ServerSocket server;

    public MarketWebServer(int port){
        this.port = port;
    }

    public void initServer(Main plugin){
        // Check if it's already running or not
        if(running){return;}
        try{
            // Server setup
            server = new ServerSocket(port);

            new Runnable(){
                @Override
                public void run(){
                    while(true){
                        try{
                            // Accept incoming socket connections
                            Socket socket = server.accept();
                            plugin.getLogger().warning(socket.getRemoteSocketAddress().toString() + " connecting...");

                            //Webpage display
                            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                            while(socket.isConnected()){
                                write.write(plugin.getDataFolder().getAbsolutePath() + "/webserver/web");
                            }

                            // Close socket connection
                            socket.close();
                            plugin.getLogger().info("Socket connection closed");


                        } catch (IOException e){
                            running=false;
                            e.printStackTrace();
                        }
                    }
                }
            };
            running = true;

        } catch(IOException e) {
            e.printStackTrace();
            running=false;
        }
    }

    public void stopServer(){
        try{
            server.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
