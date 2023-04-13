/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caritas.util;

import caritas.model.Entity.Enxoval;
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
public class GenerateReportEnxoval {
    
    String msg = "";

    public String gerarPlanilhaEnxovais(List<Enxoval> enxovais, String caminhoEscolhido) {
            String[] colunasTabelaAgenda = { "Total Enxovais "," Ano " };

            HSSFWorkbook planilha = new HSSFWorkbook();

            HSSFSheet abaPlanilha = planilha.createSheet("Enxovais");

            HSSFRow headerRow = abaPlanilha.createRow(0);

            for (int i = 0; i < colunasTabelaAgenda.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(colunasTabelaAgenda[i]);
            }

            int rowNum = 1;
            for (Enxoval enxoval : enxovais) {
                    HSSFRow novaLinha = abaPlanilha.createRow(rowNum++);

                    novaLinha.createCell(0).setCellValue(enxoval.getTotal());
                    novaLinha.createCell(1).setCellValue(enxoval.getYear());

            }

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
