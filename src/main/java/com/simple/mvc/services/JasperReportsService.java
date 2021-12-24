package com.simple.mvc.services;

import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class JasperReportsService {
    @Autowired
    private DataSource dataSource; // get datasource from spring boot

    private Connection getConnection() {
        try {
            return dataSource.getConnection(); // get connection database from datasource
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    // generate object from jasperreports with JasperPrint
    public JasperPrint generateJasperPrintObject(String sourceFile) throws Exception {
        InputStream reportFile = new ClassPathResource(sourceFile).getInputStream();

        // generate object jasperreports
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);

        // generate object jasperprint
        // (file, parameters, connection)
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, getConnection());
        return jasperPrint;
    }

}
