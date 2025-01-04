package org.spark.tutorial;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;


public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .master("local")
                .appName("Simple Application")
                .getOrCreate();

        try (JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext())) {
            JavaRDD<dataSchema> data = jsc.textFile("src/main/resources/data.txt")
                    .zipWithIndex()
                    .filter(line -> line._2() > 2)
                    .map(Tuple2::_1)
                    .filter(line -> !line.isEmpty())
                    .map(line -> {
                        String[] parts = line.split(" +");
                        dataSchema dataSchema = new dataSchema();
                        try {
                            dataSchema.setName(parts[0]);
                            dataSchema.setVal(parts[1]);
                        } catch (Exception e) {
                            System.out.println("Error: " + e);
                        }
                        return dataSchema;
                    });

            Dataset<dataSchema> ds = spark.createDataset(data.rdd(), Encoders.bean(dataSchema.class));

            ds.show(10, false);

            spark.stop();
        }
    }
}