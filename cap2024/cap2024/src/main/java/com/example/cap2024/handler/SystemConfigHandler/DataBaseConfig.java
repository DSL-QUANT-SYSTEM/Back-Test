package com.example.cap2024.handler.SystemConfigHandler;

import com.example.cap2024.handler.SecureHandler.SecureObject;

import java.util.Properties;
import java.util.logging.Logger;

// 1. Java에서는 java.util.logging.Logger를 사용하여 로깅
public class DataBaseConfig {
    // 2. IniHandler 클래스를 포함
    private final IniHandler ini = new IniHandler();

    // 3. 인덱서를 이용하여 프로퍼티 읽기/쓰기
    public String Get(String indexStr) {
        Logger.getGlobal().info(String.format("DataBaseConfig > get prop[%s], INI: %s", indexStr, ini.iniPath));
        return ini.ReadIni("DATABASE", indexStr);
    }

    public void Set(String indexStr, String value) {
        Logger.getGlobal().info(String.format("DataBaseConfig > set prop[%s]=%s", indexStr, value));
        ini.WriteIni("DATABASE", indexStr, value);
    }

    // 4. 기본 생성자
    public DataBaseConfig() {
    }

    // 5. 다른 생성자에서 IniHandler의 iniPath를 직접 설정할 수 있는 생성자
    public DataBaseConfig(String sysConfigPath) {
        ini.iniPath = sysConfigPath;
    }

    // 6. 데이터베이스 연결 문자열 생성 메소드
    public String GetConnectionString(String poolName) {
        SecureObject so = new SecureObject();
        String vendor = this.Get(poolName + "_VENDOR");
        String dbName = this.Get(poolName);
        String svrName = this.Get(poolName + "_SVR");
        String port = this.Get(poolName + "_PORT");
        String uid = this.Get(poolName + "_UID");
        String pwd = this.Get(poolName + "_PWD");
        try {
            pwd = so.GetDecryptString(this.Get(poolName + "_PWD"));
        } catch (Exception e) {
            this.Set(poolName + "_PWD", so.GetEncryptString(pwd));
        }

        return GetConnectionString(vendor, dbName, svrName, port, uid, pwd);
    }

    // 7. 데이터베이스 연결 문자열 생성 메소드
    public String GetConnectionString(String vendor, String dbName, String svrName, String port, String uid, String pwd) {
        Logger.getGlobal().info(String.format("DataBaseConfig > getConnectionString(%s, %s, %s ...)", vendor, dbName, svrName));

        return new StringBuilder("Data Source=").append(svrName).append(",").append(port)
                .append(";Initial Catalog=").append(dbName)
                .append(";User Id=").append(uid)
                .append(";Password=").append(pwd).toString();
    }
    // Get property value for a given key
    public String getProperty(String key) {
        Properties properties= new Properties();
        return properties.getProperty(key);
    }
}
