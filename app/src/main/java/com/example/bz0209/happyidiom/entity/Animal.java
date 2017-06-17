package com.example.bz0209.happyidiom.entity;

/**
 * Created by Administrator on 2017/4/27.
 */

public class Animal {
    private int id;
    private String name;//成语名称
    private String pronounce;//成语发音
    private String explain;//成语解释
    private String antonym;//反义词
    private String homoionym;//同义词
    private String derivation;//源自
    private String examples;//例子

    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }

    public Animal(int id, String name, String pronounce, String explain, String antonym, String homoionym, String derivation, String examples) {
        this.id = id;
        this.name = name;
        this.pronounce = pronounce;
        this.explain = explain;
        this.antonym = antonym;
        this.homoionym = homoionym;
        this.derivation = derivation;
        this.examples = examples;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPronounce() {
        return pronounce;
    }
    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }
    public String getExplain() {
        return explain;
    }
    public void setExplain(String explain) {
        this.explain = explain;
    }
    public String getAntonym() {
        return antonym;
    }
    public void setAntonym(String antonym) {
        this.antonym = antonym;
    }
    public String getHomoionym() {
        return homoionym;
    }
    public void setHomoionym(String homoionym) {
        this.homoionym = homoionym;
    }
    public String getDerivation() {
        return derivation;
    }
    public void setDerivation(String derivation) {
        this.derivation = derivation;
    }
    public String getExamples() {
        return examples;
    }
    public void setExamples(String examples) {
        this.examples = examples;
    }
}
