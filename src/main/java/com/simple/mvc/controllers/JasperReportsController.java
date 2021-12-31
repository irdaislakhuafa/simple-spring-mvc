package com.simple.mvc.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simple.mvc.services.JasperReportsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
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

    @Autowired
    private HttpSession session;

    @GetMapping("/products")
    @Async
    public void getProductsReport() throws Exception {
        Date date = new Date();
        String fileName = "productsList-" + date.toInstant() + ".pdf";

        response.setContentType(MediaType.APPLICATION_PDF_VALUE); // set content type
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=\"" + fileName + "\"");

        String searchKeyword = session.getAttribute("searchKeyword").toString();
        String keyword = (searchKeyword == null) ? " " : searchKeyword;

        // ! call JasperPrint object
        JasperPrint jasperPrint = jasperReportsService
                .generateJasperPrintObjectWithParams("templates/reports/ProductsList.jasper",
                        keyword);

        // ? send buffer to file and clear the buffer
        response.getOutputStream().flush();

        // ? export JasperPrint object to PDF (input, output)
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        // ! close the buffer
        response.getOutputStream().close();
    }

    @GetMapping("/test")
    @Async
    public JasperPrint test() {
        try {
            return jasperReportsService.generateJasperPrintObject("templates/reports/ListProductsjrxml.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
