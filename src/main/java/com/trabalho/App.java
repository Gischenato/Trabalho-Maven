package com.trabalho;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

/*
Exemplo simples de uso da API Apache Commons CVS
Extrair o arquivo commons-csv-1.7.jar para o diretorio do projeto
Para compilar no Windows: javac -cp .;.\commons-csv-1.7.jar App.java
Para compilar no Linux: javac -cp commons-csv-1.7.jar App.java
Para executar no windows: java -cp .;.\commons-csv-1.7.jar App
Para executar no Linux: java -cp .:commons-csv-1.7.jar App
Para executar: java -cp .;.\commons-csv-1.7.jar App.java
*/
public class App {
    private static final String SAMPLE_CSV_FILE_PATH = "src/data/veiculos.dat";

    public static void main(String[] args) throws IOException {
        HashSet<String> cores = new HashSet<>();
        boolean primeiro = true;
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {

            int normal = 0;
            int luxo = 0;
            int simples = 0;
            int totalCarros = 0;

            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String placa = csvRecord.get(0);
                String marca = csvRecord.get(1);
                String cor = csvRecord.get(2);
                String categoria = csvRecord.get(3);
                totalCarros++;
                
                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Placa : " + placa);
                System.out.println("Marca : " + marca);
                System.out.println("Cor : " + cor);
                System.out.println("Categoria : " + categoria);
                System.out.println("---------------\n\n");
                
                if(!primeiro)
                    cores.add(cor);
                primeiro = false;

                switch (categoria) {
                    case "SIMPLES":
                        simples++;
                        break;
                    case "NORMAL":
                        normal++;
                        break;
                    case "LUXO":
                        luxo++;
                        break;
                    default:
                        break;
                }
            }

            System.out.println("Carros por categoria:");
            System.out.println("SIMPLES: " + simples);
            System.out.println("NORMAL: " + normal);
            System.out.println("LUXO: " + luxo);

            System.out.println("Total de carros: " + totalCarros);

            System.out.println("Cores dos carros: " + cores.stream().reduce((c1, c2) -> c1+ ' ' +c2).get());
            
        }
    }
}
