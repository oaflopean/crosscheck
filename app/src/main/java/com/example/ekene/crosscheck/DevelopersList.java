package com.example.ekene.crosscheck;

/**
 * Created by EKENE on 7/23/2017.
 */

public class DevelopersList {

    private String login;
    private String avatar_url;
    private String html_url;
    private String text;
    private String epub;
    private String id;


    public String getLogin() {
        return login;
    }

    public String getText() {
        return text;
    }

    public String getEpub() {
        return epub;
    }

    public String getId() {
        return id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public DevelopersList(String login, String author, String text, String epub, String id) {
        this.login = login;
        this.html_url=author;
        this.text=text;
        this.epub=epub;
        this.id=id;

    }
}
