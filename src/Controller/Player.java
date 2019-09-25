/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Color;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author NguyenTuanVu
 */
public class Player {

    private Point pointTemp = null;
    public String ten;
    public ImageIcon image;
    public int score;

    public Player() {

    }

    public Player(String ten, String quanCo, int score) {
        this.ten = ten;
        this.score = score;
        if (quanCo.equals("x") || quanCo.equals("X")) {
            this.image = new ImageIcon(getClass().getResource("/Image/P_X.png"));
        } else {
            this.image = new ImageIcon(getClass().getResource("/Image/P_O.png"));
        }
    }

    public void Play(Point point, JButton[][] bt) {
        //lưu quân cờ trước
        if (pointTemp != null) {
            bt[pointTemp.x][pointTemp.y].setBackground(Color.white);
        }
        //đánh cờ
        bt[point.x][point.y].setIcon(this.image);
        bt[point.x][point.y].setBackground(Color.black);

        pointTemp = new Point(point.x, point.y);
    }
}
