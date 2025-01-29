package ioc;

import java.util.HashMap;
import java.util.Map;

public class CDLDemo {
    public static void main(String[] args) {
        Container container = new BeanContainer();
        MessageProvider mp = new HelloWorldMessageProvider();
        container.register(mp ,"provider");
        MessageRenderer mr = new StandardOutMessageRenderer();
        container.register(mr , "renderer");
        mr.performLookup(container);
        mr.render();

    }
}


interface  Container{
    Object getDependency(String key);
    void register(Object obj, String key);
}

class BeanContainer implements Container{
    Map<String, Object> beans = new HashMap<>();
    @Override
    public Object getDependency(String key) {
        if (! beans.containsKey(key)){
            throw  new RuntimeException("NoSuchBeanDefinitionFound");
        }
        return beans.get(key);
    }
    @Override
    public  void register(Object bean , String key){
        beans.put(key, bean);
    }
}

interface ManagedComponent{
    void performLookup(Container container);
}


interface  MessageProvider{
    public String getMessage();
}
class HelloWorldMessageProvider implements MessageProvider{
    @Override
    public  String getMessage(){
        return "Hello CDL World";
    }
}

interface MessageRenderer extends ManagedComponent{
    public void  render();
}

class StandardOutMessageRenderer implements MessageRenderer{
    private MessageProvider messageProvider;
    @Override
    public void performLookup(Container container) {
        messageProvider = (MessageProvider) container.getDependency("provider");
    }
    @Override
    public  void render(){
        System.out.println(messageProvider.getMessage());
    }
}