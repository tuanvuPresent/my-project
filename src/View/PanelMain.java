/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Computer;
import static Controller.MyConst.*;
import Controller.Player;
import Controller.StateGame;
import Controller.Computer;
import Controller.Player;
import TCP.TCPClient;
import TCP.TCPServer;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.html.parser.Entity;

/**
 *
 *
 * @author NguyenTuanVu
 */
public class PanelMain extends JPanel implements ActionListener {

    //Button drawChess
    private JButton btn[][] = new JButton[WIDTH_MAX][HEIGHT_MAX];       //20x20
    //cac thanh phan khung Chat
    private JTextArea txtAreaChat;                                      //bang chat
    private JTextField txtFieldChat;                                    //Chat   
    private JTextField txtField;
    private JScrollPane scrollPanel;
    //thanh phan cua Controller
    private JRadioButton rDiTruoc = new JRadioButton("Đi trước");
    private JRadioButton rDiSau = new JRadioButton("Đi sau");
    private Choice myChoiceLevel = new Choice();
    private JTextField txtFieldIP;
    private JButton btnStart, btnCreat, btnShow, btnBack;
    //panel size= 1060x630
    private JPanel pnlChess, pnlChat, pnlLAN, pnlSingle;
    private JTextField txtFieldPlayer1, txtFieldPlayer2;
    private JTextField tfCurrent;
    private JTextField tfTiSo;

    //--------------------------view--------------------------------------------
    public PanelMain() {
        this.setLayout(null);
        drawChess();
        addChat();
        showInfo();

        this.add(pnlChess);
        this.add(pnlChat);
    }

    private void drawChess() {
        pnlChess = new JPanel(null);
        pnlChess.setBounds(0, 0, 600, 600);
//        panel
        //add button
        for (int i = 0; i < WIDTH_MAX; i++) {
            for (int j = 0; j < HEIGHT_MAX; j++) {
                btn[i][j] = new JButton();
                btn[i][j].setBounds(0 + 30 * i, 0 + 30 * j, 30, 30);
                btn[i][j].setBackground(Color.WHITE);
                data[i][j] = 0;
                pnlChess.add(btn[i][j]);
                btn[i][j].addActionListener(this);
            }
        }
    }

    private void addChat() {
        pnlChat = new JPanel(null);
        pnlChat.setBounds(605, 380, 440, 300);
        //add txtAreaChat              
        txtAreaChat = new JTextArea(1, 10);
        txtAreaChat.setEditable(false);
        //add txtAreaChat vao scrollPanel
        scrollPanel = new JScrollPane(txtAreaChat);
        scrollPanel.setBounds(0, 0, 440, 190);
        pnlChat.add(scrollPanel);
        //add chat
        txtField = new JTextField("Chat");
        txtField.setBounds(0, 190, 40, 30);
        txtField.setEditable(false);
        pnlChat.add(txtField);
        //add txtFieldChat
        txtFieldChat = new JTextField();
        txtFieldChat.setBounds(40, 190, 400, 30);
        pnlChat.add(txtFieldChat);

        txtFieldChat.addActionListener(this);
    }

    private void showInfo() {
        //add Image game
        JLabel lbImage = new JLabel((new javax.swing.ImageIcon(getClass().getResource("/Image/GameCaro.png"))));
        lbImage.setBounds(605, 0, 440, 220);
        this.add(lbImage);
        //add thong tin nguoi choi
        txtFieldPlayer1 = new JTextField();
        txtFieldPlayer1.setBounds(605, 350, 190, 30);
        txtFieldPlayer1.setBackground(Color.white);
        txtFieldPlayer1.setEditable(false);
        this.add(txtFieldPlayer1);

        txtFieldPlayer2 = new JTextField();
        txtFieldPlayer2.setBounds(855, 350, 190, 30);
        txtFieldPlayer2.setBackground(Color.white);
        txtFieldPlayer2.setEditable(false);
        this.add(txtFieldPlayer2);

        tfTiSo = new JTextField();
        tfTiSo.setBounds(800, 350, 50, 30);
        tfTiSo.setEditable(false);
        this.add(tfTiSo);

        tfCurrent = new JTextField();
        tfCurrent.setBounds(700, 320, 240, 30);
        tfCurrent.setEditable(false);
        this.add(tfCurrent);
        //add  Controller
        ctlSingle();
        ctlLanGame();
    }

