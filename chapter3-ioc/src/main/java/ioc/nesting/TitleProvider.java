package ioc.nesting;


public class TitleProvider {
    private String title = "Gravity";
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title  = title;
    }

    public static TitleProvider instance(String title){
        TitleProvider childProvider = new TitleProvider();
        if(!(title == null || title.trim().isEmpty())){
            childProvider.setTitle(title);
        }
        return childProvider;
    }
}
