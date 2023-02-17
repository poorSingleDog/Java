package com.example.damo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class demo {
    public static void main(String[] args) {
        try(FileInputStream in = new FileInputStream("abc.txt");
            FileOutputStream out=new FileOutputStream("out.txt")){
            byte[] buf=new byte[3];

//            while (in.read(buf, 0, buf.length) != -1) {
//                out.write(buf);
//            }

            System.out.println(in.read());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
