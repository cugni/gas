// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import it.polito.ai.gas.business.Proposal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Proposal_Roo_Json {
    
    public static Proposal Proposal.fromJsonToProposal(String json) {
        return new JSONDeserializer<Proposal>().use(null, Proposal.class).deserialize(json);
    }
    
    public static String Proposal.toJsonArray(Collection<Proposal> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Proposal> Proposal.fromJsonArrayToProposals(String json) {
        return new JSONDeserializer<List<Proposal>>().use(null, ArrayList.class).use("values", Proposal.class).deserialize(json);
    }
    
}