    private void ctlLanGame() {
        //------------------add LAN GAME--------------------------------
        pnlLAN = new JPanel(null);
        pnlLAN.setBounds(605, 220, 440, 50);
        pnlLAN.setBorder(javax.swing.BorderFactory.createTitledBorder("MultiPlayer"));

        JLabel lbIP = new JLabel("IP:");
        lbIP.setBounds(5, 15, 30, 30);
        pnlLAN.add(lbIP);

        txtFieldIP = new JTextField();
        txtFieldIP.setBounds(30, 15, 200, 30);
        txtFieldIP.setBackground(Color.white);
        pnlLAN.add(txtFieldIP);

        btnCreat = new JButton("Creat");
        btnCreat.setBounds(230, 15, 65, 30);
        pnlLAN.add(btnCreat);

        btnShow = new JButton("Show");
        btnShow.setBounds(295, 15, 70, 30);
        pnlLAN.add(btnShow);

        btnBack = new JButton("Back");
        btnBack.setBounds(365, 15, 65, 30);
        pnlLAN.add(btnBack);

        btnCreat.addActionListener(this);
        btnShow.addActionListener(this);
        btnBack.addActionListener(this);
        this.add(pnlLAN);
    }

    private void ctlSingle() {
        //------------------add LAN GAME--------------------------------
        pnlSingle = new JPanel(null);
        pnlSingle.setBounds(605, 270, 440, 50);
        pnlSingle.setBorder(javax.swing.BorderFactory.createTitledBorder("Player vs Computer"));
        //add lbLevel
        JLabel lbLevel = new JLabel("Level");
        lbLevel.setBounds(5, 15, 30, 30);
        pnlSingle.add(lbLevel);
        //add Choice     
        myChoiceLevel.addItem("Normal");
        myChoiceLevel.addItem("Hard");
        myChoiceLevel.setBounds(40, 20, 100, 30);
        pnlSingle.add(myChoiceLevel);
        //add RadioButton
        // Tạo một nhóm để chứa 2 radio (đi trước & Đi sau).
        rDiTruoc.setBounds(150, 15, 90, 30);
        rDiSau.setBounds(250, 15, 90, 30);
        rDiTruoc.setSelected(true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rDiTruoc);
        bg.add(rDiSau);

        pnlSingle.add(rDiTruoc);
        pnlSingle.add(rDiSau);
        //add Button
        btnStart = new JButton("Start");
        btnStart.setBounds(350, 15, 80, 30);
        pnlSingle.add(btnStart);

        btnStart.addActionListener(this);
        this.add(pnlSingle);
    }

    //------------------------------------event--------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        //envet btn
        for (int i = 0; i < HEIGHT_MAX; i++) {
            for (int j = 0; j < WIDTH_MAX; j++) {
                if (e.getSource() == btn[i][j]) {
                    //lay toa do button
                    point.x = i;
                    point.y = j;
                    switch (cheDoChoi) {
                        case VS_COMPUTER:
                            vsComputer();
                            break;
                        case VS_PLAYER:
                            vsPlayer();
                            break;
                    }
                }
            }
        }
        //-------------------------------event btn Control------------------------------
        if (e.getSource() == btnStart) {
            btnStart();
        }

        if (e.getSource() == btnBack) {
            btnBack();
        }

        if (e.getSource() == btnCreat) {
            btnCreat();
        }

