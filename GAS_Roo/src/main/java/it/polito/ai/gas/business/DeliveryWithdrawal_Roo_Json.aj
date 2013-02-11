// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import it.polito.ai.gas.business.DeliveryWithdrawal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect DeliveryWithdrawal_Roo_Json {
    
    public String DeliveryWithdrawal.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static DeliveryWithdrawal DeliveryWithdrawal.fromJsonToDeliveryWithdrawal(String json) {
        return new JSONDeserializer<DeliveryWithdrawal>().use(null, DeliveryWithdrawal.class).deserialize(json);
    }
    
    public static String DeliveryWithdrawal.toJsonArray(Collection<DeliveryWithdrawal> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<DeliveryWithdrawal> DeliveryWithdrawal.fromJsonArrayToDeliveryWithdrawals(String json) {
        return new JSONDeserializer<List<DeliveryWithdrawal>>().use(null, ArrayList.class).use("values", DeliveryWithdrawal.class).deserialize(json);
    }
    
}