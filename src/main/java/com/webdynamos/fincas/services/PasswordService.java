package com.webdynamos.fincas.services;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public PasswordService(){}

    public String encryptPassword(String password) {
        try {
            // Crea una instancia del algoritmo SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Convierte la contraseña en un arreglo de bytes
            byte[] passwordBytes = password.getBytes();

            // Aplica el cifrado SHA-256
            byte[] encryptedBytes = messageDigest.digest(passwordBytes);

            // Convierte el arreglo de bytes cifrados en un string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : encryptedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Retorna la contraseña cifrada en formato hexadecimal
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Maneja la excepción en caso de que el algoritmo no esté disponible
            throw new RuntimeException("Error al cifrar la contraseña", e);
        }
    }

}
