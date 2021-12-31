package com.simple.mvc.services;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class JasperReportsService {
    @Autowired
    private DataSource dataSource; // get datasource from spring boot

    // @Autowired // get session
    // private HttpSession session;

    @Async
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
    @Async
    public JasperPrint generateJasperPrintObject(String sourceFile) throws Exception {
        InputStream reportFile = new ClassPathResource(sourceFile).getInputStream();

        // generate object jasperreports
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);

        // generate object jasperprint
        // (file, parameters, connection)
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, getConnection());
        return jasperPrint;
    }

    // overloading method
    // generate object from jasperreports with JasperPrint
    @Async
    public JasperPrint generateJasperPrintObjectWithParams(String sourceFile, String nameParameter) throws Exception {
        InputStream reportFile = new ClassPathResource(sourceFile).getInputStream();

        // generate object jasperreports
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);

        // get parameters
        Map<String, Object> jasperParams = new HashMap<String, Object>();
        jasperParams.put("name", "%" + nameParameter + "%");

        // generate object jasperprint
        // (file, parameters, connection)
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParams, getConnection());
        return jasperPrint;
    }

}
