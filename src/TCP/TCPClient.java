package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author NguyenTuanVu
 */
public class TCPClient {

    private Socket socket = null;                           // Tạo socket kết nối
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;

    public String data = null;
    public boolean isConnect = false;

    public TCPClient() {

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
            String ip = JOptionPane.showInputDialog(null, "Nhập IP");
            socket = new Socket(ip, 2019); // Kết nối dựa trên ip vừa nhập và cổng mặc định 
            JOptionPane.showMessageDialog(null, "Kết nối thành công", "Thông báo", 1);
            isConnect = true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy", "Thông báo", 1);
        }
    }

    public void close() {
        try {
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
