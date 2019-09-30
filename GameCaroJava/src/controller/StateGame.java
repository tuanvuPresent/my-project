/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MyConst.*;
import java.awt.Point;

/**
 *
 * @author
 */
public class StateGame {

    private boolean latche = true;

    private boolean checkRow(Point point, char data[][]) {
        int x = point.x - 1, count = 1;
        while ((x >= 0 && data[point.x][point.y] == data[x][point.y])) {
            count++;
            x = x - 1;
        }
        latche = (x == -1 || data[x][point.y] == 0);
        x = point.x + 1;
        while ((x < WIDTH_MAX && data[point.x][point.y] == data[x][point.y])) {
            count++;
            x = x + 1;
        }
        latche = (latche || x == WIDTH_MAX || data[x][point.y] == 0);
        if (latche && count >= 5) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkColumn(Point point, char data[][]) {
        int y = point.y - 1, count = 1;
        while ((y >= 0 && data[point.x][point.y] == data[point.x][y])) {
            count++;
            y = y - 1;
        }
        latche = (y == -1 || data[point.x][y] == 0);
        y = point.y + 1;
        while ((y < HEIGHT_MAX && data[point.x][point.y] == data[point.x][y])) {
            count++;
            y = y + 1;
        }
        latche = (latche || y == HEIGHT_MAX || data[point.x][y] == 0);
        if (latche && count >= 5) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDiagonalP(Point point, char data[][]) {
        int x = point.x + 1, y = point.y - 1, count = 1;
        while ((x < WIDTH_MAX && y >= 0 && data[point.x][point.y] == data[x][y])) {
            count++;
            x = x + 1;
            y = y - 1;
        }
        latche = (x == WIDTH_MAX || y == -1 || data[x][y] == 0);
        x = point.x - 1;
        y = point.y + 1;
        while ((x >= 0 && y < HEIGHT_MAX && data[point.x][point.y] == data[x][y])) {
            count++;
            x = x - 1;
            y = y + 1;
        }
        latche = (latche || x == -1 || y == HEIGHT_MAX || data[x][y] == 0);
        if (latche && count >= 5) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDiagonalC(Point point, char data[][]) {
        int x = point.x + 1, y = point.y + 1, count = 1;
        while ((x < WIDTH_MAX && y < HEIGHT_MAX && data[point.x][point.y] == data[x][y])) {
            count++;
            x = x + 1;
            y = y + 1;
        }
        latche = (x == WIDTH_MAX || y == HEIGHT_MAX || data[x][y] == 0);
        x = point.x - 1;
        y = point.y - 1;
        while ((x >= 0 && y >= 0 && data[point.x][point.y] == data[x][y])) {
            count++;
            x = x - 1;
            y = y - 1;
        }
        latche = (latche || x == -1 || y == -1 || data[x][y] == 0);
        if (latche && count >= 5) {
            return true;
        } else {
            return false;
        }
    }

    private char isBoard(char data[][]) {
        //kiem tra hoa game
        for (int row = 0; row < HEIGHT_MAX; row++) {
            for (int col = 0; col < WIDTH_MAX; col++) {
                if (data[row][col] == 0) {
                    return STATE_NONE;
                }
            }
        }
        return STATE_HOA;
    }

    public char stateGame(Point point, char data[][]) {
        if (checkRow(point, data) || checkColumn(point, data)
                || checkDiagonalC(point, data) || checkDiagonalP(point, data)) {
            return STATE_THANG;
        }
        if (isBoard(data) == STATE_HOA) {
            return STATE_HOA;
        } else {
            return STATE_NONE;
        }
    }

}
