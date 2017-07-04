package com.yajac.kakfa.reader;

/**
 * Test for Kafka Reader
 * Created by ian.mcewan on 7/1/17.
 */
public class KafkaReaderTest {

    @org.junit.Test
    public void read() throws Exception {
        KafkaReader reader = new KafkaReader();
        reader.read();
    }


}