package ru.kpfu.kutyavina.models;

public class Product {
    private String id;
    private String name;
    private String path;
    private String type;
    private String link;
    private String description;
    private String composition;

    public Product() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getComposition() {
        return composition;
    }
}
