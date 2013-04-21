/**
 * 
 */
package API.Hotel;



import utils.ConfigData;
import utils.SignatureUtils;
import API.comom.URLconfig;
import API.comom.commonRequest;

import base.HttpAccessAdapter;

/**
 * @author Minghui Dai
 *
 */
public class hotelSearch extends HttpAccessAdapter{
    
    
    

    private  boolean AvailableOnlyIndicator ;

    private  int AreaID;

    private  int HotelCityCode;
  
    private  String HotelName="";

    private  String Provider="";
 

    private  float Rating;


    private String ResponseXML="";

    public  hotelSearch(boolean AvailableOnlyIndicator , int AreaID,int HotelCityCode,String HotelName,String Provider,float Rating) 
    {
        this.AvailableOnlyIndicator = AvailableOnlyIndicator;
        this.AreaID = AreaID;
        this.HotelCityCode = HotelCityCode;
        this.HotelName = HotelName;
        this.Provider = Provider;
        this.Rating = Rating;
    }

    
    final public  String getRequestXML() {
        
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("<?xml version=\"1.0\"?>");
            sb.append("<Request><Header AllianceID=\"");
            sb.append(ConfigData.AllianceId);
            sb.append("\" SID=\"");
            sb.append(ConfigData.SId);
            sb.append("\" TimeStamp=\"");
            long timestamp = SignatureUtils.GetTimeStamp();
            sb.append(timestamp);
            sb.append("\" Signature=\"");
    
            String signature = SignatureUtils.CalculationSignature(timestamp
                    + "", ConfigData.AllianceId, ConfigData.SecretKey, ConfigData.SId, "OTA_HotelSearch");
            sb.append(signature);
            sb.append("\" RequestType=\"");
            sb.append("OTA_HotelSearch");
            sb.append("<HotelRequest><RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><ns:OTA_HotelSearchRQ Version=\"");
            sb.append(ConfigData.verison);
            sb.append("\" PrimaryLangID=\"");
            sb.append(ConfigData.LANG);
            sb.append("\" xsi:schemaLocation=\"http://www.opentravel.org/OTA/2003/05 OTA_HotelSearchRQ.xsd\" xmlns=\"http://www.opentravel.org/OTA/2003/05\"><ns:Criteria ");
        	sb.append("AvailableOnlyIndicator=\"");
            if(AvailableOnlyIndicator==true)
                sb.append("true\">");
            else {
                sb.append("false\">");
            }
            sb.append("<ns:Criterion><ns:HotelRef ");
            sb.append("HotelCityCode=\"");
            String  hotelCityCodeString = intToString(HotelCityCode); 
            sb.append(hotelCityCodeString);
            sb.append("\" AreaID=\"");
            String  areaIDString = intToString(AreaID); 
            sb.append(areaIDString);
            sb.append("\" HotelName=\"");
            sb.append(HotelName);
            sb.append("\"/><ns:Award ");
            sb.append("Provider=\"");
            sb.append(Provider);
            sb.append("\" Rating=\"");
            String ratingString = floatToString(Rating);
            sb.append(ratingString);
            sb.append("\"/></ns:Criterion></ns:Criteria></ns:OTA_HotelSearchRQ></RequestBody></HotelRequest></Request>");
            
            
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return sb.toString();
        
        
    }

   public  void main(){
       try{
           String requestXML=null;
           String returnXML=null;
           
           requestXML=getRequestXML();
           
           commonRequest commonRequestDo=new commonRequest(URLconfig.D_HotelSearch_Url,URLconfig.System_RequestType,requestXML);
           returnXML =commonRequestDo.doRequest();
 
        
           //this.ResponseXML=getXMLFromReturnString(returnXML);
       }
       catch(Exception e)
       {
           this.ResponseXML=null;
       }
   }
}
