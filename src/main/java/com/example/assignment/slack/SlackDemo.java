package com.example.assignment.slack;

import com.example.util.Util;

public class SlackDemo {

    public static void main(String[] args) {


        SlackRoom slackRoom = new SlackRoom("Hostel K Launde");

        SlackMember sam = new SlackMember("sam");
        SlackMember mike = new SlackMember("mike");
        SlackMember jake = new SlackMember("jake");

        slackRoom.join(sam);
        slackRoom.join(mike);

        sam.send("hi");
        Util.sleep(3);

        mike.send("hello");
        sam.send("how are ya");
        Util.sleep(2);

        slackRoom.join(jake);
        jake.send("sorry m late");

    }
}
