package cl.usm.lp20162;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class ConnectionManager {
    
    public String ipAddress;

    public ConnectionManager() {
        setupAdresses();
        createServer();
        createSendFunction();
    }
    
    private void setupAdresses(){
        List<String> addresses = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface ni : Collections.list(interfaces)){
                for(InetAddress address : Collections.list(ni.getInetAddresses()))
                {
                    if(address instanceof Inet4Address){
                        addresses.add(address.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        
        // Print the contents of our array to a string.  Yeah, should have used StringBuilder
        ipAddress = "";
        for(String str : addresses){
            ipAddress = ipAddress.concat(str + "\n");
        }
    }
    
    private void createServer(){
        // Now we create a thread that will listen for incoming socket connections
        new Thread(new Runnable(){

            @Override
            public void run() {
                ServerSocketHints serverSocketHint = new ServerSocketHints();
                // 0 means no timeout.  Probably not the greatest idea in production!
                serverSocketHint.acceptTimeout = 0;
                
                // Create the socket server using TCP protocol and listening on 9021
                // Only one app can listen to a port at a time, keep in mind many ports are reserved
                // especially in the lower numbers ( like 21, 80, etc )
                ServerSocket serverSocket = Gdx.net.newServerSocket(Protocol.TCP, 9021, serverSocketHint);
                
                // Loop forever
                while(true){
                    // Create a socket
                    Socket socket = serverSocket.accept(null);
                    
                    // Read data from the socket into a BufferedReader
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                    
                    try {
                        // Read to the next newline (\n) and display that text on labelMessage
                        TestTarea2.labelMessage.setText(buffer.readLine());    
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start(); // And, start the thread running
    }
    
    private void createSendFunction(){
        // Wire up a click listener to our button
        TestTarea2.button.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
                
                // When the button is clicked, get the message text or create a default string value
                String textToSend = "";
                if(TestTarea2.textMessage.getText().length() == 0)
                    textToSend = "Doesn't say much but likes clicking buttons\n";
                else
                    textToSend = TestTarea2.textMessage.getText() + ("\n"); // Brute for a newline so readline gets a line
                
                SocketHints socketHints = new SocketHints();
                // Socket will time our in 4 seconds
                socketHints.connectTimeout = 4000;
                //create the socket and connect to the server entered in the text box ( x.x.x.x format ) on port 9021
                Socket socket = Gdx.net.newClientSocket(Protocol.TCP, TestTarea2.textIPAddress.getText(), 9021, socketHints);
                try {
                    // write our entered message to the stream
                    socket.getOutputStream().write(textToSend.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    
}
