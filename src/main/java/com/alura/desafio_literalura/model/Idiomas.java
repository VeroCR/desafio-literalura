package com.alura.desafio_literalura.model;

public enum Idiomas {
    SPANISH("es", "español"),
    ENGLISH("en", "inglés"),
    FRENCH("fr", "francés"),
    PORTUGUES("pt", "portugués"),
    LATIN("la", "latín");

    private String idiomas;
    private String idiomasEsp;
    Idiomas (String idiomas, String idiomasEsp){
        this.idiomas = idiomas;
        this.idiomasEsp = idiomasEsp;
    }

    public static Idiomas fromString(String text){
        for(Idiomas idioma : Idiomas.values()){
            if(idioma.idiomas.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún lenguaje encontrado: " + text);
    }

    public static Idiomas fromEspanol(String text){
        for(Idiomas idioma : Idiomas.values()){
            if(idioma.idiomasEsp.equalsIgnoreCase(text) || idioma.name().equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún lenguaje encontrado: " + text);
    }

    public static String toEspanol(Idiomas idioma){
        return idioma.idiomasEsp;
    }
}
