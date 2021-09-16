
package ocpp.cs._2015._10;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * The Central System Service for the Open Charge Point Protocol
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.4-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "CentralSystemService", targetNamespace = "urn://Ocpp/Cs/2015/10/", wsdlLocation = "http://localhost/OCPP_CentralSystemService_1.6.wsdl")
public class CentralSystemService_Service
    extends Service
{

    private final static URL CENTRALSYSTEMSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(ocpp.cs._2015._10.CentralSystemService_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = ocpp.cs._2015._10.CentralSystemService_Service.class.getResource(".");
            url = new URL(baseUrl, "http://localhost/OCPP_CentralSystemService_1.6.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost/OCPP_CentralSystemService_1.6.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        CENTRALSYSTEMSERVICE_WSDL_LOCATION = url;
    }

    public CentralSystemService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CentralSystemService_Service() {
        super(CENTRALSYSTEMSERVICE_WSDL_LOCATION, new QName("urn://Ocpp/Cs/2015/10/", "CentralSystemService"));
    }

    /**
     * 
     * @return
     *     returns CentralSystemService
     */
    @WebEndpoint(name = "CentralSystemServiceSoap12")
    public CentralSystemService getCentralSystemServiceSoap12() {
        return super.getPort(new QName("urn://Ocpp/Cs/2015/10/", "CentralSystemServiceSoap12"), CentralSystemService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CentralSystemService
     */
    @WebEndpoint(name = "CentralSystemServiceSoap12")
    public CentralSystemService getCentralSystemServiceSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("urn://Ocpp/Cs/2015/10/", "CentralSystemServiceSoap12"), CentralSystemService.class, features);
    }

}