/**
 * 
 */
package API.comom;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;
import base.HttpAccessAdapter;
import base.SdkSystemException;

/**
 * @author jim.yang 2013.4.19 本系统中统一处理数据请求的类：httpRequest请求或者SOAP请求 
 *
 *
 */
public class commonRequest {


       private String requestURL ;
       /*请求的服务地址，不带上?WSDL*/
  
       private String requestType;
       /*文件中配置的请求模式常量  请求的服务类型，是httpRequest/soap*/
     
       
       private String requestXML;
       /*请求体（如果是SOAP请求，不用带上SOAP头信息）*/
  
       private String responseXML;
      /*返回体数据，如果返回结果是空，则返回null*/
       
     public commonRequest(String requestURL,String requestType,String requestXML)  
     {
         this.requestURL=requestURL;
         this.requestType=requestType;
         this.requestXML=requestXML;
     }
       
       
       /**
        *
        * 根据不同的请求类型，做SOAP获取HTTP请求
        */
       public String doRequest(){
           String responseXmlTemp="";//保存临时的返回结果
           
           if(this.requestType==this.getHttpTypeName())
           {
 
               responseXmlTemp=this.httpRequestSoapData(this.requestURL,this.requestXML);
           }
           else if(this.requestType==this.getSoapTypeName())
           {
               //String parameters=null;
               //parameters=this.requestXML;
               
               //responseXmlTemp=getDataFromSoap(this.requestURL+"?WSDL","Request",parameters);
              // responseXmlTemp=$responseXmlTemp->RequestResult;
           }
           else {
               //echo "3";
               responseXmlTemp=null;
           }
           return this.responseXML=responseXmlTemp;
       }
       /**
        *
        * @var string 返回soap的名称
        */
       public String getSoapTypeName()
       {
           return "soap";
       }
       /**
        *
        * @var string 返回httpRequest的名称
        */
       public String getHttpTypeName()
       {
           return "httpRequest";
       }
       

       
       
       
       /**
        * 2013年4月19日 jim.yang
        * 通过httpRequest调用远程webservice服务（返回一个XML）
        * @param responseUrl 远程服务的地址
        * @param requestXML 远程服务的参数请求体XML
        * @param 返回XML
        */
       private  String httpRequestSoapData(String responseUrl,String requestXML)
       {
          String TemprequestXML;
          String responseBodys;
       
        try{
               HttpRequest myhttp = new HttpRequest(responseUrl+"?WSDL","POST");
               

               TemprequestXML=requestXML.replaceAll("<","&lt;");       
               TemprequestXML=TemprequestXML.replaceAll(">","&gt;");
 
               
               TemprequestXML=AddSoapShell(TemprequestXML);
               
               
               myhttp.open();
               myhttp.send(TemprequestXML);
               
               
               
               responseBodys=myhttp.getResponseBody();//这里有可能有HEARD，要判断一下
               
         /*      if(strpos(responseBodys,"Content-Type: text/xml; charset=utf-8"))
               {
                   coutw=myhttp.responseBodyWithoutHeader;
               }
               else{
                   $coutw=$responseBodys;
               }
              */

               //$myhttp->responseBodyWithoutHeader;
               //$coutw=$myhttp->responseBodyWithoutHeader;
               
               responseBodys=AddSoapShell(responseBodys);
        
               responseBodys = responseBodys.replaceAll("&lt;", "<");
               responseBodys = responseBodys.replaceAll("&gt;", ">");
                   
               // echo $coutw;
            return responseBodys;
           }catch (Exception ex) {
               throw new RuntimeException(ex);}
         
           }
        
        private static String RemoveSoapShell(String source) {
            String result = "";
            int indexElementBegin = source.indexOf("<RequestResult>");
            int indexElementEnd = source.indexOf("</RequestResult>");
            if (indexElementBegin > 0 && indexElementEnd > 0) {
                result = source.substring(indexElementBegin
                        + "<RequestResult>".length(), indexElementEnd);
            }
            return result;
        }
        private static String AddSoapShell(
                /* String parameterName, */String patameterValue) throws Exception {
            StringBuilder soapShellStringBuilder = null;
             Log.d("mydebug","HttpAccessAdapter-----AddSoapShell--------->in");
            try {
               
                soapShellStringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><Request xmlns=\"http://ctrip.com/\"><requestXML>string</requestXML></Request></soap:Body></soap:Envelope>");
            
                String result = soapShellStringBuilder.toString();
                return result.replaceAll("string", patameterValue);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
                
            } finally {
                Log.d("mydebug","others");
                }
            }
            
}
        
        

