/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 62130500127
 */
public class DBConnection {
    
    private static final String DRIVER="org.apache.derby.jdbc.ClientDriver";
    private static final String URI="jdbc:derby://localhost:1527/bankProject";
    private static final String USERNAME="bank";
    private static final String PASSWORD="832";
    
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn=DriverManager.getConnection(URI, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("ไม่พบ Database driver! ให้ทำอย่างใดอย่างหนึ่งต่อไปนี้");
            System.out.println("-คลิกขวาที่ Libraries แล้วเลือก Add Library.. แล้วเลือก Java DB Driver");
            System.out.println("หรือ\n-เพิ่มไฟล์ derby.jar และ derbyclient.jar ไว้ที่ Libraries");
        } catch (SQLException ex) {
            System.out.println("\n---------");
            System.out.println("ไม่สามารถเชื่อมต่อ databses ได้");
            System.out.println("ให้ตรวจสอบ database ให้คลิกที่หน้าต่าง Services ดูที่หัวข้อ Databases"); 
            System.out.println("1. ตรวจสอบการ start database server");
            System.out.println("\t1.1 คลิกขวาที่ Java DB เลือก Start Server"); 
            System.out.println("2. หากทำตามข้อ 1 แล้วไม่ได้ผล \n   ให้สร้าง database ชื่อ bankProject ใหม่"); 
            System.out.println("\t2.1 ถ้ามีแล้วให้ลบออกโดยคลิกขวาที่ database ชื่อ bankProject เลือก Delete");
            System.out.println("\t2.2 การสร้างใหม่คลิกขวาที่ Java DB เลือก Create Database..");
            System.out.println("\t\tระบุชื่อเป็น bankProject และ username เป็น bank ตั้ง password เป็น 832");
            System.out.println("---------\n");
        }
        return conn;
    }
}
