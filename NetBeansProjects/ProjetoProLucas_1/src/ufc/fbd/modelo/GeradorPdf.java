/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.modelo;

/**
 *
 * @author Luan
 */
import com.itextpdf.text.Anchor;
import java.io.FileOutputStream; 
import java.io.IOException; 
import com.itextpdf.text.Document; 
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
public class GeradorPdf { 

    public GeradorPdf(Aluga aluga) {
        Document document = new Document(); 
        try {
        PdfWriter.getInstance(document, new FileOutputStream("C://pdfsGerados/notaFiscal.pdf"));
        
        document.open(); 
        document.addHeader("Nota Fiscal", "Cliente: "+aluga.getCliente().getNome()+" Cpf: "+aluga.getCliente().getCpf());
        document.top(5.0f);
        
        document.add(new Paragraph("Nota Fiscal, Locadora: "+aluga.getFilme().getLocadora().getNome()+", "+aluga.getCliente().getLocadora().getNome()+" "+ aluga.getData()));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Cliente: "+aluga.getCliente().getNome()));
        document.add(new Paragraph("Cpf: "+aluga.getCliente().getCpf()));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Nome do Filme: "+aluga.getFilme().getNome()));
        document.add(new Paragraph("Categoria: "+aluga.getFilme().getCategoria().getCategoria()));
        document.add(new Paragraph("Indicação: "+aluga.getFilme().getIndicacao()));
        document.add(new Paragraph(" "));
        
        } catch(DocumentException de) {
            System.err.println(de.getMessage());    
        } catch(IOException ioe){ 
            System.err.println(ioe.getMessage()); 
        } 
        document.close(); 
    }

}
