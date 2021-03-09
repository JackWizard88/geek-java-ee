package ru.geekbrains.krylov;

import ru.geekbrains.krylov.services.ProductServiceRem;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public class EJBClient {

    final static String JNDI_SERVICE_NAME = "ejb:/jsf-app/ProductServiceImpl!ru.geekbrains.krylov.services.ProductServiceRem";


    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();


        ProductServiceRem productService = (ProductServiceRem) context.lookup(JNDI_SERVICE_NAME);

        productService.findAll()
                .forEach(prod -> System.out.println(prod.getId() + "\t" + prod.getTitle() + "\t" + prod.getPrice()));
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EJBClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}
