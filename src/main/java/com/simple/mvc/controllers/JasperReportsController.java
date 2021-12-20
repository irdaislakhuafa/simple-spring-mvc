package com.simple.mvc.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.simple.mvc.services.JasperReportsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/reports")
public class JasperReportsController {

    @Autowired // * inject JasperReportsService
    private JasperReportsService jasperReportsService;

    @Autowired // ? inject HttpServletResponse to handle response manually
    private HttpServletResponse response;

    @GetMapping("/products")
    public void getProductsReport() throws Exception {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE); // set content type
        response.setHeader(
                "Content-Disposotion",
                "attachment; filename=\"products_list " + new Date() + ".pdf\"");

        // ! call JasperPrint object
        JasperPrint jasperPrint = jasperReportsService.generateJasperPrintObject("reports/ListProductsjrxml.jasper");

        // ? export JasperPrint object to PDF (input, output)
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
