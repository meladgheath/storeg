package org.softwareengine.utils.ui;

import net.sf.jasperreports.engine.*;
import org.softwareengine.utils.service.DatabaseService;

public class report {


    public static JasperPrint getPrint() throws JRException {

        String path = report.class.getResource("/report/item.jrxml") .toString();




        JasperReport report = null ;
        JasperPrint jp = null ;
        report = JasperCompileManager.compileReport("item.jrxml");
        jp = JasperFillManager.fillReport(report,null, DatabaseService.connection);

        return jp ;
    }
}
