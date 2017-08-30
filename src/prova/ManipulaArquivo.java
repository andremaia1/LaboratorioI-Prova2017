
package prova;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ManipulaArquivo {
    
    private static File arquivo = new File("dados.txt");
    private static File arquivo1 = new File("caixa.txt");
    
    public static void salvarDados (Produto produto) {
        
        try {
            
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(produto.atributos());
            bw.newLine();
            
            bw.close();
            fw.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static ArrayList<Produto> lerDados () {
        
        ArrayList<Produto> lista = new ArrayList();
        int indice = 0;
        
        try {
            
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            
            while (br.ready()) {
                
                String partes[] = br.readLine().split(" ; ");
                
                lista.add(new Produto());
                lista.get(indice).cadastrar(partes[0], partes[1], Integer.parseInt(partes[2]), Double.parseDouble(partes[3]), Double.parseDouble(partes[4]), Integer.parseInt(partes[5]));
                
                indice++;
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
    
    public static void salvarValorCaixa (double valorCaixa) {
        
        try {
            
            if (!arquivo1.exists()) {
                arquivo1.createNewFile();
            }
            
            FileWriter fw = new FileWriter(arquivo1, false);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(String.valueOf(valorCaixa));
            bw.newLine();
            
            bw.close();
            fw.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static double lerValorCaixa () {
        
        String linha = "1000.0";
        
        try {
            
            if (!arquivo1.exists()) {
                arquivo1.createNewFile();
            }
            
            FileReader fr = new FileReader(arquivo1);
            BufferedReader br = new BufferedReader(fr);
            
            while (br.ready()) {
                linha = br.readLine();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return Double.parseDouble(linha);
    }
}
