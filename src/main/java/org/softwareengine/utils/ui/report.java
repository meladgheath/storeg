package org.softwareengine.utils.ui;

/*import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;*/

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.StringUtils;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.collections.ObservableList;


import javafx.scene.control.Alert;
import net.sf.jasperreports.components.barcode4j.ErrorCorrectionLevelEnum;
import net.sf.jasperreports.components.barcode4j.QRCodeBean;
import net.sf.jasperreports.components.barcode4j.QRCodeImageProducer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import org.softwareengine.config.languages;
import org.softwareengine.core.model.*;
import org.softwareengine.utils.service.DatabaseService;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


/*import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import java.nio.charset.StandardCharsets;*/
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class report {


    public static JasperPrint getPrint() throws JRException {

        String path = report.class.getResource("/report/item.jrxml") .toString();




        JasperReport report = null ;
        JasperPrint jp = null ;
        report = JasperCompileManager.compileReport("item.jrxml");
        jp = JasperFillManager.fillReport(report,null, DatabaseService.connection);

        return jp ;
    }


    public JasperPrint getItemReport () throws JRException, FileNotFoundException, SQLException {


        JasperReport reports = null ;
        JasperPrint jp = null ;
//        report = JasperCompileManager.compileReport(new FileInputStream(new File("newnew.jrxml")));

//        report = JasperCompileManager.compileReport(new FileInputStream(new File("Cherry_2.jrxml")));


        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/itemReport.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<Item> list = new ArrayList<Item>();

        Item model = new Item();


        int size = model.getInfo().size();

        for (int i=0 ; i < size ; i++) {
            Item temp = new Item();
            temp.setName(model.getInfo().get(i).getName());
            temp.setCode(model.getInfo().get(i).getCode());
            temp.setTypeName(model.getInfo().get(i).getTypeName());

            list.add(temp) ;

        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint print = JasperFillManager.fillReport(reports,null,dataSource);

//        JasperViewer.viewReport(print,false);
        return print ;

    }
    public JasperPrint getBankBranchs (ObservableList<banks> bank) throws JRException, IOException, SQLException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/branchs.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<banks> list = new ArrayList<banks>();
        List<banks> list2 = bank ;

        /*banks model = new banks();


        int size = model.getInfo().size();

        for (int i=0 ; i < size ; i++) {
            banks temp = new banks();
            temp.setName(model.getInfo().get(i).getName());
            temp.setReferenceNumber(model.getInfo().get(i).getReferenceNumber());
            list.add(temp) ;
        }
*/
        languages lang = new languages();

        String data = user.getId()+","+user.getName();
//        ByteArrayInputStream in = new ByteArrayInputStream(createQR(data).toByteArray());

//        Image img = ImageIO.read(in) ;

        HashMap params = new HashMap();
        params.put("REPORT_RESOURCE_BUNDLE", lang.get());
//        params.put("QR",img);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list2);

        JasperPrint print = JasperFillManager.fillReport(reports,params,dataSource);

//        in.close();

        return print ;

    }


    public JasperPrint getAmountReport () throws JRException, FileNotFoundException, SQLException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/amountReport.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<Amount> list = new ArrayList<Amount>();

        Amount model = new Amount();


        int size = model.getInfo().size();

        for (int i=0 ; i < size ; i++) {
            Amount temp = new Amount();

            temp.setId(model.getInfo().get(i).getId());
            temp.setItem(model.getInfo().get(i).getItem());
            temp.setStore(model.getInfo().get(i).getStore());
            temp.setNum(model.getInfo().get(i).getNum());

            list.add(temp) ;

        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint print = JasperFillManager.fillReport(reports,null,dataSource);

//        JasperViewer.viewReport(print,false);
        return print ;

    }

    public JasperPrint getDistrubumentReport (List<Transaction> list) throws JRException, FileNotFoundException, SQLException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/disbursementReport.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

//        List<Transaction> list = new ArrayList<Transaction>();

//        Transaction model = new Transaction();


//        int size = model.getInfoTransactions().size();

      /*  for (int i=0 ; i < size ; i++) {
            Transaction temp = new Transaction();

            temp.setId(model.getInfoTransactions().get(i).getId());
            temp.setItem(model.getInfoTransactions().get(i).getItem());
//            temp.setStore(model.getInfoTransactions().get(i).getStore());
            temp.setBank(model.getInfoTransactions().get(i).getBank());
            temp.setNumber(model.getInfoTransactions().get(i).getNumber());
            temp.setDate(model.getInfoTransactions().get(i).getDate());


            list.add(temp) ;

        }*/


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        JasperPrint print = JasperFillManager.fillReport(reports,null,dataSource);

//        JasperViewer.viewReport(print,false);
        return print ;

    }
    public JasperPrint getSarfReport (List<Transaction> lists) throws JRException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/Saref.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<Transaction> list = new ArrayList<Transaction>();




        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lists);


//        String data = user.getId()+","+user.getName();

//        String data = "ميادة الدلح تقدم . . . " ;

