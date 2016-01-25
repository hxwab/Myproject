
package csdc.tool.webService.smdb.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import csdc.tool.webService.smdb.jaxws.WebServices;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1 in JDK 6
 * Generated source version: 2.1
 * 
 */

@WebServiceClient(name = "WebServicesService", targetNamespace = "http://server.webService.service.csdc/", wsdlLocation = "http://192.168.88.14:8080/smdb/service/webService?wsdl")
//@WebServiceClient(name = "WebServicesService", targetNamespace = "http://server.webService.service.csdc/", wsdlLocation = "http://csdc.info/smdb/service/webService?wsdl")

public class WebServicesService
    extends Service
{

    private final static URL WEBSERVICESSERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
        	url = new URL("http://192.168.88.14:8080/smdb/service/webService?wsdl");
//            url = new URL("http://csdc.info/smdb/service/webService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WEBSERVICESSERVICE_WSDL_LOCATION = url;
    }

    public WebServicesService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServicesService() {
        super(WEBSERVICESSERVICE_WSDL_LOCATION, new QName("http://server.webService.service.csdc/", "WebServicesService"));
    }

    /**
     * 
     * @return
     *     returns WebServices
     */
    @WebEndpoint(name = "WebServicesPort")
    public WebServices getWebServicesPort() {
        return (WebServices)super.getPort(new QName("http://server.webService.service.csdc/", "WebServicesPort"), WebServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServices
     */
    @WebEndpoint(name = "WebServicesPort")
    public WebServices getWebServicesPort(WebServiceFeature... features) {
        return (WebServices)super.getPort(new QName("http://server.webService.service.csdc/", "WebServicesPort"), WebServices.class, features);
    }

}
