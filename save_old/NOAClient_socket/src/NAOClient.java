/**
 * Created by kimsavinfo on 11/11/14.
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NAOClient
{
    public static void main(String[] args)
    {
        Socket socket;

        try
        {

            socket = new Socket("192.168.1.29",1234);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            String sendMessage = "123#say#Hello World!$";

            //String sendMessage = number + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message envoyé au serveur : "+sendMessage);
            socket.close();

        }catch (UnknownHostException e) {

            e.printStackTrace();
        }catch (IOException e) {

            e.printStackTrace();
        }

    }



}
