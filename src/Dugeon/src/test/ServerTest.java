package test;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.charactermodel.player.LoadNewPlayer;
import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.webserver.AppServer;
import com.rmit.sea.webserver.model.Score;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import junit.framework.TestCase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Steve
 */
public class ServerTest extends TestCase {

    public void testServerReceiveData() throws IOException {

        Socket socket = null;
        ObjectOutputStream outputStream = null;
        try {
            //Start Server
            AppServer as = new AppServer();
            as.start();


            System.out.println("Sending");
            //Open Socket
            socket = new Socket(Constant.SERVER_ADDRESS, Constant.APP_SERVER_PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeObject(new Score(new LoadNewPlayer("Cuong","123").getPlayer(new Coordinate(2, 2))));

            outputStream.flush();
            outputStream.close();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }



        }

    }
}