        if (e.getSource() == btnShow) {
            btnShow();
        }
        //event Chat
        if (e.getSource() == txtFieldChat) {
            eventChat();
        }
    }
    //------------------------------------action-------------------------------

    private void init(char CheDo) {
        /*
         B1: Nhập tên
         B2: Khởi tạo người chơi
         B3: Vẽ lại bàn cờ
         */
        tfCurrent.setText("");
        tfTiSo.setText("");
        txtAreaChat.setText("");
        // Bước 1
        String sTen = JOptionPane.showInputDialog(
                null,
                "Nhập tên của bạn",
                "Nhập thông tin",
                JOptionPane.QUESTION_MESSAGE
        );
        //Bước 2
        this.cheDoChoi = CheDo;
        switch (cheDoChoi) {
            case VS_COMPUTER:
                you = new Player(sTen, "X", 0);
                computer = new Computer(0, myChoiceLevel.getSelectedIndex());

                txtFieldPlayer1.setText("You: " + you.ten);
                txtFieldPlayer2.setText("Đối thủ: Computer");
                tfTiSo.setText(you.score + "  :  " + computer.score);
                break;
            case VS_PLAYER:
                you = new Player(sTen, "X", 0);
                txtFieldPlayer1.setText("You: " + you.ten);
                break;
        }
        //Bước 3
        reDrawChess();
    }

    private void reDrawChess() {
        pnlChess.setVisible(false);                     //Tắt bàn cờ hiện tại
        drawChess();                                    //Vẽ lại bàn cờ 
        this.add(pnlChess);                              //Thêm vào panel chính
        this.revalidate();                               //Update
        this.repaint();                                  //Vẽ lại
        //nếu player đi sau thì khởi tạo luôn computer đánh ô (9,9 )
        if (!rDiTruoc.isSelected() && cheDoChoi == VS_COMPUTER) {
            btn[9][9].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/P_O.png")));
            data[9][9] = 2;
        }
    }

    private void btnStart() {
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn chơi mới không", "thông báo", 2);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }
        //khởi tạo chế độ chơi với máy
        init(VS_COMPUTER);
    }

    private void btnCreat() {
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn chơi mới không", "thông báo", 2);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }
        //khởi tạo
        server = new TCPServer();
        isServer = true;
        flag = true; //đi trước
        init(VS_PLAYER);
        threadReadData();
        //Lấy địa chỉ IP
        try {
            InetAddress addr = InetAddress.getLocalHost();
            txtFieldIP.setText(addr.getHostAddress());
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Không lấy được IP host", "Thông báo", 1);
        }
        //ẩn các button
        btnCreat.setEnabled(false);
        btnShow.setEnabled(false);
        btnStart.setEnabled(false);
    }

    private void btnShow() {
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn chơi mới không", "thông báo", 2);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }
        //kết nối đến server
        client = new TCPClient();
        isServer = false;
        init(VS_PLAYER);
        client.connect();
        if (client.isConnect) {
            //gửi tên cho người chơi
            client.sentData(TITLE_NAME + you.ten);
            tfCurrent.setText("Đợi đối thủ đánh...................");
            txtAreaChat.append("Bạn đã vào phòng\n");
            //
            isPlay = true;                  //được chơi
            flag = false;                     //đi sau
            threadReadData();
            //ẩn các nút đi
            btnCreat.setEnabled(false);
            btnShow.setEnabled(false);
            btnStart.setEnabled(false);
        }
    }

    private void btnBack() {
        /*
        B1: Gửi thông báo cho đối thủ là mình đã thoát
        B2: Đóng các kết nối lại
        B3: Hiện các button lên
         */
        // Bước 1+ 2
        if (server != null && isServer) {
            if (server.isConnect) {
                server.sentData(TITLE_THOAT + "Đối thủ đã thoát");
            }
            server.close();
            server = null;
        } else if (client != null) {
            if (client.isConnect) {
                client.sentData(TITLE_THOAT + "Đối thủ đã thoát");
            }
            client.close();
            client = null;
        }
        //Dừng thread đọc dữ liệu
        if (threadReadData != null) {
            threadReadData.stop();
        }
        //Bước 3
        tfCurrent.setText("");
        tfTiSo.setText("");
        txtAreaChat.setText("");
        btnCreat.setEnabled(true);
        btnShow.setEnabled(true);
        btnStart.setEnabled(true);
    }

    private void eventChat() {
        /*
         B1: Hiện text lên khung Chat
         B2: Gửi Text đi qua TCP nếu là ở chế độ chơi với người
         */
        //Bước 1
        if (you == null) {
            return;
        }
        String str = txtFieldChat.getText();
        if (!str.equals("")) {
            txtFieldChat.setText(null);
            txtAreaChat.append(you.ten + ": " + str + "\n");
            txtAreaChat.setSelectionEnd(txtAreaChat.getText().length());        //xuống cuối txtChat
            //Bước 2
            if (cheDoChoi == VS_PLAYER && isPlay) {
                if (isServer) {
                    server.sentData(TITLE_CHAT + you.ten + ": " + str);
                } else {
                    client.sentData(TITLE_CHAT + you.ten + ": " + str);
                }
            }
        }
    }

    private void vsComputer() {
        /*
         B1: Người chơi đánh 
         B2: Kiểm tra trạng thái game
         B3: Computer đánh
         B4: Kiểm tra trạng thái game
         */
        //Kiểm tra xem người chơi có đánh vào ô đã đánh rồi không
        if (data[point.x][point.y] != 0) {
            return;
        }
        //---------------------------Bước 1-------------------------------------
        you.Play(point, btn);
        data[point.x][point.y] = 1;
        //---------------------------Bước 2-------------------------------------
        stateGame = check.stateGame(point, data);
        if (stateGame == STATE_THANG || stateGame == STATE_HOA) {
            endGame(stateGame, you.ten);
            return;
        }
        //---------------------------Bước 3-------------------------------------
        switch (computer.level) {
            //computer level normal
            case 0: {
                point = computer.stepCom(data);          // computer tim nuoc di
                computer.play(point, btn);
                data[point.x][point.y] = 2;
                break;
            }
            //computer level hard
            case 1: {
                break;
            }
        }
        //------------------------Bước 4----------------------------------------
        stateGame = check.stateGame(point, data);
        endGame(stateGame, "Computer");
    }

    private void vsPlayer() {
        /*
         B1: Người chơi đánh và gửi nước cờ đi
         B2: Kiểm tra trạng thái game
         B3: Đợi đối thủ chơi
         */
        //Kiểm tra xem người chơi có đánh vào ô đã đánh rồi không
        if (!isPlay || data[point.x][point.y] != 0 || !flag) {
            return;
        }
        //Nếu là server thì chạy không thì chạy client   
        //Bước 1: Người chơi đánh và gửi nước cờ đi
        you.Play(point, btn);
        data[point.x][point.y] = 1;
        flag = !flag;
        if (isServer) {
            server.sentData(TITLE_NUOC_DI + " " + point.x + " " + point.y);
        } else {
            client.sentData(TITLE_NUOC_DI + " " + point.x + " " + point.y);
        }
        //Bước 2: Kiểm tra trạng thái game
        stateGame = check.stateGame(point, data);
        if (stateGame == STATE_THANG || stateGame == STATE_HOA) {
            endGame(stateGame, you.ten);
        }
        //Bước 3: Đợi đối thủ chơi
        tfCurrent.setText("Đang chờ đối thủ.............");
    }

    private void stepCompetitor(Point temp) {
        /*
         B1: Đối thủ đánh( chỉ chạy khị đánh LAN Game)
         B2: Kiểm tra trạng thái game
         */
        //Bước 1: Đối thủ đánh       
        doiThu.Play(temp, btn);
        data[point.x][point.y] = 2;
        flag = !flag;
        //Bước 2: Kiểm tra trạng thái game
        stateGame = check.stateGame(temp, data);
        switch (stateGame) {
            case STATE_HOA:
                endGame(STATE_HOA, you.ten);
                break;
            case STATE_THANG:
                endGame(STATE_THUA, you.ten);
                break;
        }
    }

    private void processData(String str) {
        /*
         title ='1': textChat
         title ='2': nuoc di cua doi thu
         title ='3': thong tin doi thu
         */
        switch (str.charAt(0)) {
            case TITLE_CHAT:
                txtAreaChat.append(str.substring(1) + "\n");
                break;

            case TITLE_NUOC_DI:
                //Xử lí data đọc được
                String[] words = str.split("\\s");
                point.x = Integer.parseInt(words[1]);
                point.y = Integer.parseInt(words[2]);

                stepCompetitor(point);
                //Thông báo chuyển lượt
                tfCurrent.setText("Đến lượt bạn................");
                break;

            case TITLE_NAME:
                doiThu = new Player(str.substring(1), "O", 0);
                txtFieldPlayer2.setText("Đối thủ: " + str.substring(1));
                break;
            case TITLE_THOAT:
                isPlay = false;
                tfCurrent.setText(str.substring(1));
                threadReadData.stop();                  //không đọc dữ liệu nữa            
                server = null;
                client = null;
                break;
        }
    }

    private void endGame(int stateGame, String namePlayer) {
        /*
         B1: Nếu kết thúc game ==> thông báo trạng thái game + tính điểm cho người chơi
         B2: Vẽ lại bàn cờ
         */
        switch (stateGame) {
            case STATE_THANG:
                //Bước 1
                JOptionPane.showMessageDialog(null, "Chúc mừng " + namePlayer + " win", "Chúc mừng", 1);
                if (cheDoChoi == VS_COMPUTER) {
                    if (namePlayer == you.ten) {
                        you.score++;
                    } else {
                        computer.score++;
                    }
                    tfTiSo.setText(you.score + "  :  " + computer.score);
                } else {
                    you.score++;
                    tfTiSo.setText(you.score + "  :  " + doiThu.score);
                }
//                Bươc 2
                reDrawChess();
                break;
            case STATE_HOA:
                //Bước 1
                JOptionPane.showMessageDialog(null, "hai bên HÒA", "Thông báo", 1);
                //Bước 2
                reDrawChess();
                break;

            case STATE_THUA:        //chỉ có chế độ chơi 2 mới có
                //Bước 1
                JOptionPane.showMessageDialog(null, "Bạn " + you.ten + " LOST ", "Thông báo", 1);
                doiThu.score++;
                tfTiSo.setText(you.score + "  :  " + doiThu.score);
                //Bước 2: Vẽ lại bàn cờ
                reDrawChess();
                break;
            case STATE_NONE:
                break;
        }
    }

    private void threadReadData() {
        threadReadData = new Thread(new Runnable() {

            @Override
            public void run() {
                if (isServer) {
                    //server đợi client kết nối tới
                    txtAreaChat.append("Đang chờ đối thủ vào phòng........\n");
                    server.connect();
                    if (server.isConnect) {
                        txtAreaChat.append("Kết nối thành công\n Đối thủ đã vào phòng\n");
                        tfCurrent.setText("Bạn đi trước.......");
                        server.sentData(TITLE_NAME + you.ten);
                        isPlay = true;
                    }
                    //Đọc dữ liệu
                    while (server.isConnect) {
                        try {
                            server.readData();
                        } catch (IOException ex) {
                            tfCurrent.setText("Đối thủ đã thoát");
                            server.close();
                            server = null;
                            isPlay = false;
                            break;
                        }
                        processData(server.data);
                    }
                    //nếu là client thì chạy 
                } else {
                    while (client.isConnect) {
                        try {
                            client.readData();
                        } catch (IOException ex) {
                            tfCurrent.setText("Đối thủ đã thoát");
                            client.close();
                            client = null;
                            isPlay = false;
                            break;
                        }
                        processData(client.data);
                    }
                }
            }
        });
        threadReadData.start();
    }

    private StateGame check = new StateGame();
    private Player you = null;
    private Player doiThu = null;
    private Computer computer = null;
    private TCPServer server = null;
    private TCPClient client = null;
    private boolean isServer = false;                             //cho biết là server hay client  
    private boolean isPlay = false;                               //cho phép chơi ở chế độ đánh với người
    //
    private boolean flag = true;                                  //flag lượt chơi
    private Point point = new Point();                            //Tọa độ Button
    private char data[][] = new char[WIDTH_MAX][HEIGHT_MAX];      //Arry check win
    private int stateGame;                                        // trạng thái game  
    private char cheDoChoi;                                       //chế độ chơi  
    private Thread threadReadData;                                //thread để đọc dữ liệu server-client  
}