//        ByteArrayInputStream in = new ByteArrayInputStream(QRCode.from(data).stream().);

//        Image img = ImageIO.read() ;

        System.out.println("Default : "+Charset.defaultCharset());
        System.setProperty("file.encoding","UTF-8") ;
        System.out.println("Default : "+Charset.defaultCharset());
        System.out.println(System.getProperty("file.encoding"));

        Image img = null ;


        Alert message = new Alert(Alert.AlertType.ERROR);
      /*  try {
            img = createQR(data) ;
        } catch (IOException e) {
            message.setTitle("Erorr in IOException");
            message.setContentText(e.getMessage());
            message.show();
//            throw new RuntimeException(e);
        } catch (WriterException e) {
            message.setTitle("Error in WriterException");
            message.setContentText(e.getMessage());
            message.show();
//            throw new RuntimeException(e);
        }catch (UnsupportedCharsetException e) {
            message.setTitle("Error in UnsupportedCharsetException");
            message.setContentText(e.getMessage());
            message.show();
            System.out.println(e.getMessage());
        }catch (ExceptionInInitializerError e) {
            message.setTitle("Error in ExceptionInInitializerError");
            message.setContentText(e.getMessage()+" , "+e.getLocalizedMessage());
            message.show();
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
*/


        languages lang = new languages();

        HashMap params = new HashMap();
        params.put("REPORT_RESOURCE_BUNDLE", lang.get());
//        if (img != null)
//        params.put("QR",img);
        params.put("user",user.getName());

        JasperPrint print = JasperFillManager.fillReport(reports,params,dataSource);

        return print ;
    }
    public JasperPrint getDistrubumentReport (List<Transaction> lists,String report) throws JRException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/"+report)
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);

        List<Transaction> list = new ArrayList<Transaction>();




        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lists);


//        String data = user.getId()+","+user.getName();

        String data = "ميادة الدلح تقدم . . . " ;

//        ByteArrayInputStream in = new ByteArrayInputStream(QRCode.from(data).stream().);

//        Image img = ImageIO.read() ;

        System.out.println("Default : "+Charset.defaultCharset());
        System.setProperty("file.encoding","UTF-8") ;
        System.out.println("Default : "+Charset.defaultCharset());
        System.out.println(System.getProperty("file.encoding"));

        Image img = null ;


        Alert message = new Alert(Alert.AlertType.ERROR);
      /*  try {
            img = createQR(data) ;
        } catch (IOException e) {
            message.setTitle("Erorr in IOException");
            message.setContentText(e.getMessage());
            message.show();
//            throw new RuntimeException(e);
        } catch (WriterException e) {
            message.setTitle("Error in WriterException");
            message.setContentText(e.getMessage());
            message.show();
//            throw new RuntimeException(e);
        }catch (UnsupportedCharsetException e) {
            message.setTitle("Error in UnsupportedCharsetException");
            message.setContentText(e.getMessage());
            message.show();
            System.out.println(e.getMessage());
        }catch (ExceptionInInitializerError e) {
            message.setTitle("Error in ExceptionInInitializerError");
            message.setContentText(e.getMessage()+" , "+e.getLocalizedMessage());
            message.show();
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
*/


        languages lang = new languages();

        HashMap params = new HashMap();
        params.put("REPORT_RESOURCE_BUNDLE", lang.get());
//        if (img != null)
//        params.put("QR",img);
        params.put("user",user.getName());

        JasperPrint print = JasperFillManager.fillReport(reports,params,dataSource);

        return print ;
    }
    public JasperPrint getCoffee (Transaction model) throws JRException, IOException, SQLException {


        JasperReport reports = null ;
        JasperPrint jp = null ;

        reports = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/report/Coffee.jrxml")
        );

        jp = JasperFillManager.fillReport(reports,null, DatabaseService.connection);


        reports.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
        Map<String,Object> para = new HashMap<>();

        Image i = ImageIO.read(new File("add.png"));

        para.put("name","name");
        para.put("Pimg",i) ;



        JasperPrint print = JasperFillManager.fillReport(reports,para);
        return print ;

    }

    public BufferedImage createQR(String data) throws IOException,WriterException,UnsupportedCharsetException,ExceptionInInitializerError {

        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "windows-1252");
        hints.put(EncodeHintType.MARGIN, 2);
        hints.put(EncodeHintType.QR_COMPACT,true);
        hints.put(EncodeHintType.FORCE_CODE_SET,"windows-1252");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevelEnum.H) ;

           Hashtable hi = new Hashtable();
           hi.put(EncodeHintType.CHARACTER_SET,Charset.defaultCharset());

            BitMatrix matrix = //new BitMatrix(200,200,data.length(),data.getBytes(StandardCharsets.UTF_8));
                        new MultiFormatWriter().encode(
                        new String(data.getBytes()),BarcodeFormat.QR_CODE, 200, 200,hints);

        return  MatrixToImageWriter.toBufferedImage(matrix);
    }
}