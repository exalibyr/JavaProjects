package Logic;

import javax.swing.*;
import java.util.Random;

public class Logic {

    static final int MAX_RAND = 50;

    public static int exponentiation(int aValue, int xValue, int pValue){
        int x = xValue;
        if(x == 1){
            return aValue%pValue;
        }
        if(x == 0){
            return 1%pValue;
        }
        int n = (int)Math.floor(Math.log10(x) / Math.log10(2)) + 1;
        int xBit[] = new int[n];
        for (int i = 0; i < n; i++) {
            xBit[i] = x % 2;
            x /= 2;
        }
        int[] ans = new int[n];
        ans[0] = aValue;
        try{
            for (int i = 1; i < n; i++) {
                ans[i] = (ans[i - 1] * ans[i - 1]) % pValue;
            }
        }
        catch (ArithmeticException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Arithmetic error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }

        double multiplication = 1;
        for (int i = 0; i < n; i++) {
            if(xBit[i] == 1){
                multiplication *= ans[i];
            }
        }
        multiplication %= pValue;
        return (int)multiplication;
    }

    public static int[] euclid(int aValue, int bValue){
        int q;
        int u[] = {aValue, 1, 0};
        int v[] = {bValue, 0, 1};
        int t[] = new int[3];
        while(v[0] != 0){
            q = u[0] / v[0];
            t[0] = u[0] % v[0];
            t[1] = u[1] - q*v[1];
            t[2] = u[2] - q*v[2];
            for (int i = 0; i < 3; i++) {
                u[i] = v[i];
                v[i] = t[i];
            }
        }
        return u;
    }

    public static String[] xor(String message, String key){
        String[] out = new String[2];
        byte[] mes = message.getBytes();
        byte[] k = key.getBytes();
        byte[] encoded = new byte[mes.length];
        byte[] decoded = new byte[mes.length];
        for (int i = 0; i < mes.length; i++) {
            encoded[i] = (byte)(mes[i] ^ k[i % k.length]);
        }
        out[0] = new String(encoded);
        for (int i = 0; i < mes.length; i++) {
            decoded[i] = (byte)(encoded[i] ^ k[i % k.length]);
        }
        out[1] = new String(decoded);
        return out;
    }

    public static int[] elgamal(int m, int p){
        if(p < 5){
            throw new NumberFormatException("incorrect input");
        }
        int q = (p - 1) / 2;
        if((!isNumberSimple(p)) || (!isNumberSimple(q)) || (m >= p)){
            throw new NumberFormatException("incorrect input");
        }
        Random random = new Random();
        int g = selectionGParam(p, q, random);
        int[] results = new int[7];
        results[0] = g;
        // hidden key Сi, 1<Сi<p-1.
        int c = 2 + random.nextInt(p - 3);
        results[1] = c;
        //d_i=g^(C_i ) mod p open key
        int d = exponentiation(g, c, p);
        results[2] = d;
        //session key 1≤k≤p-2.
        int k = 1 + random.nextInt(p - 2);
        results[3] = k;
        //числа от А к В шифротекст r=g^k mod p; e=m∙d_B^k mod p
        int r = exponentiation(g, k, p);
        results[4] = r;
        int e = exponentiation(d, k, p) * m % p;
        results[5] = e;
        //m^'=e∙r^(p-1-C_B ) mod p
        int received_message = exponentiation(r, p - 1 - c, p) * e % p;
        results[6] = received_message;
        return results;
    }

    public static int[] diffieHellman(int p){
        if(p < 5){
            throw new NumberFormatException("incorrect input");
        }
        int q = (p - 1) / 2;
        if((!isNumberSimple(p)) || (!isNumberSimple(q))){
            throw new NumberFormatException("incorrect input");
        }
        Random random = new Random();
        int g = selectionGParam(p, q, random);
        int[] results = new int[7];
        results[0] = g;
        //secret keys
        int xa = 1 + random.nextInt(11);
        results[1] = xa;
        int xb = 1 + random.nextInt(11);
        results[2] = xb;
        //open keys
        int ya = exponentiation(g, xa, p);
        results[3] = ya;
        int yb = exponentiation(g, xb, p);
        results[4] = yb;
        int zab = exponentiation(yb, xa, p);
        results[5] = zab;
        int zba = exponentiation(ya, xb, p);
        results[6] = zba;
        return results;
    }

