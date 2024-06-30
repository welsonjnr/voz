package service;

import java.lang.reflect.Method;

public class Execute{

    private String className;
    private static final String METHOD_NAME = "execute";
    private static final String PACK_DOMAIN = "domain.";

    public Execute(String className){
        this.className = className;
    }

    public void execute(){
        try{

            Class<?> clazz = Class.forName(this.getClassName(className));

            Object obj = clazz.getDeclaredConstructor().newInstance();

            Method meth = clazz.getMethod(METHOD_NAME);

            meth.invoke(obj);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getClassName(String className){
        className = className.substring(0, 1).toUpperCase() + className.substring(1).toLowerCase();
        return PACK_DOMAIN + className;
    }
}
