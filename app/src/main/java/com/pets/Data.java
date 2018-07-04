package com.pets;

public class Data {

    private String name;
    private String family;
    private String sex;
    private String poids;

    public Data(String name, String family, String sex, String poids){
        this.name = name;
        this.family = family;
        this.sex = sex;
        this.poids = poids;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getSex() {
        return sex;
    }

    public String getPoids() {
        return poids;
    }
}

