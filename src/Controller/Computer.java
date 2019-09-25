/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MyConst.HEIGHT_MAX;
import static Controller.MyConst.WIDTH_MAX;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JButton;

/**
 *
 * @author NguyenTuanVu
 */
public class Computer {

    //    computer
//    public int[] Defend_core = {0, 1, 8, 64, 512, 4096};
//    public int[] Attack_core = {0, 2, 9, 81, 729, 6561};
    //arr score
    private int[] Defend_core = {0, 1, 7, 37, 217, 3472};                //x6=3x2
    private int[] Attack_core = {0, 2, 12, 72, 648, 16480};              //x6
    private Point pointTemp = null;                                      //Lưu tọa độ trước của computer   

    public int score;
    public int level;

    public Computer(int Core, int level) {
        this.score = Core;
        this.level = level;
    }

    private int scoreRow(Point point, char data[][], int player) {//biến space không có tác dụng gí
        int scoreRow = 0;
        int count = 1;
        int quanDich = 0;
        int space = 0;
        //row
        int i = point.x - 1;
        while (i >= 0 && space < 2) {
            if (data[i][point.y] == player) {
                count++;
                i--;
            } else if (data[i][point.y] == 0) {
                space++;
            } else {
                quanDich++;
                break;
            }
        }
        space = 0;
        i = point.x + 1;
        while (i < WIDTH_MAX && space < 2) {
            if (data[i][point.y] == player) {
                count++;
                i++;
            } else if (data[i][point.y] == 0) {
                space++;
            } else {
                quanDich++;
                break;
            }
        }
        //score
        if (quanDich == 2) {
            return 0;
        }
        if (count >= 5) {
            count = 5;
        }
        if (player == 2) {
            scoreRow = scoreRow + Attack_core[count] - 4 * Attack_core[quanDich * (count - 1)];
        } else {
            scoreRow = scoreRow + Defend_core[count] - 4 * Defend_core[quanDich * (count - 1)];
        }
        return scoreRow;
    }

    private int scoreColumn(Point point, char data[][], int player) {
        int CoreColumn = 0;
        int count = 1;
        int quanDich = 0;
        int space = 0;
        //column
        int j = point.y - 1;
        while (j >= 0 && space < 2) {
            if (data[point.x][j] == player) {
                count++;
                j--;
            } else if (data[point.x][j] == 0) {
                space++;
//                j--;
            } else {
                quanDich++;
                break;
            }
        }
        space = 0;
        j = point.y + 1;
        while (j < HEIGHT_MAX && space < 2) {
            if (data[point.x][j] == player) {
                count++;
                j++;
            } else if (data[point.x][j] == 0) {
                space++;
//                j++;
            } else {
                quanDich++;
                break;
            }
        }
        //Core
        if (quanDich == 2) {
            return 0;
        }
        if (count >= 5) {
            count = 5;
        }
        if (player == 2) {
            CoreColumn = CoreColumn + Attack_core[count] - 4 * Attack_core[quanDich * (count - 1)];
        } else {
            CoreColumn = CoreColumn + Defend_core[count] - 4 * Defend_core[quanDich * (count - 1)];
        }
        return CoreColumn;
    }

    private int scoreDiagonalC(Point point, char data[][], int player) {
        int scoreDiagonalC = 0;
        int count = 1;
        int quanDich = 0;
        int space = 0;
        //cheo chinh
        int i = point.x + 1;
        int j = point.y + 1;
        while ((i < WIDTH_MAX && j < HEIGHT_MAX && space < 2)) {
            if (data[i][j] == player) {
                count++;
                i++;
                j++;
            } else if (data[i][j] == 0) {
                space++;
            } else {
                quanDich++;
                break;
            }
        }
        i = point.x - 1;
        j = point.y - 1;
        space = 0;
        while ((i >= 0 && j >= 0 && space < 2)) {
            if (data[i][j] == player) {
                count++;
                i--;
                j--;
            } else if (data[i][j] == 0) {
                space++;
            } else {
                quanDich++;
                break;
            }
        }
        //core
        if (quanDich == 2) {
            return 0;
        }
        if (count >= 5) {
            count = 5;
        }
        if (player == 2) {
            scoreDiagonalC = scoreDiagonalC + Attack_core[count] - 4 * Attack_core[quanDich * (count - 1)];
        } else {
            scoreDiagonalC = scoreDiagonalC + Defend_core[count] - 4 * Defend_core[quanDich * (count - 1)];
        }
        return scoreDiagonalC;
    }

    private int scoreDiagonalP(Point point, char data[][], int player) {
        int scoreDiagonalP = 0;
        int count = 1;
        int quanDich = 0;
        int space = 0;
        //cheo phu
        int i = point.x + 1;
        int j = point.y - 1;
        while ((i < WIDTH_MAX && j >= 0 && space < 2)) {
            if (data[i][j] == player) {
                count++;
                i++;
                j--;
            } else if (data[i][j] == 0) {
                space++;
            } else {
                quanDich++;
                break;
            }
        }
        space = 0;
        i = point.x - 1;
        j = point.y + 1;
        while ((i >= 0 && j < HEIGHT_MAX && space < 2)) {
            if (data[i][j] == player) {
                count++;
                i--;
                j++;
            } else if (data[i][j] == 0) {
                space++;
            } else {
                quanDich++;
                break;
            }
        }
        //core
        if (quanDich == 2) {
            return 0;
        }
        if (count >= 5) {
            count = 5;
        }
        if (player == 2) {
            scoreDiagonalP = scoreDiagonalP + Attack_core[count] - 4 * Attack_core[quanDich * (count - 1)];
        } else {
            scoreDiagonalP = scoreDiagonalP + Defend_core[count] - 4 * Defend_core[quanDich * (count - 1)];
        }
        return scoreDiagonalP;
    }

    //Defend_core
    private int attackScore(Point point, char data[][]) {
        return scoreRow(point, data, 2) + scoreColumn(point, data, 2)
                + scoreDiagonalC(point, data, 2) + scoreDiagonalP(point, data, 2);
    }

    //Attack_core
    private int defendScore(Point point, char data[][]) {
        return scoreRow(point, data, 1) + scoreColumn(point, data, 1)
                + scoreDiagonalC(point, data, 1) + scoreDiagonalP(point, data, 1);
    }

    public Point stepCom(char data[][]) {
        Point point = new Point();
        Point pointMax = new Point();                           //default giua ban` co`
        int CoreMax = 0;
        for (point.x = 0; point.x < WIDTH_MAX; point.x++) {
            for (point.y = 0; point.y < HEIGHT_MAX; point.y++) {
                if (data[point.x][point.y] == 0) {
                    int AttackCore = attackScore(point, data);
                    int DefendCore = defendScore(point, data);
                    int TempCore = (AttackCore > DefendCore) ? AttackCore : DefendCore;
                    if (TempCore > CoreMax) {
                        CoreMax = TempCore;
                        pointMax.x = point.x;
                        pointMax.y = point.y;
                    }
                }
            }
        }
        return pointMax;
    }

    public void play(Point point, JButton btn[][]) {
        if (pointTemp != null) {
            btn[pointTemp.x][pointTemp.y].setBackground(Color.white);
        }
        btn[point.x][point.y].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/P_O.png")));
        btn[point.x][point.y].setBackground(Color.red);
        pointTemp = new Point(point.x, point.y);
    }

}