    public static int[] shamir(int m, int p){
        if((m >= p) || (!isNumberSimple(p))){
            throw new NumberFormatException("incorrect input");
        }
        int[] results = new int[8];
        //генерируем Ca и Cb, чтобы они были взаимо простыми с p-1
        int[] u;
        int ca, cb;
        int temp = p - 1;
        Random random = new Random();
        do{
            ca = 2 + random.nextInt(MAX_RAND);
            u = euclid(temp, ca);
        }while (u[0] != 1);
        do{
            cb = 2 + random.nextInt(MAX_RAND);
            u = euclid(temp, cb);
        }while ((u[0] != 1) || (ca == cb));
        //Сi∙di mod(p-1)=1
        u = euclid(temp, ca);
        while(u[2] < 0){
            u[2] += temp;
        }
        int da = u[2] % temp;
        u = euclid(temp, cb);
        while(u[2] < 0){
            u[2] += temp;
        }
        int db = u[2] % temp;
        results[0] = ca;
        results[1] = cb;
        results[2] = da;
        results[3] = db;
        int x1 = exponentiation(m, ca, p);
        results[4] = x1;
        int x2 = exponentiation(x1, cb, p);
        results[5] = x2;
        int x3 = exponentiation(x2, da, p);
        results[6] = x3;
        int x4 = exponentiation(x3, db, p);
        results[7] = x4;
        return results;
    }

    public static int[] rsa(int m){
        int[] results = new int[8];
        int p, q, n, f, d, c, e, received_message;
        Random random = new Random();
        do {
            p = random.nextInt(MAX_RAND);
        }
        while(!isNumberSimple(p));
        do {
            do {
                q = random.nextInt(MAX_RAND);
            }
            while (!isNumberSimple(q));
        }
        while(p == q);
        n = p * q;
        f = (p - 1) * (q - 1);
        int[] u;
        do {
            d = 2 + random.nextInt(f - 2);
            u = euclid(d, f);
        }
        while (u[0] != 1);
        //C*d mod Φ = 1
        u = euclid(f, d);
        while (u[2] < 0){
            u[2] += f;
        }
        c = u[2] % f;
        //e=m^(d_B ) modN_B
        e = exponentiation(m, d, n);
        //m^'=e^(C_B ) modN_B
        received_message = exponentiation(e, c, n);
        results[0] = p;
        results[1] = q;
        results[2] = n;
        results[3] = f;
        results[4] = d;
        results[5] = c;
        results[6] = e;
        results[7] = received_message;
        return results;
    }

    public static void DigitalSignatureElgamal(int p){
        if((p < 2) || (!isNumberSimple(p))){
            throw new NumberFormatException("incorrect input");
        }
        Random random = new Random();
        //1<g<p-1
        int g = 4;
        //1<х<p-1
        int x = 2 + random.nextInt(p - 3);
        //у= g^х mod p
        int y = exponentiation(g, x, p);

    }

    public static int[] fermatFactorization(int n){
        if(n < 3){
            throw new NumberFormatException("incorrect input");
        }
        int s = (int)(Math.ceil(Math.sqrt(n)));
        int k = 0;
        double y = (s + k) * (s + k) - n;
        double sqrt_y = Math.sqrt(y);
        while (sqrt_y - Math.round(sqrt_y) != 0){
            k++;
            y = (s + k) * (s + k) - n;
            sqrt_y = Math.sqrt(y);
        }
        int[] results = {(int)(s + k + sqrt_y), (int)(s + k - sqrt_y)};
        return results;
    }

    private static int selectionGParam(int p, int q, Random random){
        // Подбор 1<g<p-1, простое и (g^q)mod p!= 1
        int g;
        while(true){
            g = 2 + random.nextInt(p - 3);
            if(isNumberSimple(g) && exponentiation(g, q, p) != 1){
                return g;
            }
        }
    }

    private static boolean isNumberSimple(int q){
        if(q < 2){
            return false;
        }
        for (int i = 2; i < q; i++) {
            if(q % i == 0){
                return false;
            }
        }
        return true;
    }
}
