package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author NguyenTuanVu
 */
public class TCPServer {

    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;

    public String data;
    public boolean isConnect = false;

    public TCPServer() {

    }

    public void sentData(String str) {
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(str);
            dataOutputStream.flush();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Không gửi được", "Thông báo", 1);
        }
    }

    public void readData() throws IOException {
        dataInputStream = new DataInputStream(socket.getInputStream());
        data = dataInputStream.readUTF();
    }

    public void connect() {
        try {
            serverSocket = new ServerSocket(2019);
            socket = serverSocket.accept();
            serverSocket.close();
            isConnect = true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Trò chơi được hủy bỏ", "Thông báo", 1);
        }
    }

    public void close() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi", "Thông báo", 1);
        }
    }

}
