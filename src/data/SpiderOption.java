package data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Option: Store spider option data
 * 
 * @author Tomahawkd
 */

public class SpiderOption implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Request headers option contents
	 */
	
	private DefaultListModel<String> requestHeader;
	
	/**
	 * Request headers when requesting a URL
	 */
	
	private Map<String, String> headers;
	
	/**
	 * Remote host name
	 */
	
	private String host;
	
	/**
	 * Connection protocol. Only support <code>http</code> and <code>https</code>.
	 */
	
	private String protocol;
	
	/**
	 * Host-only filter activation option
	 */
	
	private String accessOption;
	
	/**
	 *  Constructor with non-parameter.
	 *  
	 *  All option use preset.
	 *  
	 *  @author Tomahawkd
	 *  
	 */
	
	public SpiderOption() {
		
		host = "";
		protocol = "http";
		accessOption = "Host Only";
		
		//Default request headers
		requestHeader = new DefaultListModel<String>();
		requestHeader.addElement("Connection: close");
		requestHeader.addElement("Accept: */*");
		requestHeader.addElement("User-Agent: "
				+ "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) "
				+ "AppleWebKit/602.3.12 (KHTML, like Gecko) "
				+ "Version/10.0.2 Safari/602.3.12");
		requestHeader.addElement("Accept-Language: zh-cn");
		requestHeader.addElement("Accept-Encoding: gzip");
		
		headers = new HashMap<String, String>();
		headers.put("Connection", "close");
		headers.put("Accept", "*/*");
		headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) "
				+ "AppleWebKit/602.3.12 (KHTML, like Gecko) "
				+ "Version/10.0.2 Safari/602.3.12");
		headers.put("Accept-Language", "zh-cn");
		headers.put("Accept-Encoding", "gzip");
	}
	
	/**
	 * Constructor with parameters.
	 * 
	 * Using it to load user's preference.
	 * 
	 * @param requestHeader Request header using in http transfer.
	 * @param host			The target server.
	 * @param protocol		Access the server with the protocol.
	 * 
	 * @author Tomahawkd
	 */
	
	public SpiderOption(DefaultListModel<String> requestHeader, String host, String protocol) {
		this.requestHeader = requestHeader;
		this.host = host;
		this.protocol = protocol;
	}

	/**
	 * Get current request header to update data in GUI.
	 * 
	 * @return current request header data array.
	 * 
	 * @author Tomahawkd
	 */
	
	public DefaultListModel<String> getRequestHeader() {
		return this.requestHeader;
	}
	
	/**
	 * Get current request header to request.
	 * 
	 * @return current request header data map.
	 * 
	 * @author Tomahawkd
	 */
	
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * Set loaded request header.
	 * ONLY use it to load a preference from file.
	 * 
	 * @param requestHeader set the header from the file.
	 * 
	 * @author Tomahawkd
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
	 * @author Tomahawkd
	 */
	
	public String getHeaderElement(int index) throws ArrayIndexOutOfBoundsException {
		return this.requestHeader.getElementAt(index);
	}
	
	/**
	 * Set a new request header element.
	 * 
	 * @param newHeader a string contains the request header data.
	 * 
	 * @author Tomahawkd
	 */
	
	public void newHeaderElement(String newHeader) {
		this.requestHeader.addElement(newHeader);
		String[] key = newHeader.split(": ");
		headers.put(key[0], key[1]);
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
	 * @author Tomahawkd
	 */
	
	public void editHeaderElement(int index, String header) throws ArrayIndexOutOfBoundsException {
		
		//header in map
		String[] oldHeader = requestHeader.getElementAt(index).split(": ");
		String[] newHeader = header.split(": ");
		
		//Consider the user would edit it completely, use remove and put method rather than replace method
		headers.remove(oldHeader[0], oldHeader[1]);
		headers.put(newHeader[0], newHeader[1]);
		
		//header in listModel
		this.requestHeader.set(index, header);
	}
	
	/**
	 * Remove a exist request header element.
	 * 
	 * @param index index The selected row in JList
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 * 
	 * @author Tomahawkd
	 */
	
	public void removeHeaderElement(int index) throws ArrayIndexOutOfBoundsException {
		String[] oldHeader = requestHeader.getElementAt(index).split(": ");
		this.requestHeader.remove(index);
		headers.remove(oldHeader[0], oldHeader[1]);
	}
	
	/**
	 * Get the destination host.
	 * 
	 * @return host
	 * 
	 * @author Tomahawkd
	 */

	public String getHost() {
		return host;
	}
	
	/**
	 * Set the destination host
	 * 
	 * @param host The destination for spider to access
	 * 
	 * @author Tomahawkd
	 */

	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Get the protocol spider uses.
	 * 
	 * @return protocol
	 * 
	 * @author Tomahawkd
	 */
	
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Set the protocol spider uses.
	 * 
	 * @param protocol Support for http and https
	 * 
	 * @author Tomahawkd
	 */
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	/**
	 * Judging is tend to active host-only filter.
	 * 
	 * @return host-only filter activation flag.
	 * 
	 * @author Tomahawkd
	 */
	
	public boolean isHostOnly() {
		boolean option = true;
		if (accessOption.equals("All Site")) {
			option = false;
		}
		return option;
	}

	/**
	 * Get filter option to refresh GUI.
	 * 
	 * @return host only or all sites
	 * 
	 * @author Tomahawkd
	 */
	
	public String getAccessOption() {
		return accessOption;
	}
	
	/**
	 * Set filter option
	 * 
	 * @param accessOption
	 * 
	 * @author Tomahawkd
	 */
	
	public void serAccessOption(String accessOption) {
		this.accessOption = accessOption;
	}
	
	
}
