package me.byungjin.Managers;

public class CertificationManager {
	private static String auth = null;
	private static String clientAuth = null;
	private static String clientSecretAuth = null;
	private static String authIRC = null;
	
	public static boolean isCheck() {
		if(auth == null)return false;
		if(clientAuth == null) return false;
		if(clientSecretAuth == null) return false;
		if(authIRC == null) return false;
		return true;
	}
	
	public static String getAuth() {
		return auth;
	}
	public static void setAuth(String auth) {
		CertificationManager.auth = auth;
	}
	public static String getClientAuth() {
		return clientAuth;
	}
	public static void setClientAuth(String clientAuth) {
		CertificationManager.clientAuth = clientAuth;
	}
	public static String getClientSecretAuth() {
		return clientSecretAuth;
	}
	public static void setClientSecretAuth(String clientSecretAuth) {
		CertificationManager.clientSecretAuth = clientSecretAuth;
	}
	public static String getAuthIRC() {
		return authIRC;
	}
	public static void setAuthIRC(String authIRC) {
		CertificationManager.authIRC = authIRC;
	}	
}
