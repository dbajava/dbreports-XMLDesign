/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLWizard;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 *
 * @author c954080
 */
@XmlType( propOrder = { "dbName", "fantasyName","hostName", "port", "userName","passw","mailto","hostmail","reports" } )
@XmlRootElement( name = "instance" )
public class Instance {
private String dbName;
private String fantasyName;
private String hostName;
private String port;
private String userName;
private String passw;
private String mailto;
private String hostmail;
List<Report> reports;
private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
private static final byte[] SALT = {
		(byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
		(byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
};

public String getDbName() {
	return dbName;
}
@XmlElement( name = "iname" )
public void setDbName(String dbName) {
	this.dbName = dbName;
}
public String getHostName() {
	return hostName;
}
@XmlElement( name = "hostname" )
public void setHostName(String hostName) {
	this.hostName = hostName;
}
public String getPort() {
	return port;
}
@XmlElement( name = "port" )
public void setPort(String port) {
	this.port = port;
}
public String getUserName() {
	return userName;
}
@XmlElement( name = "username" )
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassw() {
	return passw;
}
@XmlElement( name = "password" )
public void setPassw(String passw) {
	this.passw = passw;
}
public List<Report> getReports() {
	return reports;
}
public void add(Report report){
	if (this.reports==null){
		this.reports=new ArrayList<Report>();
	}
	this.reports.add(report);
}
@XmlElement(name="report")
public void setReports(List<Report> reports) {
	this.reports = reports;
}
public String getMailto() {
	return mailto;
}
@XmlElement(name="mailto")
public void setMailto(String mailto) {
	this.mailto = mailto;
}
public String getHostmail() {
	return hostmail;
}
@XmlElement(name="mailhost")
public void setHostmail(String hostmail) {
	this.hostmail = hostmail;
}
public String getFantasyName() {
	return fantasyName;
}
@XmlElement(name="fname")
public void setFantasyName(String fantasyName) {
	this.fantasyName = fantasyName;
}

public ArrayList<String> getToMail(){
	 StringTokenizer strTkn = new StringTokenizer(this.mailto, ",");
	 ArrayList<String> arrLis = new ArrayList<String>(this.mailto.length());
	 while(strTkn.hasMoreTokens())
	 arrLis.add(strTkn.nextToken());
	 return arrLis;
}
private static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
	SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
	Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
	pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
}
public String getEncryPass(){
	try {
		return Instance.encrypt(passw);
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	} catch (GeneralSecurityException e) {
		e.printStackTrace();
	}
	return "impossible to encrypt";
}
private static String base64Encode(byte[] bytes) {
	// NB: This class is internal, and you probably should use another impl
	return new BASE64Encoder().encode(bytes);
}
@SuppressWarnings("unused")
private static byte[] base64Decode(String property) throws IOException {
	// NB: This class is internal, and you probably should use another impl
	return new BASE64Decoder().decodeBuffer(property);
}
public Instance(String dbName, String fantasyName, String hostName, String port, String userName, String passw,
		String mailto, String hostmail) {
	super();
	this.dbName = dbName;
	this.fantasyName = fantasyName;
	this.hostName = hostName;
	this.port = port;
	this.userName = userName;
	this.passw = passw;
	this.mailto = mailto;
	this.hostmail = hostmail;
}
public Instance(){
	
}
}
