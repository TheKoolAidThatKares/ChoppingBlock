package com.example.dogan.choppingblock;

import java.util.ArrayList;

public class Website {
    String name;
    String author;
    String url;

    ArrayList<String> locations = new ArrayList<String>();
    ArrayList<String> starts = new ArrayList<String>();
    ArrayList<String> ends = new ArrayList<String>();

    public Website(String proposedName,String proposedAuthor, String proposedUrl)
    {
        name = proposedName;
        author = proposedAuthor;
        url = proposedUrl;
    }

    public void addLocation(String proposedLocation, String proposedStart, String proposedEnd)
    {
        locations.add(proposedLocation);
        starts.add(proposedStart);
        ends.add(proposedEnd);
    }
}
