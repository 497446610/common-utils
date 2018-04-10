package cn.kuangxf.common.utils.ftp;

/**
 * SFTP配置文件
 * @author yuyang
 * @date 2016-08-05
 */
public class SFTPConfig {
	/**主机*/
	private String host;
	/**端口号*/
	private int port;
	/**用户名*/
	private String username;
	/**密码*/
	private String password;
	/**传输模式  OVERWRITE重写  RESUME续传  APPEND 追加*/
	private int mode;
	/**重写*/
	public static final int OVERWRITE=0;
	/**续传*/
	public static final int RESUME=1;
	/**追加*/
	public static final int APPEND=2;
	public SFTPConfig() {
	}
	public SFTPConfig(String host, Integer port) {
		super();
		this.host = host;
		this.port = port;
		setPassword("");
		setUsername("");
		setMode(OVERWRITE);
	}
	public SFTPConfig(String host, Integer port, String username, String password) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		setMode(OVERWRITE);
	}
	
	public SFTPConfig(String host, Integer port, String username, String password, Integer mode) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.mode = mode;
		setMode(mode);
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
}
