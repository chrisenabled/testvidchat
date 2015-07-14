package com.f80prototype.verizon.protoandroid;

import java.util.ArrayList;

/**
 * Created by inyanja on 7/13/15.
 */
public class ConversationDataStructure {
    ArrayList<String> convData = new ArrayList<String>();

    public void getConversationGrp(){
        //Todo after server interface is ready
    }

    public ArrayList<String> myDataset(){
        convData.add(0,"hardy_grp1");
        convData.add(1, "testing 2");
        convData.add(2, "testing 3");
        convData.add(3,"hardy_grp1457685");
        convData.add(4, "testing nuisdf8382");
        convData.add(5, "testing 3kjsdiofmkn");
        return convData;
    }
}
