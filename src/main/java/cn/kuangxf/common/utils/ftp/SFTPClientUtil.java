package cn.kuangxf.common.utils.ftp;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * SFTP客户端
 * 
 * @author yuyang
 * @date 2016-08-05
 */
public class SFTPClientUtil {
	/**
	 * SFT配置信息
	 */
	private SFTPConfig sftpConfig;
	/**
	 * SFTP通道
	 */
	private ChannelSftp sftp;

	/**
	 * 连接服务器
	 * 
	 * @return ChannelSftp
	 */
	private ChannelSftp connect() {
		try {
			JSch jsch = new JSch();
			Session sshSession = jsch.getSession(getSftpConfig().getUsername(), getSftpConfig().getHost(),
					getSftpConfig().getPort());
			System.out.println("会话 已经创建！");
			sshSession.setPassword(getSftpConfig().getPassword());
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.setTimeout(3*60*1000);
			sshSession.connect();
			System.out.println("会话连接成功！");
			System.out.println("打开Channel!");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("连接到 " + getSftpConfig().getHost() + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sftp;
	}

	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp 
	 * @throws IOException 
	 * @throws JSchException 
	 * @return true 上传成功 false 上传失败
	 */
	public boolean upload(String directory, String uploadFile) throws IOException, JSchException {
		try {
			connect();
			sftp.cd(directory);
			File file = new File(uploadFile);
//			sftp.put(new FileInputStream(file), file.getName(),ChannelSftp.RESUME);
			FileProgressMonitor monitor = new FileProgressMonitor(file.length());
			sftp.put(new FileInputStream(file), file.getName(), monitor, sftpConfig.getMode());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			//关闭写入流
			sftp.getOutputStream().close();
			//关闭输出流
			sftp.getInputStream().close();
			//关闭会话
			sftp.getSession().disconnect();
			//关闭SFTP通道
			sftp.disconnect();
			System.out.println("和服务器断开了连接。");
		}
		return true;
	}
	
	
	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            本地的目录
	 * @param remoteDir 远程目录
	 * @param remoteFlie
	 *            远程服务器的文件
	 * @param sftp 
	 * @throws IOException 
	 * @throws JSchException 
	 * @return true 上传成功 false 上传失败
	 */
	public boolean download(String directory,String remoteDir, String remoteFlie) throws Exception {
		try {
			connect();
			sftp.cd(remoteDir);
			File file = new File(remoteFlie);
//			sftp.put(new FileInputStream(file), file.getName(),ChannelSftp.RESUME);
			FileProgressMonitor monitor = new FileProgressMonitor(file.length());
			sftp.get(remoteFlie,directory , monitor, sftpConfig.getMode());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			//关闭写入流
			sftp.getOutputStream().close();
			//关闭输出流
			sftp.getInputStream().close();
			//关闭会话
			sftp.getSession().disconnect();
			//关闭SFTP通道
			sftp.disconnect();
			System.out.println("和服务器断开了连接。");
		}
		return true;
	}
	
	
	/**
	 * 删除指定目录的文件 
	 * @param dir 文件目录
	 * @param filename 文件名
	 * @return true 成功  false-失败
	 * @throws Exception 异常
	 */
	public boolean deleteFile(String dir,String filename) throws Exception {
		return deleteFile(dir,filename,null);
	}
	
	/**
	 * 删除指定目录的文件
	 * @param dir 文件目录
	 * @param filename 文件名
	 * @param suffix 后缀
	 * @return true 成功  false-失败
	 */
	public boolean deleteFile(String dir,String filename,String suffix) throws Exception {
		boolean flag = false;
		try {
			connect();
			
			if(dir == null || dir.trim().equals("")){
				return flag;
			}
			
			try {
				this.sftp.cd(dir);
				flag = true;
			} catch (Exception e) {
				throw new Exception("切换目录失败",e);
			}
			
			if(!flag){
				return false;
			}else{
				flag = false;
			}
			
			if(filename == null || filename.trim().equals("")){
				return flag;
			}
			
			String deleteFileName = null;
			
			if(suffix != null && !suffix.trim().equals("")){
			
				deleteFileName = filename + "." + suffix;
			
			}else{
				
				deleteFileName = filename;
			
			}
			
			this.sftp.rm(deleteFileName);
			
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("删除文件失败",e);
		} finally {
			//关闭写入流
			sftp.getOutputStream().close();
			//关闭输出流
			sftp.getInputStream().close();
			//关闭会话
			sftp.getSession().disconnect();
			//关闭SFTP通道
			sftp.disconnect();
			System.out.println("和服务器断开了连接。");
		}
		return flag;
	}
	
	
	/**
	 * 删除目录
	 * @param dir 目录
	 * @return true 删除成功  false 删除失败
	 * @throws Exception 异常
	 */ 
	public boolean deletePath(String dir) throws Exception{
		boolean flag = false;
		try {
			connect();
			if(dir == null || dir.trim().equals("")){
				return flag;
			}
			
			this.sftp.rmdir(dir);
			flag = true;
		}catch(Exception e){
			throw new Exception("删除目录失败",e);
		}finally {
			//关闭写入流
			sftp.getOutputStream().close();
			//关闭输出流
			sftp.getInputStream().close();
			//关闭会话
			sftp.getSession().disconnect();
			//关闭SFTP通道
			sftp.disconnect();
			System.out.println("和服务器断开了连接。");
		}
		return flag;
	}
	
	/**
	 * 创建文件目录
	 * @param dir 
	 * @return
	 * @throws Exception
	 */
	public boolean createPath(String dirPath) throws Exception {
		boolean flag = false;
		try{
			connect();
			String[] folders = dirPath.split( "/|\\\\" );  
			
			for ( String folder : folders ) {  
			
				if ( folder.length() > 0 ) {  
			    
					try {  
			            
						sftp.cd( folder );  
			        
					}catch ( SftpException e ) {  
			        
			        	sftp.mkdir( folder );  
			            
			        	sftp.cd( folder );  
			        }  
			    }  
			}  
			
			flag = true;
		
		}catch(Exception e){
			throw new Exception("创建路径失败" ,e);
		}finally{
			//关闭写入流
			sftp.getOutputStream().close();
			//关闭输出流
			sftp.getInputStream().close();
			//关闭会话
			sftp.getSession().disconnect();
			//关闭SFTP通道
			sftp.disconnect();
			System.out.println("和服务器断开了连接。");
		}
		return flag;
	}
	
	public SFTPConfig getSftpConfig() throws Exception  {
		return sftpConfig;
	}

	public void setSftpConfig(SFTPConfig sftpConfig) {
		this.sftpConfig = sftpConfig;
	}
}
