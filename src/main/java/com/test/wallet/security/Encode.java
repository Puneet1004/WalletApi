package com.test.wallet.security;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class Encode implements PasswordEncoder {
//    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    @Override
//    public String encode(CharSequence charSequence) {
//        return passwordEncoder.encode(charSequence);
//    }
//
//    @Override
//    public boolean matches(CharSequence charSequence, String s) {
//        return passwordEncoder.matches(charSequence, s);
//    }
//}

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encode {




    public String encode(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            String s = sb.toString();
            return s;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean matches(String password, String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return (sb.toString()).equals(s);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return false;

    }

}