/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Admin
 */
import org.mindrot.jbcrypt.BCrypt;

public class GenerarHash {
    public static void main(String[] args) {
        String[] contrasenas = {"Admin1234!", "Maestro1234!", "Est1234!"};
        for (String c : contrasenas) {
            System.out.println(c + " → " + BCrypt.hashpw(c, BCrypt.gensalt(12)));
        }
    }
}
