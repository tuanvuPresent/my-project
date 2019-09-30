/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.crud.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author NguyenTuanVu
 */
public class DataController {

    private FileInputStream fis;
    private FileOutputStream fos;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public Object read(String nameFile) throws IOException, ClassNotFoundException {
        Object object = new Object();
        //Bước 1: Mở luồng file
        fis = new FileInputStream(new File(nameFile));
        inputStream = new ObjectInputStream(fis);
        //Bước 2 : Đọc dữ liệu
        object = inputStream.readObject();
        return object;
    }

    public ArrayList readDS(String nameFile) {
        ArrayList list = new ArrayList();
        try {
            //Bước 1: Mở luồng file
            fis = new FileInputStream(new File(nameFile));
            inputStream = new ObjectInputStream(fis);
            //Bước 2 : Đọc dữ liệu
            list = (ArrayList) inputStream.readObject();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra");
        } finally {
            try {
                inputStream.close();
                fis.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra");
            }
        }
        return list;
    }

    public void writeDS(ArrayList list, String nameFile) throws IOException {
        try {
            //Bước 1: Mở luồng file
            fos = new FileOutputStream(new File(nameFile));
            outputStream = new ObjectOutputStream(fos);
            //Bước 2 : Đọc dữ liệu
            outputStream.writeObject(list);
        } finally {
            outputStream.close();
            fos.close();
        }
    }

    public void write(Object object, String nameFile) throws IOException {
        //Bước 1: Mở luồng file
        fos = new FileOutputStream(new File(nameFile));
        outputStream = new ObjectOutputStream(fos);
        //Bước 2 : Đọc dữ liệu
        outputStream.writeObject(object);
    }
}
