package com.example.classtonomeram;
public class PdfClass {
    public String name;
    public String url;

    public PdfClass() {

    }

    public PdfClass(String name) {
        this.name = name;
    }

    public PdfClass(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString() {
        return name;
    }
}
