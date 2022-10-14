package com.example.contactservice.service;

import com.example.contactservice.entity.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    public String exportReport(String reportFormat, List<Student> studentLists) throws FileNotFoundException, JRException {
        String path= "C:\\Users\\BJIT\\Desktop\\report";
        List<Student> students = studentLists;
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:students.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy","Md. Al Shariar");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\students.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\students.pdf");
        }
        if(reportFormat.equalsIgnoreCase("csv")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\students.csv");
        }
        return  "report generated in this path: "+path;
    }
}
