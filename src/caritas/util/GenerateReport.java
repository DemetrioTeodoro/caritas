/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caritas.util;

import caritas.model.Entity.Peca;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author demetrio
 */
public class GenerateReport {
    
    String msg = "";

    public String gerarPlanilhaPecas(List<Peca> pecas, int total, String caminhoEscolhido) {
            String[] colunasTabelaAgenda = { "Nome da Peça"," Quantidade", " Total Peças " };

            HSSFWorkbook planilha = new HSSFWorkbook();

            HSSFSheet abaPlanilha = planilha.createSheet("Peças");

            HSSFRow headerRow = abaPlanilha.createRow(0);

            for (int i = 0; i < colunasTabelaAgenda.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(colunasTabelaAgenda[i]);
            }

            int rowNum = 1;
            for (Peca peca : pecas) {
                    HSSFRow novaLinha = abaPlanilha.createRow(rowNum++);

                    novaLinha.createCell(0).setCellValue(peca.getName());
                    novaLinha.createCell(1).setCellValue(peca.getAmount());

            }
            
            HSSFRow novaLinha = abaPlanilha.createRow(rowNum++);
            novaLinha.createCell(2).setCellValue(total);

            for (int i = 0; i < colunasTabelaAgenda.length; i++) {
                    abaPlanilha.autoSizeColumn(i);
            }

            FileOutputStream fileOut = null;

            try {
                    fileOut = new FileOutputStream(caminhoEscolhido + ".xls");
                    planilha.write(fileOut);
                    msg = " Relatório criado com sucesso! ";
            } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    msg = " Ocorreu um erro ao criar Relatório! ";
            } catch (IOException e) {
                    e.printStackTrace();
                    msg = " Ocorreu um erro ao criar Relatório verifique as permissões de escrita. ";
            }finally {
                    try {
                            fileOut.close();
                            planilha.close();
                    } catch (IOException e) {
                            e.printStackTrace();
                    }
            }

            return msg;
    }
    
}
