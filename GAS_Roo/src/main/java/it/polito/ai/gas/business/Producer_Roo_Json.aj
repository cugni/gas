// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import it.polito.ai.gas.business.Producer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Producer_Roo_Json {
    
    public String Producer.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public String Producer.toJson(String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
    }
    
    public static Producer Producer.fromJsonToProducer(String json) {
        return new JSONDeserializer<Producer>().use(null, Producer.class).deserialize(json);
    }
    
    public static String Producer.toJsonArray(Collection<Producer> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static String Producer.toJsonArray(Collection<Producer> collection, String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Producer> Producer.fromJsonArrayToProducers(String json) {
        return new JSONDeserializer<List<Producer>>().use(null, ArrayList.class).use("values", Producer.class).deserialize(json);
    }
    
}
