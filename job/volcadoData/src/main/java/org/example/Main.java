package org.example;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static final String url = "jdbc:postgresql://localhost:5432/pronacej_official";
    static final String user = "postgres";
    static final String password = "12345";

    // Método principal del programa que ejecutará la función adecuada para cada archivo
    // ya sea un reporte mensual tanto para CJDR o SOA, como los reportes diarios
    // para CJDR o SOA
    public static void main(String[] args) {

        String folderPath = "C:/registros_diarios/";

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".xlsx"));

        if (files != null) {
            for (File file : files) {
                try (FileInputStream inputStream = new FileInputStream(file)) {
                    Workbook workbook = WorkbookFactory.create(inputStream);
                    Sheet sheet = workbook.getSheetAt(0);
                    Row firstRow = sheet.getRow(0);

                    if (firstRow != null) {
                        int columnCount = firstRow.getLastCellNum();

                        if (columnCount == 5) {
                            fileReportDailySoa(file);
                        } else if (columnCount == 13) {
                            fileReportDailyCjdr(file);
                        } else {
                            fileReportMonthly(file);
                        }
                    } else {
                        System.out.println("El archivo " + file.getName() + "no tiene filas.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No se encontraron archivos Excel en la carpeta");
        }
    }

    // Método para tratar los archivos con 5 columnas es decir los reportes diarios SOA
    private static void fileReportDailySoa(File file) {
        // Lógica específica para archivos con 5 columnas
        System.out.println("Procesando reporte diario SOA: " + file.getName());

        boolean primeraFila = true;

        int totalRows = 0;

        // Mapeo entre nombres de columnas en el archivo Excel y nombre de collumnas en la base de datos
        Map<String, Integer> columnMapping = new HashMap<>();
        columnMapping.put("SOA", 0);
        columnMapping.put("Fecha", 1);
        columnMapping.put("Varones", 2);
        columnMapping.put("Mujeres", 3);
        columnMapping.put("Población", 4);


        // Ruta de la carpeta
        String destinoPath = "C:/registros_diarios/DocumentosVolcados/";

        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {

            // Deshabilitar el autocomit
            connection.setAutoCommit(false);

            File errorFolder = new File(destinoPath + "Errores/");
            if (!errorFolder.exists()) {
                errorFolder.mkdir(); // Crear la carpeta si no existe
            }

            // variable para contar las filas procesadas en este archivo
            int rowsProcessed = 0;
            String statusMessage = null;

            try (FileInputStream inputStream = new FileInputStream(file)) {
                Workbook workbook = WorkbookFactory.create(inputStream);
                Sheet sheet = workbook.getSheetAt(0);

                // Procesamiento de filas
                for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                    Row row = sheet.getRow(i);
                    if (i < 1) {
                        continue; // Saltar la primera fila
                    }
                    // Obtener valores de las columnas según el mapeo
                    String soa = getCellValueAsString(row, 0);
                    Timestamp fecha = getCellValueAsTimestamp(row, 1);
                    Integer varones = getCellValueAsInt(row, 2);
                    Integer mujeres = getCellValueAsInt(row, 3);
                    Integer poblacion = getCellValueAsInt(row, 4);


                    if (soa == null) {
                        // Si no hay más valores numéricos, sal del bucle
                        break;
                    }
                    // Inserción en la base de datos
                    String sql = "INSERT INTO matriz_diario_soa (" +
                            "centro_juvenil, fecha, varones, mujeres, poblacion)" +
                            " VALUES (?, ? , ?, ?, ?)";

                    try (PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setString(1, soa);
                        statement.setTimestamp(2, fecha);
                        statement.setInt(3, varones);
                        statement.setInt(4, mujeres);
                        statement.setInt(5, poblacion);
                        statement.executeUpdate();
                        System.out.println("Registros actualizados con éxito en la tabla matriz_diario_soa de la base de datos");
                    }
                    totalRows++;
                    // Incrementar el contador de filas procesadas
                    rowsProcessed++;
                }
                // Insertar registro en process_header para este archivo
                insertProcessHeader(connection, rowsProcessed, file.getName(),
                        "Archivo Procesado Exitosamente");

                // Confirmar la transacción después de procesar todos los archivos
                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al actualizar tabla matriz_diario_soa en base de datos");

                // Si ocurre un error, realizar un rollback de la transacción
                connection.rollback();
                System.out.println("Error al actualizar el archivo: " + file.getName());

                // Insertar registro en process_header para este archivo
                insertProcessHeader(connection, rowsProcessed, file.getName(),
                        "Error al procesar el archivo: " + e.getMessage());

                // Crear archivo de registro de errores
                String errorLogFileName = file.getName().replace(".xlsx", ".txt");
                createErrorLog(errorFolder.getPath() + "/" + errorLogFileName, e);

            } finally {
                // Mover el archivo procesado
                File destinoFile = new File(destinoPath + file.getName());
                file.renameTo(destinoFile);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Método para tratar los archivos con 5 columnas es decir los reportes diarios SOA
    private static void fileReportDailyCjdr(File file) {
        // Lógica específica para archivos con 5 columnas
        System.out.println("Procesando reporte diario CJDR: " + file.getName());

        boolean primeraFila = true;

        int totalRows = 0;

        // Mapeo entre nombres de columnas en el archivo Excel y nombre de collumnas en la base de datos
        Map<String, Integer> columnMapping = new HashMap<>();
        columnMapping.put("CJDR", 0);
        columnMapping.put("Fecha", 1);
        columnMapping.put("Población", 2);
        columnMapping.put("Sentenciados", 3);
        columnMapping.put("Procesados", 4);

        // Ruta de la carpeta
        String destinoPath = "C:/registros_diarios/DocumentosVolcados/";

        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {

            // Deshabilitar el autocomit
            connection.setAutoCommit(false);

            File errorFolder = new File(destinoPath + "Errores/");
            if (!errorFolder.exists()) {
                errorFolder.mkdir(); // Crear la carpeta si no existe
            }

            // variable para contar las filas procesadas en este archivo
            int rowsProcessed = 0;
            String statusMessage = null;

            try (FileInputStream inputStream = new FileInputStream(file)) {
                Workbook workbook = WorkbookFactory.create(inputStream);
                Sheet sheet = workbook.getSheetAt(0);

                // Procesamiento de filas
                for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                    Row row = sheet.getRow(i);
                    if (i < 1) {
                        continue; // Saltar la primera fila
                    }
                    // Obtener valores de las columnas según el mapeo
                    String cjdr = getCellValueAsString(row, 0);
                    Timestamp fecha = getCellValueAsTimestamp(row, 1);
                    Integer poblacion = getCellValueAsInt(row, 2);
                    Integer sentenciados = getCellValueAsInt(row, 3);
                    Integer procesados = getCellValueAsInt(row, 4);


                    if (cjdr == null) {
                        // Si no hay más valores numéricos, sal del bucle
                        break;
                    }
                    // Inserción en la base de datos
                    String sql = "INSERT INTO matriz_diario_cjdr (" +
                            "centro_juvenil, fecha, poblacion, sentenciado, procesado)" +
                            " VALUES (?, ? , ?, ?, ?)";

                    try (PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setString(1, cjdr);
                        statement.setTimestamp(2, fecha);
                        statement.setInt(3, poblacion);
                        statement.setInt(4, sentenciados);
                        statement.setInt(5, procesados);
                        statement.executeUpdate();
                        System.out.println("Registros actualizados con éxito en la tabla matriz_diario_cjdr de la base de datos");
                    }
                    totalRows++;
                    // Incrementar el contador de filas procesadas
                    rowsProcessed++;
                }
                // Insertar registro en process_header para este archivo
                insertProcessHeader(connection, rowsProcessed, file.getName(),
                        "Archivo Procesado Exitosamente");

                // Confirmar la transacción después de procesar todos los archivos
                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al actualizar tabla matriz_diario_cjdr en base de datos");

                // Si ocurre un error, realizar un rollback de la transacción
                connection.rollback();
                System.out.println("Error al actualizar el archivo: " + file.getName());

                // Insertar registro en process_header para este archivo
                insertProcessHeader(connection, rowsProcessed, file.getName(),
                        "Error al procesar el archivo: " + e.getMessage());

                // Crear archivo de registro de errores
                String errorLogFileName = file.getName().replace(".xlsx", ".txt");
                createErrorLog(errorFolder.getPath() + "/" + errorLogFileName, e);

            } finally {
                // Mover el archivo procesado
                File destinoFile = new File(destinoPath + file.getName());
                file.renameTo(destinoFile);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Método para tratar los archivos que corresponen con los reportes mensuales SOA y CJDR
    private static void fileReportMonthly(File file) {


        boolean primeraFila = true;

        int totalRows = 0;

        // Mapeo entre nombres de columnas en el archivo Excel y nombre de collumnas en la base de datos
        Map<String, Integer> columnMapping = new HashMap<>();
        columnMapping.put("N°", 0);
        columnMapping.put("FECHA INGRESO", 1);
        columnMapping.put("CJDR/SOA", 2);
        columnMapping.put("ESTADO", 3);
        columnMapping.put("ESTADO CIVIL DEL ADOLESCENTE", 4);
        columnMapping.put("SEXO", 5);
        columnMapping.put("SITUACIÓN JURÍDICA DE INGRESO", 6);
        columnMapping.put("SITUACIÓN JURÍDICA ACTUAL", 7);
        columnMapping.put("DELITO ESPECÍFICO\n" +
                "1", 8);
        columnMapping.put("ADOLESCENTE PARTICIPA EN PROGRAMA O ESTRATEGIA DE INTERVENCIÓN", 9);
        columnMapping.put("JUSTICIA TERAPÉUTICA", 10);
        columnMapping.put("AGRESORES SEXUALES", 11);
        columnMapping.put("SALUD MENTAL", 12);
        columnMapping.put("ADN FAMILIAR (SOA)/ FAMILIA JOVEN (CJDR)", 13);
        columnMapping.put("INTERVENCIÓN TERPÉUTICA", 14);
        columnMapping.put("FIRMES Y ADELANTE", 15);
        columnMapping.put("SITUACIÓN EDUCATIVA ACTUAL", 16);
        columnMapping.put("ROL DEL ETI EN EL SEGUIMIENTO, REINSERCIÓN E INSERCIÓN EDUCATIVA", 17);
        columnMapping.put("TIPOS DE CENTROS EDUCATIVOS", 18);
        columnMapping.put("SEGURO MÉDICO", 19);
        columnMapping.put("DE SER EL CASO, ¿CUÁL ES LA SITUACIÓN LABORAL ACTUAL?", 20);

        // Ruta de la carpeta
        String destinoPath = "C:/registros_diarios/DocumentosVolcados/";

        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {

            // Deshabilitar el autocomit
            connection.setAutoCommit(false);

            File errorFolder = new File(destinoPath + "Errores/");
            if (!errorFolder.exists()) {
                errorFolder.mkdir(); // Crear la carpeta si no existe
            }

            primeraFila = true;

            // variable para contar las filas procesadas en este archivo
            int rowsProcessed = 0;
            String statusMessage = null;

            try (FileInputStream inputStream = new FileInputStream(file)) {
                Workbook workbook = WorkbookFactory.create(inputStream);
                Sheet sheet = workbook.getSheetAt(0);

                // Procesamiento de filas
                for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                    Row row = sheet.getRow(i);
                    if (i < 3) {
                        continue; // Saltar las primeras tres filas
                    }
                    // Obtener valores de las columnas según el mapeo
                    int cellIndex = 0;
                    Integer numero_registro = getCellValueAsInt(row, cellIndex);
                    Timestamp fecha = getCellValueAsTimestamp(row, 3);
                    String centro_juvenil = getCellValueAsString(row, 1);
                    String estado = getCellValueAsString(row, 2);
                    String estado_civil_adolescente = getCellValueAsString(row, 9);
                    String sexo = getCellValueAsString(row, 8);
                    String situacion_juridica_ingreso = getCellValueAsString(row, 44);
                    String situacion_juridica_actual = getCellValueAsString(row, 47);
                    String delito_especifico = getCellValueAsString(row, 63);
                    String participa_programa = getCellValueAsString(row, 91);
                    String justicia_terapeutica = getCellValueAsString(row, 92);
                    String agresores_sexuales = getCellValueAsString(row, 93);
                    String salud_mental = getCellValueAsString(row, 94);
                    String adn_familiar = getCellValueAsString(row, 90);
                    String intervencion_terapeutica = getCellValueAsString(row, 96);
                    String firmes_adelante = getCellValueAsString(row, 97);
                    String situacion_edu_actual = getCellValueAsString(row, 66);
                    String rol_reinser_edu_mes = getCellValueAsString(row, 67);
                    String tipo_centro_educativo = getCellValueAsString(row, 69);
                    String seguro_medico = getCellValueAsString(row, 15);
                    String situacion_laboral_actual = getCellValueAsString(row, 81);

                    if (numero_registro == null) {
                        // Si no hay más valores numéricos, sal del bucle
                        break;
                    }
                    // Inserción en la base de datos
                    String sql = "INSERT INTO matriz (" +
                            "numero_registro, fecha, centro_juvenil, estado, estado_civil_adolescente, sexo," +
                            "situacion_juridica_ingreso, situacion_juridica_actual, delito_especifico," +
                            "participa_programa, justicia_terapeutica, agresores_sexuales, salud_mental, " +
                            "adn_familiar, intervencion_terapeutica, firmes_adelante, situacion_edu_actual, " +
                            "rol_reinser_edu_mes, tipo_centro_educativo, seguro_medico, situacion_laboral_actual)" +
                            " VALUES (?, ? ,?,?,?,?, ?, ?, ? ,?, ?, ?, ? ,?, ?, ?, ? ,?, ?, ?, ? )";

                    try (PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setInt(1, numero_registro);
                        statement.setTimestamp(2, fecha);
                        statement.setString(3, centro_juvenil);
                        statement.setString(4, estado);
                        statement.setString(5, estado_civil_adolescente);
                        statement.setString(6, sexo);
                        statement.setString(7, situacion_juridica_ingreso);
                        statement.setString(8, situacion_juridica_actual);
                        statement.setString(9, delito_especifico);
                        statement.setString(10, participa_programa);
                        statement.setString(11, justicia_terapeutica);
                        statement.setString(12, agresores_sexuales);
                        statement.setString(13, salud_mental);
                        statement.setString(14, adn_familiar);
                        statement.setString(15, intervencion_terapeutica);
                        statement.setString(16, firmes_adelante);
                        statement.setString(17, situacion_edu_actual);
                        statement.setString(18, rol_reinser_edu_mes);
                        statement.setString(19, tipo_centro_educativo);
                        statement.setString(20, seguro_medico);
                        statement.setString(21, situacion_laboral_actual);

                        statement.executeUpdate();
                        System.out.println("Registros actualizados con éxito en la tabla matriz de la base de datos");
                    }
                    totalRows++;
                    // Incrementar el contador de filas procesadas
                    rowsProcessed++;
                }
                // Insertar registro en process_header para este archivo
                insertProcessHeader(connection, rowsProcessed, file.getName(),
                        "Archivo Procesado Exitosamente");

                // Confirmar la transacción después de procesar todos los archivos
                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al actualizar tabla matriz en base de datos");

                // Si ocurre un error, realizar un rollback de la transacción
                connection.rollback();
                System.out.println("Error al actualizar el archivo: " + file.getName());

                // Insertar registro en process_header para este archivo
                insertProcessHeader(connection, rowsProcessed, file.getName(),
                        "Error al procesar el archivo: " + e.getMessage());

                // Crear archivo de registro de errores
                String errorLogFileName = file.getName().replace(".xlsx", ".txt");
                createErrorLog(errorFolder.getPath() + "/" + errorLogFileName, e);

            } finally {
                // Mover el archivo procesado
                File destinoFile = new File(destinoPath + file.getName());
                file.renameTo(destinoFile);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
}



    private static void createErrorLog(String filePath, Exception e) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write("Error: " + e.getMessage() + "\n");
            writer.write("StackTrace:\n");
            for (StackTraceElement element : e.getStackTrace()) {
                writer.write(element.toString() + "\n");
            }
            writer.close();
            System.out.println("Archivo de registro de errores creado: " + filePath);
        } catch (IOException ex) {
            System.out.println("Error al crear el archivo de registro de errores: " + ex.getMessage());
        }
    }


    private static void insertProcessHeader(Connection connection, int rowsProcessed, String fileName, String statusMessage) {
        String sql = "INSERT INTO process_header (type_process_header_id, start_time, end_time, amount, message,status, state) " +
                "VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?, 1)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rowsProcessed);
            statement.setString(2, "Archivo procesado: " + fileName);
            statement.setString(3, statusMessage);
            statement.executeUpdate();
            System.out.println("Registro insertado en process_header con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar en process_header.");
        }
    }


    // Método para obtener el valor de la celda como entero
    private static Integer getCellValueAsInt(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell != null) {
            try {
                // Primero, intentar obtener el valor numérico directamente
                double numericValue = cell.getNumericCellValue();
                return (int) numericValue;
            } catch (IllegalStateException e) {
                // Si falla, intenta obtener el valor como cadena y luego parsearlo a entero
                String stringValue = cell.getStringCellValue().trim();
                if (!stringValue.isEmpty()) {
                    try {
                        return Integer.parseInt(stringValue);
                    } catch (NumberFormatException ex) {
                        // Si no se puede parsear a entero, devuelve null
                        return null;
                    }
                }
            }
        }
        return null;
    }

    // Método para obtener el valor de la celda como una marca de tiempo
    private static Timestamp getCellValueAsTimestamp(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        return cell != null && cell.getCellType() == CellType.NUMERIC ? new Timestamp(cell.getDateCellValue().getTime()) : null;
    }

    // Método para obtener el valor de la celda como una cadena de texto
    private static String getCellValueAsString(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        return cell != null ? cell.getStringCellValue() : null;
    }

}