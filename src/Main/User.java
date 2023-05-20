package Main;

public class User  {
    private String name;
    private String password;

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public  String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object ojb){
        User u = (User) ojb;
        if(this.getName().equals(u.getName()) && this.getPassword().equals(u.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return String.format("%s-%s", this.getName(), this.getPassword());
    }
}
