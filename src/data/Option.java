package data;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Option: Store option data
 * 
 * @author Ghost
 */

public class Option {
	
	private DefaultListModel<String> requestHeader;
	private int portOption;
	private int portSpider;
	private String host;
	private String protocol;
	
	/**
	 *  Constructor with non-parameter.
	 *  
	 *  All option use preset.
	 *  
	 *  @author Ghost
	 *  
	 */
	
	public Option() {
		requestHeader = new DefaultListModel<String>();
		portSpider = 80;
		portOption = 8080;
		host = "";
		protocol = "http";
		
		requestHeader.addElement("Connection: close");
		requestHeader.addElement("Accept: */*");
		requestHeader.addElement("User-Agent: "
				+ "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) "
				+ "AppleWebKit/602.3.12 (KHTML, like Gecko) "
				+ "Version/10.0.2 Safari/602.3.12");
		requestHeader.addElement("Accept-Language: zh-cn");
		requestHeader.addElement("Accept-Encoding: gzip");
	}
	
	/**
	 * Constructor with parameters.
	 * 
	 * Using it to load user's preference.
	 * 
	 * @param requestHeader Request header using in http transfer.
	 * @param portOption	Intercepter listener port preference.
	 * @param portSpider	Access the server with the port.
	 * @param host			The target server.
	 * @param protocol		Access the server with the protocol.
	 * 
	 * @author Ghost
	 */
	
	public Option(DefaultListModel<String> requestHeader, int portOption, int portSpider, String host,
			String protocol) {

		this.requestHeader = requestHeader;
		this.portSpider = portSpider;
		this.portOption = portOption;
		this.host = host;
		this.protocol = protocol;
	}

	/**
	 * Get current request header.
	 * 
	 * @return current request header data array.
	 * 
	 * @author Ghost
	 */
	
	public DefaultListModel<String> getRequestHeader() {
		return this.requestHeader;
	}

	/**
	 * Set loaded request header.
	 * ONLY use it to load a preference from file.
	 * 
	 * @param requestHeader set the header from the file.
	 * 
	 * @author Ghost
	 */
	
	public void setRequestHeader(DefaultListModel<String> requestHeader) {
		this.requestHeader = requestHeader;
	}
	
	/**
	 * Get current selected request header element in JList.
	 * 
	 * @param index The selected row in JList
	 * 
	 * @return request header element
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * 
	 * @see JList
	 * 
	 * @author Ghost
	 */
	
	public String getHeaderElement(int index) throws ArrayIndexOutOfBoundsException {
		return this.requestHeader.getElementAt(index);
	}
	
	/**
	 * Set a new request header element.
	 * 
	 * @param newHeader a string contains the request header data.
	 * 
	 * @author Ghost
	 */
	
	public void newHeaderElement(String newHeader) {
		this.requestHeader.addElement(newHeader);
	}
	
	/**
	 * Edit a exist request header element.
	 * 
	 * @param index The selected row in JList
	 * @param header The newer header.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * 
	 * @see JList
	 * 
	 * @author Ghost
	 */
	
	public void editHeaderElement(int index, String header) throws ArrayIndexOutOfBoundsException {
		this.requestHeader.set(index, header);
	}
	
	/**
	 * Remove a exist request header element.
	 * 
	 * @param index index The selected row in JList
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * 
	 * @author Ghost
	 */
	
	public void removeHeaderElement(int index) throws ArrayIndexOutOfBoundsException {
		this.requestHeader.remove(index);
	}

	/**
	 * Get the port preference correspond to Intercepter.
	 * 
	 * @return port
	 * 
	 * @author Ghost
	 */
	
	public int getPortOption() {
		return portOption;
	}

	/**
	 * Set port preference correspond to Intercepter.
	 * 
	 * @param portOption a port to listen and intercept
	 * 
	 * @author Ghost
	 */
	
	public void setPortOption(int port) {
		this.portOption = port;
	}
	
	/**
	 * Get port preference correspond to Spider.
	 * 
	 * @return port
	 * 
	 * @author Ghost
	 */

	public int getPortSpider() {
		return portSpider;
	}

	/**
	 * Set port preference correspond to spider.
	 * 
	 * @param portOption Generally 80 for http and 443 for https
	 * 
	 * @author Ghost
	 */
	
	public void setPortSpider(int portSpider) {
		this.portSpider = portSpider;
	}
	
	/**
	 * Get the destination host.
	 * 
	 * @return host
	 * 
	 * @author Ghost
	 */

	public String getHost() {
		return host;
	}
	
	/**
	 * Set the destination host
	 * 
	 * @param host The destination for spider to access
	 * 
	 * @author Ghost
	 */

	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Get the protocol spider uses.
	 * 
	 * @return protocol
	 * 
	 * @author Ghost
	 */
	
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Set the protocol spider uses.
	 * 
	 * @param protocol Support for http and https
	 * 
	 * @author Ghost
	 */
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	
	
	
}
