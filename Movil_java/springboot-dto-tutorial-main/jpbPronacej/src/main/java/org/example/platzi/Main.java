package org.example.platzi;


import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;


public class JobPronacej {

    // Clase Main
    public  static void main(String[] args){
        // Ruta de la carpeta
        String folderPath = "C:/registros_diarios/";
        try(Connection connection =
                    DriverManager.getConnection(
                            "jdbc_postgresql://localhost:5432/pronacej/",
                            "postgres",
                            "12345")){
            File folder = new File(folderPath);
            File[] files = folder.listFiles((dir, name)
                    -> name.toLowerCase().endsWith(".xlxsx"));

            if (files != null){
                for (File file: files){
                    try (FileInputStream inputStream = new FileInputStream(file)){
                        Workbook workbook = Workbook
                    }
                }
            }
        }

    }


}

