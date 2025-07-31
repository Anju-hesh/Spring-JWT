package lk.ijse.gdse72.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean {

    @Value("${os.name}")
    private String osName;

    @Value("${user.name}")
    private String userName;

    @Value("${db.name}")
    private String dbName;

    @Value("${db.user}")
    private String dbUser;

    public SpringBean(){
        System.out.println("SpringBean Constructor ...!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Os Name: " + osName);
        System.out.println("User Name: " + userName);
        System.out.println("Db Name: " + dbName);
        System.out.println("Db User: " + dbUser);
    }
}
